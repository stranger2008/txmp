<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<title>修改数据字典</title>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
</head>
<body>

<div class="position">修改数据字典</div>

<sf:form method="post" action="commpara/update.action" modelAttribute="commpara">

<table width="100%" cellpadding="0" cellspacing="8" class="add_tab tr td">

	
	<tr>
		<td class="addtab_tit">参数代码<span>*</span></td>
		<td>
			<sf:input path="para_code"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="para_code" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">参数名称<span>*</span></td>
		<td>
			<sf:input path="para_name"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="para_name" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">参数键<span>*</span></td>
		<td>
			<sf:input path="para_key"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="para_key" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">参数值<span>*</span></td>
		<td>
			<sf:input path="para_value"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="para_value" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">是否有效<span>&nbsp;</span></td>
		<td>
			<sf:radiobutton path="enabled" value="0"/>有效&nbsp;&nbsp;&nbsp;&nbsp;
			<sf:radiobutton path="enabled" value="1"/>无效
		</td>
	</tr>
	
	<tr><td class="addtab_tit"></td><td><sf:errors path="sort_no" cssClass="error"/></td></tr>
	<tr>
		<td class="addtab_tit">排序<span>*</span></td>
		<td>
			<sf:input path="sort_no"/>
		</td>
	</tr>
	
	<tr><td class="addtab_tit"></td><td><sf:errors path="enabled" cssClass="error"/></td></tr>
	
	
	<tr>
		<td class="addtab_tit"></td>
		<td>
			<sf:hidden path="para_id"/>
			<button class="tab_btn" type="submit"/>提交</button>
		</td>
	</tr>
</table>

</sf:form>

</body>
</html>

