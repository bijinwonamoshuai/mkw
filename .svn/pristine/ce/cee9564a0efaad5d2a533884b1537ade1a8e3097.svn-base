package com.mkw.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.mkw.constant.Operate;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Logs {
	/**
	 * 操作
	 * @return
	 */
	public Operate operate() default Operate.SELECT;
	
	/**
	 * 模块
	 * @return
	 */
	public String module() default "";
	
	/**
	 * 描述
	 * @return
	 */
	public String remark() default "";
	
}
