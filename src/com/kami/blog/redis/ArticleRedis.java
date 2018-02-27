package com.kami.blog.redis;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import com.kami.blog.common.Assist;
import com.kami.blog.model.Article;
import com.kami.blog.service.ArticleService;
import com.kami.blog.util.KeyHelper;

/**
 * 最热文章缓存
 */
@Service
public class ArticleRedis {
	@Autowired
	private ArticleService articleService;
	@Autowired
	private RedisTemplate<String, Article> redisTemplate;
	private ZSetOperations<String, Article> zSetOperations;
	public static final int SIZE = 10;
	
	@PostConstruct
	private void init() {
		zSetOperations = redisTemplate.opsForZSet();
		
		Assist assist = new Assist().setStartRow(0).setRowSize(SIZE).setOrder(Assist.order("Article.readCount", false));
		for(Article article : articleService.selectArticle(assist)) {
			zSetOperations.add(KeyHelper.HOTEST_ARTICLE, article, article.getReadCount());
		}
	}
	
	/**
	 * 获取top 10最热文章
	 */
	public Set<Article> getArticles(String key) {
		if(!redisTemplate.hasKey(key)) {
			return new HashSet<>();
		}
		return zSetOperations.reverseRangeByScore(key, 0, Integer.MAX_VALUE, 0, Math.min(SIZE, zSetOperations.size(key)));
	}
	
	/**
	 * 增加最热文章
	 */
	public void addArticle(String key, Article article) {
		zSetOperations.add(KeyHelper.HOTEST_ARTICLE, article, article.getReadCount());
	}
	
	/**
	 * 获取最热的第十条记录的分数
	 */
	public int getScore(String key) {
		if(!redisTemplate.hasKey(key)) {
			return 0;
		}
		Set<Article> set = zSetOperations.reverseRangeByScore(key, 0, Integer.MAX_VALUE, Math.min(SIZE, zSetOperations.size(key)) - 1, 1);
		for (Article article : set) {
			return article.getReadCount();
		}
		return 0;
	}
	
	/**
	 * 更新分数
	 */
	public void updateScore(String key, int articleId, int delta) {
		Set<Article> set = zSetOperations.range(key, 0, Integer.MAX_VALUE);
		for (Article article : set) {
			if(article.getId() == articleId) {
				zSetOperations.incrementScore(key, article, delta);
			}
		}
	}
	
	/**
	 * 定时刷进数据库
	 */
	public int updateIntoMysql(String key) {
		Set<Article> set = zSetOperations.reverseRange(key, 0, Integer.MAX_VALUE);
		int result = 0;
		int index = 0;
		int lowScore = 0;
		for (Article article : set) {
			result += articleService.updateArticleById(article);
			if(index == SIZE) {
				lowScore = article.getReadCount();
			}
			index ++;
		}
		zSetOperations.removeRange(key, lowScore, Integer.MAX_VALUE);
		return result;
	}
}
