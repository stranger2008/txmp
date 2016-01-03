<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
<script type="text/javascript" src="<%=basePath%>inc/com/calendar/WdatePicker.js"></script>
<title>会员积分异动列表列表</title>
</head>
<body>


<sf:form action="interhistory/index.action" id="queryForm" modelAttribute="interhistoryQueryForm">
<div class="position">会员积分异动列表列表</div>

<div class="searchdiv">
	<ul>
		
		<li>
			<span>类型：</span>
			<sf:select path="credit_action">
				<sf:option value="">全部</sf:option>
				<sf:option value="0">订单支付</sf:option>
				<sf:option value="1">商品评价</sf:option>
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
		
		<th>积分收入</th>
		
		<th>积分支出</th>
		
		<th>剩余积分</th>
		
		<th>类型</th>
		
		<th>原因</th>
		
		<th>日期</th>
		
	</tr>
	
	<c:forEach items="${pageResult.list}" var="item">

	<tr class="tab_td">
		
		<td>${item.credit_in}</td>
		
		<td>${item.credit_out}</td>
		
		<td>${item.credit_remain}</td>
		
		<td>
			<c:if test="${item.credit_action == '0' }">订单支付</c:if>
			<c:if test="${item.credit_action == '1' }">商品评价</c:if>
		</td>
		
		<td>${item.reason}</td>
		
		<td>${item.in_date}</td>
		
	</tr>

	</c:forEach>

</table>

</c:if>
		
<div class="page">${pageBar}</div>

</sf:form>
            
</body>
</html>

