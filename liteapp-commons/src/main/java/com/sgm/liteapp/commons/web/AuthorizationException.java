package com.sgm.liteapp.commons.web;

public class AuthorizationException extends Exception {

	private static final long serialVersionUID = 1L;

	public AuthorizationException(String message) {
		super(message);
	}

	public AuthorizationException(String message, Throwable cause) {
		super(message, cause);
	}

}
