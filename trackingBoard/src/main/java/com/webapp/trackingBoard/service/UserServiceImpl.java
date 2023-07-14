package com.webapp.trackingBoard.service;

import com.webapp.trackingBoard.entities.User;
import com.webapp.trackingBoard.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private LoginServiceImpl loginService;

	@Override
	public User login(String email, String password) {
		try {
			log.info("Login with user: " + email);
			UserDetails userDetails = loginService.loadUserByUsername(email);
			if (userDetails == null) {
				return null;
			}
			if (!passwordEncoder.matches(password, userDetails.getPassword())) {
				return null;
			}
			List<GrantedAuthority> roles = new ArrayList<>(userDetails.getAuthorities());
			User user = User.builder().email(email).password("******").role(roles.get(0).getAuthority()).build();
			return user;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}

}
