<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
<title>店铺列表</title>
</head>

<body>

<%@ include file="/WEB-INF/view/shop/top.jsp" %>


<form action="shop/list.action">
<!--所有商品-->
<div class="module">
    <section>
        <div class="all-goods margin-center">
            <div class="head">
                <ul>
                    <li<c:if test="${goodsQueryForm.pg.sortCode==11}"> class="selected"</c:if>><a href="<%=basePath %>shop/list.action?cust_id=${cust_id}&pg.sortCode=11">销量</a></li>
                    <li<c:if test="${goodsQueryForm.pg.sortCode==20 || goodsQueryForm.pg.sortCode==21}"> class="selected"</c:if>>
                    	
                    	<c:choose>
						    <c:when test="${goodsQueryForm.pg.sortCode==20}">
						      <a href="<%=basePath %>shop/list.action?cust_id=${cust_id}&pg.sortCode=21">价格</a>
						    </c:when>
						    <c:when test="${goodsQueryForm.pg.sortCode==21}">
						      <a href="<%=basePath %>shop/list.action?cust_id=${cust_id}&pg.sortCode=20">价格</a>
						    </c:when>
						    <c:otherwise>
						      <a href="<%=basePath %>shop/list.action?cust_id=${cust_id}&pg.sortCode=20">价格</a>
						    </c:otherwise>
					    </c:choose>
                    	
                        <div>
                            <span<c:if test="${goodsQueryForm.pg.sortCode==20}"> style="border-bottom:6px solid #c80000;"</c:if>></span>
                            <span<c:if test="${goodsQueryForm.pg.sortCode==21}"> style="border-top:6px solid #c80000;"</c:if>></span>
                        </div>
                    </li>
                    <li<c:if test="${goodsQueryForm.pg.sortCode==0}"> class="selected"</c:if>><a href="<%=basePath %>shop/list.action?cust_id=${cust_id}&pg.sortCode=0">新品</a></li>
                </ul>
            
            </div>

            <ul class="all-goods-lst clearfix">
            
                <c:forEach items="${pageResult.list}" var="goods">
                <li>
                    <a href="<%=basePath %>goods/${goods.goods_id}.action" class=" ">
                        <span class="goods-lst-img">
                        	<c:if test="${!empty goods.img_path}">
                        		<img src="<h:imgSubstr imgpath="${goods.img_path}"/>" />
                       		</c:if>
                        </span>
                        <div class="img-box">
                            <p>${goods.goods_name}</p>
                            <span class="red-pr">¥${goods.sale_price}</span>
                        </div>
                    </a>
                </li>
                
				</c:forEach>
			
            </ul>
            <!--分页-->
            <div class="add_more margin-center">${pageBar}</div>
        </div>
        
    </section>
</div>   
<input name="cust_id" value="${cust_id}" type="hidden"/>
<input name="pg.sortCode" value="${goodsQueryForm.pg.sortCode}" type="hidden"/>

</form>     

<%@ include file="/WEB-INF/view/inc/footer.jsp" %>

</body>
</html>
