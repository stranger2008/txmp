<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page   import="com.xingfugo.web.admin.common.ConstantUtil" %>
<html>
<head>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
<title>商品品牌列表</title>
<style type="text/css">
 
#cat_attr_Div{
	width:350px;
	display:inline-block;
	text-align:left;
}
.searchdiv select{
	width:100px;
}
</style>
</head>
<body>

<div class="position">商品品牌列表</div>

<sf:form action="goodsbrand/index.action" id="queryForm" modelAttribute="goodsbrandQueryForm">

<div class="searchdiv">
	<ul>
		
		<li>
			<span>品牌名称：</span>
			<sf:input path="brand_name"/>
		</li>
		
		<li style="width:45%">
			<span>品牌分类：</span>
			 
			<span id="cat_attr_Div"></span>
			<script type="text/javascript">
			$(function(){
				showSelectCascade("<%=basePath %>","category","cat_attr_Div","goods_attr","${goodsbrandQueryForm.goods_attr}","<%=ConstantUtil.GOODS_MODULE_TYPE%>");
			});
			</script>			
		</li>			
			
		<li>
			<span>状态:</span>
			<sf:select path="info_state">
				<sf:option value="">全部</sf:option>
				<sf:option value="0">正常</sf:option>
				<sf:option value="1">禁止</sf:option>
			</sf:select>
		</li>
				
	</ul>
</div>

<div class="clearfix"></div>

<div class="kjbutdiv">
	<a href="javascript:void(0);" id="deleteInfo">删除</a>
	<a href="<%=basePath%>goodsbrand/add.action">新增</a>
	<a href="javascript:void(0);" id="searchInfo">查询</a>
</div>

<c:if test="${!empty pageResult.list}">

<table width="100%" cellpadding="0" cellspacing="0" class="info_tab">
	<tr class="tab_th">
		<th width="5%"><input type="checkbox"></th>
		
		<th>品牌名称</th>
		
		<th>品牌分类</th>
		
		<th>排序</th>
		
		<th>状态</th>
		
		<th>修改</th>
	</tr>
	
	<c:forEach items="${pageResult.list}" var="item">

	<tr class="tab_td">
		<td><input type="checkbox" name="id" value="${item.brand_id}"></td>
		
		<td>${item.brand_name}</td>
		
		<td>${item.cat_attr_names}</td>
		
		<td>${item.sort_no}</td>
		
		<td>
			<c:if test="${item.info_state == 0}">
				正常
			</c:if>
			<c:if test="${item.info_state == 1}">
				禁止
			</c:if>
		</td>
		
		<td><a href="<%=basePath%>goodsbrand/update.action?id=${item.brand_id}">修改</a></td>
	</tr>

	</c:forEach>

</table>

</c:if>
		
<div class="page">${pageBar}</div>

</sf:form>
            
</body>
</html>

