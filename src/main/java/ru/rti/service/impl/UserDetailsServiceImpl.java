package ru.rti.service.impl;

import java.util.HashSet;
import java.util.Set;

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

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	private final UserService service;

	@Autowired
	public UserDetailsServiceImpl(UserService userService) {
		service = userService;
	}

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = service.findByEmail(username);
		Set<GrantedAuthority> authorities = buildUserAuthority(user.getRoles());
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), user.isEnabled(), true, true, true, authorities);
	}
	
	private Set<GrantedAuthority> buildUserAuthority(Set<UserRole> userRoles) {
		Set<GrantedAuthority> auths = new HashSet<GrantedAuthority>();
		for (UserRole userRole: userRoles)
			auths.add(new SimpleGrantedAuthority(userRole.getRole()));
		return auths;
	}

	public static void main(String[] args) {
		System.out.println(new BCryptPasswordEncoder().encode("12345"));
	}

}