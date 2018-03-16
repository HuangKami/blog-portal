package com.kami.blog.util;

import java.util.Date;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

/**
 *	邮件帮助类
 */
public class MailHelper {
	public static final String HOST = "smtp.163.com";
	public static final String PORT = "465";
	public static final String CLASS = "javax.net.ssl.SSLSocketFactory";
	public static final String ACCOUNT = "huangkamisama@163.com";
	public static final String NAME = "HuangKami";
	public static final String PASSWORD = "kamisama";
	public static final String CHARACTER = "UTF-8";
	public static final Session SESSION;
	private static Logger logger = Logger.getLogger(MailHelper.class);
	
	static {
		//初始化配置
		Properties props = new Properties();
		props.put("username", ACCOUNT);  
        props.put("password", PASSWORD);  
        props.put("mail.smtp.host", HOST);  
        props.put("mail.smtp.socketFactory.port", PORT);  
        props.put("mail.smtp.socketFactory.class", CLASS);  
        props.put("mail.smtp.port", PORT); 
        props.put("mail.smtp.auth", "true"); 
       
        SESSION = Session.getInstance(props);
        //打印日志
        SESSION.setDebug(true);
	}
	 
	private MailHelper() {}
	
	/**
	 * 发送邮件
	 * @param account 收件人邮箱
	 * @param name 收件人昵称
	 * @param theme 邮件主题
	 * @param content 邮件内容
	 */
	public static void sendEmail(String account, String name, String theme, String content) {
		MimeMessage message = createMimeMessage(account, name, theme, content);
		Transport transport = null;
		try {
			transport = SESSION.getTransport();
			transport.connect(ACCOUNT, PASSWORD);
			transport.sendMessage(message, message.getAllRecipients());
		} catch (Exception e) {
			logger.error(e + ":发送邮件时发生异常");
		} finally {
			try {
				transport.close();
			} catch (MessagingException e) {
				logger.error(e, e);
			}
		}
		
	}
	
	private static MimeMessage createMimeMessage(String account, String name,
			String theme, String content) {
		MimeMessage message = new MimeMessage(SESSION);
		try {
			//邮件发送方
			message.setFrom(new InternetAddress(ACCOUNT, NAME, CHARACTER));
			//邮件接收方
			message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(account, name, CHARACTER));
			//邮件主题
			message.setSubject(theme, CHARACTER);
			//邮件内容
			message.setContent(content, "text/html;charset=UTF-8");
			//发件时间
			message.setSentDate(new Date());
			//保存配置
			message.saveChanges();
			
		} catch (Exception e) {
			logger.error(e, e);
		}
		return message;
	}
}
