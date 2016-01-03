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
                        <ul class="orderDet-ul">
                            <li>
                            	<div class="orderDet-box">
                                    <p class="orderDet-cw">
                                    	<span>订单编号：${order.order_id }</span>
                                    	<span>订单状态：${order_state_name }
                                    	</span>
                                        <span>订单金额：¥${order.tatal_amount }</span>
                                        <span>下单时间：${order.order_time }</span>
                                    </p>
                                    <p class="btn-area">
                                        <span class="tbl-type mg-t5">
                                            <span class="tbl-cell w33"><a href="<%=basePath %>user/orderhis-${order.order_id}.action" class="abtn-type1">订单跟踪</a></span>
                                            <span class="tbl-cell w33">
                                            	
                                            	<c:if test="${order.order_state==2}">
											 		<a href="<%=basePath %>user/confirmReceipt-${order.order_id}.action" class="abtn-type2">确认收货</a>
											 	</c:if>
											 	
											 	<c:if test="${order.order_state==0}">
											 		<a href="<%=basePath %>unionpay/unionPayOrder.action?orderNo=${order.order_id}" class="abtn-type2">付款</a>
											 	</c:if>
		                                            
                                            </span>
                                            <span class="tbl-cell w33">
											 	<c:if test="${order.order_state==0}">
											 		<a href="<%=basePath %>user/cancelOrder-${order.order_id}.action" class="abtn-type3">取消订单</a>
											 	</c:if>
                                            </span>
                                        </span>
                                    </p>
                                </div>
                            </li>
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
	                                            <span class="cart-price">价格：<em>￥${item.order_price}</em></span><br/>
	                                            <span class="cart-price">数量：<em>${item.order_num}</em></span>
	                                        </div>
	                                    </div>
	                                    
									 </c:forEach>
                            	
                                    
                                </div>
                            </li>
                            <li>
                            	<div class="orderDet-box">
                                    <p class="order-usr"><span>${order.consignee}</span><span class="fr">${order.mobile}</span></p>
                                    <p class="order-addr">${area_name_str}${order.address}</p>
                             	</div>
                            </li>
                            <li>
                            	<div class="orderDet-box gray_bg">
                                    <p class="order-usr"><span>付款方式：</span><span class="fr">在线支付</span></p>
                                    <div class="order-money">
                                        <p><span>商品金额：</span><span class="fr"><em>￥${order.goods_amount}</em></span></p>
                                        <!-- <p><span>返现：</span><span class="fr"><em>￥0.00</em></span></p> -->
                                        <p><span>运费：</span><span class="fr"><em>￥${order.ship_free}</em></span></p>
                                    </div>
                                    <p class="order-total"><span>应支付金额：</span><span class="fr">￥${order.tatal_amount}</span></p>
                             	</div>
                            </li>
                            
                            
                            
                            
                            <li>
                            	<div class="orderDet-box">
                                    <p class="order-usr"><span>发票信息</span></p>
                                    <div class="order-my">
                                    
                                    	<c:if test="${order.invoice_type==2}">
						                	<p><span>不开发票</span><span class="fr"></span></p>
						                </c:if>
						                
						                <c:if test="${order.invoice_type==0}">
						                	<p><span>普通发票</span><span class="fr"></span></p>
						                	<p><span>发票抬头：</span><span class="fr">${order.invoice_top}</span></p>
						                	<c:if test="${order.invoice_type=='单位'}">
						                		<p><span>公司名称：</span><span class="fr">${order.company_name}</span></p>
						                	</c:if>
						                	<p><span>发票内容：</span><span class="fr">${order.invoice_content}</span></p>
						                </c:if>
						                
						                
						                <c:if test="${order.invoice_type==1}">
						                	<p><span>增值税发票</span><span class="fr"></span></p>
						                	
						                	<p><span>单位名称：</span><span class="fr">${order.company_name}</span></p>
						                	<p><span>纳税人识别号：</span><span class="fr">${order.ident_number}</span></p>
						                	<p><span>注册地址：</span><span class="fr">${order.regis_address}</span></p>
						                	<p><span>注册电话：</span><span class="fr">${order.regis_tel}</span></p>
						                	<p><span>开户银行：</span><span class="fr">${order.open_bank}</span></p>
						                	<p><span>银行帐户：</span><span class="fr">${order.bank_account}</span></p>
						                	<p><span>发票内容：</span><span class="fr">${order.invoice_content}</span></p>
						                	
						                </c:if>
						                
                                    </div>
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
