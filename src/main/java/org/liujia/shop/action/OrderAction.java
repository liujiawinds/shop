package org.liujia.shop.action;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.liujia.shop.model.Cart;
import org.liujia.shop.model.Order;
import org.liujia.shop.model.Product;
import org.liujia.shop.model.User;
import org.liujia.shop.service.CartService;
import org.liujia.shop.service.OrderService;
import org.liujia.shop.service.ProductService;

import com.opensymphony.xwork2.ActionContext;

public class OrderAction {
	private Order order;
	private OrderService orderService;
	private CartService cartService;
	private ProductService productService;
	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
	
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public void setCartService(CartService cartService) {
		this.cartService = cartService;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	
	public String show(){
		Map<String ,Object> session = ActionContext.getContext().getSession();
		User user = (User) session.get("user_logined");
		Integer userId = user.getId();
		Order order = orderService.findByUserId(userId);
		List<Cart> cartItems = order.getCart();
		List<Product> productList = new ArrayList<Product>();
		for(Cart cart : cartItems){
			Product product = productService.findById(cart.getProductId());
			productList.add(product);
		}
		order.setProduct(productList);
		session.put("order", order);
		return "SUCCESS";
	}
	
	public String add(){
		Map<String ,Object> session = ActionContext.getContext().getSession();
		User user = (User) session.get("user_logined");
		if(user==null) return "LOGIN";
		if(order==null)
		{
			order = new Order();
		}
		order.setOrderDate(new Date());
		List<Cart> cartItems = (List<Cart>) session.get("cartItems");
		order.setCart(cartItems);
		float totalPrice = Float.valueOf(session.get("totalPrice").toString());
		order.setTotoalPrice(totalPrice);
		order.setDestination(user.getAddress());
		order.setStatus("待完善订单信息");
		order.setUserId(user.getId());
		orderService.save(order);
		order = orderService.findByUserId(user.getId());
		for(Cart cart : cartItems){
			cart.setOrderId(order.getId());
			cartService.update(cart);
		}
		session.remove("cartItems");
		return "SUCCESS";
	}
	
	
	public String modify(){
		return "";
	}
}
