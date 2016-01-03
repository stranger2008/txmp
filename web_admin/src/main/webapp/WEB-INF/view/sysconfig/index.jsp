<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
<title>基本设置列表</title>
 
<script type="text/javascript">
function linkToInfo(code){
  $("#group_value").val(code);
  $("#queryForm").submit();
}
 
</script>
</head>
<body>

<div class="position">基本设置列表</div>

<sf:form action="sysconfig/index.action" id="queryForm" modelAttribute="sysconfigQueryForm">
<sf:hidden path="var_group" id="group_value"/>
<div class="searchdiv">
	<ul>
		<c:forEach items="${groupMap}" var="item" varStatus="status">
		<li style="width:100px;">
			<a href="javascript:void(0);"  onclick="linkToInfo('${item.para_value}');" 
			<c:if test="${item.para_value==sysconfigQueryForm.var_group}">style="color:red;"</c:if> >${item.para_key}</a> 
		</li>
		</c:forEach>
	</ul>
</div>

<div class="clearfix"></div>

<div class="kjbutdiv">
<!-- 
	<a href="javascript:void(0);" id="deleteInfo">删除</a>
	<a href="<%=basePath%>sysconfig/add.action">新增</a>
	<a href="javascript:void(0);" id="searchInfo">查询</a>
	 -->
</div>

<c:if test="${!empty pageResult.list}">

<table width="100%" cellpadding="0" cellspacing="0" class="info_tab">
	<tr class="tab_th">
		
		<th width="20%">变量名称</th>
		
		<th width="44%">变量值</th>
		
		<th width="30%">变量说明</th>
		
		<th width="6%">修改</th>
	</tr>
	
	<c:forEach items="${pageResult.list}" var="item">

	<tr class="tab_td">
		
		<td>${item.var_name}</td>
		
		<td>
		<c:if test="${item.var_type==2&&item.var_value==0}">
		 是
		</c:if>
		<c:if test="${item.var_type==2&&item.var_value==1}">
		 否
		</c:if>
		<c:if test="${item.var_type!=2}">
			${fn:substring(item.var_value, 0, 80)}
		</c:if>
		</td>
		
		<td>${item.var_desc}</td>
		
		<td><a href="<%=basePath%>sysconfig/update.action?id=${item.var_id}">修改</a></td>
	</tr>

	</c:forEach>

</table>

</c:if>
		
<div class="page">${pageBar}</div>

</sf:form>
            
</body>
</html>

