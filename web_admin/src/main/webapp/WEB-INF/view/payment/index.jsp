<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
<title>支付方式列表</title>
</head>
<body>

<div class="position">支付方式列表</div>

<sf:form action="payment/index.action" id="queryForm" modelAttribute="paymentQueryForm">

<div class="searchdiv">
	<ul>
		
	</ul>
</div>

<div class="clearfix"></div>

<c:if test="${!empty pageResult.list}">

<table width="100%" cellpadding="0" cellspacing="0" class="info_tab">
	<tr class="tab_th">
		<th>支付方式编码</th>
		
		<th>支付方式名称</th>
		
		<th>是否启用</th>
		
		<th>排序</th>
		
		<th>支持客户端</th>
		
		<th>修改</th>
	</tr>
	
	<c:forEach items="${pageResult.list}" var="item">

	<tr class="tab_td">
		<td>${item.pay_code}</td>
		
		<td>${item.pay_name}</td>
		
		<td>
			<c:if test="${item.enabled == 1}">
				是
			</c:if>
			<c:if test="${item.enabled == 0}">
				否
			</c:if>
		</td>
		
		<td>${item.sort_no}</td>
		
		<td>
			<c:set value="${fn:split(item.client_attr, ',') }" var="rs" />
			<c:forEach items="${rs}" var="r">
				<c:if test="${r == 'android' }">
				手机客户端
				</c:if>
			</c:forEach>
			<c:forEach items="${rs }" var="r">
				<c:if test="${r == 'cp' }">
				触屏版
				</c:if>
			</c:forEach>			 
			<c:forEach items="${rs }" var="r">
				<c:if test="${r == 'web' }">
				Web版	
				</c:if>
			</c:forEach>
		</td>
				
		<td><a href="<%=basePath%>payment/update.action?id=${item.pay_id}">修改</a></td>
	</tr>

	</c:forEach>

</table>

</c:if>
		
<div class="page">${pageBar}</div>

</sf:form>
            
</body>
</html>

