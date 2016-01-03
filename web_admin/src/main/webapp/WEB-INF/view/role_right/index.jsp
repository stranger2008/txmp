<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
<title>权限列表</title>
</head>
<body>

<div class="position">权限列表</div>

<sf:form action="role_right/index.action" id="queryForm" modelAttribute="role_rightQueryForm">

<div class="searchdiv">
	<ul>
		<li>
			<span>权限名称：</span>
			<sf:input path="right_name"/>
		</li>
		<li>
			<span>权限URL：</span>
			<sf:input path="url"/>
		</li>
	</ul>
</div>

<div class="clearfix"></div>

<div class="kjbutdiv">
	<a href="javascript:void(0);" id="deleteInfo">删除</a>
	<a href="<%=basePath%>role_right/add.action">新增</a>
	<a href="javascript:void(0);" id="searchInfo">查询</a>
</div>

<c:if test="${!empty pageResult.list}">

<table width="100%" cellpadding="0" cellspacing="0" class="info_tab">
	<tr class="tab_th">
		<th width="5%"><input type="checkbox"></th>
		
		<th>权限名称</th>
		
		<th>权限URL</th>
		
		<th>修改</th>
	</tr>
	
	<c:forEach items="${pageResult.list}" var="item">

	<tr class="tab_td">
		<td><input type="checkbox" name="id" value="${item.right_id}"></td>
		
		<td>${item.right_name}</td>
		
		<td>${item.url }</td>
		
		<td><a href="<%=basePath%>role_right/update.action?id=${item.right_id}">修改</a></td>
	</tr>

	</c:forEach>

</table>

</c:if>
		
<div class="page">${pageBar}</div>

</sf:form>
            
</body>
</html>

