package com.kami.blog.controller;
import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kami.blog.entity.User;
import com.kami.blog.service.UserService;

@Controller
@RequestMapping("/user")
public class HelloController {
	@Resource
	private UserService userService;
	
	@RequestMapping("/userInfo")
	public String find(Model model) {
		model.addAttribute("userList", userService.selectUser(null));
		return "userInfo";
	}
	
	@RequestMapping("/insertUser")
	public String insert(User user) {
		userService.insertUser(user);
		return "success";
	}
}
