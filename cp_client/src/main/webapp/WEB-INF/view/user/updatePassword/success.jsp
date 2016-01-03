<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
<title>修改密码-<%=web_title %></title>
</head>

<body>

<div class="w480">
    
    <%@ include file="/WEB-INF/view/inc/top.jsp" %>
        
    <div class="content">
    
	    <!--当前位置开始-->
	    <div class="module">
	        <section>
	            <div class="position mt5">
	                <a href="<%=basePath %>">首页</a> > <a href="<%=basePath %>user/uccenter.action">用户中心</a> > <a href="<%=basePath %>user/updatePassword.action">修改密码</a>
	            </div>
	        </section>
	    </div>
	    
	    <!--内容开始-->
	    <div class="module">
	        <section class="box_sign">
	                <span class="sign_span">密码修改成功</span>
	        </section>
	    </div>
	    
	</div>
    
    
    <%@ include file="/WEB-INF/view/inc/footer.jsp" %>
</div>


</body>
</html>
