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
                <a href="<%=basePath %>">首页</a> > <a href="<%=basePath %>user/uccenter.action">用户中心</a> > <a href="javascript:void(0);">订单管理</a>
            </div>
        </section>
    </div>
    
    <form action="user/orderlist.action">
    
    <div class="module">
    <section>
    	<div class="mt5">
    	
    		<c:if test="${empty pageResult.list}">
    			<div style="padding-top:10px;">无订单信息</div>
    		</c:if>
    	
    	<c:if test="${!empty pageResult.list}">
    		
            <div class="order">
            	<!-- 
                <div class="order-tab tbl-type">
                	<a href="#" class="tbl-cell on">近一个月订单</a>
                	<a href="#" class="tbl-cell on-l">一个月前订单</a>
                </div>${status.index}
                 -->
                 
                 <c:forEach items="${pageResult.list}" var="item" varStatus="status">
                <div class="order-section <c:if test="${(status.index+1)%2==0}">gray_bg</c:if>">
                    <div class="order-tit">
                    	<span class="order-txt">订单状态：</span>
                    	<span class="order-txt-sts">
                    		${item.order_state_name}
                    	</span>
                    </div>
                    <div class="order-cont">
                        <ul class="order-cont-ul">
                            <li>
                            <a href="<%=basePath %>user/order-${item.order_id}.action" class="order-cont-ula">
                            	<c:if test="${orderType!='movie'}">
                                <span class="order-cont-ulimg">
                                		<c:choose>
                                		<c:when test="${item.order_type=='lottery_6'}">
                                				<img src="<%=basePath %>inc/outapi/lottery/images/3D.jpg" />
                                		</c:when>
                                		<c:when test="${item.order_type=='lottery_3'}">
                                				<img src="<%=basePath %>inc/outapi/lottery/images/double.jpg" />
                                		</c:when>
                                		<c:when test="${item.order_type=='lottery_4'}">
                                				<img src="<%=basePath %>inc/outapi/lottery/images/7color.jpg" />
                                		</c:when>
                                		<c:when test="${item.order_type=='lottery_1'}">
                                				<img src="<%=basePath %>inc/outapi/lottery/images/happy8.jpg" />
                                		</c:when>
                                		<c:when test="${item.order_type=='lottery_2'}">
                                				<img src="<%=basePath %>inc/outapi/lottery/images/pk10.jpg" />
                                		</c:when>
                                		<c:otherwise>
                                			<c:forEach items="${item.orderdetails}" var="orderdetail">
									 			<c:if test="${!empty orderdetail.img_path}">
				                        			<img src="<h:imgSubstr imgpath="${orderdetail.img_path}"/>" onload="DrawImage(this,90,90)"/>
			                        			</c:if>
										 	</c:forEach>
		                        		</c:otherwise>
		                        		</c:choose>
                                </span>
                            	</c:if>
                                <span class="order-cont-ulcw">
                                	<span>订单编号：${item.order_id}</span>
                                    <span>订单金额：¥${item.tatal_amount}</span>
                                    <span>下单时间：${item.order_time}</span>
                                </span>
                                <span class="arr"></span>
                            </a>
                            <p class="btn-area">
                            	<span class="tbl-type mg-t5">
                                    <span class="tbl-cell w50"><a href="<%=basePath %>user/orderhis-${item.order_id}.action" class="abtn-type1">订单跟踪</a></span>
                                    <span class="tbl-cell w50">
                                    	<c:if test="${item.order_state==2}">
									 		<a href="<%=basePath %>user/confirmReceipt-${item.order_id}.action" class="abtn-type2">确认收货</a>
									 	</c:if>
									 	<c:if test="${item.order_state==0}">
									 		<a href="<%=basePath %>unionpay/unionPayOrder.action?orderNo=${item.order_id}" class="abtn-type2">付款</a>
									 	</c:if>
                                    </span>
                                </span>
                            </p>
                            </li>
                        </ul>
                    </div>
                </div>
                
                </c:forEach>
                <input type="hidden" name="orderType" value="${orderType }"/>
                <div class="add_more margin-center">${pageBar}</div>

            </div>
            
            </c:if>
            
        </div>
    </section>
</div>

</form>
        
    <%@ include file="/WEB-INF/view/inc/footer.jsp" %>

</body>
</html>
<script src="<%=basePath %>inc/js/imgmgr.js"></script>
