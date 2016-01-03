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
</head>
<body>
<%@ include file="/WEB-INF/view/outapi/movie/header.jsp" %>
<section>
	<div class="movBg-Ok">
    	<div class="movOrd-Ok">
            <div class="movOrd-Ok-div">
                <div class="movOrd-Ok-cont margin-center">
                    <h2 class="movOrd-Ok-tit">订单提交成功！</h2>
                    <p>订单金额：￥${toltalMoney }</p>
                    <p>订单号：${orderId}</p>
                    
                </div>
            </div>
        </div>
        <p><a href="<%=basePath %>unionpay/unionPayOrder.action?orderNo=${orderId}" class="movOrd-btn">去支付</a></p>
    </div>
</section>
<%@ include file="/WEB-INF/view/inc/footer.jsp" %>
<script>
	$("#movieImg").hide();
	$(".mov-pos").hide();
</script>
</body>
</html>
