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
<link rel="stylesheet" type="text/css" href="css/personal.css">
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/nprogress.css">
<link rel="stylesheet" type="text/css" href="css/main.css">
<link rel="stylesheet" type="text/css" href="css/font-awesome.min.css">
<link rel="shortcut icon" href="images/kblog-ico.png">

<script src="js/jquery.js"></script>
<script src="js/main.js"></script>
<script src="js/common.js"></script>
<script src="js/personal.js"></script>
<script src="js/nprogress.js"></script>
<script src="js/jquery.lazyload.min.js"></script>
<script type="text/javascript" src="js/jquery.js"></script>
</head>
<body class="user-select single">
	<jsp:include page="../main/header.jsp"></jsp:include>
	<script src="js/bootstrap.min.js"></script>
	<div class="main clearfix">
      <div class="persional_property">
        <div class="person_info_con"><i class="icon-edit icon-large person-info-edit"></i><a name="M_base"></a>
          <dl class="person-photo">
            <dt>
            	<form id="uploadImg">
            		<c:if test="${sessionScope.user.id eq person.id}">
            			<input type="file" id="uploadHeadImg" name="uploadHeadImg" style="display: none" onchange="uploadImg()"/>
	            		<label for="uploadHeadImg">
	            			<img style="cursor:hand;" id="headImgUp" src="${person.headImg }" class="header">
	            		</label>
            		</c:if>
	            	<c:if test="${sessionScope.user.id != person.id}">
	            		<img id="headImgUp" src="${person.headImg }" class="header">
	            	</c:if>
	            	<span class="edit_person_pic"></span>
            	</form>
            </dt>
            <dd class="focus_num"><b><a href="/my/follow" target="_blank">${fn:length(followUsers)}</a></b>关注</dd>
            <dd class="fans_num"><b><a href="/my/fans" target="_blank">${fn:length(followedUsers)}</a></b>粉丝</dd>
          </dl>
          <dl class="person-info">
            <dt class="person-nick-name">
            	<span>${person.name }</span>  
            	<c:if test="${not empty sessionScope.user}">
	            	<c:if test="${sessionScope.user.id != person.id}">
	            		<span style="margin-left: 20px; float: right;"><a href="#" onclick="return follow('${person.id}')"><i class="fa fa-github"></i> 关注</a></span>
	            	</c:if>
	            	<c:if test="${sessionScope.user.id == person.id}">
	            		<span style="margin-left: 20px; float: right;"><a onclick="return show('#editPasswordModal')"><i class="fa fa-github"></i> 修改密码</a></span>
	            		<span style="margin-left: 20px; float: right;"><a  onclick="return show('#editModal')"><i class="fa fa-github"></i> 编辑资料</a></span>
	            	</c:if>
            	</c:if>
            </dt>
            <dd class="person-detail"> 
           		<span class="info_null">计算机</span><span>|</span><span class="info_null"><c:if test="${empty person.introduction }">未填写职位</c:if>${person.introduction }</span><span>|</span><span class="info_null">${person.name }</span><span>|</span>中国 
            </dd>
            <dd style="margin: 10px 0px"> 
	            <div class="person-status clearfix">
	          </div>
          </dd>
          </dl>
        
        </div>
      </div>
      <div class="persion_section">
        <div class="mod_product">
          <div class="prod_fans">
            
          </div>
        </div>
		<script>
			function choose(detail) {
				$("#blog_detail").removeClass("current_detail");
				$("#collect_detail").removeClass("current_detail");
				$("#focus_detail").removeClass("current_detail");
				$("#fans_detail").removeClass("current_detail");
				$("#" + detail + "_detail").addClass("current_detail");
				
				$("#blog_content").removeClass("current_content");
				$("#collect_content").removeClass("current_content");
				$("#focus_content").removeClass("current_content");
				$("#fans_content").removeClass("current_content");
				$("#" + detail + "_content").addClass("current_content");
			}
		</script>
	  <div class="person_detail_tab">
		  <ul>
			  <li id="blog_detail" data-modal="tab" class="current_detail" onclick="choose('blog')">
				  <c:if test="${(not empty sessionScope.user) && (sessionScope.user.id == person.id)}">我</c:if><c:if test="${(empty sessionScope.user) || (sessionScope.user.id != person.id)}">他</c:if>的博客
			  </li>
			  <li id="collect_detail" data-modal="tab" onclick="choose('collect')">
			  	  <c:if test="${(not empty sessionScope.user) && (sessionScope.user.id == person.id)}">我</c:if><c:if test="${(empty sessionScope.user) || (sessionScope.user.id != person.id)}">他</c:if>的收藏
			  </li>
			  <li id="focus_detail" data-modal="tab" class="lastli" onclick="choose('focus')">
			  	  <c:if test="${(not empty sessionScope.user) && (sessionScope.user.id == person.id)}">我</c:if><c:if test="${(empty sessionScope.user) || (sessionScope.user.id != person.id)}">他</c:if>的关注
			  </li>
			  <li id="fans_detail" data-modal="tab" class="lastli" onclick="choose('fans')">
			 	  <c:if test="${(not empty sessionScope.user) && (sessionScope.user.id == person.id)}">我</c:if><c:if test="${(empty sessionScope.user) || (sessionScope.user.id != person.id)}">他</c:if>的粉丝
			  </li>
		  </ul>
	  </div>
        <div class="aboutMe" style="display: block">
            <div id="blog_content" nodetype="myBlog" nodeindex="my4" data-modal="tab-layer" class="myBlog current_content">
				<div class="mod-my-collect">
                    <div class="silder-wraper">
                    </div>
                    <div class="silder-content">
                        <ul>
                        	<c:forEach var="article" items="${articles}">
                        		<li class="icon-th-list no-mess">
                        			<span><a href="article/${article.id}" target="_blank">${article.title }</a></span>
                        			<span style="float: right">
                        				<span style="margin-right: 20px">${article.readCount}人阅读</span>
                        				<span><fmt:formatDate value="${article.createTime}" pattern="yyyy-MM-dd HH:mm"/> </span>
                        			</span>
                        		</li>
                        	</c:forEach>
                        </ul>
                    </div>
                    
                </div>
            </div>


          <div id="collect_content" nodetype="myCollect" nodeindex="my3" data-modal="tab-layer" class="myCollect">
            <div data-bind="collect" class="mod-my-collect">
			  <div class="silder-wraper">
                <div class="silder" style="display: none;"><i class="active"></i>
                  <div class="wraper">
                    <ul data-bind="menu" class="clearfix">
                      <li data-bind="menuItem"><em></em><span data-bind="menuText" class="J-menu">我的收藏</span></li>
                    
                      <li data-bind="menuItem"><em></em><span data-bind="menuText" class="J-menu">知识图谱</span></li>
                    </ul>
                  </div>
                </div>
              </div>
              <div class="silder-content">
                <ul data-bind="list">
               		<c:forEach var="article" items="${collectArticles}">
                 		<li class="icon-th-list no-mess">
                 			<span><a href="article/${article.id}" target="_blank">${article.title }</a></span>
                 			<span style="float: right">
                 				<span style="margin-right: 20px">${article.readCount}人阅读</span>
                 				<span><fmt:formatDate value="${article.createTime}" pattern="yyyy-MM-dd HH:mm"/> </span>
                 			</span>
                 		</li>
                  	</c:forEach>
                </ul>
                <ul class="J-more">
                	
                </ul>
              </div>
                <a href="#" class="more" style="display: none;">显示更多<i class="icon-angle-down"></i></a>
            </div>
          </div>

          <div id="focus_content" nodetype="myConnection" nodeindex="my2" data-modal="tab-layer" class="myConnection">
            <div class=" clearfix">
           	 <ul id="myConnect1" class="my_connections clearfix">
           	 	 <c:forEach var="user" items="${followUsers }">
           	 	 	<li class="icon-th-list" style="margin: 0; height: auto;float: left;" >
	              		<span><a href="personal/${user.id }" target="_blank"><img style="width: 100px;height: 100px;border-radius: 50%" alt="${user.name }" title="${user.name }" src="${user.headImg }"></a></span>
	             	</li>
           	 	 </c:forEach>
              </ul>
              <!--
              <ul id="myConnect" class="my_connections clearfix">
	              <li class="icon-th-list no-mess" style="margin-bottom: 0; height: auto;">
	              	<span>暂时还没有关注关系</span>
	              </li>
              </ul>
                -->
              <div id="c_more" class="more" style="display: none;"><span class="icon-angle-down"></span><a href="#">显示更多</a></div>
            </div>
          </div>
			<div id="fans_content" nodetype="myConnection" nodeindex="my1" data-modal="tab-layer" class="myConnection">
            <div class=" clearfix">
           	 <ul id="myConnect3" class="my_connections clearfix">
           	 	 <c:forEach var="user" items="${followedUsers }">
           	 	 	<li class="icon-th-list" style="margin: 0; height: auto;float: left;" >
	              		<span><a href="personal/${user.id }" target="_blank"><img style="width: 100px;height: 100px;border-radius: 50%" alt="${user.name }" title="${user.name }" src="${user.headImg }"></a></span>
	             	</li>
           	 	 </c:forEach>
              </ul>
              <!--
              <ul id="myConnect" class="my_connections clearfix">
	              <li class="icon-th-list no-mess" style="margin-bottom: 0; height: auto;">
	              	<span>暂时还没有关注关系</span>
	              </li>
              </ul>-->
              <div id="c_more" class="more" style="display: none;"><span class="icon-angle-down"></span><a href="#">显示更多</a></div>
            </div>
          </div>
        </div>
        
         

        </div>
      </div>
      
      <div class="container">
        <div class="modal fade" id="editPasswordModal"  tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="btn-info modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4>修改密码</h4>
                    </div>

                    <div class="modal-body">
                        <form id="editPassowrdForm" class="form-horizontal" role="form">
                            <div class="form-group">
                                <label for="password" class="col-sm-2 control-label">原密码</label>
                                <div class="col-sm-9">
                                    <input type="password" id="password" name="password" class="form-control well" placeholder="请输入原密码"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="newPassword" class="col-sm-2 control-label">新密码</label>
                                <div class="col-sm-9">
                                    <input type="password" id="newPassword" name="newPassword" class="form-control well" placeholder="请输入新密码"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="renewPassword" class="col-sm-2 control-label">确认密码</label>
                                <div class="col-sm-9">
                                    <input type="password" id="renewPassword" name="renewPassword" class="form-control well" placeholder="请输入新密码"/>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" onclick="editPassword()" class="btn btn-info">确定</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    </div>
                </div>
            </div>
        </div>
	</div>
	
	<div class="container">
        <div class="modal fade" id="editModal"  tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="btn-info modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4>编辑资料</h4>
                    </div>

                    <div class="modal-body">
                        <form id="editForm" class="form-horizontal" role="form">
                            <div class="form-group">
                                <label for="introduction" class="col-sm-2 control-label">职业</label>
                                <div class="col-sm-9">
                                    <input type="text" id="introduction" name="introduction" class="form-control well" placeholder="请输入职业"/>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" onclick="editUser()" class="btn btn-info">确定</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    </div>
                </div>
            </div>
        </div>
	</div>
</body>
</html>