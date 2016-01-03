<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<title>添加分类属性</title>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
</head>
<body>

<div class="position">添加分类属性</div>

<sf:form method="post" action="categoryattr/add.action" modelAttribute="categoryAttr">

<table width="100%" cellpadding="0" cellspacing="8" class="add_tab tr td">

	
	<tr>
		<td class="addtab_tit">属性名称<span>*</span></td>
		<td>
			<sf:input path="attrName"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="attrName" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">所属分类<span>&nbsp;</span></td>
		<td>
			<c:set var="catAttr" value="" />
			<c:forEach items="${categorys }" var="category" varStatus="vs">
				<c:if test="${vs.index==0 }">
					${category.cat_name }
					<c:set var="catAttr" value="${category.cat_id }" />
				</c:if>
				<c:if test="${vs.index!=0 }">
					>${category.cat_name }
					<c:set var="catAttr" value="${catAttr },${category.cat_id }" />
				</c:if>
			</c:forEach>
			<input type="hidden" name="catAttr" value="${catAttr }" />
			<sf:hidden path="catId" />
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="catAttr" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">属性类型<span>*</span></td>
		<td>
			<sf:radiobutton path="attrType" value="0"/>单行文本框
			<sf:radiobutton path="attrType" value="2"/>单选按钮
            <sf:radiobutton path="attrType" value="3"/>复选框
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="attrType" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">是否必须<span>*</span></td>
		<td>
			<sf:radiobutton path="isMust" value="0"/>非必须
            <sf:radiobutton path="isMust" value="1"/>必须
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="isMust" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">排序号<span>*</span></td>
		<td>
			<sf:input path="sortNo"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="sortNo" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">属性值<span>&nbsp;</span></td>
		<td>
			<sf:textarea path="defaultVal" cssClass="txt4x1" />
			(多个值,请用半角 | 线隔开)
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="defaultVal" cssClass="error"/></td></tr>
	
	
	<tr>
		<td class="addtab_tit"></td>
		<td>
			<input type="hidden" name="moduleType" value="goods" />
			<button class="tab_btn" type="submit"/>提交</button>
		</td>
	</tr>
</table>

</sf:form>

</body>
</html>

