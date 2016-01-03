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
<script type="text/javascript">
$(document).ready(function(){
  $("li.region").click(function(){
	  $("ul.movTheater-lst").toggle();
  });
});
</script>
<title>电影票-影院列表</title>
</head>

<body>
<%@ include file="/WEB-INF/view/outapi/movie/static/header.jsp" %>
<section>
    <div class="movTheater">
    	<h2 class="movTheater-tit">
            <ul>
                <li class="whitebg-hover"><a href="###">区域</a></li>
                <li><a href="###">距离</a></li>
            </ul>
        </h2>
		<ul>
        	<li class="region"><a href="#">朝阳区<span class="arr-b1"></span></a></li>
        	<li>
            	<ul class="movTheater-lst">
                	<li><a href="<c:url value="/movie/static/cinema/movieDetail.action" />">金逸北京双桥店<span class="fr price">最低价：<span class=" red-pri">50.00</span></span><span class="arr-b2"></span></a></li>
                	<li><a href="<c:url value="/movie/static/cinema/movieDetail.action" />">北京市紫光影城<span class="fr price">最低价：<span class=" red-pri">50.00</span></span><span class="arr-b2"></span></a></li>
                	<li><a href="<c:url value="/movie/static/cinema/movieDetail.action" />">东都国际影城<span class="fr price">最低价：<span class=" red-pri">50.00</span></span><span class="arr-b2"></span></a></li>
                	<li><a href="<c:url value="/movie/static/cinema/movieDetail.action" />">华谊兄弟影院（北京望京店）<span class="fr price">最低价：<span class=" red-pri">50.00</span></span><span class="arr-b2"></span></a></li>
                </ul>
            </li>
        	<li class="region"><a href="#">海淀区<span class="arr-b1"></span></a></li>
        	<li class="region"><a href="#">宣武区<span class="arr-b1"></span></a></li>
        	<li class="region"><a href="#">崇文区<span class="arr-b1"></span></a></li>
        	<li class="region"><a href="#">大兴区<span class="arr-b1"></span></a></li>
        	<li class="region"><a href="#">通州区<span class="arr-b1"></span></a></li>
        </ul>
    </div>
</section>
<%@ include file="/WEB-INF/view/inc/footer.jsp" %>      
</body>
</html>
