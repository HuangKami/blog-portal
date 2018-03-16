function publishArticle() {
	var topic = $("#topic").text();
	var title = $("#title").val();
	var content = $("iframe").contents().find("body").html();
	if(topic == "文章类型") {
		alert("请选择文章类型");
		return;
	}
	
	if(title == "") {
		alert("请输入文章标题");
		return;
	}
	
	if(content == "") {
		alert("请输入内容");
		return;
	}
	$.ajax( {  
        type : "POST",  
        url : "article/publishArticle",  
        data : {
        	"topic" :topic,
        	"title" : title,
        	"content" : content
        },
        dataType: "json",  
        success : function(data) {  
        	if(data == "发表失败，请稍后重试") {
        		alert(data);
        	} else {
				window.location.href = "article/" + data;
			}
        	
        },
        error : function() {
        	alert("请先登录");
        	window.location.href = "login";
        }
    });  
}

function deleteArticle(articleId) {
	if(!confirm("确认删除")) {
		return;
	}
	
	$.ajax( {  
        type : "POST",  
        url : "article/delete/" + articleId,  
        dataType: "json",  
        success : function(data) {  
        	if(data == "error") {
        		alert("删除失败，请稍后重试");
        	} else {
				window.location.href = "main";
			}
        	
        },
        error : function() {
        	alert("请先登录");
        	window.location.href = "login";
        }
    });  
	return false;
}