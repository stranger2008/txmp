<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
<title>邮件模板管理</title>
</head>
<body>

<div class="position">模板管理</div>

<sf:form action="sms_email_template/sms_template_index.action" id="queryForm" modelAttribute="sms_email_templateQueryForm">
<div class="searchdiv">
	<ul>
		<li style="width:100px;"><a href="sms_email_template/sms_template_index.action" >短信模板管理</a></li>
		<li style="width:100px;"><a href="sms_email_template/email_template_index.action" style="color:red;">邮件模板管理</a></li>
		<li style="width:100px;"><a href="smshistory/index.action" >短信发送记录</a></li>
		<li style="width:100px;"><a href="emailhistory/index.action" >邮件发送记录</a></li>
	</ul>
</div>

<div class="clearfix"></div>
<div class="kjbutdiv">

</div>

<c:if test="${!empty pageResult.list}">

<table width="100%" cellpadding="0" cellspacing="0" class="info_tab">
	<tr class="tab_th">
		
		<th>模板编码</th>
		
		<th>模板名称</th>
		
		<th>标签解释</th>
		
		<th>修改</th>
	</tr>
	
	<c:forEach items="${pageResult.list}" var="item">

	<tr class="tab_td">
		
		<td>${item.temp_code}</td>
		
		<td>${item.temp_name}</td>
		
		<td>${item.tag_desc}</td>
		
		<td><a href="<%=basePath%>sms_email_template/update.action?id=${item.temp_code}">修改</a></td>
	</tr>

	</c:forEach>

</table>

</c:if>
		
<div class="page">${pageBar}</div>

</sf:form>
            
</body>
</html>

