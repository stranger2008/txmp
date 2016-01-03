<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
  	<title>订单详情</title>
    <%@ include file="/WEB-INF/view/inc/inc.jsp" %>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" href="<%=rootPath %>css/public.css" />
    <link rel="stylesheet" href="<%=rootPath %>css/product.css" />
    <link rel="stylesheet" href="<%=rootPath %>css/account.css" />
    <script src="<%=rootPath %>js/jquery.min.js" type="text/javascript"></script>
    <script src="<%=rootPath %>js/core.js"></script>
    <script src="<%=rootPath %>js/account.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/inc/js/airlinecompany.js"></script>
  </head>
  
  <body>
<!--头部开始-->
<%@ include file="/WEB-INF/view/inc/top.jsp" %>
<!--头部下搜索框-->
<%@ include file="/WEB-INF/view/user/inc/top.jsp" %>		 
<!--nav开始-->
<%@ include file="/WEB-INF/view/user/inc/nav.jsp" %>		

<!--个人中心-->
<div class="w1200">
	<div class="account-order">
        <div class="account-crumbs">
            <strong>我的天下名品</strong><span>&gt;<a href="<%=basePath %>user/orderlist.action">订单中心</a>&gt; 订单：${order.order_id }</span>
        </div>
        <div class="account-o-state">
            订单号：${order.order_id }&nbsp;&nbsp;<span>状态：${order.order_state_name }</span>
            <c:if test="${order.order_state==0}">
            	<a class="btn-red" style="margin-left:20px" href="<%=basePath %>order/ordersuccess.action?goodsTotalPrice=${order.tatal_amount}&order_id=${order.order_id}"><span></span>立即付款</a>
            </c:if>
            <c:if test="${order.order_state==2}"><a href="<%=basePath %>user/confirmReceipt-${order.order_id}.action">确认收货</a></c:if>
        </div>
        <div class="account-o-track">
            <p>订单跟踪</p>
            <table width="1170" border="0" class="account-o-t-item" cellpadding="0" cellspacing="0">
                <tr class="account-o-t-head">
                    <td width="180">处理时间</td>
                    <td width="640">处理信息</td>
                    <td>操作人</td>
                </tr>
                <c:forEach items="${orderhis}" var="item" varStatus="status">
                <tr>
                    <td><fmt:formatDate value="${item.in_date}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                    <td>${item.action_name}</td>
                    <td>
                    	<c:if test="${item.user_id!=null&&item.user_id!=''}">买家</c:if>
                    	<c:if test="${item.user_id==null||item.user_id==''}">系统</c:if>
                    </td>
                </tr>
                </c:forEach>
            </table>
        </div>
        <!-- 实体商品 -->
        <c:if test="${order.order_type == 'goods' }">
        <div class="account-o-track">
			<p>订单信息</p>
            <div class="payment-way">
				<strong>支付及配送方式</strong><br/>
				支付方式：在线支付<br/>
				运费：<c:if test="${empty order.ship_free }">¥0.00</c:if>
					<c:if test="${!empty order.ship_free }">
						<fmt:formatNumber type="number" pattern="¥0.00" value="${order.ship_free } " maxFractionDigits="2" />
					</c:if><br/>
					<br/>
				<div class="account-send-goods">
					<strong>收货人信息</strong><br/>
					收货人：${order.consignee}<br/>
					地址：${fn:replace(order.buy_area_attr_name, ",", "")}${order.address}<br/>
					手机号码：${order.mobile}<br/>
				</div>
			</div>
			<div class="payment-way">
				<strong>发票信息</strong><br/>
				<c:if test="${order.invoice_type==2}">
					不开发票
				</c:if>
				<c:if test="${order.invoice_type==0||order.invoice_type==1}">
					发票类型：<c:if test="${order.invoice_type==0}">普通发票</c:if>
					<c:if test="${order.invoice_type==1}">增值税发票</c:if><br/>
					发票抬头：${order.invoice_top}<br/>
					发票内容：${order.invoice_content}
				</c:if>
			</div>
			<div class="account-goods">
				<strong>商品清单</strong>
				<table width="100%" border="0" style="margin-top: 10px;" cellpadding="0" cellspacing="1">
					<tr bgcolor="#f2f7ff" style="text-align: center">
						<td width="685">商品名称</td>
						<td width="210">天下名品价(优惠后)</td>
						<td width="105">商品数量</td>
						<td>操作</td>
					</tr>
				</table>
				<c:forEach items="${order.orderdetails}" var="item" varStatus="status">
				<table width="100%" border="0" class="account-g-item" cellspacing="0" cellpadding="10">
					<tr>
						<td width="685">
							<c:if test="${!empty item.img_path}">
							<a href="<%=basePath %>goods/${item.goods_id}.action" >
								<img src="<h:imgSubstr imgpath="${item.img_path}"/>" width="50" height="50"/>
							</a>
							</c:if>
							<a href="<%=basePath %>goods/${item.goods_id}.action">${item.goods_name}</a>
						</td>
						<td align="center" width="210">¥${item.order_price}</td>
						<td align="center" width="105">${item.order_num}</td>
						<td align="center">
							<!-- 交易关闭 -->
							<c:if test="${order.order_state == '3' }">
								<a href="#">放入购物车</a>
							</c:if>
							<!-- 交易成功 -->
							<c:if test="${order.order_state == '4' }">
								<a href="#">申请返修/退换货</a>
								<span class="account-again-buy">还要买</span>
							</c:if>
						</td>
					</tr>
				</table>
				</c:forEach>
			</div>
			<div class="account-g-money">
				<em>(${fn:length(order.orderdetails) })</em>件商品&nbsp;总计：¥${order.goods_amount}<br />
				运费：<c:if test="${empty order.ship_free }">¥0.00</c:if>
					<c:if test="${!empty order.ship_free }">
						<fmt:formatNumber type="number" pattern="¥0.00" value="${order.ship_free } " maxFractionDigits="2" />
					</c:if><br/>
				<span style="color: #5d5d5d;font-size: 20px;font-weight: bold;;">应付总额：<strong style="color: #e10000">¥${order.tatal_amount}</strong></span>
			</div>
		</div>
        </c:if>
        <c:if test="${order.order_type == 'airline' }">
        <div class="account-o-track">
			<p>订单信息</p>
			<div class="payment-way">
				<strong>联系人信息</strong><br/>
				姓名:&nbsp;&nbsp;${order.orderdetails[0].contact_linkMan }<br />
				手机:&nbsp;&nbsp;${order.orderdetails[0].contact_linkphone }<br />
				电子邮箱:&nbsp;&nbsp;${order.orderdetails[0].contact_linkmail }<br />
			</div>
			<div class="account-goods">
				<strong>商品清单</strong>
				<table width="100%" border="0" style="margin-top: 10px;" cellpadding="0" cellspacing="1">
					<tr bgcolor="#f2f7ff" style="text-align: center">
						<td>航程</td>
						<td>起飞时间</td>
						<td>抵达时间</td>
						<td>航班信息</td>
						<td>乘机人</td>
					</tr>
					<c:forEach items="${order.orderdetails}" var="item" varStatus="status">
					<tr style="text-align: center">
						<td>${item.orgCity }-${item.dstCity }</td>
						<td>${item.date }&nbsp;${item.depTime }</td>
						<td>${item.date }&nbsp;${item.arriTime }</td>
						<td>${item.flightNo }(<script type="text/javascript"> document.write(company['${fn:substring(item.flightNo, 0, 2)}']);  </script>)</td>
						<td>${item.passenger_name }</td>
					</tr>
					</c:forEach>
				</table>
			</div>
			<div class="account-g-money">
				<em>(${fn:length(order.orderdetails) })</em>件商品&nbsp;总计：${order.goods_amount}<br />
				运费：<c:if test="${empty order.ship_free }">¥0.00</c:if>
					<c:if test="${!empty order.ship_free }">
						<fmt:formatNumber type="number" pattern="¥0.00" value="${order.ship_free } " maxFractionDigits="2" />
					</c:if><br/>
				<span style="color: #5d5d5d;font-size: 20px;font-weight: bold;;">应付总额：<strong style="color: #e10000">¥${order.tatal_amount}</strong></span>
			</div>
		</div>
        </c:if>
        <c:if test="${order.order_type == 'movie' }">
        <div class="account-o-track">
			<p>订单信息</p>
			<div class="payment-way">
				<strong>个人信息</strong><br/>
				手机:&nbsp;&nbsp;${order.mobile }
			</div>
			<div class="payment-way">
				<strong>支付信息</strong><br/>
				支付方式:&nbsp;&nbsp;在线支付<br />
				应付总额:&nbsp;&nbsp;${order.tatal_amount }
			</div>
			<div class="account-goods">
				<strong>购票信息</strong>
				<table width="100%" border="0" style="margin-top: 10px;" cellpadding="0" cellspacing="1">
					<tr bgcolor="#f2f7ff" style="text-align: center">
						<td>影院</td>
						<td>影厅编号</td>
						<td>座位号</td>
						<td>影片</td>
						<td>购票数量</td>
						<td>单价</td>
					</tr>
					<c:forEach items="${order.orderdetails}" var="item" varStatus="status">
					<tr style="text-align: center">
						<td>${item.cinemaName }</td>
						<td>${item.hallNo }</td>
						<td>${item.seats }</td>
						<td>${item.filmName }</td>
						<td>${item.goodsAmount }</td>
						<td>¥${item.singlePrice }
					</tr>
					</c:forEach>
				</table>
			</div>
			<div class="account-g-money">
				总计：¥${order.goods_amount}<br />
				运费：<c:if test="${empty order.ship_free }">¥0.00</c:if>
					<c:if test="${!empty order.ship_free }">
						<fmt:formatNumber type="number" pattern="¥0.00" value="${order.ship_free } " maxFractionDigits="2" />
					</c:if><br/>
				<span style="color: #5d5d5d;font-size: 20px;font-weight: bold;;">应付总额：<strong style="color: #e10000">¥${order.tatal_amount}</strong></span>
			</div>
		</div>
        </c:if>
    </div>
</div>

<div class="clearfix"></div>

<!--底部-->
<%@ include file="/WEB-INF/view/inc/footer.jsp" %>
  </body>
</html>
