<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<title>修改会员密码</title>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
</head>
<body>

<div class="position">修改会员密码</div>

<sf:form method="post" action="memberuser/updatepwd.action" modelAttribute="memberuser">

<table width="100%" cellpadding="0" cellspacing="8" class="add_tab tr td">
	
		<tr>
		<td class="addtab_tit">用户名<span>&nbsp;</span></td>
		<td>
			${memberuser.user_name }
		</td>
	</tr>
	
	<tr>
		<td class="addtab_tit">新密码<span>*</span></td>
		<td>
			<sf:password path="passwd"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="passwd" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit"></td>
		<td>
			<sf:hidden path="user_name"/>
			<sf:hidden path="cellphone"/>
			<sf:hidden path="state_code"/>
			<sf:hidden path="email"/>
			<sf:hidden path="real_name"/>
			<sf:hidden path="sex"/>
			<sf:hidden path="login_time"/>
			<sf:hidden path="user_level"/>
			<sf:hidden path="user_id"/>
			<button class="tab_btn" type="submit"/>提交</button>
		</td>
	</tr>
</table>

</sf:form>

</body>
</html>

