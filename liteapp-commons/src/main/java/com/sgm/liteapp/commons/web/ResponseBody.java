package com.sgm.liteapp.commons.web;

public class ResponseBody<T> {

	private int responseCode = 200;

	private String message;

	private String error;

	private long timestamp;

	private int pageNo = 1;

	private long pageSize;

	private long totalRows;

	private T data;

	public ResponseBody() {

	}

	public ResponseBody(T data) {
		this.data = data;
		this.timestamp = System.currentTimeMillis();
	}

	public ResponseBody(String message, T data) {
		this.data = data;
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

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public long getPageSize() {
		return pageSize;
	}

	public void setPageSize(long pageSize) {
		this.pageSize = pageSize;
	}

	public long getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(long totalRows) {
		this.totalRows = totalRows;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public static <T> ResponseBody<T> data(T t) {
		return new ResponseBody<T>(t);
	}

	public static <T> ResponseBody<T> data(String message, T t) {
		return new ResponseBody<T>(message, t);
	}

	public static <T> ResponseBody<T> error(int responseCode, String error) {
		ResponseBody<T> resp = new ResponseBody<T>();
		resp.setResponseCode(responseCode);
		resp.setError(error);
		return resp;
	}

}
