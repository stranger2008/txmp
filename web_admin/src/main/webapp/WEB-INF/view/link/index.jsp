<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
<title>友情链接列表</title>
</head>
<body>

<div class="position">友情链接列表</div>

<sf:form action="link/index.action" id="queryForm" modelAttribute="linkQueryForm">

<div class="searchdiv">
	<ul>
		<li>
			<span>链接名称：</span>
			<sf:input path="link_name"/>
		</li>
		<li>
			<span>链接地址：</span>
			<sf:input path="url"/>
		</li>
	</ul>
</div>

<div class="clearfix"></div>

<div class="kjbutdiv">
	<a href="javascript:void(0);" id="deleteInfo">删除</a>
	<a href="<%=basePath%>link/add.action">新增</a>
	<a href="javascript:void(0);" id="searchInfo">查询</a>
</div>

<c:if test="${!empty pageResult.list}">

<table width="100%" cellpadding="0" cellspacing="0" class="info_tab">
	<tr class="tab_th">
		<th width="5%"><input type="checkbox"></th>
		<th>链接名称</th>
		<th>链接地址</th>
		<th>排序</th>
		<th>是否显示</th>
		<th>修改</th>
	</tr>
	
	<c:forEach items="${pageResult.list}" var="item">

	<tr class="tab_td">
		<td><input type="checkbox" name="id" value="${item.link_id}"></td>
		<td>${item.link_name}</td>
		<td>${item.url}</td>
		<td>${item.sort_no}</td>
		<td>
			<c:if test="${item.is_display == 0}">
				显示
			</c:if>
			<c:if test="${item.is_display == 1}">
				隐藏
			</c:if>
		</td>
		<td><a href="<%=basePath%>link/update.action?id=${item.link_id}">修改</a></td>
	</tr>

	</c:forEach>

</table>

</c:if>
		
<div class="page">${pageBar}</div>

</sf:form>
            
</body>
</html>