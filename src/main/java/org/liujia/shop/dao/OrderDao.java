package org.liujia.shop.dao;


import org.liujia.core.dao.GenericDao;
import org.liujia.shop.model.Order;

public interface OrderDao extends GenericDao<Order, Integer>{
	public Order findByUserId(Integer userId);
}
