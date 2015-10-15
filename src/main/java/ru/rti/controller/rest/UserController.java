package ru.rti.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ru.rti.model.User;
import ru.rti.service.UserService;

@RestController
@RequestMapping("/user/service")
public class UserController {

	private final UserService service;

	@Autowired
	public UserController(UserService userService) {
		service = userService;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Iterable<User> getAllUsers() {
		return service.findAll();
	}

}