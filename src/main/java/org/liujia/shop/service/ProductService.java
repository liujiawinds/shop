package org.liujia.shop.service;


import java.util.List;

import org.liujia.shop.model.Product;
import org.springframework.transaction.annotation.Transactional;



@Transactional
public interface ProductService {
	public Product save(Product product);
	public void update(Product product);
	public void delete(Product product);
	
	public List<Product> search(String keyword);//compass
	public Product findById(Integer productId);
	public Product findProductByName(String productName);
	public List<Product> findByCategoryId(Integer categoryId);
	public List<Product> findAll();
}
