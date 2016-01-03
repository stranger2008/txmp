<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
<title>手机端版本列表</title>
<script type="text/javascript" src="<%=basePath%>inc/com/calendar/WdatePicker.js"></script> 
</head>
<body>

<div class="position">手机端版本列表</div>

<sf:form action="app_android_version/index.action" id="queryForm" modelAttribute="app_android_versionQueryForm">

<div class="searchdiv">
	<ul>
		
		<li>
			<span>版本号：</span>
			<sf:input path="ver_code"/>
		</li>
		
		<li>
			<span>版本名称：</span>
			<sf:input path="ver_name"/>
		</li>
		
	</ul>
</div>

<div class="clearfix"></div>

<div class="kjbutdiv">
	<a href="javascript:void(0);" id="deleteInfo">删除</a>
	<a href="<%=basePath%>app_android_version/add.action">新增</a>
	<a href="javascript:void(0);" id="searchInfo">查询</a>
</div>

<c:if test="${!empty pageResult.list}">

<table width="100%" cellpadding="0" cellspacing="0" class="info_tab">
	<tr class="tab_th">
		<th width="5%"><input type="checkbox"></th>
		
		<th>版本号</th>
		
		<th>版本名称</th>
		
		<th>版本所属包</th>
		
		<th>下载地址</th>
		
		<th>变更日志</th>
		
		<th>变更时间</th>
		
		<th>修改</th>
	</tr>
	
	<c:forEach items="${pageResult.list}" var="item">

	<tr class="tab_td">
		<td><input type="checkbox" name="id" value="${item.id}"></td>
		
		<td>${item.ver_code}</td>
		
		<td>${item.ver_name}</td>
		
		<td>${item.pack}</td>
		
		<td>${item.download_url}</td>
		
		<td width="240">${item.change_log}</td>
		
		<td>${item.publish_time}</td>
		
		<td><a href="<%=basePath%>app_android_version/update.action?id=${item.id}">修改</a></td>
	</tr>

	</c:forEach>

</table>

</c:if>
		
<div class="page">${pageBar}</div>

</sf:form>
            
</body>
</html>

