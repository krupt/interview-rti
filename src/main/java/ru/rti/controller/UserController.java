package ru.rti.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;

import ru.rti.service.util.CurrentUser;

@Controller
@RequestMapping("/user")
public class UserController {

	private final Logger log = LoggerFactory.getLogger(getClass());

	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public void getCurrentUserInfo(HttpServletResponse response) {
		log.debug("Получение информации о текущем пользователе");
		try (JsonGenerator generator = new JsonFactory().createGenerator(response.getOutputStream())) {
			response.setContentType(MediaType.APPLICATION_JSON_VALUE + "; charset=UTF-8");
			CurrentUser user = (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			generator.writeStartObject();
			generator.writeNumberField("id", user.getId());
			generator.writeStringField("name", user.getUsername());
			generator.writeStringField("description", user.getDescription());
			generator.writeBooleanField("enabled", user.isEnabled());
			generator.writeArrayFieldStart("roles");
			for (GrantedAuthority authority: user.getAuthorities())
				generator.writeObject(authority.getAuthority());
			generator.writeEndArray();
			generator.writeEndObject();
		} catch (IOException e) {
			log.error("Ошибка", e);
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		log.debug("Информация отправлена");
	}

}