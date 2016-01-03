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
                    <a href="<%=basePath %>">首页</a> > <a href="<%=basePath %>user/uccenter.action">用户中心</a> > <a href="<%=basePath %>user/orderlist.action">退换货</a>
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
                            	<div class="orderDet-box gray_bg">
                            	
                            		<c:forEach items="${order.orderdetails}" var="item">
                            		
	                            		<div class="clearfix" style="margin-bottom:3px;">
	                                        <span class="fl list_img">
	                                        	<c:if test="${!empty item.img_path}">
				                        			<a href="<%=basePath %>goods/${item.goods_id}.action">
					                        			<img src="<h:imgSubstr imgpath="${item.img_path}"/>" onload="DrawImage(this,90,90)"/>
					                        		</a>
				                        		</c:if>
	                                        </span>
	                                        <div class="product_info">
	                                            <a href="<%=basePath %>goods/${item.goods_id}.action" class="product-h4"><b>${item.goods_name}</b></a>
	                                            <span class="cart-price">价格：<em>¥${item.order_price}</em></span><br/>
	                                            <span class="cart-price">数量：<em>${item.order_num}</em></span>
	                                        </div>
	                                        
	                                    </div>
	                                    <p class="btn-area" style="margin-bottom:5px;">
	                                        <span class="tbl-type mg-t5">
	                                            <span class="tbl-cell w33">
	                                            	<a href="<%=basePath %>user/orderhis-${order.order_id}.action" class="abtn-type1">退货</a>
	                                            </span>
	                                            <span class="tbl-cell w33">
												 	<a href="<%=basePath %>user/goodsReturn_refund-${item.trade_id}.action" class="abtn-type2">退款</a>
	                                            </span>
	                                            <span class="tbl-cell w33">
												 	<a href="<%=basePath %>user/goodsReturn_refund-{id}.action" class="abtn-type3">换货</a>
	                                            </span>
	                                        </span>
	                                    </p>
	                                    
									 </c:forEach>
                            	
                                    
                                </div>
                            </li>
                            
                            
                            <!-- 
                            <li>
                            	<div class="orderDet-box">
                                    <p class="order-usr"><span>配送信息</span></p>
                                    <div class="order-my">
                                        <p><span>配送方式：</span><span class="fr">普通快递</span></p>
                                        <p><span>送货时间：</span><span class="fr">2014-05-15</span></p>
                                        <p><span>配送时间：</span><span class="fr">15:00-19:00</span></p>
                                    </div>
                             	</div>
                            </li>
                             -->
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
