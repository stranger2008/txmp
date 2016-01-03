<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<title>修改地区管理</title>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
</head>
<body>

<div class="position">修改地区管理</div>

<sf:form method="post" action="area/update.action" modelAttribute="area">

<table width="100%" cellpadding="0" cellspacing="8" class="add_tab tr td">

	
	<tr>
		<td class="addtab_tit">地区名称<span>*</span></td>
		<td>
			<sf:input path="area_name"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="area_name" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">英文名称<span>*</span></td>
		<td>
			<sf:input path="en_name"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="en_name" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">上级地区<span>*</span></td>
		<td>
			<sf:hidden path="up_area_id"/>
			${parent_area_name}
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="up_area_id" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">地区级别<span>*</span></td>
		<td>
			<sf:input path="area_level"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="area_level" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">排序<span>*</span></td>
		<td>
			<sf:input path="sort_no"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="sort_no" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">是否为城市<span>*</span></td>
		<td>
			<sf:radiobutton path="is_city" value="2" />国家&nbsp;&nbsp;&nbsp;&nbsp;
			<sf:radiobutton path="is_city" value="1" />城市&nbsp;&nbsp;&nbsp;&nbsp;
			<sf:radiobutton path="is_city" value="0"/>其他
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="is_city" cssClass="error"/></td></tr>
	<!-- 
		<tr>
		<td class="addtab_tit">seotitle<span>&nbsp;</span></td>
		<td>
			<sf:input path="seotitle"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="seotitle" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">seokeyword<span>&nbsp;</span></td>
		<td>
			<sf:input path="seokeyword"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="seokeyword" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">seodesc<span>&nbsp;</span></td>
		<td>
			<sf:input path="seodesc"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="seodesc" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">area_have<span>&nbsp;</span></td>
		<td>
			<sf:input path="area_have"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="area_have" cssClass="error"/></td></tr>
	 -->
	
	
	<tr>
		<td class="addtab_tit">是否开通子站<span>&nbsp;</span></td>
		<td>
			<sf:radiobutton path="is_open" value="0"/>是&nbsp;&nbsp;&nbsp;&nbsp;
			<sf:radiobutton path="is_open" value="1"/>否
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="is_open" cssClass="error"/></td></tr>
	
	
	<tr>
		<td class="addtab_tit"></td>
		<td>
			<sf:hidden path="area_id"/>
			<button class="tab_btn" type="submit"/>提交</button>
		</td>
	</tr>
</table>

</sf:form>

</body>
</html>

