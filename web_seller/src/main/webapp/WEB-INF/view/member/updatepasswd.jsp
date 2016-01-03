<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<title>密码修改</title>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
</head>
<body>

<div class="position">密码修改</div>

<sf:form method="post" action="member/updatepasswd.action" modelAttribute="updatepasswd">

<table width="100%" cellpadding="0" cellspacing="8" class="add_tab tr td">
	<tr>
		<td class="addtab_tit">旧密码<span>*</span></td>
		<td>
			<sf:password path="old_passwd"/>
		</td>
	</tr>
	
	<tr><td class="addtab_tit"></td><td><sf:errors path="old_passwd" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">新密码<span>*</span></td>
		<td>
			<sf:password path="new_passwd"/>
		</td>
	</tr>
	
	<tr><td class="addtab_tit"></td><td><sf:errors path="new_passwd" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">确认密码<span>*</span></td>
		<td>
			<sf:password path="sure_passwd"/>
		</td>
	</tr>
	
	<tr><td class="addtab_tit"></td><td><sf:errors path="sure_passwd" cssClass="error"/></td></tr>
	
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
