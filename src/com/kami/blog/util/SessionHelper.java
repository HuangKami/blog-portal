package com.kami.blog.util;

import javax.servlet.http.HttpServletRequest;

/**
 * Session工具类
 */
public class SessionHelper {
	/**
	 * session添加属性
	 */
	public static void setAttribute(HttpServletRequest request, String key, Object value) {
		request.getSession().setAttribute(key, value);
	}
	
	/**
	 * session移除属性
	 */
	public static Object removeAttribute(HttpServletRequest request, String key) {
		Object object = getAttribute(request, key);
		if(object == null) {
			return null;
		}
		request.getSession().removeAttribute(key);
		return object;
	}
	
	/**
	 * session获取值
	 */
	public static Object getAttribute(HttpServletRequest request, String key) {
		return request.getSession().getAttribute(key);
	}
	
	public static String getSessionId(HttpServletRequest request) {
		return request.getSession().getId();
	}
}
