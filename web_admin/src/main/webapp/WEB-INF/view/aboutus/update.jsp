<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page   import="com.xingfugo.web.admin.common.ConstantUtil" %>
<html>
<head>
<title>修改文章管理</title>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
<script type="text/javascript"src="inc/com/ckeditor_3.6/ckeditor.js"></script>
<script type="text/javascript">
	CKEDITOR.on( 'instanceReady', function( ev ){       
		this.config.filebrowserImageUploadUrl = '<%=basePath%>ckupload.action'; //固定路径
	});

 function save(){
 	var typeVal="";
 	$("select[name='select_cat_attr']").each(function(){
 		var val = $(this).val();
 		if(val!=null&&val!=''){
 		typeVal += val+",";
 		}
 	});
 	
 	if(typeVal==""){
 		$("#field_cat_attr").val("");
 	}
 	$("#updateAboutus").submit();
 }
</script>
</head>
<body>

<div class="position">修改文章管理</div>

<sf:form method="post" id="updateAboutus" action="aboutus/update.action" modelAttribute="aboutus">
<sf:hidden path="in_date"/>
<table width="100%" cellpadding="0" cellspacing="8" class="add_tab tr td">
	<tr>
		<td class="addtab_tit">标题<span>*</span></td>
		<td>
			<sf:input path="title" cssClass="w400"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="title" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">分类<span>*</span></td>
		<td>
			<span id="selectDivModel"></span>
			<script type="text/javascript">
			$(function(){
				showSelectCascade("<%=basePath %>","category","selectDivModel","cat_attr","${aboutus.cat_attr}","<%=ConstantUtil.ARTI_MODULE_TYPE%>");
			});
			</script>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="cat_attr" cssClass="error"/></td></tr>
 
	<tr>
		<td class="addtab_tit">内容<span>&nbsp;</span></td>
		<td>
			 <sf:textarea path="content"  id="contentEdit" cssClass="txt4x1 ckeditor" htmlEscape="false"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="content" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit"></td>
		<td>
			<sf:hidden path="info_id"/>
			<button class="tab_btn" type="button" onclick="save();"/>提交</button>
		</td>
	</tr>
</table>

</sf:form>

</body>
</html>

