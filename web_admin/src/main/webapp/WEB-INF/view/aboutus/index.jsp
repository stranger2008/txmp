<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page   import="com.xingfugo.web.admin.common.ConstantUtil" %>
<html>
<head>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
<title>文章管理列表</title>
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

<div class="position">文章管理列表</div>

<sf:form action="aboutus/index.action" id="queryForm" modelAttribute="aboutusQueryForm">

<div class="searchdiv">
	<ul>
		
		<li>
			<span>标题：</span>
			<sf:input path="title"/>
		</li>
		
		<li style="width:60%">
			<span>分类：</span>
			 
			<span id="cat_attr_Div"></span>
			<script type="text/javascript">
			$(function(){
				showSelectCascade("<%=basePath %>","category","cat_attr_Div","cat_attr","${aboutusQueryForm.cat_attr}","<%=ConstantUtil.ARTI_MODULE_TYPE%>");
			});
			</script>
			
		</li>
		
	</ul>
</div>

<div class="clearfix"></div>

<div class="kjbutdiv">
	<a href="javascript:void(0);" id="deleteInfo">删除</a>
	<a href="<%=basePath%>aboutus/add.action">新增</a>
	<a href="<%=basePath%>category/articleIndex.action">分类管理</a>
	<a href="javascript:void(0);" id="searchInfo">查询</a>
</div>

<c:if test="${!empty pageResult.list}">

<table width="100%" cellpadding="0" cellspacing="0" class="info_tab">
	<tr class="tab_th">
		<th width="5%"><input type="checkbox"></th>
		
		<th>标题</th>
		
		<th>分类</th>
		
		<th>发布时间</th>
		
		<th>修改</th>
	</tr>
	
	<c:forEach items="${pageResult.list}" var="item">

	<tr class="tab_td">
		<td><input type="checkbox" name="id" value="${item.info_id}"></td>
		
		<td>${item.title}</td>
		
		<td>${item.cat_attr_names}</td>
		
		<td>
		<fmt:formatDate value="${item.in_date}" pattern="yyyy-MM-dd HH:mm:ss"/>
		</td>
		
		<td><a href="<%=basePath%>aboutus/update.action?id=${item.info_id}">修改</a></td>
	</tr>

	</c:forEach>

</table>

</c:if>
		
<div class="page">${pageBar}</div>

</sf:form>
            
</body>
</html>

