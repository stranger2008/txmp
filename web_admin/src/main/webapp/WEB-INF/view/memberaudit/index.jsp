<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
<title>商家信息列表</title>
<script type="text/javascript" src="<%=basePath%>inc/com/calendar/WdatePicker.js"></script>
<style type="text/css">
#selectDivModel{
	width:350px;
	display:inline-block;
	text-align:left;
}
.searchdiv select{
	width:100px;
}
#apply_time_begin, #apply_time_end{
	width:86px;
	text-align:left;
}
</style>
</head>
<body>

<div class="position">商家信息列表</div>

<sf:form action="member-audit/index.action" id="queryForm" modelAttribute="memberQueryForm">

<div class="searchdiv">
	<ul>
		
		<li>
			<span>商家名称：</span>
			<sf:input path="cust_name"/>
		</li>
		
		<li style="width:455px;">
			<span>所在地区：</span>
			<span id="selectDivModel"></span>
			<script type="text/javascript">
				showSelectCascade("<%=basePath %>","area","selectDivModel","area_attr","${memberQueryForm.area_attr },");
			</script>
		</li>
		
		<li>
			<span>申请时间：</span>
			<sf:input path="apply_time_begin" id="apply_time_begin" readonly="readonly" cssClass="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\\'apply_time_end\\',{d:-1})}'})" />
			 - 
			<sf:input path="apply_time_end" id="apply_time_end" readonly="readonly" cssClass="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\\'apply_time_begin\\',{d:1})}'})" />
		</li>
			<span>状态：</span>
			<sf:select path="audit_status">
				<sf:option value="">全部</sf:option>
				<sf:option value="0">未审核</sf:option>
				<sf:option value="2">未通过</sf:option>
			</sf:select>
		<li>
		
		</li>
		
	</ul>
</div>

<div class="clearfix"></div>

<div class="kjbutdiv">
	<a href="javascript:void(0);" id="deleteInfo">删除</a>
	<a href="javascript:void(0);" id="searchInfo">查询</a>
</div>

<c:if test="${!empty pageResult.list}">

<table width="100%" cellpadding="0" cellspacing="0" class="info_tab">
	<tr class="tab_th">
		<th width="5%"><input type="checkbox"></th>
		
		<th>商家名称</th>
		
		<th>所在地区</th>
		
		<th>申请时间</th>
		
		<th>状态</th>
		
		<th>操作</th>
	</tr>
	
	<c:forEach items="${pageResult.list}" var="item">

	<tr class="tab_td">
		<td><input type="checkbox" name="id" value="${item.cust_id}"></td>
		
		<td>${item.cust_name}</td>
		
		<td>${item.area_attr_name}</td>
		
		<td><fmt:formatDate value="${item.apply_time}" pattern="yyyy-MM-dd" /></td>
		
		<td>
			<c:if test="${item.audit_status == '0' }">未审核</c:if>
			<c:if test="${item.audit_status == '1' }">正常</c:if>
			<c:if test="${item.audit_status == '2' }">未通过</c:if>
		</td>
		
		<td>
			<c:if test="${item.audit_status == '0' }">
				<a href="<%=basePath%>member-audit/update.action?id=${item.cust_id}">审核</a>
			</c:if>
		<c:if test="${item.audit_status == '2' }">
			<a href="<%=basePath%>member-audit/view.action?id=${item.cust_id}">查看</a>
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

