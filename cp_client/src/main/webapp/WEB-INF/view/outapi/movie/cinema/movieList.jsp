<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/outapi/movie/tag.jsp" %>
<%String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1,user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">

<link rel="stylesheet" href="<c:url value="/inc/outapi/movie/css/movie.css" />">
<script type="text/javascript" src="<c:url value="/inc/outapi/movie/js/jquery.min.js" />"></script>
<title>影片列表-天下名品电影票</title>
</head>
<body>
<%@ include file="/WEB-INF/view/outapi/movie/header.jsp" %>
<section>
    <div class="banner margin-center">
        <article <c:if test="${empty cinema.deviceUrl }">style="margin-bottom:55px;"</c:if>>
          <c:if test="${not empty cinema.deviceUrl }">
          <div class="scroll relative" style="top:-1px;">
            <div class="scroll_box" id="scroll_img">
                <ul class="scroll_wrap">
                  <li><a href="javascript:void(0);"><img src="${cinema.deviceUrl }" width="100%" /></a></li>
                </ul>
            </div>
            </c:if>
            <ul class="theaterName" <c:if test="${empty cinema.deviceUrl }">style="margin-top:50px;"</c:if>>
              <li>${cinema.cinemaName }</li>
            </ul>
          </div>
        </article>
    </div>
    <div class="hotShow margin-center">
        <div class="clearfix"></div>
        <c:choose><c:when test="${not empty errMsg}"><p class="no-mov">${errMsg }</p></c:when>
        <c:otherwise><ul class="hotShow-lst clearfix"><c:forEach items="${areaCinemaShowTimeList }" var="areaCinemaShowTime" >
            <li>
                <a href="<c:url value="/movie/cinema/movieDetail.action?cinemaNo=${cinemaNo}&filmNo=${areaCinemaShowTime.filmNo }" />">
                    <span class="hotShow-img"><img src="${areaCinemaShowTime.frontImg }"></span>
                    <div class="img-box">
                        <p>${areaCinemaShowTime.filmName }</p>
                    </div>
                </a>
            </li>
        </c:forEach></ul></c:otherwise></c:choose>
    </div>
</section>
<%@ include file="/WEB-INF/view/inc/footer.jsp" %>
</body>
</html>
