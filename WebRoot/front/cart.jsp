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
                    <h1 class="pagetitle">购物车</h1>
                    <div class="clear"></div>
                </section>
            </div>
        </div>
        <!-- END BEFORE CONTENT -->
        
        <!-- MAIN CONTENT -->
        <div id="outermain">
        	<div class="container">
        <s:if test="#session.cartItems.size!=0">
			<section id="maincontent" class="twelve columns">
				<div id="cart">
                <table>
                	<thead>
                        <tr>
                            <th class="remove"></th>
                            <th class="moveToCollection">移到收藏夹</th>
                            <th class="product">商品</th>
                            <th class="desc">商品名称</th>
                            <th class="unit-price">单价</th>
                            <th class="unit-price">尺寸</th>
                            <th class="qty">数量</th>
                            <th class="total">总价</th>
                        </tr>
                    </thead>
                    <tfoot>
                       
                        <tr>
                            <td class="rounded-foot" colspan="12">
                               <a class="checkout-button button alt" href="home.jsp">继续购物</a>
                                <a class="checkout-button button alt" href="front/add_order.action">结算</a>
                            </td>
                        </tr>
                    </tfoot>
                    <tbody>
                   <s:iterator value="#session.cartItems" id="cart">
                    <tr>
                        <td class="remove"><a class="remove" title="Remove this item" href="front/delete_cart.action?cartId=${cart.id }">X</a></td>
                        <td class="moveToCollection"><a class="remove" title="move to collection" href="front/moveToCollection_cart.action?cartId=${cart.id }">☆</a></td>
                        <td align=center class="product"><img src="images/content/product/${cart.product.id }/${cart.product.id }_in_l.jpg" alt="" style="width:50px;height:70px;" class="alignright imgborder"/></td>
                        <td class="desc">${cart.product.name }</td>
                        <td class="unit-price" id="unit_price">￥${cart.product.price }</td>
                        <td class="unit-price">M</td>
                        <td>
                        <div class="quantity buttons_added">
                            <input type="button" class="minus" id="minus" value="-">
                            <input maxlength="12" class="input-text qty text" title="Qty" size="4" value="${cart.quantity }" id="quantity">
                            <input type="button" class="plus" id="add" value="+">
                        </div>	
                        </td>
                        <td id="totalPrice">￥${cart.product.price*cart.quantity } </td>
                    </tr>
                    </s:iterator>
                    </tbody>
                </table>
                </div>
                
               <br><br>
                                         
        </s:if>
        <s:else>
        	<center><b>很遗憾，你的购物车是空的。。。</b></center>
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

<SCRIPT type="text/javascript">
	$(document).ready(function(){
			var quantity = $("#quantity").val();
			var tempPrice = $("#unit_price").text();
			var tempPrice2 = tempPrice.substr(1);
			var price = parseFloat(tempPrice2);
		$("#minus").click(function(){
			if(quantity>1){
				quantity--;
				var totalPrice = quantity*price;
				$("#quantity").attr("value",quantity);
				$("#totalPrice").text("￥"+totalPrice);
				}
						
			});
		$("#add").click(function(){
			quantity++;
			var totalPrice = quantity*price;
			$("#quantity").attr("value",quantity);
			$("#totalPrice").text("￥"+totalPrice);
		});
		});
</SCRIPT>
</body>
</html>
