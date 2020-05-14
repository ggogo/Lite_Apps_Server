package com.sgm.liteapp.commons.web;

public class SessionContext {

	private static final ThreadLocal<User> POOL = new ThreadLocal<User>();

	public static User getUser() {
		User userSession = POOL.get();
		if (userSession == null) {
			throw new RuntimeException("user not found in current thread");
		}
		return userSession;
	}

	public static void setUser(User user) {
		POOL.set(user);
	}

	public static void removeUser() {
		POOL.remove();
	}
}
