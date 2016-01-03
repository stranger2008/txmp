<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<title>修改会员等级</title>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
</head>
<body>

<div class="position">修改会员等级</div>

<sf:form method="post" action="userlevelset/update.action" modelAttribute="userlevelset">

<table width="100%" cellpadding="0" cellspacing="8" class="add_tab tr td">

	<tr>
		<td class="addtab_tit">用户等级<span>*</span></td>
		<td>
			${userlevelset.level_code }
		</td>
	</tr>
	<tr>
		<td class="addtab_tit">用户名<span>*</span></td>
		<td>
			<sf:input path="level_name"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="level_name" cssClass="error"/></td></tr>
	
	
	<tr>
		<td class="addtab_tit"></td>
		<td>
			<sf:hidden path="level_code"/>
			<button class="tab_btn" type="submit"/>提交</button>
		</td>
	</tr>
</table>

</sf:form>

</body>
</html>

