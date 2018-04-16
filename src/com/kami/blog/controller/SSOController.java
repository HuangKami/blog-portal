package com.kami.blog.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kami.blog.model.User;
import com.kami.blog.redis.UserSessionRedis;
import com.kami.blog.util.CookieHelper;
import com.kami.blog.util.KeyHelper;
import com.kami.blog.util.SessionHelper;

@Controller
@RequestMapping("/sso")
public class SSOController {
	@Autowired
	private RedisTemplate<String, User> redisTemplate;
	@Autowired
	private UserSessionRedis userSessionRedis;

	@RequestMapping
	public String sso(HttpServletRequest request) {
		String token = CookieHelper.getCookieValue(request, KeyHelper.TOKEN);
		User user = redisTemplate.opsForValue().get(token);
		if(user != null) {
			SessionHelper.setAttribute(request, KeyHelper.USER, user);
			userSessionRedis.saveUserSession(user.getId(), SessionHelper.getSessionId(request));
		}
		return "redirect:/main";
	}
}
