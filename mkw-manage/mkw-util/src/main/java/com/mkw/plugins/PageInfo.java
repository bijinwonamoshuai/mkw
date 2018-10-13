package com.mkw.plugins;

import com.alibaba.fastjson.JSONObject;

//分页
public class PageInfo {
	private int page;//当前页
	private int limit;//每页数量
	private JSONObject params;//请求参数
	private int code = 0;//默认成功
	private String msg = "successfully";//默认成功
	private Object data;//响应数据
	private int count;//响应总数
	private int index;//查询下标
	
	public PageInfo(int page, int limit, JSONObject params) {
		super();
		this.page = page < 1 ? 1 : page;
		this.limit = limit < 10 ? 10 : limit;
		this.params = params;
		this.index = (this.page - 1) * this.limit;
	}
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public JSONObject getParams() {
		return params;
	}
	public void setParams(JSONObject params) {
		this.params = params;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}
