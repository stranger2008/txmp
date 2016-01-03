<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<title>添加模板</title>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
</head>
<body>

<div class="position">添加模板</div>

<sf:form method="post" action="sms_email_template/add.action" modelAttribute="sms_email_template">

<table width="100%" cellpadding="0" cellspacing="8" class="add_tab tr td">

	
	<tr>
		<td class="addtab_tit">temp_name<span>*</span></td>
		<td>
			<sf:input path="temp_name"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="temp_name" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">temp_con<span>*</span></td>
		<td>
			<sf:input path="temp_con"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="temp_con" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">tag_desc<span>*</span></td>
		<td>
			<sf:input path="tag_desc"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="tag_desc" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">temp_type<span>*</span></td>
		<td>
			<sf:input path="temp_type"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="temp_type" cssClass="error"/></td></tr>
	
	
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

