package ru.rti.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ru.rti.model.User;
import ru.rti.model.UserRole;
import ru.rti.service.UserService;
import ru.rti.service.util.CurrentUser;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	private final UserService userService;
	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	public UserDetailsServiceImpl(UserService userService) {
		this.userService = userService;
	}

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.debug("Авторизация пользователя с именем: " + username);
		User user = userService.findByEmail(username);
		if (null == user) {
			log.warn("Пользователь \"" + username + "\" не найден в БД");
			throw new UsernameNotFoundException(username + " not found");
		}
		Set<GrantedAuthority> authorities = buildUserAuthority(user.getRoles());
		CurrentUser currentUser = new CurrentUser(user.getId(), user.getEmail(), user.getPassword(), user.getDescr(), user.isEnabled(), true, true, true, authorities);
		log.debug("Пользователь найден: \n" + currentUser);
		return currentUser;
	}

	private Set<GrantedAuthority> buildUserAuthority(Set<UserRole> userRoles) {
		Set<GrantedAuthority> auths = new HashSet<GrantedAuthority>();
		for (UserRole userRole: userRoles)
			auths.add(new SimpleGrantedAuthority("ROLE_" + userRole.getRole()));
		return auths;
	}

	/*
	 * Используется для генерации значений паролей
	 */
	public static void main(String[] args) {
		System.out.println(new BCryptPasswordEncoder().encode("12345"));
	}

}