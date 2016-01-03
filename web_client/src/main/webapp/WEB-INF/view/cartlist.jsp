<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/WEB-INF/view/inc/inc.jsp"%>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link rel="stylesheet" href="<%=basePath%>inc/css/public.css">
		<link rel="stylesheet" href="<%=basePath%>inc/css/sign.css">
		<link rel="stylesheet" href="<%=basePath%>inc/css/product.css">
		<script src="<%=basePath%>inc/js/jquery.min.js" type="text/javascript"></script>
		<script src="<%=basePath%>inc/js/core.js"></script>
		<script type="text/javascript" src="<%=basePath%>inc/goods_js/cartInfo.js"></script>
		<title>购物车</title>
	</head>
	<body>
		<!--头部开始-->
		<%@ include file="/WEB-INF/view/inc/top.jsp"%>

		<!--购物车-->
		<div class="w1200">
			<div class="logo-sign fl">
				<a href="<%=basePath%>/index.action"><img src="<%=basePath%>inc/images/logo-sign.png"> </a>
			</div>
			<div class="progress fr">
				<ul class="progress1">
					<li class="step-h">
						1 我的购物车
					</li>
					<li class="step">
						2 填写核对订单
					</li>
					<li class="step">
						3 成功提交订单
					</li>
				</ul>
			</div>
		</div>
		<div class="clearfix"></div>

		<c:if test="${!empty cartlist}">
			<div class="w1200 cart">
				<div class="cart-hd">
					<h2 class="fl">
						我的购物车
					</h2>
					<c:if test="${empty sessionScope.session_user_name}">
						<span><a href="<%=basePath%>login.action" class="btn-ct">登录</a><span>登录后可同步车中商品！</span> </span>
					</c:if>
				</div>
				<div class="cart-cont">
					<div class="cart-thead">
						<div class="colum fl">
							<input type="checkbox" name="iptCheckbox" checked />
							<label>
								全选
							</label>
						</div>
						<div class="goods fl">
							<label>
								商品名称
							</label>
						</div>
						<div class="price fl">
							<label>
								天下名品价(元)
							</label>
						</div>
						<div class="price fl">
							<label>
								优惠（元）
							</label>
						</div>
						<div class="price fl">
							<label>
								金额小计
							</label>
						</div>
						<div class="price fl">
							<label>
								数量
							</label>
						</div>
						<div class="price fl">
							<label>
								操作
							</label>
						</div>
					</div>
					<div>
						<c:forEach items="${cartlist}" var="item" varStatus="status">
							<div class="item-header">
	
								<div class="ct-shop fl">
									<label>
										店铺：
										<a href="<%=basePath %>shop/${item.seller.id }.action">${item.seller.name }</a>
									</label>
								</div>
							</div>
						
							<div class="bc-item">
								<div class="item-form">
									<div class="cell item-chk">
										<input type="checkbox" name="itemCheck" id="ch_${status.index}" value="${item.hash_code}" checked />
									</div>
									<div class="cell">
										<div class="item-img fl">
											<a href="<%=basePath%>goods/${item.goods.goods_id}.action"> <c:if test="${!empty item.goods.img_path}">
													<img src="<h:imgSubstr imgpath="${item.goods.img_path}"/>" onload="DrawImage(this,90,90)" />
												</c:if> </a>
										</div>
										<div class="item-name fl">
											<a href="<%=basePath%>goods/${item.goods.goods_id}.action" class="product-h4"><b>${item.goods.goods_name}${item.goods_param }</b> </a>
										</div>
									</div>
									<div class="cell price">
										<div class="item-price" id="sale_price${status.index}">
											¥${item.goods.sale_price}
										</div>
									</div>
									<div class="cell price">
										<div class="item-price">
											¥0.00
										</div>
									</div>
									<div class="cell price">
										<div class="item-price" id="itemprice${status.index}">
											<fmt:formatNumber type="number" pattern="¥0.00" value="${item.goods.sale_price * item.amount } " maxFractionDigits="2"/>
										</div>
									</div>
									<div class="cell quantity">
										<div class="quantity-form">
											<a onclick="setGoodsNum(-1,${status.index},'${item.hash_code}',${item.goods.sale_price})" class="fl">-</a>
											<input type="text" maxlength="4" class="quantity-txt fl" id="goodsOneNum${status.index}" value="${item.amount}" onkeyup="setGoodsNum(null,${status.index},'${item.hash_code}',${item.goods.sale_price});"/>
											<a onclick="setGoodsNum(1,${status.index},'${item.hash_code}',${item.goods.sale_price})" class="fl">+</a>
										</div>
									</div>
									<div class="cell price">
										<div class="do-btn">
											<a href="javascript:addCollect();">收藏</a>
											<a href="<%=basePath%>cartdel.action?id=${item.goods.goods_id}&hashcode=${item.hash_code}">删除</a>
										</div>
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
				<div class="item-total">
					<div class="total fr">
						<p>
							总计：<span id="totalMoney">¥${goodsTotalPrice }</span>
						</p>
						<p>
							优惠：<span>¥0.00</span>
						</p>
					</div>
				</div>

				<div class="item-total2">
					<div class="colum fl">
						<input type="checkbox" name="iptCheckbox" checked />
						<label>
							全选
						</label>
					</div>
					<div class="total-a fl">
						<a href="javascript:void(0);" onclick="delAll('');">删除所选</a><a href="<%=basePath%>">继续购物</a>
					</div>
					<div class="total-btn fr">
						<a href="javascript:goOrder();">去结算<b>></b> </a>
					</div>
					<div class="total2 fr">
						<input type="hidden" id="goodsTotalPrice_inp" value="${goodsTotalPrice }"/>
						<span id="tatalGoods">${cartsize }</span> 件商品总计（含运费）：
						<em id="goodsTotalPrice">¥${goodsTotalPrice }</em>
					</div>
				</div>
			</div>
		</c:if>

		<c:if test="${empty cartlist}">
			<div class="w1200 cart">
				<div class="cart-hd">
					<h2 class="fl">
						我的购物车
					</h2>
				</div>
				<c:if test="${empty sessionScope.session_user_name}">
					<div class="none-buy default-line">
						<em></em><span>购物车内暂时没有商品，<a class="a-lan" href="<%=basePath%>login.action">登录</a>后，将显示您之前加入的商品<br />
							<a class="a-lan" href="<%=basePath%>">去首页</a>挑选喜欢的商品</span>
					</div>
				</c:if>
				<c:if test="${!empty sessionScope.session_user_name}">
					<div class="none-buy default-line">
						<em></em><span>购物车内暂时没有商品，您可以<a class="a-lan" href="<%=basePath%>">去首页</a>挑选喜欢的商品</span>
					</div>
				</c:if>
			</div>
		</c:if>

		<div class="w1200">
			<div class="clearfix">
				<!--猜你喜欢-->
				<div class="like">
					<h2>
						猜你喜欢
					</h2>
					<div class="track">
						<div class="inner">

							<div class="view-port">
								<div id="example-2" class="slider-container big">
								
								<c:forEach items="${guessList}" var="goods" varStatus="status">
									<div class="item">
										<div class="like-box">
											<span class="img-like">
												<a href="<%=basePath %>goods/${goods.goods_id}.action" class="like-img" target="_blank">
					                            	<c:if test="${!empty goods.img_path}">
						                        		<img src="<h:imgSubstr imgpath="${goods.img_path}"/>" onload="DrawImage(this,90,90)"/>
					                        		</c:if>
					                            </a> 
				                            </span>
											<div class="ll">
												<p>
													<a href="<%=basePath%>goods/${goods.goods_id}.action" title="${goods.goods_name}">
														 <c:choose>
															<c:when test="${fn:length(goods.goods_name) > 20}">
																<c:out value="${fn:substring(goods.goods_name, 0, 20)}" />
															</c:when>
															<c:otherwise>
																<c:out value="${goods.goods_name}" />
															</c:otherwise>
														</c:choose>
													</a>
												</p>
												<div class="red">
													<span>¥${goods.sale_price}</span>
												</div>
											</div>
										</div>
									</div>
								</c:forEach>
								</div>
							</div>

							<div class="pagination">
								<a href="###" class="prev disabled" style="top: 70px;"></a>
								<a href="###" class="next disabled" style="top: 70px;"></a>
							</div>

						</div>
					</div>
				</div>
			</div>
		</div>

		<!--底部-->
		<%@ include file="/WEB-INF/view/inc/footer.jsp"%>
		<script>
				function goOrder(){
					var hashVal = "";
					$("input[name='itemCheck']").each(function(){
						if($(this).attr("checked") == 'checked'){
							hashVal = hashVal + $(this).val()+",";
						}
					});
					if(hashVal == ""){
						alert("请选择商品再结算");
					}else{
						window.location.href="<%=basePath%>order/order.action?hashcode="+hashVal;
					}
				}
			
				$("input[name='iptCheckbox']").click(function(){ 
					if($(this).attr("checked") == 'checked'){
						$("input[name='itemCheck']").attr("checked",true);
						$("input[name='iptCheckbox']").attr("checked",true);
					}else{
						$("input[name='itemCheck']").attr("checked",false);
						 $("input[name='iptCheckbox']").attr("checked",false);
					}
				});
				
				function delAll(id){
					 var s=''; 
					  $('input[name="itemCheck"]:checked').each(function(){ 
					    s+=$(this).val()+','; 
					  }); 
					  if(s!=''){
						location.href="<%=basePath%>cartdel.action?id="+id+"&hashcode="+s;  
					  }else{
					  	alert("请勾选删除!");
					  	return false;
					  }
					 
				}
				
				function setGoodsNum(flag,indx,hashcode,itemprice){
					var gnum = $("#goodsOneNum"+indx).val()*1;
					if(flag){
						var goodsNum =${cartsize}
						if(gnum*1 > 1000){
							gnum = 1000;
						}
						if(flag == -1){
							if(gnum > 1){
								--gnum;
							}
						}else{
							if(gnum < 1000){
								++gnum;
							}
						}
					} 
					$("#goodsOneNum"+indx).val(gnum);
					$("#itemprice"+indx).html("¥"+toDecimal2(gnum*1*itemprice));
					
					$.ajax({
						type: "get",
						url: "<%=basePath%>cartedit.action?hashcode="+hashcode+"&num="+gnum,
						success: function(data, textStatus){
							var ck = $("#ch_"+indx).attr("checked");
							if(ck){
								var result = eval("("+data+")");
								$("#goodsTotalPrice").html(formatCurrency(result.tatalPrice));
								$("#goodsTotalPrice_inp").val(result.tatalPrice);
								$("#totalMoney").html(formatCurrency(result.tatalPrice));
								$("#tatalGoods").text(result.totalNum);
							}
						}
					});
				}
			//保留两位小数	
			function toDecimal2(x) {    
		            var f = parseFloat(x);    
		            if (isNaN(f)) {    
		                return false;    
		            }    
		            var f = Math.round(x*100)/100;    
		            var s = f.toString();    
		            var rs = s.indexOf('.');    
		            if (rs < 0) {    
		                rs = s.length;    
		                s += '.';    
		            }    
		            while (s.length <= rs + 2) {    
		                s += '0';    
		            }    
		            return s;    
		        }    
		        //保留一位小数	
			function toDecimal2_bac(x) {    
		            var f = parseFloat(x);    
		            if (isNaN(f)) {    
		                return false;    
		            }    
		            var f = Math.round(x*100)/100;    
		            var s = f.toString();    
		            var rs = s.indexOf('.');    
		            if (rs < 0) {    
		                rs = s.length;    
		                s += '.';    
		            }    
		            while (s.length <= rs + 1) {    
		                s += '0';    
		            }    
		            return s;    
		        }  
				 //商品收藏
            function addCollect(){
				$.ajax({
				type: "get",
				url: "<%=basePath%>collectadd.action?info_id=${goods.goods_id}&info_type=0",
					success: function(data, textStatus){
						if(data == "1"){
							alert("收藏成功");
						}else if(data == "2"){
							alert("您已收藏该商品");
						}else if(data == "3"){
							alert("请您先登录，再收藏");
						}
					}
				});
			}
			</script>
	</body>
</html>

