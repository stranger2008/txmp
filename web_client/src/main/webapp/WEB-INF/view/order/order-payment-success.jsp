<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
		<link rel="stylesheet" href="<%=basePath %>inc/css/public.css">
		<link rel="stylesheet" href="<%=basePath %>inc/css/sign.css">
		<link rel="stylesheet" href="<%=basePath %>inc/css/product.css">
		<title>订单成功付款</title>
		<script src="<%=basePath %>inc/js/core.js"></script>
	</head>
	<body>
		<!--头部开始-->
	    <%@ include file="/WEB-INF/view/inc/top.jsp" %>
		<!--购物车-->
		<div class="w1200">
		    <div class="logo-sign fl">
		        <a href="<%=basePath %>"><img src="<%=basePath %>inc/images/logo-sign.png" /></a>
		    </div>
		    <div class="progress fr">
		    	<ul class="progress3">                    
		        	<li class="step">1 选择支付方式</li>
		        	<li class="step">2 核对支付信息</li>
		        	<li class="step-h">3 支付结果</li>
		        </ul>
		    </div>
		</div>
		<div class="clearfix"></div>
		
		<!--订单提交成功-->
		<div class="w1200">
		    <div class="order-ok">
		        <span class="order-o-icon"></span>
		        <div class="order-msg">
		            <div style="color: #5d5d5d;line-height: 24px;;">
		                您已付款成功，正在为您处理订单。<br/>
		                如遇问题请拨打天下名品客服电话：400-123-5555；010-87653255<br/>
		                服务时间：周一至周日 0:00-24:00
		                <p style="margin-top: 50px">
		                    您现在可以：<a class="go-trade" href="<%=basePath %>index.action"><span></span>继续购物</a><a class="go-trade" href="<%=basePath %>user/order-${order_id }.action"><span></span>查看订单状态</a>
		                </p>
		            </div>
		        </div>
		    </div>
		</div>


		 <!--底部开始-->
    	<%@ include file="/WEB-INF/view/inc/footer.jsp" %>

	</body>
</html>

