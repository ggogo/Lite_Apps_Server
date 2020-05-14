package com.sgm.liteapp.servicegateway.security;

import java.util.List;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequest.Builder;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;

import com.sgm.liteapp.commons.utils.CacheUtils;
import com.sgm.liteapp.servicegateway.authority.service.B2CIDMFederationService;
import com.sgm.liteapp.servicegateway.payload.Response;

import reactor.core.publisher.Mono;

@Component
@ConfigurationProperties(prefix = "servicegateway.security")
public class SecuredAccessFilter implements GlobalFilter, Ordered {

	private String[] whiteList;

	private boolean syncIdmTokenExpires;// 当Token在本地缓存失效后，是否去IDM做二次验证

	@Autowired
	private B2CIDMFederationService federationService;

	@Override
	public int getOrder() {
		return 0;
	}

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		ServerHttpRequest request = exchange.getRequest();
		String requestPath = request.getPath().value();
		ServerHttpResponse response = exchange.getResponse();
		if ("/healthcheck".equalsIgnoreCase(requestPath)) {
			// 健康检测地址
			response.setStatusCode(HttpStatus.OK);
			return response.writeWith(Mono.empty());
		}

		// 不在白名单的所有URL请求都要通过安全验证
		if (!whiteListContains(requestPath)) {
			String token = this.getAccessToken(request);
			if (token == null) {
				return this.wrapUnauthorized(exchange);
			}
			String uid = (String) CacheUtils.get(token);
			if (uid == null || "".equals(uid.trim())) {
				if (syncIdmTokenExpires) {
					// 如果缓存拿不到，就去IDM校验
					String idpUserID = null;
					List<String> idpUserIDs = request.getHeaders().get("idpUserID");
					if (idpUserIDs != null && idpUserIDs.size() > 0) {
						idpUserID = idpUserIDs.get(0);
					}
					if (federationService.validateToken(idpUserID, token)) {
						uid = idpUserID;
						if (StringUtils.isNotEmpty(uid)) {
							CacheUtils.set(token, uid, 1800L);
						}
					}
				}
			}
			if (uid == null || "".equals(uid.trim())) {
				return this.wrapUnauthorized(exchange);
			}
			Builder requestBuilder = exchange.getRequest().mutate();
			requestBuilder.header(Constants.TOKEN_HEADER_NAME, token);// 加入token请求头
			requestBuilder.header(Constants.UID_HEADER_NAME, uid);// 加入uid请求头
			ServerHttpRequest host = requestBuilder.build();
			ServerWebExchange build = exchange.mutate().request(host).build();
			return chain.filter(build);
		}
		return chain.filter(exchange);
	}

	private String getAccessToken(ServerHttpRequest request) {
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
		return token;
	}

	private Mono<Void> wrapUnauthorized(ServerWebExchange exchange) {
		return Response.error(exchange, HttpStatus.UNAUTHORIZED, "Unauthorized");
	}

	private boolean whiteListContains(String requestPath) {
		if (ArrayUtils.isNotEmpty(whiteList)) {
			for (String white : whiteList) {
				if (white.endsWith("/**")) {
					if (StringUtils.startsWithIgnoreCase(requestPath, white.substring(0, white.length() - 2))) {
						return true;
					}
				} else {
					if (StringUtils.equalsIgnoreCase(white, requestPath)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public String[] getWhiteList() {
		return whiteList;
	}

	public void setWhiteList(String[] whiteList) {
		this.whiteList = whiteList;
	}

	public boolean isSyncIdmTokenExpires() {
		return syncIdmTokenExpires;
	}

	public void setSyncIdmTokenExpires(boolean syncIdmTokenExpires) {
		this.syncIdmTokenExpires = syncIdmTokenExpires;
	}

}
