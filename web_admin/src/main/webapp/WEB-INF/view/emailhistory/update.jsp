<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<title>邮件记录信息</title>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
</head>
<body>

<div class="position">邮件记录信息</div>
<div class="searchdiv">
	<ul>
		<li style="width:100px;"><a href="sms_email_template/sms_template_index.action">短信模板管理</a></li>
		<li style="width:100px;"><a href="sms_email_template/email_template_index.action" >邮件模板管理</a></li>
		<li style="width:100px;"><a href="smshistory/index.action" >短信发送记录</a></li>
		<li style="width:100px;"><a href="emailhistory/index.action" style="color:red;">邮件发送记录</a></li>
	</ul>
</div>
<div class="clearfix"></div>
<div class="kjbutdiv"></div>
<sf:form method="post" action="emailhistory/update.action" modelAttribute="emailhistory">

<table width="100%" cellpadding="0" cellspacing="8" class="add_tab tr td">

	
	<tr>
		<td class="addtab_tit">邮件地址<span>&nbsp;</span></td>
		<td>
			${emailhistory.email_attr}
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="email_attr" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">标题<span>&nbsp;</span></td>
		<td>
			${emailhistory.title}
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="title" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">内容<span>&nbsp;</span></td>
		<td>
			${emailhistory.content}
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="content" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">日期<span>&nbsp;</span></td>
		<td>
			${emailhistory.in_date}
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="in_date" cssClass="error"/></td></tr>
	
	
	<tr>
		<td class="addtab_tit"></td>
		<td>
			<sf:hidden path="trade_id"/>
			<button class="tab_btn" type="button" onclick="history.go(-1)"/>返回</button>
		</td>
	</tr>
</table>

</sf:form>

</body>
</html>

