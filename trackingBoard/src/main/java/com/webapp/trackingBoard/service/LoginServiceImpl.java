package com.webapp.trackingBoard.service;

import com.webapp.trackingBoard.entities.User;
import com.webapp.trackingBoard.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Slf4j
@Service
public class LoginServiceImpl implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<User> optUser = userRepository.findByEmail(email);
		if (!optUser.isPresent()) {
			return null;
		}
		User user = optUser.get();

		SimpleGrantedAuthority role = new SimpleGrantedAuthority(user.getRole());
		Collection<SimpleGrantedAuthority> roleList = new ArrayList<>();
		roleList.add(role);
		org.springframework.security.core.userdetails.User userDetail;
		try {
			Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword(), roleList);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			userDetail = new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), roleList);
			return userDetail;
		} catch (Exception e) {
			SecurityContextHolder.getContext().setAuthentication(null);
			log.error("Failure in autoLogin", e);
		}
		return null;
	}
}
