<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>  
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
</head>
<body class="user-select">
	<jsp:include page="header.jsp"></jsp:include>
	<section class="container">
	<div class="content-wrap">
		<div class="content">
			<div class="excerpt-minic excerpt-minic-index">
				<h2>
					<span class="red">【推荐】</span><a target="_blank" href="#"
						title="用DTcms做一个独立博客网站（响应式模板）">用DTcms做一个独立博客网站（响应式模板）</a>
				</h2>
				<p class="note">用DTcms做一个独立博客网站（响应式模板），采用DTcms
					V4.0正式版（MSSQL）。开发环境：SQL2008R2+VS2010。DTcms
					V4.0正式版功能修复和优化：1、favicon.ico图标后台上传。（解决要换图标时要连FTP或者开服务器的麻烦）</p>
			</div>
			<div class="title">
				<h3>最新文章</h3>
				<div class="more">
					<a href="#" title="MZ-NetBlog主题">MZ-NetBlog主题</a> <a href="#"
						title="IT技术笔记">IT技术笔记</a> <a href="#" title="源码分享">源码分享</a> <a
						href="#" title="靠谱网赚">靠谱网赚</a> <a href="#" title="资讯分享">资讯分享</a>
				</div>
			</div>
			<c:forEach var="article" items="${latestArticles}">
				<div class="excerpt" style=""> 
					<header>
						<a class="cat" href="#">${article.topic }<i></i></a>
						<h2>
							<a href="article/${article.id}" target="_blank">${article.title }</a>
						</h2>
					</header>
					<p class="meta">
						<time class="time">
							<i class="glyphicon glyphicon-time"></i>
							<fmt:formatDate value="${article.createTime}" pattern="yyyy-MM-dd HH:mm"/> 
						</time>
						<span class="views">
							<i class="fa fa-user"></i>
							${article.user.name}
						</span>
						<span class="views">
							<i class="glyphicon glyphicon-eye-open"></i>
							${article.readCount}
						</span> 
						<span class="views">
							<i class="glyphicon glyphicon-comment"></i> ${article.commentCount}
						</span>
					</p>
					<p class="note">${article.content}</p>
				</div>
			</c:forEach>
			<div id="loader" style="margin-top: 10px"><center><a href="#" class="button button-glow button-border button-rounded button-primary" onclick="return viewMore()">查看更多</a></center></div>
		</div>
	</div>
	<aside class="sidebar">
	<div class="fixed">
		<div class="widget widget-tabs">
			<ul class="nav nav-tabs" role="tablist">
				<li role="presentation" class="active"><a href="#notice"
					aria-controls="notice" role="tab" data-toggle="tab">统计信息</a></li>
				<li role="presentation"><a href="#contact"
					aria-controls="contact" role="tab" data-toggle="tab">联系站长</a></li>
			</ul>
			<div class="tab-content">
				<div role="tabpanel" class="tab-pane contact active" id="notice">
				</div>
				<div role="tabpanel" class="tab-pane contact" id="contact">
					<h2>
						QQ: <a href="" target="_blank" rel="nofollow"
							data-toggle="tooltip" data-placement="bottom" title=""
							data-original-title="243313689" onclick="return false;">243313689</a>
					</h2>
					<h2>
						Email: <a href="#" target="_blank" data-toggle="tooltip"
							rel="nofollow" data-placement="bottom" title=""
							data-original-title="243313689@qq.com" onclick="return false;">243313689@qq.com</a>
					</h2>
				</div>
			</div>
		</div>
		<div class="widget widget_search">
			<form class="navbar-form" action="article/search" method="post">
				<div class="input-group">
					<input type="text" name="keyword" class="form-control" size="35"
						placeholder="请输入关键字" maxlength="15" autocomplete="off">
						 <span class="input-group-btn">
						<button class="btn btn-default btn-search" name="search" type="submit">搜索</button>
					</span>
				</div>
			</form>
		</div>
	</div>
	<div class="widget widget_hot">
		<h3>最热文章</h3>
		<ul>
			<c:forEach var="article" items="${hotestArticles}">
				<li>
					<a href="article/${article.id}" target="_blank">
						<span class="text">
							<span class="cat">${article.topic }<i></i></span>
							<span class="">${article.title }</span>
						</span>
						<span class="muted">
							<i class="glyphicon glyphicon-time"></i> 
							<fmt:formatDate value="${article.createTime}" pattern="yyyy-MM-dd HH:mm"/> 
						</span>
						<span class="muted">
							<i class="fa fa-user"></i>
							${article.user.name}
						</span>
						<span class="muted">
							<i class="glyphicon glyphicon-eye-open"></i>
							${article.readCount}
						</span> 
						<span class="muted">
							<i class="glyphicon glyphicon-comment"></i> ${article.commentCount}
						</span>
						<span class="text">${article.content}</span>
					</a>
				</li>
			</c:forEach>
		</ul>
	</div>
	</aside> </section>

	<script src="js/bootstrap.min.js"></script>
	<script src="js/jquery.ias.js"></script>
	<script src="js/main.js"></script>
</body>
</html>