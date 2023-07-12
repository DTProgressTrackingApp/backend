package com.webapp.trackingBoard.response;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
public class LoginResponse implements Serializable {
	private String token;
}