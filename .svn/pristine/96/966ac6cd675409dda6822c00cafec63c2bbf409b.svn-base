<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>美客威管理系统</title>
<link rel="stylesheet" href="${basePath }/static/plugins/layui/css/layui.css">
<link rel="stylesheet" href="${basePath }/static/css/global.css" media="all">
<link rel="stylesheet" href="${basePath }/static/css/command.css" media="all">
<link rel="stylesheet" href="${basePath }/static/plugins/font-awesome/css/font-awesome.min.css">
<script type="text/javascript" src="${basePath }/static/plugins/layui/layui.js?r=${random }"></script>
<script type="text/javascript" src="${basePath }/static/js/command.js?r=${random }"></script>
<script type="text/javascript" src="${basePath }/static/module/order/order-list.js?r=${random }"></script>
<!-- 定义模板 -->
<script type="text/html" id="tablePage">
	<a class="layui-btn layui-btn-xs" lay-event="edit">订单详情</a>
</script>
<script type="text/html" id="statusFormat">
{{# if (d.status == 0) { }}
	<span class="layui-badge layui-bg-cyan">已完成</span>
{{# } else if(d.status==1) { }}  
    <span class="layui-badge">待支付</span>
{{# } else { }}  
    <span class="layui-badge">已取消</span>
{{# } }} 
</script>
</head>
<body>
	<div class="admin-main">
	
	<!-- 操作栏 -->
		<blockquote class="layui-elem-quote">
			</a> <a href="javascript:void(0);" class="layui-btn layui-btn-small" id="searchBtn"><i class="fa fa-search" aria-hidden="true"></i></i> 订单搜索
			</a> <a href="javascript:void(0);" class="layui-btn layui-btn-small" id="exportBtn"><i class="fa fa-search" aria-hidden="true"></i></i> 批量导出
			</a>
		</blockquote>
		<!-- 表格 -->
		<table id="tableContent" class="layui-table" lay-filter="tableEvent"></table>
	</div>
</body>
</html>