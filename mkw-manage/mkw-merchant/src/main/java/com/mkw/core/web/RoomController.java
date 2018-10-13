package com.mkw.core.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.mkw.annotation.Logs;
import com.mkw.annotation.Verify;
import com.mkw.constant.KeyConstant;
import com.mkw.constant.Operate;
import com.mkw.core.common.SuperController;
import com.mkw.core.entity.Account;
import com.mkw.core.entity.Room;
import com.mkw.core.service.RoomService;
import com.mkw.plugins.DataInfo;
import com.mkw.util.AjaxUtils;

@Controller
@RequestMapping("/room")
public class RoomController extends SuperController {
	@Autowired
	private RoomService roomService;
	
	@RequestMapping("room-edit")
	@Logs(operate = Operate.SELECT, module = "门店控制器", remark = "获取门店编辑页面")
	public String roomEdit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Account account = JSON.parseObject(getAttribute(request, KeyConstant.merchant.SESSION_ACCOUNT_INFO), Account.class);
		Room room = roomService.select(account.getRoomId());
		request.setAttribute("roomInfo", JSON.toJSONString(room));
		
		return "room/room-edit";
	}
	
	@RequestMapping("edit")
	@Verify(notNull="id,name")
	@Logs(operate = Operate.UPDATE, module = "门店控制器", remark = "更新门店")
	public void edit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		DataInfo dataInfo = AjaxUtils.parseJson(request);
		roomService.editModelById(dataInfo, findAuthor(request));
		AjaxUtils.outJson(response, dataInfo);
	}
}
