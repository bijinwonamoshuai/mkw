package com.mkw.core.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mkw.annotation.Logs;
import com.mkw.annotation.Verify;
import com.mkw.constant.KeyConstant;
import com.mkw.constant.Operate;
import com.mkw.core.common.SuperController;
import com.mkw.core.entity.Room;
import com.mkw.core.service.RoomGroupService;
import com.mkw.core.service.RoomService;
import com.mkw.plugins.DataInfo;
import com.mkw.plugins.PageInfo;
import com.mkw.util.AjaxUtils;


@Controller
@RequestMapping("/room")
public class RoomController extends SuperController{
	@Autowired
	private RoomService roomService;
	
	
	@RequestMapping("room-list")
	@Logs(operate = Operate.SELECT, module = "门店控制器", remark = "获取门店信息")
	public String foodList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "room/room-list";
	}
	@RequestMapping("room-edit")
	@Logs(operate = Operate.SELECT, module = "门店控制器", remark = "获取门店编辑页面")
	public String foodEdit(HttpServletRequest request, HttpServletResponse response, String id) throws Exception {
		if (StringUtils.isNotBlank(id)) {
			Room roomItem = roomService.select(id);
			request.setAttribute("roomItem", JSON.toJSONString(roomItem));
		}
		return "room/room-edit";
	}
	
	@RequestMapping("room-add")
	@Logs(operate = Operate.SELECT, module = "门店控制器", remark = "门店添加")
	public String foodAdd(HttpServletRequest request, HttpServletResponse response, String id) throws Exception {
		if (StringUtils.isNotBlank(id)) {
			Room roomItem = roomService.select(id);
			request.setAttribute("roomInfo", JSON.toJSONString(roomItem));
		}
		return "room/room-add";
	}
	
	@RequestMapping("add")
	@Verify(notNull="name")
	@Logs(operate = Operate.INSERT, module = "门店控制器", remark = "添加门店")
	public void add(HttpServletRequest request, HttpServletResponse response) throws Exception {
		DataInfo dataInfo = AjaxUtils.parseJson(request);
		roomService.addModel(dataInfo, findAuthor(request));
		AjaxUtils.outJson(response, dataInfo);
	}
	
	@RequestMapping("edit")
	@Verify(notNull="roomId")
	@Logs(operate = Operate.UPDATE, module = "门店控制器", remark = "进行门店编辑")
	public void edit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		DataInfo dataInfo = AjaxUtils.parseJson(request);
		roomService.editModelById(dataInfo, findAuthor(request));
		AjaxUtils.outJson(response, dataInfo);
	}
	
	@RequestMapping("list")
	@Logs(operate = Operate.SELECT, module = "门店控制器", remark = "门店信息")
	public void getRoomInfo(HttpServletRequest request, HttpServletResponse response,String roomId,Integer page, Integer limit) throws Exception {
		JSONObject params = new JSONObject();
		 //后期根据个人登录下的门店id查询个人对应的门店信息
		String attribute = getAttribute(request,KeyConstant.colligate.SESSION_ACCOUNT_INFO);
		JSONObject jsonObject = JSON.parseObject(attribute);
	    String roomid = (String)jsonObject.get("roomId");
		 
		params.put("name", request.getParameter("name"));
		params.put("imageUrl", request.getParameter("imageUrl"));
		params.put("address", request.getParameter("address"));
		params.put("roomId", roomid);
		PageInfo pageInfo = new PageInfo(Integer.valueOf(page), Integer.valueOf(limit), params);
		pageInfo = roomService.findListByPage(pageInfo);
		AjaxUtils.outJson(response, pageInfo);
	}
}
