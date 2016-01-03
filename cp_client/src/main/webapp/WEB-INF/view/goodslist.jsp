<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
<title>商品列表-<%=web_title %></title>
</head>

<body>
<div class="w480">
    
    <!--头部开始-->
    <%@ include file="/WEB-INF/view/inc/top.jsp" %>
    
    <!--搜索开始-->
    <%@ include file="/WEB-INF/view/inc/search.jsp" %>
    
    <div class="content">
        
    	<!--当前位置开始-->
    	<div class="module">
            <section>
                <div class="position mt5">
                    <span>
                    	<a href="<%=basePath %>">首页</a> 
                    	<c:forEach items="${posTreeList}" var="category">
	                    	> <a href="<%=basePath %>goodslist.action?cat_id=${category.cat_id}">${category.cat_name}</a> 
                        </c:forEach>
                    </span>
                    <span class="rlt"><span>搜索到<em>${pageResult.total}</em>条</span></span>
                </div>
            </section>
        </div>
        
        <form action="goodslist.action">
    	<!--内容开始-->
        <div class="module">
        	<section>
        	<div class="all-goods margin-center">
        		<div class="head">
	                <ul>
	                    <li<c:if test="${g.pg.sortCode==11}"> class="selected"</c:if>><a href="<%=basePath %>goodslist.action?label=${g.label}&cat_id=${g.cat_id}&pg.sortCode=11">销量</a></li>
	                    <li<c:if test="${g.pg.sortCode==20 || g.pg.sortCode==21}"> class="selected"</c:if>>
	                    	
	                    	<c:choose>
							    <c:when test="${g.pg.sortCode==20}">
							      <a href="<%=basePath %>goodslist.action?label=${g.label}&cat_id=${g.cat_id}&pg.sortCode=21">价格</a>
							    </c:when>
							    <c:when test="${g.pg.sortCode==21}">
							      <a href="<%=basePath %>goodslist.action?label=${g.label}&cat_id=${g.cat_id}&pg.sortCode=20">价格</a>
							    </c:when>
							    <c:otherwise>
							      <a href="<%=basePath %>goodslist.action?label=${g.label}&cat_id=${g.cat_id}&pg.sortCode=20">价格</a>
							    </c:otherwise>
						    </c:choose>
	                    	
	                        <div>
	                            <span<c:if test="${g.pg.sortCode==20}"> style="border-bottom:6px solid #c80000;"</c:if>></span>
	                            <span<c:if test="${g.pg.sortCode==21}"> style="border-top:6px solid #c80000;"</c:if>></span>
	                        </div>
	                    </li>
	                    <li<c:if test="${g.pg.sortCode==0}"> class="selected"</c:if>><a href="<%=basePath %>goodslist.action?label=${g.label}&cat_id=${g.cat_id}&pg.sortCode=0">新品</a></li>
	                </ul>
	            
	            </div>
	           </div>
        	
        	
                <div class="list_cont mt5">
					<ul class="clearfix">
					
						
						
						<c:forEach items="${pageResult.list}" var="goods">
						
	                    	<li>
	                        	<span class="fl list_img">
	                        		
	                        		<c:if test="${!empty goods.img_path}">
	                        			<a href="<%=basePath %>goods/${goods.goods_id}.action">
		                        			<img src="<h:imgSubstr imgpath="${goods.img_path}"/>" onload="DrawImage(this,90,90)"/>
		                        		</a>
	                        		</c:if>
	                        	</span>
	                            <div class="product_info">
	                                <a href="<%=basePath %>goods/${goods.goods_id}.action" class="product-h4"><b>${goods.goods_name}</b></a>
	                                <div class="attr">${goods.goods_desc}</div>
	                                <div class="price">￥${goods.sale_price}</div>
	                            </div>
	                        </li>
                        </c:forEach>

                    </ul>
                    <div class="add_more margin-center">${pageBar}</div>
                </div>
            </section>
        </div>
        
        <input name="cat_id" value="${g.cat_id}" type="hidden"/>
        <input name="label" value="${g.label}" type="hidden"/>
        <input name="pg.sortCode" value="${g.pg.sortCode}" type="hidden"/>
        
        </form>
        
    </div>
    <!--底部开始-->
    <%@ include file="/WEB-INF/view/inc/footer.jsp" %>
</div>
</body>
</html>
