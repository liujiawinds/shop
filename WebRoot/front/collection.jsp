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
                    <h1 class="pagetitle">收藏夹</h1>
                    <div class="clear"></div>
                </section>
            </div>
        </div>
        <!-- END BEFORE CONTENT -->
        
        <!-- MAIN CONTENT -->
        <div id="outermain">
        	<div class="container">
        <s:if test="#session.collectionItems.size!=0">
			<section id="maincontent" class="twelve columns">
				<div id="cart">
                <table>
                	<thead>
                        <tr>
                            <th class="remove"></th>
                            <th class="moveToCollection">移到购物车</th>
                            <th class="product">商品</th>
                            <th class="desc">商品名称</th>
                            <th class="unit-price">单价</th>
                        </tr>
                    </thead>
                    <tfoot>
                        <tr>
                        	
                        </tr>
                            <td class="rounded-foot" colspan="7">
                               <a class="checkout-button button alt" href="front/show_cart.action">查看购物车</a>
                            </td>
                        </tr>
                    </tfoot>
                    <tbody>
                   <s:iterator value="#session.collectionItems" id="collection">
                    <tr>
                        <td class="remove"><a class="remove" title="Remove this item" href="front/delete_cart.action?cartId=${collection.id }">X</a></td>
                        <td class="moveToCart"><a class="remove" title="move to cart" href="front/moveToCart_collection.action?collectionId=${collection.id }">★</a></td>
                        <td style="vertical-align:middle;"><img src="images/content/product/${collection.product.id }/${collection.product.id }_in_l.jpg" alt="" style="width:50px;height:70px;" class="imgborder"/></td>
                        <td class="desc">${collection.product.name }</td>
                        <td class="unit-price">$${collection.product.price }</td>
                    </tr>
                    </s:iterator>
                    </tbody>
                </table>
                </div>
                
               <br><br>
                                         
        </s:if>
        <s:else>
        	<center><b>很遗憾，你的收藏夹是空的。。。</b></center>
        </s:else>
       
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
