package com.kami.blog.quartz;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kami.blog.redis.ArticleRedis;
import com.kami.blog.util.KeyHelper;

/**
 * 定时刷新redis缓存进数据库
 */
@Component
public class UpdateArticleIntoMysqlJob {
	@Autowired
	private ArticleRedis articleRedis;
	private Logger logger = Logger.getLogger(UpdateArticleIntoMysqlJob.class);
	
	public void startTask() {
		try {
			int i = articleRedis.updateIntoMysql(KeyHelper.HOTEST_ARTICLE);
			logger.info("定时器刷新了" + i + "篇文章");
		} catch (Exception e) {
			logger.error("定时器刷新文章失败" + e);
		}
	}
}
