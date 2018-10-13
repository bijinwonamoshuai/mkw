package com.mkw.util;

import java.util.Random;
import java.util.UUID;

public class IdUtils {
	
	public static void main(String[] args) throws Exception {
		System.out.println(UUID());
		System.out.println(random());
	}
	
	/**
	 * @Description: 生成32位uuid
	 * @author xiaojiayi
	 * @date 2018年3月12日 上午11:49:07 
	 *
	 * @return
	 * @throws Exception
	 */
	public static String UUID() throws Exception {
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	/**
	 * @Description: 6位随机数（3位大写字母+3位数字）
	 * @author xiaojiayi
	 * @date 2018年5月16日 下午5:26:07 
	 *
	 * @return
	 * @throws Exception
	 */
	public static String random() throws Exception {
		String CODE1 = "QWERTYUIOPASDFGHJKLZXCVBNM";
		String CODE2 = "0123456789";
		Random rand = new Random();
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < 3; i++) {
			buffer.append(CODE1.charAt(rand.nextInt(CODE1.length()-1)));
		}
		for (int i = 0; i < 3; i++) {
			buffer.append(CODE2.charAt(rand.nextInt(CODE2.length()-1)));
		}
		rand = null;
		return buffer.toString();
	}
}
