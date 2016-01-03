<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="Content-type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, target-densitydpi=high-dpi, maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<link rel="stylesheet" href="<%=basePath%>inc/outapi/nocardpay/css/pay.css">
		<title>支付成功</title>
	</head>
	<body>
		<%@ include file="/WEB-INF/view/inc/top.jsp" %>
		<section>
		<div class="pay">
			<div class="progress">
				<ul class="clearfix">
					<li class="step">
						1 填写银行卡号
						<b>></b>
					</li>
					<li class="step">
						2 填写其他验证信息
						<b>></b>
					</li>
					<li class="step-h">
						3 支付成功
					</li>
				</ul>
			</div>
			<div class="payok">
				<span class="pay-o-icon"></span>
				<div class="pay-msg">
					<p class="payok-p">
						您已成功支付188.00元
						${payRestult }
					</p>
					<p>
						为方便您查看商户订单状态，请点击
						<input type="button" class="payok-ipt" value="返回商户"  />
					</p>
				</div>
			</div>
		</div>
		</section>
			<%@ include file="/WEB-INF/view/inc/footer.jsp" %>
	</body>
</html>

