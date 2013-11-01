<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body>
	<center>
	<h1>添加商品</h1>
	<hr>
	</center>
	<div style="position:relative; left:300px;">
		<form action="admin/add_product.action" method="POST" enctype ="multipart/form-data" >
		商品类别：<select>
					<s:iterator value="#session.categoryList" id="category">
					<option value="${category.id }">${category.name }</option>
					</s:iterator>
		          </select><br>
		 商品名称：<input type="text" name="product.name"><br>
		 商品价格：<input type="text" name="product.price"><br>
		 商品图片：<input type="file" name="myFile"><br>
		 设计师：<input name="product.designer"><br>
		 商品数量：<input name="product.amount"><br>
		 商品描述：<textarea rows="5" cols="35" name="product.description"></textarea><br>
		 	<input type="submit" value="确定">
		 	<input type="reset"  value="取消">
		</form>
	</div>
</body>
</html>