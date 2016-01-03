<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
	<head>
		<title>福彩3D选号</title>
		<meta name="author" content="">
		<Meta http-equiv="Expires" Content="Wed, 26 Feb 1997 09:21:57 GMT">
		<meta http-equiv="Last-Modified" content="Wed, 26 Feb 1997 09:21:57 GMT">
		<meta http-equiv="Cache-Control" content="no-store, no-cache, must-revalidate,max-age=0,post-check=0, pre-check=0,false">
		<meta http-equiv="Pragma" CONTENT="no-cache">
		<meta name="viewport"	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<meta name="format-detection" content="telephone=no">
		<link rel="stylesheet" type="text/css" href="<%=basePath %>/inc/outapi/lottery/css/base.css?v=20140610" charset="utf-8" />
		<link rel="stylesheet" type="text/css" href="<%=basePath %>/inc/outapi/lottery/css/base2013.css?v=20140610" charset="utf-8" />
		<script type="text/javascript" src="<%=basePath %>inc/outapi/lottery/js/jquery-1.6.2.min.js"></script>
		<script type="text/javascript" src="<%=basePath %>inc/outapi/lottery/js/common.js?v=20140610"></script>
		<script type="text/javascript" src="<%=basePath %>inc/outapi/lottery/js/spin.min.js?v=20140610"></script>
		<script type="text/javascript">
			window.onunload = function() {};		
        </script>
	</head>

	<body id="body" class="plist">
		<div align="center"><script src="<%=basePath %>inc/outapi/lottery/js/lottery.3d.js?v=20140610" charset="gbk"></script> 
		<script src="<%=basePath %>inc/outapi/lottery/js/ball.js?v=20140610" charset="gbk"></script> 
		<script src="<%=basePath %>inc/outapi/lottery/js/lottery.base.js?v=20140610" charset="gbk"></script> 
		</div>
		<style>
			.redball-list .dantuo {
				background: url(<%=basePath %>/inc/outapi/lottery/images/cp_img_2.png);
				background-position: -40px -3px;
			}
		</style>
		<form id="addssq" action="<%=basePath %>lottery/fucai3dOrderInfo.action"	method="post"></form>
		<div class="cp-xh list">
			<div class="mc fc">
				<div class="mc-top clear">
					<div class="fl ga">
						<img src="<%=basePath %>/inc/outapi/lottery/images/cp_img_2.png" width="50" height="50" />
					</div>
					<div class="fl">
						<p>
							<span id='issue-name' class="red">${crep.currperiod}</span><span class="ga">期</span>
						</p>
						<p>
							<span id="issue-end" style="font-size: 14px;"></span>
						</p>
						<p>
							<span class="gray" id="remain-time" style="font-size: 14px;"></span>
						</p>
						<span class="gray" id="gray_span" style='display: none'>摇摇手机,机选1注</span>
					</div>
				</div>
				<div class="ac cp-tab cp-tab-three" style="margin: 0">
					<ul>
						<li id="zhixuan" class="first curr-f"><a href="#fs_dx_area">直选</a></li>
						<li id="zu3" class="curr-m"><a href="#fs_z3_area">组三</a></li>
						<li id="zu6" class="last curr-l"><a href="#fs_z6_area">组六</a></li>
					</ul>
				</div>
				<div id='fs_dx_area' class="i-mc" style='display: none'>
					<p class="f-t gray">
						每项至少选择一个球
					</p>
					<div id='fs_bai_list' class="red-area fc-red-area-1">
						<div class="tit clear">
							<div class="fl">
								<span class="red ga">百位</span>
							</div>
						</div>
						<ul id="baiball-list" class="redball-list ball-list clear">
							<li><div class="ball">0</div></li>
							<li><div class="ball">1</div></li>
							<li><div class="ball">2</div></li>
							<li><div class="ball">3</div></li>
							<li><div class="ball">4</div></li>
							<li><div class="ball">5</div></li>
							<li><div class="ball">6</div></li>
							<li><div class="ball">7</div></li>
							<li><div class="ball">8</div></li>
							<li><div class="ball">9</div></li>
						</ul>
					</div>

					<div id='fs_shi_list' class="red-area fc-red-area-1">
						<div class="tit clear">
							<div class="fl">
								<span class="red ga">十位</span>
							</div>
						</div>
						<ul id="shiball-list" class="redball-list ball-list clear">
							<li><div class="ball">0</div></li>
							<li><div class="ball">1</div></li>
							<li><div class="ball">2</div></li>
							<li><div class="ball">3</div></li>
							<li><div class="ball">4</div></li>
							<li><div class="ball">5</div></li>
							<li><div class="ball">6</div></li>
							<li><div class="ball">7</div></li>
							<li><div class="ball">8</div></li>
							<li><div class="ball">9</div></li>
						</ul>
					</div>

					<div id='fs_ge_list' class="red-area fc-red-area-1">
						<div class="tit clear">
							<div class="fl">
								<span class="red ga">个位</span>
							</div>
						</div>
						<ul id="geball-list" class="redball-list ball-list clear">
							<li><div class="ball">0</div></li>
							<li><div class="ball">1</div></li>
							<li><div class="ball">2</div></li>
							<li><div class="ball">3</div></li>
							<li><div class="ball">4</div></li>
							<li><div class="ball">5</div></li>
							<li><div class="ball">6</div></li>
							<li><div class="ball">7</div></li>
							<li><div class="ball">8</div></li>
							<li><div class="ball">9</div></li>
						</ul>
					</div>
					<div id="fs_dx_text" class="ball-account">
						0个号码，共
						<span class="red">0</span>注，共
						<span class="red">￥0.00</span>元
					</div>
					<div id="fs_dx_msg" class="ball-account" style='color: red; display: none'></div>
					<div id='fs_dx_btns' class="btns ac">
						<input id="btn-random1" type="button" value="机选1注" class="common-btn">
						<input id="btn-dx-clear" type="button" value="清空所选"class="common-btn">
						<input id="btn-dx-append" type="button" value="投注" class="sub_btn">
						<input id="multi_z3" name="order.multi" value="1" onpaste="return false;" autocomplete="off" maxlength="2" size="2" type="hidden">
					</div>
					<br>
					<br>
				</div>

				<div id='fs_z3_area' class="i-mc" style='display: none'>
					<p class="f-t gray">
						至少选择两个球
					</p>
					<div id='fs_z3_list' class="red-area">
						<ul id="z3ball-list" class="redball-list ball-list clear">
							<li><div class="ball">0</div></li>
							<li><div class="ball">1</div></li>
							<li><div class="ball">2</div></li>
							<li><div class="ball">3</div></li>
							<li><div class="ball">4</div></li>
							<li><div class="ball">5</div></li>
							<li><div class="ball">6</div></li>
							<li><div class="ball">7</div></li>
							<li><div class="ball">8</div></li>
							<li><div class="ball">9</div></li>
						</ul>
					</div>
					<div id="fs_z3_text" class="ball-account">
						0个号码，共
						<span class="red">0</span>注，共
						<span class="red">￥0.00</span>元
					</div>
					<div id="fs_z3_msg" class="ball-account"style='color: red; display: none'></div>
					<div id='fs_z3_btns' class="btns ac">
						<input id="random_fs_z3" type="button" value="机选2注"	class="common-btn">
						<input id="clear_fs_z3" type="button" value="清空所选"	class="common-btn">
						<input id="btn-z3-append" type="button" value="投注" class="sub_btn">
						<input id="multi_z3" name="order.multi" value="1" onpaste="return false;" autocomplete="off" maxlength="2" size="2"	type="hidden">
					</div>
					<br>
					<br>
				</div>

				<div id='fs_z6_area' class="i-mc" style='display: none'>
					<p class="f-t gray">
						至少选择三个球
					</p>
					<div id='fs_z6_list' class="red-area">
						<ul id="z6ball-list" class="redball-list ball-list clear">
							<li><div class="ball">0</div></li>
							<li><div class="ball">1</div></li>
							<li><div class="ball">2</div></li>
							<li><div class="ball">3</div></li>
							<li><div class="ball">4</div></li>
							<li><div class="ball">5</div></li>
							<li><div class="ball">6</div></li>
							<li><div class="ball">7</div></li>
							<li><div class="ball">8</div></li>
							<li><div class="ball">9</div></li>
						</ul>
					</div>
					<div id="fs_z6_text" class="ball-account">
						0个号码，共
						<span class="red">0</span>注，共
						<span class="red">￥0.00</span>元
					</div>
					<div id="fs_z6_msg" class="ball-account" style='color: red; display: none'></div>
					<div id='fs_z6_btns' class="btns ac">
						<input id="random_fs_z6" type="button" value="机选1注" class="common-btn">
						<input id="clear_fs_z6" type="button" value="清空所选" class="common-btn">
						<input id="btn-z6-append" type="button" value="投注" class="sub_btn">
						<input id="multi_z6" name="order.multi" value="1" onpaste="return false;" autocomplete="off" maxlength="2" size="2"	type="hidden">
					</div>
					<br>
					<br>
				</div>
			</div>
			<br />
			<br />
		</div>

	<script type="text/javascript">
	window.sessionStorage.setItem("period3d",${crep.currperiod});
	window.sessionStorage.setItem("currendtime3d",'${crep.currendtime}');
   //默认值
   createLotteryObj(2);

   $("#randomnum-red").val(0);
   $("#randomnum-blue").val(0);
   $('#ssq_select').val(0);
   
   var touzhu_type = "zhixuan";
   
   var ball = null;
   var canSell = 'true';
   var time = '${time}'; //本期投注剩余时间(毫秒)

   var lasttiltLR = 0;
   var accelerCount = 0; 
   
   function deviceMotionHandler(eventData) {
  	var acceleration = eventData.accelerationIncludingGravity;
  		var tiltLR = Math.round(((acceleration.x) / 9.81) * -90);
		if(Math.abs(tiltLR) > 30){
			if(lasttiltLR ==0 ){
				lasttiltLR = tiltLR;
			}
			var temp = lasttiltLR;			
			if(temp < 0 && tiltLR > 0){
				accelerCount ++;
				lasttiltLR = tiltLR;
			}
			if(temp > 0 && tiltLR < 0){
				accelerCount ++;
				lasttiltLR = tiltLR;
			}			
			if(accelerCount > 2){
				 if(touzhu_type == "zhixuan"){
				 	document.getElementById("btn-random1").click();
				 }
				 else if(touzhu_type == "zu3"){
				 	document.getElementById("random_fs_z3").click();
				 }
				 else if(touzhu_type == "zu6"){
				 	document.getElementById("random_fs_z6").click();
				 }
				 accelerCount = 0;
				 lasttiltLR = 0;
			}			
		}
   }
   
   $(document).ready(function() {
   		$(".i-mc").hide(); //Hide all content
		$("#zhixuan").addClass("curr").show(); //Activate first tab
		$(".i-mc:first").show(); //Show first tab content
		//On Click Event
		$("div.cp-tab-three li").click(function() {
			touzhu_type = this.id;
			$("div.cp-tab-three li").removeClass("curr"); //Remove any "active" class
			$(this).addClass("curr"); //Add "active" class to selected tab
			$(".i-mc").hide(); //Hide all tab content
			var activeTab = $(this).find("a").attr("href"); //Find the rel attribute value to identify the active tab + content
			$(activeTab).fadeIn(); //Fade in the active content
			return false;
		});

       //福彩3D
       FC3D_init();
	   
	   if (window.DeviceMotionEvent) {
          if(checkBrowser())
		  {
    	  	$("#gray_span").show();
			 window.addEventListener('devicemotion', deviceMotionHandler, false);
		  }
		  else
		  {
		 	 $("#gray_span").hide();
		  }
  	  }
	  else
	  {
	  	 $("#gray_span").hide();
	  }
	   
   });

   function FC3D_init() {
       canSellCheck(canSell, '');
       ball = new Lottery3D();
       ball.bind_btn();
       if (!canSell) {
           $(".sub_btn").attr("disabled", "disabled");
           $(".sub_btn").attr("class", "sub_btn no-btn");
           $("#remain-time").text("临时停售");
           return false;
       } else {
           $(".sub_btn").attr("class", "sub_btn");
       }

       var nextName = '${nextCurrperiod}';
       var nextStart = "${nextSaleTime}";//下期开售时间
       var callback = function() {
           $(".sub_btn").attr("disabled", "disabled");
           $(".sub_btn").attr("class", "sub_btn no-btn");

           $("#issue-name").text(nextName);
           $("#issue-end").text("预计开售时间：" + nextStart);
           /*expectTime = expectTime < coolTime ? expectTime : coolTime;
           var expect = new LotteryTimer(canSell, expectTime, "remain-time", function() {
               window.location.reload(true);
           });
           expect.start(expect);*/
       };
       var remain = new LotteryTimer(canSell, time, "remain-time", callback);
       remain.start(remain);

       //LotteryPlay.init(50);
   }

   function btu(){
       $("#addssq").submit();
   }
   </script>
	<script type="text/javascript">
	    $(function(){
	        $("#btnJdkey").click(function() {
	            if ($("#jdkey").css("display") == "none") {
	                $("#jdkey").show()
	            } else {
	                $("#jdkey").hide()
	            }
	        })
	    });
	</script>
	</body>