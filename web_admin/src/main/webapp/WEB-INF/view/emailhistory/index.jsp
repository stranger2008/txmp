<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
<title>邮件发送记录列表</title>
<script type="text/javascript" src="<%=basePath%>inc/com/calendar/WdatePicker.js"></script>
<style>
	.searchdiv_s{padding-top:10px;margin:10px 0 35px 0;width: 100%;font-size: 14px;color: #666666;border-top:1px solid #cccccc;}
	.searchdiv_s ul li{width: 300px;float: left;}
	#send_date_begin #send_date_end{width: 100px;}
</style>
</head>
<body>

<div class="position">模板管理</div>

<sf:form action="emailhistory/index.action" id="queryForm" modelAttribute="emailhistoryQueryForm">
<div class="searchdiv">
	<ul>
		<li style="width:100px;"><a href="sms_email_template/sms_template_index.action" >短信模板管理</a></li>
		<li style="width:100px;"><a href="sms_email_template/email_template_index.action" >邮件模板管理</a></li>
		<li style="width:100px;"><a href="smshistory/index.action" >短信发送记录</a></li>
		<li style="width:100px;"><a href="emailhistory/index.action" style="color:red;">邮件发送记录</a></li>
	</ul>
</div>
<div class="clearfix"></div>
<div class="searchdiv_s">
	<ul>
		<li><span>邮件地址：</span><sf:input path="email_attr"/></li>
		<li><span>邮件标题：</span><sf:input path="title"/></li>
		<li style="width:500px;">
			<span>发送时间：</span>
			<sf:input path="in_date_begin" id="in_date_begin" cssClass="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\\'in_date_end\\',{d:-1})}'})" />
			-
			<sf:input path="in_date_end" id="in_date_end" cssClass="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\\'in_date_begin\\',{d:1})}'})" />
		</li>		
	</ul>
</div>

<div class="kjbutdiv">
	<a href="javascript:void(0);" id="deleteInfo">删除</a>
	<a href="javascript:void(0);" id="searchInfo">查询</a>
</div>

<c:if test="${!empty pageResult.list}">

<table width="100%" cellpadding="0" cellspacing="0" class="info_tab">
	<tr class="tab_th">
	
		<th width="5%"><input type="checkbox"></th>
		
		<th>邮件地址</th>
		
		<th>邮件标题</th>
		
		<th>发送时间</th>
		
		<th>操作</th>
	</tr>
	
	<c:forEach items="${pageResult.list}" var="item">

	<tr class="tab_td">
		
		<td><input type="checkbox" name="id" value="${item.trade_id}"></td>
		
		<td>${item.email_attr}</td>
		
		<td>${item.title}</td>
		
		<td><fmt:formatDate value="${item.in_date}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
		
		<td><a href="<%=basePath%>emailhistory/update.action?id=${item.trade_id}">查看</a></td>
		
	</tr>

	</c:forEach>

</table>

</c:if>
		
<div class="page">${pageBar}</div>

</sf:form>
            
</body>
</html>

