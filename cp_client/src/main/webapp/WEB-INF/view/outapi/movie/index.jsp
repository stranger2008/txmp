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
<title>热映影片-天下名品电影票</title>
</head>
<body>
<%@ include file="/WEB-INF/view/outapi/movie/header.jsp" %>
<section>
    <div class="banner margin-center">
        <article>
          <div class="scroll relative" style="top:-1px;">
            <div class="scroll_box" id="scroll_img">
                <ul class="scroll_wrap">
                  <li><a href="###"><img src="<c:url value="/inc/outapi/movie/images/mov-banner01.jpg" />" width="100%" /></a></li>
                  <li><a href="###"><img src="<c:url value="/inc/outapi/movie/images/mov-banner01.jpg" />" width="100%" /></a></li>
                  <li><a href="###"><img src="<c:url value="/inc/outapi/movie/images/mov-banner01.jpg" />" width="100%" /></a></li>
                  <li><a href="###"><img src="<c:url value="/inc/outapi/movie/images/mov-banner01.jpg" />" width="100%" /></a></li>
                </ul>
            </div>
            <ul class="scroll_position" style="left:48%;" id='scroll_position'>
              <li class="on"><a href="javascript:void(0);">1</a></li>
              <li><a href="javascript:void(0);">2</a></li>
              <li><a href="javascript:void(0);">3</a></li>
              <li><a href="javascript:void(0);">4</a></li>
            </ul>
          </div>
        </article>
		<script type="text/javascript" src="<c:url value="/inc/outapi/movie/js/hhSwipe.js" />"></script>
    </div>
    <div class="hotShow margin-center">
    	<h2 class="hotShow-tit" style="margin-top:-1px;">
            <ul>
                <li class="white-hover"><a href="javascript:void(0);">正在热映</a></li>
                <li><a href="<c:url value="/movie/cinema/theaterList.action" />">影院列表</a></li>
            </ul>
        </h2>
        <div class="clearfix"></div>
        <c:choose><c:when test="${not empty errMsg}"><p class="no-mov">${errMsg }</p></c:when>
        <c:otherwise><ul class="hotShow-lst clearfix"><c:forEach items="${hotMovieList }" var="hotMovie" >
            <li>
                <a href="<c:url value="/movie/detail.action?filmNo=${hotMovie.filmNo }" />">
                    <span class="hotShow-img"><img src="${hotMovie.frontImg }"></span>
                    <div class="img-box">
                        <p>${hotMovie.filmName }</p>
                    </div>
                </a>
            </li>
        </c:forEach></ul></c:otherwise></c:choose>
    </div>
</section>
<%@ include file="/WEB-INF/view/inc/footer.jsp" %>
</body>
</html>
