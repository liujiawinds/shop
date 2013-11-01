package org.liujia.shop.service.impl;


import org.liujia.shop.dao.AdminDao;
import org.liujia.shop.model.Admin;
import org.liujia.shop.service.AdminService;


public class AdminServiceImpl implements AdminService{
	private AdminDao adminDao;

	public void setAdminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}

	public void delete(Admin admin) {
		adminDao.delete(admin);
	}

	public Admin save(Admin admin) {
		return null;
	}

	public void update(Admin admin) {
		
	}

	public Admin login(String name, String password) {
		return adminDao.login(name, password);
	}

	public void deleteById(Integer id) {
		adminDao.deleteById(id);
	}
	
}
