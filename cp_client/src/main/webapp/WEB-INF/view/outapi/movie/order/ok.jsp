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
<title>订单提交成功-天下名品电影票</title>
<script type="text/javascript">
function reSelectSeat(){
	window.location.href = '<c:url value="/movie/cinema/seat.action?cinemaNo=${cinemaNo}&filmNo=${filmNo}&seqNo=${seqNo}" />';
}
function selectOther(){
	window.location.href = '<c:url value="/movie/cinema/movieDetail.action?cinemaNo=${cinemaNo}&filmNo=${filmNo}" />';
}
<c:if test="${not empty movie_pay_remain_seconds}">
var curPayRemainSeconds = ${movie_pay_remain_seconds};
var __interval;
function showTime(){
    var minute = Math.floor(curPayRemainSeconds / 60);
    var second = curPayRemainSeconds % 60;
    $("#mi").text(minute);
    $("#sd").text(second);
    curPayRemainSeconds--;
    if (curPayRemainSeconds <= 0){
        $('#showText').text('已超过支付时间，该订单已自动取消');
        if(__interval){
	        $('#continueSelect').show();
        	clearInterval(__interval)
        };
    }
}
$(document).ready(function(){
	showTime();
    __interval = setInterval(showTime, 1000);
});
</c:if>
</script>
</head>
<body>
<%@ include file="/WEB-INF/view/outapi/movie/header.jsp" %>
<section>
	<div class="movBg-Ok">
    	<div class="movOrd-Ok">
            <div class="movOrd-Ok-div">
                <div class="movOrd-Ok-cont margin-center">
                    <h2 class="movOrd-Ok-tit">订单提交成功！</h2>
                    <p>订单号：${movie_seat_ticket_order.orderNo }</p>
                </div>
            </div>
        </div>
        <p><a href="<%=basePath %>unionpay/unionPayOrder.action?orderNo=${movie_seat_ticket_order.orderNo}" class="movOrd-btn">去支付</a></p>
    	<div class="movOrd-Ok margin-center">
                <div class="hint">
                	<c:choose><c:when test="${not empty movie_pay_remain_seconds}">
                    <h3 class="hint-h3" id="showText">请在<span><em id="mi">0</em>分钟<em id="sd">0</em>秒</span>内完成支付</h3>
                	</c:when><c:otherwise>
                    <h3 class="hint-h3">已超过支付时间，该订单已自动取消</h3>
                	</c:otherwise></c:choose>
                    <p class="hint-p">超时订单会自动取消，如遇支付问题，请致电：<strong>400-828-8288</strong></p>
                </div>
        </div>
        <div class="tbl-type" <c:if test="${not empty movie_pay_remain_seconds}">style="display:none"</c:if> id="continueSelect">
            <div class="tbl-cell pdtr10"><input type="button" class="rere-btn" value="返回重新选择" onclick="reSelectSeat();" /></div>
            <div class="tbl-cell"><input type="button" class="other-btn" value="选择其他场次" onclick="selectOther();" /></div>
        </div>
    </div>
</section>
<%@ include file="/WEB-INF/view/inc/footer.jsp" %>
</body>
</html>
