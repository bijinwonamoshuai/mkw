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
	    url: '/mkw-client/order/info-list.do',
	    cols: [[
	            {type:'checkbox'},
	            {sort: true, field:'id', title: '订单编号'},
	            {sort: true, field:'phone', title: '手机号'},
	            {sort: true, field:'wechatId', title: '微信账户'},
	            {sort: true, field:'allPrice', title: '金额'},
	            {sort: true, field:'payTime', title: '下单时间'},
	            {sort: true, field:'orderStatus', title: '订单状态'},
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
			openPager('/mkw-client/order/order-info.do?id=' + obj.data.id, '查看详情');
		}
	});
	
	// 初始化数据
	var roomInfo = $("#selectList").html();
	if(roomInfo.length > 0) {
		roomInfo = JSON.parse(roomInfo);
		xform.setFormData(roomInfo);
	}
	
});