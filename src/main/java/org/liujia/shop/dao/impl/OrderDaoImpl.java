package org.liujia.shop.dao.impl;


import java.util.List;

import org.liujia.core.dao.impl.GenericDaoHibImpl;
import org.liujia.shop.dao.OrderDao;
import org.liujia.shop.model.Order;

public class OrderDaoImpl extends GenericDaoHibImpl<Order, Integer> implements OrderDao{

	public Order findByUserId(Integer userId) {
		List<Order> list = getHibernateTemplate().find("from Order as order where order.userId=?",userId);
		return list.size()!=0?list.get(0):null;
	}

}
