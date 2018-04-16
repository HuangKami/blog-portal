package com.kami.blog.controller;

import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kami.blog.common.Assist;
import com.kami.blog.model.ComposeArticle;
import com.kami.blog.redis.ArticleRedis;
import com.kami.blog.service.ArticleService;
import com.kami.blog.util.DateHelper;
import com.kami.blog.util.KeyHelper;

@Controller
@RequestMapping("/main")
public class MainController {
	@Autowired
	private ArticleService articleService;
	@Autowired
	private ArticleRedis articleRedis;
	public static final AtomicLong COUNT = new AtomicLong(0);
	public static final long TIME = 1523357555364L;
	@PostConstruct
	public void init() {
		COUNT.addAndGet(articleService.getArticleRowCount(null));
	}
	
	@RequestMapping
	public String index(Model model) {
		//最新文章
		Assist assist = new Assist().setStartRow(0).setRowSize(10).setOrder(Assist.order("Article.updateTime", false));
		List<ComposeArticle> latestArticles = (List<ComposeArticle>) articleService.formatComposeArticle(articleService.selectComposeArticle(assist), 300);
		model.addAttribute("latestArticles", latestArticles);
		
		//最热文章
		Set<ComposeArticle> hotestArticles = (Set<ComposeArticle>) articleService.formatComposeArticle(articleRedis.getArticles(KeyHelper.HOTEST_ARTICLE), 70);
		model.addAttribute("hotestArticles", hotestArticles);
		
		for (ComposeArticle composeArticle : articleRedis.getArticles(KeyHelper.HOTEST_ARTICLE)) {
			composeArticle.setContent(articleService.format(composeArticle.getContent()));
			composeArticle.setContent(composeArticle.getContent().substring(0, Math.min(200, composeArticle.getContent().length())));
			model.addAttribute("recommendAticle", composeArticle);
			break;
		}
		
		model.addAttribute("COUNT", COUNT);
		model.addAttribute("TIME", DateHelper.differentDaysByMillisecond(TIME, System.currentTimeMillis()));
		return "main/main";
	}
}
