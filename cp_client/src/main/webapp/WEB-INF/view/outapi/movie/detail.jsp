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
<title>${movie.filmName}-天下名品电影票</title>
<script type="text/javascript">
var __showAll = false;
var $desc, $showImg;
function showAll(){
	if(__showAll){
		$desc.css('max-height', '190px');
		$desc.css('overflow', 'hidden');
		$showImg.attr('src', '<c:url value="/inc/outapi/movie/images/more.png" />');
	}
	else{
		$desc.removeAttr('style');
		$showImg.attr('src', '<c:url value="/inc/outapi/movie/images/more-up.png" />');
	}
	__showAll = !__showAll;
}
$(document).ready(function(){
	$desc = $('#movieDesc');
	$showImg = $('#showImg');
	if($desc[0].scrollHeight <= 200){
		$('#showImgBar').hide();
	}
});
</script>
</head>
<body>
<%@ include file="/WEB-INF/view/outapi/movie/header.jsp" %>
<section>
	<div class="movie">
       <div class="movie-m">
            <div class="movie-bg relative" style="background: url(${movie.hengPic}) repeat;">
            <div class="pop"></div></div>
            <div class="movieDet clearfix">
                <div class="movieDet-img fl"><img class="full-img" src="${movie.frontImg }" /></div>
                <div class="movieDet-cont fl">
                    <h2>${movie.filmName }</h2>
                    <ul>
                        <li>上映时间：${movie.firstShowDate }</li>
                        <li>类型：${movie.filmType }</li>
                        <li>导演：${movie.directors }</li>
                        <li>主演：${movie.mainActors }</li>
                    </ul>
                    <a href="<c:url value="/movie/cinema/list.action?filmNo=${movie.filmNo }"/>" class="seat-btn">选座订票</a>
                </div>
            </div>
            <div class="bar"></div>
        </div>
        <div class="movieInfo">
        	<h2 class="movieInfo-tit"><span>电影剧情</span></h2>
            <div class="movieInfo-cont">
            	<p style="max-height:190px; overflow:hidden;" id="movieDesc">${movie.filmDesc}</p>
			<div class="" id="showImgBar" style="width:22px; height:15px; margin:10px auto;"><a href="javascript:void(0);" onclick="showAll();"><img id="showImg" src="<c:url value="/inc/outapi/movie/images/more.png" />" /></a></div>
            </div>	
        </div>
    </div>
</section>
<%@ include file="/WEB-INF/view/inc/footer.jsp" %>
</body>
</html>
