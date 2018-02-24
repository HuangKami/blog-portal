package com.kami.blog.service.Impl;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.kami.blog.dao.ArticleDao;
import com.kami.blog.model.Article;
import com.kami.blog.common.Assist;
import com.kami.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ArticleServiceImpl implements ArticleService{
    @Autowired
    private ArticleDao articleDao;
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

    public ArticleDao getArticleDao() {
        return this.articleDao;
    }

    public void setArticleDao(ArticleDao articleDao) {
        this.articleDao = articleDao;
    }
	
    @Override
	public List<Article> selectLatestArticle(Assist assist) {
    	List<Article> list = selectArticle(assist);
    	for (Article article : list) {
			String content = article.getContent();
			Pattern pattern = Pattern.compile(">(.*?)<");
			Matcher matcher = pattern.matcher(content);
			StringBuilder sbBuilder = new StringBuilder();
			while(matcher.find()){
				sbBuilder.append(matcher.group().replaceAll(">", "").replaceAll("<", ""));
			}
			article.setContent(sbBuilder.toString().substring(0, 200));
		}
		return null;
	}

}