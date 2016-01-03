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
		    <form action="<%=basePath %>user/orderlist.action">
					    <div class="module">
					    <section>
					    	<div class="mt5">
					    		<c:if test="${empty pageResult.list}">
					    			<div style="padding-top:10px;">无订单信息</div>
					    		</c:if>
					    	<c:if test="${!empty pageResult.list}">
					            <div class="order">
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
							                            <a href="<%=basePath %>user/airLineOrderInfo.action?order_id=${item.order_id}&order_time=${item.order_time}&order_state_name=${item.order_state_name}&tatal_amount=${item.tatal_amount}" class="order-cont-ula">
							                                <span class="order-cont-ulcw">
							                                	<span><strong>${item.orgCity}-${item.dstCity}</strong></span>
							                                    <span>预定时间：${item.order_time}</span>
							                                    <span>联系人：${item.linkman}</span>
							                                </span>
							                                <span class="arr"></span>
							                            </a>
							                            <p class="btn-area">
							                            	<span class="tbl-type mg-t5">
							                                    <span class="tbl-cell w50"><a href="<%=basePath %>user/airLineOrderInfo.action?order_id=${item.order_id}&order_time=${item.order_time}" class="abtn-type1">订单详细</a></span>
							                                    <span class="tbl-cell w50">
							                                    	
																 	<c:if test="${item.order_state==0}">
																 		<a href="<%=basePath %>order/payment.action?order_id=${item.order_id}" class="abtn-type2">付款</a>
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
