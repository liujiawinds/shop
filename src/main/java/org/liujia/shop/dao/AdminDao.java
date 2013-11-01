package org.liujia.shop.dao;

import org.liujia.core.dao.GenericDao;
import org.liujia.shop.model.Admin;



public interface AdminDao extends GenericDao<Admin, Integer> {
	public Admin login(String name,String password);
}
