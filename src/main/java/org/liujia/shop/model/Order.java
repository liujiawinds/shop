package org.liujia.shop.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name="t_order")
public class Order implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -543213844077566053L;
	private Integer id;
	private Date orderDate;
	private String status;
	private String payway;
	private String remark;
	private Date sendDate;
	private String destination;
	private float totoalPrice;
	private List<Cart> cart=new ArrayList<Cart>();
	private Integer userId;
	private List<Product> product;
	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getPayway() {
		return payway;
	}
	public void setPayway(String payway) {
		this.payway = payway;
	}
	
	@Column(name="order_time",nullable=false)
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	@Column(name="send_time")
	public Date getSendDate() {
		return sendDate;
	}
	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}
	
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@Column(name="address")
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	@Column(name="total_price",nullable=false)
	public float getTotoalPrice() {
		return totoalPrice;
	}
	public void setTotoalPrice(float totoalPrice) {
		this.totoalPrice = totoalPrice;
	}
	
	@OneToMany(targetEntity=Cart.class,cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="oid",updatable=false)
	public List<Cart> getCart() {
		return cart;
	}
	public void setCart(List<Cart> cart) {
		this.cart = cart;
	}
	@Column(name="user_id",nullable=false)
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	@Transient
	public List<Product> getProduct() {
		return product;
	}
	public void setProduct(List<Product> product) {
		this.product = product;
	}
	
}
