<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
<title>商家资金提现申请</title>
</head>
<body>

<div class="position">商家资金提现申请</div>

<sf:form action="fundwithdraw/member-index.action" id="queryForm" modelAttribute="fundwithdrawQueryForm">
<div class="searchdiv">
	<ul>
		
		<li>
			<span>账号：</span>
			<sf:input path="account_no"/>
		</li>
		
		<li>
			<span>商家名称：</span>
			<sf:input path="user_name"/>
		</li>
		
		<li>
			<span>资金：</span>
				<sf:input path="min_trans_amt" cssStyle="width:72px;" />
				-
				<sf:input path="max_trans_amt" cssStyle="width:72px;" />
		</li>
		
		<li>
			<span>状态：</span>
			<sf:select path="status">
				<sf:option value="">全部</sf:option>
				<sf:option value="0">待审核</sf:option>
				<sf:option value="1">审核不通过</sf:option>
				<sf:option value="2">审核通过</sf:option>
			</sf:select>
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
	
		<th>账号</th>
		
		<th>商家名称</th>
		
		<th>提现金额</th>
		
		<th>申请时间</th>
		
		<th>状态</th>
		
		<th>操作</th>
	</tr>
	
	<c:forEach items="${pageResult.list}" var="item">

	<tr class="tab_td">
	
		<td>${item.account_no}</td>
		
		<td>
			${item.user_name }
		</td>
		
		<td>￥${item.trans_amt}</td>
		
		<td>${fundwithdraw.operate_date }
			<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${item.operate_date }" />
		</td>
		
		<td>
			<c:if test="${item.status == '0' }">
				待审核
			</c:if>
			<c:if test="${item.status == '1' }">
				审核不通过
			</c:if>
			<c:if test="${item.status == '2' }">
				审核通过
			</c:if>
			<c:if test="${item.status == '3' }">
				提现失败
			</c:if>
		</td>
		
		<td>
			<c:if test="${item.status == '0' }">
				<a href="<%=basePath%>fundwithdraw/member-audit.action?trade_id=${item.trade_id}">审核</a>
			</c:if>
			<c:if test="${item.status != '0' }">
				<a href="<%=basePath%>fundwithdraw/member-view.action?trade_id=${item.trade_id}">查看详情</a>
			</c:if>
		</td>
	</tr>

	</c:forEach>

</table>

</c:if>
		
<div class="page">${pageBar}</div>

</sf:form>
            
</body>
</html>

