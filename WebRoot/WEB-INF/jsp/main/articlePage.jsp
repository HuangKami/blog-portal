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
<script src="js/article.js"></script>
</head>
<body class="user-select">
	<jsp:include page="header.jsp"></jsp:include>
	
	<div style="width: 80%;height: 800px;margin: auto auto; background-color: white; padding: 2%">
		<jsp:include page="../common/select.jsp"></jsp:include>
		<div style="float: right;">
			<input id="title" class="wrapper-dropdown" type="text" placeholder="输入文章标题" style="outline:none;-webkit-appearance:none;" />
			<input class="button button-article" type="button" onclick="publishArticle()" value="发表" /> 
		</div>
		<jsp:include page="../common/editor.jsp">
			<jsp:param name="editorHeight" value="84%"/> 
		</jsp:include>
	</div>

	<script src="js/bootstrap.min.js"></script>
</body>
</html>