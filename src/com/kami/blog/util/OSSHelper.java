package com.kami.blog.util;


import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import com.aliyun.oss.OSSClient;

/**
 *	对象存储工具类
 */
public class OSSHelper {
	public static final String OSSURL = "http://huangkami.oss-cn-shenzhen.aliyuncs.com/";
	public static final String ENDPOINT = "oss-cn-shenzhen.aliyuncs.com";
	public static final String ACCESSKEYID = "";
	public static final String ACCESSKEYSECRET = "";
	public static final String BUCKETNAME = "";
	private static Logger logger = Logger.getLogger(OSSHelper.class);
	
	private OSSHelper() {}
	
	public static String save(MultipartFile file) {
		OSSClient client = new OSSClient(ENDPOINT, ACCESSKEYID, ACCESSKEYSECRET);
		String originalFilename = file.getOriginalFilename();
		if(!originalFilename.contains(".")) {
			logger.error("用户请求上传文件名错误");
			return KeyHelper.ERROR;
		}
		//生成文件名
		String fileName = UUIDHelper.getUUID() 
				+ originalFilename.substring(originalFilename.lastIndexOf("."));
		try {
			//上传
			client.putObject(BUCKETNAME, fileName, file.getInputStream());
		} catch (Exception e) {
			logger.error("上传oss失败" + e);
			return KeyHelper.ERROR;
		} finally {
			client.shutdown();
		}
		//文件访问路径
		return OSSURL + fileName;
	}
}
