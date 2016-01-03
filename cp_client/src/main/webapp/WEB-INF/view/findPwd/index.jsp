<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
<title>找回密码-<%=web_title %></title>
</head>

<body>

<div class="w480">
    
    <%@ include file="/WEB-INF/view/inc/top.jsp" %>
        
    <div class="content">
    
	    <!--当前位置开始-->
	    <div class="module">
	        <section>
	            <div class="position mt5">
	                <a href="<%=basePath %>findPwd/index.action">找回密码</a> > <a href="javascript:void(0);">填写手机号码</a>
	            </div>
	        </section>
	    </div>
	    
	    <!--内容开始-->
	    <div class="module">
	        <section class="box_sign">
	            <sf:form method="post" action="findPwd/index.action" modelAttribute="findPwdSetting">
	                <span class="sign_span">请输入手机号</span>
	                <span><sf:input path="cellphone" type="tel" cssClass="usrname" maxlength="20"/></span>
	                <sf:errors path="cellphone" cssClass="error"/>
	                <span class="sign_span">验证码</span>
                	<span>
                		<sf:input path="check_code" type="tel" cssClass="usrname" cssStyle="width:70px;" maxlength="4"/>
                		<%@ include file="/WEB-INF/view/inc/imgcode.jsp" %>
                		<sf:errors path="check_code" cssClass="error" />
	                </span>
	                <span><input type="submit" class="sign_btn" value="下一步"></span>
	            </sf:form>
	        </section>
	    </div>
	    
	</div>
    
    
    <%@ include file="/WEB-INF/view/inc/footer.jsp" %>
</div>


</body>
</html>
