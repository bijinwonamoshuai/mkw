package com.mkw.interceptor;

import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.mkw.core.property.ConfigProperty;

public class BaseInterceptor extends HandlerInterceptorAdapter {

	@Resource
	private ConfigProperty configProperty;

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		request.setAttribute("path", configProperty.getDomain());
		request.setAttribute("basePath", configProperty.getDomainMerchantName());
		request.setAttribute("random", new Random().nextInt());
		return true;
	}
}
