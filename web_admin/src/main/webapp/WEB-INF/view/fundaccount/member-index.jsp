<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
<title>资金列表</title>
<script type="text/javascript">
//
function enableAccount() {
	var ret = 0;
	$(".tab_td input[type='checkbox']").each(function(){
		if($(this).attr("checked") == true){
			ret = 1;
		}
	});
	if(ret == 0){
		alert("请至少选择一条信息");
	}else{
		if(confirm("确定启用已选择的账户吗？")){
			var actionVal = $("#queryForm").attr("action");
			actionVal = actionVal.replace("index","enable");
			$("#queryForm").attr("action",actionVal);
			$("#queryForm").submit();
		}
	}
}
function disableAccount() {
	var ret = 0;
	$(".tab_td input[type='checkbox']").each(function(){
		if($(this).attr("checked") == true){
			ret = 1;
		}
	});
	if(ret == 0){
		alert("请至少选择一条信息");
	}else{
		if(confirm("确定禁用已选择的账户吗？")){
			var actionVal = $("#queryForm").attr("action");
			actionVal = actionVal.replace("index","disable");
			$("#queryForm").attr("action",actionVal);
			$("#queryForm").submit();
		}
	}
}
</script>
</head>
<body>

<div class="position">资金列表</div>

<sf:form action="fundaccount/member-index.action" id="queryForm" modelAttribute="fundaccountQueryForm">
<div class="searchdiv">
	<ul>
		
		<li>
			<span>账号：</span>
			<sf:input path="account_no"/>
		</li>
		
		<li>
			<span>商家名称：</span>
			<sf:input path="cust_name"/>
		</li>
		
		<li>
			<span>资金：</span>
				<sf:input path="min_fund_num" cssStyle="width:72px;" />
				-
				<sf:input path="max_fund_num" cssStyle="width:72px;" />
		</li>
		
		<li>
			<span>状态：</span>
			<sf:select path="enabled">
				<sf:option value="">全部</sf:option>
				<sf:option value="0">有效</sf:option>
				<sf:option value="1">无效</sf:option>
			</sf:select>
		</li>
		
	</ul>
</div>

<div class="clearfix"></div>

<div class="kjbutdiv">
	<a href="javascript:void(0);" onclick="enableAccount();">启用</a>
	<a href="javascript:void(0);" onclick="disableAccount();">禁用</a>
	<a href="javascript:void(0);" id="searchInfo">查询</a>
</div>

<c:if test="${!empty pageResult.list}">

<table width="100%" cellpadding="0" cellspacing="0" class="info_tab">
	<tr class="tab_th">
	
		<th width="5%"><input type="checkbox"></th>
		
		<th>账号</th>
		
		<th>商家名称</th>
		
		<th>总金额</th>
		
		<th>可用余额</th>
		
		<th>冻结余额</th>
		
		<th>状态</th>
		
		<th>操作</th>
	</tr>
	
	<c:forEach items="${pageResult.list}" var="item">

	<tr class="tab_td">
	
		<td><input type="checkbox" name="id" value="${item.account_no}"></td>
		
		<td>${item.account_no}</td>
		
		<td>
			<c:if test="${fundaccountQueryForm.cust_type == '0' }">${item.user_name }</c:if>
			<c:if test="${fundaccountQueryForm.cust_type == '1' }">${item.cust_name }</c:if>
		</td>
		
		<td>￥${item.fund_num}</td>
		
		<td>￥${item.use_num}</td>
		
		<td>￥${item.freeze_num}</td>
		
		<td>
			<c:if test="${item.enabled == '0' }">
				有效
			</c:if>
			<c:if test="${item.enabled == '1' }">
				无效
			</c:if>
		</td>
		
		<td>
			<a href="<%=basePath%>fundhistory/index.action?account_no=${item.account_no}">查看详情</a>
		</td>
	</tr>

	</c:forEach>

</table>

</c:if>
		
<div class="page">${pageBar}</div>

</sf:form>
            
</body>
</html>

