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
    
    <form action="user/goodsReturn_prolist.action">
    
    <div class="module">
    <section>
    	<div class="mt5">
    	
    		<c:if test="${empty pageResult.list}">
    			<div style="padding-top:10px;">无退换货信息</div>
    		</c:if>
    	
    	<c:if test="${!empty pageResult.list}">
    		
            <div class="order">
            	
                <div class="order-tab tbl-type">
                	<a href="<%=basePath %>user/goodsReturn_list.action" class="tbl-cell on-l">近7天内订单</a>
                	<a href="javascript:void(0);" class="tbl-cell on">交易进度查询</a>
                </div>
                 
                 <c:forEach items="${pageResult.list}" var="item" varStatus="status">
                <div class="order-section <c:if test="${(status.index+1)%2==0}">gray_bg</c:if>">
                    <div class="order-cont">
                        <ul class="order-cont-ul">
                            <li>
                            <a href="<%=basePath %>user/goodsreturnhis-${item.trade_id}.action" class="order-cont-ula">
                                <span class="order-cont-ulimg">
							 		<c:if test="${!empty item.goods_img_path}">
		                        		<img src="<h:imgSubstr imgpath="${item.goods_img_path}"/>" onload="DrawImage(this,90,90)"/>
	                        		</c:if>
                                </span>
                                <span class="order-cont-ulcw">
                                	<span>商品名称：${item.goods_name}</span>
                                    <span>状态：${item.info_state_name}</span>
                                    <span>申请时间：${item.in_date}</span>
                                </span>
                                <span class="arr"></span>
                            </a>
                            <p class="btn-area">
                            	<span class="tbl-type mg-t5">
                                    <span class="tbl-cell w100"><a href="<%=basePath %>user/goodsreturnhis-${item.trade_id}.action" class="abtn-type1">进度查询</a></span>
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
