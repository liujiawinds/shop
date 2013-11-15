package org.liujia.shop.service;


import org.liujia.shop.model.Category;
import org.springframework.transaction.annotation.Transactional;


@Transactional
public interface CategoryService {
	public Category findCategoryById(Integer id);
}
