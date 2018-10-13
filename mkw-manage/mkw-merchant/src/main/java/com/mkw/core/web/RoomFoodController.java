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
import com.mkw.core.entity.RoomFood;
import com.mkw.core.service.RoomFoodService;
import com.mkw.plugins.DataInfo;
import com.mkw.plugins.PageInfo;
import com.mkw.util.AjaxUtils;

@RequestMapping("/roomFood")
@Controller
public class RoomFoodController extends SuperController {
	@Resource
	private RoomFoodService roomFoodService;
	
	@RequestMapping("roomFood-list")
	@Logs(operate = Operate.SELECT, module = "菜品控制器", remark = "获取菜品列表页面")
	public String roomFoodList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		return "roomFood/roomFood-list";
	}
	
	@RequestMapping("roomFood-edit")
	@Logs(operate = Operate.SELECT, module = "菜品控制器", remark = "获取菜品编辑页面")
	public String roomFoodEdit(HttpServletRequest request, HttpServletResponse response, String id) throws Exception {
		if (StringUtils.isNotBlank(id)) {
			RoomFood roomFood = roomFoodService.select(id);
			request.setAttribute("roomFoodInfo", JSON.toJSONString(roomFood));
		}
		return "roomFood/roomFood-edit";
	}
	
	@RequestMapping("edit")
	@Verify(notNull="id,showName,showPrice,showStock")
	@Logs(operate = Operate.UPDATE, module = "菜品组控制器", remark = "更新菜品")
	public void edit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		DataInfo dataInfo = AjaxUtils.parseJson(request);
		roomFoodService.editModelById(dataInfo, findAuthor(request));
		AjaxUtils.outJson(response, dataInfo);
	}
	
	@RequestMapping("delete")
	@Verify(notNull="id")
	@Logs(operate = Operate.UPDATE, module = "菜品组控制器", remark = "删除菜品")
	public void delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		DataInfo dataInfo = AjaxUtils.parseJson(request);
		roomFoodService.remoteModelById(dataInfo, findAuthor(request));
		AjaxUtils.outJson(response, dataInfo);
	}
	
	@RequestMapping("change")
	@Verify(notNull="id,status")
	@Logs(operate = Operate.UPDATE, module = "菜品组控制器", remark = "更新菜品状态")
	public void change(HttpServletRequest request, HttpServletResponse response) throws Exception {
		DataInfo dataInfo = AjaxUtils.parseJson(request);
		roomFoodService.editStatusById(dataInfo, findAuthor(request));
		AjaxUtils.outJson(response, dataInfo);
	}
	
	@RequestMapping("list")
	@Logs(operate=Operate.SELECT, module="菜品控制器", remark="获取分页数据")
	public void list(HttpServletRequest request, HttpServletResponse response, int page, int limit) throws Exception { 
		Account account = JSON.parseObject(getAttribute(request, KeyConstant.merchant.SESSION_ACCOUNT_INFO), Account.class);
    	JSONObject params = new JSONObject();
		params.put("roomId", account.getRoomId());
		params.put("status", "0");
		params.put("foodName", request.getParameter("foodName"));
		params.put("groupName", request.getParameter("groupName"));
		params.put("foodStatus", request.getParameter("foodStatus"));
		PageInfo pageInfo = new PageInfo(Integer.valueOf(page), Integer.valueOf(limit), params);
		pageInfo = roomFoodService.findListByPage(pageInfo);
		AjaxUtils.outJson(response, pageInfo);
	}
}
