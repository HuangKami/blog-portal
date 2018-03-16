package com.kami.blog.util;

import java.util.Random;

/**
 *	随机数工具类
 */
public class RandomHelper {
	private static Random random;
	private static Object object = new Object();
	//随机验证码
	public static final String AUTH_STRING = "ABCDEFGHJKLMNPRSTUVWXYZ0123456789";
	
	private RandomHelper() {}

	public static Random getRandom() {
		synchronized (object) {
			if(random == null) {
				random = new Random();
			}
		}
		return random;
	}

	/**
	 * 随机参数一个字符
	 */
	public static String randomString() {
		if(random == null) {
			getRandom();
		}
		return String.valueOf(AUTH_STRING.charAt(random.nextInt(AUTH_STRING.length())));
	}
	
	/**
	 * 获取四位随机验证码
	 */
	public static String getRandomCode() {
		StringBuilder sb = new StringBuilder();
		int codeSize = KeyHelper.CODE_SIZE;
		while(codeSize-- > 0) {
			sb.append(randomString());
		}
		return sb.toString();
	}
}