package com.sgm.liteapp.cloudapi.webapp.security;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import com.sgm.liteapp.commons.utils.CacheUtils;
import com.sgm.liteapp.commons.web.AuthenticationException;
import com.sgm.liteapp.commons.web.SessionContext;
import com.sgm.liteapp.commons.web.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SecuredAccessInterceptor implements HandlerInterceptor {

	private static final String CACHE_KEY_PREFIX = "cloudapi_";

	private static final String TOKEN_HEADER_NAME = "Access-Token";

	private static final String USER_CODE_HEADER_NAME = "x-user-code";

	private long sessionTimeout = 1800;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String token = request.getHeader(TOKEN_HEADER_NAME);
		String uid = request.getHeader(USER_CODE_HEADER_NAME);
		if (StringUtils.isEmpty(token) || StringUtils.isEmpty(uid)) {
			throw new AuthenticationException("缺少身份认证信息");
		}
		User user = (User) CacheUtils.get(CACHE_KEY_PREFIX + token);
		if (user == null) {
			// 从网关请求头中重新构造User会话
			user = new User();
			user.setUid(uid);
			CacheUtils.set(CACHE_KEY_PREFIX + token, user);
		}
		SessionContext.setUser(user);
		CacheUtils.expire(CACHE_KEY_PREFIX + token, sessionTimeout);
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		SessionContext.removeUser();
	}

	public long getSessionTimeout() {
		return sessionTimeout;
	}

	public void setSessionTimeout(long sessionTimeout) {
		this.sessionTimeout = sessionTimeout;
	}

}
