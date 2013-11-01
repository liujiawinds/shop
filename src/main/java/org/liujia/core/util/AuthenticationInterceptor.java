package org.liujia.core.util;

import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.liujia.shop.model.Admin;
import org.liujia.shop.model.Privilege;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class AuthenticationInterceptor extends AbstractInterceptor{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		String actionName = invocation.getProxy().getActionName();
		Map<String , Object> session = ActionContext.getContext().getSession();
		Admin admin = (Admin) session.get("admin_logined");
		String role = admin.getType();
		Session querySession = sessionFactory.openSession();
		Query query =querySession.createQuery("from Privilege where Privilege.role="+role);
		Privilege privilege = (Privilege) query.list().get(0);
		String[] operationArray = privilege.getOperation().split("\\,");
		for(String operation : operationArray){
			if(operation.equals(actionName.trim())){
				invocation.invoke();
				break;
			}
		}
		return null;
	}
	
}
