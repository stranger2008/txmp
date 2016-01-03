<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>确认信息 </title>
		<meta name="author" content="m.jd.com">
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
        <script type="text/javascript" src="<%=basePath %>inc/outapi/lottery/js/spin.min.js"></script><style></style>
		<script type="text/javascript">
			window.onunload = function() {};		
        </script>
	</head>	
	
<body id="body" class="plist">
   <script type="text/javascript" src="<%=basePath %>inc/outapi/lottery/js/ball.js" charset="gbk"></script>
   <script type="text/javascript" src="<%=basePath %>inc/outapi/lottery/js/lottery.check.js" charset="gbk"></script>
   <link rel="stylesheet" type="text/css" href="<%=basePath %>inc/outapi/lottery/css/jczq_order.css" charset="utf-8">

   <form name="baseForm" id="baseForm" action="<%=basePath%>lottery/payOrder.action" method="post">
	   <input type="hidden" name="lotteryCategory" 					value="1"  id="lotteryCategory">
	   <input type="hidden" name="redandblue" 						value=""   id="lotteryNumberList">
	   <input type="hidden" name="betmultir" 						value=""   id="multi">
	   <input type="hidden" name="lotteryOrder.append" 				value=""   id="append">
	   <input type="hidden" name="boards" 							value=""   id="totalStake">
	   <input type="hidden" name="totalamount" 						value=""   id="lotteryTotalFee">
	   <input type="hidden" name="lotteryOrder.stopflag" 			value=""   id="stopflag">
	   <input type="hidden" name="gameid" 							value="3"  id="gameid">   
	   <input type="hidden" name="bettype" 							value="1"  id="bettype">
	   <input type="hidden" name="redmethod" 						value="1"  id="redmethod">
	   <input type="hidden" name="bluemethod" 						value="1"  id="bluemethod">
	   <input type="hidden" name="requesttype" 						value="4"  id="requesttype">
	   <input type="hidden" name="currendtime" 						 		   id="currendtime">
	   
	   
	  <div class="info">
		      <div class="mc">
		              <h3>确认投注信息:</h3>
		       <div class="radius">
		           <ul id="confirmcardInfo1">
		           		<li>
		           				<div style="padding-right:50px">
		           					<div class="red"> 
		           						<span class="blue"></span>
		           					</div>
		           				 </div>
		           		 </li>
		           	</ul>
		           <ul id="confirmcardInfo2" style="display: none;"></ul>
		       </div>
		       <div class="radius">
		           <p>注数：<span id="s_totalstake"></span>注</p>
		           <p id="p_multiple">倍数：倍</p>
				   <p id="p_append"></p>
		           <p>投注金额：￥<strong class="red1" id="money"></strong>元 </p>
		       </div>
			   <h3>投注者身份信息:</h3>
		       <div class="radius">
		              　<input name="lotteryOrder.firstType" id="firstType" value="0" type="hidden">
		              <p>真实姓名：
				              	<input name="fullName" id="fullName" value="${lotteryAccount.fullName }" type="text" class="common-input userinfo-input" placeholder="兑奖及领奖的重要依据，请正确填写！">
				              	<span id="e_fullName_1"></span>
		              </p>
		              <p><span id="e_fullName_2" style="color:red"></span></p>
		              <p>身份证号：
		              			<input type="text" name="certificatenum" value="${lotteryAccount.certificatenum }" id="idCardNumber" class="common-input userinfo-input">
		              			<span id="e_idCardNumber_1"></span>
		              </p>
		              <p><span id="e_idCardNumber_2" style="color:red"></span></p>
		              <p>手机号码：
				              	<input name="phonenum" id="mobile" type="text" value="${lotteryAccount.phonenum }" class="common-input userinfo-input" placeholder="中奖后短信通知！">
				              	<span id="e_mobile_1"></span>
		              </p>
		              <p><span id="e_mobile_2" style="color:red"></span></p>
		              <input  type ="hidden" name ="certificatetype" value="1"/>
		              <input  type ="hidden" name ="period" id="period"/>
		              
		       </div>
		       <h3>支付方式:</h3>
		       <div class="radius">
				   <p><input name="lotteryOrder.payType" value="2" id="payType_2" type="hidden" >在线支付</p>
		       </div>
		       <div class="btns" id="btns" style="text-align:center;">
		       <input type="button" value="确认付款" id="sub_btn" class="sub_btn">
		       </div>
	   		</div>
		   <br>
		   <br>
	   </div>
   </form>
<script type="text/javascript" src="<%=basePath %>inc/outapi/lottery/js/lottery.coninfo.js" charset="gbk"></script>
<script type="text/javascript">
	$("#period").val(window.sessionStorage.getItem('period'));
	$("#currendtime").val(window.sessionStorage.getItem('currendtimessq'));
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
</html>