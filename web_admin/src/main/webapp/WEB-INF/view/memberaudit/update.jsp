<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<title>修改商家信息</title>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
<script type="text/javascript">
	//审核不通过状态值
	var MEMBER_AUDIT_REJECT = '2';
	function showNoReason(type) {
		if(type) {
			$('#no_reason_titile').html('原因<span>*</span>');
			$('#no_reason_ta').html('<textarea name="no_reason" id="no_reason" class="txt4x1"></textarea>');
			$('#no_reason_error').html('');
		} else {
			$('#no_reason_titile').html('');
			$('#no_reason_ta').html('');
			$('#no_reason_error').html('');
		}
	}
	
	$(document).ready(function() {
		$('#submit_btn').click(function() {
			var audit_status = $('input[name="audit_status"]:checked').val();
			if(audit_status == MEMBER_AUDIT_REJECT) {
				if($('#no_reason').val().trim() == null || $('#no_reason').val().trim() == '' || $('#no_reason').val().length > 200) {
					$('#no_reason_error').html('<span style="color:red;">原因不能为空,且长度不能大于200</span>');
					return;
				}
			}
			$('#info_form').submit();
		});
	});

</script>
</head>
<body>

<div class="position">修改商家信息</div>
<sf:form method="post" id="info_form" action="member-audit/update.action" modelAttribute="member">
<table width="100%" cellpadding="0" cellspacing="8" class="add_tab tr td">

	<tr>
		<td class="addtab_tit">商家名称<span>&nbsp;</span></td>
		<td>
			${member.cust_name }
		</td>
	</tr>
	
	<tr>
		<td class="addtab_tit">用户名<span>&nbsp;</span></td>
		<td>
			${member.user_name }
		</td>
	</tr>
	
	<tr>
		<td class="addtab_tit">公司logo<span>&nbsp;</span></td>
		<td>
			<c:set value="${fn:split(member.logo_img, ',') }" var="imgs" />
			<c:forEach items="${imgs }" var="img">
				<c:if test="${!empty img }">
					<a href="${img }" target="_blank"><img src="${img }" style="width: 100px; height: 100px;" /></a>
				</c:if>
			</c:forEach>
		</td>
	</tr>
	
	<tr>
		<td class="addtab_tit">公司地址<span>&nbsp;</span></td>
		<td>
			${member.area_attr_name },${member.address }
		</td>
	</tr>
	
	<tr>
		<td class="addtab_tit">公司电话<span>&nbsp;</span></td>
		<td>
			${member.phone }
		</td>
	</tr>
	
	<tr>
		<td class="addtab_tit">公司注册资金<span>&nbsp;</span></td>
		<td>
			${member.reg_money }${member.reg_money_type }
		</td>
	</tr>
	
	<tr>
		<td class="addtab_tit">营业执照号码<span>&nbsp;</span></td>
		<td>
			${member.lic_no }
		</td>
	</tr>
	
	<tr>
		<td class="addtab_tit">营业执照所在地<span>&nbsp;</span></td>
		<td>
			${member.lic_address }
		</td>
	</tr>
	
	<tr>
		<td class="addtab_tit">营业执照有效时间<span>&nbsp;</span></td>
		<td>
			<fmt:formatDate value="${member.lic_start_time}" pattern="yyyy-MM-dd" />
			&nbsp;-&nbsp;
			<fmt:formatDate value="${member.lic_end_time}" pattern="yyyy-MM-dd" />
		</td>
	</tr>
	
	<tr>
		<td class="addtab_tit">营业执照<span>&nbsp;</span></td>
		<td>
			<c:set value="${fn:split(member.lic_img, ',') }" var="imgs" />
			<c:forEach items="${imgs }" var="img">
				<c:if test="${!empty img }">
					<a href="${img }" target="_blank"><img src="${img }" style="width: 100px; height: 100px;" /></a>
				</c:if>
			</c:forEach>
		</td>
	</tr>
	
	<tr>
		<td class="addtab_tit">经营范围<span>&nbsp;</span></td>
		<td>
			${member.product }
		</td>
	</tr>
	
	<tr>
		<td class="addtab_tit">法人身份证<span>&nbsp;</span></td>
		<td>
			<c:set value="${fn:split(member.person_id_img, ',') }" var="imgs" />
			<c:forEach items="${imgs }" var="img">
				<c:if test="${!empty img }">
					<a href="${img }" target="_blank"><img src="${img }" style="width: 100px; height: 100px;" /></a>
				</c:if>
			</c:forEach>
		</td>
	</tr>
	
	<tr>
		<td class="addtab_tit">组织机构代码证件<span>&nbsp;</span></td>
		<td>
			<c:set value="${fn:split(member.org_img, ',') }" var="imgs" />
			<c:forEach items="${imgs }" var="img">
				<c:if test="${!empty img }">
					<a href="${img }" target="_blank"><img src="${img }" style="width: 100px; height: 100px;" /></a>
				</c:if>
			</c:forEach>
		</td>
	</tr>
	
	<tr>
		<td class="addtab_tit">税务登记证<span>&nbsp;</span></td>
		<td>
			<c:set value="${fn:split(member.tax_img, ',') }" var="imgs" />
			<c:forEach items="${imgs }" var="img">
				<c:if test="${!empty img }">
					<a href="${img }" target="_blank"><img src="${img }" style="width: 100px; height: 100px;" /></a>
				</c:if>
			</c:forEach>
		</td>
	</tr>
	
	<tr>
		<td class="addtab_tit">公司电子邮箱<span>&nbsp;</span></td>
		<td>
			${member.email }
		</td>
	</tr>
	
	<tr>
		<td class="addtab_tit">银行开户许可证<span>&nbsp;</span></td>
		<td>
			<c:set value="${fn:split(member.bank_img, ',') }" var="imgs" />
			<c:forEach items="${imgs }" var="img">
				<c:if test="${!empty img }">
					<a href="${img }" target="_blank"><img src="${img }" style="width: 100px; height: 100px;" /></a>
				</c:if>
			</c:forEach>
		</td>
	</tr>
	
	<tr>
		<td class="addtab_tit">开户行名称<span>&nbsp;</span></td>
		<td>
			${member.bank_name }
		</td>
	</tr>
	
	<tr>
		<td class="addtab_tit">银行账号<span>&nbsp;</span></td>
		<td>
			${member.bank_id }
		</td>
	</tr>
	
	<tr>
		<td class="addtab_tit">联系人<span>&nbsp;</span></td>
		<td>
			${member.contact_name }
		</td>
	</tr>
	
	<tr>
		<td class="addtab_tit">联系人电话<span>&nbsp;</span></td>
		<td>
			${member.contact_phone }
		</td>
	</tr>
	
	<tr>
		<td class="addtab_tit">联系人邮箱<span>&nbsp;</span></td>
		<td>
			${member.contact_email }
		</td>
	</tr>
	
	<tr>
		<td class="addtab_tit">申请时间<span>&nbsp;</span></td>
		<td>
			<fmt:formatDate value="${member.apply_time}" pattern="yyyy-MM-dd" />
		</td>
	</tr>
	
	<tr>
		<td class="addtab_tit">审核<span>&nbsp;</span></td>
		<td>
			<sf:radiobutton path="audit_status" value="1" checked="checked" onclick="showNoReason(false)" />通过
            <sf:radiobutton path="audit_status" value="2" onclick="showNoReason(true)" />不通过
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="audit_status" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit" id="no_reason_titile">
			<c:if test="${member.audit_status == '2' }">
				原因<span>*</span>
			</c:if></td>
		<td id="no_reason_ta">
			<c:if test="${member.audit_status == '2' }">
				<textarea name="no_reason" id="no_reason" class="txt4x1"></textarea>
			</c:if>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td id="no_reason_error"></td></tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="no_reason" cssClass="error"/></td></tr>
	<tr>
		<td class="addtab_tit"></td>
		<td>
			<sf:hidden path="cust_id"/>
			<button class="tab_btn" id="submit_btn" type="button" />提交</button>
		</td>
	</tr>
</table>
</sf:form>
</body>
</html>

