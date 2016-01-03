<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
<title>找回密码-<%=web_title %></title>
	<style type="text/css">
		#timeout{border:1px solid #d7d7d7;background-color:#eaedf4;width:130px;padding:2px 5px;text-align:center;}
    </style>
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
	    			document.getElementById("timeout").innerHTML = "<a class='e-ifon' href='javascript:void(0);'>"+set_time_s+"秒后，可再次获取</a>";
	    			setTimeout("setUpTime()",1000);
	    		}
	    		else
	    		{
	    			document.getElementById("timeout").innerHTML ="<a id='sendCode' class='e-ifon' href='javascript:void(0);'>获取短信验证码</a>";
	    		}
	    	}
	    	
	    	function interval()
	    	{
	    		var time_s = 0;
	    		$.ajax({
					type: "get",
					async:false,
					url: "<%=basePath%>getcellPhoneBySendDate.action?phone="+$("#cellphone").val(),
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
	    	
			$(function () {
			    $("#sendCode").click(function(){
			    	if($("#cellphone").val()!="")
			    	{
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
				});
				//
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
			})
		</script>
</head>

<body>

<div class="w480">
    
    <%@ include file="/WEB-INF/view/inc/top.jsp" %>
        
    <div class="content">
    
	    <!--当前位置开始-->
	    <div class="module">
	        <section>
	            <div class="position mt5">
	                <a href="<%=basePath %>findPwd/index.action">找回密码</a> > <a href="javascript:void(0);">填写手机号码</a> > <a href="javascript:void(0);">验证身份</a>
	            </div>
	        </section>
	    </div>
	    
	    <!--内容开始-->
	    <div class="module">
	        <section class="box_sign">
	                <span class="sign_span">用户名: ${user_name_s}</span>
	                <span class="sign_span" style="margin-top:5px;">已验证手机: ${cellphone_s}<input type="hidden" id="cellphone" value="${cellphone}"></span>
	                <span id="timeout"><a id="sendCode" href="javascript:void(0);">获取短信验证码</a></span>
	                <span class="sign_span" >短信验证码</span>
	                <span>
	                	<input type="text"  id="confirmCode" name="confirmCode" class="psd" style="width:70px;"  maxlength="6" />
                	<span>
                		
	                </span>
	                <span><input type="button" class="sign_btn" id="testCode" value="下一步"></span>
	        </section>
	    </div>
	    
	</div>
    
    
    <%@ include file="/WEB-INF/view/inc/footer.jsp" %>
</div>


</body>
</html>
