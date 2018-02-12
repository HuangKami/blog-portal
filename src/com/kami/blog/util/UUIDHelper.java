package com.kami.blog.util;

import java.util.UUID;

/**
 *	产生唯一ID工具类
 */
public class UUIDHelper {
	private UUIDHelper() {}
	
	public static String getUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}
