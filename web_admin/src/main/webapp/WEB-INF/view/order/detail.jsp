<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<title>查看订单详细</title>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
</head>
<body>

<div class="position">查看订单详细</div>

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
		<td width="110">图片</td>
		<td width="480">商品名称</td>
		<td>价格</td>
		<td>商品数量</td>
	</tr>
	<c:forEach items="${order.orderdetails}" var="item">
	<tr>
		<td>
			<c:if test="${!empty item.img_path}">
              <img src="<h:imgSubstr imgpath="${item.img_path}"/>" onload="DrawImage(this,90,90)"/>
            </c:if>
		</td>
		<td>
			${item.goods_name}
		</td>
		<td>
			￥${item.order_price}
		</td>
		<td>
			${item.order_num}
		</td>
	</tr>
	</c:forEach>
</table>


<table class="ordertab" width="100%" cellspacing="1">
	<tr>
		<td colspan="4">
			<h1>订单信息</h1>
		</td>
	</tr>
	<tr>
		<td>
			订单编号:${order.order_id }<br/>
			订单状态:${order_state_name }<br/>
			订单金额:¥${order.tatal_amount }<br/>
			下单时间:${order.order_time }<br/>
		</td>
	</tr>
</table>

<table class="ordertab" width="100%" cellspacing="1">
	<tr>
		<td colspan="4">
			<h1>收货人信息</h1>
		</td>
	</tr>
	<tr>
		<td>
			${order.consignee}&nbsp;${order.mobile}<br/>
			${area_name_str}${order.address}
		</td>
	</tr>
</table>

<table class="ordertab" width="100%" cellspacing="1">
	<tr>
		<td colspan="4">
			<h1>发票信息</h1>
		</td>
	</tr>
	<tr>
		<td>
			<c:if test="${order.invoice_type==2}">
				不开发票<br/>
			</c:if>
			
			<c:if test="${order.invoice_type==0}">
				普通发票<br/>
				发票抬头：${order.invoice_top}<br/>
				<c:if test="${order.invoice_type=='单位'}">
					公司名称：${order.company_name}<br/>
				</c:if>
				发票内容：${order.invoice_content}<br/>
			</c:if>
			
			
			<c:if test="${order.invoice_type==1}">
				增值税发票<br/>
				单位名称：${order.company_name}<br/>
				纳税人识别号：${order.ident_number}<br/>
				注册地址：${order.regis_address}<br/>
				注册电话：${order.regis_tel}<br/>
				开户银行：${order.open_bank}<br/>
				银行帐户：${order.bank_account}<br/>
				发票内容：${order.invoice_content}<br/>
				
			</c:if>
		</td>
	</tr>
</table>

<table class="ordertab" width="100%" cellspacing="1">
	<tr>
		<td colspan="4">
			<h1>结算信息</h1>
		</td>
	</tr>
	<tr>
		<td>
			商品金额：￥${order.goods_amount} + 运费：￥${order.ship_free} = 订单总金额：￥${order.tatal_amount}
		</td>
	</tr>
</table>

<c:if test="${order.order_state==1}">
<table class="ordertab" style="background:white;" width="100%" cellspacing="1">
	<tr>
		<td colspan="4" align="center">
			<button class="tab_btn" type="button" onclick="location.href='<%=basePath%>order/deliver-${order.order_id }.action';"/>发货</button>
		</td>
	</tr>
</table>
</c:if>

<br/>


</body>
</html>
