package com.kami.blog.controller;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kami.blog.common.Assist;
import com.kami.blog.lucene.LuceneService;
import com.kami.blog.lucene.LuceneTask;
import com.kami.blog.model.Article;
import com.kami.blog.model.Collect;
import com.kami.blog.model.ComposeArticle;
import com.kami.blog.model.User;
import com.kami.blog.redis.ArticleRedis;
import com.kami.blog.service.ArticleService;
import com.kami.blog.service.CollectService;
import com.kami.blog.service.ReplyService;
import com.kami.blog.util.KeyHelper;
import com.kami.blog.util.SessionHelper;
import com.kami.blog.util.StringHelper;

@Controller
@RequestMapping("/article")
public class ArticleController {
	@Autowired
	private ArticleService articleService;
	@Autowired
	private ReplyService replyService;
	@Autowired
	private ArticleRedis articleRedis;
	@Autowired
	private CollectService collectService;
	@Autowired
	private LuceneService luceneService;
	private Logger logger = Logger.getLogger(ArticleController.class);
	
	@RequestMapping("/latestArticles/{pageNow}")
	@ResponseBody
	public List<ComposeArticle> getLatesArticles(@PathVariable int pageNow) {
		Assist assist = new Assist();
		assist.setStartRow((pageNow - 1) * 10).setRowSize(10).setOrder(Assist.order("Article.updateTime", false));
		return (List<ComposeArticle>) articleService.formatComposeArticle(articleService.selectComposeArticle(assist), 300);
	}
	
	@RequestMapping("/{articleId}")
	public String getDetailArticle(HttpServletRequest request ,@PathVariable int articleId, Model model) {
		ComposeArticle article = articleService.selectDetailArticle(articleId);
		if(article == null) {
			return "404";
		}
		model.addAttribute("detailArticle", article);
		User user = (User) SessionHelper.getAttribute(request, KeyHelper.USER);
		if(user != null) {
			model.addAttribute(KeyHelper.USER, user);
		}
		articleService.updateReadCount(article);
		return "main/detail";
	}
	
	@RequestMapping("/articlePage")
	public String toArticlePage() {
		return "main/articlePage";
	}
	
	@RequestMapping("/publishArticle")
	@ResponseBody
	public String publishArticle(HttpServletRequest request, Article article) {
		if(StringHelper.isEmpty(article.getTopic())) {
			return "主题不能为空";
		}
		if(StringHelper.isEmpty(article.getTitle())) {
			return "标题不能为空";
		}
		if(StringHelper.isEmpty(article.getContent())) {
			return "内容不能为空";
		}
		User user = (User) SessionHelper.getAttribute(request, KeyHelper.USER);
		article.setUserId(user.getId());
		article.setCreateTime(new Timestamp(System.currentTimeMillis()));
		article.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		try {
			articleService.insertArticle(article);
			LuceneTask.addTask(new Runnable() {
				public void run() {
					luceneService.createIndex(article);
				}
			});
			return StringHelper.integerToString(article.getId());
		} catch (Exception e) {
			logger.error(e, e);
			return "发表失败，请稍后发表";
		}
	}
	
	
	@RequestMapping("/delete/{articleId}")
	@ResponseBody
	@Transactional
	public String deleteArticle(HttpServletRequest request, @PathVariable Integer articleId) {
		try {
			replyService.deleteReplyByArticleId(articleId);
			articleService.deleteArticleById(articleId);
			articleRedis.removeArticle(articleId);
			LuceneTask.addTask(new Runnable() {
				public void run() {
					luceneService.deleteIndex(articleId);;
				}
			});
			return KeyHelper.SUCCESS;
		} catch (Exception e) {
			logger.error(e, e);
			return KeyHelper.ERROR;
		}
		 
	}
	
	@RequestMapping("/collect")
	@ResponseBody
	public String collectArticle(HttpServletRequest request, Integer articleId) {
		try {
			User user = (User) SessionHelper.getAttribute(request, KeyHelper.USER);
			Collect collect = new Collect();
			collect.setArticleId(articleId);
			collect.setUserId(user.getId());
			collectService.insertCollect(collect);
			return KeyHelper.SUCCESS;
		} catch (Exception e) {
			return KeyHelper.ERROR;
		}
	}
	
	@RequestMapping("/search")
	public String search(Model model, String keyword) {
		model.addAttribute("searchList", luceneService.search(keyword));
		return "lucene/search";
	}
}
