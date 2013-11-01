package org.liujia.core.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 验证码 
 * 
 * @author Stephen
 */
public class CaptchaServlet extends HttpServlet {
	private static final long serialVersionUID = 4277842761354890126L;

	private static int WIDTH = 65; 	// 图片宽度
	private static int HEIGHT = 28; // 图片高度

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("image/jpeg");
		// 设置浏览器不要缓存此图片
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "on-cache");
		response.setDateHeader("Expires", 0);

		// 获取流对象向客户端写入图片
		ServletOutputStream out = response.getOutputStream();

		// 创建具有访问图象缓冲区的对象
		BufferedImage bufferedImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

		// 获取所有图形上下文的抽象基类的对象
		Graphics g = bufferedImage.getGraphics();
		g.setFont(new Font(null, Font.ITALIC, 16));

		/*
		 * 验证码放入session
		 */
		HttpSession session = request.getSession();
		String validateCode = this.getValidateCode();
		session.setAttribute("checkNumber", validateCode);

		/*
		 * 绘制验证码图片
		 */
		this.drawBackgound(g);
		this.drawCode(g, validateCode);
		g.dispose();	// 结束图像绘制过程,完成图像

		// 将内存中的图像编码成JPEG格式的图片写入到流对象中
		javax.imageio.ImageIO.write(bufferedImage, "JPEG", out);
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

	/**
	 * 
	 * 产生验证码
	 * 
	 * @return String
	 * 
	 */
	private String getValidateCode() {
		// 定义验证码的字符集
		String chars = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuffer sb = new StringBuffer();
		// 产生四个随机字符
		Random r = new Random();
		for (int i = 0; i < 4; i++) {
			int rand = r.nextInt(52);
			sb.append(chars.charAt(rand));
		}
		return sb.toString();
	}

	/**
	 * 
	 * 绘制背景图片
	 * 
	 * @param g
	 *            Graphics
	 */
	private void drawBackgound(Graphics g) {
		// 设置背景绘制颜色
		g.setColor(new Color(0xDCDCDC));

		// 给图片填充颜色
		g.fillRect(0, 0, WIDTH, HEIGHT);

		// 随机产生120个干扰点
		for (int i = 0; i < 120; i++) {
			// 产生三种随机颜色
			int color1 = (int) (Math.random() * 255);
			int color2 = (int) (Math.random() * 255);
			int color3 = (int) (Math.random() * 255);

			// 干扰点颜色
			g.setColor(new Color(color1, color2, color3));

			// 绘制干扰点
			int x = (int) (Math.random() * WIDTH);
			int y = (int) (Math.random() * HEIGHT);
			g.drawOval(x, y, 1, 0);
		}
	}

	/**
	 * 
	 * 将验证码绘制到图片上
	 * 
	 * @param g
	 *            Graphics
	 * @param validateCode
	 *            String
	 */
	private void drawCode(Graphics g, String validateCode) {
		// 设置绘制颜色
		g.setColor(Color.BLACK);
		// 设置字体
		g.setFont(new Font(null, Font.ITALIC | Font.BOLD, 16));

		// 在不同的位置上输出验证码的每个字符
		g.drawString("" + validateCode.charAt(0), 5, 20);
		g.drawString("" + validateCode.charAt(1), 20, 20);
		g.drawString("" + validateCode.charAt(2), 35, 20);
		g.drawString("" + validateCode.charAt(3), 50, 20);
	}

}
