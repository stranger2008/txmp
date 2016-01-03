<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
		<link rel="stylesheet" href="<%=basePath %>inc/css/public.css">
		<link rel="stylesheet" href="<%=basePath %>inc/css/sign.css">
		<link rel="stylesheet" href="<%=basePath %>inc/css/product.css">
		<title>订单付款</title>
		<script src="<%=basePath %>inc/js/core.js"></script>
		<script type="text/javascript">
			function pay(){
				//if(document.getElementById('times').value!=0){ return;}
				if($('#times').val()!=0){
					return;
				}
				$('#times').val('1');
				//document.getElementById('sub_btn').style.background='#666666';
				$("#sub_btn").css("background",'#666666')
				//document.getElementById('paymentForm').submit();
				var url = "<%=basePath %>user/topaymentorder.action?order_id=${paymentOrder.order_id}&total_price=${paymentOrder.total_price}&payment_way=" + $("input[name='payment_way']:checked").val(); ;
				window.open(url);
			}
		</script>
	</head>
	<body>
		<!--头部开始-->
	    <%@ include file="/WEB-INF/view/inc/top.jsp" %>
		<!--购物车-->
		<div class="w1200">
			<div class="logo-sign fl">
				<a href="<%=basePath %>"><img src="<%=basePath %>inc/images/logo-sign.png">
				</a>
			</div>
			<div class="progress fr">
				<ul class="progress2">
					<li class="step">
						1 选择支付方式
					</li>
					<li class="step-h">
						2 核对支付信息
					</li>
					<li class="step">
						3 支付结果
					</li>
				</ul>
			</div>
		</div>
		<div class="clearfix"></div>

		<!--订单提交成功-->
		<div class="w1200">
			<div class="order-ok">
				<span class="order-o-icon"></span>
				<div class="order-msg">
					<sf:form method="post" action="user/topaymentorder.action" id="paymentForm" modelAttribute="paymentOrder">
					<h1>
						订单提交成功，请您尽快付款！
					</h1>
					<p class="order-line">
						订单号：
						<span style="color: #005a9d; padding-right: 30px">${paymentOrder.order_id}</span>应付金额
						<strong style="padding-left: 20px; color: #e10000; font-size: 16px">${paymentOrder.total_price}元</strong>
					</p>
					<p style="color: #5d5d5d">
						请您在24小时内付清款项，否则订单会被自动取消。
						<a style="color: #005a9d; font-size: 12px" href="<%=basePath %>user/order-${paymentOrder.order_id }.action">[查看订单详细]</a>	
					</p>
					<p class="order-line">
						<sf:hidden path="order_id" />
						<sf:hidden path="total_price" />
						<sf:radiobutton path="payment_way" value="0" />银联支付
						<sf:radiobutton path="payment_way" value="1" />资金账户
						<input type="hidden" name="times" id="times" value="0" />
					</p>
					<p style="color: #5d5d5d">
						<sf:errors path="order_id" cssClass="error"/>
						<sf:errors path="total_price" cssClass="error"/>
						<sf:errors path="payment_way" cssClass="error"/>
						<sf:errors path="respInfo" cssClass="error"/>
					</p>
					<p style="color: #5d5d5d">
						<div id="sub_btn" class="total-btn fr">
							<a href="javascript:void(0);" onclick="pay()">立即支付</a>
						</div>
					</p>
					</sf:form>
				</div>
			</div>
			<div class="order-payment-ok">
				支付完成后您可以在：
				<a>查看订单</a>|
				<a>我的个人中心</a>中查看订单信息
			</div>
		</div>

		 <!--底部开始-->
    	<%@ include file="/WEB-INF/view/inc/footer.jsp" %>

	</body>
</html>

