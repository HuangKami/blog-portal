package com.kami.blog.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieHelper {
	private CookieHelper() {}
	
	public static void addCookie(HttpServletResponse response, String name, String value) {
		Cookie cookie = new Cookie(name, value);
		cookie.setPath("/");
		response.addCookie(cookie);
	}
	
	public static String getCookieValue(HttpServletRequest request, String name) {
		for(Cookie cookie : request.getCookies()) {
			if(StringHelper.equals(cookie.getName(), name)) {
				return cookie.getValue();
			}
		}
		return null;
	}
}
