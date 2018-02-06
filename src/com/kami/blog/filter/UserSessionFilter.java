package com.kami.blog.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.kami.blog.model.User;
import com.kami.blog.redis.UserSessionRedis;
import com.kami.blog.util.KeyHelper;
import com.kami.blog.util.SessionHelper;
import com.kami.blog.util.StringHelper;

/**
 * 顶号处理过滤器
 */
public class UserSessionFilter extends OncePerRequestFilter {
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		UserSessionRedis userSessionRedis = (UserSessionRedis) 
				WebApplicationContextUtils.getWebApplicationContext(
						request.getSession().getServletContext()).getBean("userSessionRedis");
		User user = (User) SessionHelper.getAttribute(request, KeyHelper.USER);
		if(user != null) {
			String redisSessionId = userSessionRedis.readUserSession(user.getId());
			if(!StringHelper.equals(SessionHelper.getSessionId(request), redisSessionId)) {
				request.setAttribute(KeyHelper.ERROR, "账号在异处登录");
				SessionHelper.removeAttribute(request, KeyHelper.USER);
			}
		} 
		filterChain.doFilter(request, response);
	}
	
	
}
