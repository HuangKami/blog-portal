<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>  
<header class="header"> 
	<nav class="navbar navbar-default" id="navbar">
	<div class="container">
		<div class="header-topbar hidden-xs link-border" style="margin-bottom: 25px">
			<ul id="login" class="site-nav topmenu">
				<c:if test="${empty user}">
					<li><a href="login"><i class="fa fa-sign-in"></i> 登录</a></li>
					<li><a href="login" rel="nofollow"><i class="fa fa-sign-in"></i> 注册</a></li>
				</c:if>
				<c:if test="${not empty user}">
					<li><a href="#"><i class="fa fa-user"></i> ${user.name}</a></li>
					<li><a href="#" onclick="return logout();"><i class="fa fa-sign-out"></i> 注销</a></li>
				</c:if>
			</ul>
			<ul id="logout" class="site-nav topmenu" style="display: none;">
				<li><a href="login"><i class="fa fa-sign-in"></i> 登录</a></li>
				<li><a href="login" rel="nofollow"><i class="fa fa-sign-in"></i> 注册</a></li>
			</ul>
		</div>
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#header-navbar"
				aria-expanded="false">
				<span class="sr-only"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span>
			</button>
			<h1 class="logo hvr-bounce-in">
				<a href="#" title="KBlog"><img
					src="images/kblog.png" alt="KBlog"></a>
			</h1>
		</div>
		<div class="collapse navbar-collapse" id="header-navbar">
			<form class="navbar-form visible-xs" action="/Search" method="post">
				<div class="input-group">
					<input type="text" name="keyword" class="form-control"
						placeholder="请输入关键字" maxlength="20" autocomplete="off"> <span
						class="input-group-btn">
						<button class="btn btn-default btn-search" name="search"
							type="submit">搜索</button>
					</span>
				</div>
			</form>
			<ul class="nav navbar-nav navbar-right" style="margin-top: 20px; ">
				<li><a href="main">首页</a></li>
				<li><a href="list.html">列表页</a></li>
				<li><a href="show.html">详细页</a></li>
				<li><a href="404.html">404</a></li>
				<li><a href="#">MZ-NetBolg主题</a></li>
				<li><a href="#">IT技术笔记</a></li>
				<li><a href="#">源码分享</a></li>
				<li><a href="#">靠谱网赚</a></li>
				<li><a href="article/articlePage">写博客</a></li>
			</ul>
		</div>
	</div>
	</nav> 
	</header>