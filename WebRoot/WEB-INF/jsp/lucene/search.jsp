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
	<jsp:include page="../main/header.jsp"></jsp:include>
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
	<div style="width: 30%">
		<c:forEach var="article" items="${searchList}">
			<div><a href="article/${article.id}" target="_blank">${article.title}</a></div>
			<div>${article.content}</div>
		</c:forEach>
	</div>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/jquery.ias.js"></script>
	<script src="js/main.js"></script>
</body>
</html>