<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
		<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link rel="stylesheet" href="<%=basePath %>inc/css/public.css"/>
		<link rel="stylesheet" href="<%=basePath %>inc/css/sign.css"/>
		<script type="text/javascript" src="<%=basePath%>inc/js/jquery-1.8.0.min.js"></script> 
		
		
		<script type="text/javascript">
		
		 <!--用户名 -->
		function foucsuser(){
			if($('#checkstyle').length > 0){
				$('#checkstyle').remove();
			}
			if($("#checklen").length > 0){
				$("#checklen").remove();
			}
			if(!$("#username").length > 0 ) {
				if ($("#userright").hasClass('right-ipt')) {
			            $("#userright").toggleClass("right-ipt");
			      };
				var insertText = '<div id="username" class="caution fl"><b></b>4-20位字符，支持英文、数字及符号组合</div>'; 
				$("#user").append(insertText);
				
			}
			if($("#username").length > 0 && $("#checkname").length > 0){
				var my = $("#checkname");
				if (my != null){				    
					my.remove();
				}
			}
			if($("#username11").length > 0){
				var my = $("#username11");
				if (my != null){				    
					my.remove();
				}
			}
		 }
		 function bluruser(){
			 var my = $("#username");
				if (my.length > 0){				    
					my.remove();
				}
				
				if($("#user_name").val() != ""){
					if($("#user_name").val().length >= 4){						
					$.ajax({
						type: "get",
						url: "checkname.action?username="+$("#user_name").val(),
						success: function(data, textStatus){
								if(data == "2"){
									var insertText = '<div id="checkstyle" class="caution fl caution-err"><b></b>以英文字母开头，含英文字母、数字、下划线</div>'; 
									$("#user").append(insertText);
								}
								if(data == "1"){
									if($('#checkname').length <= 0) {
										var insertText = '<div id="checkname" class="caution fl caution-err"><b></b>用户名已存在</div>'; 
										$("#user").append(insertText);
									}
								}else if(data == "0"){
										var my = $("#checkname");
										if (my.length > 0){				    
											my.remove();
										}
									var div = $('#userright'); 
									$("#userright").toggleClass("right-ipt");
								}
							}
					});
					}else{
						if($('#checklen').length <= 0) {
							var insertText = '<div id="checklen" class="caution fl caution-err"><b></b>用户名不少于4位</div>'; 
							$("#user").append(insertText);
						}
					}
				}else{
					if($('#checkname').length > 0){
						my.remove();
					}
				}
		 }
		 <!--密码 -->
		 function foucspwd(){
		 	if($("#passlen").length > 0){
		 		$("#passlen").remove();
		 	}
		 	if($('#passblank').length > 0){
		 		$('#passblank').remove();
		 	}
		 	if(!$('#pwddiv').length > 0) {
				var insertText = '<div id="pwddiv" class="caution fl"><b></b>6-20位字符，支持英文、数字及符号组合</div>'; 
				$("#pwd").append(insertText);     	 	  
			}
			if($('#pwddiv').length > 0 && $('#pwderror').length > 0){
				var my = $("#pwderror");
				my.remove();
			}
			if($('#pwddiv11').length > 0){
				var my = $("#pwddiv11");
				my.remove();
			}
		 }
		 <!--确认密码 -->
		 function foucspwdcheck(){
		 	if($('#lenpwd').val().length >=6 && $('#passlen').length > 0){
			 	$('#passlen').remove();
		 	}
		 	if(!$('#surepwddiv').length > 0) {
				var insertText = '<div id="surepwddiv" class="caution fl"><b></b>请再输入密码</div>'; 
				$("#surepwd").append(insertText);     	 	  
			}
			if($('#surepwddiv').length > 0 && $('#checksurepwd').length > 0){
				var my = $("#checksurepwd");
				my.remove();
			}
			if($('#sure_pwd').val == ""){
				var div = $('#pwdright'); 
				$("#pwdright").removeClass("right-ipt");
			}
			if($('#surepwddiv11').length > 0){
				var my = $("#surepwddiv11");
				my.remove();
			}
		 }
		 function blurpwdcheck(){
		 	$("#surepwddiv").remove();		 		
			if($('#lenpwd').val() != $('#sure_pwd').val() && $('#lenpwd').val() != "" && $('#sure_pwd').val() != ""){
				if($('#checksurepwd').length <= 0) {
					var div = $('#pwdright'); 
					$("#pwdright").removeClass("right-ipt");					
					var insertText = '<div id="checksurepwd" class="caution fl caution-err"><b></b>两次输入密码不相同</div>'; 
					$("#surepwd").append(insertText);
				}
			}
			if(($('#lenpwd').val() == $('#sure_pwd').val()) && $('#lenpwd').val() != "" && $('#sure_pwd').val() != ""){
				var div = $('#pwdright');
				$("#pwdright").addClass("right-ipt");
			}
		 }
		 <!--验证手机 -->
		 function foucscellph(obj){
		 	if($("#hascell").length > 0){
		 		$("#hascell").remove();
		 	}
		 	if(!$('#cellphdiv').length > 0) {
				var insertText = '<div id="cellphdiv" class="caution fl"><b></b>请输入手机号码，获取验证码</div>'; 
				$("#cellph").append(insertText);     	 	  
			}
			if($('#cellphdiv').length > 0 && $('#cellpherror').length > 0){
				var my = $("#cellpherror");
				if (my.length > 0 ){				    
					my.remove();
				}
			}
			if($('#cellphone').length > 0){
				var my = $("#cellphone");
				if (my.length > 0){				    
					my.remove();
				}
			}
			var div = $('#cellright'); 
			$("#cellright").removeClass("right-ipt");
			
			if($('#cellphdiv11').length > 0){
				var my = $("#cellphdiv11");
				if (my.length > 0){				    
					my.remove();
				}
			}
			$('#cellright').hide();
		 }
		 function blurcellph(){
		 	var my = $("#cellphdiv");
				if (my.length > 0){				    
					my.remove();
				}
			if($("#cellnumber").val() != ""){
					$.ajax({
						type: "get",
						url: "checkcell.action?cellnumber="+$("#cellnumber").val(),
						success: function(data, textStatus){
								if(data == "2"){
									var insertText = '<div id="hascell" class="caution fl caution-err"><b></b>手机号码已绑定，请更换号码</div>'; 
									$("#cellph").append(insertText);
								}
								if(data == "1"){
									var div = $('#cellright'); 
									$("#cellright").addClass("right-ipt").show();
									
								}else if(data == "0"){
									var insertText = '<div id="cellphone" class="caution fl caution-err"><b></b>手机号码格式有误，请输入正确的手机号</div>'; 
									$("#cellph").append(insertText);
								}
							}
					});
			}
		 }
		 <!--验证码 -->
		 function foucscheckcode(){
		 	var ccerr = $('#ckdiv');
		 	if(ccerr && ccerr.length > 0) {
		 		ccerr.remove();
		 	}
		 	var my = $("#checkcodeerror");
			if (my && my.length > 0){				    
				my.remove();
			}
				
		 	if(!$('#checkcodediv').length > 0) {
				var insertText = '<div id="checkcodediv" class="caution fl"><b></b>输入验证码</div>'; 
				$("#checkcode").append(insertText);     	 	  
			}
			if($('#checkcodediv').length > 0 && $('#checkcodeerror').length > 0){
				var my = $("#checkcodeerror");
				if (my.length > 0){				    
					my.remove();
				}
			}
			if($('#checkcodediv11').length > 0){
				var my = $("#checkcodediv11");
				if (my.length > 0){				    
					my.remove();
				}
			}
			if($('#ckdiv').length > 0){
				var my = document.getElementById("ckdiv");
				if (my.length > 0){				    
					my.remove();
				}
			}
		 }
		 function blurcheckcode(){
		 	var my = $("#checkcodediv");
				if (my.length > 0){				    
					my.remove();
				}
		 }
		 
		
		 
  </script>
  
  <script language=javascript>
  	
  	 function submit1(){
  	 	if($("#user_name").val() == "" && $("#username11").length == 0){
  	 		var insertText = '<div id="username11" class="caution fl caution-err"><b></b>请输入用户名</div>'; 
			$("#user").append(insertText);
  	 	}
  	 	if($("#lenpwd").val() == "" && $("#pwddiv11").length == 0){
  	 		var insertText = '<div id="pwddiv11" class="caution fl caution-err"><b></b>请输入密码</div>'; 
			$("#pwd").append(insertText); 
  	 	}
  	 	if($("#sure_pwd").val() == "" && $("#surepwddiv11").length == 0){
  	 		var insertText = '<div id="surepwddiv11" class="caution fl caution-err"><b></b>请确认密码</div>'; 
			$("#surepwd").append(insertText);     	 	  
  	 	}
  	 	if($("#cellnumber").val() == "" && $("#cellphdiv11").length == 0){
  	 		var insertText = '<div id="cellphdiv11" class="caution fl caution-err"><b></b>请输入手机号码</div>'; 
			$("#cellph").append(insertText);     	 	  
  	 	}
  	 	if($("#checkinput").val() == "" && $("#checkcodediv11").length == 0){
  	 		var insertText = '<div id="checkcodediv11" class="caution fl caution-err"><b></b>输入短信验证码</div>'; 
			$("#checkcode").append(insertText);     	 	  
  	 	}
  	 	if($("#username11").length == 0 && $("#pwddiv11").length == 0 && $("#surepwddiv11").length == 0 &&
  	 		$("#cellphdiv11").length == 0 && $("#checkcodediv11").length == 0 && $('#passlen').length == 0 &&
  	 		 $('#checklen').length ==0 && $('#checkstyle').length == 0 && $("#hascell").length == 0 && $('#passblank').length == 0){
  	 		 
				var data = {"user_name" : $("#user_name").val(),
				"lenpwd" :$("#lenpwd").val(),
				"sure_pwd":$("#sure_pwd").val(),
				"cellnumber": $("#cellnumber").val(),
				"checkinput": $("#checkinput").val() };	
							
  	 			$.ajax({
						type: "get",
						url: "checksubmit.action",
						data : data,
						success: function(data, textStatus){
								if(data == "1"){
									if($("#ckdiv").length == 0){
										var insertText = '<div id="ckdiv" class="caution fl caution-err"><b></b>验证码不正确</div>'; 
										$("#checkcode").append(insertText);  
									}
									
								}else{
									url = "<%=basePath %>user/uccenter.action";
									window.location = url;
								}
							}
					});
  	 	}
	
  	 }
  	
</script>

  <script language=javascript>

	//CharMode函数
	//测试某个字符是属于哪一类.
	function CharMode(iN){
		if (iN>=48 && iN <=57) //数字
			return 1;
		if (iN>=65 && iN <=90) //大写字母
			return 2;
		if (iN>=97 && iN <=122) //小写
			return 4;
		else
			return 8; //特殊字符
	}
	//bitTotal函数
	//计算出当前密码当中一共有多少种模式
	function bitTotal(num){
		modes=0;
		for (i=0;i<4;i++){
			if (num & 1) modes++;
				num>>>=1;
			}
		return modes;
	}
	//checkStrong函数
	//返回密码的强度级别
	function checkStrong(sPW){
		if (sPW.length<=4)
			return 0; //密码太短
		Modes=0;
		for (i=0;i<sPW.length;i++){
	//测试每一个字符的类别并统计一共有多少种模式.
			Modes|=CharMode(sPW.charCodeAt(i));
		}
		return bitTotal(Modes);
	}
	//pwStrength函数
	//当用户放开键盘或密码输入框失去焦点时,根据不同的级别显示不同的颜色
	function pwStrength(){
		if($('#security1').length > 0 || $('#pwdlendiv').length > 0 
				|| $('#security2').length > 0 || $('#security3').length > 0){
				$('#security1').remove();
				$('#security2').remove();
				$('#security3').remove();
			}
	var my = $("#pwddiv");
				if (my.length > 0){				    
					my.remove();
				}
		var pwd = $('#lenpwd').val();
		if(pwd != ""){
			if(pwd.length >= 6){
				var rep = /[\s]+/;	
				if(!rep.test($('#lenpwd').val())){					
					var S_level=checkStrong(pwd);
					switch(S_level) {
						case 0:
								if($('#security1').length == 0){							
									$("#pwd").after('<div id="security1" class="sign-info2"><h4 class="fl">安全程度：</h4><ul class="psd-color fl"><li><em class="red">弱</em><em>中</em><em>强</em></li></ul> <div class=" clearfix"></div> </div>');
								}
							break;
						case 1:
								if($('#security2').length == 0){							
							    	$("#pwd").after('<div id="security2" class="sign-info2"><h4 class="fl">安全程度：</h4><ul class="psd-color fl"><li><em>弱</em><em class="yellow">中</em><em>强</em></li> </ul> <div class=" clearfix"></div> </div>'); 
								}
							break;
						case 2:
								if($('#security3').length == 0){						
									$("#pwd").after('<div id="security3" class="sign-info2"><h4 class="fl">安全程度：</h4><ul class="psd-color fl"><li><em>弱</em><em>中</em><em class="green">强</em></li></ul> <div class=" clearfix"></div></div>'); 
								}
							break;
					}
					if($('#security1').length >0  && $('#security2').length > 0){
						$('#security1').remove();
					}
					if($('#security1').length > 0 && $('#security3').length > 0){
						$('#security1').remove();
					}
					if($('#security2').length > 0 && $('#security3').length > 0){
						$('#security2').remove();
					}
				}else{
					if($('#passblank').length <= 0) {
					var insertText = '<div id="passblank" class="caution fl caution-err"><b></b>密码中不能包含空格</div>'; 
					$("#pwd").append(insertText);
				}
				}			
			}else if(pwd.length < 6){
				if($('#passlen').length <= 0) {
					var insertText = '<div id="passlen" class="caution fl caution-err"><b></b>密码不少于6位</div>'; 
					$("#pwd").append(insertText);
				}
			}
			
		}else if(pwd == ""){
			if($('#security1').length > 0 || $('#pwdlendiv').length > 0 
				|| $('#security2').length > 0 || $('#security3').length > 0){
				$('#security1').remove();
				$('#security2').remove();
				$('#security3').remove();
			}
		}
	return;
}
</script>
  
<title>注册</title>
</head>

<body >

    <!--头部开始-->
    <!--注册-->
	<div class="w1200">
		<div class="logo-sign" style="margin:40px 0 10px 0;">
				<a href="<%=basePath%>index.action"><img src="<%=basePath %>inc/images/logo-sign.png" />
				</a>
				<b>欢迎注册</b>
		</div>
		<div class="login-p">
			我已经注册现在就
			<a href="<%=basePath%>login.action">登录</a>
		</div>
			 <sf:form method="get"  modelAttribute="userReg">
				<div id="form" class="box-sign">
					<div id="form1" class="form-sn">
						<h2>
							用户注册
						</h2>
						<div id="user" class="sign-info">
							<span class="fl"><i>*</i>用户名:</span>
							<div class="fl sign-ipt">
							<!-- onclick="foucsuser()" onblur="bluruser()" -->
								<sf:input id="user_name" path="user_name"  maxlength="20" cssClass="sign-txt"  onfocus="foucsuser()" onblur="bluruser()" />
								<b id="userright" ></b>
							</div>
						</div>
						<div id="pwd" class="sign-info">
							<span class="fl"><i>*</i>请设置密码:</span>
							<!-- onkeyup="textlen()"  onfocus="foucspwd()" onblur="blurpwd()" -->
							<div class="fl sign-ipt sign-ipt2">
								<sf:password  id="lenpwd" path="passwd"  maxlength="20" cssClass="sign-txt" onfocus="foucspwd()" onblur="pwStrength()" />
								<b></b>
							</div>
						</div>
						<div id="surepwd" class="sign-info">
							<span class="fl"><i>*</i>请确认密码:</span>
							<!-- onfocus="foucspwdcheck()" onblur="blurpwdcheck()" -->
							<div class="fl sign-ipt sign-ipt2">
								<sf:password id="sure_pwd" path="sure_passwd" maxlength="20" cssClass="sign-txt" onfocus="foucspwdcheck()" onblur="blurpwdcheck()"/>
								<b id="pwdright"></b>
							</div >
						</div>
						
						<script type="text/javascript">
							var set_time; 

					    	
					    	function readySetUpTime()
					    	{
					    		set_time = interval();
					    	}
					    	
					    	function setUpTime()
					    	{
					    		if(parseInt(set_time) < 60 && parseInt(set_time) >= 1)
					    		{  
					    			set_time = parseInt(set_time)+1 ;
					    			$("#cendcode").html('在60秒后请求系统');
					    			setTimeout(setUpTime(),1000);
					    			$("#cendcode").html('获取验证码');
					    		}
					    		else
					    		{
					    			$("#cendcode").text('获取验证码');
					    		}
					    	}
					    	
					    	function interval()
					    	{
					    		var time_s = 0;
					    		$.ajax({
									type: "get",
									async:false,
									url: "getcellPhoneBySendDate.action?phone="+$("#cellnumber").val(),
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
					    	
							function sendPhoneCode(){
								if($("#cellphone").val() == ""){
									alert("请输入手机号");
									$("#cellphone").focus();
								}else{
									readySetUpTime();
									if(parseInt(set_time) < 60 && parseInt(set_time) >= 1)
						    		{  
						    			set_time = parseInt(set_time)+1 ;
						    			$("#cendcode").html('在60秒后请求系统');
						    			setTimeout('setUpTime()',1000);
						    		}
						    		else
						    		{
						    			$("#cendcode").text('获取验证码');
										$.ajax({
											type: "get",
											url: "sendPhoneCode.action?phone="+$("#cellnumber").val(),
											success: function(data, textStatus){
												if(data == "1"){
													alert("发送成功");
												}else{
													alert("发送失败");
												}
											}
										});
						    		}
								}
							}
						</script>
						
						
						<div id="cellph" class="sign-info">
							<span class="fl"><i>*</i>请输入手机号</span>
			                <div class="fl sign-ipt">
				                <sf:input id="cellnumber" cssClass="sign-txt" maxlength="11" path="cellphone" onfocus="foucscellph(this)" onblur="blurcellph()"/>
				                <b  id="cellright" style="display:none"></b>
			                </div>
			                
						</div>
						
						<div id="checkcode" class="sign-info">
							<span class="fl"><i>*</i>请输入验证码:</span>
							<div class="fl">
								<sf:input id="checkinput" maxlength="6" cssClass="sign-code" path="check_code" onfocus="foucscheckcode()" onblur="blurcheckcode()"/>
								<a id="cendcode" href="javascript:void(0);" onclick="sendPhoneCode()" class="get_btn sign-codeimg">获取验证码</a>
								<b></b>
							</div>
						</div>
						
						<div class="sign-info2">
							<input class="sign-chk" type="checkbox" value="on" checked="true"/>
							<label class="at">
								我已阅读并同意<a href="#" class="chag:hover fgps xy">《用户注册协议》</a>
							</label>
						</div>
						<div class="sign-info2">
							<input id="sub" type="button" class="sign-btn" value="立即注册"  onclick="submit1()"/>
						</div>
					</div>
					<div class="sign-bg">
						<img src="<%=basePath %>inc/images/login-img.png"/>
					</div>
				</div>
				</sf:form>
			</div>
			
			
 		
		<!--底部-->
		 <%@ include file="/WEB-INF/view/inc/footer.jsp" %>
	</body>
</html>