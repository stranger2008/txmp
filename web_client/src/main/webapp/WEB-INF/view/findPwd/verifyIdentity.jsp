<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link rel="stylesheet" href="<%=basePath %>inc/css/public.css" />
		<link rel="stylesheet" href="<%=basePath %>inc/css/sign.css"/>
		<title>找回密码</title>
		<script type="text/javascript">
	    	var set_time; 
	    	$(document).ready(function () {
	    		readySetUpTime();
	    	});
	    	
	    	function readySetUpTime()
	    	{
	    		set_time = interval();
		        setUpTime();
	    	}
	    	
	    	function setUpTime()
	    	{
	    		if(parseInt(set_time) < 60 && parseInt(set_time) >= 1)
	    		{  
	    			var set_time_s = 60 - parseInt(set_time);
	    			set_time = parseInt(set_time) + 1;
	    			$("#timeout").html("<a class='e-ifon' href='javascript:void(0);'>"+set_time_s+"秒后，可再次获取</a>");
	    			setTimeout("setUpTime()",1000);
	    		}
	    		else
	    		{
	    			$("#timeout").html("<a id='sendCode' class='e-ifon' onclick='getCode();' href='javascript:void(0);'>获取短信验证码</a>");
	    		}
	    	}
	    	
	    	function interval()
	    	{
	    		var time_s = 0;
	    		$.ajax({
					type: "get",
					async:false,
					url: "<%=basePath%>getPhoneBySendDate.action?phone="+$("#cellphone").val(),
					cache:false,
					success: function(data, textStatus){
						if(data != ""){
							if(60 > data){
		        				time_s =  data;
		        			}
						}
					}
				});
				return time_s;
	    	}
	    	function getCode(){
	    		if($("#cellphone").val()!=""){
	    			$.ajax({
						type: "get",
						async:false,
						url: "<%=basePath%>sendPhoneCode.action?phone="+$("#cellphone").val(),
						cache:false,
						success: function(data, textStatus){
							if(data == "1"){
								alert("发送成功");
							}else{
								alert("发送失败");
							}
						}
					});
		    	}
			    	else
			    		alert("您暂无手机号码可验证，请重试!");
			    	 window.location.reload(); 
	    	}
			$(function () {
			     
				 $("#testCode").click(function(){
				 	var confirmCode = $("#confirmCode").val();
				 	var cfm = new RegExp("^[0-9]*$");
			    	if(!cfm.test(confirmCode)){
	        			alert("请输入6位验证码!");
	        			$("#confirmCode").val("");
	      				document.getElementById("confirmCode").focus();
	    			}
	    			else if(confirmCode.length != 6)
	    			{
	    				alert("请输入6位验证码!");
	      				document.getElementById("confirmCode").focus();
	    			}
	    			else
	    			{
	    				$.ajax({
							type: "get",
							url: "<%=basePath%>findPwTestPhone.action?phone="+$("#cellphone").val()+"&confirmCode="+confirmCode,
							cache:false,
							success: function(data, textStatus){
								if(data == "1"){
									location.href='<%=basePath %>findPwd/setNewPwd.action';
								}else{
									document.getElementById("confirmCode").focus();
									alert("手机验证码不正确，请重试");
								}
							}
						});
	    			}				
				});
				//
			});
		</script>
	</head>
	<body>
		<!--头部开始-->
		 <%@ include file="/WEB-INF/view/inc/top.jsp" %>
		<!--登录-->
		<div class="w1200">
			<div class="logo-sign">
				<a href="<%=basePath %>index.action"><img src="<%=basePath %>inc/images/logo-sign.png">
				</a>
				<b>找回密码</b>
			</div>
			<div class="boxFindpwd">
				<div class="box_findpwd">
					<h2>找回密码</h2><b></b>
				</div>
				<div class="mc">
					<div class="step">
					    <ul>
					        <li class="one">填写手机号码</li>
					        <li class="two cur">验证身份</li>
					        <li class="">设置新密码</li>
					        <li class="four">完成</li>
					    </ul>
					</div>
					<div class="find_pwd_form">
					    <div class="item">
					        <span class="label">用户名：</span>
					        <div class="fl">
							   	<div class="fl">
					            	<label class="text-ifo text-one">${user_name_s}</label>
									<label id="username_succeed" class="blank invisible"></label>
									<span class="clr"></span>
					        	</div>
							</div>
					    </div>
					    <div class="item">
    						<span class="label">已验证手机：</span>
						    <div class="fl">
						        <div id="sendMobileCodeDiv">
						            <label class="text-ifo text-one">${cellphone_s}<input type="hidden" id="cellphone" value="${cellphone}"></label>
						            <span id="timeout">
							            <a id="sendCode" class="e-ifon" onclick="getCode();" href="javascript:void(0);">获取短信验证码</a>
						            </span>
						        </div>
						    </div>
						</div>
					    <div class="item">
					        <span class="label">短信验证码：</span>
					        <div class="fl">
					            <input type="text"  id="confirmCode" name="confirmCode" style="width:100px;" class="text text-1"  maxlength="6"></input>
					        </div>
					    </div>
					    <div class="item">
    						<span class="label">&nbsp;</span>
    						<input id="testCode" class="btn-img btn-entry" type="button"  value="下一步" ></input>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!--底部-->
		 <%@ include file="/WEB-INF/view/inc/footer.jsp" %>
	</body>
</html>
