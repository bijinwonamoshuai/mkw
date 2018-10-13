<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
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
<script type="text/javascript" src="${basePath }/static/module/order/order-info.js?r=${random }"></script>
</head>
<body>
	<span id="selectList" style="display: none">
	</span>
	
	<div style="margin: 15px;">
		<div id="formContent"></div>
	</div>
</body>
</html>