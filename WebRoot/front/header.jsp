<%@ page language="java" import="java.util.*" pageEncoding="utf-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!-- HEADER -->
        <div id="outerheader">
        	<div class="container">
			<header id="top" class="twelve columns">
            	<div id="logo"  class="three columns alpha"><a href="home.jsp"><img src="images/logo.png" alt=""></a></div>
                <div id="headerright" class="six columns omega">
					<div id="chart">
                    	<a href="#" id="shop-bag"></a>
                        <ul class="shop-box">
                        	<s:iterator value="#session.cartItems" id="cart">
                        	<li>
                                <img src="images/content/product/${cart.product.id }/${cart.product.id }_in_l.jpg" alt="" class="alignright imgborder" style="width:40px;height:40px;" />
                                <h2>${cart.product.name }</h2>
                                <div class="price">${cart.quantity } x ￥${cart.product.price }</div>
                            </li>
                            </s:iterator>
                            <li>
                                <s:if test="#session.cartItems!=null">
                                <div class="total">总价: ￥${totalPrice }</div>
                                </s:if>
                                <a href="cart.jsp" class="button">购物车</a> <a href="checkout.jsp" class="button">结算</a>
                                <div class="clear"></div>
                            </li>
                        </ul>
                    	<h6>购物车</h6>
                    	<s:if test="#session.cartItems==null||#session.cartItems.size==0">
                        <p>购物车内没有商品！</p>
                    	</s:if>
                    	<s:else>
                    	<p>购物车内共有&nbsp;<strong style="color:black;"><s:property value="#session.cartItems.size"/></strong>&nbsp;件商品</p>
                    	</s:else>
                    </div>
					<ul id="user-nav">
                    	<li><a href="account.jsp">个人账户</a></li>
                        <li><a href="front/show_collection.action">收藏夹</a></li>
                        <li><a href="front/show_cart.action">购物车</a></li>
<%--                        <li><a href="checkout.jsp">结算</a></li>--%>
                        
                        <s:if test="#session.user_logined==null">
                        	<li><a href="login.jsp">登录</a></li>
                        </s:if>
                        <s:else>
                        	<li><a href="front/logout.action">注销</a></li>
                        </s:else>
                    </ul>
                </div>

                <section id="navigation" class="twelve columns">
                    <nav id="nav-wrap">
                        <ul id="topnav" class="sf-menu">
                            <li class="current"><a href="home.jsp">首页</a></li>
                            <li><a href="<%=path %>/front/show_product.action?categoryId=1">服装</a>
                            	<ul>
                                	<li><a href="<%=path %>/front/show_product.action?categoryId=5">上衣</a></li>
                                    <li><a href="<%=path %>/front/show_product.action?categoryId=6">裤子</a></li>
                                    <li><a href="<%=path %>/front/show_product.action?categoryId=7">裙子</a></li>
                                    <li><a href="<%=path %>/front/show_product.action?categoryId=8">沙滩装</a></li>
                                </ul>
                            </li>
                            <li><a href="<%=path %>/front/show_product.action?categoryId=2">包袋</a>
                            	<ul>
                                	<li><a href="<%=path %>/front/show_product.action?categoryId=9">单肩包</a></li>
                                    <li><a href="<%=path %>/front/show_product.action?categoryId=10">手拿包</a></li>
                                    <li><a href="<%=path %>/front/show_product.action?categoryId=11">旅行箱包</a></li>
                                </ul>
                            </li>
                            <li><a href="<%=path %>/front/show_product.action?categoryId=3">鞋子</a>
                            	<ul>
                                	<li><a href="<%=path %>/front/show_product.action?categoryId=12">高跟鞋</a></li>
                                    <li><a href="<%=path %>/front/show_product.action?categoryId=13">平底鞋</a></li>
                                    <li><a href="<%=path %>/front/show_product.action?categoryId=14">靴子</a></li>
                                    <li><a href="<%=path %>/front/show_product.action?categoryId=15">凉鞋</a></li>
                                    <li><a href="<%=path %>/front/show_product.action?categoryId=16">运动鞋</a></li>
                                </ul>
                            </li>
                            <li><a href="<%=path %>/front/show_product.action?categoryId=4">配饰</a>
                            	<ul>
                                	<li><a href="<%=path %>/front/show_product.action?categoryId=17">手套</a></li>
                                    <li><a href="<%=path %>/front/show_product.action?categoryId=18">帽子</a></li>
                                    <li><a href="<%=path %>/front/show_product.action?categoryId=19">太阳镜</a></li>
                                    <li><a href="<%=path %>/front/show_product.action?categoryId=20">手表</a></li>
                                </ul>
                            </li>
                            <li><a href="contact.jsp">联系店主</a></li>
                        </ul><!-- topnav -->
                    </nav><!-- nav -->	
                    
                    <form method="get" id="searchform" action="front/search_product.action">
                      <div class="bgsearch">
                          <input type="text" name="keyword" id="s" value="" /> 
                          <input type="submit" class="searchbutton" value="" />
                      </div>
                    </form>
                   
                    <div class="clear"></div>
                </section>
                <div class="clear"></div>
            </header>
            </div>
        </div>
        <!-- END HEADER -->   
