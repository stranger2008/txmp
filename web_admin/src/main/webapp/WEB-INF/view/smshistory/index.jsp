<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
<title>短信发送记录列表</title>
<script type="text/javascript" src="<%=basePath%>inc/com/calendar/WdatePicker.js"></script>
<style>
	.searchdiv_s{padding-top:10px;margin:10px 0 35px 0;width: 100%;font-size: 14px;color: #666666;border-top:1px solid #cccccc;}
	.searchdiv_s ul li{width: 300px;float: left;}
	#send_date_begin #send_date_end{width: 100px;}
</style>
</head>
<body>

<div class="position">模板管理</div>

<sf:form action="smshistory/index.action" id="queryForm" modelAttribute="smshistoryQueryForm">
<div class="searchdiv">
	<ul>
		<li style="width:100px;"><a href="sms_email_template/sms_template_index.action" >短信模板管理</a></li>
		<li style="width:100px;"><a href="sms_email_template/email_template_index.action" >邮件模板管理</a></li>
		<li style="width:100px;"><a href="smshistory/index.action" style="color:red;">短信发送记录</a></li>
		<li style="width:100px;"><a href="emailhistory/index.action" >邮件发送记录</a></li>
	</ul>
</div>
<div class="clearfix"></div>
<div class="searchdiv_s">
	<ul>
		<li><span>手机号码：</span><sf:input path="phoneattr"/></li>
		<li><span>短信内容：</span><sf:input path="sms_desc"/></li>
		<li style="width:500px;">
			<span>咨询时间：</span>
			<sf:input path="send_date_begin" id="send_date_begin" cssClass="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\\'send_date_end\\',{d:-1})}'})" />
			-
			<sf:input path="send_date_end" id="send_date_end" cssClass="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\\'send_date_begin\\',{d:1})}'})" />
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
		
		<th>手机号码</th>
		
		<th>短信动态码</th>
		
		<th>短信内容</th>
		
		<th>发送时间</th>
		
	</tr>
	
	<c:forEach items="${pageResult.list}" var="item">

	<tr class="tab_td">
		
		<td><input type="checkbox" name="id" value="${item.trade_id}"></td>
		
		<td>${item.phoneattr}</td>
		
		<td>${item.content}</td>
		
		<td>${item.sms_desc}</td>
		
		<td><fmt:formatDate value="${item.send_date}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
		
	</tr>

	</c:forEach>

</table>

</c:if>
		
<div class="page">${pageBar}</div>

</sf:form>
            
</body>
</html>

