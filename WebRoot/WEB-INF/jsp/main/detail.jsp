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
<link rel="stylesheet" type="text/css" href="css/commons.css">
<link rel="shortcut icon" href="images/kblog-ico.png">
<style type="text/css">
.line-limit-length {
overflow: hidden;
text-overflow: ellipsis;
white-space: nowrap; //文本不换行，这样超出一行的部分被截取，显示...
}
</style>

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
					<a href="personal/${detailArticle.user.id }" target="_blank"><i class="fa fa-user"></i> ${detailArticle.user.name}</a>
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
				<c:if test="${(sessionScope.user.id eq detailArticle.user.id) || (sessionScope.user.admin == true)}">
					<span class="item article-meta-comment" data-toggle="tooltip" data-placement="bottom">
						<i class="fa fa-remove"></i> <a href="#" onclick="return deleteArticle('${detailArticle.id}')">删除</a>
					</span>
				</c:if>
				<c:if test="${not empty sessionScope.user}">
					<c:if test="${sessionScope.user.id != detailArticle.user.id }">
						<span class="item article-meta-comment" data-toggle="tooltip" data-placement="bottom">
							<i class="fa fa-star"></i> <a href="#" onclick="return collectArticle('${detailArticle.id}')">收藏</a>
						</span>
					</c:if>
				</c:if>
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

			</article>
			<br />
			<br />
			<div class="relates">
				<div class="title">
					<h3>相关推荐</h3>
				</div>
				<ul>
					<c:forEach var="article" items="${recommendArticles }">
						<li><a href="article/${article.id }" title="">${article.title } - ${article.topic }</a></li>
					</c:forEach>
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
	<div class="content-wrap">
		<div class="widget" style="padding-bottom: 10px;">
			<div style="height: 30px;line-height:30px;background-color: #3399CC;padding-left: 5px;margin-bottom: 5px;"><a style="color: white;" href="personal/${detailArticle.user.id }" target="_blank">个人资料</a></div>
			<div style="padding: 5px 8px 0;">
				<div style="float: left;">
					<a href="personal/${detailArticle.user.id }" target="_blank"><img style="width: 80px;height: 80px;border-radius:50%;margin-right: 10px;" src="${detailArticle.user.headImg }" /></a>
				</div>
				<div style="margin-top:10px;color: #5B5B5B;font-size: 16px;font-weight: bold;float: right">
					<div><a style="color: #5B5B5B;" target="_blank" href="personal/${detailArticle.user.id }"> ${detailArticle.user.name} - ${detailArticle.user.introduction }</a></div>
					<div style="margin-top: 10px;">
						<span style="margin-right: 15px;">帖子(${count.articleCount })</span>
						<span style="margin-right: 15px">关注(${count.followCount})</span>
						<span style="margin-right: 20px">粉丝(${count.fansCount})</span>
					</div>
				</div>
			</div>
		</div>
		
		<div class="widget">
			<div style="height: 30px;line-height:30px;background-color: #3399CC;padding-left: 5px;margin-bottom: 5px;color:white;">帖子分类</div>
			<ul>
				<c:forEach var="topic" items="${topics }">
					<li style="margin-bottom: 10px;"><a href="javascript:doPost('article/search', {'keyword':'${topic.topic }'})" target="_blank" style="color: #5B5B5B;font-weight: bold;font-size: 16px;margin-right: 20px">【${topic.topic }】</a>  <span style="float: right;">【${topic.total }】</span> </li>
				</c:forEach>
			</ul>
		</div>
		<div class="widget widget_hot">
			<div style="height: 30px;line-height:30px;background-color: #3399CC;padding-left: 5px;margin-bottom: 5px;color:white;">阅读排行榜</div>
			<ul>
			<c:forEach var="article" items="${reads}">
				<li>
					<a href="article/${article.id}">
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
						<span class="text">${article.content}</span>
					</a>
				</li>
			</c:forEach>
		</ul>
	</div>
	</div>
	</aside> </section>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/jquery.ias.js"></script>
	<script src="js/main.js"></script>
</body>
</html>