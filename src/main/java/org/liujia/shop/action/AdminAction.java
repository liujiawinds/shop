package org.liujia.shop.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.liujia.shop.model.Admin;
import org.liujia.shop.service.AdminService;

import com.opensymphony.xwork2.ActionContext;
@SuppressWarnings("unchecked")
public class AdminAction {
	private Admin admin;
	private int	adminId;
	private AdminService adminService;
	
	
	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	
	public int getAdminId() {
		return adminId;
	}
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}
	
	public String login(){
		HttpServletRequest request=(HttpServletRequest) ServletActionContext.getRequest();
		Map session=ActionContext.getContext().getSession();
		//验证码
		String captcha=request.getParameter("captcha");
		String checkNumber=(String) session.get("checkNumber");
		if(!captcha.equalsIgnoreCase(checkNumber))return "LOGIN";
		//管理员登陆
		Admin admin_logined=adminService.login(admin.getName(), admin.getPassword());
		if(null!=admin_logined){
			session.put("admin_logined", admin_logined.getName());
			session.put("role", "admin");
			return "SUCCESS";
		}else {
			return "INPUT";
		}
	}
	
	/**删除管理员
	 * 
	 */
	public void delete(){
		adminService.deleteById(adminId);
	}
	
	public void update(){
		
	}
}
