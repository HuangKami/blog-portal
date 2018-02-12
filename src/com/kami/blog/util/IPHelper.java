package com.kami.blog.util;

import javax.servlet.http.HttpServletRequest;

/**
 * IP工具类
 */
public class IPHelper {
	private IPHelper() {}

	/**
	 * 获取请求的IP
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");

		if (StringHelper.isEmpty(ip) || StringHelper.equalsIgnoreCase("unknown", ip)) {
			if (StringHelper.isEmpty(ip) || StringHelper.equalsIgnoreCase("unknown", ip)) {
				ip = request.getHeader("Proxy-Client-IP");
			}
			if (StringHelper.isEmpty(ip) || StringHelper.equalsIgnoreCase("unknown", ip)) {
				ip = request.getHeader("WL-Proxy-Client-IP");
			}
			if (StringHelper.isEmpty(ip) || StringHelper.equalsIgnoreCase("unknown", ip)) {
				ip = request.getHeader("HTTP_CLIENT_IP");
			}
			if (StringHelper.isEmpty(ip) || StringHelper.equalsIgnoreCase("unknown", ip)) {
				ip = request.getHeader("HTTP_X_FORWARDED_FOR");
			}
			if (StringHelper.isEmpty(ip) || StringHelper.equalsIgnoreCase("unknown", ip)) {
				ip = request.getRemoteAddr();
			}
		} else if (ip.length() > 15) {
			String[] ips = ip.split(",");
			for (int index = 0; index < ips.length; index++) {
				String strIp = ips[index];
				if (!StringHelper.equalsIgnoreCase("unknown", strIp)) {
					ip = strIp;
					break;
				}
			}
		}
		if(StringHelper.equalsIgnoreCase("0:0:0:0:0:0:0:1", ip)) {
			return "127.0.0.1";
		}
		return ip;
	}
}
