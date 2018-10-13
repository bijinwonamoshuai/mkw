package com.mkw.core.aop;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.mkw.annotation.Logs;
import com.mkw.core.entity.Log;
import com.mkw.core.service.LogService;
import com.mkw.util.IpUtils;

@Aspect
@Component
public class LogPointCut {
	private static Logger logger = LoggerFactory.getLogger(LogPointCut.class);
	@Resource
	private LogService logService;
	
	/**
     * 定义控制层切入点
     */
    @Pointcut(value="execution(public * com.mkw.core.web.*.*(..)) && @annotation(logs)")
    public void doPointCut(Logs logs){
    	
    }
    
    /**
     * 定义环绕方法
     */
    @Around(value = "doPointCut(logs)")
    public Object doAround(ProceedingJoinPoint joinPoint, Logs logs) throws Throwable {
        Object[] args=joinPoint.getArgs();
        HttpServletRequest request = null;
        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof HttpServletRequest) {
                request = (HttpServletRequest) args[i];
                break;
            }
        }

    	String authorId = null;
        if (null != request) {        	
        	// 如果是退出登录设置session失效时，将无法获取
        	// 拦截器优先级高于aop
        	if(null != request.getSession(false)) {
        		authorId = (String) request.getSession().getAttribute("accountId");
			}
		}
        
        Object data = null;
        try {
        	// 进入控制器
        	data = joinPoint.proceed();
		} catch (Exception e) {
			logger.error("service error", e);
		}
		
		Log log = new Log();
		log.setStatus(2);
		log.setModule(logs.module());
		log.setOperate(logs.operate().getOperate());
        log.setRemark(logs.remark());
        log.setUrl(request.getRequestURI());
        log.setParams(getDataInfo(request));
        log.setIp(IpUtils.getIpAddr(request));
        logService.insert(log, authorId);
		
		return data;
    }
    
    private String getDataInfo(HttpServletRequest request) throws Exception {
		StringBuffer sb = new StringBuffer();
		InputStreamReader isr = new InputStreamReader(request.getInputStream());
		BufferedReader br = new BufferedReader(isr);
		String s = "";
		while ((s = br.readLine()) != null) {
			sb.append(s);
		}
		isr = null;
		br = null;
		return sb.toString();
	}
}
