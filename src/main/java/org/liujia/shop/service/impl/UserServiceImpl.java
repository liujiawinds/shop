package org.liujia.shop.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.liujia.shop.dao.CartDao;
import org.liujia.shop.dao.OrderDao;
import org.liujia.shop.dao.ProductDao;
import org.liujia.shop.dao.UserDao;
import org.liujia.shop.dto.PurchaseRecord;
import org.liujia.shop.model.Cart;
import org.liujia.shop.model.Order;
import org.liujia.shop.model.Product;
import org.liujia.shop.model.User;
import org.liujia.shop.service.ProductService;
import org.liujia.shop.service.UserService;


public class UserServiceImpl implements UserService{
	
	private UserDao userDao;
	private CartDao cartDao;
	private OrderDao orderDao;
	private ProductDao productDao;
	

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
	
	public User findUserByEmailAndPassword(String email, String password) {
		return userDao.findUserByEmailAndPassword(email, password);
	}

	public List<PurchaseRecord> getPurchaseRecord(User user) {
		Integer userId = user.getId();
		List<Order> orderList = orderDao.findByUserId(userId);
		List<PurchaseRecord> recordList = new ArrayList<PurchaseRecord>();
		if(orderList!=null && orderList.size()>0){
			for(Order order : orderList){
				PurchaseRecord pr = new PurchaseRecord();
				pr.setAddress(order.getAddress());
				pr.setId(order.getId().toString());
				pr.setOrderTime(order.getOrderTime().toLocaleString());
				pr.setStatus(order.getStatus());
				pr.setTotalPrice(String.valueOf(order.getTotoalPrice()));
				List<Cart> cartList = cartDao.findCartByOrderId(order.getId());
				if(cartList!=null && cartList.size()>0){
					String productNames = "";
					for(Cart cart : cartList){
						Product product = productDao.findById(cart.getProductId());
						productNames += product.getName();
					}
					pr.setProductName(productNames);
				}
				recordList.add(pr);
			}
		}
		return recordList;
	}

	public User findById(Integer userId) {
		return userDao.findById(userId);
	}

	public void setCartDao(CartDao cartDao) {
		this.cartDao = cartDao;
	}

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}
	
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
}
