package com.webapp.trackingBoard.response;

import com.webapp.trackingBoard.entities.User;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
public class LoginResponse implements Serializable {
	private String message;
	private String token;
	private User user;
}