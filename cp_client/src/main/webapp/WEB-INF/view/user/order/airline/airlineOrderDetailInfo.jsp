<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
<script type="text/javascript" src="<%=basePath %>/inc/outapi/airline/js/airlinecompany.js"></script>
<title>用户中心</title>
</head>
<body>
    <!--头部开始-->
    <%@ include file="/WEB-INF/view/inc/top.jsp" %>
    	<!--当前位置开始-->
    	<div class="module">
            <section>
                <div class="position">
                    <a href="<%=basePath %>">首页</a> > <a href="<%=basePath %>user/uccenter.action">用户中心</a> > 
                    <a href="<%=basePath %>user/orderlist.action">订单管理</a>
                </div>
            </section>
        </div>
   <div class="module">
    <section>
    	<div class="cont mt5">
            <div class="order">
                <div class="order-section">
                    <div class="order-cont">
                        <ul class="orderDet-ul">
                            <li>
                            	<div class="orderDet-box">
                                    <p class="orderDet-cw">
                                   		<c:forEach items="${orderInfo}" var="orderInfo" varStatus="num">
                                    		<span>票${num.index+1}：${orderInfo.orgcity}-${orderInfo.dstcity}</span>
                                    	</c:forEach>
                                    	<span>预订时间：${gf.order_time }</span>
                                        <span>联系人：${orderInfo[0].linkman }</span>
                                        <span> 状态：${gf.order_state_name }</span>
                                    </p>
                                </div>
                            </li>
                            <c:forEach items="${orderInfo}" var="orderInfo" varStatus="num">
	                              <li>
	                            	<div class="orderDet-box">
	                                    <p class="order-usr"><span>票${num.index+1}</span></p>
	                                    <div class="order-my">
							                	<p><span>起飞时间：${orderInfo.date}</span></p>
							                	<p>
							                		<span> 
								                		 <img src="<%=basePath %>/inc/outapi/airline/images/${fn:substring(orderInfo.flightno, 0, 2)}.gif" />
								                		 <script type="text/javascript"> document.write(company['${fn:substring(orderInfo.flightno, 0, 2)}']);  </script> 
								                		 ${orderInfo.flightno}
							                		 </span>
								                </p>
							                	<p><span><img src="<%=basePath %>inc/outapi/airline/images/icon_air2.png" />${orderInfo.deptime}   ${orderInfo.orgcity}</span></p>
							                	<p><span><img src="<%=basePath %>inc/outapi/airline/images/icon_air3.png" />${orderInfo.arritime}   ${orderInfo.dstcity}</span></p>
	                                    </div>
	                             	</div>
	                            </li>
                            </c:forEach>
                            <li>
                            	<div class="orderDet-box">
                                    <p class="order-usr"><span>总价信息</span></p>
                                    <div class="order-my">
						                	<p><span>乘机人数：</span><span class="fr">${fn:length(orderInfo)}</span></p>
						                	<p><span>总价：</span><span class="fr">${gf.tatal_amount}</span></p>
                                    </div>
                             	</div>
                            </li>
                            
                            <li>
                            	<div class="orderDet-box">
                                    <p class="order-usr"><span>乘机人</span></p>
                                    <div class="order-my">
                                      <c:forEach items="${orderInfo}" var="orderInfo" varStatus="num">
						                	<p><span>姓名：</span><span class="fr">${orderInfo.passengername }</span></p>
						               </c:forEach> 	
                                    </div>
                             	</div>
                            </li>
                            
                            <li>
                            	<div class="orderDet-box">
                                    <p class="order-usr"><span>配送信息</span></p>
                                    <div class="order-my">
                                     <c:if test="${empty orderInfo[0].journeysheet_id}">
                                       		 <p><span>不要行程单 </span></p>
                                     </c:if>
                                     <c:if test="${!empty orderInfo[0].journeysheet_id}">
                                       		 <p><span>行程单联系人:${orderInfo[0].jsname }</span></p>
                                       		  <p><span>联系电话：${orderInfo[0].phone } </span></p>
                                     </c:if>
                                    </div>
                             	</div>
                            </li>
                            
                        </ul>
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
<script src="<%=basePath %>inc/js/imgmgr.js"></script>
