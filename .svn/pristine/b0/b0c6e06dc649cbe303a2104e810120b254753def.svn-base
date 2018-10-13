package com.mkw.core.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mkw.annotation.Logs;
import com.mkw.annotation.Verify;
import com.mkw.constant.Operate;
import com.mkw.core.common.SuperController;
import com.mkw.core.entity.Account;
import com.mkw.core.service.AccountService;
import com.mkw.core.service.RoleService;
import com.mkw.core.service.RoomService;
import com.mkw.plugins.DataInfo;
import com.mkw.plugins.PageInfo;
import com.mkw.util.AjaxUtils;

@Controller
@RequestMapping("account")
public class AccountController extends SuperController {
	@Resource
	private RoomService roomService;
	@Resource
	private RoleService roleService;
	@Resource
	private AccountService accountService;
	
	@RequestMapping("account-list")
	@Logs(operate = Operate.SELECT, module = "管理员控制器", remark = "获取管理员列表页面")
	public String accountList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		return "account/account-list";
	}
	
	@RequestMapping("account-edit")
	@Logs(operate = Operate.SELECT, module = "管理员控制器", remark = "获取管理员编辑页面")
	public String accountEdit(HttpServletRequest request, HttpServletResponse response, String id) throws Exception {
		JSONArray allRoom = roomService.findAllRoom();
		request.setAttribute("allRoom", allRoom.toJSONString());
		JSONArray allRole = roleService.findAllRoleByStatus(1);
		request.setAttribute("allRole", allRole.toJSONString());
		if (StringUtils.isNotBlank(id)) {
			Account account = accountService.select(id);
			account.setLoginPass(null);
			request.setAttribute("accountInfo", JSON.toJSONString(account));
		}
		return "account/account-edit";
	}
	
	@RequestMapping("add")
	@Logs(operate = Operate.INSERT, module = "管理员控制器", remark = "添加管理员")
	public void add(HttpServletRequest request, HttpServletResponse response) throws Exception {
		DataInfo dataInfo = AjaxUtils.parseJson(request);
		accountService.addModel(dataInfo, findAuthor(request));
		AjaxUtils.outJson(response, dataInfo);
	}
	
	@RequestMapping("edit")
	@Verify(notNull="id")
	@Logs(operate = Operate.UPDATE, module = "管理员控制器", remark = "更新管理员")
	public void edit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		DataInfo dataInfo = AjaxUtils.parseJson(request);
		accountService.editModelById(dataInfo, findAuthor(request));
		AjaxUtils.outJson(response, dataInfo);
	}
	
	@RequestMapping("list")
	@Logs(operate=Operate.SELECT, module="管理员控制器", remark="获取分页数据")
	public void list(HttpServletRequest request, HttpServletResponse response, int page, int limit) throws Exception {    	
    	JSONObject params = new JSONObject();
		params.put("name", request.getParameter("name"));
		params.put("mobile", request.getParameter("mobile"));
		params.put("roomName", request.getParameter("roomName"));
		
		PageInfo pageInfo = new PageInfo(Integer.valueOf(page), Integer.valueOf(limit), params);
		pageInfo = accountService.findListByPage(pageInfo);
		AjaxUtils.outJson(response, pageInfo);
	}
}
