function follow(userId) {
	$.ajax( {  
        type : "POST",  
        url : "personal/follow",  
        data : {
        	beFollowed : userId
        },
        dataType: "json",  
        success : function(data) {  
        	if(data == "error") {
        		alert("您已关注");
        	} else {
				alert("关注成功");
			}
        	
        },
        error : function() {
        	alert("请先登录");
        	window.location.href = "login";
        }
    });  
	return false;
}


function uploadImg() {
	var formData = new FormData($("#uploadImg")[0]);  
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
      		 $("#headImgUp").attr("src", data);
      	  }
        },  
        error: function (data) {  
            alert("上传头像失败");  
        }  
   });  
};