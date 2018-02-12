package com.kami.blog.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.kami.blog.model.User;
import com.kami.blog.redis.UserSessionRedis;
import com.kami.blog.util.BeanHelper;
import com.kami.blog.util.KeyHelper;
import com.kami.blog.util.SessionHelper;
import com.kami.blog.util.StringHelper;

/**
 *	账号在异处登录通知用户
 */
public class ReplaceUserInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		UserSessionRedis userSessionRedis = (UserSessionRedis) 
				BeanHelper.getBean("userSessionRedis");
		User user = (User) SessionHelper.getAttribute(request, KeyHelper.USER);
		if(user != null) {
			String redisSessionId = userSessionRedis.readUserSession(user.getId());
			//用户的sessionId与redis缓存中不一致
			if(!StringHelper.isEmpty(redisSessionId) && !StringHelper.equals(SessionHelper.getSessionId(request), redisSessionId)) {
				request.setAttribute(KeyHelper.ERROR, "账号在异处登录");
				SessionHelper.removeAttribute(request, KeyHelper.USER);
				request.getRequestDispatcher("/login").forward(request, response);
				return false;
			}
		} 
		return true;
	}
}
