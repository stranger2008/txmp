<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<title>修改个人会员</title>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
</head>
<body>

<div class="position">修改个人会员</div>

<sf:form method="post" action="memberuser/update.action" modelAttribute="memberuser">

<table width="100%" cellpadding="0" cellspacing="8" class="add_tab tr td">

	<tr>
		<td class="addtab_tit">用户等级<span>&nbsp;</span></td>
		<td>
			<sf:select path="user_level" items="${memlist}" />
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="user_level" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">用户名<span>*</span></td>
		<td>
			<sf:input path="user_name"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="user_name" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">状态<span>&nbsp;</span></td>
		<td>
			<sf:radiobutton path="state_code" value="0" />正常
			<sf:radiobutton path="state_code" value="1"/>禁用
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="state_code" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">Email<span>&nbsp;</span></td>
		<td>
			<sf:input path="email"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="email" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">真实姓名<span>&nbsp;</span></td>
		<td>
			<sf:input path="real_name"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="real_name" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">性别<span>&nbsp;</span></td>
		<td>
			<sf:radiobutton path="sex" value="0" />男
			<sf:radiobutton path="sex" value="1"/>女
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="sex" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">手机<span>&nbsp;</span></td>
		<td>
			<sf:input path="cellphone" />
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="cellphone" cssClass="error"/></td></tr>
	
	<!--		 
	<tr>
		<td class="addtab_tit">注册时间<span>&nbsp;</span></td>
		<td>
			<sf:input path="login_time" readonly="true"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="login_time" cssClass="error"/></td></tr>
	 -->
	
	
	<tr>
		<td class="addtab_tit"></td>
		<td>
			<sf:hidden path="user_id"/>
			<sf:hidden path="passwd"/>
			<button class="tab_btn" type="submit"/>提交</button>
		</td>
	</tr>
</table>

</sf:form>

</body>
</html>

