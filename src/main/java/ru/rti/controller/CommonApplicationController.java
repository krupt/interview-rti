package ru.rti.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CommonApplicationController {

	private Logger log = LoggerFactory.getLogger(getClass());

	@RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
	public String getIndexPage() {
		return "index";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView getLoginPage(HttpSession session) {
		ModelAndView modelAndView = new ModelAndView("login");
		Object securityException = session.getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
		if (securityException != null) {
			log.warn("Пользователь не авторизован");
			if (securityException instanceof BadCredentialsException || securityException instanceof UsernameNotFoundException)
				modelAndView.addObject("error", "Неверное имя пользователя/пароль");
			else
				modelAndView.addObject("error", "Непредвиденная ошибка. Пожалуйста, обратитесь к администратору");
		}
		return modelAndView;
	}

	@RequestMapping(value = "/about", method = RequestMethod.GET)
	public String getAboutInfo() {
		return "about";
	}

	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public String getAccessDeniedPage() {
		return "403";
	}

}