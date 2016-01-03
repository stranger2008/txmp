<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page   import="com.xingfugo.web.admin.common.ConstantUtil" %>
<html>
<head>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
<title>广告管理</title>
<style type="text/css">
 
#cat_attr_Div{
	width:310px;
	display:inline-block;
	text-align:left;
}
.searchdiv select{
	width:100px;
} 
</style>
<script type="text/javascript">

$(function(){
		//删除分类
		$("#deleteAdvposInfo").click(function(){
			var ret = 0;
			$(".tab_td input[type='checkbox']").each(function(){
				if($(this).attr("checked") == true){
					ret = 1;
				}
			});
			if(ret == 0){
				alert("请至少选择一条信息");
			}else{
				if(confirm("删除会同时删除广告位下的广告信息，您确定删除已选择的信息吗？")){
					var actionVal = $("#queryForm").attr("action");
					actionVal = actionVal.replace("index","delete");
					$("#queryForm").attr("action",actionVal);
					$("#queryForm").submit();
				}
			}
		}); 
	});
</script>
</head>
<body>

<div class="position">广告位管理列表</div>

<sf:form action="advpos/index.action" id="queryForm" modelAttribute="advposQueryForm">

<div class="searchdiv">
	<ul>
		
		<li>
			<span>广告位名称：</span>
			<sf:input path="pos_name"/>
		</li>
		
		<li style="width:420px;">
			<span>分类：</span>
			<span id="cat_attr_Div"></span>
			<script type="text/javascript">
			$(function(){
				showSelectCascade("<%=basePath %>","category","cat_attr_Div","cat_attr","${advposQueryForm.cat_attr}","<%=ConstantUtil.ADV_MODULE_TYPE%>");
			});
			</script>
			
		</li>
		
		<li>
			<span>类型：</span>
			<sf:select path="pos_type" >
				<sf:option value="">请选择</sf:option>
				<sf:options items="${posTypeMap }"></sf:options>
			</sf:select>
			
		</li>
		
	</ul>
</div>

<div class="clearfix"></div>

<div class="kjbutdiv">
	<a href="javascript:void(0);" id="deleteAdvposInfo">删除</a>
	<a href="<%=basePath%>advpos/add.action">新增</a>
	<a href="<%=basePath%>category/advIndex.action">分类管理</a>
	<a href="javascript:void(0);" id="searchInfo">查询</a>
</div>

<c:if test="${!empty pageResult.list}">

<table width="100%" cellpadding="0" cellspacing="0" class="info_tab">
	<tr class="tab_th">
		<th width="5%"><input type="checkbox"></th>
		
		<th>广告位标识</th>
		
		<th>分类</th>
		
		<th>广告位名称</th>
		
		<th>类型</th>
		
		<th>宽*高（px）</th>
		
		<th>排序</th>
		
		<th>发布时间</th>
		
		<th>修改</th>
		
		<th>查看广告</th>
	</tr>
	
	<c:forEach items="${pageResult.list}" var="item">

	<tr class="tab_td">
		<td><input type="checkbox" name="id" value="${item.pos_id}"></td>
		
		<td>${item.pos_id}</td>
		
		<td>${item.cat_attr_names}</td>
		
		<td>${item.pos_name}</td>
		
		<td>${item.pos_type_name}</td>
		
		<td>${item.p_width}*${item.p_height}</td>
		
		<td>${item.sort_no}</td>
		
		<td>
		<fmt:formatDate value="${item.in_date}" pattern="yyyy-MM-dd"/>
		</td>
		
		<td><a href="<%=basePath%>advpos/update.action?id=${item.pos_id}">修改</a></td>
		<td><a href="<%=basePath%>advinfo/index.action?pos_id=${item.pos_id}">查看广告</a></td>
	</tr>

	</c:forEach>

</table>

</c:if>
		
<div class="page">${pageBar}</div>

</sf:form>
            
</body>
</html>

