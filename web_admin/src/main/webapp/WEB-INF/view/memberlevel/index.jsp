<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
<title>等级配置列表</title>
</head>
<body>

<div class="position">等级配置列表</div>

<sf:form action="memberlevel/index.action" id="queryForm" modelAttribute="memberlevelQueryForm">

<div class="searchdiv">
	<ul>
		
		<li>
			<span>级别编码：</span>
			<sf:input path="level_code"/>
		</li>
		
		<li>
			<span>级别名称：</span>
			<sf:input path="level_name"/>
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
		
		<th>级别编码</th>
		
		<th>级别名称</th>
		
		<th>备注</th>
		
		<th>修改</th>
	</tr>
	
	<c:forEach items="${pageResult.list}" var="item">

	<tr class="tab_td">
		
		<td>${item.level_code}</td>
		
		<td>${item.level_name}</td>
		
		<td>${item.remark}</td>
		
		<td><a href="<%=basePath%>memberlevel/update.action?id=${item.level_code}">修改</a></td>
	</tr>

	</c:forEach>

</table>

</c:if>
		
<div class="page">${pageBar}</div>

</sf:form>
            
</body>
</html>

