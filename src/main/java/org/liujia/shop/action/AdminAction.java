package org.liujia.shop.action;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;
import org.liujia.core.service.QueryService;
import org.liujia.shop.model.Admin;
import org.liujia.shop.service.AdminService;

import com.opensymphony.xwork2.ActionContext;

public class AdminAction {
	
	
	private Admin admin;
	private AdminService adminService;
	private QueryService<Admin> queryService;
	
	
	public String login(){
		HttpServletRequest request=(HttpServletRequest) ServletActionContext.getRequest();
		Map<String, Object> session=ActionContext.getContext().getSession();
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
	
	
	
	/**新增管理员
	 * @return
	 */
	public String add(){
		adminService.save(admin);
		ServletActionContext.getRequest().setAttribute("msg", "管理员添加成功！");
		return "SUCCESS";
	}
	
	public void getLoginInfo(){
		List<Map<String ,Object>> adminList = queryService.getList(Admin.class, "", "", "", true);
		String jsonArray = JSONArray.fromObject(adminList).toString();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		try {
			response.getWriter().write(jsonArray);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	
	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}

	public void setQueryService(QueryService<Admin> queryService) {
		this.queryService = queryService;
	}
	
}
