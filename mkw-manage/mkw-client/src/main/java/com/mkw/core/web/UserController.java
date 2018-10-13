package com.mkw.core.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mkw.annotation.Logs;
import com.mkw.annotation.Verify;
import com.mkw.constant.CodeConstant;
import com.mkw.constant.Operate;
import com.mkw.core.common.SuperController;
import com.mkw.core.service.UserService;
import com.mkw.plugins.DataInfo;
import com.mkw.util.AjaxUtils;
/**
 * 会员模块
 * @author luoyong
 * @Date 2018年9月7日-上午10:24:20
 */
@Controller
@RequestMapping("/user")
public class UserController extends SuperController{
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/login")
	@Verify(notNull = "username,password")
	@Logs(operate = Operate.SELECT, module = "测试控制器", remark = "查询用户登录信息")
	public String login(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DataInfo datainfo = AjaxUtils.parseJson(request);
		DataInfo findListByModel = userService.login(datainfo);
		if (findListByModel == null) {
			datainfo.setCode(CodeConstant.ERROR_LOGIN);
			datainfo.setMsg("当前用户不存在，请重新输入");
			return "/login.html";
		}
		//放入session，一旦session失效的登录拦截
		request.getSession().setAttribute("sessionId", findListByModel);
		datainfo.setCode(CodeConstant.SUCCESS);
		datainfo.setMsg("登陆成功");
		return "/index.html";
	}
	
	@RequestMapping("/add")
	@Verify(notNull = "username,authorId")
	@Logs(operate = Operate.INSERT, module = "测试控制器", remark = "添加用户信息")
	public String createUser(HttpServletRequest request, String authorId)
			throws Exception {
		DataInfo dataInfo = AjaxUtils.parseJson(request);
		DataInfo addModel = userService.addModel(dataInfo, authorId);
		if (addModel.getCode().equals(CodeConstant.SUCCESS)) {
			return "xxxx";// 添加成功返回页面
		}
		return "xxx";// 添加失败返回页面
	}
	
	@RequestMapping("/editModelById")
	@Verify(notNull="authorId")
	@Logs(operate=Operate.UPDATE, module="测试控制器", remark="根据id更新对象信息")
	public String editModelById(HttpServletRequest request,String authorId) throws Exception{
		DataInfo dataInfo = AjaxUtils.parseJson(request);
		userService.editModelById(dataInfo, authorId);
		return "xxx";
	}
	
	@RequestMapping("/remoteModelById")
	@Verify(notNull="authorId")
	@Logs(operate=Operate.UPDATE, module="测试控制器", remark="根据id删除对象信息")
	public String remoteModelById(HttpServletRequest request,String authorId) throws Exception{
		DataInfo dataInfo = AjaxUtils.parseJson(request);
			userService.editModelById(dataInfo, authorId);
		return "xxx";
	}

	@RequestMapping("/editStatusById")
	@Verify(notNull="authorId")
	@Logs(operate=Operate.UPDATE, module="测试控制器", remark="根据id更新会员状态")
	public String editStatusById(HttpServletRequest request,String authorId) throws Exception{
		DataInfo dataInfo = AjaxUtils.parseJson(request);
			userService.remoteModelById(dataInfo, authorId);
		return "";
		}
	
	@RequestMapping("/findModelById")
	@Verify(notNull="id")
	@Logs(operate=Operate.UPDATE, module="测试控制器", remark="根据id查询会员信息")
	public void findModelById(HttpServletRequest request, HttpServletResponse response) throws Exception {
		DataInfo dataInfo = AjaxUtils.parseJson(request);
		userService.findModelById(dataInfo);
		AjaxUtils.outJson(response, dataInfo);
	}
	
	@RequestMapping("/findListByModel")
	@Verify(notNull="authorId")
	@Logs(operate=Operate.UPDATE, module="测试控制器", remark="根据实体获取列表数据")
	public String findListByModel(HttpServletRequest request) throws Exception{
		DataInfo dataInfo = AjaxUtils.parseJson(request);
		userService.findListByModel(dataInfo);
		return "";}
	
}
