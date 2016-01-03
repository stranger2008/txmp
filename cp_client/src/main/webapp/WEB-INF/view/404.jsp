<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
<title>404找不到页面</title>
</head>

<body>
    <%@ include file="/WEB-INF/view/inc/top.jsp" %>
    
    <%@ include file="/WEB-INF/view/inc/search.jsp" %>
    
    <div class="page-err">
		<h2 class="pge-title">很抱歉，您查看的页面找不到了！</h2>
	    <div class="pge-cont">
	        <p>您可以：</p>
	        <ul>
	            <li>1.检查刚才的输入</li>
	            <li>2.刷新页面</li>
	            <li>3.去其他地方：<a href="<%=basePath %>">首页</a>&nbsp;<a href="<%=basePath %>user/uccenter.action">会员中心</a></li>
	        </ul>
	    </div>
	</div>
    
    
    <!--底部开始-->
    <%@ include file="/WEB-INF/view/inc/footer.jsp" %>

</body>
</html>
