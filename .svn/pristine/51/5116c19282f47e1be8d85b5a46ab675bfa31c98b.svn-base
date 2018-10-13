layui.config({
	base : '/mkw-client/static/extends/',
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
			{ type: 'text', name: 'showName', label: '名称', required: true },
			{ type: 'text', name: 'showPrice', label: '价格', required: true },
			{ type: 'text', name: 'showStock', label: '库存', required: true }
		]
	}, function(data) {
		var json = xform.getFormData();
		if (data == 'resetBtn') {
			// 重置按钮
			xform.clearFormData();
		} else if (data == 'saveBtn') {
			// 数据校验
			if(xform.verify()) {
				execute('/mkw-client/roomFood/edit.do', json, function(result) {
					alertify(1, '保存成功', function () {
						// 关闭所有弹框
						closer();
						flushParentTable();
					});
				});
			}
		} 
		
	});
	
	// 初始化数据
	var roomFoodInfo = $("#roomFoodInfo").html();
	if(roomFoodInfo.length > 0) {
		roomFoodInfo = JSON.parse(roomFoodInfo);
		xform.setFormData(roomFoodInfo);
	}
});