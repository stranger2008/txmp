<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
<title>菜单列表</title>
<script type="text/javascript">
function listMenu(syscode, up_menu_id){
	if(syscode != null && syscode != '') {
		$("#syscode").val(syscode);
	}
	$("#up_menu_id").val(up_menu_id);
	$("#queryForm").submit();
}
function addInfo() {
	$("#queryForm").attr("action", "<%=basePath %>sysmenu/add.action");
	$("#queryForm").attr("method", "GET");
	$("#queryForm").submit();
}

//删除按钮通用事件
$(document).ready(function() {
	$("#deleteSysmenu").click(function(){
		var ret = 0;
		$(".tab_td input[type='checkbox']").each(function(){
			if($(this).attr("checked") == true){
				ret = 1;
			}
		});
		if(ret == 0){
			alert("请至少选择一条信息");
		}else{
			if(confirm("删除菜单后,其子菜单也会被删除,您确定要删除吗？")){
				var actionVal = $("#queryForm").attr("action");
				actionVal = actionVal.replace("index","delete");
				$("#queryForm").attr("action",actionVal);
				$("#queryForm").submit();
			}
		}
	});
});
</script>
<style type="text/css">
.pathdiv{margin-top:10px;border-top:1px solid #cccccc;padding-top:10px;margin-bottom:10px;}
</style>
</head>
<body>

<div class="position">菜单列表</div>

<sf:form action="sysmenu/index.action" id="queryForm" modelAttribute="sysmenuQueryForm">
<sf:hidden path="syscode" id="syscode"/>
<sf:hidden path="up_menu_id" id="up_menu_id"/>
<div class="searchdiv">
	<ul>
		<c:forEach items="${commparas}" var="item" varStatus="status">
		<li style="width:100px;">
			<a href="javascript:void(0);" onclick="listMenu('${item.para_value}', '1111111111');"
			<c:if test="${item.para_value==sysmenuQueryForm.syscode}">style="color:red;"</c:if> >${item.para_key}</a> 
		</li>
		</c:forEach>
	</ul>
</div>

<div class="clearfix"></div>

<div class="clearfix"></div>
<div class="kjbutdiv">
	<a href="javascript:void(0);" id="deleteSysmenu">删除</a>
	<a href="javascript:void(0);" id="addInfo" onclick="addInfo();">新增</a>
</div>

<div class="pathdiv">
	<c:if test="${ !empty parentSysmenu }"><a href="javascript:void(0);" onclick="listMenu('', '1111111111');" >全部菜单</a></c:if>
	<c:forEach items="${parentSysmenu }" var="sysmenu" varStatus="vs">
		>
		<a href="javascript:void(0);" onclick="listMenu('${sysmenu.syscode }', '${sysmenu.menu_id }');" <c:if test="${vs.count == fn:length(parentSysmenu) }">style="color:red;"</c:if>>${sysmenu.menu_name }</a>
	</c:forEach>
</div>

<c:if test="${!empty pageResult.list}">

<table width="100%" cellpadding="0" cellspacing="0" class="info_tab">
	<tr class="tab_th">
		<th width="5%"><input type="checkbox"></th>
		
		<th>菜单名称</th>
		
		<th>有效性</th>
		
		<th>排序</th>
		
		<th>修改</th>
		
		<th>查看下级</th>
	</tr>
	
	<c:forEach items="${pageResult.list}" var="item">

	<tr class="tab_td">
		<td><input type="checkbox" name="id" value="${item.menu_id}"></td>
		
		<td>${item.menu_name}</td>
		
		<td>
			<c:if test="${item.enabled == '0'}">有效</c:if>
			<c:if test="${item.enabled == '1'}">无效</c:if>
		</td>
		
		<td>${item.sort_no}</td>
		
		<td><a href="<%=basePath%>sysmenu/update.action?id=${item.menu_id}">修改</a></td>
		
		<td><a href="javascript:void(0);" onclick="listMenu('', ${item.menu_id});">查看下级</a></td>
	</tr>

	</c:forEach>

</table>

</c:if>
		
<div class="page">${pageBar}</div>

</sf:form>
            
</body>
</html>

