package com.sgm.liteapp.servicegateway.utils;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;

import com.sgm.liteapp.commons.utils.CollectionUtils;

import reactor.core.publisher.Flux;

public class ServiceExchangeResolver {

	/**
	 * 获取被缓存的body，但是必须在定义路由时显示的指定缓存body
	 * 
	 * @param exchange
	 * @return
	 */
	public static String resolveBodyFromCachedRequest(ServerWebExchange exchange) {
		return (String) exchange.getAttribute("cachedRequestBodyObject");
	}

	/**
	 * 获取被缓存的body，但是必须在定义路由时显示的指定缓存body
	 * 
	 * @param exchange
	 * @return
	 */
	public static <T> T resolveBodyFromCachedRequest(ServerWebExchange exchange, Class<T> cls) {
		return exchange.getAttribute("cachedRequestBodyObject");
	}

	/**
	 * 获取请求体
	 * 
	 * @param serverHttpRequest
	 * @return
	 */
	public static String resolveBodyFromRequest(ServerHttpRequest request) {
		Flux<DataBuffer> body = request.getBody();
		StringBuilder sb = new StringBuilder();
		body.subscribe(buffer -> {
			byte[] bytes = new byte[buffer.readableByteCount()];
			buffer.read(bytes);
			DataBufferUtils.release(buffer);
			String bodyString = new String(bytes, StandardCharsets.UTF_8);
			sb.append(bodyString);
		});
		return sb.toString();
	}

	/**
	 * 获取请求的JSON内容
	 * 
	 * @param request
	 * @return
	 */
	public static JSONObject resolveJSONBodyFromRequest(ServerHttpRequest request) {
		Flux<DataBuffer> body = request.getBody();
		StringBuilder sb = new StringBuilder();
		body.subscribe(buffer -> {
			byte[] bytes = new byte[buffer.readableByteCount()];
			buffer.read(bytes);
			DataBufferUtils.release(buffer);
			String bodyString = new String(bytes, StandardCharsets.UTF_8);
			sb.append(bodyString);
		});
		try {
			return new JSONObject(sb.toString());
		} catch (JSONException e) {
			return new JSONObject(Collections.EMPTY_MAP);
		}

	}

	/**
	 * 获取请求头
	 * 
	 * @param request
	 * @param headerName
	 * @return
	 */
	public static String resolveHeaderFromRequest(ServerHttpRequest request, String headerName) {
		List<String> headerValues = request.getHeaders().get(headerName);
		if (CollectionUtils.isNotEmpty(headerValues)) {
			return headerValues.get(0);
		}
		return null;
	}

	/**
	 * 获取表单提交参数(多个)
	 * 
	 * @param exchange
	 * @return
	 */
	public static Map<String, List<String>> resolveFormParameters(ServerWebExchange exchange) {
		Map<String, List<String>> params = new HashMap<String, List<String>>();
		exchange.getFormData().flux().subscribe(valueMap -> {
			if (valueMap != null) {
				for (Entry<String, List<String>> entry : valueMap.entrySet()) {
					params.put(entry.getKey(), entry.getValue());
				}
			}
		});
		return params;
	}

	/**
	 * 获取表单提交参数(单一参数)
	 * 
	 * @param exchange
	 * @return
	 */
	public static Map<String, String> resolveFormParameter(ServerWebExchange exchange) {
		Map<String, String> params = new HashMap<String, String>();
		exchange.getFormData().flux().subscribe(valueMap -> {
			if (valueMap != null) {
				for (Entry<String, List<String>> entry : valueMap.entrySet()) {
					if (CollectionUtils.isNotEmpty(entry.getValue())) {
						params.put(entry.getKey(), entry.getValue().get(0));
					}
				}
			}
		});
		return params;
	}
}
