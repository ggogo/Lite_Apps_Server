package com.sgm.liteapp.servicegateway.payload;

import java.io.IOException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSON {

	private static ObjectMapper objectMapper = new ObjectMapper();

	static {
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
	}

	public static String build(Object obj) throws IOException {
		return objectMapper.writeValueAsString(obj);
	}

	public static byte[] buildUTF8(Object obj) throws IOException {
		return objectMapper.writeValueAsBytes(obj);
	}

	public static <T> T parse(String text, Class<T> valueType) throws IOException {
		return objectMapper.readValue(text, valueType);
	}
}
