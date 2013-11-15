package org.liujia.shop.dao;

import java.util.Date;
import java.util.List;

import org.liujia.core.dao.GenericDao;
import org.liujia.shop.model.Order;

public interface OrderDao extends GenericDao<Order, Integer>{
	public List<Order> findByUserId(Integer userId);
	public Order findByOrderTime(Date date);
}
