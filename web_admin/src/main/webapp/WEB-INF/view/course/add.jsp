<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<title>添加课程管理</title>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
</head>
<body>

<div class="position">添加课程管理</div>

<sf:form method="post" action="course/add.action" modelAttribute="course">

<table width="100%" cellpadding="0" cellspacing="8" class="add_tab tr td">

	<tr>
		<td class="addtab_tit">课程名称<span>*</span></td>
		<td>
			<sf:input path="c_name"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="c_name" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">图片<span>*</span></td>
		<td>
			<input type="hidden" name="imgNumLimit" id="imgNumLimit" value="1"/>
	        <sf:hidden path="img_path" id="img_path"/>
			<input id="posfile" name="uploadifyfile" type="file" />
	        <div id="fileQueue"></div>
			<div class="img-lst"><ul id="imgView"></ul></div>
			<%@ include file="/WEB-INF/view/inc/uploadify/imgInc.jsp" %>
	        <script>
	        	uploadImgComponent('<%=basePath%>','imgNumLimit','posfile','fileQueue','img_path','imgView','course')
	        </script>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="img_path" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">课程类型<span>*</span></td>
		<td>
			<sf:select path="c_type" items="${courseTypeMap}"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="c_type" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">讲师<span>*</span></td>
		<td>
			<sf:input path="teacher"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="teacher" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">课程描述<span>&nbsp;</span></td>
		<td>
			<sf:textarea path="c_desc" class="txt4x1"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="c_desc" cssClass="error"/></td></tr>
	
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

