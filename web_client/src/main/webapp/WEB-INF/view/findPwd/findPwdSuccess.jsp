<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link rel="stylesheet" href="<%=basePath %>inc/css/public.css" />
		<link rel="stylesheet" href="<%=basePath %>inc/css/sign.css"/>
		<title>找回密码</title>
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
					        <li class="one one1">填写手机号码</li>
					        <li class="two two1">验证身份</li>
					        <li class="three">设置新密码</li>
					        <li class="four cur four1">完成</li>
					    </ul>
					</div>
					<div class="find_pwd_form">
					    <div class="call suc m-return">
                			<b></b>
                			<span><strong>新密码设置成功!</strong><br/>
                			请牢记您新设置的密码。
                			<a href="<%=basePath %>">返回首页</a>
                			</span>
             			</div>
					</div>
				</div>
			</div>
		</div>
		<!--底部-->
		 <%@ include file="/WEB-INF/view/inc/footer.jsp" %>
	</body>
</html>
