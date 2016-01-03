<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<title>查看退换货详细</title>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
</head>
<body>

<div class="position">查看退换货详细</div>

<style>
	.ordertab{background:#E7CA96;margin-top:10px;line-height:24px;}
	.ordertab h1{font-weight:bold;font-size:14px;}
	.ordertab tr{background:white;}
	.ordertab td{padding:6px;}
</style>

<table class="ordertab" width="100%" cellspacing="1">
	<tr>
		<td colspan="4">
			<h1>商品信息</h1>
		</td>
	</tr>
	<tr>
		<td>
			订单编号：${goodsreturnMap.order_id }
			&nbsp;<a href="<%=basePath%>order/detail-${goodsreturnMap.order_id}.action" target="_blank">查看订单</a>
			<br/>
			商品名称：${goodsreturnMap.goods_name }<br/>
			状态：${goodsreturnMap.info_state_name }<br/>
		</td>
	</tr>
</table>

<table class="ordertab" width="100%" cellspacing="1">
	<tr>
		<td colspan="4">
			<h1>${goodsreturnMap.biz_type_name }申请</h1>
		</td>
	</tr>
	<tr>
		<td>
			问题描述：<br/>
			${goodsreturnMap.cont_desc }<br/>
			<c:if test="${! empty goodsreturnMap.img_path}">
				商品图片：
				<br/>
				<c:set value="${fn:split(goodsreturnMap.img_path,',')}" var="imgone" />
				<c:forEach items="${imgone}" var="s">
					<a href="${s}" target="_blank"><img src="${s}" width="100" height="100"/></a>&nbsp;
				</c:forEach>
				<br/>
			</c:if>
			申请人：${goodsreturnMap.user_name }<br/>
			手机号：${goodsreturnMap.cellphone }<br/>
			申请时间：${goodsreturnMap.in_date }<br/>
		</td>
	</tr>
	
</table>

<!-- 待审核 -->
<c:if test="${goodsreturnMap.info_state==0}">

<sf:form action="goodsreturn/audit.action" id="goodsreturnForm" modelAttribute="goodsreturn">
<table class="ordertab" style="background:white;" width="100%" cellspacing="1">
	<tr>
		<td colspan="3" align="left">
			不通过理由：<sf:input path="no_reason" cssClass="w400"/>
		</td>
	</tr>
	<tr>
		<td colspan="4" align="center">
			<button class="tab_btn" type="button" onclick="updateAuditSub(0);"/>通过</button>
			<button class="tab_btn" type="button" onclick="updateAuditSub(1);"/>不通过</button>
		</td>
	</tr>
</table>

<script>
	function updateAuditSub(val){
		$("#audit_code").val(val);
		$("#goodsreturnForm").submit();
	}
</script>

<sf:hidden path="trade_id" value="${goodsreturnMap.trade_id }"/>
<sf:hidden path="biz_type" value="${goodsreturnMap.biz_type }"/>
<input name="audit_code" type="hidden" id="audit_code"/>

</sf:form>

</c:if>

<!-- 等待卖家退款 -->
<c:if test="${goodsreturnMap.info_state==6}">
<table class="ordertab" style="background:white;" width="100%" cellspacing="1">
	<tr>
		<td colspan="4" align="center">
			<button class="tab_btn" type="button" onclick="updateAuditSub(0);"/>退款</button>
		</td>
	</tr>
</table>
</c:if>

</body>
</html>
