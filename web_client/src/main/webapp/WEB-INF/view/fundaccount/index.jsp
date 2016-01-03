<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>我的资金账户</title>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="<%=rootPath %>css/public.css" />
<link rel="stylesheet" href="<%=rootPath %>css/product.css" />
<link rel="stylesheet" href="<%=rootPath %>css/account.css" />
<script src="<%=rootPath %>js/jquery.min.js" type="text/javascript"></script>
<script src="<%=rootPath %>js/core.js"></script>
<script src="<%=rootPath %>js/account.js"></script>
<script language="javaScript"> 
$(document).ready(function () {
	now = new Date();
	hour = now.getHours();
	if(hour < 6){$("#category_time").append("凌晨");} 
	else if (hour < 9){$("#category_time").append("早上");} 
	else if (hour < 12){$("#category_time").append("上午");} 
	else if (hour < 14){$("#category_time").append("中午");} 
	else if (hour < 17){$("#category_time").append("下午");} 
	else if (hour < 19){$("#category_time").append("傍晚");} 
	else if (hour < 22){$("#category_time").append("晚上");} 
	else {$("#category_time").append("夜里");} 
});
</script>
</head>
  
<body>
<!--头部开始-->
<%@ include file="/WEB-INF/view/inc/top.jsp" %>
<!--头部下搜索框-->
<%@ include file="/WEB-INF/view/user/inc/top.jsp" %>		 
<!--nav开始-->
<%@ include file="/WEB-INF/view/user/inc/nav.jsp" %>	

<!--个人中心-->
<div class="w1200">
	<div class="usercenter">
		<div class="position">
			<a href="#"><strong>我的资金账户</strong></a><span></span><a href="#">账户总览</a>
		</div>
        <!--会员中心左边导航-->
		<%@ include file="/WEB-INF/view/user/inc/left.jsp" %>
        <div class="user-info fr">
        	<div class="user-info fr">
	        	<h2 class="user-hello"><a href="#"></a>${sessionScope.session_user_name}，<span id="category_time"></span>好！</h2>
	        	<div class="safe-serv">
                    <div class="form-psd btm-dh2">
						<p class="acc-p">欢迎来到您的‘资金账户’！<a href="#" class="openbtn">开通</a></p>
                    </div>
                    <div class="form-psd btm-dh2">
                    	<ul class="funds-lst">
                    		<li><span>总金额：</span><em>¥${fundaccount.fund_num }</em></li>
                        	<li><span>可用余额：</span><em>¥${fundaccount.use_num }</em></li>
                        	<li><span>冻结余额：</span><em>¥${fundaccount.freeze_num }</em></li>
                        	<li><span>账户状态：</span><em><c:if test="${fundaccount.enabled == '0' }">有效</c:if><c:if test="${fundaccount.enabled == '1' }">无效</c:if></em></li>
                        </ul>    
                    </div>
                    <div class="account-voucher-effect">
                        <strong style="color: #666;">您现在可以</strong>
                        <ul style="padding-left: 25px">
                            <li>
                                <em class="buy-voucher"></em>
                                购买积分
                            </li>
                            <li>
                                <em class="payment-order"></em>
                                支付订单
                            </li>
                            <li>
                                <em class="turn-money"></em>
                                转余额
                            </li>
                            <li>
                                <em class="turn-voucher"></em>
                                转代金券
                            </li>
                            <li>
                                <em class="turn-lntegral"></em>
                                积分转增
                            </li>
                        </ul>
                	</div>
           		 </div>
	        </div>
        </div>
	</div>
</div>

<div class="clearfix"></div>
<!--底部-->
<%@ include file="/WEB-INF/view/inc/footer.jsp" %>
</body>
</html>
