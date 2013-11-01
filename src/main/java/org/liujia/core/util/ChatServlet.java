package org.liujia.core.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.liujia.shop.model.User;
public class ChatServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Constructor of the object.
	 */
	public ChatServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
		
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		String action=request.getParameter("action");
		HttpSession session=request.getSession();
		ServletContext application=this.getServletContext();
		//聊天记录
		if("ChatList".equals(action)){
			User user = (User) session.getAttribute("user_logined");
			if(user==null){
				response.sendRedirect("login.jsp");
			}
			String username =user.getUsername();
			//获取在线用户列表
			List<String> users=(List<String>)application.getAttribute("users");
			if(users==null){
				users=new ArrayList<String>();
			}
			//将当前用户加入用户列表
			if(!users.contains(username)){
				users.add(username);
			}
			//更新application中的对象
			application.setAttribute("users", users);
			if(username==null){
				response.getWriter().write("unLogin");
			}else{
				response.getWriter().write(AllChatList(application));
			}
		}
		//发送消息
		else if("SendContent".equals(action)){
			String content=request.getParameter("content");
			response.getWriter().write(addContent(content,application,session));
			
		}
		//获取在线人员列表
		else if("onLineList".equals(action)){
			response.getWriter().write(GetOnlineUsers(application));
		}
		//下线
		else if("OffLine".equals(action)){
			response.getWriter().write(OffLine(application,session));
		}
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}
	

	//添加消息
	public String addContent(String content,ServletContext application,HttpSession session){
		List<String> strList=(List<String>) application.getAttribute("MessageList");
		if(strList==null){
			strList=new ArrayList<String>();
		}
		//获取session中的用户名
		User user = (User) session.getAttribute("user_logined");
		String username=user.getUsername();
		//获取时间
		Date date=new Date();
		content=content.replace("<:", "<img src='image/");
		content=content.replace(":>", ".gif' />");
		//组装消息
		String message="<font color=#6699CC face=Trebuchet MS>"+username+" "+date.toLocaleString()+"</font>\n<br>"+content;
		//添加到集合中
		strList.add(message);
		//放入application对象中
		application.setAttribute("MessageList", strList);
		return "True";
	}
	//获取所有的消息
	public String AllChatList(ServletContext application){
		StringBuffer sb=new StringBuffer();
		List<String> strList=(List<String>) application.getAttribute("MessageList");
		if(strList!=null){
			for(String s:strList){
				sb.append(s+"<br />");
			}
		} 
		return sb.toString();
	}
	//获取在线用户列表
	public String GetOnlineUsers(ServletContext application){
		StringBuffer sb=new StringBuffer();
		List<String> users=(List<String>) application.getAttribute("users");
		if(users!=null){
			for(String user:users){
				sb.append(user+"<br />");
			}
		} 
		return sb.toString();
	}
	//下线
	public String OffLine(ServletContext application,HttpSession session){
		//先取出用户名
		String username=(String)session.getAttribute("username");
		//移除session中的内容
		session.removeAttribute("username");
		//移除用户列表中的用户名
		List<String> users=(List<String>) application.getAttribute("users");
		if(users!=null){
			users.remove(username);
		} 
		return "True";
	}
}
