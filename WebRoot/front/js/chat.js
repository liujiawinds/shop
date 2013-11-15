$(function(){
            	//键盘监听
            	$(document).keypress(function(e){
            		if(e.which==13){
            			SendContent($("#txtContent").val());
            			goToBottom();
            		}
            	});
            	//读取消息
            	GetMessageList();
            	//读取在线用户
            	GetOnlineUsers();
            	//初始化表情
            	InitFaces();
            	//自动刷新
            	AutoRefresh();
            	//发送按钮事件响应
            	$("#Button1").click(function(){
            		var $content=$("#txtContent");
            		if($content.val()!=""){
            			SendContent($content.val());
            		}else{
            			alert("发送内容不能为空！");
            			return false;
            		}
            	});
            	$("#Button2").click(function(){
            		if(confirm("确认下线？")){
            			OffLine();
            		}
            	});
            	//发送消息
            	function SendContent(content){
            		$.ajax({
            			type:"POST",
            			url:"/shop/chat",
            			data:"action=SendContent&d="+new Date()+"&content="+content,
            			success:function(data){
            				if(!data.indexOf("True")){
            					//获取消息
            					GetMessageList();
            					//清空发送框
            					$("#txtContent").val("");
            					
            					goToBottom();
            					//alert('shit for SendMessage');
            				}else{
            					alert("发送失败");
            				}
            			}
            		});
            	}
            	//获取消息
            	function GetMessageList(){
            		$.ajax({
            			type:"POST",
            			url:"/shop/chat",
            			data:"action=ChatList&d="+new Date(),
            			success:function(data){
            				if(data.indexOf("unLogin")==0){
            					alert("非法进入，请先登录！");
            					window.location="login.jsp";
            				}else{
            					$("#divContent").html(data);
            				}
            				//alert('shit for GetMessage');
            			}
            		});
            	}
            	//获取在线用户列表
            	function GetOnlineUsers(){
            		$.ajax({
            			type:"POST",
            			url:"/shop/chat",
            			data:"action=onLineList&d="+new Date(),
            			success:function(data){
            				$("#divOnline").html(data);
            				//alert('shit for GetOnlineUsers');
            			}
            		});
            	}
            	//设置表情
            	function InitFaces(){
            		var strHTML="";
            		for(var i=1;i<=7;i++){
            			strHTML+="<img src='front/images/"+i+".gif' id='"+i+"'/>";
            		}
            		$("#divFace").html(strHTML);
            	}
            	//图片监听
            	$("table tbody tr td img").click(function(){
            		var $txtContent=$("#txtContent");
            		if($txtContent.val()!=undefined){
            			var strContent=$txtContent.val()+"<:"+this.id+":>";
            		}else{
            			var strContent="<:"+this.id+":>";
            		} 
                    $("#txtContent").val(strContent);
                });
            	//下线
            	function OffLine(){
            		$.ajax({
            			type:"POST",
            			url:"/shop/chat",
            			data:"action=OffLine&d="+new Date(),
            			success:function(data){
            				if(data.indexOf("True")==0){
            					alert("成功下线！");
            					window.location="index.jsp";
            				}
            				//alert('shit for GetOnlineUsers');
            			}
            		});
            	}
            	//到底部去
            	function goToBottom(){
            		div=document.getElementById("divContent");
					div.scrollTop = div.scrollHeight;
            	}
            	//自动更新页面信息
            	function AutoRefresh(){
            		setInterval(GetMessageList,2000);
            		setInterval(GetOnlineUsers,4000);
            	}
            });