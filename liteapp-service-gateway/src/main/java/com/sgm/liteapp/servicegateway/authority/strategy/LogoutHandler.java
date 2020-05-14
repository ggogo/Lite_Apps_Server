package com.sgm.liteapp.servicegateway.authority.strategy;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import com.sgm.liteapp.commons.utils.CacheUtils;
import com.sgm.liteapp.commons.web.ResponseBody;
import com.sgm.liteapp.servicegateway.authority.service.B2CIDMFederationService;
import com.sgm.liteapp.servicegateway.payload.JSON;
import com.sgm.liteapp.servicegateway.payload.Response;
import com.sgm.liteapp.servicegateway.security.Constants;
import com.sgm.liteapp.servicegateway.utils.ServiceExchangeResolver;

import io.netty.handler.codec.http.HttpMethod;
import reactor.core.publisher.Mono;

@Component
public class LogoutHandler implements GatewayFilter {

	@Autowired
	private B2CIDMFederationService federationService;

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		ServerHttpRequest request = exchange.getRequest();
		if (!HttpMethod.POST.name().equalsIgnoreCase(request.getMethod().name())) {
			return Response.error(exchange, HttpStatus.METHOD_NOT_ALLOWED,
					"Method Not Allowed '" + request.getMethod() + "'");
		}
		MediaType mediaType = request.getHeaders().getContentType();
		if (!MediaType.APPLICATION_JSON.isCompatibleWith(mediaType)) {
			return Response.error(exchange, HttpStatus.UNSUPPORTED_MEDIA_TYPE,
					"Unsupported Media Type '" + mediaType.toString() + "'");
		}
		String token = getAccessToken(exchange, request);
		if (token != null) {
			String idpUserID = (String) CacheUtils.get(token);
			CacheUtils.remove(token);
			if (StringUtils.isNotEmpty(idpUserID)) {
				federationService.logout(idpUserID, token);
			}
		}
		return Response.ok(exchange, new ResponseBody<Void>());
	}

	@SuppressWarnings("rawtypes")
	private String getAccessToken(ServerWebExchange exchange, ServerHttpRequest request) {
		String token = null;
		// 先从请求头中查询token
		HttpHeaders headers = request.getHeaders();
		List<String> values = headers.get(Constants.TOKEN_HEADER_NAME);
		if (values != null && values.size() > 0) {
			token = values.get(0);
		}
		// 如果不在请求头里，通过URL参数查询token
		if (StringUtils.isEmpty(token)) {
			MultiValueMap<String, String> params = request.getQueryParams();
			if (params != null) {
				token = params.getFirst(Constants.TOKEN_HEADER_NAME);
			}
		}
		// 如果不在请求头里，通过JSON参数来查询
		try {
			Map paramMap = JSON.parse(ServiceExchangeResolver.resolveBodyFromCachedRequest(exchange), Map.class);
			if (paramMap != null) {
				token = (String) paramMap.get("accessToken");
			}
		} catch (IOException e) {

		}
		return token;
	}

}
