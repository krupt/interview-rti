package ru.rti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ru.rti.service.UserService;

@Controller
@RequestMapping("/message")
public class MessageController {

	private final UserService userService;

	@Autowired
	public MessageController(UserService userService) {
		this.userService = userService;
	}

	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView getListPage() {
		ModelAndView modelAndView = new ModelAndView("message.list");
		return modelAndView;
	}

	@PreAuthorize("isAuthenticated()")
	@RequestMapping(method = RequestMethod.GET, value = "/send")
	public ModelAndView getSendPage() {
		ModelAndView modelAndView = new ModelAndView("message.send");
		modelAndView.addObject("recipients", userService.findAll());
		return modelAndView;
	}

}