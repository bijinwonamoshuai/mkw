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
import com.mkw.constant.Operate;
import com.mkw.core.common.SuperController;
import com.mkw.core.entity.Room;
import com.mkw.core.service.RoomService;
import com.mkw.plugins.DataInfo;
import com.mkw.plugins.PageInfo;
import com.mkw.util.AjaxUtils;

@Controller
@RequestMapping("room")
public class RoomController extends SuperController {
	@Resource
	private RoomService roomService;
	
	@RequestMapping("room-list")
	@Logs(operate = Operate.SELECT, module = "门店控制器", remark = "获取门店列表页面")
	public String roomList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		return "room/room-list";
	}
	
	@RequestMapping("room-edit")
	@Logs(operate = Operate.SELECT, module = "门店控制器", remark = "获取门店编辑页面")
	public String roomEdit(HttpServletRequest request, HttpServletResponse response, String id) throws Exception {
		if (StringUtils.isNotBlank(id)) {
			Room room = roomService.select(id);
			request.setAttribute("roomInfo", JSON.toJSONString(room));
		}
		return "room/room-edit";
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
	@Verify(notNull="id,name")
	@Logs(operate = Operate.UPDATE, module = "门店控制器", remark = "更新门店")
	public void edit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		DataInfo dataInfo = AjaxUtils.parseJson(request);
		roomService.editModelById(dataInfo, findAuthor(request));
		AjaxUtils.outJson(response, dataInfo);
	}
	
	@RequestMapping("list")
	@Logs(operate=Operate.SELECT, module="门店控制器", remark="获取分页数据")
	public void list(HttpServletRequest request, HttpServletResponse response, int page, int limit) throws Exception {    	
    	JSONObject params = new JSONObject();
		params.put("name", request.getParameter("name"));
		PageInfo pageInfo = new PageInfo(Integer.valueOf(page), Integer.valueOf(limit), params);
		pageInfo = roomService.findListByPage(pageInfo);
		AjaxUtils.outJson(response, pageInfo);
	}
	
	
}
