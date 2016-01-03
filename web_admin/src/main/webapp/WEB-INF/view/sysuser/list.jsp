<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
<title>用户列表</title>
<script type="text/javascript">
$(document).ready(function() {
	$("#delete").click(function(){
		var ret = 0;
		$(".tab_td input[type='checkbox']").each(function(){
			if($(this).attr("checked") == true){
				ret = 1;
			}
		});
		if(ret == 0){
			alert("请至少选择一条信息");
		}else{
			if(confirm("确定删除已选择的信息吗？")){
				var actionVal = $("#queryForm").attr("action");
				actionVal = actionVal.replace("list","delete");
				$("#queryForm").attr("action",actionVal);
				$("#queryForm").submit();
			}
		}
	});
});

</script>
</head>
<body>

<div class="position">用户列表</div>

<sf:form action="sysuser/list.action" id="queryForm" modelAttribute="sysuserQueryForm">

<div class="searchdiv">
	<ul>
		<li>
			<span>用户名：</span>
			<sf:input path="user_name"/>
		</li>
		<li>
			<span>真实姓名：</span>
			<sf:input path="real_name"/>
		</li>
		<li>
			<span>状态:</span>
			<sf:select path="state">
				<sf:option value="">全部</sf:option>
				<sf:option value="0">启用</sf:option>
				<sf:option value="1">禁用</sf:option>
			</sf:select>
		</li>
	</ul>
</div>

<div class="clearfix"></div>

<div class="kjbutdiv">
	<a href="javascript:void(0);" id="delete">删除</a>
	<a href="<%=basePath%>sysuser/add.action">新增</a>
	<a href="javascript:void(0);" id="searchInfo">查询</a>
</div>

<c:if test="${!empty pageResult.list}">

<table width="100%" cellpadding="0" cellspacing="0" class="info_tab">
	<tr class="tab_th">
		<th width="5%"><input type="checkbox"></th>
		
		<th>用户名</th>
		
		<th>真实姓名</th>
		
		<th>状态</th>
		
		<th>修改</th>
		
		<th>重置密码</th>
	</tr>
	
	<c:forEach items="${pageResult.list}" var="item">

	<tr class="tab_td">
		<td>
			<c:if test="${item.user_type == '4' && sessionScope.session_user_id != item.user_id }">
				<input type="checkbox" name="id" value="${item.user_id}" />
			</c:if>
		</td>
		
		<td>${item.user_name}</td>
		
		<td>${item.real_name}</td>
		
		<td>
			<c:if test="${item.state == '0'}">启用</c:if>
			<c:if test="${item.state == '1'}">禁用</c:if>
		</td>
		
		<td><a href="<%=basePath%>sysuser/update.action?id=${item.user_id}">修改</a></td>
		
		<td><a href="<%=basePath%>sysuser/update-passwd.action?id=${item.user_id}">重置密码</a></td>
	</tr>

	</c:forEach>

</table>

</c:if>
		
<div class="page">${pageBar}</div>

</sf:form>
            
</body>
</html>

