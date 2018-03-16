<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>KBlog</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/nprogress.css">
<link rel="stylesheet" type="text/css" href="css/main.css">
<link rel="stylesheet" type="text/css" href="css/button.css">
<link rel="stylesheet" type="text/css" href="css/font-awesome.min.css">
<link rel="shortcut icon" href="images/kblog-ico.png">

<script src="js/jquery.js"></script>
<script src="js/common.js"></script>
<script src="js/nprogress.js"></script>
<script src="js/jquery.lazyload.min.js"></script>
<script src="js/article.js"></script>
<script src="js/reply.js"></script>
</head>
<body class="user-select single">
	<jsp:include page="header.jsp"></jsp:include>
	<section class="container">
	<div class="content-wrap">
		<div class="content">
			<header class="article-header">
			<h1 class="article-title" style="color: #8AA8BD;">${detailArticle.title}</h1>
			<div class="article-meta">
				<span class="item article-meta-time"> 
					<time class="time" data-toggle="tooltip" data-placement="bottom">
						<i class="glyphicon glyphicon-time"></i> 
						<fmt:formatDate value="${detailArticle.createTime}" pattern="yyyy-MM-dd HH:mm"/>
					</time>
				</span> 
				<span class="item article-meta-source" data-toggle="tooltip" data-placement="bottom">
					<i class="fa fa-user"></i> ${detailArticle.user.name}
				</span> 
				<span
					class="item article-meta-category" data-toggle="tooltip" data-placement="bottom">
					<i class="glyphicon glyphicon-list"></i> 
					<a href="#">${detailArticle.topic}</a>
				</span> 
				<span
					class="item article-meta-views" data-toggle="tooltip" data-placement="bottom">
					<i class="glyphicon glyphicon-eye-open"></i> ${detailArticle.readCount}
				</span> 
				<span
					class="item article-meta-comment" data-toggle="tooltip" data-placement="bottom">
					<i class="glyphicon glyphicon-comment"></i> ${fn:length(detailArticle.replies)}
				</span>
				<span
					class="item article-meta-comment" data-toggle="tooltip" data-placement="bottom">
					<i class="fa fa-remove"></i> <a href="#" onclick="return deleteArticle(${detailArticle.id})">删除</a>
				</span>
			</div>
			</header>
			<article class="article-content">
			<p>${detailArticle.content}</p>
			<div class="bdsharebuttonbox">
				<a href="#" class="bds_more" data-cmd="more"></a><a href="#"
					class="bds_qzone" data-cmd="qzone" title="分享到QQ空间"></a><a href="#"
					class="bds_tsina" data-cmd="tsina" title="分享到新浪微博"></a><a href="#"
					class="bds_tqq" data-cmd="tqq" title="分享到腾讯微博"></a><a href="#"
					class="bds_weixin" data-cmd="weixin" title="分享到微信"></a><a href="#"
					class="bds_tieba" data-cmd="tieba" title="分享到百度贴吧"></a><a href="#"
					class="bds_sqq" data-cmd="sqq" title="分享到QQ好友"></a>
			</div>

			<script>
				window._bd_share_config = {
					"common" : {
						"bdSnsKey" : {},
						"bdText" : "",
						"bdMini" : "2",
						"bdMiniList" : false,
						"bdPic" : "",
						"bdStyle" : "1",
						"bdSize" : "32"
					},
					"share" : {}
				};
				with (document)
					0[(getElementsByTagName('head')[0] || body)
							.appendChild(createElement('script')).src = 'http://bdimg.share.baidu.com/static/api/js/share.js?v=0.js?cdnversion='
							+ ~(-new Date() / 36e5)];
			</script>
			</article>
			<div class="article-tags">
				标签：<a href="#list/2/" rel="tag">DTcms博客</a><a href="#list/3/"
					rel="tag">木庄网络博客</a><a href="#list/4/" rel="tag">独立博客</a><a
					href="#list/5/" rel="tag">修复优化</a>
			</div>
			<div class="relates">
				<div class="title">
					<h3>相关推荐</h3>
				</div>
				<ul>
					<li><a href="#" title="">阿里JAVA开发手册零度的思考理解(一)-Java编程主题</a></li>
					<li><a href="#" title="">阿里JAVA开发手册零度的思考理解(二)-Java编程主题</a></li>
					<li><a href="#" title="">阿里JAVA开发手册零度的思考理解(三)-Java编程主题</a></li>
					<li><a href="#" title="">阿里JAVA开发手册零度的思考理解(四)-Java编程主题</a></li>
					<li><a href="#" title="">阿里JAVA开发手册零度的思考理解(五)-Java编程主题</a></li>
					<li><a href="#" title="">阿里JAVA开发手册零度的思考理解(六)-Java编程主题</a></li>
					<li><a href="#" title="">阿里JAVA开发手册零度的思考理解(七)-Java编程主题</a></li>
					<li><a href="#" title="">阿里JAVA开发手册零度的思考理解(八)-Java编程主题</a></li>
				</ul>
			</div>
			<div class="title" id="comment">
				<h3>评论</h3>
			</div>
			<div id="respond">
				<form id="comment-form" name="comment-form" action="" method="POST">
					<jsp:include page="../common/editor.jsp">
						<jsp:param name="editorHeight" value="20%"/> 
					</jsp:include>
				</form>
				<div><input class="button button-article" type="button" onclick="reply(${detailArticle.id})" value="回复" /> </div>
			</div>
			<div id="postcomments">
				<ol id="comment_list" class="commentlist">
					<c:forEach var="reply" items="${detailArticle.replies}">
						<li class="comment-content">
							<div class="comment-main">
								<p>
									<a href="#" rel="nofollow" target="_blank">${reply.user.name}</a>
									<span class="time">
										<fmt:formatDate value="${reply.createTime}" pattern="yyyy-MM-dd HH:mm"/> 
									</span><br>
									${reply.content}
								</p>
							</div>
						</li>
					</c:forEach>
				</ol>
			</div>
		</div>
	</div>
	<aside class="sidebar">
	<div class="fixed">
		<div class="widget widget-tabs">
			<ul class="nav nav-tabs" role="tablist">
				<li role="presentation" class="active"><a href="#notice"
					aria-controls="notice" role="tab" data-toggle="tab"
					draggable="false">统计信息</a></li>
				<li role="presentation"><a href="#contact"
					aria-controls="contact" role="tab" data-toggle="tab"
					draggable="false">联系站长</a></li>
			</ul>
			<div class="tab-content">
				<div role="tabpanel" class="tab-pane contact active" id="notice">
					<h2>日志总数: 888篇</h2>
					<h2>
						网站运行: <span id="sitetime">88天 </span>
					</h2>
				</div>
				<div role="tabpanel" class="tab-pane contact" id="contact">
					<h2>
						QQ: <a href="" target="_blank" rel="nofollow"
							data-toggle="tooltip" data-placement="bottom" title=""
							draggable="false" data-original-title="QQ:577211782">577211782</a>
					</h2>
					<h2>
						Email: <a href="mailto:577211782@qq.com" target="_blank"
							data-toggle="tooltip" rel="nofollow" data-placement="bottom"
							title="" draggable="false"
							data-original-title="Email:577211782@qq.com">577211782@qq.com</a>
					</h2>
				</div>
			</div>
		</div>
		<div class="widget widget_search">
			<form class="navbar-form" action="/Search" method="post">
				<div class="input-group">
					<input type="text" name="keyword" class="form-control" size="35"
						placeholder="请输入关键字" maxlength="15" autocomplete="off"> <span
						class="input-group-btn">
						<button class="btn btn-default btn-search" name="search"
							type="submit">搜索</button>
					</span>
				</div>
			</form>
		</div>
	</div>
	<div class="widget widget_hot">
		<h3>最新评论文章</h3>
		<ul>

			<li><a title="用DTcms做一个独立博客网站（响应式模板）" href="#"><span
					class="thumbnail"> <img class="thumb"
						data-original="images/201610181739277776.jpg"
						src="images/201610181739277776.jpg" alt="用DTcms做一个独立博客网站（响应式模板）"
						style="display: block;">
				</span><span class="text">用DTcms做一个独立博客网站（响应式模板）</span><span class="muted"><i
						class="glyphicon glyphicon-time"></i> 2016-11-01 </span><span
					class="muted"><i class="glyphicon glyphicon-eye-open"></i>88</span></a></li>
			<li><a title="用DTcms做一个独立博客网站（响应式模板）" href="#"><span
					class="thumbnail"> <img class="thumb"
						data-original="images/201610181739277776.jpg"
						src="images/201610181739277776.jpg" alt="用DTcms做一个独立博客网站（响应式模板）"
						style="display: block;">
				</span><span class="text">用DTcms做一个独立博客网站（响应式模板）</span><span class="muted"><i
						class="glyphicon glyphicon-time"></i> 2016-11-01 </span><span
					class="muted"><i class="glyphicon glyphicon-eye-open"></i>88</span></a></li>
			<li><a title="用DTcms做一个独立博客网站（响应式模板）" href="#"><span
					class="thumbnail"> <img class="thumb"
						data-original="images/201610181739277776.jpg"
						src="images/201610181739277776.jpg" alt="用DTcms做一个独立博客网站（响应式模板）"
						style="display: block;">
				</span><span class="text">用DTcms做一个独立博客网站（响应式模板）</span><span class="muted"><i
						class="glyphicon glyphicon-time"></i> 2016-11-01 </span><span
					class="muted"><i class="glyphicon glyphicon-eye-open"></i>88</span></a></li>
			<li><a title="用DTcms做一个独立博客网站（响应式模板）" href="#"><span
					class="thumbnail"> <img class="thumb"
						data-original="images/201610181739277776.jpg"
						src="images/201610181739277776.jpg" alt="用DTcms做一个独立博客网站（响应式模板）"
						style="display: block;">
				</span><span class="text">用DTcms做一个独立博客网站（响应式模板）</span><span class="muted"><i
						class="glyphicon glyphicon-time"></i> 2016-11-01 </span><span
					class="muted"><i class="glyphicon glyphicon-eye-open"></i>88</span></a></li>
			<li><a title="用DTcms做一个独立博客网站（响应式模板）" href="#"><span
					class="thumbnail"> <img class="thumb"
						data-original="images/201610181739277776.jpg"
						src="images/201610181739277776.jpg" alt="用DTcms做一个独立博客网站（响应式模板）"
						style="display: block;">
				</span><span class="text">用DTcms做一个独立博客网站（响应式模板）</span><span class="muted"><i
						class="glyphicon glyphicon-time"></i> 2016-11-01 </span><span
					class="muted"><i class="glyphicon glyphicon-eye-open"></i>88</span></a></li>
			<li><a title="用DTcms做一个独立博客网站（响应式模板）" href="#"><span
					class="thumbnail"> <img class="thumb"
						data-original="images/201610181739277776.jpg"
						src="images/201610181739277776.jpg" alt="用DTcms做一个独立博客网站（响应式模板）"
						style="display: block;">
				</span><span class="text">用DTcms做一个独立博客网站（响应式模板）</span><span class="muted"><i
						class="glyphicon glyphicon-time"></i> 2016-11-01 </span><span
					class="muted"><i class="glyphicon glyphicon-eye-open"></i>88</span></a></li>
			<li><a title="用DTcms做一个独立博客网站（响应式模板）" href="#"><span
					class="thumbnail"> <img class="thumb"
						data-original="images/201610181739277776.jpg"
						src="images/201610181739277776.jpg" alt="用DTcms做一个独立博客网站（响应式模板）"
						style="display: block;">
				</span><span class="text">用DTcms做一个独立博客网站（响应式模板）</span><span class="muted"><i
						class="glyphicon glyphicon-time"></i> 2016-11-01 </span><span
					class="muted"><i class="glyphicon glyphicon-eye-open"></i>88</span></a></li>
			<li><a title="用DTcms做一个独立博客网站（响应式模板）" href="#"><span
					class="thumbnail"> <img class="thumb"
						data-original="images/201610181739277776.jpg"
						src="images/201610181739277776.jpg" alt="用DTcms做一个独立博客网站（响应式模板）"
						style="display: block;">
				</span><span class="text">用DTcms做一个独立博客网站（响应式模板）</span><span class="muted"><i
						class="glyphicon glyphicon-time"></i> 2016-11-01 </span><span
					class="muted"><i class="glyphicon glyphicon-eye-open"></i>88</span></a></li>

		</ul>
	</div>
	<div class="widget widget_sentence">

		<a href="#" target="_blank" rel="nofollow" title="MZ-NetBlog主题">
			<img style="width: 100%" src="images/ad.jpg" alt="MZ-NetBlog主题">
		</a>

	</div>
	</aside> </section>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/jquery.ias.js"></script>
	<script src="js/main.js"></script>
</body>
</html>