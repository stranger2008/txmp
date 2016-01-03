<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
<title>会员等级列表</title>
</head>
<body>

<div class="position">会员等级列表</div>

<sf:form action="userlevelset/index.action" id="queryForm" modelAttribute="userlevelsetQueryForm">


<div class="kjbutdiv">
</div>

<c:if test="${!empty pageResult.list}">

<table width="100%" cellpadding="0" cellspacing="0" class="info_tab">
	<tr class="tab_th">
		<th width="5%"><input type="checkbox"></th>
		<th>等级名称</th>
		<th>修改</th>
	</tr>
	
	<c:forEach items="${pageResult.list}" var="item">

	<tr class="tab_td">
		<td><input type="checkbox" name="id" value="${item.level_code}"></td>
		<td>${item.level_name}</td>
		
		<td><a href="<%=basePath%>userlevelset/update.action?id=${item.level_code}">修改</a></td>
	</tr>

	</c:forEach>

</table>

</c:if>
		
<div class="page">${pageBar}</div>

</sf:form>
            
</body>
</html>

