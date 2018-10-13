package com.mkw.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Verify {
	/**
	 * 不能为空
	 * 格式(多参数逗号隔开)：id,name,phone
	 * @return
	 */
	public String notNull() default "";
	
	/**
	 * 时间格式校验
	 * yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public String dateFormat() default "";
}
