<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
<title>课程视频管理列表</title>
</head>
<body>

<div class="position">课程视频管理列表</div>

<sf:form action="course_video/index.action" id="queryForm" modelAttribute="course_videoQueryForm">

<div class="searchdiv">
	<ul>
		
	</ul>
</div>

<div class="clearfix"></div>

<div class="kjbutdiv">
	<a href="javascript:void(0);" id="deleteInfo">删除</a>
	<a href="<%=basePath%>course_video/add.action?c_id=${course_videoQueryForm.c_id}">新增</a>
	<a href="javascript:void(0);" id="searchInfo">查询</a>
</div>

<c:if test="${!empty pageResult.list}">

<table width="100%" cellpadding="0" cellspacing="0" class="info_tab">
	<tr class="tab_th">
		<th width="5%"><input type="checkbox"></th>
		
		<th>视频名称</th>
		
		<th>视频地址</th>
		
		<th>修改</th>
	</tr>
	
	<c:forEach items="${pageResult.list}" var="item">

	<tr class="tab_td">
		<td><input type="checkbox" name="id" value="${item.info_id}"></td>
		
		<td>${item.video_name}</td>
		
		<td>${item.video_path}</td>
		
		<td><a href="<%=basePath%>course_video/update.action?id=${item.info_id}">修改</a></td>
	</tr>

	</c:forEach>

</table>

</c:if>
		
<div class="page">${pageBar}</div>

</sf:form>
            
</body>
</html>

