layui.config({
    base: '/mkw-client/static/extends/',
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
	    url: '/mkw-client/roomFood/list.do',
	    cols: [[
	            {sort: true, field:'img', title: '图片', templet: '<div><img height="100" width="100" src="{{d.img}}"></div>'},
	            {sort: true, field:'showName', title: '名称'},
	            {sort: true, field:'showPrice', title: '价格'},
	            {sort: true, field:'showStock', title: '库存'},
	            {sort: true, field:'groupName', title: '菜品组名称'},
	            {sort: true, field:'', title: '状态', templet: '#statusFormat'},
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
			openPager('/mkw-client/roomFood/roomFood-edit.do?id=' + obj.data.id, '编辑');
		} else if(obj.event === 'delete') {
			execute('/mkw-client/roomFood/delete.do', {id: obj.data.id}, function(e) {
				alertify(1, '保存成功');
				flushTable(searchCache, 1);
			});
		} else if(obj.event === 'down') {
			execute('/mkw-client/roomFood/change.do', {id: obj.data.id, status: 1}, function(e) {
				alertify(1, '保存成功');
				flushTable(searchCache, 1);
			});
		} else if(obj.event === 'up') {
			execute('/mkw-client/roomFood/change.do', {id: obj.data.id, status: 0}, function(e) {
				alertify(1, '保存成功');
				flushTable(searchCache, 1);
			});
		}
	});
	
	$("#searchBtn").click(function() {
		xdialog.init({
			title: '查询',
			columns: [
				{ type: 'text', name: 'foodName', label: '菜品名称' },
				{ type: 'text', name: 'groupName', label: '菜品组名称' },
				{ type: 'select', name: 'foodStatus', label: '状态', selectMaps: [{label: '已上架', value: 0}, {label: '已下架', value: 1}] }
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