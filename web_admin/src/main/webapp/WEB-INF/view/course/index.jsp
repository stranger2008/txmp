<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
<title>课程管理列表</title>
</head>
<body>

<div class="position">课程管理列表</div>

<sf:form action="course/index.action" id="queryForm" modelAttribute="courseQueryForm">

<div class="searchdiv">
	<ul>
		
		<li>
			<span>课程名称：</span>
			<sf:input path="c_name"/>
		</li>
		
		<li>
			<span>课程类型：</span>
			<sf:select path="c_type" items="${courseTypeMap}"/>
		</li>
		
		<li>
			<span>讲师：</span>
			<sf:input path="teacher"/>
		</li>
		
	</ul>
</div>

<div class="clearfix"></div>

<div class="kjbutdiv">
	<a href="javascript:void(0);" id="deleteInfo">删除</a>
	<a href="<%=basePath%>course/add.action">新增</a>
	<a href="javascript:void(0);" id="searchInfo">查询</a>
</div>

<c:if test="${!empty pageResult.list}">

<table width="100%" cellpadding="0" cellspacing="0" class="info_tab">
	<tr class="tab_th">
		<th width="5%"><input type="checkbox"></th>
		
		<th>课程名称</th>
		
		<th>课程类型</th>
		
		<th>讲师</th>
		
		<th>发布时间</th>
		
		<th>操作</th>
	</tr>
	
	<c:forEach items="${pageResult.list}" var="item">

	<tr class="tab_td">
		<td><input type="checkbox" name="id" value="${item.info_id}"></td>
		
		<td>${item.c_name}</td>
		
		<td>${item.c_type_name}</td>
		
		<td>${item.teacher}</td>
		
		<td>${item.in_date}</td>
		
		<td>
			<a href="<%=basePath%>course/update.action?id=${item.info_id}">修改</a>
			&nbsp;
			<a href="<%=basePath%>course_video/index.action?c_id=${item.info_id}">视频管理</a>
		</td>
	</tr>

	</c:forEach>

</table>

</c:if>
		
<div class="page">${pageBar}</div>

</sf:form>
            
</body>
</html>

