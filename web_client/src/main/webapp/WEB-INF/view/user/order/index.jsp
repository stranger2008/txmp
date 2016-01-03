<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>

		<%@ include file="/WEB-INF/view/inc/inc.jsp"%>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link rel="stylesheet" href="<%=rootPath%>css/public.css" />
		<link rel="stylesheet" href="<%=rootPath%>css/product.css" />
		<link rel="stylesheet" href="<%=rootPath%>css/account.css" />
		<script src="<%=rootPath%>js/jquery.min.js" type="text/javascript"></script>
		<script src="<%=rootPath%>js/core.js"></script>
		<script src="<%=rootPath%>js/account.js"></script>
		<title>我的订单</title>


	</head>

	<body>
		<!--头部开始-->
		<%@ include file="/WEB-INF/view/inc/top.jsp"%>
		<!--头部下搜索框-->
		<%@ include file="/WEB-INF/view/user/inc/top.jsp"%>
		<!--nav开始-->
		<%@ include file="/WEB-INF/view/user/inc/nav.jsp"%>
		<!--个人中心-->
		<div class="w1200">
			<div class="usercenter">
				<div class="position">
					<a href="#"><strong>我的天下名品</strong> </a><span></span><a href="#">账户安全</a>
				</div>

				<!--会员中心左边导航-->
				<%@ include file="/WEB-INF/view/user/inc/left.jsp"%>

				<sf:form action="user/orderlist.action" id="orderForm"
					modelAttribute="goodsorderQueryForm">
					<div class="user-info fr">
						<!--我的订单-->
						<div class="my-order">
							<div style="border: 1px solid #ddd;">
								<div class="my-order-search">
									<div id="headdiv" class="my-order-box">
										<span><a href="<%=basePath%>user/orderlist.action">全部订单</a>
											<script type=""></script> </span>
										<em></em>
										<span><a href="<%=basePath%>user/deletelist.action">回收站</a>
										</span>
										<div class="none">
											<i></i>
											<ul
												style="height: auto; overflow: hidden; padding: 15px 0; border: 2px solid #e6e6e6; background: #fff; position: relative; top: 21px; left: -1px">
												<li>
													不限：
													<a>全部类型</a>
												</li>
												<li>
													普通：
													<a>彩票</a>|
													<a>彩票</a>
												</li>
												<li>
													旅游：
													<a>彩票</a>|
													<a>彩票</a>
												</li>
												<li>
													充值：
													<a>彩票</a>|
													<a>彩票</a>
												</li>
												<li>
													票务：
													<a>彩票</a>|
													<a>彩票</a>
												</li>
												<li>
													数字：
													<a>彩票</a>|
													<a>彩票</a>
												</li>
												<li>
													其他：
													<a>彩票</a>|
													<a>彩票</a>
												</li>
											</ul>
										</div>
									</div>
									<c:if test="${goodsorderQueryForm.is_del == '0' }">
										<p>
											<sf:input path="keywords" placeholder="商品名称、订单编号"
												cssClass="my-o-s-input" />
											<a class="my-o-s-go" href="javascript:void(0);"
												onclick="document.getElementById('orderForm').submit();">查询</a>
										</p>
									</c:if>
								</div>
								<c:if test="${empty pageResult.list}">
									<center>
										无数据记录
									</center>
								</c:if>
								<c:forEach items="${pageResult.list}" var="item"
									varStatus="status">

									<c:if test="${item.is_del==0}">
										<c:if test="${status.index == '0'&& item.is_del==0}">
											<table id="headtab" width="100%" class="my-order-head"
												border="0" style="margin-top: 10px;" cellpadding="0"
												cellspacing="0">
												<tr bgcolor="#e7e7e7" style="text-align: center">

													<td width="380">
														订单信息
													</td>
													<td width="130">
														收货人
													</td>
													<td width="130">
														订单金额
													</td>
													<td width="130">
														<sf:select path="sear_days" items="${seardaysMap}"
															onchange="document.getElementById('orderForm').submit();" />
													</td>
													<td width="130">
														<sf:select path="order_state" items="${orderStateMap}"
															onchange="document.getElementById('orderForm').submit();" />
													</td>
													<td>
														操作
													</td>
												</tr>
											</table>
										</c:if>


										<table width="100%" border="0" class="account-g-item"
											cellspacing="0" cellpadding="10">
											<tr>
												<td bgcolor="#f5f5f5" colspan="6">
													&nbsp;&nbsp;订单编号：
													<span style="color: #005ea7;"><a
														href="<%=basePath%>user/order-${item.order_id}.action">${item.order_id}</a>
													</span>
												</td>
											</tr>
											<tr>
												<td width="380" valign="middle">
												<c:forEach items="${item.ordergoods}" var="bean">
														<c:if test="${item.order_type == 'goods' }">
															<c:if test="${!empty bean.img}">
																<a href="<%=basePath%>goods/${bean.id }.action"><img
																		src="<h:imgSubstr imgpath="${bean.img}"/>" /> </a>
															</c:if>
														</c:if>
														<c:if test="${item.order_type != 'goods' }">
															<a href="<%=basePath%>goods/${bean.id }.action">${bean.name
																}</a>
														</c:if>
													
												</c:forEach>
												</td>
												<td width="130" align="center" valign="middle">
													${item.consignee }
												</td>
												<td align="center" width="130">
													¥${item.tatal_amount}
													<br />
													在线支付
												</td>
												<td align="center" width="130">
													<fmt:formatDate value="${item.order_time}"
														pattern="yyyy-MM-dd HH:mm:ss" />
												</td>
												<td width="130" align="center">
													<span class="my-order-payment">${item.order_state_name}</span><br/><br/>
                                					<span><a href="<%=basePath %>user/add_complaint.action?id=${item.order_id}">投诉</a></span>
												</td>
												<td align="center">
													<c:if test="${item.order_state==0}">
														<a target="_blank" class="my-order-check"
															href="<%=basePath%>order/ordersuccess.action?goodsTotalPrice=${item.tatal_amount }&order_id=${item.order_id}">付款</a>
														<br />
													</c:if>
													<a href="<%=basePath%>user/order-${item.order_id}.action">查看订单</a>
													<br />
													<c:if test="${item.order_state==0}">
														<a
															href="<%=basePath%>user/cancelOrder-${item.order_id}.action">取消订单</a>
														<br />
													</c:if>
													<c:if test="${item.order_state==3}">
														<a
															href="<%=basePath%>user/cancellist-${item.order_id}.action">删除订单</a>
														<br />
													</c:if>
													<c:if test="${item.order_state==4}">
														<c:if test="${item.haseval == 0}">
														<a href="<%=basePath%>goodseval/index-${item.order_id}-${item.sale_cust_id}.action" onclick="hidetable()">评价</a>
														</c:if>
														<c:if test="${item.haseval == 1}">
														<a href="<%=basePath%>goodseval/index-${item.order_id}-${item.sale_cust_id}.action" onclick="hidetable()">查看评价</a>
														</c:if>
														
													</c:if>
													<script type="text/javascript">
					                                	function hidetable(){
					                                		$(".my-order-head").hide();
					                                		$(".account-g-item").hide();
					                                		$(".my-order").hide();
					                                		$(".mer-eval").show();
					                                		$(".mymsg").show();
					                                	}
					                                </script>

													<!--                                <a>申请退/换货</a>-->
													<!--                                <span class="account-again-buy">还要买</span>-->
												</td>
											</tr>
										</table>
									</c:if>
									<c:if test="${item.is_del==1}">
										<c:if test="${status.index == '0' && item.is_del==1}">
											<table width="100%" class="my-order-head" border="0"
												style="margin-top: 10px;" cellpadding="0" cellspacing="0">
												<tr bgcolor="#e7e7e7" style="text-align: center">

													<td width="380">
														订单信息
													</td>
													<td width="130">
														订单金额
													</td>
													<td width="130">
														订单日期
													</td>
													<td>
														操作
													</td>
												</tr>
											</table>
										</c:if>

										<table width="100%" border="0" class="account-g-item"
											cellspacing="0" cellpadding="10">
											<tr>
												<td bgcolor="#f5f5f5" colspan="6">
													&nbsp;&nbsp;订单编号：
													<span style="color: #005ea7;"><a
														href="<%=basePath%>user/order-${item.order_id}.action">${item.order_id}</a>
													</span>
												</td>
											</tr>
											<tr>
												<td width="380" valign="middle">
													<c:forEach items="${item.ordergoods}" var="bean">
														<c:if test="${item.order_type == 'goods' }">
															<c:if test="${!empty bean.img}">
																<a href="<%=basePath%>goods/${bean.id }.action"><img
																		src="<h:imgSubstr imgpath="${bean.img}"/>"  /> </a>
															</c:if>
														</c:if>
														<c:if test="${item.order_type != 'goods' }">
															<a href="<%=basePath%>goods/${bean.id }.action">${bean.name
																}</a>
														</c:if>
													</c:forEach>
												</td>
												<td align="center" width="130">
													¥${item.tatal_amount}
													<br />
													在线支付
												</td>
												<td align="center" width="130">
													<fmt:formatDate value="${item.order_time}"
														pattern="yyyy-MM-dd HH:mm:ss" />
												</td>
												<td align="center">
													<a
														href="<%=basePath%>user/returncancel-${item.order_id}.action">还原</a>
													<br />
													<a href="<%=basePath%>user/remove-${item.order_id}.action">彻底删除</a>
													<br />

													<!--                                <a>申请退/换货</a>-->
													<!--                                <span class="account-again-buy">还要买</span>-->
												</td>
											</tr>
										</table>
									</c:if>
								</c:forEach>
								<div class="subtr">

									<table width="100%" border="0" cellspacing="0" cellpadding="0"
										class="store-action">
										<tr>
											${pageBar}
											<!-- 以下样式暂时不用 -->
											<!--                            <td width="50">-->
											<!--                                <input type="checkbox" />&nbsp;全选-->
											<!--                            </td>-->
											<!--                            <td width="110">-->
											<!--                                <span class="account-page-prev">删除</span>-->
											<!--                            </td>-->
											<!--                            <td>&nbsp;</td>-->
											<!--                            <td>&nbsp;</td>-->
											<!--                            <td align="right">-->
											<!--                                <div class="account-page">-->
											<!--                                    <span class="account-page-num">1</span>/1-->
											<!--                                    <span class="account-page-prev"><em></em>上一页</span>-->
											<!--                                    <span>1</span>-->
											<!--                                    <span class="account-page-next">下一页<em></em></span>-->
											<!--                                    跳转到第<input class="account-page-input" type="text"/>页-->
											<!--                                    <a class="btn-ct" href="#">确定</a>-->
											<!--                                </div>-->
											<!--                            </td>-->
										</tr>
									</table>
									<!--猜你喜欢-->
									<!-- 
                    <div class="shop-like">
                        <h1>猜你喜欢</h1>
                        <div class="shop-l-c" id="imgRoll1">
                            <div class="shop-l-item">
                                <ul>
                                	<c:forEach items="${userLikeGoods }" var="goods">
                                	<li>
                                		<a href="<%=basePath%>goods/${goods.goods_id }.action"><img src="${goods.img_path }" style="width:130px; height: 130px;" /></a>
                                		<a href="<%=basePath%>goods/${goods.goods_id }.action">${goods.goods_name }</a>
                                		<span><a href="<%=basePath%>goods/${goods.goods_id }.action">${goods.sale_price }</a></span>
                                	</li>
                                	</c:forEach>
                                </ul>
                            </div>
                        </div>
                    </div>
                     -->
								</div>
							</div>
						</div>
						
					</div>
				</sf:form>
			</div>
		</div>
		<div class="clearfix"></div>
		<!--底部-->
		<%@ include file="/WEB-INF/view/inc/footer.jsp"%>


	</body>
</html>
