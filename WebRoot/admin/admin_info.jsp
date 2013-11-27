<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
 	<base href="<%=basePath%>">
  	<script type="text/javascript" src="admin/js/jquery-1.9.1.js"></script>
  </head>
  
  <body>
	  <center>
		  	<table>
		  		<tr>
		  			<th>管理员编号</th>
		  			<th>管理员用户名</th>
		  			<th>上次登入时间</th>
		  		</tr>
		  		<tbody id="adminInfoTable"></tbody>
		  	</table>
	  	</center>
  </body>
  <script type="text/javascript">
	  $(document).ready(function(){
			$.get('/shop/admin/adminLoginInfo.action',function(retStr){ 
				var data = eval('(' + retStr + ')'); 
	         	var html = "";
				for(var i in data){
					var date;
					if(data[i].lastLoginTime!=null){
						date = new Date(data[i].lastLoginTime.time);
					}
					html += "<tr><td>"+data[i].id+"</td><td>"+data[i].name+"</td><td><fmt:formatDate value='"+(data[i].lastLoginTime==undefined?"未记录":date)+"' pattern='yyyy-MM-dd HH:mm:ss'/></td><tr>"
	          }
				$("#adminInfoTable").html(html);
			});
		});
  </script>
</html>
