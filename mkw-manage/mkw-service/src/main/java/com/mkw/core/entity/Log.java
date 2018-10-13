package com.mkw.core.entity;

import com.mkw.core.common.SuperEntity;

/**
 * <p>
 * 日志表
 * </p>
 *
 * @author xiaojiayi
 * @since 2018-09-06
 */
public class Log extends SuperEntity {
    private static final long serialVersionUID = 1L;
    /**
     * 模块
     */
	private String module;
    /**
     * 操作方式
     */
	private String operate;
    /**
     * ip地址
     */
	private String ip;
    /**
     * 链接
     */
	private String url;
    /**
     * 参数
     */
	private String params;
	/**
     * 模块
     */
	public String getModule() {
		return module;
	}
	/**
     * 模块
     */
	public void setModule(String module) {
		this.module = module;
	}
	/**
     * 操作方式
     */
	public String getOperate() {
		return operate;
	}
	/**
     * 操作方式
     */
	public void setOperate(String operate) {
		this.operate = operate;
	}
	/**
     * ip地址
     */
	public String getIp() {
		return ip;
	}
	/**
     * ip地址
     */
	public void setIp(String ip) {
		this.ip = ip;
	}
	/**
     * 链接
     */
	public String getUrl() {
		return url;
	}
	/**
     * 链接
     */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
     * 参数
     */
	public String getParams() {
		return params;
	}
	/**
     * 参数
     */
	public void setParams(String params) {
		this.params = params;
	}
}
