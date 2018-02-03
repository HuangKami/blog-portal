/**
 * 登录界面
 */
function signin() {
	$(".signin")[0].className="signin focus";
	$(".signup")[0].className="signup";
	$(".findBack")[0].className="findBack";
	$(".input_signin")[0].className="input_signin active";
	$(".input_signup")[0].className="input_signup";
	$(".input_findBack")[0].className="input_findBack";
	$(".input_affirm")[0].className="input_affirm";
	$(".input_reset")[0].className="input_reset";
	return false;
}

/**
 * 注册界面
 */
function signup() {
	$(".signup")[0].className="signup focus";
    $(".signin")[0].className="signin";
    $(".findBack")[0].className="findBack";
    $(".input_signup")[0].className="input_signup active";
    $(".input_signin")[0].className="input_signin";
    $(".input_findBack")[0].className="input_findBack";
    $(".input_affirm")[0].className="input_affirm";
	$(".input_reset")[0].className="input_reset";
    return false;
}

/**
 * 找回密码界面
 */
function toFindBack() {
	$(".findBack")[0].className="findBack inline focus";
	$(".signin")[0].className="signin";
	$(".signup")[0].className="signup";
	$(".input_findBack")[0].className="input_findBack active";
	$(".input_signin")[0].className="input_signin";
	$(".input_signup")[0].className="input_signup";
	return false;
}

/**
 * 更新验证码
 */
function changeAuthCode(authCodeType) {
	//random是为了让浏览器不去读缓存
	$("#" + authCodeType + "Image").attr("src","authCode?authCodeType=" + authCodeType + "&random=" + Math.random());
	return false;
}

/**
 * 登录
 */
function login() {
	var name = $("#login_user_name").val();
	var password = $("#login_password").val();
	var authCode = $("#loginAuthCode").val();
	$.ajax( {  
        type : "POST",  
        url : "login/login",  
        data : {
        	"name" :name,
        	"password" : password,
        	"loginAuthCode" : authCode
        },
        dataType: "json",  
        success : function(data) {  
        	if(data != "success") {
        		alert(data);
        	} else {
        		window.location.href = "main";
        	}
        }
    });  
}

/**
 * 检查用户名是否已存在
 */
function checkName() {
	var name = $("#user_name").val();
	if(name == "") {
		return;
	}
	$.ajax( {  
        type : "POST",  
        url : "login/checkName",  
        data : {
        	"name" : name
        },
        dataType: "json",  
        success : function(data) {  
            if(data == false) {
            	var input = document.getElementById("hint_name");
            	input.className = "hint hint_wrong";
            	input.innerHTML = "×用户名已被注册";
            }
        }
    });  
}

/**
 * 检查邮箱是否已存在
 */
function checkEmail() {
	var email = $("#user_email").val();
	if(email == "") {
		return;
	}
	$.ajax( {  
        type : "POST",  
        url : "login/checkEmail",  
        data : {
        	"email" : email
        },
        dataType: "json",  
        success : function(data) {  
        	if(data == false) {
        		var input = document.getElementById("hint_email");
            	input.className = "hint hint_wrong";
            	input.innerHTML = "×邮箱已被注册";
            }
        }
    });  
}

/**
 * 用户注册
 */
function register() {
	var name = $("#user_name").val();
	var email = $("#user_email").val();
	var password = $("#password").val();
	var authCode = $("#registerAuthCode").val();
	loadingActive("login_cont");
	$.ajax( {  
        type : "POST",  
        url : "login/register",  
        data : {
        	"name" :name,
        	"email" : email,
        	"password" : password,
        	"registerAuthCode" : authCode
        },
        dataType: "json",  
        success : function(data) {  
        	loadingUnactive("login_cont");
        	if(data == "success") {
        		alert("注册成功，请前往邮箱激活，若没收到邮件，请在垃圾箱查找");
        		signin();
        	} else {
        		alert(data);
        	}
        },
        error : function() {
        	loadingUnactive("login_cont");
        } 
    });  
}

/**
 * 找回密码
 */
function findBack() {
	var email = $("#forget_user_email").val();
	loadingActive("login_cont");
	$.ajax( {  
        type : "POST",  
        url : "login/findBack",  
        data : {
        	"email" : email
        },
        dataType: "json",  
        success : function(data) { 
        	loadingUnactive("login_cont");
        	if(data == "success") {
        		alert("操作成功，请在邮箱查看验证码，若没收到邮件，请在垃圾箱查找");
        		$(".input_findBack")[0].className="input_findBack";
        		$(".input_affirm")[0].className="input_affirm active";
        	} else {
        		alert(data);
        	}
        },
        error : function() {
        	loadingUnactive("login_cont");
        } 
    });  
}

/**
 * 确认邮箱验证码
 */
function affirmCode() {
	var code = $("#affirmCode").val();
	loadingActive("login_cont");
	$.ajax( {  
        type : "POST",  
        url : "login/affirmCode",  
        data : {
        	"code" : code
        },
        dataType: "json",  
        success : function(data) { 
        	loadingUnactive("login_cont");
        	if(data == "success") {
        		alert("操作成功，请输入新密码重置");
        		$(".input_affirm")[0].className="input_affirm";
        		$(".input_reset")[0].className="input_reset active";
        	} else {
        		alert(data);
        	}
        },
        error : function() {
        	loadingUnactive("login_cont");
        }   
    }); 
}

/**
 * 重置密码
 */
function resetPassword() {
	var password = $("#reset_password").val();
	loadingActive("login_cont");
	$.ajax( {  
        type : "POST",  
        url : "login/resetPassword",  
        data : {
        	"password" : password
        },
        dataType: "json",  
        success : function(data) { 
        	loadingUnactive("login_cont");
        	if(data == "success") {
        		alert("重置密码成功");
        		signin();
        	} else {
        		alert(data);
        	}
        },
        error : function() {
        	loadingUnactive("login_cont");
        }  
    }); 
}