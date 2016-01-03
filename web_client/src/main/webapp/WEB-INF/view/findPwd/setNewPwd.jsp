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
				//
				 $("#updatePWD").click(function(){
				 	var old_passwd = $("#old_passwd").val();//新登录密码
				 	var new_passwd = $("#new_passwd").val();//确认新密码
				 	var cfm_passwd = /^(\w){6,20}$/;//只能输入6-20个字母、数字、下划线	
			    	if(old_passwd ==""){ 
	        			alert("请输入新登录密码!"); 
	      				document.getElementById("old_passwd").focus();
	    			}
	    			else if(!cfm_passwd.test(old_passwd))
	    			{
	    				alert("新登录密码只能由6-20个字母、数字、下划线组成"); 
	    				$("#old_passwd").val("");
	      				document.getElementById("old_passwd").focus();
	    			}
	    			else if(old_passwd.length<6 || old_passwd.length>20)
	    			{
	    				alert("新登录密码只能由6-20个字母、数字、下划线组成");
	    				$("#old_passwd").val("");
	      				document.getElementById("old_passwd").focus();
	    			}
	    			else if(old_passwd != new_passwd)
	    			{
	    				alert("新登录密码与确认新密码不一致");
	    				$("#old_passwd").val("");
	    				$("#new_passwd").val("");
	      				document.getElementById("old_passwd").focus();
	    			}
	    			else
	    			{
	    				document.getElementById("setNewPwdPostForm").submit();
	    			}				
				});
			})
		</script>
	</head>
	<body>
		<!--头部开始-->
		 <%@ include file="/WEB-INF/view/inc/top.jsp" %>
		<!--登录-->
		<div class="w1200">
			<div class="logo-sign">
				<a href="<%=basePath %>index.action"><img src="<%=basePath %>inc/images/logo-sign.png"></a>
				<b>找回密码</b>
			</div>
			<div class="boxFindpwd">
				<div class="box_findpwd">
					<h2>找回密码</h2><b></b>
				</div>
				<div class="mc">
					<div class="step">
					    <ul>
					        <li class="one one1">填写手机号码</li>
					        <li class="two">验证身份</li>
					        <li class="three cur">设置新密码</li>
					        <li class="four">完成</li>
					    </ul>
					</div>
					<sf:form method="post" action="findPwd/setNewPwdPost.action" id="setNewPwdPostForm" modelAttribute="updatepasswd">
					<div class="find_pwd_form">
					    <div class="item">
					        <span class="label">新登录密码：</span>
					        <div class="fl">
					            <sf:password path="old_passwd" class="text"  maxlength="16" />
					            <span class="clr"></span>
					            <sf:errors path="old_passwd" style="color:#ff6600;"/>
					        </div>
					    </div>
					    <div class="item">
					        <span class="label">确认新密码：</span>
					        <div class="fl">
					            <sf:password path="new_passwd" class="text"  maxlength="16" />
					            <span class="clr"></span>
					            <sf:errors path="new_passwd" style="color:#ff6600;"/>
					        </div>
					    </div>
					    <div class="item">
    						<span class="label">&nbsp;</span>
    						<input id="updatePWD" class="btn-img btn-entry" type="button"  value="提交"></input>
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
