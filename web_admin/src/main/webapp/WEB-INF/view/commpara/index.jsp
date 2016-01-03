<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
<title>数据字典列表</title>
</head>
<body>

<div class="position">数据字典列表</div>

<sf:form action="commpara/index.action" id="queryForm" modelAttribute="commparaQueryForm">

 <div class="searchdiv">
	<ul>
		<li>
			<span>参数编码：</span>
			<sf:input path="para_code"/>
		</li>
		<li>
			<span>参数名称：</span>
			<sf:input path="para_name"/>
		</li>
		<li>
			<span>参数键：</span>
			<sf:input path="para_key"/>
		</li>
		<li>
			<span>参数值：</span>
			<sf:input path="para_value"/>
		</li>
		
		
	</ul>
<div class="clearfix"></div>
</div> 


<div class="kjbutdiv">
	<a href="javascript:void(0);" id="deleteInfo">删除</a>
	<a href="<%=basePath%>commpara/add.action">新增</a>
	<a href="javascript:void(0);" id="searchInfo">查询</a>
</div>

 <c:if test="${!empty pageResult.list}" >

<table width="100%" cellpadding="0" cellspacing="0" class="info_tab">
	<tr class="tab_th">
		<th width="5%"><input type="checkbox"></th>
		
		<th>排序</th>
		<th>参数编码</th>
		<th>参数名称</th>
		<th>参数键</th>
		<th>参数值</th>
		<th>是否有效</th>
		
		<th>修改</th>
	</tr>
	
	<c:forEach items="${pageResult.list}" var="item">

	<tr class="tab_td">
		<td><input type="checkbox" name="id" value="${item.para_id}"></td>
		
		<td>${item.sort_no}</td>
		<td>${item.para_code}</td>
		<td>${item.para_name}</td>
		<td>${item.para_key}</td>
		<td>${item.para_value}</td>
		<td>
			<c:if test="${item.enabled == 0}">
				是
			</c:if>
			<c:if test="${item.enabled == 1}">
				否
			</c:if>
		</td>
		
		<td><a href="<%=basePath%>commpara/update.action?id=${item.para_id}">修改</a></td>
	</tr>

	</c:forEach>

</table>

</c:if>
		
<div class="page">${pageBar}</div>

</sf:form>
            
</body>
</html>

