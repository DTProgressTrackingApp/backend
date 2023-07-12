package com.webapp.trackingBoard.service;

import com.webapp.trackingBoard.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
	public boolean login(String email, String password) {
		try {
			log.info("Login with user: " + email);
			UserDetails userDetails = loginService.loadUserByUsername(email);
			if (userDetails == null) {
				return false;
			}
			if (!passwordEncoder.matches(password, userDetails.getPassword())) {
				return false;
			}
			return true;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return false;
	}

}
