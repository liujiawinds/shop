<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="org.liujia.shop.model.*" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>show user</title>
</head>
<body>
<CENTER>
<h1>用户信息查看</h1>
<hr>
	<table width="60%" border="2">
	<tr>
		<td>用户ID</td>
		<td>用户名</td>
		<td>地址</td>
		<td>电话</td>
		<td>注册日期</td>
		<td>邮箱</td>
		<td>账户金额</td>
	</tr>
	<c:forEach var="user" items="${usersInDb}">
		<tr><td>${user.id }</td><td>${user.username }</td><td>${user.address }</td><td>${user.telephone }</td>
			<td>${user.registDate }</td><td>${user.email }</td><td>${user.balance }</td>
		</tr>
	</c:forEach>
	</table>
	</CENTER>
</body>
</html>