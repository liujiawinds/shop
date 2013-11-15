package org.liujia.shop.service;


import java.util.Date;
import java.util.List;

import org.liujia.shop.model.Order;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface OrderService {
	public Order save(Order entity);
	public void update(Order entity);
	public void delete(Order entity);
	
	public Order find(Integer orderId);
	public List<Order> findByUserId(Integer userId);
	public Order findByOrderTime(Date date);
}
