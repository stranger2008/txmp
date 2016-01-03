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
			$(function () {
				$("#findCellphoneSubmit").click(function(){
					var cellphone = $("#cellphone").val();
				 	var check_code = $("#check_code").val();
				 	var cfmPhone = new RegExp("^1\\d{10}$");
				 	var cfmCode = new RegExp("^[0-9]*$");
				 	if(cellphone == "")
	    			{
	    				alert("请输入手机号码!"); 
	      				document.getElementById("cellphone").focus();
	    			}
				 	else if(!cfmPhone.test(cellphone)){
	        			alert("请输入正确的手机号码!"); 
	        			$("#cellphone").val("");
	      				document.getElementById("cellphone").focus();
	    			}
			    	else if(!cfmCode.test(check_code)){
	        			alert("请输入验证码!"); 
	        			$("#check_code").val("");
	      				document.getElementById("check_code").focus();
	    			}
	    			else if(check_code.length != 4)
	    			{
	    				alert("请输入4位验证码!"); 
	      				document.getElementById("check_code").focus();
	    			}
	    			else
	    			{
	    				document.getElementById("verifyByPhoneForm").submit();
	    			}
				});
			})
		</script>
	</head>
	<body>
		<!--头部开始-->
		 <%@ include file="/WEB-INF/view/inc/top.jsp" %>
		<div class="w1200">
			<div class="logo-sign">
				<a href="<%=basePath %>index.action"><img src="<%=basePath %>inc/images/logo-sign.png"/>
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
					        <li class="one cur">填写手机号码</li>
					        <li class="">验证身份</li>
					        <li class="">设置新密码</li>
					        <li class="four">完成</li>
					    </ul>
					</div>
					
					<sf:form method="post" action="findPwd/verifyByPhone.action" id="verifyByPhoneForm" modelAttribute="findPwd">
					<div class="find_pwd_form">
					    <div class="item">
					        <span class="label">手机号码：</span>
					        <div class="fl">
					            <sf:input path="cellphone" class="text"  maxlength="16"/>
					            <span class="clr"></span>
					            <sf:errors path="cellphone" style="color:#ff6600;"/>
					        </div>
					    </div>
					    <div class="item">
					        <span class="label">验证码：</span>
					        <div class="fl">
					            <sf:input path="check_code" class="text text-1" maxlength="4"/>
					            <%@ include file="/WEB-INF/view/inc/imgcode.jsp" %>
					            <label class="ftx23"></label>
					            <span class="clr"></span>
					            <sf:errors path="check_code" style="color:#ff6600;"/>
					        </div>
					    </div>
					    <div class="item">
    						<span class="label">&nbsp;</span>
    						<input id="findCellphoneSubmit" class="btn-img btn-entry" type="button" value="下一步"></input>
						</div>
					</div>
					</sf:form>
					
				</div>
			</div>
		</div>
		<!--底部-->
		 <%@ include file="/WEB-INF/view/inc/footer.jsp" %>
	</body>
</html>
