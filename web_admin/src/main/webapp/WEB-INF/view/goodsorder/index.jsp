<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
<script type="text/javascript" src="<%=basePath%>inc/com/calendar/WdatePicker.js"></script>
<title>订单信息列表</title>
<style type="text/css">
#order_time_begin, #order_time_end{
	width:86px;
	text-align:left;
}
</style>
</head>
<body>

<div class="position">订单信息列表</div>

<sf:form action="goodsorder/index.action" id="queryForm" modelAttribute="goodsorderQueryForm">

<div class="searchdiv">
	<ul>
		
		<li>
			<span>订单号：</span>
			<sf:input path="order_id"/>
		</li>
		
		<li>
			<span>商品名称：</span>
			<sf:input path="goods_name"/>
		</li>
		
		<li>
			<span>订单状态：</span>
			<sf:select path="order_state">
				<sf:option value="">全部</sf:option>
				<c:forEach items="${orderStates }" var="item">
					<sf:option value="${item.key }">${item.value }</sf:option>
				</c:forEach>
			</sf:select>
		</li>
		
		<li>
			<span>订单时间：</span>
			<sf:input path="order_time_begin" id="order_time_begin" readonly="readonly" cssClass="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\\'order_time_end\\',{d:-1})}'})" />
			 - 
			<sf:input path="order_time_end" id="order_time_end" readonly="readonly" cssClass="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\\'order_time_begin\\',{d:1})}'})" />
		</li>
		
	</ul>
</div>

<div class="clearfix"></div>

<div class="kjbutdiv">
	<a href="javascript:void(0);" id="searchInfo">查询</a>
</div>

<c:if test="${!empty pageResult.list}">

<table width="100%" cellpadding="0" cellspacing="0" class="info_tab">
	<tr class="tab_th">
		<th width="50%">订单信息</th>
		<th width="10%">订单金额</th>
		<th width="10%">下单时间</th>
		<th width="20%">订单状态</th>
		<th width="10%">操作</th>
	</tr>
	<c:if test="${!empty pageResult.list}">
	<c:forEach items="${pageResult.list}" var="item">
	<tr class="tab_td">
		<td colspan="6">
			订单编号：<a href="<%=basePath%>goodsorder/view.action?order_id=${item.order_id }">${item.order_id}</a>
		</td>
	</tr>
	<tr class="tab_td">
		<td>
		<c:forEach items="${item.ordergoods}" var="bean">
 			<table width="100%">
				<tr>
					<td style="border:0px; width:100px;">
					<c:if test="${!empty bean.img}">
						<img src="<h:imgSubstr imgpath="${bean.img}"/>" />
					</c:if>
					</td>
					<td style="border:0px;">
						${bean.name}
					</td>
				</tr>
			</table>
		</c:forEach>
		</td>
		<td>
			￥${item.tatal_amount}
		</td>
		<td>
			<fmt:formatDate value="${item.order_time}" pattern="yyyy-MM-dd HH:mm:ss" />
		</td>
		<td>
			${item.order_state_name}
		</td>
		<td>
			<a href="<%=basePath%>goodsorder/view.action?order_id=${item.order_id }">查看详细</a><br/>
		</td>
	</tr>
	</c:forEach>
	</c:if>
</table>
</c:if>
<c:if test="${empty pageResult.list}">
	<center>无订单信息</center>
</c:if>
<div class="page">${pageBar}</div>

</sf:form>
            
</body>
</html>

