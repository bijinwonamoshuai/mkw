package com.mkw.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

public class TimeUtils {
	private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	private static DateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/**
	 * @Description: 获取时间
	 *
	 * @author xiaojiayi
	 * @date 2018年6月15日 下午4:42:09
	 * @param dateStr
	 * @return
	 * @throws Exception
	 */
	public static Date findDateTime(String dateStr) throws Exception {
		if (StringUtils.isNotBlank(dateStr)) {
			return dateTimeFormat.parse(dateStr);
		}
		return null;
	}
	
	public static String findDateTime(Date date) throws Exception {
		return dateTimeFormat.format(date);
	}
	
	/**
	 * @Description: 获取日期
	 *
	 * @author xiaojiayi
	 * @date 2018年6月15日 下午4:42:29
	 * @param dateStr
	 * @return
	 * @throws Exception
	 */
	public static Date findDate(String dateStr) throws Exception {
		return dateFormat.parse(dateStr);
	}
	
	public static Date findDate(String dateStr, String format) throws Exception {
		DateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.parse(dateStr);
	}
	
	/**
	 * 获取时间
	 * @return
	 */
	public static String findDate(Date date, String format) {
		DateFormat dateFormat = new SimpleDateFormat(format);
		String string= dateFormat.format(date);
		date = null;
		return string;
	}
	
	/**
	 * 获取今天时间
	 * @return
	 */
	public static String today(String format) {
		DateFormat date = new SimpleDateFormat(format);
		String string= date.format(new Date());
		date = null;
		return string;
	}
	
	/**
	 * @Description: 判断是否为标准日期格式（yyyy-MM-dd）
	 * @author xiaojiayi
	 * @date 2018年4月11日 下午5:44:29 
	 *
	 * @param date
	 * @return
	 */
	public static boolean isDateFormat(String date) {
		try {
			dateFormat.parse(date);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * @Description: 判断是否为标准时间格式（yyyy-MM-dd HH:mm:ss）
	 * @author xiaojiayi
	 * @date 2018年4月11日 下午5:44:29 
	 *
	 * @param date
	 * @return
	 */
	public static boolean isDateTimeFormat(String dateTime) {
		try {
			dateTimeFormat.parse(dateTime);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static void main(String[] args) {
		System.out.println(TimeUtils.isDateFormat("2018-04-15 55:55:55"));
		System.out.println(TimeUtils.isDateTimeFormat("2018-04-15"));
	}
}
