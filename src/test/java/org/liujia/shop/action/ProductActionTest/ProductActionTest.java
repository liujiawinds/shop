package org.liujia.shop.action.ProductActionTest;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.liujia.shop.model.Product;
import org.liujia.shop.service.ProductService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ProductActionTest {
	
	private ClassPathXmlApplicationContext context;
	private SessionFactory sessionFactory;
	private ProductService productService;
	private Session session;
	@Before
	public void before(){
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		productService = (ProductService) context.getBean("productService");
		session = sessionFactory.openSession();
	}
	
	@Test
	public void testSearch(){
		List<Product> list = productService.search("357");
		for(Product product : list){
			System.out.println(product.getName());
		}
	}
}
