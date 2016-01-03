<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<jsp:useBean id="now" class="java.util.Date" />
<html>
<head>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
<title>上下架信息列表</title>
</head>
<body>
<div class="position">上下架信息列表</div>
	<sf:form action="goods/stateList.action" id="goodsForm" modelAttribute="goodsQueryForm">
		<table class="search" cellpadding="0" cellspacing="10" width="100%">
               <tbody>
                   <tr>
                       <td>
                       	<sf:input path="key" placeholder="商品名称" maxlength="50" cssClass="w400"/>
                       	<input name="sub" value="搜索" class="sear-btn" type="submit"/>
					</td>
                   </tr>
               </tbody>
           </table>
            
            <table width="100%" cellpadding="0" cellspacing="0" class="info_tab">
                <tr class="tab_th">
                	<th width="40%">商品名称</th>
                	<th width="10%">商品售价</th>
                	<th width="10%">当前库存</th>
                	<th width="10%">销售状态</th>
                	<th width="10%">上架时间</th>
                	<th width="10%">操作</th>
                </tr>
                <c:if test="${not empty pageResult.list}">
                <c:forEach items="${pageResult.list}" var="item">
                <tr class="tab_td" >
                	<td>
                  		<img src="<h:imgSubstr imgpath="${item['img_path']}"/>" />
                  		<span title="${item['goods_name']}">${fn:length(item['goods_name'])>10?fn:substring(item['goods_name'], 0, 20): item['goods_name']}</span>
                	</td>
                	<td>￥${item['sale_price'] }</td>
                	<td>${item['now_stock'] }</td>
                	<td>${(now lt item['up_date']) ? '未上架' : ((now gt item['down_date']) ? '已下架' : '销售中') }</td>
                	<td><fmt:formatDate value="${item['in_date']}" pattern="yyyy-MM-dd" /></td>
                	<td>
                		<c:choose>
                			<c:when test="${(now lt item['up_date']) or (now gt item['down_date'])}" >
                		<a href="<%=basePath%>goods/up/${item['goods_id']}.action">商品上架</a>
                			</c:when>
                			<c:otherwise>
                		<a href="<%=basePath%>goods/down/${item['goods_id']}.action">商品下架</a>
                			</c:otherwise>
                		</c:choose>
                	</td>
                </tr>
                </c:forEach>
                </c:if>
            </table>
            <div class="page">${pageBar}</div>

            <c:if test="${empty pageResult.list}">
	            <center>无商品信息</center>
	        </c:if>
</sf:form>
</body>
</html>
