<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
<title>发货地址列表</title>
<script type="text/javascript">
$(document).ready(function() {
	$("#deleteInfo").click(function(){
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
				actionVal = actionVal.replace("index","delete");
				$("#queryForm").attr("action",actionVal);
				$("#queryForm").submit();
			}
		}
	});
	$('#allChk').click(function(){
		var chk = $(this).attr('checked');
		if(chk){
			$('[name=id]').attr('checked', true);
		}
		else{
			$('[name=id]').removeAttr('checked');
		}
	});
});

</script>

</head>
<body>

<div class="position">发货地址列表</div>

<sf:form action="selleraddr/index.action" id="queryForm" modelAttribute="selleraddrQueryForm">

<div class="searchdiv">
	<ul>
		
	</ul>
</div>

<div class="clearfix"></div>

<div class="kjbutdiv">
	<a href="<%=basePath%>selleraddr/add.action">新增</a>
	<a href="javascript:void(0);" id="deleteInfo" >删除</a>
</div>

<c:if test="${!empty pageResult.list}">

<table width="100%" cellpadding="0" cellspacing="0" class="info_tab">
	<tr class="tab_th">
		<th width="5%"><input type="checkbox"  id="allChk"></th>
		
		<th>收货人姓名</th>
		
		<th>地址</th>
		
		<th>手机号码</th>
		
		<th>修改</th>
	</tr>
	
	<c:forEach items="${pageResult.list}" var="item">

	<tr class="tab_td">
		<td><input type="checkbox" name="id" value="${item.addr_id}"></td>
		
		<td>${item.cust_name}</td>
		
		<td>${item.area_name_str}</td>
		
		<td>${item.cell_phone}</td>
		
		<td><a href="<%=basePath%>selleraddr/update.action?id=${item.addr_id}">修改</a></td>
	</tr>

	</c:forEach>

</table>

</c:if>
		
<div class="page">${pageBar}</div>

</sf:form>
            
</body>
</html>

