package com.webapp.trackingBoard.controller;


import com.webapp.trackingBoard.entities.User;
import com.webapp.trackingBoard.helper.AuthenticationUtil;
import com.webapp.trackingBoard.helper.ValidtionUtils;
import com.webapp.trackingBoard.request.LoginRequest;
import com.webapp.trackingBoard.response.LoginResponse;
import com.webapp.trackingBoard.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class UserController {

	@Autowired
	private AuthenticationUtil jwtTokenUtil;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/health", method = RequestMethod.GET)
	public ResponseEntity<?> createAuthenticationToken() throws Exception {
		return ResponseEntity.ok("Service up!!!");
	}

	@RequestMapping(value = "/api/v1/auth/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest rq) {
		LoginResponse res = LoginResponse.builder().build();
		if (ValidtionUtils.checkEmptyOrNull(rq.getEmail(), rq.getPassword())) {
			res.setMessage("Email or Password cannot be null");
			return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
		}
		try {
			User user = userService.login(rq.getEmail(), rq.getPassword());
			log.info("resultLogin: " + user);
			if (user == null) {
				res.setMessage("Email or Password not valid");
				return new ResponseEntity<>(res, HttpStatus.FORBIDDEN);
			}

			final String token = jwtTokenUtil.generateToken(rq.getEmail() + "-" + rq.getPassword());
			res.setUser(user);
			res.setMessage("Success");
			res.setToken(token);
			return new ResponseEntity<>(res, HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
