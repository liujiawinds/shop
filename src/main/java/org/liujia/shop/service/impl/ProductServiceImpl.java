package org.liujia.shop.service.impl;


import java.util.List;

import org.liujia.shop.dao.ProductDao;
import org.liujia.shop.model.Product;
import org.liujia.shop.service.ProductService;



public  class ProductServiceImpl implements ProductService{

	private ProductDao productDao;
	

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	public void delete(Product product) {
		productDao.delete(product);
	}


	public Product save(Product product) {
		return productDao.save(product);
	}

	public void update(Product product) {
		productDao.update(product);
	}

	

	public List<Product> search(String keyword) {
		return productDao.searchProduct(keyword);
	}

	public Product findById(Integer productId) {
		return productDao.findById(productId);
	}

	public List<Product> findByCategoryId(Integer categoryId) {
		return productDao.findProductByCategoryId(categoryId);
	}

	public List<Product> findAll() {
		return productDao.findAll();
	}
}
