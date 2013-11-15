package org.liujia.core.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.liujia.core.dao.support.QueryEngine;
import org.liujia.core.dao.support.QueryExpressionAnalysis;
import org.liujia.core.service.QueryService;
import org.liujia.core.util.PageSupport;
import org.liujia.shop.model.Product;

public class QueryServiceImpl<T> implements QueryService<T> {
	
	private QueryEngine queryEngine;
	
	public void setQueryEngine(QueryEngine queryEngine) {
		this.queryEngine = queryEngine;
	}

	public Map<String, Object> getEntity(Class T, String keyword, String filter, String sort,boolean isCache) {
		Object[] objects=queryEngine.getEntity(T, keyword, filter, sort, isCache);
		Map<String,Object> map=recordToMap(objects, T);
		return map;
	}

	public List<Map<String, Object>> getList(Class T, String keyword, String filter,
			String sort,boolean isCache) {
		List recordList=queryEngine.getList(T, keyword, filter, sort, isCache);
		List<Map<String, Object>> list=recordToList(recordList, T);
		return list;
	}

	public PageSupport getPage(Class T, String keyword, String filter, String sort,boolean isCache,int page) {
		
		return queryEngine.getPage(T, keyword, filter, sort, isCache,page);
	}
	
	
//	private List<Map<String, Object>> initial(List<Map<String, Object>> list){
//		
//		for(Map<String, Object> product:list){
//			String imgPath=(String) product.get("picture");
//			product.put("ouPicture", (Object)imgPath.replace("in", "ou"));
//			product.put("frPicture", (Object)imgPath.replace("in", "fr"));
//		}
//		return list;
//	}
	
	private List<Map<String, Object>> recordToList(List recordList,Class T) {
		List<Map<String, Object>> list = null;
		QueryExpressionAnalysis analysis=new QueryExpressionAnalysis();
		if (recordList != null && recordList.size() > 0) {
			list = new ArrayList<Map<String,Object>>();
			String[] properties = analysis.generaterProperties(T);		// �����õ����еĲ�ѯ����
			Object[] records = recordList.toArray();
			for (int i = 0; i < records.length; i++) {
				// ����ѯ�ļ�¼��װ��Map��,KeyΪ������valueΪ��ѯ���ֶ�ֵ
				Map<String, Object> item = new HashMap<String, Object>();
				Object[] fields = (Object[]) records[i];
				for (int j = 0; j < fields.length; j++) {
					if (j < properties.length) {
						if (fields[j] != null && !"".equals(fields[j].toString())) {
							item.put(properties[j], fields[j]);
						} else {
							item.put(properties[j], null);
						}
					}
				}
				list.add(item);
			}
		}
		return list;
	} 
	
	private Map<String, Object> recordToMap(Object[] objects,Class T){
		QueryExpressionAnalysis analysis = new QueryExpressionAnalysis();
		Map<String, Object> item = new HashMap<String, Object>();
		if(objects!=null){
			String[] properties=analysis.generaterProperties(T);
			for(int i=0; i<objects.length;i++){
				if(i<properties.length){
					if(objects[i]!=null&&!"".equals(objects[i].toString())){
						item.put(properties[i], objects[i]);
					}else{
						item.put(properties[i], null);
					}
				}
			}
		}
		return item;
	}
	
}
