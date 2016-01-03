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
	                <a href="<%=basePath %>findPwd/index.action">找回密码</a> > <a href="javascript:void(0);">填写手机号码</a> > <a href="javascript:void(0);">验证身份</a> > <a href="javascript:void(0);">设置新密码</a> > <a href="javascript:void(0);">完成</a>
	            </div>
	        </section>
	    </div>
	    
	    <!--内容开始-->
	    <div class="module">
	        <section class="box_sign">
	                <span class="sign_span">新密码设置成功</span>
	        </section>
	    </div>
	    
	</div>
    
    
    <%@ include file="/WEB-INF/view/inc/footer.jsp" %>
</div>


</body>
</html>
