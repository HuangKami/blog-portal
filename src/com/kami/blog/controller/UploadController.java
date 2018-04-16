package com.kami.blog.controller;


import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.kami.blog.model.User;
import com.kami.blog.service.UserService;
import com.kami.blog.util.KeyHelper;
import com.kami.blog.util.OSSHelper;
import com.kami.blog.util.SessionHelper;
import com.kami.blog.util.StringHelper;

/**
 *	文件上传
 */
@Controller
@RequestMapping("/upload")
public class UploadController {
	private Logger logger = Logger.getLogger(UploadController.class); 
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/head")
	@ResponseBody
	public String uploadHead(HttpServletRequest request, @RequestParam("uploadHeadImg") MultipartFile uploadHeadImg) {
		User user = (User) SessionHelper.getAttribute(request, KeyHelper.USER);
        if(user == null) {
        	logger.error("错误：用户未登录请求修改头像");
        	return KeyHelper.ERROR;
        }
        
        String imgUrl = "";
        try {
        	imgUrl = OSSHelper.save(uploadHeadImg);
        	if(StringHelper.equals(KeyHelper.ERROR, imgUrl)) {
        		logger.error("头像上传失败");
            	return KeyHelper.ERROR;
        	}
            user.setHeadImg(imgUrl);
            userService.updateNonEmptyUserById(user);
        } catch (Exception e) {
        	logger.error("头像上传失败" + e);
        	return KeyHelper.ERROR;
        }
		return imgUrl;
	}
}
