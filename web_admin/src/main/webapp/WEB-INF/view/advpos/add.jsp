<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page   import="com.xingfugo.web.admin.common.ConstantUtil" %>
<html>
<head>
<title>添加广告位</title>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>

</head>
<body>

<div class="position">添加广告位</div>

<sf:form method="post" action="advpos/add.action" modelAttribute="advpos">

<table width="100%" cellpadding="0" cellspacing="8" class="add_tab tr td">

	
	<tr>
		<td class="addtab_tit">广告位名称<span>*</span></td>
		<td>
			<sf:input path="pos_name"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="pos_name" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">广告位类型<span>*</span></td>
		<td>
			<sf:select path="pos_type" >
				<sf:option value="">请选择</sf:option>
				<sf:options items="${posTypeMap }"></sf:options>
			</sf:select>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="pos_type" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">所属分类<span>*</span></td>
		<td>
			<span id="selectDivModel"></span>
			<script type="text/javascript">
				showSelectCascade("<%=basePath %>","category","selectDivModel","cat_attr","${advpos.cat_attr}","<%=ConstantUtil.ADV_MODULE_TYPE%>");
			</script>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="cat_attr" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">广告位介绍<span>&nbsp;</span></td>
		<td>
			<sf:input path="pos_desc" cssClass="w400"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="pos_desc" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">宽<span>&nbsp;</span></td>
		<td>
			<sf:input path="p_width" value="0"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="p_width" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">高<span>&nbsp;</span></td>
		<td>
			<sf:input path="p_height" value="0"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="p_height" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">示意图<span>&nbsp;</span></td>
		<td>
			<input type="hidden" name="imgNumLimit" id="imgNumLimit" value="1"/>
	        <sf:hidden path="img_path" id="img_path"/>
			<input id="posfile" name="uploadifyfile" type="file" />
	        <div id="fileQueue"></div>
			<div class="img-lst"><ul id="imgView"></ul></div>
			<%@ include file="/WEB-INF/view/inc/uploadify/imgInc.jsp" %>
	        <script>
	        	uploadImgComponent('<%=basePath%>','imgNumLimit','posfile','fileQueue','img_path','imgView','advpos')
	        </script>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="img_path" cssClass="error"/></td></tr>
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

