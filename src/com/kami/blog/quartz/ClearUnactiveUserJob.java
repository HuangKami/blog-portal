package com.kami.blog.quartz;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kami.blog.service.UserService;

@Component
public class ClearUnactiveUserJob {
	@Autowired
	private UserService userService;
	private Logger logger = Logger.getLogger(ClearUnactiveUserJob.class);
	
	public void startTask() {
		try {
			int i = userService.deleteUnactiveUser();
			logger.info("定时器删除了" + i + "个用户");
		} catch (Exception e) {
			logger.error("定时器删除用户失败" + e);
		}
		
	}
}
