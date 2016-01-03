<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
<title>广告信息管理列表</title>
</head>
<body>

<div class="position">广告信息管理列表</div>
<sf:form action="advinfo/index.action" id="queryForm" modelAttribute="advinfoQueryForm">
<sf:hidden path="pos_id"/>
<div class="searchdiv">
	<ul>
		
		<li>
			<span>广告名称：</span>
			<sf:input path="adv_name"/>
		</li>
		
	</ul>
</div>

<div class="clearfix"></div>

<div class="kjbutdiv">
	<a href="javascript:void(0);" id="deleteInfo">删除</a>
	<a href="<%=basePath%>advinfo/add.action?pos_id=${advinfoQueryForm.pos_id}">新增</a>
	<a href="javascript:void(0);" id="searchInfo">查询</a>
</div>

<div style="border-top:1px solid #cccccc ; padding:5px 0 ; " >
<span style="width:100px;"> 
 广告位：<a href="<%=basePath%>advpos/index.action?pos_name=${pos_name}" style="color:red;">${pos_name}</a>
</span>
</div>
<c:if test="${!empty pageResult.list}">

<table width="100%" cellpadding="0" cellspacing="0" class="info_tab">
	<tr class="tab_th">
		<th width="5%"><input type="checkbox"></th>
		
		<th>广告名称</th>
		
		<th>开始时间</th>
		
		<th>结束时间</th>
		
		<th>发布时间</th>
		
		<th>修改</th>
	</tr>
	
	<c:forEach items="${pageResult.list}" var="item">

	<tr class="tab_td">
		<td><input type="checkbox" name="id" value="${item.adv_id}"></td>
		
		<td>${item.adv_name}</td>
		
		<td>
		<fmt:formatDate value="${item.start_date}" pattern="yyyy-MM-dd"/>
		</td>
		
		<td>
		<fmt:formatDate value="${item.end_date}" pattern="yyyy-MM-dd"/>
		</td>
		
		<td>
		<fmt:formatDate value="${item.in_date}" pattern="yyyy-MM-dd"/>
		</td>
		
		<td><a href="<%=basePath%>advinfo/update.action?id=${item.adv_id}">修改</a></td>
	</tr>

	</c:forEach>

</table>

</c:if>
		
<div class="page">${pageBar}</div>

</sf:form>
            
</body>
</html>

