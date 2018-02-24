package com.kami.blog.dao;
import com.kami.blog.model.Article;
import java.util.List;
public interface ArticleDao{
	/**
	 * 获得Article数据的总行数
	 * @return
	 */
    long getArticleRowCount();
	/**
	 * 获得Article数据集合
	 * @return
	 */
    List<Article> selectArticle();
	/**
	 * 获得一个Article对象,以参数Article对象中不为空的属性作为条件进行查询
	 * @param obj
	 * @return
	 */
    Article selectArticleByObj(Article obj);
	/**
	 * 通过Article的id获得Article对象
	 * @param id
	 * @return
	 */
    Article selectArticleById(Integer id);
	/**
	 * 插入Article到数据库,包括null值
	 * @param value
	 * @return
	 */
    int insertArticle(Article value);
	/**
	 * 插入Article中属性值不为null的数据到数据库
	 * @param value
	 * @return
	 */
    int insertNonEmptyArticle(Article value);
	/**
	 * 批量插入Article到数据库,包括null值
	 * @param value
	 * @return
	 */
    int insertArticleByBatch(List<Article> value);
	/**
	 * 通过Article的id删除Article
	 * @param id
	 * @return
	 */
    int deleteArticleById(Integer id);
	/**
	 * 通过Article的id更新Article中的数据,包括null值
	 * @param enti
	 * @return
	 */
    int updateArticleById(Article enti);
	/**
	 * 通过Article的id更新Article中属性不为null的数据
	 * @param enti
	 * @return
	 */
    int updateNonEmptyArticleById(Article enti);
}