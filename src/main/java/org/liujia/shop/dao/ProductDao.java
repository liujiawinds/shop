package org.liujia.shop.dao;



import java.util.List;

import org.liujia.core.dao.GenericDao;
import org.liujia.shop.model.Product;



public interface ProductDao extends GenericDao<Product, Integer> {

	public List<Product> findProductByCategoryId(Integer id);

}
