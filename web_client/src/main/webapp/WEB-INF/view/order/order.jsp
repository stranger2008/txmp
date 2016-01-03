<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/WEB-INF/view/inc/inc.jsp"%>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link rel="stylesheet" href="<%=basePath%>inc/css/public.css"/>
		<link rel="stylesheet" href="<%=basePath%>inc/css/product.css"/>
		<script type="text/javascript" src="<%=basePath%>inc/js/jquery.silver_track.js" charset="utf-8"></script>
		<script type="text/javascript" src="<%=basePath%>inc/js/jquery.silver_track.navigator.js" charset="utf-8"></script>
		<script type="text/javascript" src="<%=basePath%>inc/js/jquery.silver_track.bullet_navigator.js" charset="utf-8"></script>
		<script type="text/javascript" src="<%=basePath%>inc/js/jquery.silver_track.responsive_hub_connector.js" charset="utf-8"></script>
		<script type="text/javascript" src="<%=basePath%>inc/js/script.js" charset="utf-8"></script>
		<script type="text/javascript" src="<%=basePath%>inc/js/example2_and_3.js" charset="utf-8"></script>
		<script type="text/javascript" src="<%=basePath%>inc/js/core.js" charset="utf-8"></script>
		<script type="text/javascript" src="<%=basePath%>inc/js/cart.js" charset="utf-8"></script>
		<title>填写订单信息</title>
	</head>

	<body> 

		<!--头部开始-->
		<%@ include file="/WEB-INF/view/inc/top.jsp"%>

		<!--填写订单信息-->
		<div class="w1200">
			<div class="logo-sign fl">
				<a href="###"><img src="<%=basePath%>inc/images/logo-sign.png" />
				</a>
			</div>
			<div class="progress fr">
				<ul class="progress2">
					<li class="step">
						1 我的购物车
					</li>
					<li class="step-h">
						2 填写核对订单
					</li>
					<li class="step">
						3 成功提交订单
					</li>
				</ul>
			</div>
		</div>
		<div class="clearfix"></div>
		<div class="w1200 getorder">
			<div class="getorder-hd">
				<h2>
					核对订单
				</h2>
			</div>
			<div class="getorder-cont">
				<div id="addr_info" class="address">
					<strong style="font-weight:bold;font-size:14px;">
						收货人地址信息
						<span id="eidt_address"> <a href="javascript:void(0);" onclick="update_addr();" style="margin: 0 5px; color: #005ea7; font-size: 12px;font-weight:normal;">[修改]</a> </span>
					</strong><span id="save_address_info" style="color:#b0b0b0;display:none">如需修改，请先保存收货人信息</span>
					<div id="addr_selected" class="address-lst">
							<div>
								<p>
									<input type="hidden" id="selected_addr_id" name="rdoDefault" value="${sessionScope.order_address_map.addr_id}" />
									<label>
										<strong style="font-size:14px;color:#666;"><span class="name" 	id="selected_cust_name">	${sessionScope.order_address_map.cust_name} 	</span></strong>
										<strong style="font-size:14px;color:#666;"><span class="tel" 	id="selected_cell_phone">	${sessionScope.order_address_map.cell_phone}	</span>	</strong>
										<strong style="font-size:14px;color:#666;"><span 				id="selected_area_name_str">${sessionScope.order_address_map.area_name_str} </span></strong>
										<strong style="font-size:14px;color:#666;"><span 				id="selected_address">		${sessionScope.order_address_map.address}		</span></strong>
									</label>
								</p>
							</div>
					</div>
					<div id="update_addr_info" style="display: none;">
						<div id="addr_list" class="address-lst">
							<input type="hidden" id="select_addr_id" />
							<input type="hidden" id="select_cust_name" />
							<input type="hidden" id="select_cell_phone" />
							<input type="hidden" id="select_area_name_str" />
							<input type="hidden" id="select_address" />
							<c:forEach items="${addrlist}" var="addr" varStatus="status">
								<div id="address${status.index}">
									<p>
										<input type="radio" value="${addr.addr_id }" name="select_addr" addr_id="${addr.addr_id }" cust_name="${addr.cust_name}" cell_phone="${addr.cell_phone}" area_name_str="${addr.area_name_str }" address="${addr.address }" onclick="changeAddr('${addr.addr_id }', '${addr.cust_name}', '${addr.cell_phone}', '${addr.area_name_str }', '${addr.address }');" <c:if test="${addr.is_default=='1'}">checked="checked"</c:if>/>
										<label>
											<span class="name">${addr.cust_name}</span><span>${addr.area_name_str}</span><span>${addr.address}</span>
											<span class="tel">${addr.cell_phone}</span>
											<span class="edit"> 
												<a onclick="javascript:eidtAddres('${addr.addr_id}','${addr.cust_name}','${addr.area_attr}','${addr.address}','${addr.cell_phone}','${addr.phone}','${addr.is_default}');">编辑</a> <a href="javascript:deleteAddress('${addr.addr_id}','${status.index}');">删除</a><c:if test="${addr.is_default=='1'}"> 默认地址</c:if>
													<c:if test="${addr.is_default!='1'}">
														<a onclick="javascript:setDufealt('${addr.addr_id}');">设为默认地址</a>
													</c:if> 
												</span>
										</label>
									</p>
								</div>
							</c:forEach>
						</div>
						<div class="order-newaddr-btn">
							<a href="javascript:void(0);" onclick="add_addr_info();">使用新地址</a>
						</div>
						<div id="add_address_info" class="add-address">
							<ul class="error">
								<li>
									<label>
										<span>*</span>收货人：
									</label>
									<input type="hidden" name="addr_id" id="addr_id" />
									<input type="text" class="input-address" name="cust_name" id="cust_name" />
									<em><span id="span_cust_name"></span>
									</em>
								</li>
								<li>
									<label>
										<span>*</span>所在地址：
									</label>
									<span id="selectDivModel"></span>
									<script type="text/javascript">
										showSelectCascade("<%=basePath%>","area","selectDivModel","area_attr","");
										$("select[name='select_area_attr']").addClass("selectpsd");
									</script>
									<em><span id="span_area_attr"></span>
									</em>
								</li>
								<li>
									<label>
										<span>*</span>详细地址：
									</label>
									<input type="text" class="input-address" style="width: 315px" name="address" id="address" />
									<em><span id="span_address"></span>
									</em>
								</li>
								<li>
									<label>
										<span>*</span>手机号码：
									</label>
									<input type="text" class="input-address" name="cell_phone" id="cell_phone" />
									<b style="color: #999;">或</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<b style="color: #666">固定电话</b>
									<input type="text" class="input-address" name="phone" id="phone" />
									<em><span id="span_cell_phone"></span>
									</em>
								</li>
								<li>
									<label>
										<span>*</span>设为默认地址：
									</label>
									<p>
										<input type="radio" name="is_default" id="is_default1" value="1" checked="checked" />
										&nbsp;是&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="radio" name="is_default" id="is_default2" value="0" />
										&nbsp;否
									</p>
								</li>
							</ul>
						</div>
						<div style="margin: 20px 43px;" id="subAddress">
							<a class="add-address-btn" onclick="javascript:insertAddress();">提交地址</a>
						</div>
					</div>

				</div>
				<div class="order-payment" id="order-payment">
					<strong>支付信息</strong>
					<span id="edit_pay_info"> <a href="javascript:void(0);" onclick="update_pay_type();" style="margin: 0 5px; color: #005ea7; font-size: 12px;">[修改]</a> </span>
					<span id="save_pay_info" style="color:#b0b0b0;display:none">如需修改，请先保存收货人信息</span>
					<div class="order-payment-type">
						<strong>在线支付</strong>
						<div id="update_pay_type" style="display: none;">
							<div class="order-p-y-item">
								<input type="radio" value="在线支付" name="pay_type" checked="checked" />
								&nbsp;&nbsp;在线支付&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;即时到账，支持绝大数银行借记卡及部分信用卡
							</div>
							<div id="save_pay_type" >
								<a class="add-address-btn" onclick="javascript:savePayInfo();" id="pay_type">保存支付方式</a>
							</div>
						</div>
					</div>
					<p class="none">
						在线支付
					</p>
				</div>
				<div class="order-payment" id="invioce_info">
					<strong>发票信息</strong>&nbsp;&nbsp;<span id="eidt_invoice"><a class="a-lan">[修改]</a></span>&nbsp;&nbsp;<span id="save_invoice_info" style="color:#b0b0b0;display:none">如需修改，请先保存收货人信息</span>
					<div class="order-payment-type" id="edit_invoice_info">
						<br />
						<div class="order-p-y-item">
							<strong style="margin:0;" id="invoice_content">不开发票</strong>&nbsp;&nbsp;
						</div>
						
						<div class="order-p-t-bill none" id="give_invoice">
							发票开具方式：
							<input type="radio" value="2" name="invoice_type" checked="checked" />
							&nbsp;不开发票&nbsp;&nbsp;&nbsp;&nbsp;
							<input value="0" type="radio" name="invoice_type" />
							&nbsp;普通发票&nbsp;&nbsp;&nbsp;&nbsp;
							<input value="1" type="radio" name="invoice_type" />
							&nbsp;增值税发票&nbsp;&nbsp;
						</div>
						<div class="order-bill none" id="invoice_info">
							<table  border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td width="100" align="right">
										<span>*</span>纳税人识别号：
									</td>
									<td>
										<input type="text" name="ident_number" id="ident_number" value=${invoice.ident_number } ></input>
										<span id="number"></span>
									</td>
								</tr>
								<tr>
									<td width="100" align="right">
										<span>*</span>注册地址：
									</td>
									<td>
										<input type="text" name="regis_address" id="regis_address" value=${invoice.regis_address }></input>
										<span id="regisaddress"></span>
									</td>
								</tr>
								<tr>
									<td width="100" align="right">
										<span>*</span>注册电话：
									</td>
									<td>
										<input type="text" name="regis_tel" id="regis_tel" value=${invoice.regis_tel }></input>
										<span id="tel"></span>
									</td>
								</tr>
								<tr>
									<td width="100" align="right">
										<span>*</span>开户银行：
									</td>
									<td>
										<input type="text" name="open_bank" id="open_bank" value=${invoice.open_bank}></input>
										<span id="bank"></span>
									</td>
								</tr>
								<tr>
									<td width="100" align="right">
										<span>*</span>银行账户：
									</td>
									<td>
										<input type="text" name="bank_account" id="bank_account" value=${invoice.bank_account} ></input>
										<span id="account"></span>
									</td>
								</tr>
							</table>
						</div>
						<div style="margin-bottom: 15px;" class="none" id="invoice_address">
							&nbsp;&nbsp;&nbsp;&nbsp;发票抬头：
							<c:if test="${invoice.invoice_top == '个人'}">
							<input checked="checked" type="radio" name="invoice_top" value="个人" /> &nbsp;个人&nbsp;&nbsp;&nbsp;&nbsp;
							</c:if>
							<c:if test="${invoice.invoice_top != '个人'}">
							<input type="radio" name="invoice_top" value="个人" /> &nbsp;个人&nbsp;&nbsp;&nbsp;&nbsp;
							</c:if>
							<c:if test="${invoice.invoice_top == '单位'}">
								<input checked="checked" type="radio" name="invoice_top" value="单位" />&nbsp; 单位&nbsp;&nbsp;
								<input type="text"  name="company_name" id="company_name" value=${invoice.company_name}></input>
								<span id="companyname" style="color:red"></span>
							</c:if>
							<c:if test="${invoice.invoice_top != '单位'}">
							<input type="radio" name="invoice_top" value="单位" /> &nbsp; 单位&nbsp;&nbsp;
								<input type="text" style="margin-left: 0; display: none;" name="company_name" id="company_name" value=${invoice.company_name}></input>
								<span id="companyname" style="color:red"></span>
							</c:if>
						</div>
						<div class="none" id="invoice_contents">
							&nbsp;&nbsp;&nbsp;&nbsp;发票内容：
							<c:if test="${invoice.invoice_content == '明细' || invoice.invoice_content == null}">
							<input checked="checked" type="radio" name="invoice_content" value="明细"  /> &nbsp;明细&nbsp;&nbsp;&nbsp;&nbsp;
							</c:if>
							<c:if test="${invoice.invoice_content != '明细'}">
							<input type="radio" name="invoice_content" value="明细"  /> &nbsp;明细&nbsp;&nbsp;&nbsp;&nbsp;
							</c:if>
							<c:if test="${invoice.invoice_content == '办公用品'}">
							<input checked="checked" type="radio" name="invoice_content" value="办公用品" /> &nbsp;办公用品&nbsp;&nbsp;&nbsp;&nbsp;
							</c:if>
							<c:if test="${invoice.invoice_content != '办公用品'}">
							<input type="radio" name="invoice_content" value="办公用品" /> &nbsp;办公用品&nbsp;&nbsp;&nbsp;&nbsp;
							</c:if>
							<c:if test="${invoice.invoice_content == '电脑配件'}">
							<input  checked="checked" type="radio" name="invoice_content" value="电脑配件" /> &nbsp;电脑配件&nbsp;&nbsp;&nbsp;&nbsp;
							</c:if>
							<c:if test="${invoice.invoice_content != '电脑配件'}">
							<input  type="radio" name="invoice_content" value="电脑配件" /> &nbsp;电脑配件&nbsp;&nbsp;&nbsp;&nbsp;
							</c:if>
							<c:if test="${invoice.invoice_content == '耗材'}">
							<input  checked="checked" type="radio" name="invoice_content" value="耗材" /> &nbsp;耗材
							</c:if>
							<c:if test="${invoice.invoice_content != '耗材'}">
							<input  type="radio" name="invoice_content" value="耗材" /> &nbsp;耗材
							</c:if>
						</div>
						<div id="save_invoice" style="display:none">
							<a class="add-address-btn" onclick="javascript:setInvoice();">保存发票信息</a>
						</div>
					</div>
					<p class="none">
						普通发票（电子）
					</p>
				</div>
				<div class="order-payment" style="border-bottom:0">
					<strong>商品清单</strong>
					<a href="<%=basePath%>cartlist.action" style="display: inline-block; float: right; color: #005ea7; font-size: 12px;">返回修改购物车</a>
					<table border="0" cellpadding="0" class="order-list" cellspacing="1">
						<tr class="order-l-head">
							<td width="415">
								商品名称
							</td>
							<td width="135">
								天下名品价（元）
							</td>
							<td width="135">
								优惠
							</td>
							<td width="135">
								金额小计
							</td>
							<td width="135">
								数量
							</td>
							<td>
								库存状态
							</td>
						</tr>
						<c:forEach items="${cartlist}" var="item" varStatus="status">
							<tr>
								<td>
									<a href="<%=basePath%>goods/${item.goods.goods_id}.action" title="${item.goods.goods_name}"> <c:if test="${!empty item.goods.img_path}">
											<img src="<h:imgSubstr imgpath="${item.goods.img_path}"/>" />
										</c:if> ${item.goods.goods_name}${item.goods_param } </a>
								</td>
								<td>
									¥${item.goods.sale_price}
								</td>
								<td>
									¥0.00
								</td>
								<td>
									<fmt:formatNumber type="number" pattern="¥0.00" value="${item.goods.sale_price * item.amount } " maxFractionDigits="2" />
								</td>
								<td>
									${item.amount}
								</td>
								<td>
									有货
								</td>
							</tr>
						</c:forEach>
					</table>
				</div>
				<div class="item-total">
					<div class="total fr">
						<p>
							总计：
							<span>¥${goodsTotalPrice }</span>
						</p>
						<p>
							优惠：
							<span>¥0.00</span>
						</p>
					</div>
					<div class="amout fr">
						<span>（${cartsize}件）</span>商品
					</div>
				</div>

				<div class="item-total2">
					<div class="total-a fl">
						<a href="<%=basePath%>">继续购物</a>
					</div>
					<div id="sub_btn" class="total-btn fr">
						<input type="hidden" name="times" id="times" value="0" />
						<a  onclick="javascript:subOrder();">提交订单<b></b> </a>
					</div>
					<div class="total2 fr">
						<span>${cartsize}件</span> 商品总计（含运费）：
						<em>¥${goodsTotalPrice}</em>
					</div>
				</div>
			</div>
		</div>
		<script>
			//支付方式
			function savePayInfo(){
				$("#update_pay_type").hide();
				$('#order-payment').attr('class', 'order-payment');
				//保存完成后可执行其他修改
				$('#eidt_address').show();
				$('#eidt_invoice').show();
				$('#save_address_info').hide();
				$('#save_invoice_info').hide();
			
			}
			//提交订单
			function subOrder(){
				if($("#selected_addr_id").val().length<1){
					alert("请选择收货地址");
					return false;
				}
				 if('${fn:length(addrlist)}'=='0'){
				 	alert("请填写收货地址");
				 	return false;
				 }
				 
				 if(!$("#update_addr_info").is(":hidden")){
				 	alert("请保存收货地址信息");
				 	return false;
				 }
				 
				 if(!$("#update_pay_type").is(":hidden")){
				 	alert("请保存支付信息");
				 	return false;
				 }
				 
				 if(!$("#update_pay_type").is(":hidden")){
				 	alert("请保存支付信息");
				 	return false;
				 }
				 
				 if(!$("#give_invoice").is(":hidden")){
				 	alert("请保存发票信息");
				 	return false;
				 }
				if($('#times').val()!=0){
					return;
				}
				$('#times').val('1');
				$("#sub_btn").css("background",'#666666');
				location.href="<%=basePath%>order/orderok.action?goodsTotalPrice=${goodsTotalPrice}";
			}
		
			//收货人地址信息-修改
			function update_addr() {
				$('#addr_selected').css('display', 'none');
				$('#update_addr_info').css('display', 'block');
				
				if($('#addr_list input[@type=radio]:checked').length == 1) {
					$("#add_address_info").hide();
				}
				$('#add_address_info input').each(function() {
					$(this).val('');
 				});
 				$('#add_address_info select').each(function() {
					$(this).val('');
 				}); 
 				//设置边框样式
 				$('#addr_info').attr('class', 'address active');
				$('#order-payment').attr('class', 'order-payment');
				 $('#invioce_info').attr('class', 'order-payment');
				 //保存完收货地址才能执行其他操作
				 $('#edit_pay_info').hide();
				 $('#eidt_invoice').hide();
				 
				 $('#save_pay_info').show()
				 $('#save_invoice_info').show()
				 $('#save_pay_info').text("如需修改，请先保存收货人信息");
				 $('#save_invoice_info').text("如需修改，请先保存收货人信息");
				 
				 
				 
				 
			}
			
			//新增收货人地址
			function add_addr_info() {
				if($('#addr_list input[@type=radio]:checked').length == 1) {
					$("#add_address_info").hide();
				}
 				$('#add_address_info input').each(function() {
					$(this).val('');
 				});
 				$('#add_address_info select').each(function() {
					$(this).val('');
 				});
				$('#addr_list input[@type=radio]:checked').attr("checked", false);
				if($(".add-address").is(":hidden")){
					$(".add-address").show();
				}else{
					$(".add-address").hide();
				}
				
			}
			//页面加载完成后显示发票信息
			$(document).ready(function(){ 
				
				var invoice_type='${sessionScope.order_invoice_map.invoice_type}',
					invoice_title ='${sessionScope.order_invoice_map.invoice_top}';
					
				if(invoice_title=='单位'){
					invoice_title='${sessionScope.order_invoice_map.company_name}';
				}
				if(invoice_type=='0'){
		 			$("#invoice_content").html("普通发票   发票抬头 "+invoice_title+" 发票内容 ${sessionScope.order_invoice_map.invoice_content}");
		 		}
				if(invoice_type=='1'){
		 			$("#invoice_content").html("增值税发票   发票抬头 "+invoice_title+" 发票内容 ${sessionScope.order_invoice_map.invoice_content}");
		 		}
		 		if(invoice_type=='2'||invoice_type==''){
		 			$("#invoice_content").html("不开发票");
		 		}
			}) 
		 	
			
			//选择地址
			function select_addr() {
				$(".add-address").hide();
			}
			
			//选择地址-保存
			function changeAddr(addr_id, cust_name, cell_phone, area_name_str, address) {
				$('#add_address_info').hide();
				$('#select_addr_id').val(addr_id);
				$('#select_cust_name').val(cust_name);
				$('#select_cell_phone').val(cell_phone);
				$('#select_area_name_str').val(area_name_str);
				$('#select_address').val(address);
			}
			
			//修改支付方式
			function update_pay_type() {
				if($('#selected_addr_id').val() == null || $('#selected_addr_id').val() == '') {
					alert('请先选择收货人地址信息');
					return;
				}
				
				$('#addr_info').attr('class', 'address');
				$('#order-payment').attr('class', 'address active');
				$('#invioce_info').attr('class', 'order-payment');
				$('#update_pay_type').css('display', 'block');
				
				//不能操作其他修改项
				$('#eidt_address').hide();
				$('#eidt_invoice').hide();
				
				$('#save_address_info').show();
				$('#save_address_info').text("如需修改，请先保存支付信息");
				
				$('#save_invoice_info').show();
				$('#save_invoice_info').text("如需修改，请先保存支付信息");
			}
		
			function deleteAddress(address_id,indx){
					$("#addr_id").val("");
					$.ajax({
						type: "get",
						url: "<%=basePath%>order/deleteaddress.action?address_id="+address_id,
						success: function(data, textStatus){
							$("#address"+indx).remove();
						}
					});
			}
			
			function setDufealt(address_id){
				$.ajax({
						type: "get",
						url: "<%=basePath%>user/addrEditDefault-"+address_id+".action",
						success: function(data, textStatus){
							if(data.length>0){
								location.reload();
							}
						}
					});
			}
			
			//设置发票
			function setInvoice(){
				var invoice_type=$("input[name='invoice_type']:checked").val(),
				ident_number=$("#ident_number").val(),
				regis_address=$("#regis_address").val(),
				regis_tel=$("#regis_tel").val(),
				open_bank=$("#open_bank").val(),
				bank_account=$("#bank_account").val(),
				invoice_top=$("input[name='invoice_top']:checked").val(),
				company_name=$("#company_name").val(),
				invoice_content=$("input[name='invoice_content']:checked").val();
				
				if(typeof invoice_type == 'undefined'){
					alert("请选择发票开具方式!");
					return false;
				}
				var invoice_title='';
				if(invoice_top=='单位'){
					invoice_title=company_name;
				}else{
					invoice_title=invoice_top;
				}
				
				if(invoice_type=='1'||invoice_type==1){
					if(ident_number.length<1){
						$("#ident_number").focus();
						$("#number").text("请输入纳税人识别号");
						return false;
					}else if(ident_number.length>25){
						$("#number").text("纳税人识别号太长不能超过25个字符");
						return false;
					}else{
						$("#number").text("");
					}
					if(regis_address.length<1){
						$("#regis_address").focus();
						$("#regisaddress").text("请输入注册地址");
						return false;
					}else if(regis_address.length>50){
						$("#regis_address").focus();
						$("#regisaddress").text("注册地址不能超过50个字符");
						return false;
					}else{
						$("#regisaddress").text("");
					}
					if(regis_tel.length<1){
						$("#regis_tel").focus();
						$("#tel").text("请输入注册电话号码");
						return false;
					}else if(regis_tel.length>20){
						$("#regis_tel").focus();
						$("#tel").text("注册电话号码太长");
						return false;
					}else{
						$("#tel").text("");
					}
					if(open_bank.length<1){
						$("#open_bank").focus();
						$("#bank").text("请输入开户银行");
						return false;
					}else if(open_bank.length>22){
						$("#open_bank").focus();
						$("#bank").text("开户银行输入太长!不能超过20个字符");
						return false;
					}else{
						$("#bank").text("");
					}
					if(bank_account.length<1){
						$("#bank_account").focus();
						$("#account").text("请输入银行账户");
						return false;
					}else if(bank_account.length>22){
						$("#bank_account").focus();
						$("#account").text("银行账户输入太长! 不能超过20个字符");
						return false;
					}else{
						$("#account").text("");
					}
					$("#invoice_content").html("增值税发票   发票抬头 "+invoice_title+" 发票内容 "+invoice_content);
				}
				if(invoice_type=='2'||invoice_type==2){
					$("#invoice_content").html("不开发票");
				}
				if(invoice_type=='0'||invoice_type==0){
					$("#invoice_content").html("普通发票   发票抬头 "+invoice_title+" 发票内容 "+invoice_content);
				}
				if(invoice_type=='1'||invoice_type=='0'){
					if($("input[name='invoice_top']:checked").val()=='单位'){
						if(company_name.length<1){
							$("#company_name").focus();
							$("#companyname").text("请输入单位名称");
								return false;
							}else if(company_name.length>20){
								$("#companyname").text("单位名称不能超过20个字符");
								return false;
							}else{
								$("#companyname").text("");
							}
						}
				}
			//ajax 提交选择的发票
			$.ajax({
					type: 'post',
					data :{'invoice_type':invoice_type,'ident_number':ident_number
						,'regis_address':regis_address,'regis_tel':regis_tel
						,'open_bank':open_bank,'bank_account':bank_account
						,'invoice_top':invoice_top,'company_name':company_name
						,'invoice_content':invoice_content},
					url: "<%=basePath%>order/setInvoice.action",
					success: function(data, textStatus){
						invoice_callback();
					}
				});
			}
			//发票选择 回调函数
			function invoice_callback(){
				$("#give_invoice").hide();
				$("#invoice_info").hide();
				$("#invoice_contents").hide();
				$("#invoice_address").hide();
				$("#save_invoice").hide();
				$(".order-p-y-item").show();
				$("input:radio[name='invoice_type']").attr("checked", false);
				$("input:radio[value='2']").attr("checked", true);
				$('#invioce_info').attr('class', 'order-payment');
				
				//保存发票信息后可执行其他操作
				   $("#edit_pay_info").show();
			        $("#eidt_address").show();
			        
			        $("#save_address_info").hide();
			        $("#save_pay_info").hide();
			        $("#save_invoice_info").hide();
			        
				
			} 
			
			//是否显示开具发票公司输入框
			$("input:radio[name='invoice_top']").click(function() { 
				if($("input[name='invoice_top']:checked").val()=='单位'){
					$("#company_name").show();
				}else{
					$("#company_name").hide();
				}
			});
			
			//编辑地址
			function eidtAddres(addr_id,cust_name,field_area_attr,address,cell_phone,phone,is_default){
				$('#addr_list input[@type=radio]:checked').attr("checked", false);
				 $(".add-address").show();
				 $("#addr_id").val(addr_id);
				 $("#cust_name").val(cust_name);
				 $("#field_area_attr").val(field_area_attr);
				 $("#address").val(address);
				 $("#cell_phone").val(cell_phone);
				 $("#phone").val(phone);
				 
		 		showSelectCascade("<%=basePath%>","area","selectDivModel","area_attr",field_area_attr);
				$("select[name='select_area_attr']").addClass("selectpsd");
				 if(is_default==1||is_default=='1'){
				 	 $("#is_default1").attr('checked', 'checked');
				 }else{
				 	 $("#is_default2").attr('checked', 'checked');
				 }
			}
			
			//选择收货地址
			function selectAddress(){
					var addr_id = $('#select_addr_id').val();
					if(addr_id.length<1){
						addr_id=$('#addr_list input[@type=radio]:checked').attr("addr_id")
					}
					var cust_name = $('#select_cust_name').val();
					
					if(cust_name.length<1){
						cust_name=$('#addr_list input[@type=radio]:checked').attr("cust_name")
					}
					var cell_phone = $('#select_cell_phone').val();
					if(cell_phone.length<1){
						cell_phone=$('#addr_list input[@type=radio]:checked').attr("cell_phone")
					}
					var area_name_str = $('#select_area_name_str').val();
					if(area_name_str.length<1){
						area_name_str=$('#addr_list input[@type=radio]:checked').attr("area_name_str")
					}
					var address = $('#select_address').val();
					if(address.length<1){
						address=$('#addr_list input[@type=radio]:checked').attr("address")
					}
					
					$('#selected_addr_id').val(addr_id);
					$('#selected_cust_name').html(cust_name);
					$('#selected_cell_phone').html(cell_phone);
					$('#selected_area_name_str').html(area_name_str);
					$('#selected_address').html(address);
					
					
					$('#addr_info').attr('class', 'address');
					$('#update_addr_info').hide();
					$('#addr_selected').show();
					
					
					$('#edit_pay_info').show();
					 $('#eidt_invoice').show();
					 
					 $('#save_pay_info').hide()
					 $('#save_invoice_info').hide()
					 return false;
			}
			//手机号码校验
			function check_mobile(mobile){
				  var regu = /^\d{11}$/;
				  var re = new RegExp(regu);
				  if(!re.test(mobile)){
					 return  false;
				  }
				  return true;
				}
			
			//保存或修改收货地址
			function insertAddress(){
				 var is_defaults =$("input:radio[name='is_default']"),
					 addr_id =$("#addr_id").val(),
					 cust_name =$("#cust_name").val(),
					 field_area_attr =$("#field_area_attr").val(),
					 address =$("#address").val(),
					 cell_phone =$("#cell_phone").val(),
					 phone =$("#phone").val(),
					 selectValue1 = $("#area_attr1").find("option:selected").val(),
					 selectText1=$("#area_attr1").find("option:selected").text(),
					 selectValue2 = $("#area_attr2").find("option:selected").val(),
					 selectText2=$("#area_attr2").find("option:selected").text(),
					 selectValue3 = $("#area_attr3").find("option:selected").val(),
					 selectText3=$("#area_attr3").find("option:selected").text(),
					 is_default='';
					 
				 if(selectText3=='请选择'){
				 	selectText3='';
				 }
				 for(var i=0;i<is_defaults.length;i++){
				 	if(is_defaults[i].checked){
					 	is_default = is_defaults[i].id
					 	break;
					 }
				 }
				 if(is_default=='is_default1'||is_default==is_default1){
				 	is_default='1';
				 }else{
				 	is_default=0;
				 }
				 
				 //选择了其他地址
				 if($('#addr_list input[@type=radio]:checked').length == 1) {
						selectAddress();
				 }
				 if($('#addr_list input[@type=radio]:checked').length<1&& $("#add_address_info").is(":hidden")) {
						alert('请选择一个收货人地址');
						return;
				  }
				  if(cust_name.length<=0){
				 		$("#span_cust_name").html("请您填写收货人姓名！");
				 		$("#cust_name").focus();
						return false;
					}else if(cust_name.length>20){
						$("#span_cust_name").html("收货人姓名不能超过20个字符!")
						$("#cust_name").focus();
						return false;
					}else{
						$("#span_cust_name").html("");
					}
					if(field_area_attr.length<=0){
						$("#span_area_attr").html("所在地址不能为空！");
						return false;
					}else{
						$("#span_area_attr").html("");
					}
					if(address.length<=0){
						$("#span_address").html("详细地址不能为空！");
						$("#address").focus();
						return false;
					}else if(address.length>50){
						$("#span_address").html("详细地址不能超过50个字符！");
						$("#address").focus();
						return false;
					}else{
						$("#span_address").html("");
					}
					if(cell_phone.length<=0){
						$("#span_cell_phone").html("手机号码不能为空！");
						cell_phone.focus();
						return false;
					}else if(!check_mobile(cell_phone)){
						$("#span_cell_phone").html("手机号码格式错误！");
						cell_phone.focus();
						return false;
					}else{
						$("#span_cell_phone").html("");
					}
					//修改收货地址
				if(addr_id.length>0){
					$.ajax({
						type: 'post',
						url: '<%=basePath%>user/addrupdate.action',
						data: {addr_id: addr_id,cust_name: cust_name, area_attr: field_area_attr, cell_phone: cell_phone, is_default: is_default, address: address, phone: phone},
						dataType: 'json',
						success: function(data) {
							location.reload();
						},error:function(data){
							location.reload();
						}
					});
				}else{
					//新增收货地址
					$.ajax({
						type: 'post',
						url: '<%=basePath%>order/addrinsert.action',
						data: {cust_name: cust_name, area_attr: field_area_attr, cell_phone: cell_phone, is_default: is_default, address: address, phone: phone},
						dataType: 'json',
						success: function(data) {
							location.reload();
						},error:function(XMLHttpResponse){
								alert("系统繁忙，请稍后再试！");
								return false;
						}
					});
				}
			}
			
			$("input:radio[name='select_addr']").click(function() { 
					var rdoValu=$("input[name='select_addr']:checked").val();
					$.ajax({
						type: "get",
						url: "<%=basePath%>order/getUserAddress-"+rdoValu+".action",
						success: function(data){}
					});
				}); 
				
		</script>
		<!--底部-->
		<%@ include file="/WEB-INF/view/inc/footer.jsp"%>
	</body>
</html>

