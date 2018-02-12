package com.kami.blog.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

/**
 *	字符串工具类
 */
public class StringHelper extends StringUtils {
	private static Logger logger = Logger.getLogger(StringHelper.class);
	
	public static Integer stringToInteger(String str) {
		if(StringHelper.isEmpty(str)) {
			return null;
		}
		str.trim();
		try {
			return Integer.parseInt(str);
		} catch (NumberFormatException e) {
			logger.error("String转换Integer出异常：" + str);
			return null;
		}
	}
	
	public static String integerToString(Integer integer) {
		return String.valueOf(integer);
	}
}
