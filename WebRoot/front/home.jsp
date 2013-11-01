<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!--[if lt IE 7 ]><html class="ie ie6" lang="zh-CN"> <![endif]-->
<!--[if IE 7 ]><html class="ie ie7" lang="zh-CN"> <![endif]-->
<!--[if IE 8 ]><html class="ie ie8" lang="zh-CN"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!--><html lang="zh-CN"> <!--<![endif]-->
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
    <link rel="stylesheet" href="styles/flexslider.css" />
    <link rel="stylesheet" href="styles/color.css" />
	<link rel="stylesheet" href="styles/layout.css" />
    
    
    
	<!-- Demo Switcher
	================================================== -->
    <link id="layoutcss" href="styles/fullwidth-layout.css" rel="stylesheet" type="text/css" />
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
    
        <!-- SLIDER -->
        <div id="outerslider">
        	<div class="container">
        	<div id="slidercontainer" class="twelve columns">
            
            	<section id="slider">
                    <div id="slideritems" class="flexslider">
                        <ul class="slides">
                            <li>
                                <img src="images/content/346769_ou_xl.jpg" style="width: 288px;height: 432px;"alt="" />
                                <div class="flex-caption">
                                    <h1>VICTORIA BECKHAM</h1>
                                    <p>每个考究的衣橱中都少不了线条利落的黑色裤装，Victoria Beckham 这款修身效果出色的高腰绉纱紧身裤就是你的完美选择。 这条走秀款紧身裤带有弹力背衬，可为你塑造出优美流畅的曲线。 建议搭配干练的西装。 </p>
                                    <a href="product-details.jsp" class="button">查看商品</a> <a href="cart.jsp" class="button">加入购物车</a>
                                </div>
                            </li>
                            <li>
                                <img src="images/content/336504_ou_xl.jpg" style="width: 288px;height: 432px;"/>
                                <div class="flex-caption">
                                    <h1>EMILIO PUCCI</h1>
                                    <p>简约的黑色连衣裙是衣橱必备，而 Emilio Pucci 的羊毛绉纱连衣裙更是其中的优雅典范。 这条时尚的裙子采用凸显身材的剪裁、引领潮流的细肩带和大胆前卫的开衩裙部。若要打造迷人的鸡尾酒会装扮，它是我们的首选。</p>
                                    <a href="product-details.jsp" class="button">查看商品</a> <a href="cart.jsp" class="button">加入购物车</a>
                                </div>
                            </li>
                            <li>
                                <img src="images/content/350511_ou_xl.jpg" style="width: 288px;height: 432px;" alt="" />
                                <div class="flex-caption">
                                    <h1>SAINT LAURENT</h1>
                                    <p>Saint Laurent 这款“Sac du Jour”皮质手提包采用无懈可击的简约设计，让我们十分喜爱。 中间拉链夹层可将你的日常随身物品收纳有序。 这款绒面革衬里设计是打造上班装或都市造型的完美选择，必将伴你时尚地走过一季又一季。</p>
                                    <a href="product-details.jsp" class="button">查看商品</a> <a href="cart.jsp" class="button">加入购物车</a>
                                </div>
                            </li>
                            <li>
                                <img src="images/content/365693_ou_xl.jpg" style="width: 288px;height: 432px;" alt="" />
                                <div class="flex-caption">
                                    <h1>MICHAEL KORS</h1>
                                    <p>Michael Kors 辨识度极高的手表设计绝对是潮人必备。这款金色不锈钢和白色醋酸树脂计时腕表堪称经典之选。不妨与几条风格迥异的手链层叠佩戴。</p>
                                    <a href="product-details.html" class="button">查看商品</a> <a href="cart.jsp" class="button">加入购物车</a>
                                </div>
                            </li>
                        </ul>
                    </div>
                </section>
            </div>
            </div>
        </div>
        <!-- END SLIDER -->
        
      
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

<!-- jQuery Flexslider -->
<script type="text/javascript" src="js/jquery.flexslider-min.js"></script>

<!-- jQuery Dropdown Mobile -->
<script type="text/javascript" src="js/tinynav.min.js"></script>

<!-- Custom Script -->
<script type="text/javascript" src="js/custom.js"></script>

<script type="text/javascript">
jQuery(window).load(function() {
	//Slider
	jQuery('.flexslider').flexslider({
          animation: "fade"
    	});
		
	//Carousel
	jQuery('.flexslider-carousel').flexslider({
	  animation: "slide",
	  animationLoop: false,
	  itemWidth: 220,
	  minItems: 2,
	  maxItems: 4,

	});
		
});

</script>	

<!-- Demo Switcher -->
<script type="text/javascript" src="js/jquery.cookie.js"></script>



</body>
</html>
