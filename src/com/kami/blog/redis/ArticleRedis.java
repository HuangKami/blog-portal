package com.kami.blog.redis;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import com.kami.blog.common.Assist;
import com.kami.blog.model.ComposeArticle;
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
	private RedisTemplate<String, ComposeArticle> redisTemplate;
	private ZSetOperations<String, ComposeArticle> zSetOperations;
	public static final int SIZE = 15;
	
	@PostConstruct
	private void init() {
		zSetOperations = redisTemplate.opsForZSet();
		zSetOperations.removeRange(KeyHelper.HOTEST_ARTICLE, 0, Integer.MAX_VALUE);
		Assist assist = new Assist().setStartRow(0).setRowSize(SIZE).setOrder(Assist.order("Article.readCount", false));
		for(ComposeArticle article : articleService.selectComposeArticle(assist)) {
			zSetOperations.add(KeyHelper.HOTEST_ARTICLE, article, article.getReadCount());
		}
	}
	
	/**
	 * 获取top 20最热文章
	 */
	public Set<ComposeArticle> getArticles(String key) {
		if(!redisTemplate.hasKey(key)) {
			return new HashSet<>();
		}
		return zSetOperations.reverseRangeByScore(key, 0, Integer.MAX_VALUE, 0, Math.min(SIZE, zSetOperations.size(key)));
	}
	
	/**
	 * 增加最热文章
	 */
	public void addArticle(String key, ComposeArticle article) {
		zSetOperations.add(KeyHelper.HOTEST_ARTICLE, article, article.getReadCount());
	}
	
	/**
	 * 获取最热文章最后一条记录的分数
	 */
	public int getScore(String key) {
		if(!redisTemplate.hasKey(key)) {
			return 0;
		}
		Set<ComposeArticle> set = zSetOperations.reverseRangeByScore(key, 0, Integer.MAX_VALUE, Math.min(SIZE, zSetOperations.size(key)) - 1, 1);
		for (ComposeArticle article : set) {
			return article.getReadCount();
		}
		return 0;
	}
	
	/**
	 * 更新分数
	 */
	public boolean updateScore(String key, int articleId, int delta) {
		Set<ComposeArticle> set = zSetOperations.range(key, 0, Integer.MAX_VALUE);
		ComposeArticle composeArticle = null;
		for (ComposeArticle article : set) {
			if(article.getId() == articleId) {
				composeArticle = article;
				break;
			}
		}
		if(composeArticle != null) {
			zSetOperations.remove(KeyHelper.HOTEST_ARTICLE, composeArticle);
			composeArticle.setReadCount(composeArticle.getReadCount() + delta);
			addArticle(KeyHelper.HOTEST_ARTICLE, composeArticle);
			return true;
		}
		return false;
	}
	
	/**
	 * 移除缓存
	 */
	public long removeArticle(int articleId) {
		Set<ComposeArticle> set = zSetOperations.range(KeyHelper.HOTEST_ARTICLE, 0, Integer.MAX_VALUE);
		ComposeArticle article = null;
		for (ComposeArticle composeArticle : set) {
			if(composeArticle.getId() == articleId) {
				article = composeArticle;
				break;
			}
		}
		if(article != null) {
			return zSetOperations.remove(KeyHelper.HOTEST_ARTICLE, article);
		}
		return 0;
	}
	
	/**
	 *	定时清除排行榜外的文章
	 */
	public long removeHotestArticle() {
		return zSetOperations.removeRange(KeyHelper.HOTEST_ARTICLE, 2 * SIZE, Integer.MAX_VALUE);
	}
}
