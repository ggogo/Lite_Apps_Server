package com.sgm.liteapp.admin.exception;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.http.fileupload.FileUploadBase.SizeLimitExceededException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sgm.liteapp.admin.exception.ResultException;
import com.sgm.liteapp.admin.model.enums.RequestCode;

@ControllerAdvice
@ResponseBody
public class ControllerExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(ControllerExceptionHandler.class);

	@Autowired
	private HttpServletRequest request;
	

	//这个注解是指当controller中抛出这个指定的异常类的时候，都会转到这个方法中来处理异常
	@ExceptionHandler(ResultException.class)
	public Map handlerException(ResultException e) {
		logger.error("---------------------------------------------------");
		logger.error("" ,e);
		logger.error("---------------------------------------------------");
		return resultFormat(e.getMessage(), e.getCode());
	}

	//空指针异常
	@ExceptionHandler(NullPointerException.class)
	public Map nullPointerExceptionHandler(NullPointerException e) {
		logger.error("---------------------------------------------------");
		logger.error("" ,e);
		logger.error("---------------------------------------------------");
		return resultFormat(e.getMessage(), 500);
	}

	//类型转换异常
	@ExceptionHandler(ClassCastException.class)
	public Map classCastExceptionHandler(ClassCastException e) {
		logger.error("---------------------------------------------------");
		logger.error("" ,e);
		logger.error("---------------------------------------------------");
		return resultFormat(e.getMessage(), 500);
	}

	//IO异常
	@ExceptionHandler(IOException.class)
	public Map iOExceptionHandler(IOException e) {
		logger.error("---------------------------------------------------");
		logger.error("" ,e);
		logger.error("---------------------------------------------------");
		return resultFormat(e.getMessage(), 500);
	}

	//未知方法异常
	@ExceptionHandler(NoSuchMethodException.class)
	public Map noSuchMethodExceptionHandler(NoSuchMethodException e) {
		logger.error("---------------------------------------------------");
		logger.error("" ,e);
		logger.error("---------------------------------------------------");
		return resultFormat("未知方法异常", 55);
	}

	//数组越界异常
	@ExceptionHandler(IndexOutOfBoundsException.class)
	public Map indexOutOfBoundsExceptionHandler(IndexOutOfBoundsException e) {
		logger.error("---------------------------------------------------");
		logger.error("" ,e);
		logger.error("---------------------------------------------------");
		return resultFormat(e.getMessage(), 500);
	}

	//400错误
	@ExceptionHandler({HttpMessageNotReadableException.class})
	public Map requestNotReadable(HttpMessageNotReadableException e) {
		logger.error("---------------------------------------------------");
		logger.error("" ,e);
		logger.error("---------------------------------------------------");
		return resultFormat(e.getMessage(), 400);
	}

	//400错误
	@ExceptionHandler({TypeMismatchException.class})
	public Map requestTypeMismatch(TypeMismatchException e) {
		logger.error("---------------------------------------------------");
		logger.error("" ,e);
		logger.error("---------------------------------------------------");
		return resultFormat(e.getMessage(), 400);
	}

	//400错误
	@ExceptionHandler({MissingServletRequestParameterException.class})
	public Map requestMissingServletRequest(MissingServletRequestParameterException e) {
		logger.error("---------------------------------------------------");
		logger.error("" ,e);
		logger.error("---------------------------------------------------");
		return resultFormat(e.getMessage(), 400);
	}

	//404错误
	@ExceptionHandler({NoHandlerFoundException.class})
	public Map request404(NoHandlerFoundException e) {
		logger.error("---------------------------------------------------");
		logger.error("" ,e);
		logger.error("---------------------------------------------------");
		return resultFormat(e.getMessage(), 404);
	}

	//405错误
	@ExceptionHandler({HttpRequestMethodNotSupportedException.class})
	public Map request405(HttpRequestMethodNotSupportedException e) {
		logger.error("---------------------------------------------------");
		logger.error("" ,e);
		logger.error("---------------------------------------------------");
		return resultFormat(e.getMessage(), 405);
	}

	//406错误
	@ExceptionHandler({HttpMediaTypeNotAcceptableException.class})
	public Map request406(HttpMediaTypeNotAcceptableException e) {
		logger.error("---------------------------------------------------");
		logger.error("" ,e);
		logger.error("---------------------------------------------------");
		return resultFormat(e.getMessage(), 406);
	}

	//500错误
	@ExceptionHandler({ConversionNotSupportedException.class, HttpMessageNotWritableException.class})
	public Map server500(RuntimeException e) {
		logger.error("---------------------------------------------------");
		logger.error("" ,e);
		logger.error("---------------------------------------------------");
		return resultFormat(e.getMessage(), 500);
	}

	//栈溢出
	@ExceptionHandler({StackOverflowError.class})
	public Map requestStackOverflow(StackOverflowError e) {
		logger.error("---------------------------------------------------");
		logger.error("" ,e);
		logger.error("---------------------------------------------------");
		return resultFormat("栈溢出", 406);
	}

	//除数不能为0
	@ExceptionHandler({ArithmeticException.class})
	public Map arithmeticException(ArithmeticException e) {
		logger.error("---------------------------------------------------");
		logger.error("" ,e);
		logger.error("---------------------------------------------------");
		return resultFormat(e.getMessage(), 500);
	}

	//其他的所有错误
	@ExceptionHandler({Exception.class})
	public Map exception(Exception e) {
		logger.error("---------------------------------------------------");
		logger.error("" ,e);
		logger.error("---------------------------------------------------");
		return resultFormat(RequestCode.CODE501.getDesc(), 500);
	}
	

	/**
	 * 异常返回信息
	 *
	 * @param msg
	 * @param code
	 * @param url
	 * @return
	 * @throws JsonProcessingException 
	 */
	public Map resultFormat(String msg, Integer code){
		ObjectMapper om = new ObjectMapper();
		Map<String, Object> result=new HashMap<String, Object>();
		result.put("status", false);
		result.put("code", code);
		result.put("msg", msg);
		result.put("url", request.getRequestURI());
		return result;
	}
	
}
