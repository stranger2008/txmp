<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<title>商家桌面</title>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
</head>
<body>
<div class="shop">
	<div class="shop-info clearfix">
		<a href="#" class="shop-img fl"><img src="${shopconfig.shop_logo }"></a>
		<div class="shop-ul fl">
			<p><span>店铺名称：<em>${shopconfig.shop_name }</em></span>&nbsp;&nbsp;<span>等级：<em>${sessionScope.session_cust_level_name }</em></span></p>
			<p><span>企业名称：<em>${shopconfig.cust_name }</em></span></p>
			<a class="gotobtn" href="###">进入店铺</a>                    
		</div>
	</div>
	<div class="todo">
		<h2>待办事项</h2>
		<p class="todo-p">
			<strong>商品：</strong>
			<c:if test="${empty goodsCounts }">
				无事项
			</c:if>
			<c:forEach items="${goodsCounts }" var="item">
				<a href="<%=basePath %>goods/list.action?info_state=${item.info_state }&sear_days=''">
					<c:if test="${item.info_state == '0' }">待审核</c:if>
					<c:if test="${item.info_state == '2' }">审核未通过</c:if>
					<em>(${item.cnt })</em>
				</a>
				&nbsp;&nbsp;
			</c:forEach>
		</p>
		<p class="todo-p">
			<strong>订单：</strong>
			<c:if test="${empty orderCounts }">
				无事项
			</c:if>
			<c:forEach items="${orderCounts }" var="item">
				<a href="<%=basePath %>order/list.action?order_state=${item.order_state }&sear_days=30">
					待发货
					<em>(${item.cnt })</em>
				</a>
			</c:forEach>
			<c:forEach items="${goodsreturnCounts }" var="item">
				<a href="<%=basePath %>goodsreturn/list.action?info_state=${item.info_state }&sear_days=">
					退货待审核
					<em>(${item.ct })</em>
				</a>
			</c:forEach>
		</p>
	</div>
	<div class="notice-sys">
		<h2>系统公告</h2>
		<ul>
			<li><a href="javascript:void(0);" class="fl">在哪里可以查看到天下名品的促销活动？</a><span class="date fr">2014.09.27</span></li>
			<li><a href="javascript:void(0);" class="fl">页面显示有货的商品，为什么下单之后会显示无货呢？</a><span class="date fr">2014.09.27</span></li>
			<li><a href="javascript:void(0);" class="fl">商品的规格是什么？性能怎样？</a><span class="date fr">2014.09.27</span></li>
			<li><a href="javascript:void(0);" class="fl">订单提交成功后商品降价了，可以退给我差价吗？</a><span class="date fr">2014.09.27</span></li>
			<li><a href="javascript:void(0);" class="fl">为什么商品没有签收而订单中快递单号查询显示已签收的状态？</a><span class="date fr">2014.09.27</span></li>
		</ul>
	</div>
</div>
</body>
</html>
