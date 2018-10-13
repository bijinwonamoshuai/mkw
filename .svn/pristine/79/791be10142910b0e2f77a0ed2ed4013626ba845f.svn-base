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
<script type="text/javascript" src="${basePath }/static/module/rolePermission/rolePermission-edit.js?r=${random }"></script>
</head>
<body>
	<span id="rolePermissionList" style="display: none">${rolePermissionList }</span>
	<span id="rolePermissionInfo" style="display: none">${rolePermissionInfo }</span>
	
	<br>
	<div class="container">
		<div class="layui-row">
			<form class="layui-form">
				<input type="hidden" name="id" value="${roleInfo.id }">
				<div class="layui-form-item">
					<label class="layui-form-label">角色名称</label>
					<div class="layui-input-block">
						<input class="layui-input" type="text" name="name" placeholder="请输入角色名称" value="${roleInfo.name }" />
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">选择权限</label>
					<div class="layui-input-block">
						<div id="LAY-auth-tree-index"></div>
					</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-input-block">
						<button class="layui-btn" lay-submit="" lay-filter="submitBtn">提交</button>
						<button class="layui-btn layui-btn-primary" type="reset">重置</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>