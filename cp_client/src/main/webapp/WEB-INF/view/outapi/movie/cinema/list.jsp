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
<script type="text/javascript">
$(document).ready(function(){
  $("li.region").click(function(){
	  $("#li-" + $(this).attr("id")).toggle();
  });
});
</script>
<title>影院列表-天下名品电影票</title>
</head>

<body>
<%@ include file="/WEB-INF/view/outapi/movie/header.jsp" %>
<section>
    <div class="movTheater">
    	<%--<h2 class="movTheater-tit">
            <ul>
                <li class="whitebg-hover"><a href="###">区域</a></li>
                <li><a href="###">距离</a></li>
            </ul>
        </h2>--%>
        <c:set var="curAreaNo"></c:set>
        <c:choose><c:when test="${not empty errMsg}"><p class="no-mov" style="text-align:center;margin-top:40px;">${errMsg }</p></c:when>
        <c:otherwise><ul><c:forEach items="${areaMovieShowTimeList }" var="areaMovieShowTime" varStatus="loop" >
			<c:choose>
				<c:when test="${(loop.index == 0) or (areaMovieShowTime.areaNo != curAreaNo)}">
				<c:if test="${loop.index > 0}"></ul></li></c:if>
        <li class="region" id="${areaMovieShowTime.areaNo }"><a href="#">${areaMovieShowTime.areaName }<span class="arr-b1"></span></a></li>
		<li id="li-${areaMovieShowTime.areaNo }" <c:if test="${loop.index > 0}">style="display:none;"</c:if>>
           	<ul class="movTheater-lst">
               	<li><a href="<c:url value="/movie/cinema/movieDetail.action?cinemaNo=${areaMovieShowTime.cinemaNo }&filmNo=${filmNo }" />"><span class="fl mov-shop">${areaMovieShowTime.cinemaName }</span><span class="fr price">最低价：<span class=" red-pri">${areaMovieShowTime.salePrice }</span></span><span class="arr-b2"></span></a></li>
				</c:when>
				<c:otherwise>
               	<li><a href="<c:url value="/movie/cinema/movieDetail.action?cinemaNo=${areaMovieShowTime.cinemaNo }&filmNo=${filmNo }" />"><span class="fl mov-shop">${areaMovieShowTime.cinemaName }</span><span class="fr price">最低价：<span class=" red-pri">${areaMovieShowTime.salePrice }</span></span><span class="arr-b2"></span></a></li>
				</c:otherwise>
			</c:choose>
				<c:if test="${loop.count == fn:length(areaMovieShowTimeList)}"></ul></li></c:if>
        <c:set var="curAreaNo">${areaMovieShowTime.areaNo}</c:set>
        </c:forEach></ul></c:otherwise></c:choose>
    </div>
</section>
<%@ include file="/WEB-INF/view/inc/footer.jsp" %>      
</body>
</html>
