package com.sgm.liteapp.servicegateway.authority.strategy;

import org.springframework.beans.BeansException;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder.Builder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "servicegateway.security.authorize")
public class AuthorityStrategyConfiguration implements ApplicationContextAware {

	private ApplicationContext applicationContext;

	private String contextPath;

	@Bean
	public RouteLocator routeLocator(RouteLocatorBuilder builder) {
		Builder b = builder.routes();
		// 注册登录Handler
		LoginHandler login = applicationContext.getBean(LoginHandler.class);
		if (login != null) {
			b.route(r -> r.readBody(String.class, requestBody -> {
				// 缓存了body信息，在filter中可以通过
				// exchange.getAttribute("cachedRequestBodyObject")直接获取body内容;
				return true;
			}).and().path(contextPath + "/login").uri("Authenticate://Login").filters(login).id("login-handler"));
		}
		// 注册刷新令牌Handler
		RefreshTokenHandler refresh = applicationContext.getBean(RefreshTokenHandler.class);
		if (refresh != null) {
			b.route(r -> r.readBody(String.class, requestBody -> {
				return true;
			}).and().path(contextPath + "/refreshtoken").uri("Authenticate://RefreshToken").filters(refresh)
					.id("refresh-token"));
		}

		// 注册登出Handler
		LogoutHandler logout = applicationContext.getBean(LogoutHandler.class);
		if (logout != null) {
			b.route(r -> r.readBody(String.class, requestBody -> {
				return true;
			}).and().path(contextPath + "/logout").uri("Authenticate://Logout").filters(logout).id("logout-handler"));
		}
		return b.build();
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	public String getContextPath() {
		return contextPath;
	}

	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}

}
