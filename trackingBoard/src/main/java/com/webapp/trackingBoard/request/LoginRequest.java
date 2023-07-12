package com.webapp.trackingBoard.request;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class LoginRequest implements Serializable {
	private String email;
	private String password;
}