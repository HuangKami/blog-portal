package com.kami.blog.util;


import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import com.aliyun.oss.OSSClient;

public class OSSHelper {
	public static final String OSSURL = "";
	public static final String ENDPOINT = "";
	public static final String ACCESSKEYID = "";
	public static final String ACCESSKEYSECRET = "";
	public static final String BUCKETNAME = "huangkami";
	private static Logger logger = Logger.getLogger(OSSHelper.class);
	
	private OSSHelper() {}
	
	public static String save(MultipartFile file) {
		OSSClient client = new OSSClient(ENDPOINT, ACCESSKEYID, ACCESSKEYSECRET);
		String originalFilename = file.getOriginalFilename();
		if(!originalFilename.contains(".")) {
			logger.error("用户请求上传文件名错误");
			return KeyHelper.ERROR;
		}
		String fileName = UUIDHelper.getUUID() 
				+ originalFilename.substring(originalFilename.lastIndexOf("."));
		try {
			client.putObject(BUCKETNAME, fileName, file.getInputStream());
		} catch (Exception e) {
			logger.error("上传oss失败" + e);
			return KeyHelper.ERROR;
		} finally {
			client.shutdown();
		}
		return OSSURL + fileName;
	}
}
