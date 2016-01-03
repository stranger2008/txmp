<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
<title>会员积分列表</title>
</head>
<body>

<div class="position">会员积分列表</div>

<sf:form action="memberinter/index.action" id="queryForm" modelAttribute="memberinterQueryForm">

<div class="searchdiv">
	<ul>
		
		<li>
			<span>用户名：</span>
			<sf:input path="user_name"/>
		</li>
		<li>
			<span>总积分：</span>
				<sf:input path="min_credit_num" cssStyle="width:72px;" />
				-
				<sf:input path="max_credit_num" cssStyle="width:72px;" />
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
		
		<th>用户名</th>
		
		<th>总积分</th>
		
		<th>可用积分</th>
		
		<th>操作</th>
	</tr>
	
	<c:forEach items="${pageResult.list}" var="item">

	<tr class="tab_td">
		
		<td>${item.user_name}</td>
		
		<td>${item.credit_sum}</td>
		
		<td>${item.credit_usable}</td>
		
		<td><a href="<%=basePath%>interhistory/index.action?user_id=${item.user_id}">查看详细</a></td>
	</tr>

	</c:forEach>

</table>
</c:if>
		
<div class="page">${pageBar}</div>

</sf:form>
            
</body>
</html>

