package com.sgm.liteapp.servicegateway.payload;

public class Fault {

	private int responseCode;

	private String error;

	private String message;

	private long timestamp;

	public Fault() {
		this.timestamp = System.currentTimeMillis();
	}

	public Fault(int code, String error) {
		this.responseCode = code;
		this.error = error;
		this.timestamp = System.currentTimeMillis();
	}

	public Fault(int code, String error, String message) {
		this.responseCode = code;
		this.error = error;
		this.message = message;
		this.timestamp = System.currentTimeMillis();
	}

	public int getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

}
