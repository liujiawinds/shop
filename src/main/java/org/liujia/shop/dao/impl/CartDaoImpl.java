package org.liujia.shop.dao.impl;

import java.util.List;

import org.liujia.core.dao.impl.GenericDaoHibImpl;
import org.liujia.shop.dao.CartDao;
import org.liujia.shop.model.Cart;

@SuppressWarnings("unchecked")
public class CartDaoImpl extends GenericDaoHibImpl<Cart, Integer> implements CartDao{


	public void modifyQuantity(Integer id, Integer quantity) {
		Cart cart=getHibernateTemplate().load(Cart.class, id);
		cart.setQuantity(quantity);
		getHibernateTemplate().update(cart);
	}
	
	public void saveAndCombine(Cart cart){
		Integer productId=cart.getProductId();
		Integer userId=cart.getUserId();
		List<Cart> list=getHibernateTemplate().find("from Cart as cart where cart.userId=? and cart.productId=? and cart.isLike=0 and cart.orderId is null",userId,productId);
		if(list==null||list.size()==0){
			getHibernateTemplate().save(cart);
			return;
		}
		for(Cart tempCart : list){
			if(tempCart.getProductId().equals(productId)){
				tempCart.setQuantity(tempCart.getQuantity()+cart.getQuantity());
				getHibernateTemplate().save(tempCart);
				break;
			}
		}
	}

	public List<Cart> findCartByUserId(Integer userId) {
		List<Cart> list = getHibernateTemplate().find("from Cart as cart where cart.userId=? and cart.isLike=0 and cart.orderId is null",userId);
		return list;
	}

	public Cart findCartByUserIdAndProductId(Integer userId, Integer productId) {
		List<Cart> list = getHibernateTemplate().find("from Cart as cart where cart.userId=? and cart.productId=?",userId,productId);
		return list!=null?list.get(0):null;
	}

	public List<Cart> findCollectionByUserId(Integer userId) {
		List<Cart> list = getHibernateTemplate().find("from Cart as cart where cart.userId=? and cart.isLike=1",userId);
		return list;
	}
}
