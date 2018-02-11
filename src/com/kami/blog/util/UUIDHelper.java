package com.kami.blog.util;

import java.util.UUID;

public class UUIDHelper {
	private UUIDHelper() {}
	
	public static String getUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}
