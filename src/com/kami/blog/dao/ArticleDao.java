package com.kami.blog.dao;
import com.kami.blog.model.Article;
import com.kami.blog.model.ComposeArticle;

import java.util.List;
import com.kami.blog.common.Assist;
import org.apache.ibatis.annotations.Param;
public interface ArticleDao{
	/**
	 * 获得Article数据的总行数,可以通过辅助工具Assist进行条件查询,如果没有条件则传入null
	 * @param assist
	 * @return
	 */
    long getArticleRowCount(Assist assist);
	/**
	 * 获得Article数据集合,可以通过辅助工具Assist进行条件查询,如果没有条件则传入null
	 * @param assist
	 * @return
	 */
    List<Article> selectArticle(Assist assist);
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
	 * 通过辅助工具Assist的条件删除Article
	 * @param assist
	 * @return
	 */
    int deleteArticle(Assist assist);
	/**
	 * 通过Article的id更新Article中的数据,包括null值
	 * @param enti
	 * @return
	 */
    int updateArticleById(Article enti);
 	/**
	 * 通过辅助工具Assist的条件更新Article中的数据,包括null值
	 * @param value
	 * @param assist
	 * @return
	 */
    int updateArticle(@Param("enti") Article value, @Param("assist") Assist assist);
	/**
	 * 通过Article的id更新Article中属性不为null的数据
	 * @param enti
	 * @return
	 */
    int updateNonEmptyArticleById(Article enti);
 	/**
	 * 通过辅助工具Assist的条件更新Article中属性不为null的数据
	 * @param value
	 * @param assist
	 * @return
	 */
    int updateNonEmptyArticle(@Param("enti") Article value, @Param("assist") Assist assist);
    /**
     * 通过id更新阅读数
     */
    int updateArticleReadCountById(Integer id);
    /**
     * 查询文章
     */
    List<ComposeArticle> selectComposeArticle(Assist assist);
    /**
     * 查询文章详情
     */
    ComposeArticle selectDetailArticle(Integer id);
    /**
     * 通过用户查询文章
     */
    List<Article> selectArticleByUserId(String userId);
    /**
     * 查询收藏的文章
     */
    List<Article> selectCollectArticleByUserId(String userId);
}