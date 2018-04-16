package com.kami.blog.controller;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

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
import com.kami.blog.model.Article;
import com.kami.blog.model.Collect;
import com.kami.blog.model.ComposeArticle;
import com.kami.blog.model.User;
import com.kami.blog.quartz.ExecutorTask;
import com.kami.blog.redis.ArticleRedis;
import com.kami.blog.redis.TopicRedis;
import com.kami.blog.service.ArticleService;
import com.kami.blog.service.CollectService;
import com.kami.blog.service.ReplyService;
import com.kami.blog.service.UserService;
import com.kami.blog.util.KeyHelper;
import com.kami.blog.util.SessionHelper;
import com.kami.blog.util.StringHelper;

@Controller
@RequestMapping("/article")
public class ArticleController {
	@Autowired
	private UserService userService;
	@Autowired
	private ArticleService articleService;
	@Autowired
	private ReplyService replyService;
	@Autowired
	private ArticleRedis articleRedis;
	@Autowired
	private TopicRedis topicRedis;
	@Autowired
	private CollectService collectService;
	@Autowired
	private LuceneService luceneService;
	public static final int RECOMMENDSIZE = 8;
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
		//帖子详情
		ComposeArticle article = articleService.selectDetailArticle(articleId);
		if(article == null) {
			return "404";
		}
		model.addAttribute("detailArticle", article);
		User user = (User) SessionHelper.getAttribute(request, KeyHelper.USER);
		if(user != null) {
			model.addAttribute(KeyHelper.USER, user);
		}
		
		//推荐
		List<Article> recommendArticles = new LinkedList<>();
		for(Article article2 : luceneService.search(article.getTopic())) {
			if(article2.getId() != article.getId() && recommendArticles.size() <= RECOMMENDSIZE) {
				recommendArticles.add(article2);
			}
		}
		model.addAttribute("recommendArticles", recommendArticles);
		
		//文章作者信息
		model.addAttribute("count", userService.selectPersonalDetail(article.getUser().getId()));
		
		//主题
		model.addAttribute("topics", topicRedis.getTopics());
		
		//阅读排行
		model.addAttribute("reads", (Set<ComposeArticle>) articleService.formatComposeArticle(articleRedis.getArticles(KeyHelper.HOTEST_ARTICLE), 70));
		
		//更新浏览数
		ExecutorTask.addTask(new Runnable() {
			@Override
			public void run() {
				articleService.updateReadCount(article);
			}
		});
		
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
			MainController.COUNT.incrementAndGet();
			ExecutorTask.addTask(new Runnable() {
				public void run() {
					topicRedis.addTopicCount(article.getTopic(), 1);
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
			Article article = new Article();
			article.setId(articleId);
			article.setDel(true);
			articleService.updateNonEmptyArticleById(article);
			articleRedis.removeArticle(articleId);
			MainController.COUNT.decrementAndGet();
			ExecutorTask.addTask(new Runnable() {
				public void run() {
					topicRedis.addTopicCount(articleService.selectArticleById(articleId).getTopic(), -1);
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
		model.addAttribute("keyword", keyword);
		if(StringHelper.isNoneEmpty(keyword)) {
			List<Article> articles = luceneService.search(keyword);
			model.addAttribute("searchList", articles);
		}
		//阅读排行
		model.addAttribute("hottest", (Set<ComposeArticle>) articleService.formatComposeArticle(articleRedis.getArticles(KeyHelper.HOTEST_ARTICLE), 70));
		return "lucene/search";
	}
}
