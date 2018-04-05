package com.kami.blog.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kami.blog.model.Follow;
import com.kami.blog.model.User;
import com.kami.blog.service.ArticleService;
import com.kami.blog.service.FollowService;
import com.kami.blog.service.UserService;
import com.kami.blog.util.KeyHelper;
import com.kami.blog.util.SessionHelper;

@Controller
@RequestMapping("/personal")
public class PersonalController {
	@Autowired
	private ArticleService articleService;
	@Autowired
	private UserService userService;
	@Autowired
	private FollowService followService;
	
	@RequestMapping
	public String index() {
		return "redirect:/login";
	}
 	
	@RequestMapping("/{userId}")
	public String personalIndex(HttpServletRequest request, Model model,@PathVariable String userId) {
		User user = userService.selectUserById(userId);
		if(user == null) {
			return "404";
		}
		model.addAttribute("person", user);
		model.addAttribute("collectArticles", articleService.selectCollectArticleByUserId(userId));
		model.addAttribute("followUsers", userService.selectFollowByUserId(userId));
		model.addAttribute("followedUsers", userService.selectFollowedByUserId(userId));
		model.addAttribute("articles", articleService.selectArticleByUserId(userId));
		return "personal/personal";
	}
	
	@RequestMapping("/follow")
	@ResponseBody
	public String follow(HttpServletRequest request, Follow follow) {
		try {
			User user = (User) SessionHelper.getAttribute(request, KeyHelper.USER);
			follow.setFollower(user.getId());
			followService.insertFollow(follow);
			return KeyHelper.SUCCESS;
		} catch (Exception e) {
			return KeyHelper.ERROR;
		}
	}
}
