package org.liujia.shop.service.impl;

import java.util.Date;
import java.util.List;

import org.liujia.shop.dao.OrderDao;
import org.liujia.shop.model.Order;
import org.liujia.shop.service.OrderService;

public class OrderServiceImpl implements OrderService{
	
	private OrderDao orderDao;
	
	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	public void delete(Order entity) {
		orderDao.delete(entity);
	}

	public Order save(Order entity) {
		return orderDao.save(entity);
	}

	public void update(Order entity) {
		orderDao.update(entity);
	}

	public Order find(Integer orderId) {
		return orderDao.findById(orderId);
	}

	public List<Order> findByUserId(Integer userId) {
		return orderDao.findByUserId(userId);
	}

	public Order findByOrderTime(Date date) {
		return orderDao.findByOrderTime(date);
	}


}
