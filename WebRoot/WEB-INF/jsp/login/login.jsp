<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
<script type="text/javascript" src="js/common.js"></script>
<link rel="stylesheet" type="text/css" href="css/common.css">
<link rel="stylesheet" type="text/css" href="css/login.css">
</head>

<body>
	<div class="login_cont">
		<div class="login_nav">
			<div class="nav_slider">
				<a href="#" class="signin focus" onclick="return signin()">登录</a> 
				<a href="#" class="signup" onclick="return signup()">注册</a>
				<a href="#" class="findBack">重置密码</a>
			</div>
		</div>
		
		<!-- 登录 -->
		<div class="input_signin active">
			<input class="input" id="login_user_name" type="text" name="name" aria-label="用户名" placeholder="用户名或邮箱" />
			<div class="hint">请输入用户名或邮箱账号</div>
			<input class="input" id="login_password" type="password" name="password" aria-label="密码" placeholder="密码（不少于 6 位）" />
			<div class="hint">请输入密码</div>
			<input class="input" id="loginAuthCode" style="width: 220px;" type="text" name="loginAuthCode" aria-label="验证码" placeholder="验证码" />
			<a href="#" onclick="return changeAuthCode('loginAuthCode')"><img src="authCode?authCodeType=loginAuthCode" style="float: right;" id="loginAuthCodeImage" name="authCode" title="图片看不清？点击重新得到验证码" style="cursor: pointer;" /></a> 
			<div class="hint">请输入验证码</div>
			<input type="submit" id="login" class="button" value="登录" />
			<div class="forget">
				<a href="#" onclick="return toFindBack()">忘记密码？</a>
			</div>
		</div>
		
		<!-- 注册 -->
		<div class="input_signup">
			<input class="input" id="user_name" type="text" aria-label="用户名（包含字母／数字／下划线" placeholder="用户名" onblur="checkName()" />
			<div id="hint_name" class="hint">请输入用户名</div>
			<input class="input" id="user_email" type="text" aria-label="邮箱" placeholder="邮箱" onblur="checkEmail()" />
			<div id="hint_email" class="hint">请输入邮箱</div>
			<input class="input" id="password" type="password" aria-label="密码" placeholder="密码（不少于 6 位）" />
			<div class="hint">请输入密码</div>
			<input class="input" id="repassword" type="password" aria-label="密码" placeholder="确认密码" />
			<div class="hint">请再次输入密码</div>
			<input class="input" id="registerAuthCode" style="width: 220px;" type="text" aria-label="验证码" placeholder="验证码" />
			<a href="#" onclick="return changeAuthCode('registerAuthCode')"><img src="authCode?authCodeType=registerAuthCode" style="float: right" id="registerAuthCodeImage" title="图片看不清？点击重新得到验证码" style="cursor: pointer;" /></a> 
			<div class="hint">请输入验证码</div>
			<input type="submit" class="button" id="register" name="register" value="注册"/>
		</div>
		
		<!-- 找回密码 -->
		<div class="input_findBack">
			<input class="input" id="forget_user_email" type="text" aria-label="邮箱" placeholder="输入邮箱重置密码" />
			<div class="hint">请输入邮箱</div>
			<input class="input" id="findBackAuthCode" style="width: 220px;" type="text" aria-label="验证码" placeholder="验证码" />
			<a href="#" onclick="return changeAuthCode('findBackAuthCode')"><img src="authCode?authCodeType=findBackAuthCode" style="float: right" id="findBackAuthCodeImage" title="图片看不清？点击重新得到验证码" style="cursor: pointer;" /></a> 
			<div class="hint">请输入验证码</div>
			<input type="submit" class="button" id="findBack" name="button" value="获取验证码" />
		</div>
		
		<!-- 确认邮箱验证码 -->
		<div class="input_affirm">
			<input class="input" id="affirmCode" type="text" aria-label="邮箱验证码" placeholder="输入邮箱的验证码" />
			<div class="hint">输入邮箱的验证码</div>
			<input type="submit" id="affirm" class="button" id="affirm" name="button" value="确认" />
		</div>
		
		<!-- 重置密码 -->
		<div class="input_reset">
			<input class="input" id="reset_password" type="password" aria-label="密码" placeholder="密码（不少于 6 位）" />
			<div class="hint">请输入新密码</div>
			<input class="input" id="reset_repassword" type="password" aria-label="密码" placeholder="确认密码" />
			<div class="hint">请再次输入密码</div>
			<input type="submit" id="resetPassword" class="button" id="reset" name="button" value="重置密码" />
		</div>
	</div>
	<jsp:include page="../common/loading.jsp"></jsp:include>
</body>

<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/login.js"></script>
<script type="text/javascript" src="js/form.js"></script>
<script type="text/javascript">
	var error = "${requestScope.error}";
	if(error != "") {
		alert(error);
	}
	
	var message = "${message}";
	if(message != "") {
		alert(message);
	}
</script>
</html>
