package org.liujia.shop.action;

import java.util.List;
import java.util.Map;

import org.liujia.shop.model.Cart;
import org.liujia.shop.model.Product;
import org.liujia.shop.model.User;
import org.liujia.shop.service.CartService;
import org.liujia.shop.service.ProductService;

import com.opensymphony.xwork2.ActionContext;
@SuppressWarnings("unchecked")
public class CollectionAction {
	private Cart cart;
	private int productId;
	private int collectionId;
	private CartService cartService;
	private ProductService productService;
	
	public void setCartService(CartService cartService) {
		this.cartService = cartService;
	}
	public int getCollectionId() {
		return collectionId;
	}
	public void setCollectionId(int collectionId) {
		this.collectionId = collectionId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public Cart getCart() {
		return cart;
	}
	public void setCart(Cart cart) {
		this.cart = cart;
	}
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	public String show(){
		Map session = ActionContext.getContext().getSession();
		User user = (User) session.get("user_logined");
		if(null==user)return "LOGIN";
		List<Cart> list = cartService.findCollectionByUserId(user.getId());
		if(list.size()>0||list!=null){
			for(Cart collection:list){
				Product product=productService.findById(collection.getProductId());
				collection.setProduct(product);
			}
		}
		session.put("collectionItems", list);
		return "SUCCESS";
	}
	
	
	public String delete(){
		
			if(collectionId!=0){
				cartService.deleteByCartId(collectionId);
			}
		
		return "SUCCESS";
	}
	
	public String add(){
		Map session=ActionContext.getContext().getSession();
		User user = (User)session.get("user_logined");
		
		if(cart == null) cart = new Cart();
		if(user == null) return "LOGIN";
		Integer userId = user.getId();
		cart.setUserId(userId);
		cart.setQuantity(0);
		cart.setIsLike(1);
		if(productId != 0){
			cart.setProductId(productId);
		}
		cartService.save(cart);
		return "SUCCESS";
	}
	
	
	public String moveToCart(){
		Cart cart = cartService.findById(collectionId);
		if(cart==null) cart = new Cart();
		
		cart.setIsLike(0);
		cart.setQuantity(1);
		
		cartService.update(cart);
		return "SUCCESS";
	}
}
