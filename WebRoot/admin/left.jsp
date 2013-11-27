<%@ page language="java" contentType="text/html; charset=gbk" pageEncoding="gbk"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>管理页面</title>

<script src="js/prototype.lite.js" type="text/javascript"></script>
<script src="js/moo.fx.js" type="text/javascript"></script>
<script src="js/moo.fx.pack.js" type="text/javascript"></script>
<link rel="stylesheet" href="styles/left.css" type="text/css">

</head>

<body>
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#EEF2FB">
  <tr>
    <td width="182" valign="top"><div id="container">
      
      <h1 class="type"><a href="javascript:void(0)">商品信息管理</a></h1>
      <div class="content">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td><img src="images/menu_topline.gif" width="182" height="5" /></td>
          </tr>
        </table>
        <ul class="MM">
          <li><a href="<%=path %>/admin/list_product.action?requestSource=info" target="main">商品信息</a></li>
          <li><a href="add_product.jsp" target="main">商品添加</a></li>
          <li><a href="amdin/list_product.action?requestSource=manage" target="main">商品信息修改</a></li>
        </ul>
      </div>
      
      <h1 class="type"><a href="javascript:void(0)">会员管理</a></h1>
      <div class="content">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td><img src="images/menu_topline.gif" width="182" height="5" /></td>
          </tr>
        </table>
        <ul class="MM">
          <li><a href="<%=path %>/admin/list_user.action" target="main">会员信息管理</a></li>
        </ul>
      </div>
      
      <h1 class="type"><a href="javascript:void(0)">管理员设置</a></h1>
      <div class="content">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td><img src="images/menu_topline.gif" width="182" height="5" /></td>
          </tr>
        </table>
        <ul class="MM">
		  <li><a href="admin_info.jsp" target="main">管理员信息</a></li>
          <li><a href="add_admin.jsp" target="main">添加管理员</a></li>
          <li><a href="admin_privilege.jsp" target="main">管理员权限</a></li>
        </ul>
      </div>
      
      <h1 class="type"><a href="javascript:void(0)">订单管理</a></h1>
      <div class="content">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td><img src="images/menu_topline.gif" width="182" height="5" /></td>
          </tr>
        </table>
        <ul class="MM">
          <li><a href="<%=path %>/admin/listAll.action" target="main">订单查询</a></li>
          <li><a href="confirm_order.jsp" target="main">确认订单</a></li>
          <li><a href="manage_order.jsp" target="main">订单管理</a></li>
        </ul>
      </div>
    </div>
    
        <h1 class="type"><a href="javascript:void(0)">销售统计</a></h1>
      <div class="content">
          <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td><img src="images/menu_topline.gif" width="182" height="5" /></td>
            </tr>
          </table>
        <ul class="MM">
            <li><a href="" target="main">按产品统计</a></li>
          <li><a href="#" target="main">按产品类别统计</a></li>
          <li><a href="#" target="main">按会员统计</a></li>
        </ul>
      </div>
      </div>
        <script type="text/javascript">
		var contents = document.getElementsByClassName('content');
		var toggles = document.getElementsByClassName('type');
	
		var myAccordion = new fx.Accordion(
			toggles, contents, {opacity: true, duration: 400}
		);
		myAccordion.showThisHideOpen(contents[0]);
	</script>
        </td>
  </tr>
</table>
</body>
</html>
