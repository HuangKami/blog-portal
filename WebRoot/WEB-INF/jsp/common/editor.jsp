<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- icon -->
    <link rel="shortcut icon" type="image/x-icon" href="http://classfoo.com/static/favicon.ico">
    <link rel="apple-touch-icon" href="http://classfoo.com/static/apple-touch-icon-precomposed.png">
    <script src="js/global.js"></script>
    <script src="js/wysihtml5/main.js"></script>
    <script src="js/wysihtml5/classfoo-editor.js"></script>

   <style>
	.well.cf,.panel.cf{border-color:#d8d8d8;-webkit-box-shadow:0 1px 0 #cfcfcf;-moz-box-shadow:0 1px 0 #cfcfcf;box-shadow:0 1px 0 #cfcfcf}
	.transit {
	    -webkit-transition: all 0.4s ease-in-out;
	    -o-transition: all 0.4s ease-in-out;
	    transition: all 0.4s ease-in-out
	}
	.cfeditor {
	    padding: 10px;
	    overflow: auto;
	    width: 100%;
	    height: ${param.editorHeight};
	    border: 1px solid #cccccc;
	    border-radius: 4px;
	    outline: 0
	}
	div.cf-toolbar a.wysihtml5-command-active {
	    background-image: none;
	    -webkit-box-shadow: inset 0 2px 4px rgba(0,0,0,0.15),0 1px 2px rgba(0,0,0,0.05);
	    -moz-box-shadow: inset 0 2px 4px rgba(0,0,0,0.15),0 1px 2px rgba(0,0,0,0.05);
	    box-shadow: inset 0 2px 4px rgba(0,0,0,0.15),0 1px 2px rgba(0,0,0,0.05);
	    background-color: #E6E6E6;
	    background-color: #D9D9D9;
	    outline: 0
	}
	.change-label .label {
	    font-size: 100%
	}
	.cf-toolbar .tb-bg ul li a,.cf-toolbar .tb-alert ul li a {
	    padding: 0px 0px
	}
	.cf-toolbar .tb-bg ul {
	    padding: 0px
	}
	.cf-toolbar .tb-bg ul li span {
	    display: block;
	    padding: 8px 10px;
	    width: 100%
	}
	.cf-toolbar .tb-alert ul li p {
	    margin: 6px
	}
	.cf-toolbar .showit {
	    width: 66px
	}
	.cf-toolbar .result-wrap.showit {
	    width: 39px
	}
	.cf-toolbar .hideit {
	    width: 0px
	}
	.cf-toolbar .extra-part {
	    overflow-x: hidden;
	    display: block
	}
	.cf-toolbar .toggle-extra-part {
	    padding: 6px;
	    background-color: transparent
	}
	.cf-toolbar .extra-part.open {
	    overflow-x: visible
	}
	.bg-primary,.bg-success,.bg-info,.bg-warning,.bg-danger {
	    padding: 8px 10px
	}
</style>

<div class="row">
<div class="col-lg-12">
	<div class="section well cf">
		<div id="form-container-a794381c">
				<div class="row">
					<div class="col-md-12">
						<div class="btn-toolbar cf-toolbar" id="page-toolbar-a794381c" style="margin-bottom:4px;">
							<div class="btn-group">
								<a class="btn btn-default" data-wysihtml5-command="bold" title="CTRL+B" target="_blank" unselectable="on"><i class="fa fa-bold"></i></a>
								<a class="btn btn-default" data-wysihtml5-command="italic" title="CTRL+I" target="_blank" unselectable="on"><i class="fa fa-italic"></i></a>
								<a class="btn btn-default" data-wysihtml5-command="underline" title="CTRL+U" target="_blank" unselectable="on"><i class="fa fa-underline"></i></a>
								<a class="btn btn-default" data-wysihtml5-command="strikethrough" target="_blank" unselectable="on"><i class="fa fa-strikethrough"></i></a>
							</div>

							<div class="btn-group">
								<a class="btn btn-default" data-wysihtml5-command="formatInline" data-wysihtml5-command-value="code" title="文字、代码片段 " target="_blank" unselectable="on"><code>片段</code></a>
								<div class="btn-group transit extra-part hideit" id="page-toolbar-a794381c-s-text"><!--text-->
									<a class="btn btn-default dropdown-toggle" data-toggle="dropdown" target="_blank" unselectable="on">属性&nbsp;<span class="caret"></span></a>
									<ul class="dropdown-menu" role="menu">
										<li><a href="javascript:void(0);" selector="#page-toolbar-a794381c-s-text" target="_blank" data-wysihtml5-command="myClass" data-wysihtml5-command-value="text-muted" unselectable="on"><span class="text-muted">次要 / 弱化</span></a></li>
										<li><a href="javascript:void(0);" selector="#page-toolbar-a794381c-s-text" target="_blank" data-wysihtml5-command="myClass" data-wysihtml5-command-value="text-primary" unselectable="on"><span class="text-primary">首要 / 最佳</span></a></li>
										<li><a href="javascript:void(0);" selector="#page-toolbar-a794381c-s-text" target="_blank" data-wysihtml5-command="myClass" data-wysihtml5-command-value="text-info" unselectable="on"><span class="text-info">信息 / 数据</span></a></li>
										<li><a href="javascript:void(0);" selector="#page-toolbar-a794381c-s-text" target="_blank" data-wysihtml5-command="myClass" data-wysihtml5-command-value="text-warning" unselectable="on"><span class="text-warning">提醒 / 警告</span></a></li>
										<li><a href="javascript:void(0);" selector="#page-toolbar-a794381c-s-text" target="_blank" data-wysihtml5-command="myClass" data-wysihtml5-command-value="text-success" unselectable="on"><span class="text-success">成功 / 好结果</span></a></li>
										<li><a href="javascript:void(0);" selector="#page-toolbar-a794381c-s-text" target="_blank" data-wysihtml5-command="myClass" data-wysihtml5-command-value="text-danger" unselectable="on"><span class="text-danger">危险 / 坏结果</span></a></li>
										<li></li>
									</ul>
								</div>
								<div class="btn-group extra-part transit hideit" id="page-toolbar-a794381c-s-tag"><!--tag-->
									<a class="btn btn-default dropdown-toggle" data-toggle="dropdown" target="_blank" unselectable="on">标签&nbsp;<span class="caret"></span></a>
									<ul class="dropdown-menu cf change-label" role="menu">
										<li><a href="javascript:void(0);" selector="#page-toolbar-a794381c-s-tag" target="_blank" data-wysihtml5-command="myClass" data-wysihtml5-command-value="label label-default" unselectable="on"><span class="label label-default">默认 / 普通</span></a></li>
										<li><a href="javascript:void(0);" selector="#page-toolbar-a794381c-s-tag" target="_blank" data-wysihtml5-command="myClass" data-wysihtml5-command-value="label label-primary" unselectable="on"><span class="label label-primary">首要 / 最佳</span></a></li>
										<li><a href="javascript:void(0);" selector="#page-toolbar-a794381c-s-tag" target="_blank" data-wysihtml5-command="myClass" data-wysihtml5-command-value="label label-info" unselectable="on"><span class="label label-info">信息 / 数据</span></a></li>
										<li><a href="javascript:void(0);" selector="#page-toolbar-a794381c-s-tag" target="_blank" data-wysihtml5-command="myClass" data-wysihtml5-command-value="label label-warning" unselectable="on"><span class="label label-warning">提醒 / 警告</span></a></li>
										<li><a href="javascript:void(0);" selector="#page-toolbar-a794381c-s-tag" target="_blank" data-wysihtml5-command="myClass" data-wysihtml5-command-value="label label-success" unselectable="on"><span class="label label-success">成功 / 好结果</span></a></li>
										<li><a href="javascript:void(0);" selector="#page-toolbar-a794381c-s-tag" target="_blank" data-wysihtml5-command="myClass" data-wysihtml5-command-value="label label-danger" unselectable="on"><span class="label label-danger">危险 / 坏结果</span></a></li>
									</ul>
								</div>
								<a class="btn btn-default toggle-extra-part" target="_blank"><i class="fa fa-caret-right"></i></a>
							</div>

							<div class="btn-group">
								<a class="btn btn-default" data-wysihtml5-command="formatBlock" data-wysihtml5-command-value="blockquote" title=" 引用 " target="_blank" unselectable="on"><i class="fa fa-quote-left"></i></a>
								<a class="btn btn-default" data-wysihtml5-command="formatBlock" data-wysihtml5-command-value="pre" title=" 代码块 " target="_blank" unselectable="on"><i class="fa fa-code"></i></a>
								<a class="btn btn-default" data-wysihtml5-command="insertUnorderedList" title=" 无序列表 " target="_blank" unselectable="on"><i class="fa fa-list"></i></a>
								<a class="btn btn-default" data-wysihtml5-command="insertOrderedList" title=" 有序列表 " target="_blank" unselectable="on"><i class="fa fa-list-ol"></i></a>
								<div class="btn-group tb-bg extra-part transit hideit" id="page-toolbar-a794381c-s-bg"><!--bg-->
									<a class="btn btn-default dropdown-toggle" data-toggle="dropdown" target="_blank" unselectable="on">背景&nbsp;<span class="caret"></span></a>
									<ul class="dropdown-menu cf change-label" role="menu">
										<li><a href="javascript:void(0);" selector="#page-toolbar-a794381c-s-bg" target="_blank" data-wysihtml5-command="bgColor" data-wysihtml5-command-value="bg-primary" unselectable="on"><span class="bg-primary">首要 / 最佳</span></a></li>
										<li><a href="javascript:void(0);" selector="#page-toolbar-a794381c-s-bg" target="_blank" data-wysihtml5-command="bgColor" data-wysihtml5-command-value="bg-info" unselectable="on"><span class="bg-info">信息 / 数据</span></a></li>
										<li><a href="javascript:void(0);" selector="#page-toolbar-a794381c-s-bg" target="_blank" data-wysihtml5-command="bgColor" data-wysihtml5-command-value="bg-success" unselectable="on"><span class="bg-success">成功 / 好结果</span></a></li>
										<li><a href="javascript:void(0);" selector="#page-toolbar-a794381c-s-bg" target="_blank" data-wysihtml5-command="bgColor" data-wysihtml5-command-value="bg-warning" unselectable="on"><span class="bg-warning">提醒 / 警告</span></a></li>
										<li><a href="javascript:void(0);" selector="#page-toolbar-a794381c-s-bg" target="_blank" data-wysihtml5-command="bgColor" data-wysihtml5-command-value="bg-danger" unselectable="on"><span class="bg-danger">危险 / 坏结果</span></a></li>
									</ul>
								</div>
								<div class="btn-group tb-alert extra-part transit hideit" id="page-toolbar-a794381c-s-alert"><!--alert-->
									<a class="btn btn-default dropdown-toggle" data-toggle="dropdown" target="_blank" unselectable="on">突显&nbsp;<span class="caret"></span></a>
									<ul class="dropdown-menu cf change-label" role="menu">
										<li><a href="javascript:void(0);" selector="#page-toolbar-a794381c-s-alert" target="_blank" data-wysihtml5-command="myAlert" data-wysihtml5-command-value="info" unselectable="on"><p class="alert alert-info">信息 / 数据</p></a></li>
										<li><a href="javascript:void(0);" selector="#page-toolbar-a794381c-s-alert" target="_blank" data-wysihtml5-command="myAlert" data-wysihtml5-command-value="success" unselectable="on"><p class="alert alert-success">成功 / 好结果</p></a></li>
										<li><a href="javascript:void(0);" selector="#page-toolbar-a794381c-s-alert" target="_blank" data-wysihtml5-command="myAlert" data-wysihtml5-command-value="warning" unselectable="on"><p class="alert alert-warning">提醒 / 警告</p></a></li>
										<li><a href="javascript:void(0);" selector="#page-toolbar-a794381c-s-alert" target="_blank" data-wysihtml5-command="myAlert" data-wysihtml5-command-value="danger" unselectable="on"><p class="alert alert-danger">危险 / 坏结果</p></a></li>
									</ul>
								</div>
								<div class="btn-group extra-part result-wrap transit hideit">
									<a class="btn btn-default" data-wysihtml5-command="myResult" data-wysihtml5-command-value="thumbnail" title=" 运行结果 " target="_blank" unselectable="on"><i class="fa fa-terminal"></i></a>
								</div>
								<a class="btn btn-default toggle-extra-part" target="_blank"><i class="fa fa-caret-right"></i></a>
							</div>

							<div class="btn-group">
								<!--<label for="file" class="btn btn-default" title="图片"><input type="file" id="file" style="display: none" /><i class="fa fa-image"></i></label>  -->
								<a id="page-toolbar-a794381cLBID" dialog="#page-toolbar-a794381cLMID" class="btn btn-default" href="javascript:void(0);" data-wysihtml5-command="createLink" title="链接" unselectable="on"><i class="fa fa-link"></i></a>
							</div>
							<script>
								if ( typeof jQuery != 'undefined' ) {
									$('#page-toolbar-a794381cLMID').appendTo('body');
								}
							</script>
							<script>
								$('#page-toolbar-a794381cLBID').attr('data-wysihtml5-command','createLink').attr('title','链接');
								$('#page-toolbar-a794381cLMID').attr('data-wysihtml5-dialog','createLink');
							</script>
						</div>

					</div>
				</div>
				<div class="row">
					<div class="col-md-12" id="content">
						<textarea name="content" id="id-page-editor-a794381c" class="cfeditor"></textarea>
					</div>
				</div>

			</div>
		</div>
</div>
<script>
	var href = '../css/bootstrap.min.css'
	var editor = new wysihtml5.Editor(
		"id-page-editor-a794381c",{
			name: "page-editor-a794381c",
			style: true,
			toolbar:  "page-toolbar-a794381c",
			parserRules:  ToolBarRules,
			useLineBreaks: false,
			stylesheets: href
		});    
	</script>
</div>
<!--########## content_left_end ##########-->

<div class="modal fade noautofocus" id="page-toolbar-a794381cLMID" tabindex="-1" role="dialog" aria-labelledby="page-toolbar-a794381cLMIDLabel" aria-hidden="true" style="display:none" data-wysihtml5-dialog="createLink">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
				<h3 class="modal-title" id="page-toolbar-a794381cLMIDLabel">链接</h3>
			</div>
			<div class="modal-body">
				<input class="form-control" data-wysihtml5-dialog-field="href" value="http://">
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal" data-wysihtml5-dialog-action="cancel">取消</button>
				<button type="button" class="btn btn-primary" data-dismiss="modal" data-wysihtml5-dialog-action="save">确定（Enter）</button>
			</div>
		</div>
	</div>
</div>
