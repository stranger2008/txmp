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
		        <div class="order-msg">
		            <div style="text-align:center;">
		               <span class="order-no-icon"></span><span style="color: #E4393C; font-size: 18px; line-height: 24px; font-family: 微软雅黑">您的订单存在安全风险，请查看订单状态或稍后重试。</span><a href="<%=basePath %>user/orderlist.action">返回我的订单</a>
		            </div>
		        </div>
		    </div>
		</div>

		 <!--底部开始-->
    	<%@ include file="/WEB-INF/view/inc/footer.jsp" %>

	</body>
</html>

