layui.config({
	base : '/mkw-colligate/static/extends/',
	version : new Date().getTime()
});
layui.use(['xform' ], function() {
	var $ = layui.jquery;
	var xform = layui.xform();
	
	var allRoom = $("#allRoom").html();
	if(allRoom.length > 0) {
		allRoom = JSON.parse(allRoom);
	}
	var allRole = $("#allRole").html();
	if(allRole.length > 0) {
		allRole = JSON.parse(allRole);
	}

	xform.init({
		elem: '#formContent',
		buttons: [
			{ label: '保存', id: 'saveBtn', icon: '&#xe618;' },
			{ label: '重置', id: 'resetBtn', icon: '&#x1002;', color: 'layui-btn-primary' }
		],
		columns: [
			{ type: 'hidden', name: 'id' }, 
			{ type: 'text', name: 'loginName', label: '账号', required: true }, 
			{ type: 'password', name: 'loginPass', label: '密码', required: true }, 
			{ type: 'text', name: 'name', label: '姓名', required: true }, 
			{ type: 'text', name: 'mobile', label: '联系方式', required: true },
			{ type: 'select', name: 'roomId', label: '所属门店', required: true, selectMaps: allRoom },
			{ type: 'select', name: 'roleId', label: '所属角色', required: true, selectMaps: allRole }
		]
	}, function(data) {
		var json = xform.getFormData();
		if (data == 'resetBtn') {
			// 重置按钮
			xform.clearFormData();
		} else if (data == 'saveBtn') {
			// 数据校验
			if(xform.verify()) {
				var url = json.id == null ? '/mkw-colligate/account/add.do' : '/mkw-colligate/account/edit.do';
				execute(url, json, function(result) {
					if('405' == result.code) {
						alertify(2, '账号已存在');
					} else {
						alertify(1, '保存成功', function () {
							// 关闭所有弹框
							closer();
							flushParentTable();
						});
					}
				});
			}
		} 
		
	});
	
	// 初始化数据
	var accountInfo = $("#accountInfo").html();
	if(accountInfo.length > 0) {
		accountInfo = JSON.parse(accountInfo);
		// 延迟200毫秒执行
		setTimeout(function() {
			xform.setFormData(accountInfo);
		}, 200);
	}
});