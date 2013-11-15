package org.liujia.shop.action;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
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
		List<Order> orderList = orderService.findByUserId(userId);
		if(orderList.size() > 0){
			Order order = orderList.get(orderList.size()-1);
			List<Cart> cartItems = order.getCart();
			List<Product> productList = new ArrayList<Product>();
			for(Cart cart : cartItems){
				Product product = productService.findById(cart.getProductId());
				productList.add(product);
			}
			order.setProduct(productList);
			session.put("order", order);
		}
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
		order.setOrderTime(new Date());
		List<Cart> cartItems = (List<Cart>) session.get("cartItems");
		order.setCart(cartItems);
		float totalPrice = Float.valueOf(session.get("totalPrice").toString());
		order.setTotoalPrice(totalPrice);
		order.setAddress(user.getAddress());
		order.setPayment("货到付款");
		order.setStatus("待发货");
		order.setUserId(user.getId());
		orderService.save(order);
		order = orderService.findByOrderTime(order.getOrderTime());//FIXME
		if(cartItems!=null){
			for(Cart cart : cartItems){
				cart.setOrderId(order.getId());
				cartService.update(cart);
			}
			session.remove("cartItems");
		}
		return "SUCCESS";
	}
	
}
