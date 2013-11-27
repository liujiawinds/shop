<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
  	<script type="text/javascript" src="<%=basePath %>admin/js/jquery-1.9.1.js"></script>
  </head>
  	
  <body>
	<input name="user.id" id="userId">
	<button id="search" onclick="return false;">搜索</button>
  </body>
  <script type="text/javascript">
  	$("#search").click(function(){
  		var userId = $("#userId").val();
  		$.ajax({
			url:'<%=basePath%>admin/getUserInfoAsync.action',
			type:'post',
			data:{
				userId:userId
			},
			success:function(data){
	  			var user = eval('('+data+')');
	  			if(confirm("确定要重置用户   "+user.username+"  的密码？")){
	  					
	  			}
			}
			});
  	});
  </script>
</html>
