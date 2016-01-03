<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
<script type="text/javascript" src="<%=basePath%>inc/com/calendar/WdatePicker.js"></script>
<title>店铺级别列表</title>
</head>
<body>
<div class="position">添加店铺级别</div>
<sf:form method="post" action="levelinfo/add.action" modelAttribute="levelinfo">
<table width="100%" cellpadding="0" cellspacing="8" class="add_tab tr td">
	<tr>
		<td class="addtab_tit">级别<span>*</span></td>
		<td>
			<sf:select path="level_code">
				<sf:option value="">请选择</sf:option>
			<c:forEach items="${memberlevels }" var="memberlevel">
				<sf:option value="${memberlevel.level_code }">${memberlevel.level_name }</sf:option>
			</c:forEach>
			</sf:select>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="level_code" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">开始日期<span>*</span></td>
		<td>
			<sf:input path="start_date" id="start_date" readonly="readonly" cssClass="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\\'end_date\\',{d:-1})}'})" />
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="start_date" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">结束日期<span>*</span></td>
		<td>
			<sf:input path="end_date" id="end_date" readonly="readonly" cssClass="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\\'start_date\\',{d:1})}'})" />
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="end_date" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">备注<span>&nbsp;</span></td>
		<td>
			<sf:textarea path="remark" cssClass="txt4x1" />
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="remark" cssClass="error"/></td></tr>
	
	
	<tr>
		<td class="addtab_tit"></td>
		<td>
			<sf:hidden path="cust_id" />
			<button class="tab_btn" type="submit"/>提交</button>
		</td>
	</tr>
</table>
</sf:form>

<sf:form action="levelinfo/view.action" id="queryForm" modelAttribute="levelinfoQueryForm">
<c:if test="${!empty pageResult.list}">
<table width="100%" cellpadding="0" cellspacing="0" class="info_tab">
	<tr class="tab_th">
		<th width="5%"><input type="checkbox"></th>
		
		<th>级别</th>
		<th>有效时间</th>
		<th>操作人</th>
		<th>操作时间</th>
		<th>备注</th>
		<th>操作</th>
	</tr>
	
	<c:forEach items="${pageResult.list}" var="item">
	<tr class="tab_td">
		<td><input type="checkbox" name="id" value="${item.trade_id}"></td>
		
		<td>${item.level_name}</td>
		<td>
			<fmt:formatDate value="${item.start_date}" pattern="yyyy-MM-dd" />
			&nbsp;-&nbsp;
			<fmt:formatDate value="${item.end_date}" pattern="yyyy-MM-dd" />
		</td>
		<td>${item.user_name }</td>
		<td><fmt:formatDate value="${item.in_date}" pattern="yyyy-MM-dd" /></td>
		<td>${item.remark }</td>
		<td><a href="<%=basePath %>levelinfo/delete.action?id=${item.trade_id }">删除</a></td>
	</tr>
	</c:forEach>

</table>
</c:if>
<div class="page">${pageBar}</div>
</sf:form>
            
</body>
</html>

