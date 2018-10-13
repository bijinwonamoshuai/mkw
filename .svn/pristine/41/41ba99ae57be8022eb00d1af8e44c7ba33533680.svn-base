layui.config({
	base : '/mkw-merchant/static/extends/',
	version : new Date().getTime()
});
layui.use(['xform' ], function() {
	var $ = layui.jquery;
	var xform = layui.xform();

	xform.init({
		elem: '#formContent',
		buttons: [
			{ label: '保存', id: 'saveBtn', icon: '&#xe618;' },
			{ label: '重置', id: 'resetBtn', icon: '&#x1002;', color: 'layui-btn-primary' }
		],
		columns: [
			{ type: 'hidden', name: 'id' },
			{ type: 'text', name: 'name', label: '菜品组名称', required: true }
		]
	}, function(data) {
		var json = xform.getFormData();
		if (data == 'resetBtn') {
			// 重置按钮
			xform.clearFormData();
		} else if (data == 'saveBtn') {
			// 数据校验
			if(xform.verify()) {
				var url = null == json.id ? '/mkw-merchant/roomGroup/add.do' : '/mkw-merchant/roomGroup/edit.do';
				json.status = 0;
				execute(url, json, function(result) {
					if('406' == result.code) {
						alertify(2, '菜品组名称已存在');
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
	var roomGroupInfo = $("#roomGroupInfo").html();
	if(roomGroupInfo.length > 0) {
		roomGroupInfo = JSON.parse(roomGroupInfo);
		xform.setFormData(roomGroupInfo);
	}
});