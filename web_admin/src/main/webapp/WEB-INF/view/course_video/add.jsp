<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<title>添加课程视频管理</title>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
</head>
<body>

<div class="position">添加课程视频管理</div>

<sf:form method="post" action="course_video/add.action" modelAttribute="course_video">

<table width="100%" cellpadding="0" cellspacing="8" class="add_tab tr td">

	<sf:hidden path="c_id"/>
	
	<tr>
		<td class="addtab_tit">视频名称<span>*</span></td>
		<td>
			<sf:input path="video_name"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="video_name" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">视频地址<span>*</span></td>
		<td>
			<sf:input path="video_path" class="w400"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="video_path" cssClass="error"/></td></tr>
	
	
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

