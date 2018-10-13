package com.mkw.core.common;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mkw.constant.KeyConstant;

public class SuperController {
	protected static Logger log = LoggerFactory.getLogger(SuperController.class);
	
	/**
	 * 设置缓存
	 * @param request
	 * @param key
	 * @param value
	 * @throws Exception
	 */
	public void setAttribute(HttpServletRequest request, String key, String value) throws Exception {
		request.getSession().setAttribute(key, value);
	}
	
	/**
	 * 获取缓存
	 * @param request
	 * @param key
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	public String getAttribute(HttpServletRequest request, String key) throws Exception {
		return (String) request.getSession().getAttribute(key);
	}
	
	/**
	 * @Description: 获取登陆者id
	 * @author xiaojiayi 
	 * @date 2018年9月7日 下午6:18:03
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String findAuthor(HttpServletRequest request) throws Exception {
		return (String) request.getSession().getAttribute(KeyConstant.colligate.SESSION_ACCOUNT_ID);
	}
}
