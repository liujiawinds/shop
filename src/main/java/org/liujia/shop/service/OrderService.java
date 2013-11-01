package org.liujia.shop.service;


import org.liujia.shop.model.Order;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface OrderService {
	public Order find(Integer orderId);
	public Order save(Order entity);
	public void update(Order entity);
	public void delete(Order entity);
	
	public Order findByUserId(Integer userId);
}
