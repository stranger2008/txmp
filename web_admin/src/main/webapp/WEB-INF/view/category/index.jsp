<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<html>
<head>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
<title>${typeName }分类列表</title>
<script type="text/javascript">
	 //商品分类导航
	function linkToInfo(cat_id){
		$("#parent_id").val(cat_id);
		$("#queryForm").submit();
	}
	//查看下级分类
	function selNextLevel(cat_id){
		$("#parent_id").val(cat_id);
		$("#cat_name").val("");
	 	$("#queryForm").submit();
	}
	//修改分类是否显示
	function updateDisplay(isDisplay){
		var ret = 0;
		$(".tab_td input[type='checkbox']").each(function(){
			if($(this).attr("checked") == true){
				ret = 1;
			}
		});
		if(ret == 0){
			alert("请至少选择一条信息");
		}else{
			$("#displayStatus").val(isDisplay);
			var actionVal = $("#queryForm").attr("action");
			actionVal = actionVal.replace("Index","DisplayStatus");
			$("#queryForm").attr("action",actionVal);
			$("#queryForm").submit();
		}
	}

	 
	$(function(){
		//删除分类
		$("#deleteCatInfo").click(function(){
			var ret = 0;
			$(".tab_td input[type='checkbox']").each(function(){
				if($(this).attr("checked") == true){
					ret = 1;
				}
			});
			if(ret == 0){
				alert("请至少选择一条信息");
			}else{
				if(confirm("删除会同时删除子分类，您确定删除已选择的信息吗？")){
					var actionVal = $("#queryForm").attr("action");
					actionVal = actionVal.replace("Index","Delete");
					$("#queryForm").attr("action",actionVal);
					$("#queryForm").submit();
				}
			}
		}); 
	});
	
</script>
</head>
<body>

<div class="position">${typeName }分类列表</div>

<sf:form action="category/${categoryQueryForm.module_type}Index.action" id="queryForm" modelAttribute="categoryQueryForm">

<div class="searchdiv">
	<ul>
		
		<li>
			<span>分类名称：</span>
			<sf:input path="cat_name"/>
		</li>
		
	</ul>
</div>

<div class="clearfix"></div>

<div class="kjbutdiv">
	<a href="javascript:void(0);" id="deleteCatInfo">删除</a>
	<a href="javascript:void(0);" onclick="updateDisplay('show')">显示</a>
	<a href="javascript:void(0);" onclick="updateDisplay('hide')">不显示</a>
	<a href="<%=basePath%>category/${categoryQueryForm.module_type}Add.action?up_cat_id=${categoryQueryForm.up_cat_id}">新增</a>
	<a href="javascript:void(0);" id="searchInfo">查询</a>
</div>
 
<div style="border-top:1px solid #cccccc ; padding:5px 0 ; " >
<sf:hidden path="up_cat_id" id="parent_id"/>
<sf:hidden path="module_type"/>
<input type="hidden" name="isDisplay" id="displayStatus"/> 

		<c:forEach items="${parentCatList}" var="item" varStatus="status">
		<span style="width:100px;"> 
			<a href="javascript:void(0);"  onclick="linkToInfo('${item.cat_id}');" 
			<c:if test="${item.cat_id==categoryQueryForm.up_cat_id}">style="color:red;"</c:if> >${item.cat_name}</a>
			<c:if test="${!status.last}"> > </c:if>
		</span>
		</c:forEach>
	 
</div>
 
<c:if test="${!empty pageResult.list}">

<table width="100%" cellpadding="0" cellspacing="0" class="info_tab">
	<tr class="tab_th">
		<th width="5%"><input type="checkbox"></th>
		
		<th>分类名称</th>
		
		<th>排序</th>
		
		<th>是否显示</th>
		
		<th>查看下级</th>
		
		<c:if test="${categoryQueryForm.module_type == 'goods'}">
		<th>属性管理</th>
		</c:if>
		
		<th>修改</th>
	</tr>
	
	<c:forEach items="${pageResult.list}" var="item">

	<tr class="tab_td">
		<td><input type="checkbox" name="id" value="${item.cat_id}"></td>
		
		<td>${item.cat_name}</td>
		
		<td>${item.sort_no}</td>
		<td>
		<c:if test="${item.is_display==0}">是</c:if>
		<c:if test="${item.is_display==1}">否</c:if>
		</td>
		
		<td><a href="javascript:void(0);" onclick="selNextLevel('${item.cat_id}');">查看下级</a></td>
		
		<c:if test="${categoryQueryForm.module_type == 'goods'}">
		<td><a href="${pageContext.request.contextPath }/categoryattr/index.action?catAttr=${item.cat_id }">属性管理</a></td>
		</c:if>
		
		<td><a href="<%=basePath%>category/${item.module_type}Update.action?id=${item.cat_id}">修改</a></td>
	</tr>

	</c:forEach>

</table>

</c:if>
		
<div class="page">${pageBar}</div>

</sf:form>
            
</body>
</html>

