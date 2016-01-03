<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<%@ include file="/WEB-INF/view/inc/inc.jsp"%>
		<title>关于我们介绍</title>
	</head>
	<body>
		<div class="w480">
			<!--头部开始-->
			<%@ include file="/WEB-INF/view/inc/top.jsp"%>
			<!--搜索开始-->
			<%@ include file="/WEB-INF/view/inc/search.jsp"%>
			<div class="content">
				<!--当前位置开始-->
				<div class="module">
					<section>
						<div class="position">
							<a href="<%=basePath%>">首页</a> >
							<a href="javascript:void(0)">订单提交</a>
						</div>
					</section>
				</div>
				<!--内容开始-->
				<div class="module">
					<section class="box_book">
						<h1>
							订单提交成功
						</h1>
						<c:forEach items="${sessionScope.orderIdList}" var="orderId">
	                       ${orderId}--<a href="<%=basePath%>nocardpay/toPayPage.action?orderNo=${orderId}">无卡支付</a><br/>
	                       ${orderId}--<a href="<%=basePath %>unionpay/unionPayOrder.action?orderNo=${orderId}">银联付款</a><br/>
						</c:forEach>
					</section>
				</div>
			</div>
			<!--底部开始-->
			<%@ include file="/WEB-INF/view/inc/footer.jsp"%>
		</div>
	</body>
</html>
