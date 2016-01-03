<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
<script type="text/javascript" src="<%=basePath %>inc/com/calendar/WdatePicker.js"></script>
<title>举报记录信息列表</title>
</head>
<body>

<div class="position">举报记录信息列表</div>

<sf:form action="buyer_report/index.action" id="queryForm" modelAttribute="buyer_reportQueryForm">

<div class="searchdiv">
	<ul>
		<li>
			<span>被举报商家：</span>
			<sf:input path="cust_name"/>
		</li>
		
		<li>
			<span>举报类型：</span>
			<sf:select path="r_type" items="${reportMap}"/>
		</li>
		
		<li>
			<span>投诉状态：</span>
			<sf:select path="status">
				<sf:option value="">请选择</sf:option>
				<sf:option value="0">未处理</sf:option>
				<sf:option value="1">已处理</sf:option>
			</sf:select>
		</li>
		
		<li style="width:400px;">
			<span>举报时间：</span>
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
		
		<th>被举报商家</th>
		
		<th>相关宝贝</th>
		
		<th>举报类型</th>
		
		<th>描述</th>
		
		<th>处理结果</th>
		
		<th width="4%">状态</th>
		
		<th width="12%">举报时间</th>
		
		<th width="4%">操作</th>
	</tr>
	
	<c:forEach items="${pageResult.list}" var="item">

	<tr class="tab_td">
		<td><input type="checkbox" name="id" value="${item.report_id}"></td>
		
		<td>${item.cust_name}</td>
		
		<td>${item.goods_name}</td>
		
		<td>${item.report_type_s}</td>
		
		<td>${item.r_desc}</td>
		
		<td>${item.remark}</td>
		
		<td>
			<c:if test="${item.status==0}">未处理</c:if>
	        <c:if test="${item.status==2}">已处理</c:if>
		</td>
		
		<td><fmt:formatDate value="${item.in_date }" pattern="yyyy-MM-dd HH-mm-ss"/></td>
		
		<td><a href="<%=basePath%>buyer_report/view.action?id=${item.report_id}">查看</a></td>
	</tr>

	</c:forEach>

</table>

</c:if>
		
<div class="page">${pageBar}</div>

</sf:form>
            
</body>
</html>

