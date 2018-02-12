package com.kami.blog.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kami.blog.util.KeyHelper;
import com.kami.blog.util.RandomHelper;
import com.kami.blog.util.SessionHelper;
import com.kami.blog.util.StringHelper;

@Controller
@RequestMapping("/authCode")
public class AuthCodeController {
	//验证码宽度
	public static final int WIDTH = 70;
	//验证码高度
	public static final int HEIGHT = 35;
	//绘制图片类型
	public static final int IMAGE_TYPE = 1;
	//验证码个数
	public static final int AUTH_SIZE = KeyHelper.CODE_SIZE;
	//干扰线个数
	public static final int INTERRUPT_SIZE = 40;
	private Random random = RandomHelper.getRandom();
	 

	/**
	 * 获取验证码
	 */
	@RequestMapping
	public void getAuthCode(HttpServletRequest request, HttpServletResponse response) {
		// 设置response头信息
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);

		try {
			String authCodeType = request.getParameter("authCodeType");
			BufferedImage image = drawImage(request, authCodeType);
			ImageIO.write(image, "JPEG", response.getOutputStream());
			response.getOutputStream().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private BufferedImage drawImage(HttpServletRequest request, String authCodeType) {
		// 生成缓冲区image类
		BufferedImage image = new BufferedImage(WIDTH, HEIGHT, IMAGE_TYPE);
		
		// 产生image类的Graphics用于绘制操作
		Graphics g = image.getGraphics();
		
		// Graphics类的样式
		g.setColor(this.getRandColor(200, 250));
		g.setFont(new Font("Times New Roman", 0, 28));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		// 绘制干扰线
		for (int i = 0; i < INTERRUPT_SIZE; i++) {
			g.setColor(this.getRandColor(130, 200));
			int x = random.nextInt(WIDTH);
			int y = random.nextInt(HEIGHT);
			int x1 = random.nextInt(12);
			int y1 = random.nextInt(12);
			g.drawLine(x, y, x + x1, y + y1);
		}

		// 绘制字符
		StringBuilder authCode = new StringBuilder();
		for (int i = 0; i < AUTH_SIZE; i++) {
			String randomString = RandomHelper.randomString();
			authCode.append(randomString);
			g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
			g.drawString(randomString, 13 * i + 6, 28);
		}

		g.dispose();
		
		//把验证码存进session中
		if(StringHelper.equals(KeyHelper.LOGIN_AUTHCODE, authCodeType)) {
			SessionHelper.setAttribute(request, KeyHelper.LOGIN_AUTHCODE, authCode.toString());
		} else if(StringHelper.equals(KeyHelper.REGISTER_AUTHCODE, authCodeType)) {
			SessionHelper.setAttribute(request, KeyHelper.REGISTER_AUTHCODE, authCode.toString());
		} else if(StringHelper.equals(KeyHelper.FIND_BACK_AUTHCODE, authCodeType)) {
			SessionHelper.setAttribute(request, KeyHelper.FIND_BACK_AUTHCODE, authCode.toString());
		}
//		else {
//			SessionHelper.setAttribute(request, KeyHelper.AUTHCODE, authCode.toString());
//		}
		
		return image;
	}

	/**
	 * 随机颜色
	 */
	private Color getRandColor(int fc, int bc) {
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}
}
