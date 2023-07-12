package com.webapp.trackingBoard.helper;


import java.util.regex.Pattern;

public class ValidtionUtils {

	public static boolean checkEmptyOrNull(String... strings) {
		for (String s : strings) {
			if (s == null || s == "") {
				return true;
			}
		}
		return false;
	}

	public static boolean validEmail(String email) {
		String regexPattern = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
		return Pattern.compile(regexPattern).matcher(email).matches();
	}
}
