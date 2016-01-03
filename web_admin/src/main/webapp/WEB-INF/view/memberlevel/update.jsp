<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<title>修改等级配置</title>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
</head>
<body>

<div class="position">修改等级配置</div>

<sf:form method="post" action="memberlevel/update.action" modelAttribute="memberlevel">

<table width="100%" cellpadding="0" cellspacing="8" class="add_tab tr td">

	<tr>
		<td class="addtab_tit">级别编码<span>&nbsp;</span></td>
		<td>
			${memberlevel.level_code }
		</td>
	</tr>
	
	<tr>
		<td class="addtab_tit">级别名称<span>*</span></td>
		<td>
			<sf:input path="level_name"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="level_name" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">备注<span>&nbsp;</span></td>
		<td>
			<sf:textarea path="remark" cssClass="txt4x1" />
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="remark" cssClass="error"/></td></tr>
	
	
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

