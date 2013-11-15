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
 * ��ѯ�����ļ��Ĺ���

 * 
 */
@SuppressWarnings("unchecked")
public final class QueryParamConfig {
	// ��ǰ��ѯ��ʵ����
	private String entityClassName = null;
	// ��ѯ��,ÿ����ѯ��һ��ʵ��

	private static Map<String,QueryParamConfig> spool =new ConcurrentHashMap<String,QueryParamConfig>(); 
	// ��ѯ�����ļ�������

	private static List<Map<String, Object>> classList = null;
	
	/** ˽�й��췽�� */
	private QueryParamConfig(String entityClassName){
		this.entityClassName = entityClassName;
		if(classList == null) {
			loadXML();
		}
	}
	
	/***
	 * ����XML
	 */
	private synchronized void loadXML() {
		classList = new ArrayList<Map<String,Object>>();
		long startTime = System.currentTimeMillis();
		
		// �����ļ�·��
		String path=Path.CLASS_PATH.toString().replaceAll("%20", " ") + "query-engine.xml";
		
		// ����XML�ĵ�����
		Document document = null;
		try {
			document = new SAXReader().read(new File(path));
		} catch (Exception e) {
			e.printStackTrace();
		}
		// �����ĵ�����
		Element queryElement = document.getRootElement();
		List<Element> classElements = queryElement.elements();
		// ����Ԫ��<class>
		for (Element classElement : classElements) {
			Map<String, Object> classMap = new HashMap<String, Object>();
			classMap.put("name",classElement.attributeValue("name"));
			classMap.put("alias",classElement.attributeValue("alias"));
			classMap.put("paramList", new ArrayList<Map<String,String>>());
			// ����Ԫ��<param>
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
		System.out.println("���������ļ�[query_config.xml]����ʱ: " + (System.currentTimeMillis() - startTime + " ����"));
	}
	
	/**
	 * ��õ�ǰ���ʵ������
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
	 * ���ݲ�ѯ����������������
	 * 
	 *  @param queryName String ��ѯ������

	 *  @return String ������
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
	 * �����������������Ա��� 
	 * 
	 * @param propertyName String ������
	 * @return String ���Եı���
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
	 * ��ѯʵ����ı��� 
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
	 * �����Ҫ��ʾ���������� 
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
	 * ��ѯ�������й�����ѯ������

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
