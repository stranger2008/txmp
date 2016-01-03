<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>资金总览</title>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="<%=basePath %>inc/fundaccount/css/fundaccount.css" />
</head>
<body>
<div>
	<div class="fundaccount">
		<h2 style="font-family: '微软雅黑';padding: 10px 0;color: #cc0000;font-weight: bold;font-size: 16px;height: 34px;line-height: 34px;">资金账户</h2>
		<ul>
			<li>
				总金额：<em>￥${fundaccount.fund_num }</em>&nbsp;&nbsp;
				可用余额：<em>￥${fundaccount.use_num }</em>&nbsp;&nbsp;
				冻结余额：<em>￥${fundaccount.freeze_num }</em>&nbsp;&nbsp;
				账户状态：<em><c:if test="${fundaccount.enabled == '0' }">有效</c:if><c:if test="${fundaccount.enabled == '1' }">无效</c:if></em>
				<a href="<%=basePath %>fundaccount/withdraw.action">提现</a>
				<a href="<%=basePath %>fundaccount/recharge.action">充值</a>
			</li>
		</ul>
	</div>
	<div class="fundaccount">
		<h2>交易记录</h2>
		<sf:form method="post" action="fundaccount/index.action" id="queryForm" modelAttribute="fundhistoryQueryForm">
				<table width="100%" cellpadding="0" cellspacing="0" class="info_tab">
					<tbody>
					<tr>
						<th height="36" align="center" bgcolor="#f5f5f5" class="tab_th">
							<sf:select path="in_date_range" onchange="document.getElementById('queryForm').submit();">
								<sf:option value="1">三个月以内</sf:option>
								<sf:option value="-1">三个月以外</sf:option>
							</sf:select>
						</th>
						<th align="center" bgcolor="#f5f5f5" class="tab_th">存入<br /></th>
						<th align="center" bgcolor="#f5f5f5" class="tab_th">支出<br /></th>
						<th align="center" bgcolor="#f5f5f5" class="tab_th">余额<br /></th>
						<th align="center" bgcolor="#f5f5f5" class="tab_th">
							<sf:select path="action_type" onchange="document.getElementById('queryForm').submit();">
								<sf:option value="">全部</sf:option>
								<sf:option value="0">充值</sf:option>
								<sf:option value="1">提现</sf:option>
								<sf:option value="3">退款</sf:option>
							</sf:select>
						<br /></th>
						<th bgcolor="#f5f5f5" align="center" class="tab_th">明细<br /></th>
						<th bgcolor="#f5f5f5" align="center" class="tab_th">操作人<br /></th>
					</tr>
					<c:forEach items="${pageResult.list}" var="item">
					<tr style="text-align:center;" class="tab_td">
						<td>
							<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${item.in_date }" />
						</td>
						<td style="text-align:center;">
							<c:if test="${(item.fund_in < 0) or (item.fund_in > 0)}">￥${item.fund_in }</c:if>
							<c:if test="${not ((item.fund_in < 0) or (item.fund_in > 0))}">-</c:if>
						</td>
						<td style="text-align:center;">
							<c:if test="${(item.fund_out < 0) or (item.fund_out > 0)}">￥${item.fund_out }</c:if>
							<c:if test="${not ((item.fund_out < 0) or (item.fund_out > 0))}">-</c:if>
						</td>
						<td>
							￥${item.balance }
						</td>
						<td>
							<c:if test="${item.action_type == '0' }">充值</c:if>
							<c:if test="${item.action_type == '1' }">提现</c:if>
							<c:if test="${item.action_type == '3' }">退款</c:if>
							<c:if test="${item.action_type == '4' }">提现</c:if>
						</td>
						<td>
							${item.reason }
						</td>
						<td>
							${item.user_name }${item.cust_name }
							<c:if test="${item.user_id == null && item.cust_id == null }">系统</c:if>
						</td>
					</tr>
					</c:forEach>
					</tbody>
				</table>
			<div class="page">${pageBar}</div>
		</sf:form>
	</div>
</div>
</body>
</html>
