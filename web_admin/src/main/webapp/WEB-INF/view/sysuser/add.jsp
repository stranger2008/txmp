<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<title>添加用户</title>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
</head>
<body>

<div class="position">添加用户</div>

<sf:form method="post" action="sysuser/add.action" modelAttribute="sysuser">

<table width="100%" cellpadding="0" cellspacing="8" class="add_tab tr td">

	
	<tr>
		<td class="addtab_tit">用户名<span>*</span></td>
		<td>
			<sf:input path="user_name"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="user_name" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">用户笔名<span>&nbsp;</span></td>
		<td>
			<sf:input path="nike_name"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="nike_name" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">密码<span>*</span></td>
		<td>
			<sf:password path="passwd"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="passwd" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">用户角色<span>*</span></td>
		<td>
			<c:set value="${fn:split(sysuser.role_id, ',') }" var="rs" />
			<c:forEach items="${roles }" var="role">
			<input type="checkbox" name="role_id" value="${role.role_id }" style="height:12px;" 
				<c:forEach items="${rs }" var="r">
					<c:if test="${r == role.role_id }">
					checked="checked"
					</c:if>
				</c:forEach>
			 />${role.role_name }
			</c:forEach>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="role_id" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">真实姓名<span>&nbsp;</span></td>
		<td>
			<sf:input path="real_name"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="real_name" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">电子邮箱<span>&nbsp;</span></td>
		<td>
			<sf:input path="email"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="email" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">状态<span>&nbsp;</span></td>
		<td>
			<sf:radiobutton path="state" value="0"/>启用
            <sf:radiobutton path="state" value="1"/>禁用
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="state" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit"></td>
		<td>
			<sf:hidden path="user_type"/>
			<button class="tab_btn" type="submit"/>提交</button>
		</td>
	</tr>
</table>

</sf:form>

</body>
</html>

