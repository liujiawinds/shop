package org.liujia.core.dao.support;

import java.util.List;

import org.liujia.core.util.PageSupport;

public interface QueryEngine<T> {
	
	
	
	/** ʹ�ô���Ĳ�����̬��ƴ��sql��䣬Ȼ�����sql��䣬ʹ��hibernate��createQuery������ѯ���ݿ⣬
	 * ��ȡһ��object�������͵�ʵ�塣
	 * @param T  ��ѯ�ı�����Ӧ��ʵ����
	 * @param keyword �ؼ��ʣ���like
	 * @param filter ����  ���� id=1
	 * @param sort ���� �� �� order by id desc;
	 * @param isCache  �Ƿ񻺴�
	 * @return Object[]&nbsp;entity
	 */
	public Object[] getEntity(Class T, String keyword, String filter, String sort,boolean isCache);
	
	
	
	/**�ô���Ĳ�����̬��ƴ��sql��䣬Ȼ�����sql��䣬ʹ��hibernate��createQuery������ѯ���ݿ⣬
	 * ��ȡһ��List���͵ķ���ֵ��
	 * @param T  ��ѯ�ı�����Ӧ��ʵ����
	 * @param keyword �ؼ��ʣ���like
	 * @param filter ����  ���� id=1
	 * @param sort ���� �� �� order by id desc;
	 * @param isCache  �Ƿ񻺴�
	 * @return
	 */
	public List<T> getList(Class T, String keyword, String filter,
			String sort,boolean isCache);
	
	
	
	/** �ô���Ĳ�����̬��ƴ��sql��䣬Ȼ�����sql��䣬ʹ��hibernate��createQuery������ѯ���ݿ⣬
	 * 	�ٽ�queryע�뵽pagesupport�Ĺ��췽������ȥ���õ�pagesupport����
	 *  ����ʹ��pageSupport.getList()��ò�ѯ�����
	 *	@param T  ��ѯ�ı�����Ӧ��ʵ����
	 * @param keyword �ؼ��ʣ���like
	 * @param filter ����  ���� id=1
	 * @param sort ���� �� �� order by id desc;
	 * @param isCache  �Ƿ񻺴�
	 * @return
	 */
	public PageSupport getPage(Class T, String keyword, String filter, String sort,boolean isCache,int page);
	
}
