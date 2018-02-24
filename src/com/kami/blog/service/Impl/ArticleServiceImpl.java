package com.kami.blog.service.Impl;
import java.util.List;
import com.kami.blog.dao.ArticleDao;
import com.kami.blog.model.Article;
import com.kami.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ArticleServiceImpl implements ArticleService{
    @Autowired
    private ArticleDao articleDao;
    @Override
    public long getArticleRowCount(){
        return articleDao.getArticleRowCount();
    }
    @Override
    public List<Article> selectArticle(){
        return articleDao.selectArticle();
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
    public int updateArticleById(Article enti){
        return articleDao.updateArticleById(enti);
    }
    @Override
    public int updateNonEmptyArticleById(Article enti){
        return articleDao.updateNonEmptyArticleById(enti);
    }

    public ArticleDao getArticleDao() {
        return this.articleDao;
    }

    public void setArticleDao(ArticleDao articleDao) {
        this.articleDao = articleDao;
    }

}