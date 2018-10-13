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
	    url: '/mkw-client/roomgroup/add-list.do',
	    cols: [[
	            {sort: true, field:'img', title: '图片', templet: '<div><img height="100" width="100" src="{{d.img}}"></div>'},
	            {sort: true, field:'name', title: '名称'},
	            {sort: true, field:'type', title: '类型'},
	            {sort: true, field:'originalPrice', title: '原价'},
	            {sort: true, field:'price', title: '现价'},
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
		if(obj.event === 'add') {
			execute('/mkw-client/roomgroup/addRoomFood.do', {packageId: obj.data.id}, function(e) {
				alertify(1, '添加成功');
				flushTable(searchCache, 1);
			});
		}
	});
	
	
});