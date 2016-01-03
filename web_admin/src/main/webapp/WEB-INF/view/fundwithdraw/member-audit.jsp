<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<title>商家资金提现审批</title>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
</head>
<body>

<div class="position">商家资金提现审批</div>

<sf:form method="post" id="member_audit_form" action="fundwithdraw/member-audit.action" modelAttribute="fundwithdraw">

<table width="100%" cellpadding="0" cellspacing="8" class="add_tab tr td">

	<tr>
		<td class="addtab_tit">流水号<span>&nbsp;</span></td>
		<td>
			${fundwithdrawmap.order_no }
			<sf:hidden path="order_no" />
		</td>
	</tr>
	
	<tr>
		<td class="addtab_tit">资金账号<span>&nbsp;</span></td>
		<td>
			${fundwithdrawmap.account_no }
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="order_no" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">商家名称<span>&nbsp;</span></td>
		<td>
			${fundwithdrawmap.user_name }
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="account_no" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">提现金额<span>&nbsp;</span></td>
		<td>
			￥${fundwithdrawmap.trans_amt }
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="trans_amt" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">总金额<span>&nbsp;</span></td>
		<td>
			￥${fundwithdrawmap.fund_num }
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="card_no" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">可用金额<span>&nbsp;</span></td>
		<td>
			￥${fundwithdrawmap.use_num }
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="usr_name" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">冻结金额<span>&nbsp;</span></td>
		<td>
			￥${fundwithdrawmap.freeze_num }
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="open_bank" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">收款人卡号<span>&nbsp;</span></td>
		<td>
			${fundwithdrawmap.card_no }
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="sub_bank" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">收款人姓名<span>&nbsp;</span></td>
		<td>
			${fundwithdrawmap.usr_name }
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="prov" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">开户银行<span>&nbsp;</span></td>
		<td>
			${fundwithdrawmap.open_bank }
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="city" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">开户支行<span>&nbsp;</span></td>
		<td>
			<c:if test="${empty fundwithdrawmap.sub_bank }">-</c:if>
			${fundwithdrawmap.sub_bank }
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="user_id" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">开户行省份<span>&nbsp;</span></td>
		<td>
			${fundwithdrawmap.prov }
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="user_type" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">开户行城市<span>&nbsp;</span></td>
		<td>
			${fundwithdrawmap.city }
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="operate_date" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">申请时间<span>&nbsp;</span></td>
		<td>
			<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${fundwithdrawmap.operate_date }" />
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="status" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">状态<span>&nbsp;</span></td>
		<td>
			<c:if test="${fundwithdrawmap.status == '0' }">
				待审核
			</c:if>
			<c:if test="${fundwithdrawmap.status == '1' }">
				审核不通过
			</c:if>
			<c:if test="${fundwithdrawmap.status == '2' }">
				审核通过
			</c:if>
			<c:if test="${fundwithdrawmap.status == '3' }">
				提现失败
			</c:if>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="audit_user_id" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">审核<span>*</span></td>
		<td>
            <sf:radiobutton path="status" value="2" checked="checked" />通过
			<sf:radiobutton path="status" value="1" />拒绝
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="status" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">原因<span>&nbsp;</span></td>
		<td>
			<textarea name="remark" id="remark" class="txt4x1"></textarea>(审核不通过,必须填写原因)
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="remark" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit"></td>
		<td>
			<sf:hidden path="trade_id" />
			<input type="hidden" id="times" value="0" />
			<button class="tab_btn" type="button" onclick="if(document.getElementById('times').value!=0) return; document.getElementById('times').value=1; this.style.background='#666666'; document.getElementById('member_audit_form').submit();"/>提交</button>
		</td>
	</tr>
</table>

</sf:form>

</body>
</html>

