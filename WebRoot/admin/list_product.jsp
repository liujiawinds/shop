<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%pageContext.setAttribute("ctx", request.getContextPath()); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<body>
		<center>
			<h1>全部商品</h1>
			<hr>
			<ul style="list-style:none;display:inline;">
				<c:forEach var="product" items="${products}">
					<li style="margin: 0 0 0 30px;float:left;">
						<a href="<%=path %>/admin/detail_product.action?productId=${product.id }"><img src="../front/images/content/product/${product.id }/${product.id}_in_l.jpg"
							onmouseover="this.src='../front/images/content/product/${product.id }/${product.id}_ou_l.jpg'"
							onmouseout="this.src='../front/images/content/product/${product.id }/${product.id}_in_l.jpg'"
							onerror="this.src='../front/images/content/product/${product.id }/${product.id}_fr_l.jpg'" />
						</a>
						<h2><a href="admin/modify_product.action?productId=${product.id }">${product.name }</a></h2>
                               	<div class="price">$${product.price }</div>
					</li>
				</c:forEach>
			</ul>
		</center>
	</body>
</html>