package org.liujia.shop.dao.impl;



import java.util.List;

import org.liujia.core.dao.impl.GenericDaoHibImpl;
import org.liujia.shop.dao.ProductDao;
import org.liujia.shop.model.Product;




public class ProductDaoImpl extends GenericDaoHibImpl<Product, Integer>
		implements ProductDao {
	

	@SuppressWarnings("unchecked")
	public List<Product> findProductByCategoryId(Integer id) {
		List<Product> list = null;
		if(id>4){
			list=getHibernateTemplate().find("from Product as p where p.category.id=?",id);
		}else{
			list=getHibernateTemplate().find("from Product as p where p.category.parentId=?",id);
		}
		
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Product> searchProduct(String keyword) {
		List<Product> list = getHibernateTemplate().find("from Product as p where p.name like '%"+keyword+"%'");
		return list;
	}

}
