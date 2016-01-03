<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<title>修改广告信息</title>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
<script type="text/javascript" src="<%=basePath%>inc/com/calendar/WdatePicker.js"></script> 
</head>
<body>

<div class="position">修改广告信息</div>

<sf:form method="post" action="advinfo/update.action" modelAttribute="advinfo">

<sf:hidden path="pos_id"/>

<table width="100%" cellpadding="0" cellspacing="8" class="add_tab tr td">

	<tr>
		<td class="addtab_tit">广告名称<span>*</span></td>
		<td>
			<sf:input path="adv_name" cssClass="w400"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="adv_name" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">广告介绍<span>&nbsp;</span></td>
		<td>
			<sf:input path="adv_desc"  cssClass="w400"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="adv_desc" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">广告代码<span>&nbsp;</span></td>
		<td>
			<sf:input path="adv_code" cssClass="w400"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="adv_code" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">上传图片<span>&nbsp;</span></td>
		<td>
			<input type="hidden" name="imgNumLimit" id="imgNumLimit" value="1"/>
			<sf:hidden path="img_path"/>
			<input id="file_adv" name="upload_file" type="file" />
			<div id="fileQueue"></div>
			<div class="img-lst"><ul id="imgView"></ul></div>
			<%@ include file="/WEB-INF/view/inc/uploadify/imgInc.jsp" %>
	        <script>
	        	$(function(){
	        		uploadImgComponent('<%=basePath%>','imgNumLimit','file_adv','fileQueue','img_path','imgView','advinfo');
	        	})
	        </script>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="img_path" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">flash地址<span>&nbsp;</span></td>
		<td>
			<sf:input path="flash_url" cssClass="w400"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="flash_url" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">链接地址<span>&nbsp;</span></td>
		<td>
			<sf:input path="link_url" cssClass="w400"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="link_url" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">显示标题<span>&nbsp;</span></td>
		<td>
			<sf:input path="title" cssClass="w400"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="title" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">显示描述<span>&nbsp;</span></td>
		<td>
			<sf:input path="content" cssClass="w400"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="content" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">开始时间<span>*</span></td>
		<td>
			<sf:input path="start_date" id="start_date" cssClass="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\\'end_date\\',{d:-1})}',readOnly:true})" />
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="start_date" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">结束时间<span>*</span></td>
		<td>
			<sf:input path="end_date" id="end_date" cssClass="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\\'start_date\\',{d:1})}',readOnly:true})" />
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="end_date" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">备注<span>&nbsp;</span></td>
		<td>
			<sf:textarea path="remark" cssClass="txt4x1"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="remark" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit"></td>
		<td>
			<sf:hidden path="adv_id"/>
			<button class="tab_btn" type="submit"/>提交</button>
		</td>
	</tr>
</table>

</sf:form>

</body>
</html>

