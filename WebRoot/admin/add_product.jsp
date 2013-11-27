<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<script type="text/javascript" src="../front/js/jquery-1.6.4.min.js"></script>
	<script src="js/jquery.multi.js" type="text/javascript"></script>  
	<script src="js/jquery.metadata.js" type="text/javascript"></script>  
</head>
<body>
	<center>
	<h1>添加商品</h1>
	<hr>
	</center>
	<div style="position:relative; left:300px;">
		<form action="admin/add_product.action" method="POST" enctype ="multipart/form-data" >
		商品类别：<select id="categorySelector" name="product.category.id">
		          </select><br>
		 商品名称：<input type="text" name="product.name"><br>
		 商品价格：<input type="text" name="product.price"><br>
		 商品图片：<input name="upload" type="file" class="multi {accept:'gif|jpg', max:7, STRING:{ remove:'移除',  selected:'Selecionado: $file', denied:'该格式文件不能上传 $ext!', duplicate:'该文件已经添加:\n$file!'}}" id="file-upload"> <br>
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
		
	</script>
</html>