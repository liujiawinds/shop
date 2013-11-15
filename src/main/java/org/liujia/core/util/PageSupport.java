package org.liujia.core.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.ScrollableResults;
import org.liujia.core.dao.support.QueryExpressionAnalysis;
import org.liujia.shop.model.Product;

/**
 * This class provides pagination for displaying results from a large result set
 * over a number of pages (i.e. with a given number of results per page).
 * 
 * Taken from http://blog.hibernate.org/cgi-bin/blosxom.cgi/2004/08/14#fn.html.
 * 
 * @author Gavin King 
 * @author Eric Broyles 
 */
public class PageSupport
{

    @SuppressWarnings("unchecked")
	private List results;
    private int pageSize;
    private int page;
    private ScrollableResults scrollableResults;
    private int totalResults = -1;
    private int totalPage = 0;
    
    private Log logger;
    /**
     * Construct a new Page. Page numbers are zero-based, so the
     * first page is page 0.
     * 
     * @param query
     * the Hibernate Query
     * @param page
     * the page number (zero-based)
     * @param pageSize
     * the number of results to display on the page
     */
    @SuppressWarnings("unchecked")
	public PageSupport(Query query, int page, int pageSize,Log logger,Class T)
    {
        //page--;
    	//if(page<0)page=0;
    	this.page = page;
        this.pageSize = pageSize;
        this.logger = logger;
        try
        {
            scrollableResults = query.scroll();
            /*
             * We set the max results to one more than the specfied pageSize to
             * determine if any more results exist (i.e. if there is a next page
             * to display). The result set is trimmed down to just the pageSize
             * before being displayed later (in getList()).
             */
            
            List lst = query.setFirstResult((page - 1) * pageSize).setMaxResults(pageSize).list();
            List<Map<String, Object>> list = null;
    		QueryExpressionAnalysis analysis=new QueryExpressionAnalysis();
    		if (lst != null && lst.size() > 0) {
    			list = new ArrayList<Map<String,Object>>();
    			String[] properties = analysis.generaterProperties(T);		// 解析得到所有的查询属性
    			Object[] records = lst.toArray();
    			for (int i = 0; i < records.length; i++) {
    				// 将查询的记录封装到Map中,Key为属性名，value为查询的字段值
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
            results = list;
        }
        catch (HibernateException e)
        {
            logger.error(
                    "Failed to get paginated results: " + e.getMessage());
            e.printStackTrace();
        }

    }

    public boolean isFirstPage()
    {
        return page == 0;
    }

    public boolean isLastPage()
    {
        return page >= getLastPageNumber();
    }

    public boolean hasNextPage()
    {
        return results.size() > pageSize;
    }

    public boolean hasPreviousPage()
    {
        return page > 0;
    }

    public int getLastPageNumber()
    {
        /*
         * We use the Math.floor() method because page numbers are zero-based
         * (i.e. the first page is page 0).
         */
        double totalResults = new Integer(getTotalResults()).doubleValue();
        return new Double(Math.floor(totalResults / pageSize)).intValue();
    }

    @SuppressWarnings("unchecked")
	public List getList()
    {
        /*
         * Since we retrieved one more than the specified pageSize when the
         * class was constructed, we now trim it down to the pageSize if a next
         * page exists.
         */
    	List lst = hasNextPage() ? results.subList(0, pageSize) : results;
        return lst;
    }

    public int getTotalResults()
    {
        try
        {
            getScrollableResults().last();
            totalResults = getScrollableResults().getRowNumber()+1;
        }
        catch (HibernateException e)
        {
        	logger.error(
                    "Failed to get last row number from scollable results: "
                            + e.getMessage());
        }
        if(totalResults<0) totalResults=0;
        return totalResults;
    }
    
    public int getTotalPage()
    {
    	if(totalResults<0)
    		getTotalResults();
    	totalPage=(int)Math.floor(totalResults/pageSize);
		if(totalResults%pageSize>0) totalPage++;
		if(totalPage==0) totalPage=1;
    	return totalPage;
    }

    public int getFirstResultNumber()
    {
        return page * pageSize + 1;
    }

    public int getLastResultNumber()
    {
        int fullPage = getFirstResultNumber() + pageSize - 1;
        return getTotalResults() < fullPage ? getTotalResults() : fullPage;
    }

    public int getNextPageNumber()
    {
        return page + 1;
    }

    public int getPreviousPageNumber()
    {
        return page - 1;
    }

    protected ScrollableResults getScrollableResults()
    {
        return scrollableResults;
    }

}

