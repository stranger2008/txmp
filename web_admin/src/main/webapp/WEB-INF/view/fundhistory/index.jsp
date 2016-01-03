<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
<script type="text/javascript" src="<%=basePath%>inc/com/calendar/WdatePicker.js"></script>
<title>收入/支出列表</title>
</head>
<body>

<div class="position">${fundaccount }, 资金异动列表</div>

<sf:form action="fundhistory/index.action" id="queryForm" modelAttribute="fundhistoryQueryForm">
<div class="searchdiv">
	<ul>
		<li>
			<span>类型：</span>
			<sf:select path="action_type">
				<sf:option value="">全部</sf:option>
				<sf:option value="0">充值</sf:option>
				<sf:option value="1">提现</sf:option>
				<sf:option value="2">订单支付</sf:option>
				<sf:option value="3">退款</sf:option>
			</sf:select>
		</li>
		
		<li>
			<span>日期：</span>
			<sf:input path="start_in_date" id="start_in_date" readonly="true" cssClass="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\\'end_in_date\\',{d:-1})}'})" cssStyle="width:86px;" />
			 - 
			<sf:input path="end_in_date" id="end_in_date" readonly="true" cssClass="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\\'start_in_date\\',{d:1})}'})" cssStyle="width:86px;" />
		</li>
		
	</ul>
</div>

<div class="clearfix"></div>

<div class="kjbutdiv">
	<a href="javascript:void(0);" id="searchInfo">查询</a>
</div>

<c:if test="${!empty pageResult.list}">

<table width="100%" cellpadding="0" cellspacing="0" class="info_tab">
	<tr class="tab_th">
		
		<th>收入</th>
		
		<th>支出</th>
		
		<th>余额</th>
		
		<th>类型</th>
		
		<th>明细</th>
		
		<th>操作人</th>
		
		<th>操作时间</th>
	</tr>
	
	<c:forEach items="${pageResult.list}" var="item">

	<tr class="tab_td">
		
		<td>
			<c:if test="${(item.fund_in < 0) or (item.fund_in > 0)}">￥${item.fund_in }</c:if>
			<c:if test="${not ((item.fund_in < 0) or (item.fund_in > 0))}">-</c:if>
		</td>
		
		<td>
			<c:if test="${(item.fund_out < 0) or (item.fund_out > 0)}">￥${item.fund_out }</c:if>
			<c:if test="${not ((item.fund_out < 0) or (item.fund_out > 0))}">-</c:if>
		</td>
		
		<td>${item.balance}</td>
		
		<td>
			<c:if test="${item.action_type == '0' }">充值</c:if>
			<c:if test="${item.action_type == '1' }">提现</c:if>
			<c:if test="${item.action_type == '2' }">订单支付</c:if>
			<c:if test="${item.action_type == '3' }">退款</c:if>
		</td>
		
		<td>${item.reason}</td>
		
		<td>
			${item.user_name }${item.cust_name }
			<c:if test="${item.user_id == null && item.cust_id == null }">系统</c:if>
		</td>
		
		<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${item.in_date }" /></td>
		
	</tr>

	</c:forEach>

</table>

</c:if>
		
<div class="page">${pageBar}</div>
<sf:hidden path="account_no" />
</sf:form>
            
</body>
</html>

