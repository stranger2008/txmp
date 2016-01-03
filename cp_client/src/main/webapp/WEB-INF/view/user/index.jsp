<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
<link rel="stylesheet" href="<%=basePath %>inc/css/order.css">
<title>用户中心</title>
</head>

<body>

<div class="w480">
    
    <!--头部开始-->
    <%@ include file="/WEB-INF/view/inc/top.jsp" %>
        
    <!--当前位置开始-->
	<div class="module">
	    <section>
	        <div class="position mt5">
	            <a href="<%=basePath %>">首页</a> > <a href="javascript:void(0);">用户中心</a>
	        </div>
	    </section>
	</div>
	
	<!--内容开始-->    
	<div class="content">
	   <div class="module">
        <section>
        <div class="user">
            <h3>订单中心</h3>
            <div class="ordercenter">
           <ul>
                <li><a href="<%=basePath %>user/userOrderPay.action" class="allorder-btn"><span>待付款订单</span></a></li>
                <li><a href="<%=basePath %>user/userOrderType.action" class="allorder-btn"><span>全部订单</span></a></li>
            </ul>
            </div>
            <h3>账户中心</h3>
            <div class="accountcenter">
            <ul>
                <li><a href="<%=basePath %>user/collectgoods.action" class="allorder-btn"><span>我的收藏</span></a></li>
                <li><a href="<%=basePath %>user/addrlist.action" class="allorder-btn"><span>收货地址</span></a></li>
                <li><a href="<%=basePath %>user/goodsReturn_list.action" class="allorder-btn"><span>退换货</span></a></li>
                <li><a href="<%=basePath %>user/goodsasklist.action" class="allorder-btn"><span>消息中心（${reContCount}）</span></a></li>
                <li><a href="<%=basePath %>user/updatePassword.action" class="allorder-btn"><span>账户安全</span></a></li>
            </ul>
            </div>
        </div>
        </section>

    </div>
	    
	</div>
    
    
    <!--搜索开始-->
    <%@ include file="/WEB-INF/view/inc/search.jsp" %>

    <!--底部开始-->
    <%@ include file="/WEB-INF/view/inc/footer.jsp" %>
</div>

</body>
</html>
