package org.liujia.shop.dao;

import org.liujia.core.dao.GenericDao;
import org.liujia.shop.model.User;



public interface UserDao extends GenericDao<User, Integer>{
	User login(String name,String pwd);
	boolean valid(String email);
	public User findUserByEmailAndPassword(String email,String password);
}
