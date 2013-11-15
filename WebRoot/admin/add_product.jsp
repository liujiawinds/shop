<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<script type="text/javascript" src="../front/js/jquery-1.6.4.min.js"></script>
</head>
<body>
	<center>
	<h1>添加商品</h1>
	<hr>
	</center>
	<div style="position:relative; left:300px;">
		<form action="admin/add_product.action" method="POST" enctype ="multipart/form-data" >
		商品类别：<select id="categorySelector">
		          </select><br>
		 商品名称：<input type="text" name="product.name"><br>
		 商品价格：<input type="text" name="product.price"><br>
		 商品图片：<input type="file" name="myFile" id="myFile"><br>
		 设&nbsp;计&nbsp;师：<input name="product.designer"><br>
		 商品数量：<input name="product.amount"><br>
		 商品描述：<textarea rows="5" cols="35" name="product.description"></textarea><br>
		 	<input type="submit" value="确定">
		 	<input type="reset"  value="取消">
		</form>
	</div>
</body>
	<script type="text/javascript">
		$(document).ready(function(){
			$.get('/shop/admin/getCategory.action',function(retStr){ 
				var data = eval('(' + retStr + ')'); 
	           for(var i in data){
	        	   var html = "<option value='"+data[i].id+"'>"+data[i].name+"</option>";
	        	   $("#categorySelector").append(html);
	           }
			});
		});
		
		$("#myFile").change(function(){
			var tempSrc =  $(this).val();
			alert(tempSrc);
			$(this).after("<img src='"+$(this).val()+"' style='width:50px;height:50px;'/>");
		});
	</script>
</html>