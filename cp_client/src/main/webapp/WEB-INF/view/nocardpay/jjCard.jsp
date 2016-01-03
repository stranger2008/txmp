<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
		<title>填写其他验证信息2</title>
	</head>
	<body>&nbsp; 
	<%@ include file="/WEB-INF/view/inc/top.jsp" %>
		<section>
		<div class="pay">
			<div class="progress">
				<ul class="clearfix">
					<li class="step-h">
						1 填写银行卡号
						<b>></b>
					</li>
					<li class="step-h">
						2 填写其他验证信息
						<b>></b>
					</li>
					<li class="step">
						3 支付成功
					</li>
				</ul>
			</div>
			<form action="<%=basePath %>nocardpay/payOrder.action" id="cardInfo" method="post">
				<div class="payok">
					<ul class="payok-ul">
						<li>
							<span>手机号码：</span>
							<label>
								<input type="text" class="pc-txt" name="phone" id="xykPhone" onkeyup="check()">
							</label>
						</li>
						<li>
							<span>持卡人身份证号：</span>
							<input type="text" class="pc-txt"  name="sfzh" id="jjkSfzh" onkeyup="check()">
						</li>
						<li>
							<span>一卡通支付密码：</span>
							<input type="password" class="psd-txt" name="despin"     id="jjkCardPwd" 	onkeyup="check()">
							<input type ="hidden" 				   name="orderNo" 	 id="orderNo"     	value="${orderNo}"/>
							<input type ="hidden" 				   name="trk" 	 	 id="trk" 	  	  	value="${cardNo}"/>
							<input type ="hidden" 				   name="cartType" 	 id="cartType" 	  	value="${cartType}"/>
						</li>
						<li>
							<span></span>
							<input type="button" class="next-btn" value="确认支付"  onclick="javascript:orderPay();"/>
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
			
			function orderPay(){
				 var xykPhone =$("#xykPhone"),
				 	 jjkSfzh=$("#jjkSfzh"),
				 	 jjkCardPwd=$("#jjkCardPwd");
				 	 
				 if(xykPhone.val().length<1){
					alert("请输入手机号码");
					xykPhone.focus();
					return false;
				 }
				 
				 if(jjkSfzh.val().length<1){
					alert("请输入持卡人身份证号");
					jjkSfzh.focus();
					return false;
				 }
				 
				 if(jjkCardPwd.val().length<1){
					alert("请输入密码");
					jjkCardPwd.focus();
					return false;
				 }
				 $("#cardInfo").submit();
			}
			
		</script>
    <!--底部开始-->
    <%@ include file="/WEB-INF/view/inc/footer.jsp" %>
	</body>
</html>

