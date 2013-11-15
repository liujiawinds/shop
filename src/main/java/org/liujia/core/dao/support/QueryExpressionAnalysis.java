package org.liujia.core.dao.support;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.liujia.core.util.Path;
import org.liujia.shop.model.User;

public final class QueryExpressionAnalysis {
	
	public QueryExpressionAnalysis() {

	}

	public Document document = null;
	StringBuffer hql=null;
	//初始化成员变量
	public void init(String xmlFilePath) throws Exception {
		hql=new StringBuffer();
		SAXReader reader=new SAXReader();
		document=reader.read(new File(xmlFilePath));
	}

	/**生成基本查询语句
	 * @param T
	 * @return select .... from T
	 */
	@SuppressWarnings("unchecked")
	public StringBuffer generateBasicQuery(Class T){//现在的想法是传一个参数进来，选择要查询的class
		Element root=document.getRootElement();
		//迭代选择所需要查询的class
		Element classElement = null;
		for(Iterator iterator = root.elementIterator("class");iterator.hasNext();){
			classElement = (Element) iterator.next();
			if(T.getName().equals(classElement.attributeValue("name"))){
				break;
			}
		}
		
		//获取class节点的name属性值跟alias属性值
		String className=classElement.attributeValue("name");
		String alias=classElement.attributeValue("alias");
		//用一个list来装每个param节点遍历出来的name属性值
		List list=new ArrayList();
		hql.append("select ");//开始进行查询语句的拼接
		
		for(Iterator iterator = classElement.elementIterator("parm");iterator.hasNext();){
			Element element=(Element) iterator.next();
			if(null!=element.attributeValue("is-select")&&element.attributeValue("is-select").equals("false"))
				continue;
			list.add(element.attributeValue("name"));
		}
		//判断这个类的属性值个数是否为空，还有是否有别名，执行以下代码
		if(list.size()!=0&&null!=alias){
			for(Object name:list){
				hql.append(alias+"."+name.toString());
				if(list.indexOf(name)==list.size()-1){
					hql.append(" ");
					break;
				}
				hql.append(", ");
			}
		}
		if(list.size()!=0&&null==alias){
			for(Object name:list){
				hql.append(name.toString());
				if(list.indexOf(name)==list.size()-1){
					hql.append(" ");
					break;
				}
				hql.append(", ");
			}
			return hql.append("from "+className);
		}
		
		hql.append("from "+className +" as " +alias);
		return hql;
	}
	
	
	/**根据输入固定格式的String生成where后面的like部分sql，格式如name|Nike|category|sport
	 * @param input
	 * @return like部分sql
	 */
	@SuppressWarnings("unchecked")
	public StringBuffer generateKeyWord(String keyword){
		if(!keyword.contains("|")||keyword.length()==0)throw new IllegalArgumentException("你输入的格式不对！需用|隔开关键词");
		
		String[] keyWordStrings=keyword.split("\\|");
		Map keyWordPair=new LinkedHashMap<String, String>();
		for(int i=0;i<keyWordStrings.length;i+=2){
			keyWordPair.put(keyWordStrings[i], keyWordStrings[i+1]);
		}
//		System.out.println(keyWordPair);
		Iterator iterator=keyWordPair.entrySet().iterator();
		while(iterator.hasNext()){
			Map.Entry m=(Entry) iterator.next();
			hql.append(m.getKey()+" like '%"+m.getValue()+"%'");
			if(!iterator.hasNext())break;
			hql.append(" and ");
		}
		return hql;
	}
	
	
	
	/**生成过滤部分sql,参数输入格式如,  &nbsp; &nbsp; &nbsp;   id:=17602;price:>50
	 * 
	 *                            还没做between and的情况
	 * @param filter
	 */
	public StringBuffer generateFilter(String filter){
		if(!filter.contains(":<")&&!filter.contains(":=")&&!filter.contains(":>")){
			throw new IllegalArgumentException("请按格式输入过滤参数");
		}
		hql.append(" and ");
		String[] filterStrings=filter.split("\\;");
		for(int i=0;i<filterStrings.length;i++){
			String temp=filterStrings[i].replace(":", "");
			String temp1 = null;
			if(temp.contains("=")){
				temp1=temp.replace("=", "='");
			}
			if(temp.contains(">")){
				temp1=temp.replace(">", ">'");
			}
			if(temp.contains("<")){
				temp1=temp.replace("<", "<'");
			}
			filterStrings[i]=temp1.concat("'");
			hql.append(filterStrings[i]);
			if(i!=filterStrings.length-1){
				hql.append(" and ");
			}
		}
		return hql;
	}
	
	
	
	/**好像order by 后面不能跟多个，也就是说只能输入一个排序方式。
	 * 所以就直接把参数加到了后面。
	 * @param sort 排序方式
	 */
	public StringBuffer generateSort(String sort){
		hql.append(" order by ");
		String[] sortStrings=sort.split("\\;");
		for(int i=0;i<sortStrings.length;i++){
			hql.append(sortStrings[i]);
			if(i!=sortStrings.length-1){
				hql.append(" , ");
			}
		}
		return hql;
	}
	
	
	
	
	/**
	 * 调用Initial跟generateBasicQuery两个方法，生成查询主体，然后进行一系列对关键字，过滤，排序部分sql的拼接
	 * 并生成最终的sql语句
	 * @param T 需要查询的实体类
	 * @param keyword 关键字，如like
	 * @param filter 过滤，如>,=,<,between
	 * @param sort 排序
	 * @return String 类型的sql语句
	 */
	@SuppressWarnings("unchecked")
	public StringBuffer generateQuery(Class T,String keyword,String filter,String sort){
		try {
			String path = Path.CLASS_PATH.toString().replaceAll("%20", " ") + "query-engine.xml";
			init(path);
			if(null!=T)generateBasicQuery(T);
			if(keyword.length()!=0||filter.length()!=0||sort.length()!=0){
				hql.append(" where 1=1 ");
				if(keyword.length()!=0)generateKeyWord(keyword);
				if(filter.length()!=0)generateFilter(filter);
				if(sort.length()!=0)generateSort(sort);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hql;
	}
	
	public String[] generaterProperties(Class T){
		String xmlFilePath=Path.CLASS_PATH.toString().replaceAll("%20", " ") + "query-engine.xml";
		try {
			init(xmlFilePath);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Element rootElement=document.getRootElement();
		Element classElement = null;
		for(Iterator iterator = rootElement.elementIterator("class");iterator.hasNext();){
			classElement = (Element) iterator.next();
			if(T.getName().equals(classElement.attributeValue("name"))){
				break;
			}
		}
		//获取class节点的name属性值跟alias属性值
		String className=classElement.attributeValue("name");
		String[] properties=new String[15];
		int i=0;
		for(Iterator iterator = classElement.elementIterator("parm");iterator.hasNext();){
			Element element=(Element) iterator.next();
			if(null!=element.attributeValue("is-select")&&element.attributeValue("is-select").equals("false"))
				continue;
			properties[i++]=element.attributeValue("name");
		}
		return properties;
	}
	public static void main(String[] args) throws Exception {
		QueryExpressionAnalysis queryEngine =new QueryExpressionAnalysis();
		System.out.println(queryEngine.generateQuery(User.class, "", "username:=liujia;password:=liujia", ""));
		String [] properties=queryEngine.generaterProperties(User.class);
		for(String s:properties){
			if(null==s)break;
			System.out.println(s);
			
		}
	}
	
}
