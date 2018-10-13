/** index.js By Beginner Emain:zheng_jinfan@126.com HomePage:http://www.zhengjinfan.cn */

var tab;

layui.config({
	base: '/mkw-merchant/static/extends/',
    version: new Date().getTime()
}).use(['element', 'layer', 'navbar', 'tab'], function () {
    var element = layui.element,
        $ = layui.jquery,
        layer = layui.layer,
        navbar = layui.navbar();
    tab = layui.tab({
        elem: '.admin-nav-card',
        contextMenu: true,
        autoRefresh: true,
        closeBefore: function (obj) {
            return true;
        }
    });
    
	var flag = 0, t1, notReadCount;
    function showMsg() {
    	if (0 == flag) {
	    	$("#messageCount").css("background-color","#FFFFFF");
	    	flag = 1;
    	}else{
	    	$("#messageCount").css("background-color","#FF5722");
	    	flag = 0;
    	}
    }
    function checkShow(open) {
    	if (open) {
    		t1 = window.setInterval(function () {
        		notReadCount = $("#messageCount").html();
        		if (parseInt(notReadCount) != 0) {
        			showMsg();
        		} else {
        			$("#messageCount").css("background-color","#FF5722");
        			window.clearInterval(t1);
        		}
        	}, 500);
    	} else {
    		$("#messageCount").css("background-color","#FF5722");
			window.clearInterval(t1);
    	}
    }
    
    //iframe自适应
    $(window).on('resize', function () {
        var $content = $('.admin-nav-card .layui-tab-content');
        $content.height($(this).height() - 121);
        $content.find('iframe').each(function () {
            $(this).height($content.height());
        });
    }).resize();
    
    execute('/mkw-merchant/findMenu.do', {status: 0, level: 1}, function(result) {
        //设置navbar
        navbar.set({
            spreadOne: true,
            elem: '#admin-navbar-side',
            cached: true,
            data: result.responseData
        });
        //渲染navbar
        navbar.render();
        
        //监听左菜单点击事件
        navbar.on('click(side)', function (data) {
        	$.each(result.responseData, function(i, item){
        		if(item.title === data.field.title) {
                	// 删除所有老的Tab
                	tab.removeAllTab();
                	
                	// 打开的新的Tab
                	execute('/mkw-merchant/findMenu.do', {status: 0, level: 2, parentId: item.id}, function(k) {
                    	$.each(k.responseData, function(i, item){
                    		tab.tabAdd(item);
                    	});
                    });
        		}
        	});
        });
        
        // 重置第一个tab
        execute('/mkw-merchant/findMenu.do', {status: 0, level: 2, parentId: result.responseData[0].id}, function(e) {
        	$("#firstCite").html(e.responseData[0].title);
        	$("#firstIframe").attr('src', e.responseData[0].href);
        });
	});

    $('.admin-side-toggle').on('click', function () {
        var sideWidth = $('#admin-side').width();
        if (sideWidth === 200) {
            $('#admin-body').animate({
                left: '0'
            }); //admin-footer
            $('#admin-footer').animate({
                left: '0'
            });
            $('#admin-side').animate({
                width: '0'
            });
        } else {
            $('#admin-body').animate({
                left: '200px'
            });
            $('#admin-footer').animate({
                left: '200px'
            });
            $('#admin-side').animate({
                width: '200px'
            });
        }
    });
    $('.admin-side-full').click(function() {
        var docElm = document.documentElement;
        //W3C  
        if (docElm.requestFullscreen) {
            docElm.requestFullscreen();
        }
        //FireFox  
        else if (docElm.mozRequestFullScreen) {
            docElm.mozRequestFullScreen();
        }
        //Chrome等  
        else if (docElm.webkitRequestFullScreen) {
            docElm.webkitRequestFullScreen();
        }
        //IE11
        else if (elem.msRequestFullscreen) {
            elem.msRequestFullscreen();
        }
        layer.msg('按Esc即可退出全屏');
    });
    
    $('#myMessage').click(function() {
        tab.tabAdd({
            href: '/mkw-merchant/message/list-message.do',
            icon: 'fa-bell-o',
            title: '消息通知'
        });
    });

    $('#userinfo').click(function() {
        tab.tabAdd({
            href: '/mkw-merchant/account/edit-userinfo.do',
            icon: 'fa-user-circle',
            title: '个人信息'
        });
    });
    
    $('#repass').click(function() {
        tab.tabAdd({
            href: '/mkw-merchant/account/edit-password.do',
            icon: 'fa-key',
            title: '修改密码'
        });
    });
    
    $('#logout').click(function() {
    	top.location.href = "/mkw-merchant/logout.do";
    });
});