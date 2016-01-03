<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
<title>登录-<%=web_title %></title>
</head>

<body>

<div class="w480">
    
    <%@ include file="/WEB-INF/view/inc/top.jsp" %>
        
    <div class="content">
    
	    <!--当前位置开始-->
	    <div class="module">
	        <section>
	            <div class="position mt5">
	                <a href="<%=basePath %>">首页</a> > <a href="javascript:void(0);">用户登录</a>
	            </div>
	        </section>
	    </div>
	    
	    <!--内容开始-->
	    <div class="module">
	        <section class="box_sign">
	            <sf:form method="post" action="logon.action" modelAttribute="userLogin">
	                <span class="sign_span">请输入手机号</span>
	                <span><sf:input path="cellphone" type="tel" cssClass="usrname"/></span>
	                <sf:errors path="cellphone" cssClass="error"/>
	                <span class="sign_span">请输入密码</span>
	                <span><sf:password path="passwd" cssClass="psd"/></span>
	                <sf:errors path="passwd" cssClass="error" />
	                <span class="sign_span">验证码</span>
                	<span>
                		<sf:input path="check_code" type="tel" cssClass="usrname" cssStyle="width:70px;" maxlength="4"/>
                		<%@ include file="/WEB-INF/view/inc/imgcode.jsp" %>
                		<sf:errors path="check_code" cssClass="error" />
	                </span>
	                <span><input type="submit" class="sign_btn" value="登录"></span>
	                <span><p class="reg_btn"><a href="<%=basePath %>signup.action">新用户注册</a>&nbsp;&nbsp;
	                <a href="<%=basePath %>findPwd/index.action">找回密码</a></p></span>
	            </sf:form>
	        </section>
	    </div>
	    
	</div>
    
    
    <%@ include file="/WEB-INF/view/inc/footer.jsp" %>
</div>


</body>
</html>
