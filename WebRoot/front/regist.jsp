<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
    
	<link href='../../fonts.googleapis.com/css@family=Droid+Sans_3A400,700' rel='stylesheet' type='text/css'>
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
                    <h1 class="pagetitle">用户登录</h1>
                    <div class="clear"></div>
                </section>
            </div>
        </div>
        <!-- END BEFORE CONTENT -->
        
        <!-- MAIN CONTENT -->
        <div id="outermain">
        	<div class="container">
			<section id="maincontent" class="twelve columns">
				<section id="content" class="positionleft nine columns alpha"> 
                	<div class="padcontent">
                    	
      	
         <form method="post" action="front/regist_user.action" id="regform">
         邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱：<input type="text" name="user.email" value="<%=request.getParameter("email") %>"  style="font-size:12px;padding:7px 5px;margin-bottom:15px;font-size:11px;font-family:Arial;
	width:40%;-webkit-border-radius: 4px;-moz-border-radius: 4px;border-radius: 4px;"/>
         密码：<input type="password" name="user.password"  style="font-size:12px;padding:7px 5px;margin-bottom:15px;font-size:11px;font-family:Arial;
	width:40%;-webkit-border-radius: 4px;-moz-border-radius: 4px;border-radius: 4px;"/><br />
         确认密码：<input type="password" name="confirm" style="font-size:12px;padding:7px 5px;margin-bottom:15px;font-size:11px;font-family:Arial;
	width:40%;-webkit-border-radius: 4px;-moz-border-radius: 4px;border-radius: 4px;"/>
         昵称：<input type="text" name="user.username"  style="font-size:12px;padding:7px 5px;margin-bottom:15px;font-size:11px;font-family:Arial;
	width:40%;-webkit-border-radius: 4px;-moz-border-radius: 4px;border-radius: 4px;"/><br />
         真实姓名：<input type="text" name="user.realName"  style="font-size:12px;padding:7px 5px;margin-bottom:15px;font-size:11px;font-family:Arial;
	width:40%;-webkit-border-radius: 4px;-moz-border-radius: 4px;border-radius: 4px;"/>
         电话：<input type="text" name="user.telephone"  style="font-size:12px;padding:7px 5px;margin-bottom:15px;font-size:11px;font-family:Arial;
	width:40%;-webkit-border-radius: 4px;-moz-border-radius: 4px;border-radius: 4px;"/><br />
         邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;编：<input type="text" name="user.postcode" style="font-size:12px;padding:7px 5px;margin-bottom:15px;font-size:11px;font-family:Arial;
	width:40%;-webkit-border-radius: 4px;-moz-border-radius: 4px;border-radius: 4px;"/>
         地址：<input type="text" name="user.address" style="font-size:12px;padding:7px 5px;margin-bottom:15px;font-size:11px;font-family:Arial;
	width:40%;-webkit-border-radius: 4px;-moz-border-radius: 4px;border-radius: 4px;"/><br />
         <input type="submit" class="button" value="注册" />
         </form>
      
                	</div>
                </section>
             
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

<!-- Demo Switcher -->
<script type="text/javascript" src="js/jquery.cookie.js"></script>
<script type="text/javascript" src="js/switcher.js"></script>

</body>
</html>
