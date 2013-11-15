<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>修改用户信息</title>
    
  </head>
  <body>
  	<form action="admin/saveUserInfo.action" method="post" target="_parent">
  		<input name="user.id" value="${user.id }" type="hidden">
  		用户名：<input name="user.username" value="${user.username }"><br>
	  	邮&nbsp;&nbsp;箱：<input name="user.email" value="${user.email }"><br>
	  	电&nbsp;&nbsp;话：<input name="user.telephone" value="${user.telephone }">
	  	<input type="submit" value="提交">
  	</form>
  </body>
</html>
