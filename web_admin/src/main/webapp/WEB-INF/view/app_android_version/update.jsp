<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<title>修改手机端版本</title>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
</head>
<body>

<div class="position">修改手机端版本</div>

<sf:form method="post" action="app_android_version/update.action" modelAttribute="app_android_version">

<table width="100%" cellpadding="0" cellspacing="8" class="add_tab tr td">

	
	<tr>
		<td class="addtab_tit">版本号<span>*</span></td>
		<td>
			<sf:input path="ver_code"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="ver_code" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">版本名称<span>*</span></td>
		<td>
			<sf:input path="ver_name"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="ver_name" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">版本所属包<span>*</span></td>
		<td>
			<sf:input path="pack" size="35"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="pack" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">下载地址<span>*</span></td>
		<td>
			<sf:input path="download_url" size="50"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="download_url" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">变更日志<span>&nbsp;</span></td>
		<td>
			<sf:textarea path="change_log" rows="6" cols="55"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="change_log" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit"></td>
		<td>
			<sf:hidden path="id"/>
			<sf:hidden path="publish_time"/>
			<button class="tab_btn" type="submit"/>提交</button>
		</td>
	</tr>
</table>

</sf:form>

</body>
</html>

