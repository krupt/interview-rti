package ru.rti.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CommonApplicationController {

	@RequestMapping(method = RequestMethod.GET, value = {"/", "/index"})
	public String getIndexPage() {
		return "index";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/login")
	public String getLoginPage() {
		return "login";
	}

}