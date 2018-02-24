package com.kami.blog.controller;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kami.blog.model.Article;
import com.kami.blog.service.ArticleService;

@Controller
@RequestMapping("/main")
public class MainController {
	@Autowired
	private ArticleService articleService;
	
	@RequestMapping
	public String index(Model model) {
		List<Article> list = articleService.selectArticle();
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
		model.addAttribute("list", list);
		return "main/main";
	}
}
