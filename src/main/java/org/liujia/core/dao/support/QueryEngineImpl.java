package org.liujia.core.dao.support;

import java.util.List;

import org.apache.commons.logging.Log;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.liujia.core.util.PageSupport;


@SuppressWarnings("unchecked")
public class QueryEngineImpl<T> implements QueryEngine<T>{
	private SessionFactory sessionFactory;
	private Log logger;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	

	public Object[] getEntity(Class T, String keyword, String filter, String sort,boolean isCache) {
		Session session=sessionFactory.openSession();
		QueryExpressionAnalysis analysis=new QueryExpressionAnalysis();
		StringBuffer hql=analysis.generateQuery(T, keyword, filter, sort);
		System.out.println(hql);
		Query query=session.createQuery(hql.toString());
		query.setCacheable(isCache);
//		List list=query.list();
//		System.out.println(query.list());
		Object[] objects = null;
		if(query.list().size()!=0){
			objects=(Object[]) query.list().get(0);
		}else{
			return null;
		}
		return objects;
	}

	public List<T> getList(Class T, String keyword, String filter,
			String sort,boolean isCache) {
		
		Session session=sessionFactory.openSession();
		QueryExpressionAnalysis analysis=new QueryExpressionAnalysis();
		StringBuffer hql=analysis.generateQuery(T, keyword, filter, sort);
		System.out.println(hql);
		Query query=session.createQuery(hql.toString());
		
		query.setCacheable(isCache);
		
		return query.list();
	}

	
	public PageSupport getPage(Class T, String keyword, String filter, String sort,boolean isCache,int page) {
		
		Session session=sessionFactory.openSession();
		QueryExpressionAnalysis analysis=new QueryExpressionAnalysis();
		StringBuffer hql=analysis.generateQuery(T, keyword, filter, sort);
		System.out.println(hql);
		Query query=session.createQuery(hql.toString());
		
		query.setCacheable(isCache);
		
		PageSupport pageSupport=new PageSupport(query, page, 12, logger,T);
		return pageSupport;
	}
	


	
}
