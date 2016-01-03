<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"  
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">  
<html>
<head>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<meta http-equiv="Content-type" content="text/html; charset=UTF-8"/>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<link rel="stylesheet" href="<%=basePath %>inc/css/login.css"/>
<script type="text/javascript" src="<%=basePath %>inc/js/jquery-1.4.4.min.js"></script>
<title>商家登录</title>
</head>

<body style="background:url(<%=basePath %>inc/images/bodybg.gif) repeat-x; vertical-align:top;">

 <!--内容开始-->
    <div class="signsp-box">
        <div class="logo margin-center">
            <a href="javascript:void(0);"><img class="full-img" src="<%=basePath %>inc/images/logo.png"/></a>	
        </div>
        <div class="shop-sign margin-center">
            <h1 class="shop-sign-tit">天下名品商家后台登录</h1>
            <sf:form method="post" cssClass="shop-sign-form" action="login.action" modelAttribute="login">
                <p><span class="shop-sign-txt">用户名：</span><span><sf:input path="user_name" cssClass="shop-ipt"/></span></p>
                <p><sf:errors path="user_name" cssClass="error"/></p>
                <p><span class="shop-sign-txt">密码：</span><span><sf:password path="passwd" cssClass="shop-ipt"/></span></p>
                <p><sf:errors path="passwd" cssClass="error"/></p>
                <p>
                	<span class="shop-sign-txt">验证码：</span>
                    <span>
                    	<sf:input path="check_code" cssClass="cd-ipt" maxlength="4"/>
                    	<%@ include file="/WEB-INF/view/inc/imgcode.jsp" %>
					</span>
                </p>
                <p><sf:errors path="check_code" cssClass="error"/></p>
                <p><input type="submit" class="shop-sign-btn" value="登录"/><input type="reset" class="shop-sign-btn" value="重设"/></p>
            </sf:form>
        </div>
    </div>
</body>
</html>
