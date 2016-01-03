<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html >
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK">
		<title>天下名品彩票-购买新时时彩</title>
		<meta charset="GBK">
		<meta name="keywords" content="天下名品彩票,天下名品彩票触屏版">
		<meta name="description" content="天下名品彩票,天下名品彩票触屏版">
		<meta name="author" content="cp.360.cn">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
		<meta name="copyright" content="Copyright @tianxiamingpin.com 版权所有">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<meta name="format-detection" content="telephone=no">
		<meta http-equiv="cleartype" content="on"> 
		<link rel="stylesheet" type="text/css" href="<%=basePath %>inc/outapi/lottery/css/base.css" charset="utf-8">
		<link rel="stylesheet" type="text/css" href="<%=basePath %>inc/outapi/lottery/css/base2013.css" charset="utf-8">
		<link rel="stylesheet" href="<%=basePath %>inc/outapi/lottery/css/public.css">
		<link rel="stylesheet" href="<%=basePath %>inc/outapi/lottery/css/mobile.css">
		<script src="<%=basePath %>inc/outapi/lottery/js/libs.js"></script>
		<script src="<%=basePath %>inc/outapi/lottery/js/pks.js"></script>
		<style>
				.gmu-media-detect{-webkit-transition: width 0.001ms; width: 0; position: absolute; top: -10000px;}
				@media screen and (width: 1440px) { #gmu-media-detect0 { width: 1px; } }
		</style>
		<script type="text/javascript" src="<%=basePath %>inc/outapi/lottery/js/md5.js"></script>
	</head>
<body>
	<div class="wrap">
	  <div class="head"><h2 id="navtit">购买新PK拾</h2><a href="<%=basePath%>lottery/lotteryInfo.action?gameid=2" class="btn-qgkj" id="sourceUrl"><span><em></em></span>继续选号</a></div>
	  <div class="w320">
	    <div class="area1">
	      <div class="xq-tit" pt="F2" endtime="1406777534" prevtime="68" issale="1">
	        <h2>第<cite id="actQH"><script type="text/javascript">document.write(window.sessionStorage.getItem('pksperiod'))</script></cite>期选号内容</h2>
	        <em class="red" id="countdowm">8分12秒</em><em class="red" id="friendtxt">剩余时间:</em>
	      </div>
	      <div class="buy-box">
	        <ul id="mycart"></ul>
	        <p id="tools"><a href="javascript:;" class="btn2" act="delall" method="0">全部删除</a></p>
	      </div>
	    </div>
	    <form action="<%=basePath %>lottery/pksSubmitOrder.action" method="post">
	    <input type="hidden" 	name ="ballList" 						id="ballList">
		<input type="hidden" 	name ="period" 							id="period"> 
	 	<input type="hidden" 	name="requesttype" 			value="4"  	id="requesttype">
	  	<input type="hidden"	name="gameid" 				value="2"  	id="gameid">
	  	<input type="hidden"	name="currendtime" 			 		  	id="currendtime">
	  	<input type="hidden"	name="boards" 			 		  		id="boards">
	    <div class="line-3"></div>
	    <div class="area1">
	      <div class="infolist">
	           <table width="100%">
		          <tbody>
		          <tr>
		            <th>购买倍数:</th>
		            <td><input name="betmultir" id="ownMul" type="tel" max="99999" maxlength="5" class="ipt-6" value="1">&nbsp;&nbsp;倍</td>
		          </tr>
		          <tr>
		            <th>购买注数:</th>
		            <td><strong class="red" id="ownCount">6</strong>注</td>
		          </tr>
		          <tr>
		            <th>投注金额:</th>
		            <td><strong class="red" id="ownMoney">12</strong>元</td>
		          </tr>
		        </tbody>
	        </table>
	      </div>
	        <h3>投注者身份信息:</h3>
		       <div class="infolist">
		              <p>真实姓名：
	              			<input name="fullName" id="fullName" value="${lotteryAccount.fullName }" type="text" class="common-input userinfo-input" placeholder="兑奖及领奖的重要依据，请正确填写！">
			              	<span id="e_fullName_1"></span>
		              </p>
		              <p><span id="e_fullName_2" style="color:red"></span></p>
		              <p>身份证号：
	              			<input type="text" name="certificatenum" value="${lotteryAccount.certificatenum }" id="idCardNumber" class="common-input userinfo-input" placeholder="兑奖及领奖的重要依据，请正确填写！">
	              			<span id="e_idCardNumber_1"></span>
		              </p>
		              <p><span id="e_idCardNumber_2" style="color:red"></span></p>
		              <p>手机号码：
			              	<input name="phonenum" id="mobile" type="text" value="${lotteryAccount.phonenum }" class="common-input userinfo-input" placeholder="中奖后短信通知！">
			              	<span id="e_mobile_1"></span>
		              </p>
		              <p><span id="e_mobile_2" style="color:red"></span></p>
		              <input  type ="hidden" name ="certificatetype" value="1"/>
		       </div>
	      
	      	  <button class="btn btn100" id="pay_buy">立即付款</button>
		      <div class="pact">
		        	<input type="checkbox" class="check-1" name="pettreaty" disabled="">我已阅读并同意<a href="http://m.cp.360.cn/guide/bettreaty" class="blue">《用户委托投注协议》</a>
		      </div>
	    </div>
	   </form>
	  </div>
		<div class="foot">
	 	 <span><cite class="touch font14">触屏版</cite> | <a target="_blank" href="" class="font14">电脑版</a> | <a target="_blank" href="" class="font14">网址大全</a></span><p>京ICP证080047号 <a href="">帮助中心</a></p>
	  	 <p>Copyright &#169; 2005-2014 tianxiamingpin.com版权所有</p>
		</div>
	</div>
		<script src="<%=basePath %>inc/outapi/lottery/js/saved_resource"></script>
		<script>
			monitor.setProject('360_cp_m').getTrack().getClickAndKeydown();
	 		$("#period").val(window.sessionStorage.getItem('pksperiod'));
	 		$("#currendtime").val(window.sessionStorage.getItem('currendtimepks'));
		</script>
  </body>
</html>