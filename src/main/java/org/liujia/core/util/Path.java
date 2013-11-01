package org.liujia.core.util;

import java.net.URL;



public class Path<T> {
	
	public static final String CLASS_PATH;
	public static final boolean isLinux;
	
	static {
		URL resource = Path.class.getResource("Path.class");
		String classPath = resource.getPath();
		String className = Path.class.getName().replace('.', '/') + ".class";
		String classesPath = classPath.substring(0, classPath
				.indexOf(className));
		if (System.getProperty("os.name").toUpperCase().indexOf("WINDOWS") != -1
				&& classesPath.startsWith("/")) {
			classesPath = classesPath.substring(1);
			isLinux = false;
		} else {
			isLinux = true;
		}
		CLASS_PATH = classesPath;
	}
//	
//	
//	public Object recordToEntity(Object[] objects){
//
//		QueryExpressionAnalysis analysis = new QueryExpressionAnalysis();
//		if(objects!=null){
//			String[] properties = analysis.generaterProperties(T);
//			try {
//				Object instance = Class.forName(T.getName()).newInstance();
//				for(int i=0; i<objects.length;i++){
//					
//					Method method = T.getMethod("set"+properties[i].substring(0,1).toUpperCase()+properties[i].substring(1), Integer.class);
//					method.invoke(instance, objects[i]);
//				}
//				return instance;
//			} catch (Exception e1) {
//				e1.printStackTrace();
//			}
//			
//			
//		}
//		try {
//
//			T result = null;
//			
//			
//			String[] properties = analysis.generaterProperties(T);
//			for (int i = 0; i < objects.length; i++) {
//				Object[] fields = (Object[]) objects[i];
//				for (int j = 0; j < fields.length; j++) {
//					if (j < properties.length) {
//						if (fields[j] != null && !"".equals(fields[j].toString())) {
//							
//							item.put(properties[j], fields[j]);
//						} else {
//							item.put(properties[j], null);
//						}
//					}
//				}
//			}
//
//		} catch (Exception e) {
//		}		
//		return null;
//	}
}
