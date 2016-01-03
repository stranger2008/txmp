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
<title>电影票</title>
</head>
<body>
<%@ include file="/WEB-INF/view/outapi/movie/static/header.jsp" %>
<section>
    <!--banner开始-->
    <div class="banner margin-center">
        <article>
          <!--scroll-->
          <div class="scroll relative mt10">
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
          <!--scroll-->
        </article>
        <script src='<c:url value="/inc/outapi/movie/js/hhSwipe.js" />'></script>
    </div>
    <div class="hotShow margin-center mt10">
    	<h2 class="hotShow-tit">
            <ul>
                <li class="white-hover"><a href="###">正在热映</a></li>
                <li><a href="###">影院列表</a></li>
            </ul>
        </h2>
        <br />
        <ul class="hotShow-lst clearfix">
            <li>
                <a href="<c:url value="/movie/static/detail.action" />">
                    <span class="hotShow-img"><img src="<c:url value="/inc/outapi/movie/images/hotmov-pic01.jpg" />"></span>
                    <div class="img-box">
                        <p>电影名称</p>
                    </div>
                </a>
            </li>
            <li>
                <a href="<c:url value="/movie/static/detail.action" />">
                    <span class="hotShow-img"><img src="<c:url value="/inc/outapi/movie/images/hotmov-pic02.jpg" />"></span>
                    <div class="img-box">
                        <p>电影名称</p>
                    </div>
                </a>
            </li>
            <li>
                <a href="<c:url value="/movie/static/detail.action" />">
                    <span class="hotShow-img"><img src="<c:url value="/inc/outapi/movie/images/hotmov-pic03.jpg" />"></span>
                    <div class="img-box">
                        <p>电影名称</p>
                    </div>
                </a>
            </li>
            <li>
                <a href="<c:url value="/movie/static/detail.action" />">
                    <span class="hotShow-img"><img src="<c:url value="/inc/outapi/movie/images/hotmov-pic04.jpg" />"></span>
                    <div class="img-box">
                        <p>电影名称</p>
                    </div>
                </a>
            </li>
            <li>
                <a href="<c:url value="/movie/static/detail.action" />">
                    <span class="hotShow-img"><img src="<c:url value="/inc/outapi/movie/images/hotmov-pic05.jpg" />"></span>
                    <div class="img-box">
                        <p>电影名称</p>
                    </div>
                </a>
            </li>
            <li>
                <a href="<c:url value="/movie/static/detail.action" />">
                    <span class="hotShow-img"><img src="<c:url value="/inc/outapi/movie/images/hotmov-pic06.jpg" />"></span>
                    <div class="img-box">
                        <p>电影名称</p>
                    </div>
                </a>
            </li>
        </ul>
    </div>
</section>
<%@ include file="/WEB-INF/view/inc/footer.jsp" %>
</body>
</html>
