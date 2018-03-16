package com.kami.blog.quartz;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kami.blog.redis.ArticleRedis;

/**
 *	定时清除排行榜外的文章
 */
@Component
public class ClearArticleRedisJob {
	@Autowired
	private ArticleRedis articleRedis;
	private Logger logger = Logger.getLogger(ClearArticleRedisJob.class);
	
	public void startTask() {
		try {
			long i = articleRedis.removeHotestArticle();
			logger.info("定时器删除了" + i + "篇文章");
		} catch (Exception e) {
			logger.error("定时器删除文章失败" + e);
		}
		
	}
}
