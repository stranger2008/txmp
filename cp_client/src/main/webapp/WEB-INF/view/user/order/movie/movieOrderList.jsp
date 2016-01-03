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
		   	  <form action="<%=basePath %>user/movieOrder.action.action">
					    <div class="module" id="orderList">
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
							                            <a href="javascript:showOrderDatil('${item.order_state_name}','${item.order_id}','${item.mobile}','${item.goods_name}','${item.aoods_amount}','${item.film_name}','${item.single_price}','${item.tatal_amount}');" class="order-cont-ula">
							                                <span class="order-cont-ulcw">
							                                	<span>订单号：${item.movie_order_id}</span>
							                                	<span>商品详情：${item.goods_name}/${item.film_name}</span>
							                                	<span>订单金额：${item.goods_amount}</span>
							                                    <span>下单时间：${item.order_time}</span>
							                                    
							                                </span>
							                                <span class="arr"></span>
							                            </a>
							                            <p class="btn-area">
							                            	<span class="tbl-type mg-t5">
							                                    <span class="tbl-cell w50"><a href="javascript:showOrderDatil('${item.order_state_name}','${item.order_id}','${item.mobile}','${item.goods_name}','${item.aoods_amount}','${item.film_name}','${item.single_price}','${item.tatal_amount}');" class="abtn-type1">订单详细</a></span>
							                                    <span class="tbl-cell w50">
							                                    	<c:if test="${item.order_state==2}">
																 		<a href="<%=basePath %>user/confirmReceipt-${item.order_id}.action" class="abtn-type2">确认收货</a>
																 	</c:if>
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
					<div class="module" id="orderInfo" style ="display:none">
						 <div class="order">
					                <div class="order-section <c:if test="${(status.index+1)%2==0}">gray_bg</c:if>">
					                	<div class="order-tit">
					                    	<span class="order-txt" >订单号：</span>
					                    	<span  id="order_id"></span>
					                    </div>
					                    <div class="order-tit">
					                    	<span class="order-txt" >订单状态：</span>
					                    	<span class="order-txt-sts" id="state_name"></span>
					                    </div>
					                    <div class="order-cont">
					                        <ul class="order-cont-ul">	
					                        	 <li>
					                            	<div class="orderDet-box">
					                                    <p class="order-usr"><span><strong>订单信息</strong></span></p>
					                                    <div class="order-my">
											                	<p>手机号码：<span id="moblie"></span></p>
											                	<p>影院：<span id="movie_name"></span> </p>
											                	<p>购票数量：<span id="goods_amount"></span></p>
											                	<p>电影名称：<span id="file_name"></span></p>
					                                    </div>
					                             	</div>
					                            </li>
					                            <li>
					                            	<div class="orderDet-box">
					                                    <p class="order-usr"><span><strong>支付信息</strong></span></p>
					                                    <div class="order-my">
											                	<p>支付类型：<span>在线支付</span></p>
											                	<p>单价：<span id="single_price"></span> </p>
											                	<p>订单支付总金额：<span id="taltal_amount"></span></p>
					                                    </div>
					                             	</div>
					                            </li>
					                        </ul>
					                    </div>
					                </div>
				            </div>
					</div>
			</form>
	    <%@ include file="/WEB-INF/view/inc/footer.jsp" %>
	    <script type="text/javascript">
	    		function showOrderDatil(order_state_name,order_id,moblie,goods_name,goods_amount,file_name,single_price,taltal_amount){
	    			$("#state_name").html(order_state_name);
	    			$("#order_id").html(order_id);
	    			$("#moblie").html(moblie);
	    			$("#movie_name").html(goods_name);
	    			$("#goods_amount").html(goods_amount);
	    			$("#file_name").html(file_name);
	    			$("#single_price").html(single_price);
	    			$("#taltal_amount").html(taltal_amount);
	    			
	    			$("#orderList").hide();
	    			$("#orderInfo").show();
	    		}
	    </script>
	</body>
</html>
<script src="<%=basePath %>inc/js/imgmgr.js"></script>
