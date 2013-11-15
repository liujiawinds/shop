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
	//��ʼ����Ա����
	public void init(String xmlFilePath) throws Exception {
		hql=new StringBuffer();
		SAXReader reader=new SAXReader();
		document=reader.read(new File(xmlFilePath));
	}

	/**���ɻ�����ѯ���
	 * @param T
	 * @return select .... from T
	 */
	@SuppressWarnings("unchecked")
	public StringBuffer generateBasicQuery(Class T){//���ڵ��뷨�Ǵ�һ������������ѡ��Ҫ��ѯ��class
		Element root=document.getRootElement();
		//����ѡ������Ҫ��ѯ��class
		Element classElement = null;
		for(Iterator iterator = root.elementIterator("class");iterator.hasNext();){
			classElement = (Element) iterator.next();
			if(T.getName().equals(classElement.attributeValue("name"))){
				break;
			}
		}
		
		//��ȡclass�ڵ��name����ֵ��alias����ֵ
		String className=classElement.attributeValue("name");
		String alias=classElement.attributeValue("alias");
		//��һ��list��װÿ��param�ڵ����������name����ֵ
		List list=new ArrayList();
		hql.append("select ");//��ʼ���в�ѯ����ƴ��
		
		for(Iterator iterator = classElement.elementIterator("parm");iterator.hasNext();){
			Element element=(Element) iterator.next();
			if(null!=element.attributeValue("is-select")&&element.attributeValue("is-select").equals("false"))
				continue;
			list.add(element.attributeValue("name"));
		}
		//�ж�����������ֵ�����Ƿ�Ϊ�գ������Ƿ��б�����ִ�����´���
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
	
	
	/**��������̶���ʽ��String����where�����like����sql����ʽ��name|Nike|category|sport
	 * @param input
	 * @return like����sql
	 */
	@SuppressWarnings("unchecked")
	public StringBuffer generateKeyWord(String keyword){
		if(!keyword.contains("|")||keyword.length()==0)throw new IllegalArgumentException("������ĸ�ʽ���ԣ�����|�����ؼ���");
		
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
	
	
	
	/**���ɹ��˲���sql,���������ʽ��,  &nbsp; &nbsp; &nbsp;   id:=17602;price:>50
	 * 
	 *                            ��û��between and�����
	 * @param filter
	 */
	public StringBuffer generateFilter(String filter){
		if(!filter.contains(":<")&&!filter.contains(":=")&&!filter.contains(":>")){
			throw new IllegalArgumentException("�밴��ʽ������˲���");
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
	
	
	
	/**����order by ���治�ܸ������Ҳ����˵ֻ������һ������ʽ��
	 * ���Ծ�ֱ�ӰѲ����ӵ��˺��档
	 * @param sort ����ʽ
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
	 * ����Initial��generateBasicQuery�������������ɲ�ѯ���壬Ȼ�����һϵ�жԹؼ��֣����ˣ����򲿷�sql��ƴ��
	 * ���������յ�sql���
	 * @param T ��Ҫ��ѯ��ʵ����
	 * @param keyword �ؼ��֣���like
	 * @param filter ���ˣ���>,=,<,between
	 * @param sort ����
	 * @return String ���͵�sql���
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
		//��ȡclass�ڵ��name����ֵ��alias����ֵ
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
