layui.config({
    base: '/mkw-colligate/static/extends/',
    version: new Date().getTime()
});
layui.use(['table', 'jquery', 'form', 'layer', 'xdialog'], function() {
	var table = layui.table;
	var $ = layui.jquery;
	var form = layui.form;
	var layer = layui.layer;
	var xdialog = layui.xdialog();
	
	//初始化table
	table.render({
		id: 'myTable',
	    elem: '#tableContent',
	    url: '/mkw-colligate/room/list.do',
	    cols: [[
	            {type:'checkbox'},
	            {sort: true, field:'name', title: '门店名称'},
	            {sort: true, field:'address', title: '门店地址'},
	            {align:'left', title: '操作', toolbar: '#tablePage'}
	    ]],
	    page: true, //是否显示分页
        limit: 50, //每页默认显示的数量
        limits:[50,100,200,300,500,1000],
	    width: 'full',
	    height: 'full',
	    cellMinWidth: 250
	});
	
	//监听事件
	table.on('tool(tableEvent)', function(obj) {
		if(obj.event === 'edit') {
			openPager('/mkw-colligate/room/room-edit.do?id=' + obj.data.id, '编辑');
		}
	});
	
	$("#addBtn").click(function() {
		openPager('/mkw-colligate/room/room-edit.do', '添加');
	});
	
	$("#searchBtn").click(function() {
		xdialog.init({
			title: '查询',
			columns: [
				{ type: 'text', name: 'name', label: '门店名称' }
			],
			buttons: [
				{ label: '查询', id: 'queryBtn', icon: '&#xe615;' },
				{ label: '重置', id: 'resetBtn', icon: '&#xe640;', color: 'layui-btn-primary' }
			]
		});
		
		xdialog.show(function(data) {
			if (data == 'queryBtn') {
				searchCache = xdialog.getFormData();
				xdialog.closer();
				flushTable(searchCache, 1);
			}
			if (data == 'resetBtn') {
				xdialog.clearFormData();
			}
		});
	});
	
	
});