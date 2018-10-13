layui.config({
	base: '/mkw-merchant/static/extends/',
    version: new Date().getTime()
}).use([ 'layer' ], function() {
	var layer = layui.layer;
	var $ = layui.jquery;
	
	// 清空数据
	$("#loginName").val('');
	$("#loginPass").val('');
	
	$("#loginBtn").click(function() {
		var loginName = $("#loginName").val();
		var loginPass = $("#loginPass").val();
		
		if (loginName.length == 0) {
			alertify(2, '请输入账号');
		} else if (loginPass.length == 0) {
			alertify(2, '请输入密码');
		} else {
			login(loginName, loginPass);
		}
	});
	
	function login(loginName, loginPass) {
		var params = {
			loginName: loginName,
			loginPass: loginPass
		};
		execute("/mkw-merchant/checkLogin.do", params, function(result) {
			if(result.code == '401') {
				alertify(2, "登录账号或密码错误");
			} else {
				window.location.href = "/mkw-merchant/home.do";
			}
		}, false);
	}
});
