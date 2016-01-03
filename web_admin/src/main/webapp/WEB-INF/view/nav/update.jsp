<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<title>修改导航</title>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
</head>
<body>

<div class="position">修改导航</div>

<sf:form method="post" action="nav/update.action" modelAttribute="nav">

<table width="100%" cellpadding="0" cellspacing="8" class="add_tab tr td">

	
	<tr>
		<td class="addtab_tit">导航名称<span>*</span></td>
		<td>
			<sf:input path="nav_name"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="nav_name" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">链接地址<span>*</span></td>
		<td>
			<sf:input path="link_url" cssClass="w400"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="link_url" cssClass="error"/></td></tr>
	
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
			<sf:radiobutton path="isshow" value="0"/>显示
            <sf:radiobutton path="isshow" value="1"/>隐藏
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="isshow" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">打开方式<span>&nbsp;</span></td>
		<td>
			<sf:radiobutton path="isopen" value="_self"/>本页面
            <sf:radiobutton path="isopen" value="_blank"/>新页面
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="isopen" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">导航编码<span>&nbsp;</span></td>
		<td>
			<sf:input path="nav_code"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="nav_code" cssClass="error"/></td></tr>
	
	
	<tr>
		<td class="addtab_tit"></td>
		<td>
			<sf:hidden path="nav_post"/>
			<sf:hidden path="plat_type"/>
			<sf:hidden path="nav_id"/>
			<button class="tab_btn" type="submit"/>提交</button>
		</td>
	</tr>
</table>

</sf:form>

</body>
</html>

