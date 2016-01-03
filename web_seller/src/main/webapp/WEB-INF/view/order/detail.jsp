<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<title>查看订单详细</title>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
<%@ include file="/WEB-INF/view/inc/uploadify/resource.jsp" %>
<style>
	.ordertab{background:#E7CA96;margin-top:10px;line-height:24px;}
	.ordertab h1{font-weight:bold;font-size:14px;}
	.ordertab tr{background:white;}
	.ordertab td{padding:6px;}
</style>
</head>
<body>

<div class="position">查看订单详细</div>
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
			下单时间:<fmt:formatDate value="${order.order_time}" pattern="yyyy-MM-dd HH:mm:ss" /><br/>
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
		<td>
			<h1>结算信息</h1>
		</td>
	</tr>
	<tr>
		<td>
			商品金额：￥${order.goods_amount} + 运费：￥<c:if test="${empty goodsorder.ship_free}">0</c:if><c:if test="${!empty goodsorder.ship_free}">${goodsorder.ship_free}</c:if> = 订单总金额：￥${order.tatal_amount}
		</td>
	</tr>
</table>

<c:if test="${order.order_state == '2' || order.order_state == '4' || order.order_state == '5' || order.order_state == '6'}">
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

<c:if test="${order.order_state == '1'}">
<sf:form method="post" action="order/deliver-${order.order_id }.action" modelAttribute="inc_shipinfo">
<a name="001" id="001" ></a>
<table class="ordertab" width="100%" cellspacing="1">
	<tr>
		<td>
			<h1>物流信息</h1>
		</td>
	</tr>
	<tr>
		<td>
			<table>
				<tr>
					<td>物流公司<span style="color: red;">*</span></td>
					<td>
						<sf:select path="ship_id" onblur="checkInputInfo('ship_id','物流公司');">
			       			<sf:option value="">请选择</sf:option>
							<sf:options items="${shipTypeMap}"></sf:options>
			       		</sf:select>
					</td>
				</tr>
				<tr><td></td><td><sf:errors path="ship_id" cssClass="error"/></td></tr>
				
				<tr>
					<td>运单号<span style="color: red;">*</span></td>
					<td>
						<sf:input path="ship_no" cssClass="w400" maxlength="100"/>
					</td>
				</tr>
				<tr><td></td><td><sf:errors path="ship_no" cssClass="error"/></td></tr>
				
				<tr>
					<td>发货说明</td>
					<td>
						<sf:textarea path="ship_desc" cssClass="txt4x1" style="vertical-align:top;" />
					</td>
				</tr>
				<tr><td></td><td><sf:errors path="ship_desc" cssClass="error"/></td></tr>
				
				<tr>
					<td>发货凭证</td>
					<td>
						<input type="hidden" name="imgNumLimit" id="imgNumLimit" value="2"/>
						<sf:hidden path="ship_img"/>
						<input id="file" name="file" type="file" />
						<div id="fileQueue"></div>
						<div class="img-lst"><ul id="imgView"></ul></div>
				        <script>
				        	$(function(){
				        		uploadImgComponent('<%=basePath%>','imgNumLimit','file','fileQueue','ship_img','imgView','seller_resend');
				        	})
				        </script>
					</td>
				</tr>
				<tr><td></td><td><sf:errors path="ship_img" cssClass="error"/></td></tr>
			</table>
		</td>
	</tr>
	<tr>
		<td><button class="tab_btn" type="submit"/>提交</button></td>
	</tr>
</table>
</sf:form>
</c:if>

<br/>


</body>
</html>
