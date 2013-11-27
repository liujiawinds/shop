<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
  </head>
  
  <body>
  		<center>
  		<h1>添加管理员</h1>
  		<hr>
  		<form action="admin/add_admin.action" method="post">
  			登入用户名：<input type="text" name="admin.name" />
  			密码：<input type="password" name="admin.password" />
  			确认密码：<input type="password" name="confirmPassword" />
  			权限级别：<select name="admin.type">
  						<option value="normal">普通管理员</option>	
  						<option value="super">超级管理员</option>	
  					</select>
  					<input type="submit" value="提交">
  		</form>
  		<c:if test="${msg!=null }">
  			<span style="color: red;">${msg }</span>
  		</c:if>
  		</center>
  		
  </body>
</html>
