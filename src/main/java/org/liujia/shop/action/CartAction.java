package org.liujia.shop.action;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.liujia.shop.model.Cart;
import org.liujia.shop.model.Product;
import org.liujia.shop.model.User;
import org.liujia.shop.service.CartService;
import org.liujia.shop.service.ProductService;

import com.opensymphony.xwork2.ActionContext;

public class CartAction {
	private Cart cart;
	private int cartId;
	private int productId;
	private int quantity;
	private float totalPrice;
	private List<Product> list;
	private CartService cartService;
	private ProductService productService;
	
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	public List<Product> getList() {
		return list;
	}
	public void setList(List<Product> list) {
		this.list = list;
	}
	public Cart getCart() {
		return cart;
	}
	public void setCart(Cart cart) {
		this.cart = cart;
	}
	public float getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}
	public void setCartService(CartService cartService) {
		this.cartService = cartService;
	}
	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getCartId() {
		return cartId;
	}
	public void setCartId(int cartId) {
		this.cartId = cartId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String add(){
		
		Map session=ActionContext.getContext().getSession();
		User user= (User) session.get("user_logined");
		if(user==null)return "LOGIN";
		if(cart==null)cart=new Cart();
		Integer userId =  user.getId();
		cart.setUserId(userId);
		if(productId!=0){
			cart.setProductId(productId);
		}
		if(quantity==0){
			quantity=1;
		}
		cart.setQuantity(quantity);
		cart.setIsLike(0);
		cartService.save(cart);
		return "SUCCESS";
	}
	
	
	
	public String delete(){
		if(cartId!=0)
				cartService.deleteByCartId(cartId);
		return "SUCCESS";
	}
	
	public String show(){
		Map<String, Object> session = ActionContext.getContext().getSession();
		User user= (User) session.get("user_logined");
		if(null==user)return "LOGIN";
		Integer userId=user.getId();
		List<Cart> list=cartService.findCartByUserId(userId);
		
		if (list!= null && list.size() > 0) {
			for (Cart cart : list) {
				Product tempProduct = productService.findById(cart.getProductId());
				cart.setProduct(tempProduct);
				Integer quantity=cart.getQuantity();
				float price=tempProduct.getPrice();
				float tempPrice=quantity*price;
				totalPrice+=tempPrice;
			}
		}
		session.put("totalPrice", totalPrice);
		session.put("cartItems", list);
		return "SUCCESS";
	}

	public void modify(){
		HttpServletResponse response = ServletActionContext.getResponse();
		Product product = productService.findById(productId);
		if(cartId!=0&&quantity!=0){
			cartService.modifyQuantity(cartId, quantity);
			float price = product.getPrice();
			Float totalPrice=price*quantity;
			try {
				response.getWriter().write(totalPrice.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public String moveToCollection(){
		Map session = ActionContext.getContext().getSession();
		User user = (User) session.get("user_logined");
		Integer userId = user.getId();
		if(userId==null) return "LOGIN";
		Cart cart = cartService.findById(cartId);		

		cart.setIsLike(1);
		cart.setQuantity(0);
		
		cartService.update(cart);
		return "SUCCESS";
	}
}
