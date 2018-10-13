package com.mkw.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

public class CookieUtil {
	
	/**
     * 添加cookie
     * 
     * @param response
     * @param name
     * @param value
     * @param maxAge
     */
    public static void addCookie(HttpServletResponse response, String key, String value) {
        if (StringUtils.isNotBlank(key) && StringUtils.isNotBlank(value)) {
        	Cookie cookie = new Cookie(key, value);
            cookie.setPath("/");
            cookie.setMaxAge(60 * 60 * 24 * 7);
            response.addCookie(cookie);
		}
    }

    /**
     * 删除cookie
     * 
     * @param response
     * @param name
     */
    public static void removeCookie(HttpServletResponse response, String key) {
        if (StringUtils.isNotBlank(key)) {
        	Cookie uid = new Cookie(key, null);
            uid.setPath("/");
            uid.setMaxAge(0);
            response.addCookie(uid);
		}
    }

    /**
     * 获取cookie值
     * 
     * @param request
     * @return
     */
    public static String getCookie(HttpServletRequest request, String key) {
        if (StringUtils.isNotBlank(key)) {
        	Cookie cookies[] = request.getCookies();
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(key)) {
                    return cookie.getValue();
                }
            }
		}
        return null;
    }
}
