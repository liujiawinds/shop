package org.liujia.core.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import java.lang.reflect.ParameterizedType;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.liujia.core.dao.GenericDao;



@SuppressWarnings("unchecked")
public class GenericDaoHibImpl<T,PK extends Serializable> extends HibernateDaoSupport implements GenericDao<T, PK> {
	private Class<T> clazz;

	public GenericDaoHibImpl() {
		clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
	}
	public T findById(PK id) {
		return (T) getHibernateTemplate().get(clazz, id);
	}

	public List<T> findAll() {
		return getHibernateTemplate().find("from " + clazz.getName());
	}

	public T save(T entity) {
		getHibernateTemplate().save(entity);
		return entity;
	}

	public void update(T entity) {
		getHibernateTemplate().update(entity);
	}

	public void delete(T entity) {
		getHibernateTemplate().delete(entity);
	}
	
	public void deleteById(Integer id) {
		getHibernateTemplate().delete(getHibernateTemplate().load(clazz, id));
	}
	
	public Object queryObject(final String hql, final Object[] params) {
		return getHibernateTemplate().execute(new HibernateCallback<Object>() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				if (params != null) {
					for (int i = 0; i < params.length; i++) {
						query.setParameter(i, params);
					}
				}
				return query.uniqueResult();
			}
		});
	}
	

}
