<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>  
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
<link rel="stylesheet" type="text/css" href="css/common.css">
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/jquery.js"></script>
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
</head>

<body>
	<form id="upload">
		<p><input type="file" name="file"/></p>  
      	<input type="button" value="上传" onclick="doUpload()" />  
	</form>
	
	<div id="head" style="display: none">
		<img id="headImg" src="">
	</div>
	
	<jsp:include page="WEB-INF/jsp/common/loading.jsp"></jsp:include>
</body>

<script type="text/javascript">
	function doUpload() {
		var formData = new FormData($("#upload")[0]);  
		loadingActiveById("upload");
	     $.ajax({  
	          url: 'upload/head' ,  
	          type: 'POST',  
	          data: formData,  
	          cache: false,  
	          contentType: false,  
	          processData: false,  
	          success: function (data) {  
	        	  if(data == "error") {
	        		  alert("上传头像失败");
	        	  } else {
	        		 $("#head").attr("style", "");
	        		 $("#headImg").attr("src", data);
	        	  }
	        	  loadingUnactiveById("upload");
	          },  
	          error: function (data) {  
	              alert("上传头像失败");  
	              loadingUnactiveById("upload");
	          }  
	     });  
	}
</script>
</html>
