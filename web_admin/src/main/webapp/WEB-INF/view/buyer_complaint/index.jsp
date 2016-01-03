<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
<script type="text/javascript" src="<%=basePath %>inc/com/calendar/WdatePicker.js"></script>
<title>投诉记录信息列表</title>
</head>
<body>

<div class="position">投诉记录信息列表</div>

<sf:form action="buyer_complaint/index.action" id="queryForm" modelAttribute="buyer_complaintQueryForm">

<div class="searchdiv">
	<ul>
		<li>
			<span>订单编号：</span><sf:input path="order_id" maxlength="20" cssStyle="width:120px;"/>
		</li>
		
		<li>
			<span>投诉方：</span><sf:input path="user_name" maxlength="30" cssStyle="width:120px;"/>
		</li>
		
		<li>
			<span>涉及商家：</span><sf:input path="cust_name" maxlength="30" cssStyle="width:120px;"/>
		</li>
		
		
		<li>
			<span>类型：</span><sf:select path="com_type" items="${compTypeMap}"/>
		</li>
		
		<li>
			<span>投诉状态：</span>
			<sf:select path="status">
				<sf:option value="">请选择</sf:option>
				<sf:option value="0">商家处理中</sf:option>
				<sf:option value="2">运营商处理中</sf:option>
				<sf:option value="1">已处理</sf:option>
			</sf:select>
		</li>
		
		<li style="width:400px;">
			<span>投诉日期：</span>
			<sf:input path="in_date_begin" id="in_date_begin" cssClass="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\\'in_date_end\\',{d:-1})}',readOnly:true})" style="width:100px;"/>
					- 
			<sf:input path="in_date_end" id="in_date_end" cssClass="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\\'in_date_begin\\',{d:1})}',readOnly:true})" style="width:100px;"/>
		</li>
		
	</ul>
</div>

<div class="clearfix"></div>

<div class="kjbutdiv">
	<a href="javascript:void(0);" id="deleteInfo">删除</a>
	<a href="javascript:void(0);" id="searchInfo">查询</a>
</div>

<c:if test="${!empty pageResult.list}">

<table width="100%" cellpadding="0" cellspacing="0" class="info_tab">
	<tr class="tab_th">
		<th width="5%"><input type="checkbox"></th>
		
		<th width="12%">订单号</th>
		
		<th width="8%">类型</th>
		
		<th width="12%">投诉方</th>
		
		<th width="10%">涉及商家</th>
		
		<th>投诉原因</th>
		
		<th width="8%">状态</th>
		
		<th width="8%">投诉日期</th>
		
		<th width="5%">操作</th>
	</tr>
	
	<c:forEach items="${pageResult.list}" var="item">

	<tr class="tab_td">
		<td><input type="checkbox" name="id" value="${item.complaint_id}"></td>
		
		<td>${item.order_id}</td>
		
		<td>${item.com_type_s}</td>
		
		<td>${item.user_name}</td>
		
		<td>${item.cust_name}</td>
		
		<td>${item.reason }</td>
		
		<td>
			 <c:if test="${item.status==0}">商家处理中</c:if>
             <c:if test="${item.status==2}">运营商处理中</c:if>
             <c:if test="${item.status==1}">已处理</c:if>
		</td>
		
		<td><fmt:formatDate value="${item.in_date }" pattern="yyyy-MM-dd"/></td>
		
		<td><a href="<%=basePath%>buyer_complaint/viewComplaint-${item.complaint_id}.action" >查看</a></td>
	</tr>

	</c:forEach>

</table>

</c:if>
		
<div class="page">${pageBar}</div>

</sf:form>
            
</body>
</html>

