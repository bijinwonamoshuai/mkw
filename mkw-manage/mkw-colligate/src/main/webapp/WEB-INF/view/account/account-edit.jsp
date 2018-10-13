<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
<script type="text/javascript" src="${basePath }/static/module/account/account-edit.js?r=${random }"></script>
</head>
<body>
	<span id="allRoom" style="display: none">${allRoom }</span>
	<span id="allRole" style="display: none">${allRole }</span>
	<span id="accountInfo" style="display: none">${accountInfo }</span>
	
	<div style="margin: 15px;">
		<div id="formContent"></div>
	</div>
</body>
</html>