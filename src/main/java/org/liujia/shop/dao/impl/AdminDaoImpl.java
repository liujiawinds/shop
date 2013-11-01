package org.liujia.shop.dao.impl;

import java.util.List;

import org.liujia.core.dao.impl.GenericDaoHibImpl;
import org.liujia.shop.dao.AdminDao;
import org.liujia.shop.model.Admin;



public class AdminDaoImpl extends GenericDaoHibImpl<Admin, Integer>	implements AdminDao{

	@SuppressWarnings("unchecked")
	public Admin login(String name, String password) {
		// TODO Auto-generated method stub
		List<Admin> list=getHibernateTemplate().find("from Admin admin where admin.name=?and admin.password=?",name,password);
		return list.size()!=0?list.get(0):null;
	}
	
}
