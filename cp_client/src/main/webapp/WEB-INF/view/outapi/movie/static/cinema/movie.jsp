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
<title>电影票-影院详细</title>
</head>
<body>
<%@ include file="/WEB-INF/view/outapi/movie/static/header.jsp" %>
<!--影院列表-->
<section>
    <div class="movie">
    <!--banner开始-->
    <div class="movie-banner margin-center">
        <article>
          <!--scroll-->
          <div class="scroll relative">
            <div class="scroll_box" id="scroll_img">
                <ul class="scroll_wrap">
                  <li class="movie-img"><a href="###"><img src="<c:url value="/inc/outapi/movie/images/movie.jpg" />" class="full-img" /></a></li>
                  <li class="movie-img"><a href="###"><img src="<c:url value="/inc/outapi/movie/images/movie.jpg" />" class="full-img" /></a></li>
                  <li class="movie-img"><a href="###"><img src="<c:url value="/inc/outapi/movie/images/movie.jpg" />" class="full-img" /></a></li>
                  <li class="movie-img"><a href="###"><img src="<c:url value="/inc/outapi/movie/images/movie.jpg" />" class="full-img" /></a></li>
                </ul>
            </div>
            <ul class="theaterName">
              <li>国贸百丽宫影院</li>
            </ul>
            <div class="down"><b></b></div>
          </div>
          <!--scroll-->
        </article>
        <script src='<c:url value="/inc/outapi/movie/js/hhSwipe.js" />'></script>
    </div>
    	<h2 class="movie-name">明日边缘</h2>
		<div class="movie-cont">
        	<p>导演：道格·里曼<br />主演：汤姆·克鲁斯 | 艾米莉·布朗特 | 诺亚·泰勒 </p>
            <div class="movie-date clearfix">
            	<a class="sele-hover" href="#">06-19</a><a href="#">06-20</a>
            </div>
            <ul class="movie-detail">
            	<li><span class="text-red">16:55</span><span>2D/国语</span><span>10号厅</span><span class="text-red">68.00</span><span><a href="<c:url value="/movie/static/cinema/seat.action" />" class="movBuy-btn">购买</a></span></li>
            	<li><span class="text-red">20:00</span><span>3D/国语</span><span>8号厅</span><span class="text-red">98.00</span><span><a href="<c:url value="/movie/static/cinema/seat.action" />" class="movBuy-btn">购买</a></span></li>
            </ul>
        </div>
    </div>
</section>
<%@ include file="/WEB-INF/view/inc/footer.jsp" %>
</body>
</html>
