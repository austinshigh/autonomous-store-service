package com.cscie97.store.authentication;

public class AuthenticationException {

	private String reason;

	private String command;

	public AuthenticationException(String reason) {
		this.reason = reason;
	}
}
