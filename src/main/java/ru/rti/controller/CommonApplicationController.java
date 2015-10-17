package ru.rti.controller;

import javax.servlet.http.HttpSession;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
	public ModelAndView getLoginPage(HttpSession session) {
		ModelAndView modelAndView = new ModelAndView("login");
		Object securityException = session.getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
		if (securityException != null)
			if (securityException instanceof BadCredentialsException || securityException instanceof UsernameNotFoundException)
				modelAndView.addObject("error", "Неверное имя пользователя/пароль");
			else
				modelAndView.addObject("error", "Непредвиденная ошибка. Пожалуйста, обратитесь к администратору");
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/about")
	public String getAboutInfo() {
		return "about";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/403")
	public String getAccessDeniedPage() {
		return "403";
	}

	@PreAuthorize("isAuthenticated()")
	@RequestMapping(method = RequestMethod.GET, value = "/send")
	public ModelAndView getSendPage() {
		ModelAndView modelAndView = new ModelAndView("send");
		return modelAndView;
	}

}