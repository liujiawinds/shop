package org.liujia.shop.dao;



import java.util.List;

import org.liujia.core.dao.GenericDao;
import org.liujia.shop.model.Cart;


public interface CartDao extends GenericDao<Cart, Integer>{
	public void modifyQuantity(Integer id,Integer quantity);
	public void saveAndCombine(Cart cart);
	public List<Cart> findCartByUserId(Integer userId);
	public Cart findCartByUserIdAndProductId(Integer userId, Integer productId);
	public List<Cart> findCollectionByUserId(Integer userId);
}
