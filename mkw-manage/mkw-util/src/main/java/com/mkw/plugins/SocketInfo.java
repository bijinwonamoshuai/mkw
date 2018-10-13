package com.mkw.plugins;

public class SocketInfo {
	// 接受者
	private String toUserId;
	// 发送者
	private String formUserId;
	// 消息内容	
	private String message;
	// 消息类型（1在线消息， 2离线消息）
	private Integer type;
	// 标题
	private String title;
	
	public SocketInfo(String toUserId, String formUserId, String title, String message, Integer type) {
		this.toUserId = toUserId;
		this.formUserId = formUserId;
		this.title = title;
		this.message = message;
		this.type = type;
	}
	
	public String getToUserId() {
		return toUserId;
	}
	public void setToUserId(String toUserId) {
		this.toUserId = toUserId;
	}
	public String getFormUserId() {
		return formUserId;
	}
	public void setFormUserId(String formUserId) {
		this.formUserId = formUserId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
