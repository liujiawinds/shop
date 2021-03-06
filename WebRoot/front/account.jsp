<%@ page language="java" import="java.util.*,org.liujia.shop.model.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	if(session.getAttribute("user_logined")==null)response.sendRedirect("login.jsp");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!--[if lt IE 7 ]><html class="ie ie6" lang="en"> <![endif]-->
<!--[if IE 7 ]><html class="ie ie7" lang="en"> <![endif]-->
<!--[if IE 8 ]><html class="ie ie8" lang="en"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!--><html lang="en"> <!--<![endif]-->
<head>

	<!-- Basic Page Needs
  ================================================== -->
	<meta charset="utf-8" />
	<title>SmartShop</title>
    <meta name="robots" content="index, follow" />
    <meta name="keywords" content="" />
    <meta name="description" content="" />
	<meta name="author" content="" />

	<!-- Mobile Specific Metas
  ================================================== -->
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
    

	<!-- CSS
  ================================================== -->
	<link rel="stylesheet" href="styles/skeleton.css" />
    <link rel="stylesheet" href="styles/style.css" />
    <link rel="stylesheet" href="styles/inner.css" />
    <link rel="stylesheet" href="styles/color.css" />
	<link rel="stylesheet" href="styles/layout.css" />
    
	<!--[if lt IE 9]>
		<script src="../../html5shim.googlecode.com/svn/trunk/html5.js"></script>
	<![endif]-->

	<!-- Demo Switcher
	================================================== -->
    <link id="layoutcss" href="styles/fixed-layout.css" rel="stylesheet" type="text/css" />
    <link href="styles/style-switcher.css" rel="stylesheet" type="text/css" />

	<!-- Favicons
	================================================== -->
	<link rel="shortcut icon" href="images/favicon.ico" />
	<link rel="apple-touch-icon" href="images/apple-touch-icon.png" />
	<link rel="apple-touch-icon" sizes="72x72" href="images/apple-touch-icon-72x72.png" />
	<link rel="apple-touch-icon" sizes="114x114" href="images/apple-touch-icon-114x114.png" />

</head>


<body>
<div id="bodychild">
	<div id="outercontainer">
    
        <!-- HEADER -->
        <%@include file="header.jsp" %>
        <!-- END HEADER -->  

        <!-- BEFORE CONTENT -->
        <div id="outerbeforecontent">
        	<div class="container">
                <section id="beforecontent" class="twelve columns">
                    <h1 class="pagetitle">个人中心</h1>
                    <div class="clear"></div>
                </section>
            </div>
        </div>
        <!-- END BEFORE CONTENT -->
        
        <!-- MAIN CONTENT -->
        <div id="outermain">
        	<div class="container">
			<section id="maincontent" class="twelve columns">
				
                <p>你好， <strong><s:property value="#session.user_logined.realName"/></strong>！.
                <a href="#">修改密码</a>.</p>
                <h3>购买记录</h3>
                <table class="my_account_orders">
                    <thead>
                        <tr>
                            <th class="no-order"><span class="nobr">订单号</span></th>
                            <th><span class="nobr">日期</span></th>
                            <th><span class="nobr">商品名称</span></th>
                            <th class="ship"><span class="nobr">送货地址</span></th>
                            <th><span class="nobr">总价</span></th>
                            <th colspan="2" class="status"><span class="nobr">订单状态</span></th>
                        </tr>
                    </thead>
	                    <tbody id="recordTable">
	                    </tbody>
                </table>
                <br>

                <div class=" one_half firstcols">
					<header class="title2">				
                        <h4>我的地址</h4>&nbsp;&nbsp;&nbsp;&nbsp;默认为订单派送地址
                        <a href="#" class="edit" id="update">更新</a>	
                    </header>
                    <div class="clear"></div>
					<input id="address" value="${session.user_logined.address }" name="address" size="50" style="border:0;"/>
                </div>
                
                      
                <div class="clear"></div><!-- clear float --> 
            </section>
            </div>
        </div>
        <!-- END MAIN CONTENT -->
        
        <!-- FOOTER -->
        <%@include file="footer.jsp" %>
        <!-- END FOOTER -->
        
	</div><!-- end bodychild -->
</div><!-- end outercontainer -->

<!-- ////////////////////////////////// -->
<!-- //      Javascript Files        // -->
<!-- ////////////////////////////////// -->
<script type="text/javascript" src="js/jquery-1.6.4.min.js"></script>

<!-- jQuery Superfish -->
<script type="text/javascript" src="js/hoverIntent.js"></script> 
<script type="text/javascript" src="js/superfish.js"></script> 
<script type="text/javascript" src="js/supersubs.js"></script>

<!-- jQuery Dropdown Mobile -->
<script type="text/javascript" src="js/tinynav.min.js"></script>

<!-- Custom Script -->
<script type="text/javascript" src="js/custom.js"></script>

<!-- Twitter Script -->


<script type="text/javascript">
	$(document).ready(function(){
		$.get('/shop/front/show_record.action',function(retStr){ 
			var data = eval('(' + retStr + ')'); 
           	var html = "";
			for(var i in data){
				html += "<tr class='order'><td>"+data[i].id+"</td><td><time>"+data[i].orderTime+"</td></time><td>"+data[i].productName+"</td><td class='ship'><address>"
				+data[i].address+"</address></td><td>￥"+data[i].totalPrice+"</td><td>"+data[i].status+"</td><td><a href='front/delete_order.action?recordId='"+data[i].id+"' class='button'>删除</a></td></td></tr>";
            }
			$("#recordTable").html(html);
		});
	});

	$("#update").click(function(){
		var address = $("#address").val();
		$.ajax({
			url:'modify_address.action',
			type:'post',
			data:{
				address:address
			},
			success:function(msg){
				alert("修改成功！");
			}
			});
		});

</script>	

<!-- Demo Switcher -->
<script type="text/javascript" src="js/jquery.cookie.js"></script>

</body>
</html>
