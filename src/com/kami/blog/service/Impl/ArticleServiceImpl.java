package com.kami.blog.service.Impl;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.kami.blog.dao.ArticleDao;
import com.kami.blog.model.Article;
import com.kami.blog.model.ComposeArticle;
import com.kami.blog.model.Topic;
import com.kami.blog.redis.ArticleRedis;
import com.kami.blog.common.Assist;
import com.kami.blog.service.ArticleService;
import com.kami.blog.util.KeyHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private ArticleRedis articleRedis;
    @Override
    public long getArticleRowCount(Assist assist){
        return articleDao.getArticleRowCount(assist);
    }
    @Override
    public List<Article> selectArticle(Assist assist){
        return articleDao.selectArticle(assist);
    }
    @Override
    public Article selectArticleByObj(Article obj){
        return articleDao.selectArticleByObj(obj);
    }
    @Override
    public Article selectArticleById(Integer id){
        return articleDao.selectArticleById(id);
    }
    @Override
    public int insertArticle(Article value){
        return articleDao.insertArticle(value);
    }
    @Override
    public int insertNonEmptyArticle(Article value){
        return articleDao.insertNonEmptyArticle(value);
    }
    @Override
    public int insertArticleByBatch(List<Article> value){
        return articleDao.insertArticleByBatch(value);
    }
    @Override
    public int deleteArticleById(Integer id){
        return articleDao.deleteArticleById(id);
    }
    @Override
    public int deleteArticle(Assist assist){
        return articleDao.deleteArticle(assist);
    }
    @Override
    public int updateArticleById(Article enti){
        return articleDao.updateArticleById(enti);
    }
    @Override
    public int updateArticle(Article value, Assist assist){
        return articleDao.updateArticle(value,assist);
    }
    @Override
    public int updateNonEmptyArticleById(Article enti){
        return articleDao.updateNonEmptyArticleById(enti);
    }
    @Override
    public int updateNonEmptyArticle(Article value, Assist assist){
        return articleDao.updateNonEmptyArticle(value,assist);
    }
    @Override
	public List<ComposeArticle> selectComposeArticle(Assist assist) {
    	return articleDao.selectComposeArticle(assist);
    }

    public ArticleDao getArticleDao() {
        return this.articleDao;
    }

    public void setArticleDao(ArticleDao articleDao) {
        this.articleDao = articleDao;
    }
    
    @Override
    public Collection<Article> formatArticle(Collection<Article> list, int contentLength) {
    	for (Article article : list) {
    		String format = format(article.getContent());
    		article.setContent(format.substring(0, Math.min(contentLength, format.length())));
		}
    	return list;
    }
    
    @Override
    public Collection<ComposeArticle> formatComposeArticle(Collection<ComposeArticle> list, int contentLength) {
    	for (ComposeArticle article : list) {
    		String format = format(article.getContent());
			article.setContent(format.substring(0, Math.min(contentLength, format.length())));
		}
    	return list;
    }
    
    @Override
    public String format(String content) {
    	Pattern pattern = Pattern.compile(">(.*?)<");
		Matcher matcher = pattern.matcher(content);
		StringBuilder sbBuilder = new StringBuilder();
		while(matcher.find()){
			sbBuilder.append(matcher.group().replaceAll(">", "").replaceAll("<", ""));
		}
		return sbBuilder.toString();
    }
	@Override
	public int updateArticleReadCountById(Integer id) {
		return articleDao.updateArticleReadCountById(id);
	}
	@Override
	public ComposeArticle selectDetailArticle(Integer id) {
		return articleDao.selectDetailArticle(id);
	}
	
	@Override
	public void updateReadCount(ComposeArticle article) {
		this.updateArticleReadCountById(article.getId());
		article.setReadCount(article.getReadCount() + 1);
		article.setCommentCount(article.getReplies().size());
		synchronized (this) {
			int score = articleRedis.getScore(KeyHelper.HOTEST_ARTICLE);
			boolean result = false;
			if(score <= article.getReadCount() - 1) {
				result = articleRedis.updateScore(KeyHelper.HOTEST_ARTICLE, article.getId(), 1);
			} 
			if(!result && score <= article.getReadCount()) {
				articleRedis.addArticle(KeyHelper.HOTEST_ARTICLE, article);
			}
		}
	}
	@Override
	public List<Article> selectArticleByUserId(String userId) {
		return articleDao.selectArticleByUserId(userId);
	}
	@Override
	public List<Article> selectCollectArticleByUserId(String userId) {
		return articleDao.selectCollectArticleByUserId(userId);
	}
	@Override
	public List<Topic> selectTopicDetail() {
		return articleDao.selectTopicDetail();
	}
}