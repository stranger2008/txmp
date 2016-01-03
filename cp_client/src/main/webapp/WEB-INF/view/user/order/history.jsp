<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
<title>用户中心</title>
</head>

<body>

    <!--头部开始-->
    <%@ include file="/WEB-INF/view/inc/top.jsp" %>
        
    	<!--当前位置开始-->
    	<div class="module">
            <section>
                <div class="position">
                    <a href="<%=basePath %>">首页</a> > <a href="<%=basePath %>user/uccenter.action">用户中心</a> > <a href="<%=basePath %>user/orderlist.action">订单管理</a>
                </div>
            </section>
        </div>
        
        
   <div class="module">
    <section>
    	<div class="cont mt5">
            <div class="order">
                <div class="order-section">
                    <div class="order-cont">
                        <div class="order-track">
                            <span>订单编号：<a href="<%=basePath %>user/order-${order.order_id}.action" style="font-size:12px;">${order.order_id}</a></span>
                            <span class="order-txt-sts">状态：${order_state_name }</span>
                        </div>
                        <div class="order-flow">
                        	<span class="order-arr-t"></span>
                            <ul class="order-track-ul">
                            
                                <c:forEach items="${orderhis}" var="item" varStatus="status">
                            	<li>
                                	<span class="icon <c:if test="${status.index==0}">on</c:if>"></span>
                                    <span>${item.action_name}</span>
                                    <span>${item.in_date}</span>
                                </li>
                                </c:forEach>
                            	
                            </ul>
                        </div>
                    </div>
                </div>

                
    
    
    
            </div>
        </div>
    </section>
</div>
        
        
    <!--搜索开始-->
    <%@ include file="/WEB-INF/view/inc/search.jsp" %>

    <!--底部开始-->
    <%@ include file="/WEB-INF/view/inc/footer.jsp" %>

</body>
</html>
