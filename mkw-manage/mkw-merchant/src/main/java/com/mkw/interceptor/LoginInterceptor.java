package com.mkw.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSON;
import com.mkw.constant.KeyConstant;
import com.mkw.plugins.DataInfo;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String sId = request.getSession().getId();
		String sessionId = (String) request.getSession().getAttribute(KeyConstant.merchant.SESSION_ID);
		String accountId = (String) request.getSession().getAttribute(KeyConstant.merchant.SESSION_ACCOUNT_ID);
		
		//验证是否登录超时
		if (StringUtils.isBlank(accountId) || StringUtils.isBlank(sessionId) || !sId.equals(sessionId)) {
			// 设置session失效
			HttpSession session = request.getSession();
			session.invalidate();
			
			if (isAjax(request)){
				//ajax请求时
	            outMsg(response, "555555");
	        }else{
	            //非ajax请求时
				request.getRequestDispatcher("/welcome.jsp").forward(request, response);
	        }
			return false;
		}
		
		return true;
	}
	
	private void outMsg(HttpServletResponse response, String code) throws Exception {
		DataInfo dataInfo = new DataInfo();
        dataInfo.setSuccess(true);
        dataInfo.setCode(code);
        
        response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		response.getWriter().print(JSON.toJSONString(dataInfo));
		response.getWriter().flush();
		response.getWriter().close();
	}
	
	/**
	 * 是否为ajax请求
	 * @param request
	 * @return
	 */
	private boolean isAjax(HttpServletRequest request) {
		return request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest");
	}
}
