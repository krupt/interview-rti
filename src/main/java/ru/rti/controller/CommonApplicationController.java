package ru.rti.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CommonApplicationController {

	@RequestMapping(method = RequestMethod.GET, value = {"/", "/index"})
	public String getIndexPage() {
		return "index";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/login")
	public ModelAndView getLoginPage(HttpServletRequest request) {
		ModelAndView modelView = new ModelAndView("login");
		Object securityException = request.getSession().getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
		if (securityException != null)
			if (securityException instanceof BadCredentialsException)
				modelView.addObject("error", "Неверное имя пользователя/пароль");
			else
				modelView.addObject("error", "Непредвиденная ошибка. Пожалуйста, обратитесь к администратору");
		return modelView;
	}

}