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
						<div class="mymsg">
							<h2>
								商品评价
							</h2>
							<table width="100%" class="eval-tab" border="0" cellpadding="0"
								cellspacing="0">
								<tr>
									<th>
										商品信息
									</th>
									<th>
										购买时间
									</th>
									<th>
										发表评价
									</th>
								</tr>
								<c:forEach items="${goodslists}" var="item" varStatus="status">
									<c:forEach items="${item.orderdetailList}" var="detail"
										varStatus="status1">
										<tr>
											<td width="530">
												<span class="fl productimg"><a
													href="<%=basePath%>goods/${detail.goods_id }.action"><img
															src="<h:imgSubstr imgpath="${detail.img_path}"/>"  /> </a> </span>
												<span class="fl productintro" style="margin-left: 10px;"><a
													href="<%=basePath%>goods/${detail.goods_id }.action">${detail.goods_name
														}</a> </span>
											</td>
											<td width="230" class="pay-time">
												<fmt:formatDate value="${item.order_time}"
													pattern="yyyy-MM-dd HH:mm:ss" />
											</td>
											<td width="260">
												<c:if test="${detail.eval_trade == null}">
													<a id='${detail.goods_id }' href="#" class="check-ord fbpj"
														style="display: block; width: 50px; margin: 0 auto;"
														onclick="goodsid(${detail.goods_id })">发表评价</a>
												</c:if>
												<c:if test="${detail.eval_trade != null}">
													<a class="check-ord ckpj" onclick="checkeval(${detail.goods_id },this)">查看</a>
												</c:if>
												<script type="text/javascript">
													       function checkeval(id,o){
													       $("span[id='errortext']").text("");
													       var thisObj=$(o);
														       	$.ajax({
																	type: 'post',
																	async: false,
																	url: 'goodseval/checkview.action',
																	data : {'goods_id':id,'order_id':'${item.order_id}'},
																	dataType: 'json',
																	success: function(data) {
																			if(data == null) {
																				return;
																			}
																			$.each(data,function(entryIndex,entry){
																				var textObj=thisObj.parents('tr').next().next().next();
																				if(textObj.is(':visible')){
																					textObj.hide();
																					return;
																				}
																				textObj.find(".agreement-btn-y").text("关闭").bind('click', account.orderAssessClose);;
					  													        $("tr[id='orderassess']").hide();
																				textObj.show();
																				textObj.find('textarea').parent().append('<div>'+entry.g_comment+'</div>');
																				textObj.find('textarea').remove();
																				switch(entry.goods_score/20){
																					case 1: 
																							$("span[id='span1']").attr("class","serve-l-red");
																							$("span[id='span2']").attr("class","serve-l-red serve-l-ash");
																							$("span[id='span3']").attr("class","serve-l-red serve-l-ash");
																							$("span[id='span4']").attr("class","serve-l-red serve-l-ash");
																							$("span[id='span5']").attr("class","serve-l-red serve-l-ash");
																							break;
																					case 2: 
																							$("span[id='span1']").attr("class","serve-l-red");
																							$("span[id='span2']").attr("class","serve-l-red ");
																							$("span[id='span3']").attr("class","serve-l-red serve-l-ash");
																							$("span[id='span4']").attr("class","serve-l-red serve-l-ash");
																							$("span[id='span5']").attr("class","serve-l-red serve-l-ash");
																							break;
																					case 3: 
																							$("span[id='span1']").attr("class","serve-l-red");
																							$("span[id='span2']").attr("class","serve-l-red");
																							$("span[id='span3']").attr("class","serve-l-red");
																							$("span[id='span4']").attr("class","serve-l-red serve-l-ash");
																							$("span[id='span5']").attr("class","serve-l-red serve-l-ash");
																							break;
																					case 4: 
																							$("span[id='span1']").attr("class","serve-l-red");
																							$("span[id='span2']").attr("class","serve-l-red");
																							$("span[id='span3']").attr("class","serve-l-red");
																							$("span[id='span4']").attr("class","serve-l-red");
																							$("span[id='span5']").attr("class","serve-l-red serve-l-ash");
																							break;
																					case 5: 
																							$("span[id='span1']").attr("class","serve-l-red");
																							$("span[id='span2']").attr("class","serve-l-red");
																							$("span[id='span3']").attr("class","serve-l-red");
																							$("span[id='span4']").attr("class","serve-l-red");
																							$("span[id='span5']").attr("class","serve-l-red");
																				}
																				
																			});
																		},
																		error: function(XMLHttpRequest, textStatus, errorThrown) {
																				alert('加载数据失败,请稍后再试');
																			}
																});
													       }
													</script>
											</td>
										<tr style="display: none" id="goodsid"></tr>
										<script type="text/javascript">
													       function goodsid(obj){
														       $("#goodsid").text(obj);
														       $(".agreement-btn-y").text("完成评价");
														       $("textarea[id='comment']").val("10至200字以内");
														       $("span[id='errortext']").text("");
														       $("span[id='span1']").attr("class","serve-l-red ");
															   $("span[id='span2']").attr("class","serve-l-red");
															   $("span[id='span3']").attr("class","serve-l-red ");
															   $("span[id='span4']").attr("class","serve-l-red ");
															   $("span[id='span5']").attr("class","serve-l-red ");
													       }
													</script>
										<tr id="orderassess" class="none">
											<td colspan="3">
												<div class="eval-box">
													<div class="box-top"></div>
													<ul id="clearfix">
														<li class="clearfix">
															<span class=" fl"><em>*</em>评分：</span>

															<p class="fl goods-d-level">
																<span id="span1" class="serve-l-red"
																	onclick="starnum(this)"></span>
																<span id="span2" class="serve-l-red "
																	onclick="starnum(this)"></span>
																<span id="span3" class="serve-l-red "
																	onclick="starnum(this)"></span>
																<span id="span4" class="serve-l-red "
																	onclick="starnum(this)"></span>
																<span id="span5" class="serve-l-red "
																	onclick="starnum(this)"></span>
																<span id="span6" style="display: none"
																	class="serve-l-red serve-l-ash">5</span>
															</p>
															<script type="text/javascript">
													       function starnum(obj){
														       switch (obj.id){
														       		case 'span1' : $("#span6").text("1"); break; 
														       		case 'span2' : $("#span6").text("2"); break; 
														       		case 'span3' : $("#span6").text("3"); break; 
														       		case 'span4' : $("#span6").text("4"); break; 
														       		case 'span5' : $("#span6").text("5"); break; 
														       }
													       }
													</script>
														</li>
														<li class="clearfix">
															<span class=" fl"><em>*</em>评价：</span>

															<div class="eval-texta fl">
																<textarea id="comment" onclick="cleantext()">10至200字以内</textarea>
															</div>
															<script type="text/javascript">
																function cleantext(){
																	if('10至200字以内' == $("textarea[id='comment']").val()){
																		$("textarea[id='comment']").val("");
																	}
																}
															</script>
														</li>
														<li class="clearfix">
															<span class="eval-spn fl">&nbsp;</span>

															<div class="eval-texta fl">
																<c:if test="${detail.eval_trade == null}">
																	<a  class="agreement-btn-y" onclick=" submiteval($('#goodsid').text(),this)">完成评价</a>
																	<span id="errortext" style="color:red"></span>
																</c:if>
																<c:if test="${detail.eval_trade != null}">
																	<a  class="agreement-btn-y" onclick="closedtr()" >关闭</a>
																</c:if>
															</div>
															<script type="text/javascript">
																function closedtr(){
																	$("span[id='orderassess']").hide();
																}
															</script>
															<script type="text/javascript">
												       function submiteval(id,o){
												       			var textareaObj=$(o).parents('li').prev().find('textarea');
												       			var textareaObjVal=$.trim(textareaObj.val());
												       			$("span[id='errortext']").text("");
												       			if(textareaObjVal.length < 10 || textareaObjVal == '10至200字以内' || textareaObjVal.length > 200){
												       				$("span[id='errortext']").text("请输入10至200字以内的评价内容");
												       				return;
												       			}
																$.ajax({
																type: 'post',
																url: 'goodseval/saveeval.action',
																data : 
																		{'goods_id':$("#goodsid").text(),'order_id':'${item.order_id}'
																		,'buy_cust_id':'${item.buy_cust_id}','sale_cust_id':'${item.sale_cust_id}'
																		,'g_eval':$('#span6').text(),'eval_memt':textareaObjVal},
																success: function(data, textStatus){
																			if (data == id){
																				$("span[id='span6']").val('');
																				$("span[id='comment']").val('');
																				$("#"+id).unbind();
																				$("#"+id).text("查看").removeClass('fbpj').click(function(){
																					$(this).parents('tr').next().next().next().find('.agreement-btn-y').click(function(){
																						closedtr();
																					});
																					checkeval($(this).attr('id'),$(this)[0]);
																					$(".agreement-btn-y").text("关闭");
																					return false;
																				});
																				textareaObj.parents('#orderassess').hide();
																			}
																	}
																});
												       }
												</script>
														</li>
													</ul>
												</div>
											</td>
										</tr>
									</c:forEach>
								</c:forEach>
							</table>
						</div>
						
						<div class="mer-eval mer-eval1">
							<div class="mer-eval-cont">
								<div class="mer-eval-cont-l fl">
									<div class="fl">
										<img class="img" src="<%=rootPath%>images/user-img.png" />
									</div>
									<ul class="mer-eval-cont-ul fl">
										<li>
											<span>店铺名称:</span>${salelist.cust_name}
										</li>
										<li>
											<span>地 址:</span>${salelist.address}
										</li>
										<li>
											<span>联系电话:</span>${salelist.contact_phone}
										</li>
									</ul>
									<div class="box-right"></div>
								</div>

								<c:if test="${empty sellereval}">									
								<ul class="mer-eval-cont-ul2 fl">
									<li class="clearfix">
										<span class=" fl">包装满意度评价：</span>

										<p class="fl goods-d-level">
											<span id="p_eval1" class="serve-l-red" onclick="p_eval(this)"></span>
											<span id="p_eval2" class="serve-l-red" onclick="p_eval(this)"></span>
											<span id="p_eval3" class="serve-l-red" onclick="p_eval(this)"></span>
											<span id="p_eval4" class="serve-l-red" onclick="p_eval(this)"></span>
											<span id="p_eval5" class="serve-l-red "onclick="p_eval(this)"></span>
											<span id="p_eval6" style="display: none">5</span>
										</p>
										<script type="text/javascript">
													       function p_eval(obj){
														       switch (obj.id){
														       		case 'p_eval1' : $("#p_eval6").text("1"); break; 
														       		case 'p_eval2' : $("#p_eval6").text("2"); break; 
														       		case 'p_eval3' : $("#p_eval6").text("3"); break; 
														       		case 'p_eval4' : $("#p_eval6").text("4"); break; 
														       		case 'p_eval5' : $("#p_eval6").text("5"); break; 
														       }
													       }
													</script>
									</li>
									<li class="clearfix">
										<span class=" fl">送货速度评价：</span>

										<p class="fl goods-d-level">
											<span id="sp_eval1" class="serve-l-red"
												onclick="sp_eval(this)"></span>
											<span id="sp_eval2" class="serve-l-red"
												onclick="sp_eval(this)"></span>
											<span id="sp_eval3" class="serve-l-red"
												onclick="sp_eval(this)"></span>
											<span id="sp_eval4" class="serve-l-red"
												onclick="sp_eval(this)"></span>
											<span id="sp_eval5" class="serve-l-red "
												onclick="sp_eval(this)"></span>
											<span id="sp_eval6" style="display: none">5</span>
										</p>
										<script type="text/javascript">
													       function sp_eval(obj){
														       switch (obj.id){
														       		case 'sp_eval1' : $("#sp_eval6").text("1"); break; 
														       		case 'sp_eval2' : $("#sp_eval6").text("2"); break; 
														       		case 'sp_eval3' : $("#sp_eval6").text("3"); break; 
														       		case 'sp_eval4' : $("#sp_eval6").text("4"); break; 
														       		case 'sp_eval5' : $("#sp_eval6").text("5"); break; 
														       }
													       }
													</script>
									</li>
									<li class="clearfix">
										<span class=" fl">服务态度评价：</span>

										<p class="fl goods-d-level">
											<span id="ser_eval1" class="serve-l-red"
												onclick="ser_eval(this)"></span>
											<span id="ser_eval2" class="serve-l-red"
												onclick="ser_eval(this)"></span>
											<span id="ser_eval3" class="serve-l-red"
												onclick="ser_eval(this)"></span>
											<span id="ser_eval4" class="serve-l-red"
												onclick="ser_eval(this)"></span>
											<span id="ser_eval5" class="serve-l-red "
												onclick="ser_eval(this)"></span>
											<span id="ser_eval6" style="display: none">5</span>
										</p>
										<script type="text/javascript">
													       function ser_eval(obj){
														       switch (obj.id){
														       		case 'ser_eval1' : $("#ser_eval6").text("1"); break; 
														       		case 'ser_eval2' : $("#ser_eval6").text("2"); break; 
														       		case 'ser_eval3' : $("#ser_eval6").text("3"); break; 
														       		case 'ser_eval4' : $("#ser_eval6").text("4"); break; 
														       		case 'ser_eval5' : $("#ser_eval6").text("5"); break; 
														       }
													       }
													</script>
									</li>
									<li>
										<a id="eval_1" class="eval-btn" onclick="saleeval()">评价</a>
									</li>
									<script type="text/javascript">
												       function saleeval(){
															$.ajax({
															type: 'post',
															url: 'goodseval/salesaveeval.action',
															data : 
																	{'cust_id':'${goodsorder.sale_cust_id}','order_id':'${goodsorder.order_id}'
																	,'buy_cust_id':'${goodsorder.buy_cust_id}','sale_cust_id':'${item.sale_cust_id}'
																	,'p_eval':$('#p_eval6').text(),'sp_eval':$('#sp_eval6').text()
																	,'ser_eval':$('#ser_eval6').text()},
															success: function(data, textStatus){
																		if(data == '0'){
																			alert("已对该商家评价");
																		}else{
																			$("#eval_1").hide();
																		}
																}
															});
												       }
												</script>
								</ul>
								</c:if>
								<c:forEach items="${sellereval}" var="item" varStatus="status">
									<c:if test="${item.order_id != null }">
									<ul class="mer-eval-cont-ul2 fl">
										<c:if test="${item.package_score_eval == '1' }">
										
										
											<li class="clearfix">
												<span class=" fl">包装满意度评价：</span>
												<p class="fl goods-d-level">
													<span id="p_eval1" class="serve-l-red" ></span>
													<span id="p_eval2" class="serve-l-red serve-l-ash" ></span>
													<span id="p_eval3" class="serve-l-red serve-l-ash" ></span>
													<span id="p_eval4" class="serve-l-red serve-l-ash" ></span>
													<span id="p_eval5" class="serve-l-red serve-l-ash" ></span>
													<span id="p_eval6" style="display: none">5</span>
												</p>
												
											</li>
										</c:if>
										<c:if test="${item.package_score_eval == '2' }">
											<li class="clearfix">
												<span class=" fl">包装满意度评价：</span>
												<p class="fl goods-d-level">
													<span id="p_eval1" class="serve-l-red" ></span>
													<span id="p_eval2" class="serve-l-red " ></span>
													<span id="p_eval3" class="serve-l-red serve-l-ash" ></span>
													<span id="p_eval4" class="serve-l-red serve-l-ash" ></span>
													<span id="p_eval5" class="serve-l-red serve-l-ash" ></span>
													<span id="p_eval6" style="display: none">5</span>
												</p>
												
											</li>
										</c:if>
										<c:if test="${item.package_score_eval == '3' }">
											<li class="clearfix">
												<span class=" fl">包装满意度评价：</span>
												<p class="fl goods-d-level">
													<span id="p_eval1" class="serve-l-red" ></span>
													<span id="p_eval2" class="serve-l-red " ></span>
													<span id="p_eval3" class="serve-l-red " ></span>
													<span id="p_eval4" class="serve-l-red serve-l-ash" ></span>
													<span id="p_eval5" class="serve-l-red serve-l-ash" ></span>
													<span id="p_eval6" style="display: none">5</span>
												</p>
												
											</li>
										</c:if>
										<c:if test="${item.package_score_eval == '4' }">
											<li class="clearfix">
												<span class=" fl">包装满意度评价：</span>
												<p class="fl goods-d-level">
													<span id="p_eval1" class="serve-l-red" ></span>
													<span id="p_eval2" class="serve-l-red " ></span>
													<span id="p_eval3" class="serve-l-red " ></span>
													<span id="p_eval4" class="serve-l-red " ></span>
													<span id="p_eval5" class="serve-l-red serve-l-ash" ></span>
													<span id="p_eval6" style="display: none">5</span>
												</p>
												
											</li>
										</c:if>
										<c:if test="${item.package_score_eval == '5' }">
											<li class="clearfix">
												<span class=" fl">包装满意度评价：</span>
												<p class="fl goods-d-level">
													<span id="p_eval1" class="serve-l-red" ></span>
													<span id="p_eval2" class="serve-l-red " ></span>
													<span id="p_eval3" class="serve-l-red " ></span>
													<span id="p_eval4" class="serve-l-red " ></span>
													<span id="p_eval5" class="serve-l-red " ></span>
													<span id="p_eval6" style="display: none">5</span>
												</p>
												
											</li>
										</c:if>
										<c:if test="${item.speed_score_eval == '1' }">
											<li class="clearfix">
												<span class=" fl">送货速度评价：</span>
												<p class="fl goods-d-level">
													<span id="sp_eval1" class="serve-l-red" ></span>
													<span id="sp_eval2" class="serve-l-red" ></span>
													<span id="sp_eval3" class="serve-l-red" ></span>
													<span id="sp_eval4" class="serve-l-red" ></span>
													<span id="sp_eval5" class="serve-l-red serve-l-ash" ></span>
													<span id="sp_eval6" style="display: none">5</span>
												</p>
											</li>
										</c:if>
										<c:if test="${item.speed_score_eval == '2' }">
											<li class="clearfix">
												<span class=" fl">送货速度评价：</span>
												<p class="fl goods-d-level">
													<span id="sp_eval1" class="serve-l-red" ></span>
													<span id="sp_eval2" class="serve-l-red " ></span>
													<span id="sp_eval3" class="serve-l-red serve-l-ash" ></span>
													<span id="sp_eval4" class="serve-l-red serve-l-ash" ></span>
													<span id="sp_eval5" class="serve-l-red serve-l-ash" ></span>
													<span id="sp_eval6" style="display: none">5</span>
												</p>
											</li>
										</c:if>
										<c:if test="${item.speed_score_eval == '3' }">
											<li class="clearfix">
												<span class=" fl">送货速度评价：</span>
												<p class="fl goods-d-level">
													<span id="sp_eval1" class="serve-l-red" ></span>
													<span id="sp_eval2" class="serve-l-red" ></span>
													<span id="sp_eval3" class="serve-l-red" ></span>
													<span id="sp_eval4" class="serve-l-red serve-l-ash" ></span>
													<span id="sp_eval5" class="serve-l-red serve-l-ash" ></span>
													<span id="sp_eval6" style="display: none">5</span>
												</p>
											</li>
										</c:if>
										<c:if test="${item.speed_score_eval == '4' }">
											<li class="clearfix">
												<span class=" fl">送货速度评价：</span>
												<p class="fl goods-d-level">
													<span id="sp_eval1" class="serve-l-red" ></span>
													<span id="sp_eval2" class="serve-l-red" ></span>
													<span id="sp_eval3" class="serve-l-red" ></span>
													<span id="sp_eval4" class="serve-l-red" ></span>
													<span id="sp_eval5" class="serve-l-red serve-l-ash" ></span>
													<span id="sp_eval6" style="display: none">5</span>
												</p>
											</li>
										</c:if>
										<c:if test="${item.speed_score_eval == '5' }">
											<li class="clearfix">
												<span class=" fl">送货速度评价：</span>
												<p class="fl goods-d-level">
													<span id="sp_eval1" class="serve-l-red" ></span>
													<span id="sp_eval2" class="serve-l-red" ></span>
													<span id="sp_eval3" class="serve-l-red" ></span>
													<span id="sp_eval4" class="serve-l-red" ></span>
													<span id="sp_eval5" class="serve-l-red " ></span>
													<span id="sp_eval6" style="display: none">5</span>
												</p>
											</li>
										</c:if>
										<c:if test="${item.service_score_eval == '1' }">
											<li class="clearfix">
												<span class=" fl">服务态度评价：</span>
												<p class="fl goods-d-level">
													<span id="sp_eval1" class="serve-l-red" ></span>
													<span id="sp_eval2" class="serve-l-red serve-l-ash" ></span>
													<span id="sp_eval3" class="serve-l-red serve-l-ash" ></span>
													<span id="sp_eval4" class="serve-l-red serve-l-ash" ></span>
													<span id="sp_eval5" class="serve-l-red serve-l-ash" ></span>
													<span id="sp_eval6" style="display: none">5</span>
												</p>
											</li>
										</c:if>
										<c:if test="${item.service_score_eval == '2' }">
											<li class="clearfix">
												<span class=" fl">服务态度评价：</span>
												<p class="fl goods-d-level">
													<span id="sp_eval1" class="serve-l-red" ></span>
													<span id="sp_eval2" class="serve-l-red " ></span>
													<span id="sp_eval3" class="serve-l-red serve-l-ash" ></span>
													<span id="sp_eval4" class="serve-l-red serve-l-ash" ></span>
													<span id="sp_eval5" class="serve-l-red serve-l-ash" ></span>
													<span id="sp_eval6" style="display: none">5</span>
												</p>
											</li>
										</c:if>
										<c:if test="${item.service_score_eval == '3' }">
											<li class="clearfix">
												<span class=" fl">服务态度评价：</span>
												<p class="fl goods-d-level">
													<span id="sp_eval1" class="serve-l-red" ></span>
													<span id="sp_eval2" class="serve-l-red" ></span>
													<span id="sp_eval3" class="serve-l-red " ></span>
													<span id="sp_eval4" class="serve-l-red serve-l-ash" ></span>
													<span id="sp_eval5" class="serve-l-red serve-l-ash" ></span>
													<span id="sp_eval6" style="display: none">5</span>
												</p>
											</li>
										</c:if>
										<c:if test="${item.service_score_eval == '4' }">
											<li class="clearfix">
												<span class=" fl">服务态度评价：</span>
												<p class="fl goods-d-level">
													<span id="sp_eval1" class="serve-l-red" ></span>
													<span id="sp_eval2" class="serve-l-red" ></span>
													<span id="sp_eval3" class="serve-l-red" ></span>
													<span id="sp_eval4" class="serve-l-red " ></span>
													<span id="sp_eval5" class="serve-l-red serve-l-ash" ></span>
													<span id="sp_eval6" style="display: none">5</span>
												</p>
											</li>
										</c:if>
										<c:if test="${item.service_score_eval == '5' }">
											<li class="clearfix">
												<span class=" fl">服务态度评价：</span>
												<p class="fl goods-d-level">
													<span id="sp_eval1" class="serve-l-red" ></span>
													<span id="sp_eval2" class="serve-l-red" ></span>
													<span id="sp_eval3" class="serve-l-red" ></span>
													<span id="sp_eval4" class="serve-l-red" ></span>
													<span id="sp_eval5" class="serve-l-red " ></span>
													<span id="sp_eval6" style="display: none">5</span>
												</p>
											</li>
										</c:if>
									</ul>
									</c:if>
								</c:forEach>
							</div>
							<div class="mer-eval">
								<h2>
									评价说明：
								</h2>

								<ul class="eval-intro">
									<li>
										1.满意度评价用来对本次购物过程中的服务进行评价，评价大于100元的订单可以获得20个京豆；
									</li>
									<li>
										2.商品评价用来对所购商品的质量进行评价，赠品评价暂时不奖励京豆，奖励京豆规则详见京豆说明；
									</li>
									<li>
										3.商品评价资格有效期为订单完成后半年内，满意度评价资格有效期为订单完成后三个月内。
									</li>
								</ul>

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
