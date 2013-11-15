package org.liujia.shop.action;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;
import org.liujia.core.service.QueryService;
import org.liujia.shop.model.Category;
import org.liujia.shop.service.CategoryService;


public class CategoryAction {
	
	private CategoryService categoryService;
	private QueryService<Category> queryService;
	
	public void getCategory(){
		List<Map<String ,Object>> categoryList = queryService.getList(Category.class, "", "c.id:>4", "", true);
		String retStr = JSONArray.fromObject(categoryList).toString();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		try {
			response.getWriter().write(retStr);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setQueryService(QueryService<Category> queryService) {
		this.queryService = queryService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	
}
