<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<title>添加邮件发送记录</title>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
</head>
<body>

<div class="position">添加邮件发送记录</div>

<sf:form method="post" action="emailhistory/add.action" modelAttribute="emailhistory">

<table width="100%" cellpadding="0" cellspacing="8" class="add_tab tr td">

	
	<tr>
		<td class="addtab_tit">email_attr<span>*</span></td>
		<td>
			<sf:input path="email_attr"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="email_attr" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">title<span>*</span></td>
		<td>
			<sf:input path="title"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="title" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">content<span>*</span></td>
		<td>
			<sf:input path="content"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="content" cssClass="error"/></td></tr>
	
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

