<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html >
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>天下名品彩票-购买快乐8</title>
	<meta charset="utf-8">
	<meta name="keywords" content="天下名品彩票,天下名品彩票触屏版">
	<meta name="description" content="天下名品彩票,天下名品彩票触屏版">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
	<meta name="copyright" content="Copyright @xingfugou 版权所有">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="format-detection" content="telephone=no">
	<meta http-equiv="cleartype" content="on"> 
	<link rel="stylesheet" type="text/css" href="<%=basePath %>inc/outapi/lottery/css/base.css" charset="utf-8">
	<link rel="stylesheet" type="text/css" href="<%=basePath %>inc/outapi/lottery/css/base2013.css" charset="utf-8">
	<link rel="stylesheet" href="<%=basePath %>inc/outapi/lottery/css/public.css">
	<link rel="stylesheet" href="<%=basePath %>inc/outapi/lottery/css/mobile.css">
	<script type="text/javascript">document.domain = "localhost";</script>
	<script src="<%=basePath %>inc/outapi/lottery/js/libs.js"></script>
	<style>
			.gmu-media-detect{-webkit-transition: width 0.001ms; width: 0; position: absolute; top: -10000px;}
			@media screen and (width: 1440px) { #gmu-media-detect0 { width: 1px; } }
	</style>
	<script src="<%=basePath %>inc/outapi/lottery/js/cart.js"></script>
	<script src="<%=basePath %>inc/outapi/lottery/js/md5.js"></script>
</head>
	<body>
		<div class="head">
			<h2 id="navtit">购买快乐8</h2>
				<a href="<%=basePath %>lottery/lotteryInfo.action?gameid=1" class="btn-qgkj" id="sourceUrl">
					<span><em></em></span>继续选号
				</a>
		</div>
	<form action="<%=basePath %>lottery/klbSubmitOrder.action" method="post">
		<input type="hidden" 	name ="ballList" 						id="ballList">
		<input type="hidden" 	name ="period" 							id="period"> 
	 	<input type="hidden" 	name="requesttype" 			value="4"  	id="requesttype">
	  	<input type="hidden"	name="gameid" 				value="1"  	id="gameid">
	  	<input type="hidden"	name="currendtime" 			 		  	id="currendtime">
	  	<input type="hidden"	name="boards" 			 		  		id="boards">
	  	
		
		<div class="w320">
		  <div class="area1">
		    <div class="xq-tit" issue="2014084" issale="1" endtime="1405943820">
		      <h3>第<cite id="actQH"><script>document.write(window.sessionStorage.getItem('klbperiod'));</script></cite>期选号内容</h3>
		      <em class="red" id="countdowm">截止时间：07-21(今日)19:57</em>
		    </div>
		    <div class="buy-box">
		     	 <ul id="mycart"></ul>
		      	 <p id="tools">
	      	 		<a href="javascript:;" class="btn2" act="delall">全部删除</a>
		      	 </p>
		    </div>
		  </div>
		  <div class="line-3"></div>
		  <div class="area1">
		    <div class="infolist">
		      <table width="100%">
			        <tbody>
				        <tr>
				          <th>购买倍数</th>
				          <td><input type="tel" id="ownMul" class="ipt-6" max="500" name="betmultir" maxlength="5" value="1">倍</td>
				        </tr>
				        <tr>
				          <th>购买注数</th>
				          <td><strong class="red" id="ownCount"></strong> 注</td>
				        </tr>
				        <tr>
				          <th>投注金额</th>
				          <td><strong class="red" id="ownMoney"></strong> 元</td>
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
		       <br></br>
		    <button class="btn btn100" id="pay_buy">立即付款</button>
		    <div class="pact">
		      <input type="checkbox" class="check-1" disabled="">我已阅读并同意<a href="" class="blue">《用户委托投注协议》</a>
		    </div>
		  </div>
		</div>
		</form>
		
		
		<footer>
			<div class="new-footer">
		    	<div class="new-f-login">
					<a href="">登录</a><span class="new-bar2">|</span><a href="">注册</a>
					<span class="new-back-top"><a href="">反馈</a><span class="new-bar2">|</span><a href="">回到顶部</a></span>
		        </div>
		    	<div class="new-f-section"><a href="">标准版</a><a href="javascript:void(0)" class="on">触屏版</a><a href="">电脑版</a></div>
		        <div class="new-f-section2">Copyright © 2012-2014 天下名品 xngfugou.com 版权所有</div>
		    </div>
		</footer>
		
		<script src="<%=basePath %>inc/outapi/lottery/js/saved_resource"></script>
		<script>
			 monitor.setProject('360_cp_m').getTrack().getClickAndKeydown();
			 $("#period").val(window.sessionStorage.getItem('klbperiod'));
			 $("#currendtime").val(window.sessionStorage.getItem('currendtimeklb'));
		</script>
		
	</body>
</html>