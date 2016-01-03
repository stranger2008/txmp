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
                <a href="<%=basePath %>">首页</a> > <a href="<%=basePath %>user/uccenter.action">用户中心</a> > <a href="javascript:void(0);">退换货</a>
            </div>
        </section>
    </div>
    
    <form action="user/goodsReturnList.action">
    
    <div class="module">
    <section>
    	<div class="mt5">
    	
    		<c:if test="${empty pageResult.list}">
    			<div style="padding-top:10px;">无订单信息</div>
    		</c:if>
    	
    	<c:if test="${!empty pageResult.list}">
    		
            <div class="order">
            	
                <div class="order-tab tbl-type">
                	<a href="javascript:void(0);" class="tbl-cell on">近7天内订单</a>
                	<a href="<%=basePath %>user/goodsReturn_prolist.action" class="tbl-cell on-l">交易进度查询</a>
                </div>
                 
                 <c:forEach items="${pageResult.list}" var="item" varStatus="status">
                <div class="order-section <c:if test="${(status.index+1)%2==0}">gray_bg</c:if>">
                    <div class="order-cont">
                        <ul class="order-cont-ul">
                            <li>
                            <a href="<%=basePath %>user/goodsReturn_selectgoods-${item.order_id}.action" class="order-cont-ula">
                                <span class="order-cont-ulimg">
                                	<c:forEach items="${item.orderdetails}" var="orderdetail">
                                	
								 		<c:if test="${!empty orderdetail.img_path}">
			                        		<img src="<h:imgSubstr imgpath="${orderdetail.img_path}"/>" onload="DrawImage(this,90,90)"/>
		                        		</c:if>
		                        		
								 	</c:forEach>
                                </span>
                                <span class="order-cont-ulcw">
                                	<span>订单编号：${item.order_id}</span>
                                    <span>订单金额：¥${item.tatal_amount}</span>
                                    <span>下单时间：${item.order_time}</span>
                                </span>
                                <span class="arr"></span>
                            </a>
                            <p class="btn-area">
                            	<span class="tbl-type mg-t5">
                                    <span class="tbl-cell w100"><a href="<%=basePath %>user/goodsReturn_selectgoods-${item.order_id}.action" class="abtn-type1">选择商品</a></span>
                                </span>
                            </p>
                            </li>
                        </ul>
                    </div>
                </div>
                
                </c:forEach>
                
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
