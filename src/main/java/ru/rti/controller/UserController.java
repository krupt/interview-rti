package ru.rti.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
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

	@RequestMapping(value = "/info", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public void getCurrentUserInfo(HttpServletResponse response) {
		try (JsonGenerator generator = new JsonFactory().createGenerator(response.getOutputStream())) {
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
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		}
	}

}