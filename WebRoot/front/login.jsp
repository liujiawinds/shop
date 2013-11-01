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

	<!-- Demo Switcher
	================================================== -->

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
                    	
                        <div class="one_half firstcols">
                            <h2 class="title">用户注册</h2>
                            <h5>输入邮箱地址创建账号。</h5>
                            <form action="regist.jsp" method="POST">
                            <fieldset>
                            <label>邮箱</label><br />
                            <input type="text" name="email" id="email" onblur="valid()"/><div id="msg"></div><br />
                            <input type="submit" class="button" id="submitButton" value="注册" onsubmit="valid()">
                            </fieldset>
                            </form>
                        </div>
                        <div class="one_half lastcols">
                            <h2 class="title">已经注册？</h2>
                            <form method="post" action="front/login_user.action" id="loginform">
                            <fieldset>
                            <label>邮箱：</label><br />
                            <input type="text" name="user.email"/><br />
                            <label>密码：</label><br />
                            <input type="password" name="user.password" class="text-input" /><br />
                            <label>验证码：</label><br />
                            <input style="font-size:12px;padding:7px 5px;margin-bottom:15px;font-size:11px;font-family:Arial;
	width:65%;-webkit-border-radius: 4px;-moz-border-radius: 4px;border-radius: 4px;" type="text" name="captcha" width="50px"/>
                            <img alt="验证码" src="http://localhost:8080/shop/captcha" style="top:8px;position:relative;"><br />
                            <input type="submit" class="button" value="登录" />
                            </fieldset>
                            <s:if test="#msg!=null">
                            	<s:property value="msg"/>
                            </s:if>
                            </form>
                        </div>
                                        
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
<script type="text/javascript">
function valid(){
	var email=$("#email").val();
	if(''!=email){
		var reg = /^[_a-zA-Z\d\-\.]+@[_a-zA-Z\d\-]+(\.[_a-zA-Z\d\-]+)+$/;
		if(!reg.test(email)){
			$("#submitButton").attr("disabled",true);
			alert("请使用正确格式输入邮箱！");
			return false;
		}else{
			$("#submitButton").attr("disabled",false);
			$.ajax({
				type:'post',
				url:'http://localhost:8080/shop/front/valid_user.action',
				data:{
				email:email
			},
			success:function(msg){
				if(msg=="yes"){
					$("#msg").html("未被注册，可以使用！");
					$("#submitButton").attr("disabled",false);
				}else{
					$("#msg").html("<b style='color:red;'>已被注册，请重新输入其它邮箱！</b>");
					$("#submitButton").attr("disabled",true);
					return false;
				}
			}
			
			});
		}
	}
}
</script>

<!-- Demo Switcher -->
<script type="text/javascript" src="js/jquery.cookie.js"></script>

</body>
</html>
