package com.kami.blog.controller;



import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kami.blog.model.Reply;
import com.kami.blog.model.User;
import com.kami.blog.service.ReplyService;
import com.kami.blog.util.KeyHelper;
import com.kami.blog.util.SessionHelper;

@Controller
@RequestMapping("/reply")
public class ReplyController {
	@Autowired
	private ReplyService replyService;
	private Logger logger = Logger.getLogger(ReplyController.class);
	
	@RequestMapping
	@ResponseBody
	public Reply reply(HttpServletRequest request, Reply reply) {
		User user = (User) SessionHelper.getAttribute(request, KeyHelper.USER);
		reply.setUser(user);
		reply.setUserId(user.getId());
		if(reply.getArticleId() == 0) {
			return null;
		}
		try {
			reply.setCreateTime(new Timestamp(System.currentTimeMillis()));
			replyService.insertReply(reply);
		} catch (Exception e) {
			logger.error(e, e);
		}
		return reply;
	}
}
