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
<title>订单提交成功-天下名品机票</title>
<script type="text/javascript">
function reSelectSeat(){
	window.location.href = '<c:url value="/airLine/searchairline.action" />';
}
function selectOther(){
	window.location.href = '<c:url value="/movie/cinema/movieDetail.action?cinemaNo=${cinemaNo}&filmNo=${filmNo}" />';
}
</script>
</head>
<body>
<%@ include file="/WEB-INF/view/outapi/movie/header.jsp" %>
<section>
	<div class="movBg-Ok">
    	<div class="movOrd-Ok">
        	
            <div class="movOrd-no-div">
                <div class="movOrd-Ok-cont margin-center">
                    <h2 class="movOrd-Ok-tit">订单提交失败！</h2>
                    <p>错误信息：${returnMessage }</p>
                </div>
            </div>
        </div>
        <div class="tbl-type">
            <div class="tbl-cell pdtr10"><input type="button" class="rere-btn" value="返回重新选择" onclick="reSelectSeat();" /></div>
        </div>
    </div>
</section>
<script>
	$("#movieImg").hide();
	$(".mov-pos").hide();
</script>
<%@ include file="/WEB-INF/view/inc/footer.jsp" %>
</body>
</html>
