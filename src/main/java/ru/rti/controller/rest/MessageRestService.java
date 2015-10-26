package ru.rti.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ru.rti.controller.CommonApplicationController;
import ru.rti.model.Message;
import ru.rti.service.MessageService;

@RestController
@RequestMapping(CommonApplicationController.SERVICE_PATH + "message")
public class MessageRestService {

	private final MessageService messageService;

	@Autowired
	public MessageRestService(MessageService messageService) {
		this.messageService = messageService;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Page<Message> getMessages() {
		Pageable page = new PageRequest(0, 10);
		return messageService.findAll(page);
	}

}