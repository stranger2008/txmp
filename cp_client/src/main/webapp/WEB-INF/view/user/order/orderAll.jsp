<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE>
<html>
	<head>
		<%@ include file="/WEB-INF/view/inc/inc.jsp"%>
		<link rel="stylesheet" href="<%=basePath %>inc/css/public.css">
		<link rel="stylesheet" href="<%=basePath %>inc/css/order.css">
		<title>全部订单-APP</title>
	</head>

	<body>
		<!--头部开始-->
 		 <%@ include file="/WEB-INF/view/inc/top.jsp" %>

		<section>
			<div class="allorder">
				<!--当前位置开始-->
			 	<div class="module">
			        <section>
			            <div class="position">
			                <a href="<%=basePath %>">首页</a> > <a href="<%=basePath %>user/uccenter.action">用户中心</a> > <a href="javascript:void(0);">订单管理</a>
			            </div>
			        </section>
			    </div>
				    
				<div class="allorder-cont">
					<ul>
						<li>
							<a href="<%=basePath %>user/orderlist.action?orderType=goods" class="allorder-btn"> <span>实物订单</span> </a>
						</li>
						<li>
							<a href="<%=basePath %>user/orderlist.action?orderType=lottery" class="allorder-btn"> <span>我的彩票</span> </a>
						</li>
						<li>
							<a href="<%=basePath %>user/movieOrder.action" class="allorder-btn"> <span>我的电影票</span> </a>
						</li>
						<li>
							<a href="<%=basePath %>user/airLineOrder.action" class="allorder-btn"> <span>我的机票</span> </a>
						</li>
					</ul>
				</div>
			</div>
		</section>

		<!--底部开始-->
		<%@ include file="/WEB-INF/view/inc/footer.jsp"%>
	</body>
</html>

