<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<title>添加友情链接</title>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
</head>
<body>

<div class="position">添加友情链接</div>

<sf:form method="post" action="link/add.action" modelAttribute="link">

<table width="100%" cellpadding="0" cellspacing="8" class="add_tab tr td">
	<tr>
		<td class="addtab_tit">链接名称<span>*</span></td>
		<td>
			<sf:input path="link_name"/>
		</td>
	</tr>
	
	<tr><td class="addtab_tit"></td><td><sf:errors path="link_name" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">链接地址<span>*</span></td>
		<td>
			<sf:input path="url" cssClass="w400"/>
		</td>
	</tr>
	
	<tr><td class="addtab_tit"></td><td><sf:errors path="url" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">排序<span>&nbsp;</span></td>
		<td>
			<sf:input path="sort_no"/>
		</td>
	</tr>
	
	<tr><td class="addtab_tit"></td><td><sf:errors path="sort_no" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">是否显示<span>&nbsp;</span></td>
		<td>
			<sf:radiobutton path="is_display" value="0"/>显示
            <sf:radiobutton path="is_display" value="1"/>隐藏
		</td>
	</tr>
	
	<tr><td class="addtab_tit"></td><td><sf:errors path="is_display" cssClass="error"/></td></tr>
	
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
