package ru.rti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ru.rti.model.Message;
import ru.rti.model.User;
import ru.rti.service.MessageService;
import ru.rti.service.UserService;
import ru.rti.service.util.CurrentUser;

@Controller
@RequestMapping(MessageController.URL)
public class MessageController {

	public static final String PATH = "message";
	public static final String URL = "/" + PATH;
	private final UserService userService;
	private final MessageService messageService;

	@Autowired
	public MessageController(UserService userService, MessageService messageService) {
		this.userService = userService;
		this.messageService = messageService;
	}

	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView getListPage() {
		ModelAndView modelAndView = new ModelAndView(PATH + ".list");
		modelAndView.addObject("messages", messageService.findAll());
		return modelAndView;
	}

	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/send", method = RequestMethod.GET)
	public ModelAndView getSendPage() {
		ModelAndView modelAndView = new ModelAndView(PATH + ".send");
		modelAndView.addObject("recipients", userService.findAll());
		return modelAndView;
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Message createMessage(@RequestParam(required = true) long recipient, @RequestParam(required = true) String topic, @RequestParam(required = true) String message) {
		/* 
		 * Сделано для того, чтобы было видно картинку загрузки и надпись "Подождите..."
		 * В реальном проекте - убираем :)
		*/
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
		}
		Message newMessage = new Message();
		newMessage.setSender(new User(((CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId()));
		newMessage.setRecipient(new User(recipient));
		newMessage.setTopic(topic);
		newMessage.setMessage(message);
		return messageService.save(newMessage);
	}

}