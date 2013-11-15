package org.liujia.core.dao.support;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.liujia.core.util.Path;
import org.liujia.shop.model.User;



/**
 * 查询配置文件的管理

 * 
 */
@SuppressWarnings("unchecked")
public final class QueryParamConfig {
	// 当前查询的实体类
	private String entityClassName = null;
	// 查询池,每个查询类一个实例

	private static Map<String,QueryParamConfig> spool =new ConcurrentHashMap<String,QueryParamConfig>(); 
	// 查询配置文件的数据

	private static List<Map<String, Object>> classList = null;
	
	/** 私有构造方法 */
	private QueryParamConfig(String entityClassName){
		this.entityClassName = entityClassName;
		if(classList == null) {
			loadXML();
		}
	}
	
	/***
	 * 解析XML
	 */
	private synchronized void loadXML() {
		classList = new ArrayList<Map<String,Object>>();
		long startTime = System.currentTimeMillis();
		
		// 配置文件路径
		String path=Path.CLASS_PATH.toString().replaceAll("%20", " ") + "query-engine.xml";
		
		// 构建XML文档对象
		Document document = null;
		try {
			document = new SAXReader().read(new File(path));
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 解析文档对象
		Element queryElement = document.getRootElement();
		List<Element> classElements = queryElement.elements();
		// 解析元素<class>
		for (Element classElement : classElements) {
			Map<String, Object> classMap = new HashMap<String, Object>();
			classMap.put("name",classElement.attributeValue("name"));
			classMap.put("alias",classElement.attributeValue("alias"));
			classMap.put("paramList", new ArrayList<Map<String,String>>());
			// 解析元素<param>
			List<Map<String, String>> paramList = (List<Map<String, String>>) classMap.get("paramList");
			List<Element> paramElements = classElement.elements();
			for (Element paramElement : paramElements) {
				Map<String, String> paramMap = new HashMap<String, String>();
				paramMap.put("name", paramElement.attributeValue("name"));
				paramMap.put("property", paramElement.attributeValue("property"));
				paramMap.put("alias", paramElement.attributeValue("alias"));
				paramMap.put("is-select", paramElement.attributeValue("is-select"));
				paramMap.put("is-relation", paramElement.attributeValue("is-relation"));
				paramList.add(paramMap);
			}
			classList.add(classMap);
		}
		System.out.println("解析配置文件[query_config.xml]共用时: " + (System.currentTimeMillis() - startTime + " 毫秒"));
	}
	
	/**
	 * 获得当前类的实例对象
	 * 
	 * @return QueryParamConfig
	 */
	public synchronized static QueryParamConfig getInstance(String entityClassName) {
		QueryParamConfig queryConfig = spool.get(entityClassName);
		if (queryConfig == null) {
			queryConfig = new QueryParamConfig(entityClassName);
			spool.put(entityClassName, queryConfig);
		}
		return queryConfig;
	}
	
	/**
	 * 根据查询参数名查找属性名
	 * 
	 *  @param queryName String 查询参数名

	 *  @return String 属性名
	 */
	public String queryPropertyName(String paramName) {
		for (Map<String, Object> classMap : classList) {
			if (entityClassName.equals(classMap.get("name"))) {
				List<Map<String, String>> paramList = (List<Map<String, String>>) classMap.get("paramList");
				for (Map<String, String> paramMap : paramList) {
					if (paramName.equals(paramMap.get("name"))) {
						return classMap.get("alias") + "." + paramMap.get("property");
					}
				}
			}
		}
		return null;
	}
	
	/**
	 * 根据属性名查找属性别名 
	 * 
	 * @param propertyName String 属性名
	 * @return String 属性的别名
	 */
	public String queryPropertyAlias(String propertyName) {
		for (Map<String, Object> classMap : classList) {
			if (entityClassName.equals(classMap.get("name"))) {
				List<Map<String, String>> paramList = (List<Map<String, String>>) classMap.get("paramList");
				for (Map<String, String> paramMap : paramList) {
					if (propertyName.equals(paramMap.get("property"))) {
						return paramMap.get("alias");
					}
				}
			}
		}
		return null;
	}
	
	/**
	 * 查询实体类的别名 
	 */
	public String getEntityAlias() {
		for (Map<String, Object> classMap : classList) {
			if (entityClassName.equals(classMap.get("name"))) {
				return (String) classMap.get("alias");
			}
		}
		return "";
	}
	
	/**
	 * 获得需要显示的所有属性 
	 */
	public String getSelectProperties() {
		StringBuilder s = new StringBuilder();
		for (Map<String, Object> classMap : classList) {
			if (entityClassName.equals(classMap.get("name"))) {
				List<Map<String, String>> paramList = (List<Map<String, String>>) classMap.get("paramList");
				for (Map<String, String> paramMap : paramList) {
					if (paramMap.get("is-select") != null && paramMap.get("is-select").equals("true")) {
						s.append(classMap.get("alias") + "." + paramMap.get("property") + ",");
					}
				}
			}
		}
		return (s.length() > 0) ? s.substring(0, s.length() - 1) : "";
	}
	
	/**
	 * 查询主控类中关联查询的属性

	 * 
	 * @return Iterator<String>
	 */
	public Iterator<String> queryRelationProperty() {
		Set<String> properties = new HashSet<String>();
		for (Map<String, Object> classMap : classList) {
			if (entityClassName.equals(classMap.get("name"))) {
				List<Map<String, String>> paramList = (List<Map<String, String>>) classMap.get("paramList");
				for (Map<String, String> paramMap : paramList) {
					if ("true".equals(paramMap.get("is-relation"))) {
						properties.add(classMap.get("alias") + "." + paramMap.get("property"));
					}
				}
			}
		}
		return properties.iterator();
	}
	public static void main(String[] args) {
		QueryParamConfig queryParamConfig=QueryParamConfig.getInstance(User.class.getName());
		queryParamConfig.loadXML();
		System.out.println(queryParamConfig.getSelectProperties());
	}
	
}
