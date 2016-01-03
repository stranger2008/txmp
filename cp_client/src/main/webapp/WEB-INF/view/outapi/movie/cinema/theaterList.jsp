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
        <c:choose><c:when test="${not empty errMsg}"><p class="no-mov" style="text-align:center;margin-top:40px;">${errMsg }</p></c:when>
        <c:otherwise><ul>
        	<c:forEach items="${areaTheaterList }" var="areaTheater" varStatus="loop" >
        	<li class="region" id="${areaTheater.no }"><a href="javascript:void(0);">${areaTheater.name }<span class="arr-b1"></span></a></li>
        	<li id="li-${areaTheater.no }" <c:if test="${loop.index > 0}">style="display:none;"</c:if>><ul class="movTheater-lst">
        		<c:forEach items="${areaTheater.theaterList}" var="theater">
                	<li><a href="<c:url value="/movie/cinema/movieList.action?cinemaNo=${theater.cinemaNo }" />">${theater.cinemaName }<span class="arr-b2"></span></a></li>
        		</c:forEach>
        	</ul></li>
        	</c:forEach></ul>
        </c:otherwise></c:choose>
    </div>
</section>
<%@ include file="/WEB-INF/view/inc/footer.jsp" %>      
</body>
</html>
