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
		<title>填写银行卡号</title>
	</head>
	<body>
	<%@ include file="/WEB-INF/view/inc/top.jsp" %>
		<section>
		<div class="pay">
			<div class="progress">
				<ul class="clearfix">
					<li class="step-h">
						1 填写银行卡号
						<b>></b>
					</li>
					<li class="step">
						2 填写其他验证信息
						<b>></b>
					</li>
					<li class="step">
						3 支付成功
					</li>
				</ul>
			</div>
			<form action="<%=basePath %>nocardpay/cardInfo.action" id="cardInfo">
				<div class="payok">
					<ul class="payok-ul">
						<li>
							<span>银行卡类型：</span>
							<input type="radio" name="card" checked="checked" value="1">
							<label class="mr">
								信用卡
							</label>
							<input type="radio" name="card" value="2">
							<label class="mr">
								借记卡
							</label>
						</li>
						<li>
							<span>银行卡号：</span>
							<input type="text" class="card-txt" name="cardNo" id="cardNo" onkeyup="check()">
							<input type="hidden" name ="orderNo" value="${orderNo}"/>
						</li>
						<li>
							<span></span>
							<input type="button" class="next-btn" value="下一步" onclick="javascript:cardInfo();"/>
						</li>
					</ul>
				</div>
			</form>
		</div>
		</section>
		<script type="text/javascript">
			
			function check(e){
				var e=e? e:window.event;
				var tarobj=event.srcElement? event.srcElement:event.target;
				if(!(event.keyCode>=48&&event.keyCode<=57)){
					tarobj.value=tarobj.value.replace(/[^\d]/g,'');
				}
			}
		
			function cardInfo(){
				var cardNo =$("#cardNo");
				var radVal=$("input[type='radio']:checked").val();
				if(cardNo.val().length<1){
					cardNo.focus();
					alert("请输入卡号!");
					return false;
				}
				$("#cardInfo").submit();
			}
		
		</script>
	<%@ include file="/WEB-INF/view/inc/footer.jsp" %>
	</body>
</html>
