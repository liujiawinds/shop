package org.liujia.shop.service.impl;


import java.util.List;

import org.liujia.shop.dao.CartDao;
import org.liujia.shop.model.Cart;
import org.liujia.shop.service.CartService;

public class CartServiceImpl implements CartService {

	
	private CartDao cartDao;
	
	public void setCartDao(CartDao cartDao) {
		this.cartDao = cartDao;
	}

	public void delete(Cart cart) {
		cartDao.delete(cart);
	}

	public void save(Cart cart) {
		cartDao.saveAndCombine(cart);
	}
	
	public void saveCollection(Cart collection){
		cartDao.save(collection);
	}
	
	public void update(Cart entity) {
		cartDao.update(entity);
	}

	public void deleteByCartId(Integer id) {
		cartDao.deleteById(id);
	}

	public void modifyQuantity(Integer id, Integer quantity) {
		cartDao.modifyQuantity(id, quantity);	
	}

	public List<Cart> findCartByUserId(Integer userId) {
		return cartDao.findCartByUserId(userId);
	}

	public Cart findById(Integer cartId) {
		return cartDao.findById(cartId);
	}

	public Cart findByUserIdAndProductId(Integer userId, Integer productId) {
		// TODO Auto-generated method stub
		return cartDao.findCartByUserIdAndProductId(userId, productId);
	}

	public List<Cart> findCollectionByUserId(Integer userId) {
		// TODO Auto-generated method stub
		return cartDao.findCollectionByUserId(userId);
	}

}
