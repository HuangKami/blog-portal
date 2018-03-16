package com.kami.blog.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kami.blog.common.Assist;
import com.kami.blog.model.ComposeArticle;
import com.kami.blog.redis.ArticleRedis;
import com.kami.blog.service.ArticleService;
import com.kami.blog.util.KeyHelper;

@Controller
@RequestMapping("/main")
public class MainController {
	@Autowired
	private ArticleService articleService;
	@Autowired
	private ArticleRedis articleRedis;
	
	@RequestMapping
	public String index(Model model) {
		//最新文章
		Assist assist = new Assist().setStartRow(0).setRowSize(10).setOrder(Assist.order("Article.updateTime", false));
		List<ComposeArticle> latestArticles = (List<ComposeArticle>) articleService.formatComposeArticle(articleService.selectComposeArticle(assist), 300);
		model.addAttribute("latestArticles", latestArticles);
		
		//最热文章
		Set<ComposeArticle> hotestArticles = (Set<ComposeArticle>) articleService.formatComposeArticle(articleRedis.getArticles(KeyHelper.HOTEST_ARTICLE), 70);
		model.addAttribute("hotestArticles", hotestArticles);
		
		return "main/main";
	}
}
