<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/outapi/movie/tag.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="Content-type" content="text/html; charset=UTF-8">
<meta name="keywords" content="关键字">
<meta name="description" content="网站描述"> 

<meta name="viewport" content="width=device-width, initial-scale=1, target-densitydpi=high-dpi,
 maximum-scale=1,user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
      
<link rel="stylesheet" href="<c:url value="/inc/outapi/movie/css/movie.css" />">
<script type="text/javascript" src="<c:url value="/inc/outapi/movie/js/jquery.min.js" />"></script>
<title>电影票-地区选择</title>
</head>
<body>
<%@ include file="/WEB-INF/view/outapi/movie/static/header.jsp" %>
<section>
    <div class="theaterCity">
		<ul>
        	<li class="region text-red">选择城市</li>
        	<li>
            	<ul class="theaterCity-lst">
                	<li><a href="<c:url value="/movie/static.action" />">北京<span class="arr-b2"></span></a></li>
                	<li><a href="<c:url value="/movie/static.action" />">上海<span class="arr-b2"></span></a></li>
                	<li><a href="<c:url value="/movie/static.action" />">广州<span class="arr-b2"></span></a></li>
                	<li><a href="<c:url value="/movie/static.action" />">深圳<span class="arr-b2"></span></a></li>
                	<li><a href="<c:url value="/movie/static.action" />">天津<span class="arr-b2"></span></a></li>
                	<li><a href="<c:url value="/movie/static.action" />">南京<span class="arr-b2"></span></a></li>
                	<li><a href="<c:url value="/movie/static.action" />">北京<span class="arr-b2"></span></a></li>
                	<li><a href="<c:url value="/movie/static.action" />">上海<span class="arr-b2"></span></a></li>
                	<li><a href="<c:url value="/movie/static.action" />">广州<span class="arr-b2"></span></a></li>
                	<li><a href="<c:url value="/movie/static.action" />">深圳<span class="arr-b2"></span></a></li>
                	<li><a href="<c:url value="/movie/static.action" />">天津<span class="arr-b2"></span></a></li>
                	<li><a href="<c:url value="/movie/static.action" />">南京<span class="arr-b2"></span></a></li>
                	<li><a href="<c:url value="/movie/static.action" />">北京<span class="arr-b2"></span></a></li>
                	<li><a href="<c:url value="/movie/static.action" />">上海<span class="arr-b2"></span></a></li>
                	<li><a href="<c:url value="/movie/static.action" />">广州<span class="arr-b2"></span></a></li>
                	<li><a href="<c:url value="/movie/static.action" />">深圳<span class="arr-b2"></span></a></li>
                	<li><a href="<c:url value="/movie/static.action" />">天津<span class="arr-b2"></span></a></li>
                	<li><a href="<c:url value="/movie/static.action" />">南京<span class="arr-b2"></span></a></li>
                </ul>
            </li>
        </ul>
    </div>
</section>
<%@ include file="/WEB-INF/view/inc/footer.jsp" %>        
</body>
</html>
