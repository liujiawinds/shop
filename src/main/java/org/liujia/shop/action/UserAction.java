package org.liujia.shop.action;


import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.liujia.core.util.MD5;
import org.liujia.shop.model.User;
import org.liujia.shop.service.UserService;

import com.opensymphony.xwork2.ActionContext;

@SuppressWarnings("unchecked")
public class UserAction {
	int i=0;
	String msg;
	private User user;
	String newPassword;
	private UserService userService;
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String getNewPassword() {
		return newPassword;
	}


	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}


	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
	//------------------------------前台方法--------------------------
	public String login(){
		HttpServletRequest request=(HttpServletRequest) ServletActionContext.getRequest();
		Map session=ActionContext.getContext().getSession();
		//验证码
		String captcha=request.getParameter("captcha");
		String checkNumber=(String) session.get("checkNumber");
		MD5 md5 = new MD5();
		if(!captcha.equalsIgnoreCase(checkNumber)){
			msg="验证码输入错误！";
			return "ERROR";
		}
		//登录验证
		User user2=userService.findUserByEmailAndPassword(user.getEmail(), md5.md5s(user.getPassword()));
		if(null!=user2){
			session.put("user_logined", user2);
			session.put("role", "user");
			return "SUCCESS";
		}else {
			msg="邮箱或者密码输入错误，请检查后重新输入！";
			return "ERROR";
		}
	}
	
	public void valid() throws IOException {                                                                                                                                                                                                                                                                                                                                                                                                                                        {
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		String email=request.getParameter("email");
		if(userService.valid(email)){
			response.getWriter().write("yes");
		}else {
			response.getWriter().write("no");
		}
	}
	}
	
	public String regist(){
		if(user.getEmail() != null && user.getPassword() != null){
			user.setRegistDate(new Date());
			user.setBalance(0);
			String tempPassword = user.getPassword();
			MD5 md5=new MD5();
			user.setPassword(md5.md5s(tempPassword));
			userService.save(user);
			ActionContext context=ActionContext.getContext();
			Map session=context.getSession();
			session.put("user_logined", user);
			return "SUCCESS";
		}else{
			return "ERROR";
		}
		
	}
	
	
	public String modify(){
		Map session = ActionContext.getContext().getSession();
		String msg = null;
		if(user!=null){
			User tempUser = userService.findUserByEmailAndPassword(user.getEmail(), user.getPassword());
			if(tempUser!=null&&!user.getUsername().equals("")){
				tempUser.setUsername(user.getUsername());
				tempUser.setPassword(newPassword);
				userService.update(user);
				msg="信息修改成功！";
			}else{
				msg="邮箱或者密码输入错误！";
			}
		}
		session.put("msg", msg);
		return "SUCCESS";
	}
	
	public String delete(){
		HttpServletRequest request=ServletActionContext.getRequest();
		int id=Integer.valueOf(request.getParameter("id"));
		userService.delete(id);
		return "SUCCESS";
	}
	
	public String logout(){
		Map session = ActionContext.getContext().getSession();
		session.clear();
		return "SUCCESS";
	}
	
	//--------------------------------后台方法--------------------------
	public String listAll(){
		List<User> users = userService.findAll();
		ActionContext context=ActionContext.getContext();
		Map session=context.getSession();
		session.put("usersInDb", users);
		return "SUCCESS";
	}
	
}
