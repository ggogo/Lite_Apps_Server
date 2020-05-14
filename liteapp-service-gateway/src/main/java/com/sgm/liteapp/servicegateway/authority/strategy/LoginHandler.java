package com.sgm.liteapp.servicegateway.authority.strategy;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import com.sgm.liteapp.commons.utils.CacheUtils;
import com.sgm.liteapp.commons.web.ResponseBody;
import com.sgm.liteapp.servicegateway.authority.service.AuthenticationException;
import com.sgm.liteapp.servicegateway.authority.service.B2CIDMFederationService;
import com.sgm.liteapp.servicegateway.authority.service.Credentials;
import com.sgm.liteapp.servicegateway.payload.JSON;
import com.sgm.liteapp.servicegateway.payload.Response;
import com.sgm.liteapp.servicegateway.utils.ServiceExchangeResolver;
import io.netty.handler.codec.http.HttpMethod;
import reactor.core.publisher.Mono;

@Component
public class LoginHandler extends AuthorityHandler {

	@Autowired
	private B2CIDMFederationService federationService;

	@Override
	@SuppressWarnings("rawtypes")
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
		Map paramMap = null;
		try {
			paramMap = JSON.parse(ServiceExchangeResolver.resolveBodyFromCachedRequest(exchange), Map.class);
			String idpUserID = (String) paramMap.get("idpUserId");
			String ticket = (String) paramMap.get("ticket");
			String deviceId = (String) paramMap.get("deviceId");
			Credentials creds = federationService.exchangeTokenByTicket(idpUserID, ticket, deviceId);
			CacheUtils.set(creds.getAccessToken(), idpUserID, creds.getExpiresIn());
			return Response.ok(exchange, new ResponseBody<Credentials>(creds));
		} catch (AuthenticationException e) {
			return Response.error(exchange, HttpStatus.FORBIDDEN, e.getMessage());
		} catch (Exception e) {
			return Response.error(exchange, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}

}
