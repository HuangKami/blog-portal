package com.kami.blog.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.kami.blog.model.User;
import com.kami.blog.util.KeyHelper;
import com.kami.blog.util.SessionHelper;

/**
 *	拦截未登录的请求
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		User user = (User) SessionHelper.getAttribute(request, KeyHelper.USER);
		if(user == null) {
			response.sendRedirect(request.getSession().getServletContext().getContextPath() + "/login");
			return false;
		}
		return true;
	}

}
