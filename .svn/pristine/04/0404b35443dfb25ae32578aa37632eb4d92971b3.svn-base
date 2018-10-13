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
			{ type: 'text', name: 'name', label: '门店名称', required: true },
			{ type: 'text', name: 'address', label: '门店地址', required: true },
			{ type: 'file', name: 'imageUrl', label: '门店图片', required: true },
		]
	}, function(data) {
		var json = xform.getFormData();
		if (data == 'resetBtn') {
			// 重置按钮
			xform.clearFormData();
		} else if (data == 'saveBtn') {
			// 数据校验
			if(xform.verify()) {
				var url = null == json.id ? '/mkw-client/room/add.do' : '/mkw-client/room/edit.do';
				execute(url, json, function(result) {
					if('402' == result.code) {
						alertify(2, '门店名称已存在');
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
	var roomInfo = $("#roomInfo").html();
	if(roomInfo.length > 0) {
		roomInfo = JSON.parse(roomInfo);
		roomInfo.imageUrl = JSON.parse(roomInfo.imageUrl);
		xform.setFormData(roomInfo);
	}
});