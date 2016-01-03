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
  <form action="user/lotteryOrder.action">
	    <div class="module" id="lotteryOrder">
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
		                    	<span class="order-txt">订单编号：</span>
		                    	<span class="order-txt-sts">
		                    		${item.order_id}
		                    	</span>
		                    </div>
		                    <div class="order-cont">
		                        <ul class="order-cont-ul">
		                            <li>
		                             <a href="javascript:showOrderDatil('${item.order_id}');" class="order-cont-ula">
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
					                        		</c:choose>
			                                </span>
		                            	</c:if>
		                                <span class="order-cont-ulcw">
		                                	<span >第<font color='red'>${item.period}</font>期</span>
		                                    <span>订单金额：¥${item.tatal_amount}</span>
		                                    <span>订单状态：</span>
		                                    <span>中奖状态：</span>
		                                </span>
		                                <span class="arr"></span>
		                            </a>
		                            <p class="btn-area">
		                            	<span class="tbl-type mg-t5">
		                                    <span class="tbl-cell w50"><a href="javascript:showOrderDatil();" class="abtn-type1">查看</a></span>
		                                    <span class="tbl-cell w50">
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
		
		<div class="module" id="lotteryOrderDatil" style ="display:none">
				 <div class="order">
	                <div class="order-section <c:if test="${(status.index+1)%2==0}">gray_bg</c:if>">
	                	<div class="order-tit">
	                    	<span class="order-txt" >订单号：</span>
	                    	<span  id="order_id">111</span>
	                    	<span  id="order_id">111</span>
	                    </div>
	                    <div class="order-cont">
	                        <ul class="order-cont-ul">	
	                        	 <li>
	                            	<div class="orderDet-box">
	                                    <p class="order-usr"><span><strong>订单信息</strong></span></p>
	                                    <div class="order-my">
							                	<p>下单时间：<span id="single_date"></span></p>
							                	<p>截止投注：<span id="end_date"></span> </p>
							                	<p>注    数：<span id="boards"></span></p>
							                	<p>倍    数：<span id="betmultir"></span></p>
							                	<p>投注金额：<span id="totalamount"></span></p>
							                	<p>开奖号码：<span id="file_name"></span></p>
	                                    </div>
	                             	</div>
	                           </li>
	                            <li>
	                            	<div class="orderDet-box">
	                                    <p class="order-usr"><span><strong>投注内容</strong></span></p>
	                                    <div class="order-my">
							                	<p><span id="taltal_amount">02 04 20 22 25 31 10</span></p>
	                                    </div>
	                             	</div>
	                            </li>
	                             <li>
	                            	<div class="orderDet-box">
	                                    <p class="order-usr"><span><strong>投注者身份</strong></span></p>
	                                    <div class="order-my">
							                	<p>真实名称：<span>王益龙</span></p>
							                	<p>身份证号：<span id="single_price">610124198810181533</span> </p>
							                	<p>手机号码：<span id="taltal_amount">15510519022</span></p>
	                                    </div>
	                             	</div>
	                            </li>
	                        </ul>
	                    </div>
	                </div>
			 </div>
		</div>
		 <script type="text/javascript">
	    		function showOrderDatil(order_id){
	    			$("#order_id").html(order_id);
	    			$("#lotteryOrder").hide();
	    			$("#lotteryOrderDatil").show();
	    		}
	    </script>
</form>
    <%@ include file="/WEB-INF/view/inc/footer.jsp" %>
</body>
</html>
<script src="<%=basePath %>inc/js/imgmgr.js"></script>
