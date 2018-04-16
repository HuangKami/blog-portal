package com.kami.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kami.blog.model.User;

@Controller
@RequestMapping("/user")
public class UserController {
	@RequestMapping("/editPassword")
	public void resetPassword(User user) {
		
	}
}
