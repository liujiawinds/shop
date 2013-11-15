<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="org.liujia.shop.model.*" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="js/layer/layer.js"></script>
<title>show user</title>
</head>
<body >
<CENTER>
<h1>用户信息查看</h1>
<hr>
	<table width="auto" border="1">
	<tr>
		<th>用户ID</th>
		<th>用户名</th>
		<th>地址</th>
		<th>电话</th>
		<th>注册日期</th>
		<th>邮箱</th>
		<th>账户金额</th>
	</tr>
	<c:forEach var="user" items="${usersInDb}">
		<tr><td>${user.id }</td><td>${user.username }</td><td>${user.address }</td><td>${user.telephone }</td>
			<td><fmt:formatDate value="${user.registDate }" pattern="yyyy-MM-dd HH:mm:ss"/></td><td>${user.email }</td><td>${user.balance }</td>
		</tr>
	</c:forEach>
	</table>
	</CENTER>
</body>
	<script type="text/javascript">
		$("tr").dblclick(function(){
			var id = $(this)[0].children[0].innerText;
			  $.layer({
					type : 2,
					title : '用户基本信息修改',
					iframe : {src : 'getUserInfo.action?userId='+id},
					area : ['500px' , '270px'],
					offset : ['100px','']
				});
		});
	</script>
</html>