<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
<title>分类属性列表</title>
<style type="text/css">
.pathdiv{margin-top:10px;border-top:1px solid #cccccc;padding-top:10px;margin-bottom:10px;}
</style>
</head>
<body>

<div class="position">分类属性列表</div>

<sf:form action="categoryattr/index.action" id="queryForm" modelAttribute="categoryattrQueryForm">

<div class="searchdiv">
	<ul>
		
		<li>
			<span>属性名称：</span>
			<sf:input path="attrName"/>
		</li>
		<!-- 
		<li>
			<span>所属分类：</span>
			<sf:input path="catAttr"/>
		</li>
		 -->
		
		<li>
			<span>属性类型：</span>
			<sf:select path="attrType">
				<sf:option value="">全部</sf:option>
				<sf:option value="0">单行文本</sf:option>
				<sf:option value="2">单选按钮</sf:option>
				<sf:option value="3">复选框</sf:option>
			</sf:select>
		</li>
		
		<li>
			<span>是否必须：</span>
			<sf:select path="isMust">
				<sf:option value="">全部</sf:option>
				<sf:option value="1">必须</sf:option>
				<sf:option value="0">非必须</sf:option>
			</sf:select>
		</li>
		
	</ul>
</div>

<div class="clearfix"></div>

<div class="kjbutdiv">
	<a href="javascript:void(0);" id="deleteInfo">删除</a>
	<a href="<%=basePath%>categoryattr/add.action?catAttr=${categoryattrQueryForm.catAttr }">新增</a>
	<a href="javascript:void(0);" id="searchInfo">查询</a>
</div>

<div class="pathdiv">
	<sf:hidden path="catAttr" />
	<a href="${pageContext.request.contextPath }/category/goodsIndex.action">所属分类</a>
	<c:forEach items="${categorys }" var="category" varStatus="vs">
		>
		<a href="${pageContext.request.contextPath }/categoryattr/index.action?catAttr=${category.cat_id }" <c:if test="${vs.count == fn:length(categorys) }">style="color:red;"</c:if>>${category.cat_name }</a>
	</c:forEach>
</div>

<c:if test="${!empty pageResult.list}">

<table width="100%" cellpadding="0" cellspacing="0" class="info_tab">
	<tr class="tab_th">
		<th width="5%"><input type="checkbox"></th>
		
		<th>属性名称</th>
		
		<th>属性类型</th>
		
		<th>是否必须</th>
		
		<th>排序号</th>
		
		<th>修改</th>
	</tr>
	
	<c:forEach items="${pageResult.list}" var="item">

	<tr class="tab_td">
		<td><input type="checkbox" name="id" value="${item.attrId}"></td>
		
		<td>${item.attrName}</td>
		
		<td>
		<c:if test="${item.attrType == '0' }">单行文本框</c:if>
		<c:if test="${item.attrType == '1' }">多行文本框</c:if>
		<c:if test="${item.attrType == '2' }">单选按钮</c:if>
		<c:if test="${item.attrType == '3' }">复选框</c:if>
		</td>
		
		<td>
		<c:if test="${item.isMust == '1' }">必须</c:if>
		<c:if test="${item.isMust == '0' }">非必须</c:if>
		</td>
		
		<td>${item.sortNo}</td>
		
		<td><a href="<%=basePath%>categoryattr/update.action?id=${item.attrId}">修改</a></td>
	</tr>

	</c:forEach>

</table>

</c:if>
		
<div class="page">${pageBar}</div>

</sf:form>
            
</body>
</html>

