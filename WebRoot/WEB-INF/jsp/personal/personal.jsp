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
	
	<div class="main clearfix">
      <div class="persional_property">
        <div class="person_info_con"><i class="icon-edit icon-large person-info-edit"></i><a name="M_base"></a>
          <dl class="person-photo">
            <dt>
            	<form id="uploadImg">
            		<c:if test="${sessionScope.user.id eq person.id}">
            			<input type="file" id="uploadHeadImg" name="uploadHeadImg" style="display: none" onchange="uploadImg()"/>
	            		<label for="uploadHeadImg">
	            			<img id="headImgUp" src="${person.headImg }" class="header">
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
            	</c:if>
            </dt>
            <dd class="person-detail"> 
            		<span class="info_null">未填写行业</span><span>|</span><span class="info_null">未填写职位</span><span>|</span><span class="info_null">未填写姓名</span><span>|</span>中国<span>|</span><span class="info_null">未填写性别</span><span>|</span><span class="info_null">未填写生日</span> </dd>
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
            <div style="left: 34px" nodetype="box_1" id="box_1" class="box box_1">
              <div nodetype="ie-fans" class="IE-fans"></div>
              <div nodetype="fanInfo" class="info level orange">V1</div><a href="http://blog.csdn.net/huangkami" target="_blank" nodetype="fanText" class="box_title">博客</a>
              <div class="wraper_right fanOrange">
                <div nodetype="moveFan-right" class="fan"></div>
              </div>
              <div class="wraper_left fanOrange">
                <div nodetype="moveFan-left" class="fan"></div>
              </div>
            </div>
            <div style="left: 209px" nodetype="box_2" id="box_2" class="box box_2">
              <div nodetype="ie-fans" class="IE-fans"></div>
              <div nodetype="fanInfo" class="info level green">V1</div><a href="https://download.csdn.net/my" target="_blank" nodetype="fanText" class="box_title">下载</a>
              <div class="wraper_right fanGreen">
                <div nodetype="moveFan-right" class="fan"></div>
              </div>
              <div class="wraper_left fanGreen">
                <div nodetype="moveFan-left" class="fan"></div>
              </div>
            </div>
            <div style="left: 384px" nodetype="box_3" id="box_3" class="box box_3">
              <div nodetype="ie-fans" class="IE-fans"></div>
              <div nodetype="fanInfo" class="info level blue">V1</div><a href="https://bbs.csdn.net" target="_blank" nodetype="fanText" class="box_title">论坛</a>
              <div class="wraper_right fanBlue">
                <div nodetype="moveFan-right" class="fan"></div>
              </div>
              <div class="wraper_left fanBlue">
                <div nodetype="moveFan-left" class="fan"></div>
              </div>
            </div>
            <div style="left: 559px" nodetype="box_4" id="box_4" class="box box_4">
              <div nodetype="ie-fans" class="IE-fans"></div>
              <div nodetype="fanInfo" class="info level blueDark">V1</div><a href="https://code.csdn.net/dashboard/index" target="_blank" nodetype="fanText" class="box_title">CODE</a>
              <div class="wraper_right fanBlueDark">
                <div nodetype="moveFan-right" class="fan"></div>
              </div>
              <div class="wraper_left fanBlueDark">
                <div nodetype="moveFan-left" class="fan"></div>
              </div>
            </div>
            <div style="left: 734px" nodetype="box_5" id="box_5" class="box box_5">
              <div nodetype="ie-fans" class="IE-fans"></div>
              <div nodetype="fanInfo" class="info level greenDark">V1</div><a href="http://student.csdn.net/" target="_blank" nodetype="fanText" class="box_title">高校</a>
              <div class="wraper_right fanGreenDark">
                <div nodetype="moveFan-right" class="fan"></div>
              </div>
              <div class="wraper_left fanGreenDark">
                <div nodetype="moveFan-left" class="fan"></div>
              </div>
            </div>
          </div>
          <div class="money">
            <ul class="clearfix">
              <li>C币<a href="#" target="_blank" class="cb">0</a></li>
              <li>勋章<span class="medals"></span></li>
            </ul>
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
			  <li id="blog_detail" data-modal="tab" class="current_detail" onclick="choose('blog')">我的博客</li>
			  <li id="collect_detail" data-modal="tab" onclick="choose('collect')">我的收藏</li>
			  <li id="focus_detail" data-modal="tab" class="lastli" onclick="choose('focus')">我的关注</li>
			  <li id="fans_detail" data-modal="tab" class="lastli" onclick="choose('fans')">我的粉丝</li>
		  </ul>
	  </div>
        <div class="aboutMe" style="display: block">
            <div id="blog_content" nodetype="myBlog" nodeindex="my4" data-modal="tab-layer" class="myBlog current_content">
				<div class="mod-my-collect">
                    <div class="silder-wraper">
                        <div class="operate clearfix"><a style="font-size:16px;color: #e9733a;" href="https://my.csdn.net/my/favorite" target="_blank">按标签查看</a></div>
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
                <div class="operate clearfix"><a style="font-size:16px;color: #e9733a;" href="https://my.csdn.net/my/favorite" target="_blank">按标签查看</a></div>
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
            <div class="connection_list_con clearfix">
           	 <ul id="myConnect1" class="my_connections clearfix">
           	 	 <c:forEach var="user" items="${followUsers }">
           	 	 	<li class="icon-th-list" style="margin-bottom: 0; height: auto;">
	              		<span><a href="personal/${user.id }" target="_blank"><img alt="${user.name }" title="${user.name }" src="${user.headImg }"></a></span>
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
            <div class="connection_list_con clearfix">
           	 <ul id="myConnect3" class="my_connections clearfix">
           	 	 <c:forEach var="user" items="${followedUsers }">
           	 	 	<li class="icon-th-list" style="margin-bottom: 0; height: auto;">
	              		<span><a href="personal/${user.id }" target="_blank"><img alt="${user.name }" title="${user.name }" src="${user.headImg }"></a></span>
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
</body>
</html>