package com.webapp.trackingBoard.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LogoutService implements LogoutHandler {
	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		log.info("Logout with user: " + authentication.getPrincipal());
		final String authHeader = request.getHeader("Authorization");
		if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
			return;
		}
		SecurityContextHolder.clearContext();
	}
}
