package com.sgm.liteapp.cloudapi.webapp.advice;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.sgm.liteapp.commons.web.AuthenticationException;
import com.sgm.liteapp.commons.web.AuthorizationException;
import com.sgm.liteapp.commons.web.ResponseBody;
import com.sgm.liteapp.commons.web.WebAccessException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class WebControllerErrorAdvice {

	protected final static Logger _log = LoggerFactory.getLogger(WebControllerErrorAdvice.class);

	@ExceptionHandler
	public ResponseBody<Void> processError(HttpServletRequest request, HttpServletResponse response, Exception e) {
		_log.error("process web request error", e);
		response.setHeader("Content-Type", "application/json;charset=UTF-8");
		if (e instanceof AuthenticationException) {
			return process401Error(request, response, e);
		} else if (e instanceof AuthorizationException) {
			return process403Error(request, response, e);
		}
		ResponseBody<Void> resp = new ResponseBody<>();
		// 其他系统异常
		if (e instanceof WebAccessException) {
			WebAccessException webError = (WebAccessException) e;
			response.setStatus(webError.getHttpStatus());
			resp.setResponseCode(webError.getHttpStatus());
			HttpStatus httpStatus = HttpStatus.resolve(webError.getHttpStatus());
			if (httpStatus != null) {
				resp.setError(httpStatus.getReasonPhrase());
			} else {
				resp.setError("Unknown Error");
			}
		} else {
			response.setStatus(500);
			resp.setResponseCode(500);
			resp.setError("Internal Server Error");
		}
		resp.setMessage(StringUtils.isNotBlank(e.getMessage()) ? e.getMessage() : e.getCause().toString());
		return resp;
	}

	public ResponseBody<Void> process401Error(HttpServletRequest request, HttpServletResponse response, Exception e) {
		ResponseBody<Void> resp = new ResponseBody<Void>();
		resp.setResponseCode(401);
		resp.setError("Unauthorized");
		resp.setMessage(e.getMessage());
		response.setStatus(401);
		return resp;
	}

	public ResponseBody<Void> process403Error(HttpServletRequest request, HttpServletResponse response, Exception e) {
		ResponseBody<Void> resp = new ResponseBody<Void>();
		resp.setResponseCode(403);
		resp.setError("Forbidden");
		resp.setMessage(e.getMessage());
		response.setStatus(403);
		return resp;
	}

}
