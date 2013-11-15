<%@ page language="java" import="java.util.*,org.liujia.shop.model.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%
	User user = (User)session.getAttribute("user_logined");
	if(user==null)response.sendRedirect("login.jsp");
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
                    <h1 class="pagetitle">结算</h1>
                    <div class="clear"></div>
                </section>
            </div>
        </div>
        <!-- END BEFORE CONTENT -->
        
        <!-- MAIN CONTENT -->
         <div id="outermain">
        	<div class="container">
        	<section id="maincontent" class="twelve columns checkout">
                        <div id="toggle">
                        
                            <h2 class="trigger"><span>配送方式</span></h2>
                            <div class="toggle_container">
                                <div class="block">
                                <div><input type="radio" name="deliveryway" value="first" checked="checked"><label for="first" style="float:none; display:inline;">周一至周五、工作日送货</label></div>
                                <div><input type="radio" name="deliveryway" value="second"><label for="second" style="float:none; display:inline;">周六、周日、假日送货</label></div>
                                <div><input type="radio" name="deliveryway" value="third"><label for="third" style="float:none; display:inline;">周一至周日、假日均可送货</label></div>
                                <div><input type="radio" name="deliveryway" value="forth"><label for="forth" style="float:none; display:inline;">校园配送（特别安排可能超出预计送货天数）</label></div>
                               	<h5>声明：</h5>
                               	<p>
								1. 送货时间：早 8:30至 晚7:30<br>
								2. 我们会努力按照您选择的时间进行配送，但因天气、交通等因素影响，您的订单有可能会有延误现象，请您谅解！</p>
                                </div>
                            </div>
                        </div><!-- end toggle -->
          				   <div id="toggle">
                        
                            <h2 class="trigger"><span>支付方式</span></h2>
                            <div class="toggle_container">
                                <div class="block" style="margin:0 0 20px 0;">
								            <div class="cash-on-delivery">         
								                <input type="radio" name="order.payment" checked="checked">
								                <label style="float:none; display:inline;">货到付款</label><br>
								            </div>
								            <div class="pay-online">            
								                <input  name="order.payment" type="radio" value="-1$-1$False$False$0"/>
								                <label style="float:none; display:inline;">在线支付</label>  
								              </div>          
								            <div class="pay-postal">            
								                <input  type="radio" name="order.payment" value="04$4$False$False$0">
								                <label  style="float:none; display:inline;">邮局汇款</label>
								                <span>请您下单后尽快到邮局汇款，同时在汇款单填写汇款金额、汇款人相关信息、并在附言栏注明订单号</span>
								            </div>
                                </div>
                            </div>
                        </div><!-- end toggle -->
                    <table class="checkout_cart">
                        <thead>
                            <tr>
                                <th class="product">商品</th>
                                <th class="desc">商品名称</th>
                                <th class="available">是否有货</th>
                                <th class="unit-price">单价</th>
                                <th class="qty">尺寸</th>
                                <th class="qty">数量</th>
                                <th class="total">总价</th>
                            </tr>
                        </thead>
                        <tbody>
                        <s:iterator value="#session.order.product" id="product">
                        <s:iterator value="#session.order.cart" id="cart">
                        <s:if test="#product.id==#cart.productId">
                        <tr>
                            <td class="product"><img src="images/content/product/${product.id }/${product.id }_in_l.jpg" alt="" style="width:50px;height:70px;" /></td>
                            <td class="desc">${product.name }</td>
                            <td class="available">有货</td>
                            <td class="unit-price">￥${product.price } </td>
                            <td class="unit-price">M</td>
                            <td> ${cart.quantity } </td>
                            <td>￥${cart.quantity*product.price } </td>
                        </tr>
                        </s:if>
                        </s:iterator>
                        </s:iterator>
                        </tbody>
                    </table>
                
                    <table class="checkout_totals">
                      <tbody>
                          <tr class="cart-subtotal">
                              <th>商品总价 ：</th>
                              <td id="price">￥${totalPrice }</td>
                          </tr>      
                          <tr class="shipping">
                              <th>快递费用：</th>
                              <td>￥5.00</td>
                          </tr>        
                          <tr class="total">
                              <th>共计：</th>
                              <td id="total">￥${totalPrice+5 }</td>
                          </tr>
                      </tbody>
                    </table> 
                	<br>

                    <div id="payment">
                       
                    
                        <div class="form-row">		
                            <a href="order.jsp" class="button">提交订单</a>
                        </div>
                        <div class="clear"></div>
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

<!-- Demo Switcher -->
<script type="text/javascript" src="js/jquery.cookie.js"></script>
</body>
</html>
