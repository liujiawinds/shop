package org.liujia.shop.dao.impl;

import java.util.List;

import org.liujia.core.dao.impl.GenericDaoHibImpl;
import org.liujia.shop.dao.UserDao;
import org.liujia.shop.model.User;



@SuppressWarnings("unchecked")
public class UserDaoImpl extends GenericDaoHibImpl<User, Integer> implements UserDao{

	public User login(String name, String pwd) {
		String s="from User where username=? and password=?";
		List<User> users=getHibernateTemplate().find(s, new Object[]{name,pwd});
		return users.size()>0?users.get(0):null;
	}
	public boolean valid(String email) {
		String queryString="from User where email=?";
		List<User> users=getHibernateTemplate().find(queryString, new Object[]{email});
		return users.size()==0;
	}
	public User findUserByEmailAndPassword(String email, String password) {
		
		List<User> users=getHibernateTemplate().find("from User where email=? and password=?", new Object[]{email,password});
		return users.size()>0?users.get(0):null;
	}
}
