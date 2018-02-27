package com.kami.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kami.blog.common.Assist;
import com.kami.blog.model.Article;
import com.kami.blog.service.ArticleService;

@Controller
@RequestMapping("/article")
public class ArticleController {
	@Autowired
	private ArticleService articleService;
	//private Logger logger = Logger.getLogger(ArticleController.class);
	
	@RequestMapping("/latestArticles/{pageNow}")
	@ResponseBody
	public List<Article> getLatesArticles(@PathVariable int pageNow) {
		Assist assist = new Assist();
		assist.setStartRow((pageNow - 1) * 10).setRowSize(10).setOrder(Assist.order("Article.updateTime", false));
		return (List<Article>) articleService.formatArticle(articleService.selectArticle(assist), 200);
	}
}
