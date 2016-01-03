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
<title>电影票-选择座位</title>
</head>
<body>
<%@ include file="/WEB-INF/view/outapi/movie/static/header.jsp" %>
<section>
    <div class="movieSeat margin-center">
        <h2>国贸百丽宫影院</h2>
        <div class="movieSeat-cont1">
            <h3>明日边缘</h3>
            <ul>
               <li><span>16:55</span><span>2D国语/10号厅</span><span>68.00元</span></li>
               <li class="li2"><span>银幕：2D</span><span>张数：2张</span></li>
            </ul>
        </div>
        <div class="seat-bar"></div>
        <div class="seatSel">
        	<div style="max-width:480px; max-height:397px; min-width:320px; min-height:397px; margin:0 auto;">
        	<a href="<c:url value="/movie/static/order/preview.action" />">
        	<img src="<c:url value="/inc/outapi/movie/images/seat.jpg" />" style="max-width:480px; max-height:397px; min-width:320px; min-height:397px;" class="full-img" />
        	</a>
        	</div>
        </div>
    </div>
</section>
<%@ include file="/WEB-INF/view/inc/footer.jsp" %>
</body>
</html>
