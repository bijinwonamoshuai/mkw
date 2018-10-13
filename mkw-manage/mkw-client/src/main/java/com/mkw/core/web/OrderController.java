package com.mkw.core.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mkw.annotation.Logs;
import com.mkw.constant.Operate;
import com.mkw.core.entity.OrderItemDetail;
import com.mkw.core.entity.Room;
import com.mkw.core.service.OrderItemDetailService;
import com.mkw.core.service.OrderItemService;
import com.mkw.plugins.PageInfo;
import com.mkw.util.AjaxUtils;
import com.mkw.util.ExcelUtil;

@Controller
@RequestMapping("order")
public class OrderController {
	@Autowired
	private OrderItemService orderItemService;
	@Autowired
	private OrderItemDetailService orderItemDetailService;
	
	
	@RequestMapping("order-list")
	@Logs(operate = Operate.SELECT, module = "订单控制器", remark = "获取订单信息")
	public String orderList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "order/order-list";
	}
	
	@RequestMapping("order-info")
	@Logs(operate = Operate.SELECT, module = "订单控制器", remark = "获取订单信息")
	public String orderinfo(HttpServletRequest request, HttpServletResponse response,String id) throws Exception {
		if(StringUtils.isNoneBlank(id)){
			OrderItemDetail orderItemDetail=new OrderItemDetail();
			orderItemDetail.setOrderId(id);
			List<OrderItemDetail> selectList = orderItemDetailService.selectList(orderItemDetail);
			request.setAttribute("selectList", selectList);
		}
		return "order/order-info";
	}
	

	@RequestMapping("info-list")
	@Logs(operate = Operate.SELECT, module = "订单控制器", remark = "获取订单详情列表信息")
	public void getOrderItemInfoList(HttpServletRequest request, HttpServletResponse response,Integer page, Integer limit,String id) throws Exception {
		JSONObject params = new JSONObject();
		//订单编号，手机号，微信账户，金额，下单时间，订单状态（已完成，已支付，已取消）
		params.put("id", request.getParameter("id"));
		params.put("foodId", request.getParameter("food_id"));
		params.put("originPrice", request.getParameter("origin_price"));
		params.put("code", request.getParameter("code"));
		params.put("name", request.getParameter("name"));
		params.put("foodType", request.getParameter("food_type"));
		
		PageInfo pageInfo = new PageInfo(Integer.valueOf(page), Integer.valueOf(limit), params);
		pageInfo = orderItemDetailService.findListByPage(pageInfo);
		AjaxUtils.outJson(response, pageInfo);
	}
	
	@RequestMapping("list")
	@Logs(operate = Operate.SELECT, module = "订单控制器", remark = "获取订单列表信息")
	public void getOrderItemInfo(HttpServletRequest request, HttpServletResponse response,Integer page, Integer limit ) throws Exception {
		JSONObject params = new JSONObject();
		//订单编号，手机号，微信账户，金额，下单时间，订单状态（已完成，已支付，已取消）
		params.put("id", request.getParameter("id"));
		params.put("phone", request.getParameter("phone"));
		params.put("wechatId", request.getParameter("wechat_id"));
		params.put("allPrice", request.getParameter("all_price"));
		params.put("payTime", request.getParameter("pay_time"));
		params.put("orderStatus", request.getParameter("order_status"));
		PageInfo pageInfo = new PageInfo(Integer.valueOf(page), Integer.valueOf(limit), params);
		pageInfo = orderItemService.findListByPage(pageInfo);
		AjaxUtils.outJson(response, pageInfo);
	}
	
	@RequestMapping("/exportData")
	@Logs(operate = Operate.SELECT, module = "订单控制器", remark = "导出列表")
	public void exportData(HttpServletRequest request){
		ExcelUtil.exportToExcel(null, null,null, "");
	}
}
