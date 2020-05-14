package com.sgm.liteapp.commons.web;

public class WebAccessException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private int httpStatus = 500;

	public WebAccessException(int httpStatus, String message) {
		super(message);
		this.httpStatus = httpStatus;
	}

	public WebAccessException(String message) {
		super(message);
	}

	public WebAccessException(String message, Throwable cause) {
		super(message, cause);
	}

	public int getHttpStatus() {
		return httpStatus;
	}

}
