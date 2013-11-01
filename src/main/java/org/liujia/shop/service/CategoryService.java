package org.liujia.shop.service;


import java.util.List;

import org.liujia.shop.model.Category;
import org.springframework.transaction.annotation.Transactional;


@Transactional
public interface CategoryService {
	public Category findCategoryById(Integer id);
	public List<Category> findAll();
}
