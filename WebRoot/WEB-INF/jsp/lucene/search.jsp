<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>  
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
	<jsp:include page="../main/header.jsp"></jsp:include>
	<div class="widget widget_search">
		<form class="navbar-form" action="article/search" method="post">
			<div class="input-group"  style=" width:56%;">
				<input type="text" name="keyword" class="form-control" size="85" style="margin-left: 42px"
					placeholder="请输入关键字" maxlength="15" autocomplete="off" value="${keyword }">
					 <span class="input-group-btn">
					<button class="btn btn-default btn-search" name="search" type="submit">搜索</button>
				</span>
			</div>
		</form>
	</div>
	<div style="width:700px;display: inline-block; margin-left: 40px;">
		<div style="margin: 10px 20px;">KBlog为您找到相关结果约${fn:length(searchList)}个</div>
		<div class="widget widget_hot" style="width: 100%; margin-left: 20px;">
			<c:forEach var="article" items="${searchList}">
				<div class="excerpt" style=""> 
						<header>
							<a class="cat" href="#">${article.topic }<i></i></a>
							<h2>
								<a href="article/${article.id}" target="_blank">${article.title }</a>
							</h2>
						</header>
						<p class="note">${article.content}</p>
					</div>
			</c:forEach>
		</div>
	</div>
	<div class="widget widget_hot" style="width: 380px;display: inline-block;float: right;margin-right: 80px;margin-top: 38px;">
		<div style="height: 30px;line-height:30px;background-color: #3399CC;padding-left: 5px;margin-bottom: 5px;color:white;">阅读排行榜</div>
		<ul>
			<c:forEach var="article" items="${hottest}">
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
						<span class="muted">
							<i class="glyphicon glyphicon-comment"></i> ${article.commentCount}
						</span>
						<span class="text">${article.content}</span>
					</a>
				</li>
			</c:forEach>
		</ul>
	</div>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/jquery.ias.js"></script>
	<script src="js/main.js"></script>
</body>
</html>