function reply(articleId) {
	var content = $("iframe").contents().find("body").html();
	if(content == "") {
		alert("请输入内容");
		return;
	}
	$.ajax( {  
        type : "POST",  
        url : "reply",  
        data : {
        	"articleId" :articleId,
        	"content" : content
        },
        dataType: "json",  
        success : function(data) {  
    		$("#comment_list").append(
    	        	  "<li class='comment-content'>"
    				+ " <div class='comment-main'>"
    				+ " <p>"
    				+ "	<a href='#' rel='nofollow' target='_blank'>" + data.user.name + "</a>"
    				+ "	<span class='time'>"
    				+ 	new Date(data.createTime).toLocaleString()
    				+ "	</span><br>"
    				+ 	data.content
    				+ " </p>"
    				+ " </div>"
    				+ " </li>");
    		$("iframe").contents().find("body").html("");
    		alert("回复成功");
        },
        error : function() {
        	alert("请先登录");
        	window.location.href = "login";
        }
    });  
}

