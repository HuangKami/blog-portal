package com.kami.blog.controller;

import java.util.Date;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kami.blog.model.User;
import com.kami.blog.service.UserService;
import com.kami.blog.spring.UserExistBloomFilter;
import com.kami.blog.util.KeyHelper;
import com.kami.blog.util.MD5Helper;
import com.kami.blog.util.MailHelper;
import com.kami.blog.util.RandomHelper;
import com.kami.blog.util.SessionHelper;
import com.kami.blog.util.StringHelper;


@Controller
@RequestMapping("/login")
public class LoginController {
	@Autowired
	private UserService userService;
	
	private Logger logger =  Logger.getLogger(LoginController.class);
	
	/**
	 * 登陆界面
	 */
	@RequestMapping
	public String index() {
		return "login/login";
	}
	
	/**
	 * 用户请求登陆
	 */
	@RequestMapping("/login")
	@ResponseBody
	public String login(HttpServletRequest request, @ModelAttribute User loginUser,
			@RequestParam(value="loginAuthCode") String loginAuthCode) {
		loginUser.setEmail(loginUser.getName());
		String result = userService.login(request, loginUser, loginAuthCode);
		if(StringHelper.equals(KeyHelper.SUCCESS, result)) {
			return KeyHelper.SUCCESS;
		}
		return result;
	}
	
	/**
	 * 用户请求注销
	 */
	@RequestMapping("/logout")
	@ResponseBody
	public String logout(HttpServletRequest request) {
		SessionHelper.removeAttribute(request, KeyHelper.USER);
		return KeyHelper.SUCCESS;
	}
	
	/**
	 * 检查用户名是否已存在
	 */
	@RequestMapping("/checkName")
	@ResponseBody
	public boolean checkName(@RequestParam(value="name") String name) {
		if(UserExistBloomFilter.nameFilter.mightContain(name)) {
			return false;
		}
		return true;
	}

	/**
	 * 检查邮件是否已存在
	 */
	@RequestMapping("/checkEmail")
	@ResponseBody
	public boolean checkEmail(@RequestParam(value="email") String email) {
		if(UserExistBloomFilter.emailFilter.mightContain(email)) {
			return false;
		}
		return true;
	}
	
	/**
	 * 注册用户
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseBody
	public String register(HttpServletRequest request, @ModelAttribute User user, 
			@RequestParam(value=KeyHelper.REGISTER_AUTHCODE) String registerAuthCode) {
		String result = userService.checkRegister(request, user, registerAuthCode);
		if(!StringHelper.equals(result, KeyHelper.SUCCESS)) {
			return result;
		}
		try {
			user.setPassword(MD5Helper.md5(user.getPassword()));
			user.setCreateTime(new Date());
			userService.insertUser(user);
		} catch (Exception e) {
			logger.error("插入用户失败:" + user.toString() + e);
			return "注册失败，请重新注册";
		}
		//加入布隆过滤器
		UserExistBloomFilter.nameFilter.put(user.getName());
		UserExistBloomFilter.emailFilter.put(user.getEmail());
		//移除验证码
		SessionHelper.removeAttribute(request, KeyHelper.REGISTER_AUTHCODE);
		//发激活邮件
		MailHelper.sendEmail(user.getEmail(), user.getName(), "欢迎注册KBlog", 
				"恭喜您注册成功，请点击下面链接进行激活，若无法打开请复制链接在浏览器打开，该链接将在3天后失效  " + KeyHelper.URL + "/login/active/" + user.getId());
		return result;
	}
	
	/**
	 * 激活用户
	 */
	@RequestMapping("/active/{id}")
	public String activeUser(@PathVariable String id, Model model) {
		User user = userService.selectUserById(id);
		if(user == null) {
			model.addAttribute(KeyHelper.MESSAGE, "该链接已失效");
		} else {
			if(user.isActive()) {
				model.addAttribute(KeyHelper.MESSAGE, "已激活成功过");
			} else {
				user.setActive(true);
				userService.updateUserById(user);
				model.addAttribute(KeyHelper.MESSAGE, "激活成功");
			}
		}
		return "/login/login";
	}
	
	/**
	 * 找回密码
	 */
	@RequestMapping("/findBack")
	@ResponseBody
	public String findBackPass(HttpServletRequest request, @RequestParam(value="email") String email,
			@RequestParam(value="findBackAuthCode") String findBackAuthCode) {
		if(StringHelper.isEmpty(email)) {
			return "邮箱不能为空";
		}
		if(StringHelper.isEmpty(findBackAuthCode)) {
			return "验证码不能为空";
		}
		Object authCode = SessionHelper.getAttribute(request, KeyHelper.FIND_BACK_AUTHCODE);
		if(authCode == null) {
			return "请刷新重试";
		}
		if(!StringHelper.equalsIgnoreCase(findBackAuthCode, authCode.toString())) {
			return "验证码错误";
		}
		User user = userService.selectUserByEmail(email);
		if(user == null) {
			return "找不到该用户";
		}
		try {
			String code = RandomHelper.getRandomCode();
			SessionHelper.setAttribute(request, KeyHelper.EMAIL_AUTHCODE, code);
			SessionHelper.setAttribute(request, KeyHelper.EMAIL, email);
			MailHelper.sendEmail(user.getEmail(), user.getName(), "KBlog重置验证码", code);
		} catch (Exception e) {
			return "邮箱不存在";
		}
		SessionHelper.removeAttribute(request, KeyHelper.FIND_BACK_AUTHCODE);
		return KeyHelper.SUCCESS;
	}
	
	/**
	 * 确认邮箱验证码
	 */
	@RequestMapping("/affirmCode")
	@ResponseBody
	public String affirmCode(HttpServletRequest request, @RequestParam(value="code") String code) {
		if(StringHelper.isEmpty(code)) {
			return "邮箱验证码不能为空";
		}
		if(!StringHelper.equalsIgnoreCase(SessionHelper.getAttribute(request, KeyHelper.EMAIL_AUTHCODE).toString(), code)) {
			return "验证码错误";
		}
		return KeyHelper.SUCCESS;
	}
	
	/**
	 * 重置密码
	 */
	@RequestMapping("/resetPassword")
	@ResponseBody
	public String resetPassword(HttpServletRequest request, @RequestParam(value="password") String password) {
		if(StringHelper.isEmpty(password)) {
			return "密码不能为空";
		}
		Object email = SessionHelper.getAttribute(request, KeyHelper.EMAIL);
		if(email == null || SessionHelper.getAttribute(request, KeyHelper.EMAIL_AUTHCODE) == null) {
			return "别闹了";
		}
		SessionHelper.removeAttribute(request, KeyHelper.EMAIL);
		SessionHelper.removeAttribute(request, KeyHelper.EMAIL_AUTHCODE);
		User user = userService.selectUserByEmail(email.toString());
		if(user == null) {
			return "该用户不存在";
		}
		user.setPassword(MD5Helper.md5(password));
		try {
			userService.updateUserById(user);
		} catch (Exception e) {
			logger.error("重置密码失败" + e);
			return "重置密码失败";
		}
		return KeyHelper.SUCCESS;
	}
}
