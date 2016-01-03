<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
<title>角色管理列表</title>
<style type="text/css">
.kjbutdiv{border: none;}
</style>
</head>
<body>

<div class="position">角色管理列表</div>

<sf:form action="role/index.action" id="queryForm" modelAttribute="roleQueryForm">


<div class="clearfix"></div>

<div class="kjbutdiv">
	<a href="javascript:void(0);" id="deleteInfo">删除</a>
	<a href="<%=basePath%>role/add.action">新增</a>
</div>

<c:if test="${!empty pageResult.list}">

<table width="100%" cellpadding="0" cellspacing="0" class="info_tab">
	<tr class="tab_th">
		<th width="5%"><input type="checkbox"></th>
		
		<th>角色名称</th>
		<!-- 
		<th>客户标识</th>
		
		<th>菜单权限</th>
		
		<th>权限串</th>
		
		<th>备注</th>
		 -->
		<th>修改</th>
	</tr>
	
	<c:forEach items="${pageResult.list}" var="item">

	<tr class="tab_td">
		<td><input type="checkbox" name="id" value="${item.role_id}"></td>
		
		<td>${item.role_name}</td>
		<!-- 
		<td>${item.cust_id}</td>
		
		<td>${item.menu_right}</td>
		
		<td>${item.oper_right}</td>
		
		<td>${item.remark}</td>
		 -->
		<td><a href="<%=basePath%>role/update.action?id=${item.role_id}">修改</a></td>
	</tr>

	</c:forEach>

</table>

</c:if>
		
<div class="page">${pageBar}</div>

</sf:form>
            
</body>
</html>

