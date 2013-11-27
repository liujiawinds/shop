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
<h1>商品信息管理</h1>
<hr>
	<table width="80%" border="1" bordercolor="gray">
	<tr>
		<th>商品ID</th>
		<th>商品名</th>
		<th>类别</th>
		<th>价格</th>
		<th>设计师</th>
		<th>剩余数量</th>
		<th>商品描述</th>
	</tr>
	<c:forEach var="product" items="${products}">
		<tr><td width="70px">${product.id }</td><td width="10%">${product.name }</td><td width="10%">${product.category.name }</td><td width="70px">${product.price }</td>
			<td width="10%">${product.designer }</td><td width="70px">${product.amount }</td><td width="auto">${product.description }</td>
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
					iframe : {src : 'front/product_detail.action?requestSource=manage&productId='+id},
					area : ['500px' , '270px'],
					offset : ['100px','']
				});
		});
	</script>
</html>