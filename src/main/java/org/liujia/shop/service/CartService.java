package org.liujia.shop.service;


import java.util.List;

import org.liujia.shop.model.Cart;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface CartService {
	
	public void save(Cart cart);
	public void saveCollection(Cart collection);
	public void update(Cart cart);
	public void delete(Cart cart);
	
	public void deleteByCartId(Integer id);
	public void modifyQuantity(Integer id, Integer quantity);
	
	public List<Cart> findCartByUserId(Integer userId);
	public Cart findById(Integer cartId);
	public Cart findByUserIdAndProductId(Integer userId,Integer productId);
	public List<Cart> findCollectionByUserId(Integer userId);
}
