<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.xingfugo.web.admin.common.ConstantUtil"%>
<html>
	<head>
		<title>添加文章管理</title>
		<%@ include file="/WEB-INF/view/inc/inc.jsp"%>
		<script type="text/javascript" src="inc/com/ckeditor_3.6/ckeditor.js"></script>
		<script type="text/javascript">
　$(function (){
	CKEDITOR.replace('contentEdit',
	{
	  on :{instanceReady : function( ev ){ 
	       this.config.filebrowserImageUploadUrl = '<%=basePath%>ckupload.action'; //固定路径  
	  	}
	  }
	});
 });
</script>
	</head>
	<body>

		<div class="position">
			添加文章管理
		</div>

		<sf:form method="post" action="aboutus/add.action"
			modelAttribute="aboutus">

			<table width="100%" cellpadding="0" cellspacing="8"
				class="add_tab tr td">


				<tr>
					<td class="addtab_tit">
						标题
						<span>*</span>
					</td>
					<td>
						<sf:input path="title" cssClass="w400" />
					</td>
				</tr>
				<tr>
					<td class="addtab_tit"></td>
					<td>
						<sf:errors path="title" cssClass="error" />
					</td>
				</tr>

				<tr>
					<td class="addtab_tit">
						分类
						<span>*</span>
					</td>
					<td>
						<span id="selectDivModel"></span>
						<script type="text/javascript">
			$(function(){
			showSelectCascade("<%=basePath%>","category","selectDivModel","cat_attr","${aboutus.cat_attr}","<%=ConstantUtil.ARTI_MODULE_TYPE%>");
			});
			</script>
					</td>
				</tr>
				<tr>
					<td class="addtab_tit"></td>
					<td>
						<sf:errors path="cat_attr" cssClass="error" />
					</td>
				</tr>

				<tr>
					<td class="addtab_tit">
						内容
						<span>&nbsp;</span>
					</td>
					<td>
						<sf:textarea path="content" id="contentEdit"
							cssClass="txt4x1 ckeditor" htmlEscape="false" />
					</td>
				</tr>
				<tr>
					<td class="addtab_tit"></td>
					<td>
						<sf:errors path="content" cssClass="error" />
					</td>
				</tr>

				<tr>
					<td class="addtab_tit"></td>
					<td>
						<button class="tab_btn" type="submit" />
							提交
						</button>
					</td>
				</tr>
			</table>

		</sf:form>

	</body>
</html>

