package org.liujia.core.dao.support;

import java.util.List;

import org.liujia.core.util.PageSupport;

public interface QueryEngine<T> {
	
	
	
	/** 使用传入的参数动态的拼接sql语句，然后根据sql语句，使用hibernate的createQuery方法查询数据库，
	 * 获取一个object数组类型的实体。
	 * @param T  查询的表所对应的实体类
	 * @param keyword 关键词，如like
	 * @param filter 过滤  ，如 id=1
	 * @param sort 排序 ， 如 order by id desc;
	 * @param isCache  是否缓存
	 * @return Object[]&nbsp;entity
	 */
	public Object[] getEntity(Class T, String keyword, String filter, String sort,boolean isCache);
	
	
	
	/**用传入的参数动态的拼接sql语句，然后根据sql语句，使用hibernate的createQuery方法查询数据库，
	 * 获取一个List类型的返回值。
	 * @param T  查询的表所对应的实体类
	 * @param keyword 关键词，如like
	 * @param filter 过滤  ，如 id=1
	 * @param sort 排序 ， 如 order by id desc;
	 * @param isCache  是否缓存
	 * @return
	 */
	public List<T> getList(Class T, String keyword, String filter,
			String sort,boolean isCache);
	
	
	
	/** 用传入的参数动态的拼接sql语句，然后根据sql语句，使用hibernate的createQuery方法查询数据库，
	 * 	再将query注入到pagesupport的构造方法里面去，得到pagesupport对象。
	 *  可以使用pageSupport.getList()获得查询结果。
	 *	@param T  查询的表所对应的实体类
	 * @param keyword 关键词，如like
	 * @param filter 过滤  ，如 id=1
	 * @param sort 排序 ， 如 order by id desc;
	 * @param isCache  是否缓存
	 * @return
	 */
	public PageSupport getPage(Class T, String keyword, String filter, String sort,boolean isCache,int page);
	
}
