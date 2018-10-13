package com.mkw.interceptor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mkw.annotation.Verify;
import com.mkw.plugins.DataInfo;
import com.mkw.util.TimeUtils;

public class VerifyInterceptor extends HandlerInterceptorAdapter {
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        Verify verify = method.getAnnotation(Verify.class);
        if (null != verify) {
        	DataInfo dataInfo = getDataInfo(request);
        	
        	String notNull = verify.notNull();
        	if (StringUtils.isNotBlank(notNull)) {
        		String[] split = notNull.split(",");
				for (String string : split) {
					if (!checkNotNull(dataInfo, string)) {
						outJson(response, dataInfo, "参数校验错误：" + string);
						return false;
					}
				}
			}
        	
        	String dateFormat = verify.dateFormat();
        	if (StringUtils.isNotBlank(dateFormat)) {
        		String[] split = dateFormat.split(",");
        		for (String string : split) {
        			if (!checkDateFormat(dataInfo, string)) {
						outJson(response, dataInfo, "参数校验错误：" + string);
						return false;
					}
        		}
        	}
		}
		return true;
	}
	
	private boolean checkDateFormat(DataInfo dataInfo, String key) {
		if (null != dataInfo && null != dataInfo.getRequestData()) {
			String string = dataInfo.getRequestData().getString(key);
			if (null != string) {
				return TimeUtils.isDateTimeFormat(string);
			}
		}
		return false;
	}
	
	private boolean checkNotNull(DataInfo dataInfo, String key) {
		if (null != dataInfo && null != dataInfo.getRequestData()) {
			if (null != dataInfo.getRequestData().getString(key)) {
				return true;
			}
		}
		return false;
	}
	
	private DataInfo getDataInfo(HttpServletRequest request) throws Exception {
		StringBuffer sb = new StringBuffer();
		InputStreamReader isr = new InputStreamReader(request.getInputStream());
		BufferedReader br = new BufferedReader(isr);
		String s = "";
		while ((s = br.readLine()) != null) {
			sb.append(s);
		}
		isr = null;
		br = null;
		JSONObject jsonObject = JSONObject.parseObject(sb.toString());
		return null == jsonObject ? null : jsonObject.toJavaObject(DataInfo.class);
	}
	
	/**
	 * 输出json到客户端
	 * 
	 * @param response
	 * @param object
	 * @throws Exception 
	 */
	public static void outJson(HttpServletResponse response, DataInfo dataInfo, String msg) throws Exception {
		if (null != msg) {
			if (null == dataInfo) {
				dataInfo = new DataInfo();
			}
			dataInfo.setSuccess(true);
			dataInfo.setMsg(msg);
			dataInfo.setCode("444444");
			response.setHeader("Access-Control-Allow-Origin", "*");
			response.setContentType("application/json");
			response.setCharacterEncoding("utf-8");
			response.getWriter().print(JSON.toJSONString(dataInfo));
			response.getWriter().flush();
			response.getWriter().close();
		}
	}
}
