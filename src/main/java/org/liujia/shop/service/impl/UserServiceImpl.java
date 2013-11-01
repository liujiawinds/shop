package org.liujia.shop.service.impl;

import java.util.List;

import org.liujia.shop.dao.UserDao;
import org.liujia.shop.model.User;
import org.liujia.shop.service.UserService;


public class UserServiceImpl implements UserService{
	
	private UserDao userDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public User login(String name, String pwd) {
		return userDao.login(name, pwd);
	}

	public boolean valid(String name) {
		return userDao.valid(name);
	}

	public void delete(User user) {
		userDao.delete(user);
	}

	public User save(User user) {
		return userDao.save(user);
	}

	public void update(User user) {
		userDao.update(user);
	}

	public void delete(Integer id) {
		userDao.deleteById(id);
	}

	public User findById(Integer userId) {
		return userDao.findById(userId);
	}

	public List<User> findAll() {
		return userDao.findAll();
	}

	public User findUserByEmailAndPassword(String email, String password) {
		return userDao.findUserByEmailAndPassword(email, password);
	}
}
