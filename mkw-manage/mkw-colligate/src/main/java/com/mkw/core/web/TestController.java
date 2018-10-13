package com.mkw.core.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mkw.annotation.Logs;
import com.mkw.constant.Operate;
import com.mkw.core.common.SuperController;
import com.mkw.core.service.PermissionService;
import com.mkw.plugins.DataInfo;
import com.mkw.util.AjaxUtils;

@Controller
@RequestMapping("test")
public class TestController extends SuperController {
	
	@Resource
	private PermissionService permissionService;
	
	@RequestMapping("a")
	@Logs(operate = Operate.UPDATE, module = "测试控制器", remark = "测试")
	public void a(HttpServletRequest request, HttpServletResponse response) throws Exception {
		DataInfo dataInfo = AjaxUtils.parseJson(request);
		
		permissionService.addModel(dataInfo, "21c70c60c46348f89ef52dcf896b603d");

		AjaxUtils.outJson(response, dataInfo);
	}
}
