$('body').show();
$('.version').text(NProgress.version);
NProgress.start();
setTimeout(function() {
	NProgress.done();
	$('.fade').removeClass('out')
}, 1000);
(function() {
	$('img').attr('draggable', 'false');
	$('a').attr('draggable', 'false')
})();

function setCookie(name, value, time) {
	var strsec = getsec(time);
	var exp = new Date();
	exp.setTime(exp.getTime() + strsec * 1);
	document.cookie = name + "=" + escape(value) + ";expires=" + exp.toGMTString()
}
function getsec(str) {
	var str1 = str.substring(1, str.length) * 1;
	var str2 = str.substring(0, 1);
	if (str2 == "s") {
		return str1 * 1000
	} else if (str2 == "h") {
		return str1 * 60 * 60 * 1000
	} else if (str2 == "d") {
		return str1 * 24 * 60 * 60 * 1000
	}
}
function getCookie(name) {
	var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
	if (arr = document.cookie.match(reg)) {
		return unescape(arr[2])
	} else {
		return null
	}
}
$.fn.navSmartFloat = function() {
	var position = function(element) {
			var top = element.position().top,
				pos = element.css("position");
			$(window).scroll(function() {
				var scrolls = $(this).scrollTop();
				if (scrolls > top) {
					$('.header-topbar').fadeOut(0);
					if (window.XMLHttpRequest) {
						element.css({
							position: "fixed",
							top: 0
						}).addClass("shadow")
					} else {
						element.css({
							top: scrolls
						})
					}
				} else {
					$('.header-topbar').fadeIn(500);
					element.css({
						position: pos,
						top: top
					}).removeClass("shadow")
				}
			})
		};
	return $(this).each(function() {
		position($(this))
	})
};
$("#navbar").navSmartFloat();
$('[data-toggle="tooltip"]').tooltip();
jQuery.ias({
	history: false,
	container: '.content',
	item: '.excerpt',
	pagination: '.pagination',
	next: '.next-page a',
	trigger: '查看更多',
	loader: '<div class="pagination-loading"><img src="images/loading.gif" /></div>',
	triggerPageThreshold: 5,
	onRenderComplete: function() {
		$('.excerpt .thumb').lazyload({
			placeholder: '../images/occupying.png',
			threshold: 400
		});
		$('.excerpt img').attr('draggable', 'false');
		$('.excerpt a').attr('draggable', 'false')
	}
});
$(window).scroll(function() {
	var sidebar = $('.sidebar');
	var sidebarHeight = sidebar.height();
	var windowScrollTop = $(window).scrollTop();
	if (windowScrollTop > sidebarHeight - 60 && sidebar.length) {
		$('.fixed').css({
			'position': 'fixed',
			'top': '70px',
			'width': '360px'
		})
	} else {
		$('.fixed').removeAttr("style")
	}
});

document.onkeydown = function(event) {
	var e = event || window.event || arguments.callee.caller.arguments[0];
	if (e.keyCode === 67 || e.keyCode === 86 || e.keyCode === 13) return true;
	if ((e.keyCode === 123) || (e.ctrlKey) || (e.ctrlKey) && (e.keyCode === 85)) {
		return false
	}
};

function SiteSearch(send_url, divTgs) {
	var str = $.trim($(divTgs).val());
	if (str.length > 0 && str != "请输入关键字") {
		str = str.replace(/\s+/g, "");
		str = str.replace(/[\ |\~|\`|\!|\@|\#|\$|\%|\^|\&|\*|\(|\)|\-|\_|\+|\=|\||\\|\[|\]|\{|\}|\;|\:|\"|\'|\,|\<|\.|\>|\/|\?|\，|\。|\：|\；|\·|\~|\！|\、|\《|\》|\‘|\“|\”|\【|\】|\?{|\}|\-|\=|\——|\+|\’|\—|\？]/g, "");
		str = str.replace(/<[^>]*>|/g, "");
		window.location.href = send_url + "?keyword=" + encodeURI(str)
	}
	return false
}

/**
 * 注销
 */
function logout() {
	$.ajax( {  
        type : "POST",  
        url : "login/logout",  
        success : function(data) {  
        	$("#login").hide();
        	$("#logout").show();
        },
        error : function(data) {
        }
    });  
	return false;
}

/**
 * 分页查询
 */
var pageNow = 1;
function viewMore() {  
	$.ajax( {  
        type : "POST",  
        url : "article/latestArticles/" + (pageNow + 1),  
        dataType: "json",  
        success : function(data) {  
        	if(data.length == 0) {
        		$("#loader").hide();
        	} else {
        		$.each(data, function(idx, article){  
    				$("#loader").before(
    						"<div class='excerpt' style=''> "
    						+" <header>"
    						+"	<a class='cat' href='#'>"+ article.topic + "<i></i></a>"
    						+"	<h2>"
    						+"		<a href='article/" + article.id + "' target='_blank'>"+ article.title +"</a>"
    						+"	</h2>"
    						+" </header>"
    						+" <p class='meta'>"
    						+"	<time class='time'>"
    						+"		<i class='glyphicon glyphicon-time'></i> "
    						+		new Date(article.createTime).toLocaleString()
    						+"	</time>"
    						+"  <span class='views'>"
    						+"  <i class='fa fa-user'></i>"
    						+   article.user.name
    						+"  </span>"
    						+"	<span class='views'>"
    						+"		<i class='glyphicon glyphicon-eye-open'></i>"
    						+		 article.readCount
    						+"	</span> "
    						+"	<span class='views'>"
    						+"		<i class='glyphicon glyphicon-comment'></i> "+ article.commentCount
    						+"	</span>"
    						+" </p>"
    						+" <p class='note'>"+ article.content +"</p>"
    						+" </div>"); 
                });
            	pageNow = pageNow + 1;
        	}
        }
    });  
    return false;
}