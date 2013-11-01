package org.liujia.shop.service.impl;

import java.util.List;

import org.liujia.shop.dao.CategoryDao;
import org.liujia.shop.model.Category;
import org.liujia.shop.service.CategoryService;


public class CategoryServiceImpl implements CategoryService{
	private CategoryDao categoryDao;
	
	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}


	public Category findCategoryById(Integer id) {
		return categoryDao.findById(id);
	}


	public List<Category> findAll() {
		return categoryDao.findAll();
	}
	
}
