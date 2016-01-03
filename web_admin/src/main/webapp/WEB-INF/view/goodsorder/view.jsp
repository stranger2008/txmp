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
	<c:forEach items="${orderdetail}" var="item">
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
			订单编号:&nbsp;&nbsp; ${goodsorder.order_id }<br/>
			订单状态:&nbsp;&nbsp; ${goodsorder.order_state_name }<br/>
			订单金额:&nbsp;&nbsp; ¥${goodsorder.tatal_amount }<br/>
			下单时间:&nbsp;&nbsp; <fmt:formatDate value="${goodsorder.order_time}" pattern="yyyy-MM-dd HH:mm:ss" /><br/>
		</td>
	</tr>
</table>

<table class="ordertab" width="100%" cellspacing="1">
	<tr>
		<td>
			<h1>卖家信息</h1>
		</td>
	</tr>
	<tr>
		<td>
			名称:&nbsp;&nbsp; ${goodsorder.cust_name}<br />
			手机:&nbsp;&nbsp; ${goodsorder.cust_mobile}<br/>
			地址:&nbsp;&nbsp; ${goodsorder.cust_area_attr_name}&nbsp;${goodsorder.cust_address}
		</td>
	</tr>
</table>

<table class="ordertab" width="100%" cellspacing="1">
	<tr>
		<td>
			<h1>买家信息</h1>
		</td>
	</tr>
	<tr>
		<td>
			账号:&nbsp;&nbsp; ${goodsorder.buy_cust_name}<br />
			手机:&nbsp;&nbsp; ${goodsorder.buy_cellphone}
		</td>
	</tr>
</table>

<table class="ordertab" width="100%" cellspacing="1">
	<tr>
		<td>
			<h1>收货人信息</h1>
		</td>
	</tr>
	<tr>
		<td>
			姓名:&nbsp;&nbsp; ${goodsorder.consignee}<br />
			手机:&nbsp;&nbsp; ${goodsorder.mobile}<br/>
			地址:&nbsp;&nbsp; ${goodsorder.buy_area_attr_name}&nbsp;${goodsorder.address}
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
			<c:if test="${goodsorder.invoice_type==2}">
				不开发票<br/>
			</c:if>
			
			<c:if test="${goodsorder.invoice_type==0}">
				普通发票<br/>
				发票抬头:&nbsp;&nbsp; ${goodsorder.invoice_top}<br/>
				<c:if test="${goodsorder.invoice_type=='单位'}">
					公司名称:&nbsp;&nbsp; ${goodsorder.company_name}<br/>
				</c:if>
				发票内容:&nbsp;&nbsp; ${goodsorder.invoice_content}<br/>
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
			商品金额:&nbsp;&nbsp; ￥${goodsorder.goods_amount} + 运费：￥<c:if test="${empty goodsorder.ship_free}">0</c:if><c:if test="${!empty goodsorder.ship_free}">${goodsorder.ship_free}</c:if> = 订单总金额：￥${goodsorder.tatal_amount}
		</td>
	</tr>
</table>

<c:if test="${goodsorder.order_state == '2' || goodsorder.order_state == '4' || goodsorder.order_state == '5' || goodsorder.order_state == '6'}">
<table class="ordertab" width="100%" cellspacing="1">
	<tr>
		<td>
			<h1>物流信息</h1>
		</td>
	</tr>
	<tr>
		<td>物流公司:&nbsp;&nbsp;${inc_shipinfo.ship_name }<br />
			&nbsp;&nbsp;&nbsp;运单号:&nbsp;&nbsp;${inc_shipinfo.ship_no }<br />
			发货说明:&nbsp;&nbsp;${inc_shipinfo.ship_desc }<br />
			发货凭证:&nbsp;&nbsp;
			<c:forEach items="${fn:split(inc_shipinfo.ship_img, ',') }" var="img">
				<c:if test="${!empty img }">
					<a href="${img }"><img src="${img }" width="90px" height="90px" /></a>
				</c:if>
			</c:forEach><br />
			发货时间:&nbsp;&nbsp;<fmt:formatDate value="${inc_shipinfo.in_date}" pattern="yyyy-MM-dd HH:mm:ss" />
		</td>
	</tr>
</table>
</c:if>

</body>
</html>
