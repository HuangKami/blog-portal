<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="css/select.css">
<div id="dd" class="wrapper-dropdown-3" tabindex="1">
	<span id="topic">文章类型</span>
	<ul class="dropdown">
		<li><a href="#">人工智能</a></li>
		<li><a href="#">移动开发</a></li>
		<li><a href="#">物联网</a></li>
		<li><a href="#">架构</a></li>
		<li><a href="#">云计算/大数据</a></li>
		<li><a href="#">游戏开发</a></li>
		<li><a href="#">运维</a></li>
		<li><a href="#">数据库</a></li>
		<li><a href="#">前端</a></li>
		<li><a href="#">后端</a></li>
		<li><a href="#">编程语言</a></li>
		<li><a href="#">研发管理</a></li>
		<li><a href="#">区块链</a></li>
		<li><a href="#">程序人生</a></li>
		<li><a href="#">计算机理论与基础</a></li>
		<li><a href="#">其他</a></li>
	</ul>
</div>

<script type="text/javascript">
	function DropDown(el) {
		this.dd = el;
		this.placeholder = this.dd.children('span');
		this.opts = this.dd.find('ul.dropdown > li');
		this.val = '';
		this.index = -1;
		this.initEvents();
	}

	DropDown.prototype = {
		initEvents : function() {
			var obj = this;

			obj.dd.on('click', function(event){
				$(this).toggleClass('active');
				return false;
			});

			obj.opts.on('click',function(){
				var opt = $(this);
				obj.val = opt.text();
				obj.index = opt.index();
				obj.placeholder.text(obj.val);
			});
		},

		getValue : function() {
			return this.val;
		},

		getIndex : function() {
			return this.index;
		}
	}

	$(function() {
		var dd = new DropDown( $('#dd') );
		$(document).click(function() {
			$('.wrapper-dropdown-3').removeClass('active');
		});
	});
</script>