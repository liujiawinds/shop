package org.liujia.core.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<T,PK extends Serializable> {
	public T save(T entity);
	public void update(T entity);
	public void delete(T entity);
	public void deleteById(Integer id);
	public T findById(PK key);
	public List<T> findAll();	
}
