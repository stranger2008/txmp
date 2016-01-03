<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/outapi/movie/tag.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1,user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
      
<link rel="stylesheet" href="<c:url value="/inc/outapi/movie/css/movie.css" />">
<script type="text/javascript" src="<c:url value="/inc/outapi/movie/js/jquery.min.js" />"></script>
<title>影院详情-天下名品电影票</title>
<script type="text/javascript">
$(document).ready(function(){
	$("div.movie-date > a:first").addClass('sele-hover');
  $("div.movie-date > a").click(function(){
  	  $('div.movie-date > a').removeClass('sele-hover');
  	  $(this).addClass('sele-hover');
  	  $('ul.movie-detail').hide();
	  $("#ul-" + $(this).attr("id")).show();
  });
});
</script>
</head>
<body>
<%@ include file="/WEB-INF/view/outapi/movie/header.jsp" %>
<!--影院列表-->
<section>
    <div class="movie">
    <!--banner开始-->
    <div class="movie-banner margin-center">
        <article>
          <!--scroll-->
          <div class="scroll relative">
            <div class="scroll_box" id="scroll_img">
            	<c:if test="${not empty movie.moviePictures.pictureList}">
                <ul class="scroll_wrap">
                <c:forEach items="${movie.moviePictures.pictureList}" var="moviePicture" end="9">
                  <li class="movie-img"><a href="javascript:void(0);"><img src="${moviePicture.pictureUrl }" class="full-img" /></a></li>
                </c:forEach>
                </ul>
                </c:if>
            </div>
            <ul class="theaterName">
              <li>${cinema.cinemaName }</li>
            </ul>
            <div class="down"><b></b></div>
          </div>
          <!--scroll-->
        </article>
        <script src='<c:url value="/inc/outapi/movie/js/hhSwipe.js" />'></script>
    </div>
    	<h2 class="movie-name">${movie.filmName }</h2>
		<div class="movie-cont">
        	<p>导演：${movie.directors }<br />主演：${movie.mainActors } </p>
            <div class="movie-date clearfix">
            	<c:set var="curShowDate"></c:set>
            	<c:forEach items="${cinemaMovieShowTimeList}" var="cinemaMovieShowTime" varStatus="loop">
            	<c:if test="${loop.index == 0 or cinemaMovieShowTime.showDate != curShowDate}">
            	<a href="javascript:void(0);" id="${cinemaMovieShowTime.showDate }">${cinemaMovieShowTime.showDate }</a>
            	<c:set var="curShowDate">${cinemaMovieShowTime.showDate }</c:set>
            	</c:if>
            	</c:forEach>
            </div>
           	
           	<c:set var="curShowDate"></c:set>
           	<c:forEach items="${cinemaMovieShowTimeList}" var="cinemaMovieShowTime" varStatus="loop" >
			<c:choose>
				<c:when test="${(loop.index == 0) or (cinemaMovieShowTime.showDate != curShowDate)}">
				<c:if test="${loop.index > 0}"></ul></c:if>
            <ul class="movie-detail" id="ul-${cinemaMovieShowTime.showDate }" <c:if test="${loop.index > 0}">style="display:none;"</c:if> >
            	<li><span class="text-red">${cinemaMovieShowTime.showTime }</span>
            	<span>${cinemaMovieShowTime.showTypeDesc}/${cinemaMovieShowTime.language}</span>
            	<span>${cinemaMovieShowTime.hallName }</span><span class="text-red">${cinemaMovieShowTime.salePrice }</span>
            	<span><a href="<c:url value="/movie/cinema/seat.action?cinemaNo=${cinemaNo}&filmNo=${filmNo}&seqNo=${cinemaMovieShowTime.seqNo}" />" class="movBuy-btn">购买</a></span></li>
				</c:when>
				<c:otherwise>
            	<li><span class="text-red">${cinemaMovieShowTime.showTime }</span>
            	<span>${cinemaMovieShowTime.showTypeDesc}/${cinemaMovieShowTime.language}</span>
            	<span>${cinemaMovieShowTime.hallName }</span><span class="text-red">${cinemaMovieShowTime.salePrice }</span>
            	<span><a href="<c:url value="/movie/cinema/seat.action?cinemaNo=${cinemaNo}&filmNo=${filmNo}&seqNo=${cinemaMovieShowTime.seqNo}" />" class="movBuy-btn">购买</a></span></li>
				</c:otherwise>
			</c:choose>
			<c:if test="${loop.count == fn:length(cinemaMovieShowTimeList)}"></ul></c:if>
			<c:set var="curShowDate">${cinemaMovieShowTime.showDate }</c:set>
            </c:forEach>
        </div>
    </div>
</section>
<%@ include file="/WEB-INF/view/inc/footer.jsp" %>
</body>
</html>
