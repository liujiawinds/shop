package org.liujia.shop.service.impl;


import java.util.ArrayList;
import java.util.List;

import org.compass.core.Compass;
import org.compass.core.CompassHits;
import org.compass.core.CompassQueryBuilder;
import org.compass.core.CompassSession;
import org.compass.core.CompassTemplate;
import org.liujia.shop.dao.ProductDao;
import org.liujia.shop.model.Product;
import org.liujia.shop.service.ProductService;



public  class ProductServiceImpl implements ProductService{

	private ProductDao productDao;
	private CompassTemplate compassTemplate;
	
	public void setCompassTemplate(CompassTemplate compassTemplate) {
		this.compassTemplate = compassTemplate;
	}

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	

	public List<Product> search(String keyword){
		Compass compass = compassTemplate.getCompass();
		CompassSession session = compass.openSession();
		CompassQueryBuilder compassQueryBuilder = session.queryBuilder();
		
		CompassHits compassHits=compassQueryBuilder.queryString("name:"+keyword).toQuery().hits();
		List<Product> list = new ArrayList<Product>();
		for(int i =0 ; i<compassHits.length(); i++){
			list.add((Product) compassHits.data(i));
		}
		
		return list;
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
	public Product findById(Integer productId) {
		return productDao.findById(productId);
	}

	public List<Product> findByCategoryId(Integer categoryId) {
		return productDao.findProductByCategoryId(categoryId);
	}

	public List<Product> findAll() {
		return productDao.findAll();
	}

	public Product findProductByName(String productName) {
		return productDao.findProductByName(productName);
	}
	
}
