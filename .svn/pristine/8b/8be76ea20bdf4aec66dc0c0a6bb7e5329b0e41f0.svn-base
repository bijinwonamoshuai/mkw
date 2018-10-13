package com.mkw.core.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mkw.core.common.SuperController;

@Controller
@RequestMapping("/food")
public class FoodController extends SuperController{
	
	/*@RequestMapping("/add")
	@Verify(notNull = "name")
	@Logs(operate = Operate.INSERT, module = "测试控制器", remark = "添加菜品信息")
	public String createFoodItem(HttpServletRequest request, String authorId)
			throws Exception {
		DataInfo dataInfo = AjaxUtils.parseJson(request);
		DataInfo addModel = foodItemService.addModel(dataInfo, authorId);
		if (addModel.getCode().equals(CodeConstant.SUCCESS)) {
			return "xxxx";// 添加成功返回页面
		}
		return "xxx";// 添加失败返回页面
	}*/
	
	/*@RequestMapping("/editModelById")
	@Verify(notNull="authorId")
	@Logs(operate=Operate.UPDATE, module="测试控制器", remark="根据id更新菜品信息")
	public String editModelById(HttpServletRequest request,String authorId) throws Exception{
		DataInfo dataInfo = AjaxUtils.parseJson(request);
		foodItemService.editModelById(dataInfo, authorId);
		return "xxx";
	}
	
	@RequestMapping("/remoteModelById")
	@Verify(notNull="authorId")
	@Logs(operate=Operate.UPDATE, module="测试控制器", remark="根据id删除菜品信息")
	public String remoteModelById(HttpServletRequest request,String authorId) throws Exception{
		DataInfo dataInfo = AjaxUtils.parseJson(request);
		foodItemService.editModelById(dataInfo, authorId);
		return "xxx";
	}

	@RequestMapping("/editStatusById")
	@Verify(notNull="authorId")
	@Logs(operate=Operate.UPDATE, module="测试控制器", remark="根据id更新菜品当前状态")
	public String editStatusById(HttpServletRequest request,String authorId) throws Exception{
		DataInfo dataInfo = AjaxUtils.parseJson(request);
		foodItemService.editStatusById(dataInfo, authorId);
		return "";
		}
	
	@RequestMapping("/findModelById")
	@Verify(notNull="id")
	@Logs(operate=Operate.UPDATE, module="测试控制器", remark="根据id查询对象信息")
	public String findModelById(HttpServletRequest request) throws Exception{
		DataInfo dataInfo = AjaxUtils.parseJson(request);
		foodItemService.findModelById(dataInfo);
		return "";
		}
	
	@RequestMapping("/findListByModel")
	@Verify(notNull="authorId")
	@Logs(operate=Operate.UPDATE, module="测试控制器", remark="根据实体获取列表数据")
	public String findListByModel(HttpServletRequest request) throws Exception{
		DataInfo dataInfo = AjaxUtils.parseJson(request);
		foodItemService.findListByModel(dataInfo);
		return "";
		}
	
	@RequestMapping("food-list")
	@Logs(operate = Operate.SELECT, module = "菜品控制器", remark = "获取菜品列表页面")
	public String foodList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "food/food-list";
	}
	@RequestMapping("food-edit")
	@Logs(operate = Operate.SELECT, module = "菜品控制器", remark = "获取菜品编辑页面")
	public String foodEdit(HttpServletRequest request, HttpServletResponse response, String id) throws Exception {
		if (StringUtils.isNotBlank(id)) {
			FoodItem foodItem = foodItemService.select(id);
			request.setAttribute("foodItem", JSON.toJSONString(foodItem));
		}
		return "food/food-edit";
	}
	
	@RequestMapping("food-add")
	@Logs(operate = Operate.SELECT, module = "菜品控制器", remark = "获取菜品编辑页面")
	public String foodAdd(HttpServletRequest request, HttpServletResponse response, String id) throws Exception {
		if (StringUtils.isNotBlank(id)) {
			FoodItem foodItem = foodItemService.select(id);
			request.setAttribute("foodItem", JSON.toJSONString(foodItem));
		}
		return "food/food-add";
	}
	@RequestMapping("add")
	@Verify(notNull="name")
	@Logs(operate = Operate.INSERT, module = "菜品控制器", remark = "添加菜品")
	public void add(HttpServletRequest request, HttpServletResponse response) throws Exception {
		DataInfo dataInfo = AjaxUtils.parseJson(request);
		foodItemService.addModel(dataInfo, findAuthor(request));
		AjaxUtils.outJson(response, dataInfo);
	}
	
	@RequestMapping("edit")
	@Verify(notNull="id,name")
	@Logs(operate = Operate.UPDATE, module = "菜品控制器", remark = "更新菜品")
	public void edit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		DataInfo dataInfo = AjaxUtils.parseJson(request);
		foodItemService.editModelById(dataInfo, findAuthor(request));
		AjaxUtils.outJson(response, dataInfo);
	}
	
	@RequestMapping("list")
	@Logs(operate=Operate.SELECT, module="菜品控制器", remark="获取菜品分页数据")
	public void list(HttpServletRequest request, HttpServletResponse response, Integer page, Integer limit) throws Exception {    	
		JSONObject params = new JSONObject();
		params.put("name", request.getParameter("name"));
		params.put("image", request.getParameter("image"));
		params.put("content", request.getParameter("content"));
		params.put("foodGroup", request.getParameter("foodGroup"));
		params.put("merchantId", request.getParameter("merchantId"));
		params.put("price", request.getParameter("price"));
		params.put("preferencialPrice", request.getParameter("preferencialPrice"));
		params.put("kuCun", request.getParameter("kuCun"));
		params.put("isSale", request.getParameter("isSale"));
		PageInfo pageInfo = new PageInfo(Integer.valueOf(page), Integer.valueOf(limit), params);
		pageInfo = foodItemService.findListByPage(pageInfo);
		AjaxUtils.outJson(response, pageInfo);
	}*/
}
