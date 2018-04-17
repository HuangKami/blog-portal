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

function collectArticle(articleId) {
	$.ajax( {  
        type : "POST",  
        url : "article/collect", 
        data : {
        	"articleId" : articleId
        },
        dataType: "json",  
        success : function(data) {  
        	if(data == "error") {
        		alert("您已收藏该文章");
        	} else {
				alert("收藏成功");
			}
        	
        },
        error : function() {
        	alert("请先登录");
        	window.location.href = "login";
        }
    });  
	return false;
}

function doPost(to, p) {
	var myForm = document.createElement("form"); 
	myForm.method = "post"; 
	myForm.action = to; 
	for (var i in p){ 
		var myInput = document.createElement("input"); 
		myInput.setAttribute("name", i); // 为input对象设置name 
		myInput.setAttribute("value", p[i]); // 为input对象设置value 
		myForm.appendChild(myInput); 
	} 
	document.body.appendChild(myForm); 
	myForm.submit(); 
	document.body.removeChild(myForm); // 提交后移除创建的form 
}