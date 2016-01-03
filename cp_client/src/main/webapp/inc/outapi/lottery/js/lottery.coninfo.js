var userInfo = {};

$(document)
		.ready(
				function() {
					// 为了执行onunload事件
					if (window.DeviceMotionEvent) {
						if (check(/safari/)) {
							window.addEventListener('devicemotion',
									deviceMotionHandler, false);
						}
					}
					sessionStorage.removeItem("xsscInfos");
					createLotteryObj($("#lotteryCategory").val());
					var totalStake = caltotalstake();
					$("#confirmcardInfo2").hide();
					var hasUserInfo = $("#hasUserInfo").val();
					if (hasUserInfo == 0) {
						var jsonstr = sessionStorage.getItem("lotteryUserInfo");
						if (!!jsonstr) {
							userInfo = JSON.parse(jsonstr);
							$("#fullName").val(userInfo.fullName);
							$("#idCardNumber").val(userInfo.idCardNumber);
							$("#mobile").val(userInfo.mobile);
						}
						
					}
					// 如果没有投注，隐藏信息并提示
					if (totalStake <= 0) {
						var url = $("#noStakeUrl").val();
							$("#confirmcardInfo1").html('没有投注信息，<a href="' + url+ '" style="color:blue;">点击</a>投注').css("text-align", "center");
						if ($("#errInfo").length == 0) { // 如果没有显示错误信息
							$(".mc .radius:gt(1),.mc h3:gt(1)").remove();
						} else { // 如果显示了错误信息
							$(".mc .radius:eq(0),.mc .radius:gt(2),.mc h3:gt(1)").remove();
						}
						$("#btns").remove();
						return;
					}
					if($("#lotteryCategory").val() == 301){
						$("#confirmcardInfo1").html(confirmcardHtml(false));
					} else {
						// 展现号码列表信息
						if (totalStake < 5) {
							$("#confirmcardInfo1").html(confirmcardHtml(false));
						} else {
							$("#confirmcardInfo1").html(confirmcardHtml(true));
							$("#confirmcardInfo2").html(confirmcardHtml(false));
						}
					}
					// 组织号码
					$("#lotteryNumberList").val(getLotteryNumberList());

					// 总注数
					$("#s_totalstake").html(totalStake);
					$("#totalStake").val(totalStake);

					// 倍数
					$("#p_multiple").html("倍数：" + caltotalMulti() + "倍");
					$("#multi").val(caltotalMulti());

					// 追号期数
					if (caltotalAppend() > 1) {
						$("#p_append").html("追号：" + caltotalAppend() + "期");
						$("#append").val(caltotalAppend());
					}
					// 总金额
					var lotteryTotalFee = caltotalpay(totalStake);
					$("#money").html(lotteryTotalFee + ".00");
					$("#lotteryTotalFee").val(lotteryTotalFee);
					// 停追类型，只有1,3 中奖即停追，不停追
					if (lotteryCategory == 1004) {
						$("#stopflag").val(lotteryObj.appendType);
					} else if (lotteryCategory != 301) {
						$("#stopflag").val(lotteryObj.appendissue.stopflag);
					}

					// $("#appendissueinfo").val(getAppendIssue(totalStake));

					// 余额 京豆 购买
					var balance = $("#balance").val();
					var isBalance = true;
					var isJingbean = true;
					var jingbean = 0;
					if (!isNaN($("#jingbean").val())) {
						jingbean = $("#jingbean").val();
					} else {
						isJingbean = false;
					}

					// 追号
					if (caltotalAppend() > 1) {
						$("#payType_4").attr("disabled", "disabled");
						$("#coupon_detail").hide();
						$("#couponTip").html("&nbsp;&nbsp;&nbsp;优惠券暂不支持追号订单")
								.show();
					}

					// 优惠券支付
					var isCoupon = false;
					if ($("#coupon").val() == 1) {
						isCoupon = true;
					}
					// 余额不足
					if (lotteryTotalFee > balance) {
						$("#payType_1").attr("disabled", "disabled");
						$("#p_payPassword").hide();
						$("#balanceNull").show();
						isBalance = false;
					}
					// 京豆不足
					if (lotteryTotalFee * 100 > jingbean) {
						$("#payType_6").attr("disabled", "disabled");
						$("#p_payPassword").hide();
						$("#jingbeanMuch").hide();

						$("#jingbeanNull").show();
						isJingbean = false;
					}

					var disabled = $("#payType_4").attr("disabled");
					if (disabled == "disabled") {
						isCoupon = false;
					}
					if (!isBalance && !isJingbean && !isCoupon) { // 如果京豆和余额
						// 都不足
						$("#payType_2").attr("checked", true);
					} else {
						// 余额可用
						if (isBalance) {
							$("#payType_1").attr("checked", true);
							// 以防被禁掉
							$("#payType_1").removeAttr("disabled");
							$("#p_payPassword").show();
						}
						
						
						$("#jingbeanDesc").html("" + (lotteryTotalFee * 100));
						if (!isBalance && isJingbean) { // 京豆可用
							$("#jingbeanMuch").show();
							$("#payType_6").attr("checked", true);
							// 以防被禁掉
							$("#payType_6").removeAttr("disabled");
							$("#p_payPassword").show();
						}
						if (!isBalance && !isJingbean && isCoupon) {
							$("#payType_4").attr("checked", true);
							// 以防被禁掉
							$("#payType_4").removeAttr("disabled");
							$("#coupon_detail").show();
							$("#p_payPassword").show();
						}
					}

					$('input:radio[name="lotteryOrder.payType"]').click(
							function() {
								var val = $(this).val();
								if (val != 4) {
									$("#couponNull").hide();
									$("#coupon_detail").slideUp(500);
								}
								if (val == 6) {
									$("#jingbeanMuch").show();
								} else {
									$("#jingbeanMuch").hide();
									if (val == 4) {
										$("#coupon_detail").slideDown(500);
									}
								}
								if (val != 2) { // 不是在线支付 要使用支付密码
									$("#p_payPassword").show();
								} else {
									$("#p_e_payPassword").hide();
									$("#p_payPassword").hide();
								}
							});

					function deviceMotionHandler(eventData) {
						var acceleration = eventData.accelerationIncludingGravity;
					}

					// 用户名
					var ele1_1 = [ "#==''", "姓名不能为空" ];
					var ele1_2 = [ "!checkRule.ChineseName.test(#)", "姓名格式不正确" ];
					var ele1 = {
						"target" : '#fullName',
						"error" : '#e_fullName',
						"arr" : [ ele1_1, ele1_2 ]
					};

					// 身份证
					var ele2_1 = [ "#==''", "身份证号不能为空" ];
					var ele2_2 = [ "onCheckRegIDno('idCardNumber')","身份证号格式不正确" ];
					var ele2_3 = [ "onCheckIDnoYoung('idCardNumber')","未满18岁不能购买彩票" ];
					var ele2 = {
						"target" : '#idCardNumber',
						"error" : '#e_idCardNumber',
						"arr" : [ ele2_1, ele2_2, ele2_3 ]
					};

					// 手机号
					var ele3_1 = [ "#==''", "手机号不能为空" ];
					var ele3_2 = [ "#!=''&&!checkRule.Mobile.test(#)",
							"手机号码格式不正确" ];
					var ele3 = {
						"target" : '#mobile',
						"error" : '#e_mobile',
						"arr" : [ ele3_1, ele3_2 ]
					};
					var eles = [ ele1, ele2, ele3 ];

					function checkPayPwd() {
						return true;
					}

					var subFlag = false;

					if ($("#firstType").val() == 0) {
						bindTesting(eles);
					}

					$("#payPassword").blur(function() {
						checkPayPwd();
					});

					// 使用优惠券相关事件
					$("input[name='lotteryOrder.couponIds']").click(
							function(event) {
								var couponType = $(this).attr(
										"data-coupon-type");
								if (couponType == 0) { // 京券
									$("input[data-coupon-type='2']")
											.removeAttr("checked");
								} else { // 东券
									$("input[data-coupon-type='1']")
											.removeAttr("checked");
								}
								caculateMoney($(this));
								event.stopPropagation();
							});

					var couponMoney = 0;
					var leftMoney;

					function caculateMoney(checkObj) {
						var obj = $("input[name='lotteryOrder.couponIds']:checked");
						var length = obj.length;
						var money = 0;
						obj.each(function() {
							var value = $(this).attr("data-discount");
							if ($(this).attr("data-coupon-type") == 0) {
								money += parseInt(value);
							}
						});

						if (checkObj != undefined && checkObj.attr("checked")) {
							var discount = parseInt(checkObj.attr("data-discount"));
							// 如果当前选择的金额足以支付, 取消其他选中
							if (money - discount >= lotteryTotalFee) { // 如果前面的已选的金额可以支付，取消当前
								checkObj.attr("checked", false);
								alertTip("已选优惠券总金额足以支<br />付，不需要此张优惠券");
								money = money - discount;
							} else if (discount >= lotteryTotalFee
									&& length > 1) {
								obj.each(function() {
									$(this).attr("checked", false);
								});
								alertTip("此张优惠券已足以支付订单<br />您不需要使用其他优惠券");
								checkObj.attr("checked", true);
								money = discount;
							}
							obj = $("input[name='lotteryOrder.couponIds']:checked");// 重新获取选中的对象
						}

						couponMoney = money;
						if (couponMoney >= lotteryTotalFee) {
							$("#couponNull").hide();
						}
						$("#couponCount").html(obj.length);
						$("#couponMoney").html(money);
					}
					$(".coupon_span").click(function(event) {
						var input = $(this).prev("input");
						var checked = input.attr("checked");
						if (checked) {
							input.attr("checked", false);
						} else {
							input.attr("checked", true);
						}
						caculateMoney(input);
						event.stopPropagation();
					});

					if ($("#couponListSize").val() == 9) {
						$("#showMore").click(function() {
							var obj = $("#couponList");
							var hide = obj.attr("data-hide");
							if (hide == 1) {
								$(".counpon_item:gt(8)").slideDown(300);
								obj.attr("data-hide", "0");
								$(this).hide();
							} else {
								$(".counpon_item:gt(8)").slideUp(300);
								obj.attr("data-hide", "1");
							}
						});
						$(".counpon_item:gt(8)").hide();
					}

					caculateMoney();
					// 验证优惠券金额是否足够
					function checkCoupon() {
						// 优惠券金额不足
						if (lotteryTotalFee > couponMoney) {
							$("#couponNull").html("&nbsp;&nbsp;优惠券金额不足").show();
							return false;
						}

						var obj = $("input[name='lotteryOrder.couponIds']:checked");
						var length = obj.length;
						var money = 0;
						for ( var i = 0; i < length; i++) {
							var item = obj.eq(i);
							var value = item.attr("data-discount");
							var discount;
							if (item.attr("data-coupon-type") == 0) {
								discount = parseInt(value);
								if (couponMoney - discount >= lotteryTotalFee) { // 有多余的券
									$("#couponNull").html("&nbsp;&nbsp;建议重新选择优惠券").show();
									return false;
								}
								if (lotteryTotalFee <= discount && length > 1) {
									$("#couponNull").html("&nbsp;&nbsp;优惠券选择有误").show();
									return false;
								}
								money += discount;
							}
						}
						return true;
					}

					$("#sub_btn").click(
									function() {
										if (($("#fullName").attr("value") && $("#fullName").attr("value").length == 0)||($("#e_fullName_2").text().length != 0 && $("#e_fullName_2").css("display") != "none"))
										 {
											$("#fullName").focus();
										} else if (($("#idCardNumber").attr("value") && $("#idCardNumber").attr("value").length == 0)|| ($("#e_idCardNumber_2").text().length != 0 && $("#e_idCardNumber_2").css("display") != "none")) {
											$("#idCardNumber").focus();
										} else if (($("#mobile").attr("value") && $("#mobile").attr("value").length == 0)|| ($("#e_mobile_2").text().length != 0 && $("#e_mobile_2").css("display") != "none")) {
											$("#mobile").focus();
										}
										if (subFlag) {
											$("#p_e_payPassword").show();
											$("#e_payPassword").html("为避免您的损失,请不要重复提交");
											return;
										}

										if ($("#firstType").val() == 0) {
											if (!submitCheck(eles)) {
												return;
											}
										}
										if ($("#totalStake").val() < 1) {
											$("#p_e_payPassword").show();
											$("#e_payPassword").html("您还未下注,请选择号码试试手气吧");
											return;
										}

										var payType = $("input[name='lotteryOrder.payType']:checked").val();
										var isCouponComfirm = false;
										if (payType != 2) { // 余额或京豆支付，必须提供支付密码
											if (!checkPayPwd()) {
												return;
											}
											if (payType == 4) {
												if (!checkCoupon()) {
													return;
												}
												if (couponMoney > lotteryTotalFee) {
													getIndexTip();
													isCouponComfirm = true;
												}
											}
										}

										if (hasUserInfo == 0) {
											userInfo.fullName = $("#fullName").val();
											userInfo.idCardNumber = $("#idCardNumber").val();
											userInfo.mobile = $("#mobile").val();
											sessionStorage.setItem("lotteryUserInfo",JSON.stringify(userInfo));
										}

										if (!isCouponComfirm) {
											subFlag = true;
											$("#sub_btn").hide();
											$("#btns").html("正在提交中...");
											var _lottery_refer = sessionStorage.getItem("lottery_refer");
											if(_lottery_refer !== null && _lottery_refer !== undefined){
												$("#referFrom").val(_lottery_refer);												
												sessionStorage.removeItem("lottery_refer");
											}
											$("#baseForm").submit();
										}
									});

					$("#cancel").click(function() {
						$("body").css("opacity", "1");
						$("#indexTipOuter").hide();
					});

					$("#indexSure").click(function() {
						$("#indexTipOuter").hide();	
						subFlag = true;
						$("#sub_btn").hide();
						$("#btns").html("正在提交中...");
						var _lottery_refer = sessionStorage.getItem("lottery_refer");						
						if(_lottery_refer !== null && _lottery_refer !== undefined){
							$("#referFrom").val(_lottery_refer);							
							sessionStorage.removeItem("lottery_refer");
						}
						$("#baseForm").submit();
					});

					$("input").keydown(function(event) {
						if (event.keyCode == 13) {
							$("#sub_btn").click();
							return false;
						}
					});

					if ($("#usedFlag").val() == 0) {
						$("input[name='lotteryOrder.payType']")
								.each(
										function() {
											var value = $(this).val();
											if (value != 2) {
												$(this).attr("disabled",
														"disabled");
												$("#coupon_detail").hide();
												$("#balanceNull")
														.html(
																"&nbsp;&nbsp;未开启安全支付，无法使用");
												$("#jingbeanNull")
														.html(
																"&nbsp;&nbsp;未开启安全支付，无法使用");
												if (getOrderType($lotteryCategory) != 3) {
													$("#couponTip")
															.html(
																	"&nbsp;&nbsp;未开启安全支付，无法使用")
															.show();
												}
											} else {
												$(this).attr("checked", true);
											}
										});
						$("#p_payPassword").hide();
					}

				});

/**
 * 获取下单的类型
 * 
 * @param lotteryCategory
 * @returns
 */
function getOrderType(lotteryCategory) {
	var str = sessionStorage.getItem("lottery_" + lotteryCategory);
	var json = JSON.parse(str);
	return json.orderType;
}

function getIndexTip() {
	var x = $(window).width();
	var y = $(window).height();
	$("#indexTipOuter").show();
	$("body").css("overflow", "hidden");
	var div_x = $("#indexTip").width();
	var div_y = $("#indexTip").height();
	var pos_x = Math.ceil((x - div_x) / 2);
	var pos_y = Math.ceil((y - div_y) / 2);
	$("#indexTip").css('left', pos_x);
	$("#indexTip").css('top', pos_y);
}
