package org.liujia.shop.dao.impl;


import java.util.Date;
import java.util.List;

import org.liujia.core.dao.impl.GenericDaoHibImpl;
import org.liujia.shop.dao.OrderDao;
import org.liujia.shop.model.Order;

@SuppressWarnings("unchecked")
public class OrderDaoImpl extends GenericDaoHibImpl<Order, Integer> implements OrderDao{

	public List<Order> findByUserId(Integer userId) {
		List<Order> list = getHibernateTemplate().find("from Order as order where order.userId=? order by order.orderTime",userId);
		return list;
	}

	public Order findByOrderTime(Date date) {
		List<Order> order = getHibernateTemplate().find("from Order as o where o.orderTime = ?",date);
		return order!=null && order.size()!=0 ?order.get(0):null;
	}
	
}
