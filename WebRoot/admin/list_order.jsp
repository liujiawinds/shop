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
<title>show order</title>
</head>
<body >
<CENTER>
<h1>订单信息查看</h1>
<hr>
	<table width="auto" border="1">
	<tr>
		<th>订单ID</th>
		<th>下单时间</th>
		<th>下单用户</th>
		<th>地址</th>
		<th>总价</th>
		<th>付款方式</th>
		<th>当前状态</th>
	</tr>
	<c:forEach var="order" items="${orderList}">
		<tr><td>${order.id }</td><td><fmt:formatDate value="${order.orderTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td><td>${order.userId }</td><td>${order.address }</td>
			<td>￥${order.totalPrice }</td><td>${order.payment }</td><td>${order.status }</td>
		</tr>
	</c:forEach>
	</table>
	</CENTER>
	<div style="position:absolute;top:400px;left:800px;">*  双击修改订单信息</div>
</body>
	<script type="text/javascript">
		$("tr").dblclick(function(){
			var id = $(this)[0].children[0].innerText;
			  $.layer({
					type : 2,
					title : '用户基本信息修改',
					iframe : {src : 'getorderInfo.action?orderId='+id},
					area : ['500px' , '270px'],
					offset : ['100px','']
				});
		});
	</script>
</html>