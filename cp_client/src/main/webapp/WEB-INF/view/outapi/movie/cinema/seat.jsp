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
<title>选择座位-天下名品电影票</title>
<script type="text/javascript">
var __selectedSeat = [];
var __hasSubmited = false;
var $seatList, $seatSelectedMoney, $seatSelectedInfo, $form;
$(document).ready(function(){
	$seatList = $('#seatList');
	$seatSelectedMoney = $('#seatSelectedMoney');
	$seatSelectedInfo = $('#seatSelectedInfo');
	$form = $('#seatOrderForm');
});
function clkSeat(seatId){
	var i = $.inArray(seatId, __selectedSeat);
	if(i == -1){
		if(__selectedSeat.length == ${maxSelectedSeat}){
			window.alert('最多选择${maxSelectedSeat}个座位！');
			return;
		}
		__selectedSeat.push(seatId);
		seatSelectInfo(seatId, false);
	}
	else{
		__selectedSeat.splice(i, 1);
		seatSelectInfo(seatId, true);
	}
	$seatSelectedMoney.text('￥'+ (__selectedSeat.length*${cinemaMovieShowTime.salePrice}) );
}
function seatSelectInfo(seatId, isRemove){
	var seat = toSeatMap(seatId);
	var $seat = $('#' + seatId, $seatList);
	if(isRemove){
		$seat.removeClass('selected');
		if(seat.type != '1'){
			$seat.addClass('love');
		}
		$('#info_' + seatId, $seatSelectedInfo).remove();
	}
	else{
		if(seat.type != '1'){
			$seat.removeClass('love');
		}
		$seat.addClass('selected');
		var html = '<li id="info_' + seatId + '">';
		html += (seat.imgRowNo + '排' + seat.colNo + '号</li>');
		$(html).appendTo($seatSelectedInfo);
	}
}
function submitSeatOrder(){
	if(__hasSubmited){
		return;
	}
	
	var len = __selectedSeat.length;
	if(len < 1){
		window.alert('请选择座位！');
		return;
	}
	var seats = '';
	$.each(__selectedSeat, function(i, item){
		if(i > 0){
			seats += '|';
		}
		var seat = toSeatMap(item);
		seats += (seat.locNo + '_' + seat.rowNo + '_' + seat.colNo);
	});
	$('#seats', $form).val(seats);
	$('#amount', $form).val(len);
	__hasSubmited = true;
	$form.submit();
}
function toSeatMap(seatId){
	var seat = {};
	var s = seatId.split("_");
	seat.locNo = ''+s[0];
	seat.rowNo = ''+s[1];
	seat.imgRowNo = ''+s[2];
	seat.colNo = ''+s[3];
	//seat.status= ''+s[4]; 
	seat.type= ''+s[4]; 
	return seat;
}
</script>
</head>
<body>
<%@ include file="/WEB-INF/view/outapi/movie/header.jsp" %>
	
<section>
    <div class="movieSeat margin-center">
        <h2>${cinema.cinemaName }</h2>
        <div class="seatSel">
        	<div class="mv-info clearfix">
                <div class="fl mv-tit">
                    <h3>${movie.filmName }</h3>
                    <fmt:parseDate value="${cinemaMovieShowTime.showDate}" var="date" pattern="yyyy-MM-dd" />
                    <p>${cinemaMovieShowTime.showDate}(<fmt:formatDate value="${date}" pattern="E"/>)${cinemaMovieShowTime.showTime}</p>
                </div>
               	<a href="javascript:void(0);" class="fr movsub-btn" onclick="submitSeatOrder();">提交订单</a>
               	<form name="seatOrderForm" id="seatOrderForm" action="<c:url value="/movie/order/save.action" />" method="post">
               		<input type="hidden" name="cinemaNo" value="${cinemaNo}" />
               		<input type="hidden" name="filmNo" value="${filmNo}" />
               		<input type="hidden" name="seqNo" value="${cinemaMovieShowTime.seqNo}" />
               		<input type="hidden" name="hallNo" value="${cinemaMovieShowTime.hallNo}" />
               		<input type="hidden" id="seats" name="seats" value="" />
               		<input type="hidden" id="amount" name="amount" value="0" />
               	</form>
            </div>
        	<div class="cf">
                <p class="cf-p"><span>一次最多选择4个座位</span><span class="fr price" id="seatSelectedMoney">￥0</span></p>
                <ul class="cf-ul clearfix" id="seatSelectedInfo">
                </ul>
            </div>
            <div class="seat-tip">
            	<span class="seat active"></span>
            	<span class="st-nm">可选</span>
            	<span class="seat selected"></span>
            	<span class="st-nm">已选</span>
            	<span class="seat disabled"></span>
            	<span class="st-nm">不可选</span>
            	<span class="seat love"></span>
            	<span class="st-nm">情侣座</span>
            </div>
            <div class="seat-info">
                <h4>${cinemaMovieShowTime.hallName}银幕</h4>
                
                <div class="seat-tab seat-overflow">
					<p style="text-align: center;">银幕中央</p>
                <div class="seat-cont" style=" " id="seatList">
            <c:choose><c:when test="${not empty errMsg}">${errMsg}</c:when><c:otherwise>
       		<c:forEach items="${rowSeatList}" var="rs" varStatus="loop" ><p><span class="num">${rs.imgRow}</span>
        	<c:forEach items="${rs.seatList}" var="s"><c:choose>
	        	<c:when test="${s.type == '3' or s.colNo == 'ZL'}"><a href="javascript:void(0);" class="seat seat-none"></a></c:when>
    	    	<c:when test="${s.status == '1' or s.status == '2'}"><a href="javascript:void(0);" class="seat"></a></c:when>
        		<c:when test="${s.status == '0'}">
	        		<c:choose><c:when test="${s.type=='1'}"><a href="javascript:void(0);" id="${s.locNo}_${rs.rowNo}_${rs.imgRow}_${s.colNo}_${s.type}" onclick="clkSeat(this.id);" class="seat active"></a></c:when>
        			<c:otherwise><a href="javascript:void(0);" id="${s.locNo}_${rs.rowNo}_${rs.imgRow}_${s.colNo}_${s.type}" onclick="clkSeat(this.id);" class="seat love"></a></c:otherwise></c:choose>
        	</c:when></c:choose></c:forEach></p></c:forEach></c:otherwise></c:choose>
                </div>
                </div>
            </div>
        </div>
    </div>
</section>
<%@ include file="/WEB-INF/view/inc/footer.jsp" %>
</body>
</html>
