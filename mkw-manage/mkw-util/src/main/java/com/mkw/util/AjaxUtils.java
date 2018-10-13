package com.mkw.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mkw.plugins.DataInfo;

public class AjaxUtils {
	protected static Logger logger = LoggerFactory.getLogger(AjaxUtils.class);

	/**
	 * 输出json到客户端
	 * 
	 * @param response
	 * @param object
	 */
	public static void outJson(HttpServletResponse response, Object dataInfo) {
		if (null != dataInfo) {
			try {
				String json = JSON.toJSONString(dataInfo);
				logger.info("outJSONObjectToClient is :" + json);
				response.setHeader("Access-Control-Allow-Origin", "*");
				response.setContentType("application/json");
				response.setCharacterEncoding("utf-8");
				response.getWriter().print(json);
				response.getWriter().flush();
				response.getWriter().close();
			} catch (Exception e) {
				logger.info("outJson", e);
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 输出json到客户端
	 * 
	 * @param response
	 * @param object
	 */
	public static void outString(HttpServletResponse response, Object object) {
		if (null != object) {
			try {
				String json = JSON.toJSONString(object);
				logger.info("outJSONObjectToClient is :" + json);
				response.setHeader("Access-Control-Allow-Origin", "*");
				response.setContentType("text/html");
				response.setCharacterEncoding("utf-8");
				response.getWriter().print(json);
				response.getWriter().flush();
				response.getWriter().close();
			} catch (Exception e) {
				logger.info("outString", e);
			}
		}
	}

	/**
	 * 获取json
	 * @param request
	 */
	public static DataInfo parseJson(HttpServletRequest request) {
		String s = null;
		JSONObject jsonObject = new JSONObject();
		try {
			if (request.getMethod().equals("GET")) {//GET
				if (null != request.getQueryString()) {
					s = URLDecoder.decode(request.getQueryString(), "utf-8");
		            if (null != s) {
						String[] params = s.split("&");
						if (0 < params.length) {
							jsonObject = new JSONObject();
							for (String string : params) {
								String[] str = string.split("=");
								if (0 < str.length) {
									jsonObject.put(str[0], str[1]);
								}
							}
						}
					}
				}
	        } else {// POST...
	    		StringBuilder builder = new StringBuilder();
				BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), "utf-8"));
				while ((s = br.readLine()) != null) {
					builder.append(s);
				}
				if (0 < builder.length()) {
					jsonObject = JSONObject.parseObject(builder.toString());
				}
				builder = null;
	        }
		} catch (Exception e) {
			logger.error("parseJson", e);
		}
		
		//防注入
		DataInfo dataInfo = new DataInfo();
		if(null != jsonObject) {
			JSONObject json = new JSONObject();
			Set<Entry<String, Object>> set = jsonObject.getJSONObject("requestData").entrySet();
			for (Entry<String, Object> entry : set) {
				String value = String.valueOf(entry.getValue());
				if (StringUtils.isNotBlank(value) && !"null".equals(value)) {
					json.put(entry.getKey(), XssShieldUtils.stripXss(value));
				}
			}
			dataInfo.setRequestData(json);
		}
		
		logger.info("parseJson is :" + JSON.toJSONString(dataInfo));
		return dataInfo;
	}
}
