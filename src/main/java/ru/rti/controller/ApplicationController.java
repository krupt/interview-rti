package ru.rti.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class ApplicationController {

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public String getAppName() {
		return "Hello, world!<br>Привет, мир!";
	}

}