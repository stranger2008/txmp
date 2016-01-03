<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<title>添加商家自定义分类</title>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
</head>
<body>

<div class="position">添加商家自定义分类</div>

<sf:form method="post" action="membercat/add.action" modelAttribute="membercat">

<table width="100%" cellpadding="0" cellspacing="8" class="add_tab tr td">

	
	<tr>
		<td class="addtab_tit">cat_name<span>*</span></td>
		<td>
			<sf:input path="cat_name"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="cat_name" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">cust_id<span>*</span></td>
		<td>
			<sf:input path="cust_id"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="cust_id" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">up_cat_id<span>*</span></td>
		<td>
			<sf:input path="up_cat_id"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="up_cat_id" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">cat_level<span>*</span></td>
		<td>
			<sf:input path="cat_level"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="cat_level" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">sort_no<span>*</span></td>
		<td>
			<sf:input path="sort_no"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="sort_no" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">is_display<span>*</span></td>
		<td>
			<sf:input path="is_display"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="is_display" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">in_date<span>*</span></td>
		<td>
			<sf:input path="in_date"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="in_date" cssClass="error"/></td></tr>
	
	
	<tr>
		<td class="addtab_tit"></td>
		<td>
			<button class="tab_btn" type="submit"/>提交</button>
		</td>
	</tr>
</table>

</sf:form>

</body>
</html>

