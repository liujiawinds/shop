package org.liujia.core.service;

import java.util.List;
import java.util.Map;

import org.liujia.core.util.PageSupport;

public interface QueryService<T> {
	
	public PageSupport getPage(Class T, String keyword, String filter, String sort,boolean isCache,int page);
	
	public List<Map<String, Object>> getList(Class T, String keyword, String filter, String sort,boolean isCache);
	
	public Map<String, Object> getEntity(Class T, String keyword, String filter, String sort,boolean isCache);
	
}
