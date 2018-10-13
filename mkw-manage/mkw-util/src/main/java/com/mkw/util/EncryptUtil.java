package com.mkw.util;

import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import sun.misc.BASE64Decoder;

@SuppressWarnings("restriction")
public class EncryptUtil {
	private static final String KEY = "www.czs-tech.com";
	private static final String ALGORITHMSTR = "AES/ECB/PKCS5Padding";

	private static String base64Encode(byte[] bytes) {
		return Base64.encodeBase64String(bytes);
	}

	private static byte[] base64Decode(String base64Code) throws Exception {
		BASE64Decoder base64Decoder = new BASE64Decoder();
		byte[] decodeBuffer = base64Decoder.decodeBuffer(base64Code);
		base64Decoder = null;
		return decodeBuffer;
	}

	private static byte[] aesEncryptToBytes(String content, String encryptKey) throws Exception {
		SecretKeySpec secretKeySpec = new SecretKeySpec(encryptKey.getBytes(), "AES");
		KeyGenerator kgen = KeyGenerator.getInstance("AES");
		kgen.init(128);
		Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
		cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
		byte[] doFinal = cipher.doFinal(content.getBytes("utf-8"));
		secretKeySpec = null;
		return doFinal;
	}

	private static String aesDecryptByBytes(byte[] encryptBytes, String decryptKey) throws Exception {
		SecretKeySpec secretKeySpec = new SecretKeySpec(decryptKey.getBytes(), "AES");
		KeyGenerator kgen = KeyGenerator.getInstance("AES");
		kgen.init(128);
		Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
		cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
		byte[] decryptBytes = cipher.doFinal(encryptBytes);
		secretKeySpec = null;
		return new String(decryptBytes);
	}
	
	/**
	 * @Description: 加密
	 * @author xiaojiayi
	 * @date 2018年6月1日 上午11:01:56 
	 *
	 * @param content
	 * @param encryptKey
	 * @return
	 * @throws Exception
	 */
	public static String aesEncrypt(String content) throws Exception {
		return base64Encode(aesEncryptToBytes(content, KEY));
	}
	
	/**
	 * @Description: 解密
	 * @author xiaojiayi
	 * @date 2018年6月1日 上午11:02:40 
	 *
	 * @param encryptStr
	 * @return
	 * @throws Exception
	 */
	public static String aesDecrypt(String encryptStr) throws Exception {
		return aesDecryptByBytes(base64Decode(encryptStr), KEY);
	}
	
	/*** 
     * MD5加密 生成32位md5码 
     */  
    public static String MD5(String inStr) throws Exception {  
        MessageDigest md5 = null;  
        md5 = MessageDigest.getInstance("MD5");  
        char[] charArray = inStr.toCharArray();  
        byte[] byteArray = new byte[charArray.length];  
  
        for (int i = 0; i < charArray.length; i++)  
            byteArray[i] = (byte) charArray[i];  
        byte[] md5Bytes = md5.digest(byteArray);  
        StringBuffer hexValue = new StringBuffer();  
        for (int i = 0; i < md5Bytes.length; i++){  
            int val = ((int) md5Bytes[i]) & 0xff;  
            if (val < 16)  
                hexValue.append("0");  
            hexValue.append(Integer.toHexString(val));  
        }  
        return hexValue.toString();
    }

	/**
	 * @Description: 测试
	 * @author xiaojiayi
	 * @date 2018年6月1日 上午11:02:50 
	 *
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		String content = "48:A3:80:28:95:54";
		System.out.println("加密前：" + content);

		String encrypt = aesEncrypt(content);
		System.out.println("加密后：" + encrypt);

		String decrypt = aesDecrypt("qLELbpehsX9Xjs7p2nLs5yBw1j+eJQpYG42LH011BW8=");
		System.out.println("解密后：" + decrypt);
		
		String md5 = MD5("123");
		System.out.println(md5);
	}
}
