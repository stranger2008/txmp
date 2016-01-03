<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<title>商家资金提现详情</title>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
</head>
<body>

<div class="position">商家资金提现详情</div>

<table width="100%" cellpadding="0" cellspacing="8" class="add_tab tr td">

	<tr>
		<td class="addtab_tit">流水号<span>&nbsp;</span></td>
		<td>
			${fundwithdraw.order_no }
		</td>
	</tr>
	
	<tr>
		<td class="addtab_tit">资金账号<span>&nbsp;</span></td>
		<td>
			${fundwithdraw.account_no }
		</td>
	</tr>
	
	<tr>
		<td class="addtab_tit">商家名称<span>&nbsp;</span></td>
		<td>
			${fundwithdraw.user_name }
		</td>
	</tr>
	
	<tr>
		<td class="addtab_tit">提现金额<span>&nbsp;</span></td>
		<td>
			${fundwithdraw.trans_amt }
		</td>
	</tr>
	
	<tr>
		<td class="addtab_tit">总金额<span>&nbsp;</span></td>
		<td>
			${fundwithdraw.fund_num }
		</td>
	</tr>
	
	<tr>
		<td class="addtab_tit">可用金额<span>&nbsp;</span></td>
		<td>
			${fundwithdraw.use_num }
		</td>
	</tr>
	
	<tr>
		<td class="addtab_tit">冻结金额<span>&nbsp;</span></td>
		<td>
			${fundwithdraw.freeze_num }
		</td>
	</tr>
	
	<tr>
		<td class="addtab_tit">收款人卡号<span>&nbsp;</span></td>
		<td>
			${fundwithdraw.card_no }
		</td>
	</tr>
	
	<tr>
		<td class="addtab_tit">收款人姓名<span>&nbsp;</span></td>
		<td>
			${fundwithdraw.usr_name }
		</td>
	</tr>
	
	<tr>
		<td class="addtab_tit">开户银行<span>&nbsp;</span></td>
		<td>
			${fundwithdraw.open_bank }
		</td>
	</tr>
	
	<tr>
		<td class="addtab_tit">开户支行<span>&nbsp;</span></td>
		<td>
			${fundwithdraw.sub_bank }
		</td>
	</tr>
	
	<tr>
		<td class="addtab_tit">开户行省份<span>&nbsp;</span></td>
		<td>
			${fundwithdraw.prov }
		</td>
	</tr>
	
	<tr>
		<td class="addtab_tit">开户行城市<span>&nbsp;</span></td>
		<td>
			${fundwithdraw.city }
		</td>
	</tr>
	
	<tr>
		<td class="addtab_tit">申请时间<span>&nbsp;</span></td>
		<td>
			<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${fundwithdraw.operate_date }" />
		</td>
	</tr>
	
	<tr>
		<td class="addtab_tit">状态<span>&nbsp;</span></td>
		<td>
			<c:if test="${fundwithdraw.status == '0' }">
				待审核
			</c:if>
			<c:if test="${fundwithdraw.status == '1' }">
				审核不通过
			</c:if>
			<c:if test="${fundwithdraw.status == '2' }">
				审核通过
			</c:if>
			<c:if test="${fundwithdraw.status == '3' }">
				提现失败
			</c:if>
		</td>
	</tr>
	
	<tr>
		<td class="addtab_tit">备注<span>&nbsp;</span></td>
		<td>
			${fundwithdraw.remark }
		</td>
	</tr>
</table>

</body>
</html>

