
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" href="<%=basePath %>inc/css/public.css">
    <link rel="stylesheet" href="<%=basePath %>inc/css/usercenter.css">
    <link rel="stylesheet" href="<%=basePath %>inc/css/product.css">
    <link rel="stylesheet" href="<%=basePath %>inc/css/account.css">
    <script src="<%=basePath %>inc/js/core.js"></script>
    <script src="<%=basePath %>inc/js/account.js"></script>
    <title>帮助中心</title>
</head>

<body>


<!--头部开始-->
	<%@ include file="/WEB-INF/view/inc/top.jsp" %>
		
		<%@ include file="/WEB-INF/view/inc/search.jsp" %>
		
		<%@ include file="/WEB-INF/view/inc/nav.jsp" %>

<!--个人中心-->
<div class="w1200">
	<div class="usercenter">
        <div class="leftnav fl">
        
        	<c:forEach items="${leftList}" var="item">
				<h2>${item.cat_name}</h2>
	            <ul>
	            	<c:forEach items="${item.aboutusList}" var="about">
	                <li><a href="<%=basePath %>aboutus/${about.info_id }.action">${about.title }</a></li>
	                </c:forEach>
	            </ul>
			</c:forEach>
            
        </div>
        <div class="user-info fr">
            <!--安全中心-->
            <div class="account-voucher">
                <h1 class="store-title">${aboutus.title }</h1>
                <div class="help-detail">
                    ${aboutus.content }
                </div>
            </div>
        </div>
	</div>
</div>

<div class="clearfix"></div>
		<!--底部-->
		 <%@ include file="/WEB-INF/view/inc/footer.jsp" %>
</body>
</html>
