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
                    <a href="<%=basePath %>">首页</a> > <a href="<%=basePath %>user/uccenter.action">用户中心</a> > <a href="javascript:void(0);">我的收藏</a>
                </div>
            </section>
        </div>
        
        
<div class="module">
    <section>
        <div class="collect mt5">
            <div class="collect-tab">
            	<ul class="clearfix" id="ct-tab">
                    <li><a href="<%=basePath %>user/collectgoods.action" class="item on">商品收藏</a></li>
                    <li><a href="<%=basePath %>user/collectshop.action" class="item">店铺收藏</a></li>
                </ul>
            </div>
            
            <form action="<%=basePath %>user/collectgoods.action">
            
            <c:forEach items="${pageResult.list}" var="collect">
            
	            <div class="collect-cont1">
	                <ul>
	                    <li>
	                    	<div class="clearfix">
	                            <span class="fl list_img">
	                            	<a href="<%=basePath %>goods/${collect.info_id}.action">
	                            		<c:if test="${!empty collect.img_path}">
			                        		<img src="<h:imgSubstr imgpath="${collect.img_path}"/>" onload="DrawImage(this,90,90)"/>
		                        		</c:if>
	                            	</a>
	                            </span>
	                            <div class="product_info">
	                                <a href="<%=basePath %>goods/${collect.info_id}.action" class="product-h4"><b>${collect.goods_name}</b></a>
	                                <div class="price">￥${collect.sale_price}</div>
	                            </div>
	                        </div>
	                        <p class="clt-btn"><a href="<%=basePath %>user/collectdel-${collect.coll_id}-${collect.info_type}.action" class="clt-del">删除</a></p>
	                    </li>
	
	
	                </ul>
	            </div>
	            
            </c:forEach>
            
            <div class="add_more margin-center">${pageBar}</div>
            
            
            </form>
            
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
