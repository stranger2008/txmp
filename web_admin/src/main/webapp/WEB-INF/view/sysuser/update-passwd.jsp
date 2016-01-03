<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<title>修改用户</title>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
</head>
<body>

<div class="position">重置用户密码</div>

<sf:form method="post" action="sysuser/update-passwd.action" modelAttribute="sysuser">

<table width="100%" cellpadding="0" cellspacing="8" class="add_tab tr td">

	
	<tr>
		<td class="addtab_tit">用户名<span>&nbsp;</span></td>
		<td>
			${sysuser.user_name }
		</td>
	</tr>
	
	<tr>
		<td class="addtab_tit">密码<span>*</span></td>
		<td>
			<sf:password path="passwd"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="passwd" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit"></td>
		<td>
			<sf:hidden path="user_name"/>
			<sf:hidden path="role_id"/>
			<sf:hidden path="state"/>
			<sf:hidden path="user_type"/>
			<sf:hidden path="user_id"/>
			<button class="tab_btn" type="submit"/>提交</button>
		</td>
	</tr>
</table>

</sf:form>

</body>
</html>

