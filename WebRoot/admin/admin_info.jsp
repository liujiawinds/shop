<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
  </head>
  
  <body>
  	<table>
  		<tr>
  			<td>管理员编号</td>
  			<td>管理员用户名</td>
  			<td>上次登入时间</td>
  		</tr>
  	</table>
  </body>
  <script type="text/javascript">
  	
  </script>
</html>
