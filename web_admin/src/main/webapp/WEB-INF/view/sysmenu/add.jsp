<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<title>添加菜单</title>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
</head>
<body>

<div class="position">添加菜单</div>

<sf:form method="post" action="sysmenu/add.action" modelAttribute="sysmenu">

<table width="100%" cellpadding="0" cellspacing="8" class="add_tab tr td">

	
	<tr>
		<td class="addtab_tit">菜单名称<span>*</span></td>
		<td>
			<sf:input path="menu_name"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="menu_name" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">所属后台<span>&nbsp;</span></td>
		<td>
			<sf:hidden path="syscode"/>
			${sysmenu.para_key }
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="syscode" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">上级<span>&nbsp;</span></td>
		<td>
			<sf:hidden path="up_menu_id"/>
			${sysmenu.up_menu_name }
		</td>
	</tr>
	<tr>
		<td class="addtab_tit">排序<span>*</span></td>
		<td>
			<sf:input path="sort_no"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="sort_no" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">有效性<span>&nbsp;</span></td>
		<td>
			<sf:radiobutton path="enabled" value="0"/>有效
            <sf:radiobutton path="enabled" value="1"/>无效
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="enabled" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">链接<span>*</span></td>
		<td>
			<sf:input path="url"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="url" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">跳转类型<span>&nbsp;</span></td>
		<td>
			<sf:radiobutton path="target" value="_self"/>本页面
            <sf:radiobutton path="target" value="_blank"/>新页面
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="target" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">基础权限<span>&nbsp;</span></td>
		<td>
			<sf:textarea path="oper_basic_right" cssClass="txt4x1" />
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="oper_basic_right" cssClass="error"/></td></tr>
	
	
	<tr>
		<td class="addtab_tit"></td>
		<td>
			<button class="tab_btn" type="submit"/>提交</button>
		</td>
	</tr>
</table>
	<sf:hidden path="menu_level"/>	
</sf:form>

</body>
</html>

