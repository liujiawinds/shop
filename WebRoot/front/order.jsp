<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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
                    <h1 class="pagetitle"></h1>
                    <div class="clear"></div>
                </section>
            </div>
        </div>
        <!-- END BEFORE CONTENT -->
        
        <!-- MAIN CONTENT -->
        <div id="outermain">
        	<div class="container">
			<section id="maincontent" class="twelve columns" >
				<center>下单成功，如有疑问请跟店主联系。</center>
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

</body>
</html>
