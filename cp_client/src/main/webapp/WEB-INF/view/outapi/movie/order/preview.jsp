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
<title>电影票-订单确认</title>
</head>
<body>
<%@ include file="/WEB-INF/view/outapi/movie/static/header.jsp" %>
<!--详细订单-->
<section>
	<div class="movBg">
    	<div class="movOrd">
            <h2 class="movOrd-tit">详细订单</h2>
            <div class="movOrd-detail">
            	<ul>
                	<li><span>影院名：</span> 国贸百丽宫影院</li>
                	<li><span>影厅：</span> 6厅</li>
                	<li><span>电影名：</span> 明日边缘</li>
                	<li><span>日期：</span> 2014-06-11</li>
                	<li><span>场次：</span> 15:55</li>
                	<li><span>制式：</span> 2D</li>
                	<li><span>语言：</span> 英语（中文字幕）</li>
                	<li><span>座位号：</span>6排7号 6排9号</li>
                	<li><span>张数：</span> 2</li>
                </ul>
            </div>
            <p><a href="<c:url value="/movie/static/order/save.action" />" class="movOrd-btn">提交</a></p>
        </div>
    </div>
</section>
<%@ include file="/WEB-INF/view/inc/footer.jsp" %>
</body>
</html>
