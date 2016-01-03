<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<html>
<head>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
<title>商品库存变更列表</title>
</head>
<body>
<div class="position">商品库存变更列表</div>
	<sf:form action="goods/stock/history.action" id="goodsStockHistoryForm" modelAttribute="goodsStockHistoryForm">
            <table width="100%" cellpadding="0" cellspacing="0" class="info_tab">
                <tr class="tab_th">
                	<th width="15%">变更类型</th>
                	<th width="10%">变更数量</th>
                	<th width="10%">变更前</th>
                	<th width="10%">变更后</th>
                	<th width="30%">变更原因</th>
                	<th width="25%">变更时间</th>
                </tr>
                <c:if test="${not empty pageResult.list}">
                <c:forEach items="${pageResult.list}" var="item">
                <tr class="tab_td" >
                	<td>${item.changeTypeDesc }</td>
                	<td>${item.changeAmount }</td>
                	<td>${item.beforeChange }</td>
                	<td>${item.afterChange }</td>
                	<td>${item.changeReasonDesc }</td>
                	<td><fmt:formatDate value="${item.changeTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                </tr>
                </c:forEach>
                </c:if>
            </table>
            <div class="page">${pageBar}</div>

            <c:if test="${empty pageResult.list}">
	            <center>无商品库存变更信息</center>
	        </c:if>
</sf:form>
</body>
</html>
