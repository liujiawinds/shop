<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
	<link rel="stylesheet" href="styles/detail.css" type="text/css" />
	<link rel="stylesheet" href="styles/thickbox.css" type="text/css" />
	<script type="text/javascript" src="js/jquery-1.6.4.min.js"></script>
	<script src="js/jquery.jqzoom.js" type="text/javascript"></script>
	<script src="js/use_jqzoom.js" type="text/javascript"></script>
	<script src="js/jquery.thickbox.js" type="text/javascript"></script>
	<script src="js/jquery.livequery.js" type="text/javascript"></script>
	<script src="js/switchImg.js" type="text/javascript"></script>
	<script src="js/switchColor.js" type="text/javascript"></script>
	<script src="js/sizeAndprice.js" type="text/javascript"></script>
	<script type="text/javascript" src="js/custom.js"></script>

	<!-- Demo Switcher
	================================================== -->
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
                    <h1 class="pagetitle">${product.name }</h1>
                    <div class="clear"></div>
                </section>
            </div>
        </div>
        <!-- END BEFORE CONTENT -->
        
        <!-- MAIN CONTENT -->
        <div id="outermain">
                	
        			
					<div class="pro_detail">
						<div class="pro_detail_left">
							<ul style="list-style:none;">
								<li><img src="images/content/product/${product.id }/${product.id }_bk_xs.jpg" alt="" /></li>
								<li><img src="images/content/product/${product.id }/${product.id }_cu_xs.jpg" alt="" /></li>
								<li><img src="images/content/product/${product.id }/${product.id }_fr_xs.jpg" alt="" /></li>
								<li><img src="images/content/product/${product.id }/${product.id }_in_xs.jpg" alt="" /></li>
								<li><img src="images/content/product/${product.id }/${product.id }_ou_xs.jpg" alt="" /></li>
							</ul>
							<div class="jqzoom"><img src="images/content/product/${product.id }/${product.id }_fr_l.jpg" class="fs" alt=""  jqimg="images/content/product/${product.id }/${product.id }_fr_xl.jpg" id="bigImg"/></div>
							<span>
				                <a href="images/content/product/${product.id }/${product.id }_fr_xl.jpg" id="thickImg" title="${product.name }" class="thickbox">
				                   <img alt="点击看大图" src="images/look.gif" />
				                </a>
				            </span>
							
						</div>
						<div class="pro_detail_right">
							<h2>${product.designer }</h2>
							<p>${product.description }</p>
							<p><strong>价格：￥${product.price }  </strong></p>
							<p><strong>货号：${product.id }</strong></p>
							<div class="pro_size">
								尺寸：<strong>未选择</strong>
								<ul>
									<li><span>S</span></li>
									<li><span>M</span></li>
									<li><span>L</span></li>
									<li><span>XL</span></li>
									<li><span>XXL</span></li>
								</ul>
							</div>
							<br>
							<br>
							<div id="cart">
								<a class="checkout-button button alt" href="front/add_cart.action?productId=${product.id }">加入购物车</a>
								<a class="checkout-button button alt" href="front/add_collection.action?productId=${product.id }">加入收藏夹</a>
							</div>
						</div>
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
