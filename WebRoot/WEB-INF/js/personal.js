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

function show(id) {
	$("" + id).modal();
	return false;
}

function editUser() {
	$.ajax({
        type: "POST",
        url: "personal/editUser",
        data:$("#editForm").serialize(),
        error: function(request) {
            alert("未登录");
        },
        success: function(data) {
          if(data == "success"){
        	  alert("修改成功");
              $("#editModal").modal("hide");
          }else{
              alert(data);
          }
        }
    });
}

function editPassword() {
	var password = $("#password").val();
	if(password == "") {
		alert("原密码不能为空");
		return;
	}
	var newPassword = $("#newPassword").val();
	if(newPassword == "") {
		alert("新密码不能为空");
		return;
	}
	var renewPassword = $("#renewPassword").val();
	if(renewPassword == "") {
		alert("确认密码不能为空");
		return;
	}
	if(newPassword != renewPassword) {
		alert("两次密码不一致");
		return;
	}
	$.ajax({
        type: "POST",
        url: "personal/editPassword",
        data: {
        	"password" : password,
        	"newPassword" : newPassword
        },
        error: function(request) {
            alert("未登录");
        },
        success: function(data) {
          if(data == "success"){
        	  alert("修改成功");
              $("#editPasswordModal").modal("hide");
          }else{
              alert(data);
          }
        }
    });
}