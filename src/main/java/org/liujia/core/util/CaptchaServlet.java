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
 * ��֤�� 
 * 
 * @author Stephen
 */
public class CaptchaServlet extends HttpServlet {
	private static final long serialVersionUID = 4277842761354890126L;

	private static int WIDTH = 65; 	// ͼƬ���
	private static int HEIGHT = 28; // ͼƬ�߶�

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("image/jpeg");
		// �����������Ҫ�����ͼƬ
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "on-cache");
		response.setDateHeader("Expires", 0);

		// ��ȡ��������ͻ���д��ͼƬ
		ServletOutputStream out = response.getOutputStream();

		// �������з���ͼ�󻺳����Ķ���
		BufferedImage bufferedImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

		// ��ȡ����ͼ�������ĵĳ������Ķ���
		Graphics g = bufferedImage.getGraphics();
		g.setFont(new Font(null, Font.ITALIC, 16));

		/*
		 * ��֤�����session
		 */
		HttpSession session = request.getSession();
		String validateCode = this.getValidateCode();
		session.setAttribute("checkNumber", validateCode);

		/*
		 * ������֤��ͼƬ
		 */
		this.drawBackgound(g);
		this.drawCode(g, validateCode);
		g.dispose();	// ����ͼ����ƹ���,���ͼ��

		// ���ڴ��е�ͼ������JPEG��ʽ��ͼƬд�뵽��������
		javax.imageio.ImageIO.write(bufferedImage, "JPEG", out);
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

	/**
	 * 
	 * ������֤��
	 * 
	 * @return String
	 * 
	 */
	private String getValidateCode() {
		// ������֤����ַ���
		String chars = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuffer sb = new StringBuffer();
		// �����ĸ�����ַ�
		Random r = new Random();
		for (int i = 0; i < 4; i++) {
			int rand = r.nextInt(52);
			sb.append(chars.charAt(rand));
		}
		return sb.toString();
	}

	/**
	 * 
	 * ���Ʊ���ͼƬ
	 * 
	 * @param g
	 *            Graphics
	 */
	private void drawBackgound(Graphics g) {
		// ���ñ���������ɫ
		g.setColor(new Color(0xDCDCDC));

		// ��ͼƬ�����ɫ
		g.fillRect(0, 0, WIDTH, HEIGHT);

		// �������120�����ŵ�
		for (int i = 0; i < 120; i++) {
			// �������������ɫ
			int color1 = (int) (Math.random() * 255);
			int color2 = (int) (Math.random() * 255);
			int color3 = (int) (Math.random() * 255);

			// ���ŵ���ɫ
			g.setColor(new Color(color1, color2, color3));

			// ���Ƹ��ŵ�
			int x = (int) (Math.random() * WIDTH);
			int y = (int) (Math.random() * HEIGHT);
			g.drawOval(x, y, 1, 0);
		}
	}

	/**
	 * 
	 * ����֤����Ƶ�ͼƬ��
	 * 
	 * @param g
	 *            Graphics
	 * @param validateCode
	 *            String
	 */
	private void drawCode(Graphics g, String validateCode) {
		// ���û�����ɫ
		g.setColor(Color.BLACK);
		// ��������
		g.setFont(new Font(null, Font.ITALIC | Font.BOLD, 16));

		// �ڲ�ͬ��λ���������֤���ÿ���ַ�
		g.drawString("" + validateCode.charAt(0), 5, 20);
		g.drawString("" + validateCode.charAt(1), 20, 20);
		g.drawString("" + validateCode.charAt(2), 35, 20);
		g.drawString("" + validateCode.charAt(3), 50, 20);
	}

}
