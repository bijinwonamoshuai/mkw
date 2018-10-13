package com.mkw.core.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mkw.annotation.Logs;
import com.mkw.annotation.Verify;
import com.mkw.constant.KeyConstant;
import com.mkw.constant.Operate;
import com.mkw.core.common.SuperController;
import com.mkw.core.entity.Account;
import com.mkw.core.entity.RoomGroup;
import com.mkw.core.service.FoodPackageService;
import com.mkw.core.service.RoomFoodService;
import com.mkw.core.service.RoomGroupService;
import com.mkw.plugins.DataInfo;
import com.mkw.plugins.PageInfo;
import com.mkw.util.AjaxUtils;

@RequestMapping("/roomGroup")
@Controller
public class RoomGroupController extends SuperController {
	@Resource
	private RoomGroupService roomGroupService;
	@Resource
	private RoomFoodService roomFoodService;
	@Resource
	private FoodPackageService foodPackageService;
	
	@RequestMapping("roomGroup-packages")
	@Logs(operate = Operate.SELECT, module = "菜品组控制器", remark = "获取添加菜品组列表页面")
	public String roomGroupPackages(HttpServletRequest request, HttpServletResponse response, String id) throws Exception {
		setAttribute(request, KeyConstant.merchant.SESSION_GROUP_ID, id);
		return "roomGroup/roomGroup-packages";
	}
	
	@RequestMapping("roomGroup-list")
	@Logs(operate = Operate.SELECT, module = "菜品组控制器", remark = "获取菜品组列表页面")
	public String roomGroupList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		return "roomGroup/roomGroup-list";
	}
	
	@RequestMapping("roomGroup-edit")
	@Logs(operate = Operate.SELECT, module = "菜品组控制器", remark = "获取菜品组编辑页面")
	public String roomGroupEdit(HttpServletRequest request, HttpServletResponse response, String id) throws Exception {
		if (StringUtils.isNotBlank(id)) {
			RoomGroup roomGroup = roomGroupService.select(id);
			request.setAttribute("roomGroupInfo", JSON.toJSONString(roomGroup));
		}
		return "roomGroup/roomGroup-edit";
	}
	
	@RequestMapping("add")
	@Verify(notNull="name,status")
	@Logs(operate = Operate.INSERT, module = "菜品组控制器", remark = "添加菜品组")
	public void add(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Account account = JSON.parseObject(getAttribute(request, KeyConstant.merchant.SESSION_ACCOUNT_INFO), Account.class);
		DataInfo dataInfo = AjaxUtils.parseJson(request);
		roomGroupService.addModel(dataInfo, account.getRoomId(), account.getId());
		AjaxUtils.outJson(response, dataInfo);
	}
	
	@RequestMapping("edit")
	@Verify(notNull="id,name,status")
	@Logs(operate = Operate.UPDATE, module = "菜品组控制器", remark = "更新菜品组")
	public void edit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Account account = JSON.parseObject(getAttribute(request, KeyConstant.merchant.SESSION_ACCOUNT_INFO), Account.class);
		DataInfo dataInfo = AjaxUtils.parseJson(request);
		roomGroupService.editModelById(dataInfo, account.getRoomId(), account.getId());
		AjaxUtils.outJson(response, dataInfo);
	}
	
	@RequestMapping("delete")
	@Verify(notNull="id")
	@Logs(operate = Operate.DELETE, module = "菜品组控制器", remark = "删除菜品组")
	public void delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Account account = JSON.parseObject(getAttribute(request, KeyConstant.merchant.SESSION_ACCOUNT_INFO), Account.class);
		DataInfo dataInfo = AjaxUtils.parseJson(request);
		roomGroupService.remoteModelById(dataInfo, account.getId());
		AjaxUtils.outJson(response, dataInfo);
	}
	
	@RequestMapping("list")
	@Logs(operate=Operate.SELECT, module="菜品组控制器", remark="获取分页数据")
	public void list(HttpServletRequest request, HttpServletResponse response, int page, int limit) throws Exception {   
		Account account = JSON.parseObject(getAttribute(request, KeyConstant.merchant.SESSION_ACCOUNT_INFO), Account.class);
    	JSONObject params = new JSONObject();
		params.put("name", request.getParameter("name"));
		params.put("roomId", account.getRoomId());
		params.put("status", "0");
		PageInfo pageInfo = new PageInfo(Integer.valueOf(page), Integer.valueOf(limit), params);
		pageInfo = roomGroupService.findListByPage(pageInfo);
		AjaxUtils.outJson(response, pageInfo);
	}
	
	@RequestMapping("add-list")
	@Logs(operate=Operate.SELECT, module="菜品组控制器", remark="获取分页数据")
	public void addList(HttpServletRequest request, HttpServletResponse response, int page, int limit) throws Exception {    	
    	JSONObject params = new JSONObject();
		params.put("groupId", getAttribute(request, KeyConstant.merchant.SESSION_GROUP_ID));
		PageInfo pageInfo = new PageInfo(Integer.valueOf(page), Integer.valueOf(limit), params);
		pageInfo = foodPackageService.findListByPage(pageInfo);
		AjaxUtils.outJson(response, pageInfo);
	}
	
	@RequestMapping("addRoomFood")
	@Verify(notNull="packageId")
	@Logs(operate = Operate.INSERT, module = "菜品组控制器", remark = "添加菜品组菜品")
	public void addRoomFood(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String groupId = getAttribute(request, KeyConstant.merchant.SESSION_GROUP_ID);
		DataInfo dataInfo = AjaxUtils.parseJson(request);
		roomFoodService.addModel(dataInfo, groupId, findAuthor(request));
		AjaxUtils.outJson(response, dataInfo);
	}
}
