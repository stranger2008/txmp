<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html >
	<head>
		<title>双色球选号 </title>
		<meta name="author" content="">
        <meta http-equiv="Expires" content="Wed, 26 Feb 1997 09:21:57 GMT">
		<meta http-equiv="Last-Modified" content="Wed, 26 Feb 1997 09:21:57 GMT">
		<meta http-equiv="Cache-Control" content="no-store, no-cache, must-revalidate,max-age=0,post-check=0, pre-check=0,false">   
        <meta http-equiv="Pragma" content="no-cache">  		
       	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
	    <meta name="apple-mobile-web-app-capable" content="yes">
        <meta name="apple-mobile-web-app-status-bar-style" content="black">
        <meta name="format-detection" content="telephone=no">
		<link rel="stylesheet" type="text/css" href="<%=basePath %>inc/outapi/lottery/css/base.css" charset="gbk">
        <link rel="stylesheet" type="text/css" href="<%=basePath %>inc/outapi/lottery/css/base2013.css" charset="gbk">
		<script type="text/javascript" src="<%=basePath %>inc/outapi/lottery/js/jquery-1.6.2.min.js"></script>
        <script type="text/javascript" src="<%=basePath %>inc/outapi/lottery/js/common.js"></script>
        <script type="text/javascript" src="<%=basePath %>inc/outapi/lottery/js/spin.min.js"></script>
		<script type="text/javascript">
			window.onunload = function() {};
        </script>
        <script src="<%=basePath %>inc/outapi/lottery/js/ball.js" charset="gbk"></script>
		<script src="<%=basePath %>inc/outapi/lottery/js/lottery.ssq.js" charset="gbk"></script>
		<script src="<%=basePath %>inc/outapi/lottery/js/lottery.base.js" charset="gbk"></script>
		<script type="application/javascript" src="<%=basePath %>inc/outapi/lottery/js/iscroll.js" charset="gbk"></script>
	</head>	
	
<body id="body" class="plist">
	<link rel="stylesheet" type="text/css" href="<%=basePath %>inc/outapi/lottery/css/scrollbar.css"/>
	<style>
	        .redball-list .dantuo{background: url(<%=basePath %>inc/outapi/lottery/images/icoin-half.png); background-position:-40px -3px;}
			.overFlow{overflow-y:scroll;}
	</style>
   <form id="addssq" action="<%=basePath %>lottery/doubleChromosphereOrder.action" method="get"></form>
   <div class="cp-xh list">
   <div class="mc">
       <div class="mc-top clear">
           <div class="fl ga">
               <img src="<%=basePath %>/inc/outapi/lottery/images/cp_img_1.png" width="50" height="50">
           </div>
           <div class="fl">
               <p><span id="issue-name" class="red">${crep.currperiod }</span><span class="ga">期</span></p>
               <p><span id="issue-end" style="font-size: 14px;"></span></p>
               <p><span id="remain-time" style="font-size: 14px;"><b></b><em>天</em><b></b><em>小时</em><b></b><em>分</em><b></b><em>秒</em></span></p>
               <p>
                   <span class="gray" id="gray_span" style="display: none;">摇摇手机,机选1注</span>
               </p>
           </div>
       </div>
       <div id="fs_area" class="i-mc" style="">
           <div id="fs_red_list" class="red-area">
               <div class="tit clear">
                   <div class="fl"><span class="red ga">红球</span><span class="gray">至少选择6个号码</span></div>
                   <div class="fr">机选
                       <select id="randomnum-red" style="width:70px;">
                           <option value="0">选择</option>
                           <option value="6">6</option>
                           <option value="7">7</option>
                           <option value="8">8</option>
                           <option value="9">9</option>
                           <option value="10">10</option>
                           <option value="11">11</option>
                           <option value="12">12</option>
                           <option value="13">13</option>
                           <option value="14">14</option>
                           <option value="15">15</option>
                           <option value="16">16</option>
                       </select>
                   个</div>
               </div>
               <ul id="redball-list" class="redball-list ball-list clear">
                   <li><div class="ball">01</div></li>
                   <li><div class="ball">02</div></li>
                   <li><div class="ball">03</div></li>
                   <li><div class="ball">04</div></li>
                   <li><div class="ball">05</div></li>
                   <li><div class="ball">06</div></li>
                   <li><div class="ball">07</div></li>
                   <li><div class="ball">08</div></li>
                   <li><div class="ball">09</div></li>
                   <li><div class="ball">10</div></li>
                   <li><div class="ball">11</div></li>
                   <li><div class="ball">12</div></li>
                   <li><div class="ball">13</div></li>
                   <li><div class="ball">14</div></li>
                   <li><div class="ball">15</div></li>
                   <li><div class="ball">16</div></li>
                   <li><div class="ball">17</div></li>
                   <li><div class="ball">18</div></li>
                   <li><div class="ball">19</div></li>
                   <li><div class="ball">20</div></li>
                   <li><div class="ball">21</div></li>
                   <li><div class="ball">22</div></li>
                   <li><div class="ball">23</div></li>
                   <li><div class="ball">24</div></li>
                   <li><div class="ball">25</div></li>
                   <li><div class="ball">26</div></li>
                   <li><div class="ball">27</div></li>
                   <li><div class="ball">28</div></li>
                   <li><div class="ball">29</div></li>
                   <li><div class="ball">30</div></li>
                   <li><div class="ball">31</div></li>
                   <li><div class="ball">32</div></li>
                   <li><div class="ball">33</div></li>
               </ul>
           </div>
            
           <div id="fs_blue_list" class="blue-area">
               <div class="tit clear">
                   <div class="fl"><span class="blue ga">蓝球</span><span class="gray">至少选择1个号码</span></div>
                   <div class="fr">机选
                       <select id="randomnum-blue" name="" style="width:70px;">
                           <option value="0">选择</option>
                           <option value="1">1</option>
                           <option value="2">2</option>
                           <option value="3">3</option>
                           <option value="4">4</option>
                           <option value="5">5</option>
                           <option value="6">6</option>
                           <option value="7">7</option>
                           <option value="8">8</option>
                           <option value="9">9</option>
                           <option value="10">10</option>
                           <option value="11">11</option>
                           <option value="12">12</option>
                           <option value="13">13</option>
                           <option value="14">14</option>
                           <option value="15">15</option>
                           <option value="16">16</option>
                       </select>
                   个</div>
               </div>
               <ul id="blueball-list" class="blueball-list ball-list clear">
                   <li><div class="ball">01</div></li>
                   <li><div class="ball">02</div></li>
                   <li><div class="ball">03</div></li>
                   <li><div class="ball">04</div></li>
                   <li><div class="ball">05</div></li>
                   <li><div class="ball">06</div></li>
                   <li><div class="ball">07</div></li>
                   <li><div class="ball">08</div></li>
                   <li><div class="ball">09</div></li>
                   <li><div class="ball">10</div></li>
                   <li><div class="ball">11</div></li>
                   <li><div class="ball">12</div></li>
                   <li><div class="ball">13</div></li>
                   <li><div class="ball">14</div></li>
                   <li><div class="ball">15</div></li>
                   <li><div class="ball">16</div></li>
               </ul>
           </div>
           <div id="fs_text" class="ball-account">0个红球，0个蓝球，共0注，共<span class="red">￥0.00</span>元</div>
           <div id="fs_msg" class="ball-account" style="color:red;display:none"></div>
           <div id="fs_btns" class="btns ac">
               <input id="btn-random1" type="button" value="机选1注" class="common-btn">
			   <input id="btn-random5" type="button" value="机选5注" class="common-btn">
			   <input id="btn-random10" type="button" value="机选10注" class="common-btn">				
               <input id="btn-clear" type="button" value="清空所选" class="common-btn">
			   <input id="btn-null" type="button" value="占位" class="common-btn" style="visibility:hidden">
               <input id="btn-append" type="button" value="投注" class="sub_btn">
               <input id="multi" name="order.multi" value="1" onpaste="return false;" autocomplete="off" maxlength="2" size="2" type="hidden">
               <!--<input id="lotteryNumber" name="order.lotteryNumber" value="" type="text">
               <input id="totalStake" name="order.totalStake" type="text">
               <input id="totalFee" name="order.totalFee" type="text">-->
           </div>
           <br><br>
       </div>

       <div id="dantuo_area" class="i-mc" style="display: none;">
           <div class="red-area">
               <div class="cp-tab">
                   <ul>
                       <li id="danma" class="curr curr-f"><span>胆码</span></li>
                       <li id="tuoma"><span>拖码</span></li>
                   </ul>
               </div>
               <div class="tit">
                   <div><span id="red_ga" class="red ga">胆码 </span><span id="gray" class="gray">至少选择1个号码，最多选择5个号码</span></div>
               </div>
               <ul id="dt-redball-list1" class="redball-list ball-list clear">
                   <li><div class="ball">01</div></li>
                   <li><div class="ball">02</div></li>
                   <li><div class="ball">03</div></li>
                   <li><div class="ball">04</div></li>
                   <li><div class="ball">05</div></li>
                   <li><div class="ball">06</div></li>
                   <li><div class="ball">07</div></li>
                   <li><div class="ball">08</div></li>
                   <li><div class="ball">09</div></li>
                   <li><div class="ball">10</div></li>
                   <li><div class="ball">11</div></li>
                   <li><div class="ball">12</div></li>
                   <li><div class="ball">13</div></li>
                   <li><div class="ball">14</div></li>
                   <li><div class="ball">15</div></li>
                   <li><div class="ball">16</div></li>
                   <li><div class="ball">17</div></li>
                   <li><div class="ball">18</div></li>
                   <li><div class="ball">19</div></li>
                   <li><div class="ball">20</div></li>
                   <li><div class="ball">21</div></li>
                   <li><div class="ball">22</div></li>
                   <li><div class="ball">23</div></li>
                   <li><div class="ball">24</div></li>
                   <li><div class="ball">25</div></li>
                   <li><div class="ball">26</div></li>
                   <li><div class="ball">27</div></li>
                   <li><div class="ball">28</div></li>
                   <li><div class="ball">29</div></li>
                   <li><div class="ball">30</div></li>
                   <li><div class="ball">31</div></li>
                   <li><div class="ball">32</div></li>
                   <li><div class="ball">33</div></li>
               </ul>
               <div class="tit">
                   <div class="red ga" id="jd_title" style="display:none">此号码已被胆码选中。</div>
               </div>
           </div>
           <div class="blue-area">
               <div class="tit clear">
                   <div class="fl"><span class="blue ga">篮球</span><span class="gray">至少选择1个号码</span></div>
                   <!--<div class="fr">机选<select><option>1</option></select>个</div>-->
               </div>
               <ul id="dt-blueball-list" class="blueball-list ball-list clear">
                   <li><div class="ball">01</div></li>
                   <li><div class="ball">02</div></li>
                   <li><div class="ball">03</div></li>
                   <li><div class="ball">04</div></li>
                   <li><div class="ball">05</div></li>
                   <li><div class="ball">06</div></li>
                   <li><div class="ball">07</div></li>
                   <li><div class="ball">08</div></li>
                   <li><div class="ball">09</div></li>
                   <li><div class="ball">10</div></li>
                   <li><div class="ball">11</div></li>
                   <li><div class="ball">12</div></li>
                   <li><div class="ball">13</div></li>
                   <li><div class="ball">14</div></li>
                   <li><div class="ball">15</div></li>
                   <li><div class="ball">16</div></li>
               </ul>
           </div>
           <div id="dantuo_text" class="ball-account">0个红球(0个胆码,0个拖码)，0个蓝球，共0注，共<span class="red">￥0.00</span>元</div>
           <div id="dantuo_msg" class="ball-account" style="color:red;display:none"></div>
           <div class="btns ac">
               <input type="button" value="机选1注" class="disabled-btn">
               <input id="btn-clear-dt" type="button" value="清空所选" class="common-btn">
               <input id="btn-append-dt" type="button" value="投注" class="sub_btn">
           </div>
       </div>
   </div>
   <br>
   <br>
   </div>
<div id="noticePopShade" class="shade" style="display:none">
	<div class="ss-section  new-p-re">
		<div id="popId" class="ss-pop">
			<div id="footRibbon">
    			<p class="pop-tit">所选号码：</p>
                <div id="wrapper5" style="display:none">
                        <ul id="randomInfo5" class="ss-lst">
						</ul>
                </div>				
                <div id="wrapper" class="wrapper" style="display:none">
                	<div id="scroller" class="scroller">
                        <ul id="randomInfo10" class="ss-lst">
						</ul>
                	</div>
                </div>				
            </div>
            <p class="ss-p-txt new-mg-t5" id="cue_span">换换手气？点"重选"按钮重新机选5注</p>
            <p class="ss-p-txt" id="random_text"></p>
            <div class="new-tbl-type">
                <span class="new-tbl-cell"><a href="" id="rd_btn_cancel" class="btn-type">取消</a></span>
                <span class="new-tbl-cell"><a href="" id="rd_btn_reRandom5" class="btn-type">重选</a>
                <a href="" id="rd_btn_reRandom10" class="btn-type">重选</a></span>
                <span class="new-tbl-cell"><a href="<%=basePath %>lottery/doubleChromosphereOrder.action" id="rd_btn_append" class="btn-type4">投注</a></span>
            </div>			
        </div>
    </div>
</div>
   <script type="text/javascript">
   //默认值
   createNullLotteryObj();
   createLotteryObj(1);

   $("#randomnum-red").val(0);
   $("#randomnum-blue").val(0);

	window.sessionStorage.setItem("period",${crep.currperiod});
	window.sessionStorage.setItem("currendtimessq",'${crep.currendtime}');

   var ball = null;
   var canSell = 'true';
   var time = '${time}';//本期投注剩余时间(毫秒)
   
   var lasttiltLR = 0;
   var accelerCount = 0;  
    var myScroll;
    function loaded() {
    	myScroll = new iScroll('wrapper', { scrollbarClass: 'myScrollbar',fixedScrollbar:true,checkDOMChanges: true });
    }
    
    /**
     * 实时计算近期开奖弹出层的居中位置
     */
    function noticePopCenter() {
    	$('.ss-pop').css({
    		left : ($(window).width() - $('.ss-pop').outerWidth()) / 2,
    		top : ($(window).height() - $('.ss-pop').outerHeight()) / 2 + $(document).scrollTop()
    	});
    	$("#noticePopShade").height($(document).height());
    }
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
				 document.getElementById("btn-random1").click();	
				 accelerCount = 0;
				 lasttiltLR = 0;
			}			
		}
   }
   
   showShake = false;
   $(document).ready(function() {
       //双色球
       ssq_init();
	   
	  if (window.DeviceMotionEvent) {        
		  if(checkBrowser())
		  {
    	  	$("#gray_span").show();
			showShake = true;
			 window.addEventListener('devicemotion', deviceMotionHandler, false);
		  }
		  else
		  {
		 	 $("#gray_span").hide();
			  showShake = false;
		  }
  	  }
	  else
	  {
	  	 $("#gray_span").hide();
		  showShake = false;
	  }
	  
	  //机选五注
	  var ball = null;	  
      $("#btn-random5").bind("click", function() {
    	 	 if(caltotalstake()*2 >= maxAmount || caltotalstake()*2 + 10 >= maxAmount){
    			alertTip('投注金额已经达到'+maxAmount+'元,无法继续选号');
    			return false;
    		 }
			 $("#wrapper5").show();
			 $("#wrapper").hide();
			 
			 $("#popId").height(350);
    	     $("#footRibbon").height(240);
    		 noticePopCenter();
    		 $("#noticePopShade").toggle();
    		 if (ball == null) {
    			 ball = new LotterySSQ();
    		 }
    		 ball.get_fiveOrTenFs(5);
      });

       if(navigator.userAgent.toLowerCase().indexOf("windows phone")>0){
           $("#btn-random10").unbind("click").attr("disabled","").css("background","#ccc");
       }else{
           $("#btn-random10").click(function(){
               if(caltotalstake()*2 >= maxAmount || caltotalstake()*2 + 20 >= maxAmount){
                   alertTip('投注金额已经达到'+maxAmount+'元,无法继续选号');
                   return false;
               }
               $("#wrapper5").hide();
               $("#wrapper").show();

               $("#popId").height(350);
               $("#footRibbon").height(240);
               var platform = navigator.platform;
               if (platform == "iPod" || platform == "iPhone" || platform == "iPad") {
                   $("#wrapper").height(210);
               } else {
                   $("#wrapper").height(200);
               }
               noticePopCenter();
               if (ball == null) {
                   ball = new LotterySSQ();
               }
               ball.get_fiveOrTenFs(10);
               $("#noticePopShade").toggle();

               if(navigator.userAgent.match(/IEMobile/i)){
                   $("#wrapper").css("overflow-y","scroll");
               }else{
                   loaded();
               }
           });
       }

      $("#rd_btn_cancel").bind("click", function() {
    	    ssqLotteryObj.lottery = [];			
    	    $("#noticePopShade").toggle();
      });

      $("#rd_btn_reRandom5").bind("click", function(e) {
	        e.preventDefault();
			$("#popId").height(350);
    	    $("#footRibbon").height(240);
    	    ssqLotteryObj.lottery = [];
		    ball.get_fiveOrTenFs(5);
			
      });	

      $("#rd_btn_reRandom10").bind("click", function(e) {
	        e.preventDefault();
			$("#popId").height(350);
    	    $("#footRibbon").height(240);
    	    ssqLotteryObj.lottery = [];
		    ball.get_fiveOrTenFs(10);
      });
	  
      $("#rd_btn_append").bind("click", function() {
	        var item;
	        for ( var i = 0; i < ssqLotteryObj.lottery.length; i++) {
		       item = ssqLotteryObj.lottery[i];
			   item["num"] = ssqLotteryObj.count;
			   addLotteryItem(item);
			}
			ssqLotteryObj.lottery = [];
			btu();
      });
	  // 屏幕旋转事件监听，使得机选多注弹出框居中显示
	  //window.addEventListener("onorientationchange" in window ? "orientationchange":"resize", noticePopCenter, false);
	  window.addEventListener("resize", noticePopCenter, false);
   });
   function ssq_init() {
       canSellCheck(canSell, '');
       ball = new LotterySSQ();
       ball.fs();
       ball.dantuo();
       var lotteryNumber = "";
       if (lotteryNumber != "") {
           ball.set_data();
       }

       if (!canSell) {
           $(".sub_btn").attr("disabled", "disabled");
           $(".sub_btn").attr("class", "sub_btn no-btn");
           $("#remain-time").text("临时停售");
           return false;
       } else {
           $(".sub_btn").attr("class", "sub_btn");
       }

       var nextName = '2014080';
       var nextStart = "2014-07-13 21:00:00";//下期开售时间
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
       $("#randomnum-red").val(0);
       $("#randomnum-blue").val(0);
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

</body></html>