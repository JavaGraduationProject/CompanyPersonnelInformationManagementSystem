package com.empl.mgr.support;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.empl.mgr.constant.SessionKey;

public class RandomValidateCode implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String RANDOMCODEKEY = "RANDOMVALIDATECODEKEY";//放到session中的key
	private static Random random = new Random();
	private static String randString = "23456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghjklmnpqrstuxwxyz";//随机产生的字符串

	private static int width = 70;//图片宽
	private static int height = 30;//图片高
	private static int lineSize = 40;//干扰线数量
	private static int stringNum = 4;//随机产生字符数量

	/*
	 * 获得字体
	 * xiaoming [K]
	 */
	private static Font getFont() {
		return new Font("Fixedsys", Font.CENTER_BASELINE, 18);
	}

	/*
	 * 获得颜色
	 * xiaoming [K]
	 */
	private static Color getRandColor(int fc, int bc) {
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc - 16);
		int g = fc + random.nextInt(bc - fc - 14);
		int b = fc + random.nextInt(bc - fc - 18);
		return new Color(r, g, b);
	}

	/*
	 * 生成随机图片
	 * xiaoming [K]
	 */
	public static void getRandcode(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		//BufferedImage类是具有缓冲区的Image类,Image类是用于描述图像信息的类
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
		Graphics g = image.getGraphics();//产生Image对象的Graphics对象,改对象可以在图像上进行各种绘制操作
		g.fillRect(0, 0, width, height);
		g.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 18));
		g.setColor(getRandColor(110, 133));
		//绘制干扰线
		for (int i = 0; i <= lineSize; i++) {
			drowLine(g);
		}
		//绘制随机字符
		String randomString = "";
		for (int i = 1; i <= stringNum; i++) {
			randomString = drowString(g, randomString, i);
		}
		/*session.removeAttribute(RANDOMCODEKEY);
		session.setAttribute(RANDOMCODEKEY, randomString);
		session.setAttribute(SessionKey.VALIDATE_CODE, randomString);*/
		
		request.getSession().setAttribute(RANDOMCODEKEY,randomString);
		request.getSession().setAttribute(SessionKey.VALIDATE_CODE,randomString);
		g.dispose();
		try {
			ImageIO.write(image, "JPEG", response.getOutputStream());//将内存中的图片通过流动形式输出到客户端
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * 绘制字符串
	 * xiaoming [K]
	 */
	private static String drowString(Graphics g, String randomString, int i) {
		g.setFont(getFont());
		g.setColor(new Color(random.nextInt(101), random.nextInt(111), random.nextInt(121)));
		String rand = String.valueOf(getRandomString(random.nextInt(randString.length())));
		randomString += rand;
		g.translate(random.nextInt(3), random.nextInt(3));
		g.drawString(rand, 13 * i, 16);
		return randomString;
	}

	/*
	 * 绘制干扰线
	 * xiaoming [K]
	 */
	private static void drowLine(Graphics g) {
		int x = random.nextInt(width);
		int y = random.nextInt(height);
		g.drawLine(x, y, x + random.nextInt(13), y + random.nextInt(15));
	}

	/*
	 * 获取随机的字符
	 * xiaoming [K]
	 */
	public static String getRandomString(int num) {
		return String.valueOf(randString.charAt(num));
	}
}