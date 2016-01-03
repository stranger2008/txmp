<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<title>修改模板</title>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
<script type="text/javascript"src="inc/com/ckeditor_3.6/ckeditor.js"></script>
<script type="text/javascript">
　$(function (){
	CKEDITOR.replace('contentEdit',
	{
	toolbar :
		[
		//加粗     斜体，     下划线      穿过线      下标字        上标字
		['Bold','Italic','Underline','Strike','Subscript','Superscript'],
		//数字列表          实体列表            减小缩进    增大缩进
		['NumberedList','BulletedList','-','Outdent','Indent'],
		//左对齐             居中对齐          右对齐          两端对齐
		['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock'],
		//超链接 取消超链接 锚点
		['Link','Unlink','Anchor'],
		//图片    flash    表格       水平线            表情       特殊字符        分页符
		['Image','Flash','Table','HorizontalRule','Smiley','SpecialChar','PageBreak'],
		'/',
		//样式       格式      字体    字体大小
		['Styles','Format','Font','FontSize'],
		//文本颜色     背景颜色
		['TextColor','BGColor'],
		//全屏           显示区块			源码
		['Maximize', 'ShowBlocks','-','Source']
		]
	}
	);
	
 });
</script>
<style>
	#cke_contentEdit{width:700px;}
</style>
</head>
<body>

<sf:form method="post" action="sms_email_template/update.action" modelAttribute="sms_email_template">
<c:if test="${sms_email_template.temp_type == '0'}">
		<div class="position">修改邮件模板</div>
</c:if>
<c:if test="${sms_email_template.temp_type == '1'}">
		<div class="position">修改短信模板</div>
</c:if>
<div class="searchdiv">
	<ul>
		<c:if test="${sms_email_template.temp_type == '0'}">
			<li style="width:100px;"><a href="sms_email_template/sms_template_index.action" style="color:red;">短信模板管理</a></li>
			<li style="width:100px;"><a href="sms_email_template/email_template_index.action" >邮件模板管理</a></li>
		</c:if>
		<c:if test="${sms_email_template.temp_type == '1'}">
			<li style="width:100px;"><a href="sms_email_template/sms_template_index.action">短信模板管理</a></li>
			<li style="width:100px;"><a href="sms_email_template/email_template_index.action" style="color:red;">邮件模板管理</a></li>
		</c:if>
		
		<li style="width:100px;"><a href="smshistory/index.action" >短信发送记录</a></li>
		<li style="width:100px;"><a href="emailhistory/index.action" >邮件发送记录</a></li>
	</ul>
</div>
<div class="clearfix"></div>
<div class="kjbutdiv"></div>
<div class="clearfix"></div>

<table width="100%" cellpadding="0" cellspacing="8" class="add_tab tr td">

	<tr>
		<td class="addtab_tit">模板编码<span>&nbsp;</span></td>
		<td>
			${sms_email_template.temp_code}<sf:hidden path="temp_type"/>
		</td>
	</tr>
	<tr>
		<td class="addtab_tit">模板名称<span>*</span></td>
		<td>
			<sf:input path="temp_name" maxlength="25"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="temp_name" cssClass="error"/></td></tr>
	
	
	<tr>
		<td class="addtab_tit">模板内容<span>*</span></td>
		<td>
			<c:if test="${sms_email_template.temp_type == '0'}">
				<sf:textarea path="temp_con" cssClass="txt4x1" />
			</c:if>
			<c:if test="${sms_email_template.temp_type == '1'}">
				<sf:textarea path="temp_con"  id="contentEdit" class="txt4x1" htmlEscape="false"/>
			</c:if>
		</td>
	</tr>
	
	
	
	<tr><td class="addtab_tit"></td><td><sf:errors path="temp_con" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">标签解释<span>*</span></td>
		<td>
			<sf:textarea path="tag_desc" cssClass="txt4x1" />
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="tag_desc" cssClass="error"/></td></tr>	
	
	<tr>
		<td class="addtab_tit"></td>
		<td>
			<sf:hidden path="temp_code"/>
			<button class="tab_btn" type="submit"/>提交</button>
		</td>
	</tr>
</table>

</sf:form>

</body>
</html>

