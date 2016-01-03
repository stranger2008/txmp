<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html >
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>天下名品彩票-PK拾</title>
		<meta charset="utf-8">
		<meta name="keywords" content="天下名品彩票,天下名品彩票触屏版">
		<meta name="description" content="天下名品彩票,天下名品彩票触屏版">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
		<meta name="copyright" content="Copyright @tianxiamingpin.com 版权所有">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<meta name="format-detection" content="telephone=no">
		<meta http-equiv="cleartype" content="on"> 
		<link rel="stylesheet" href="<%=basePath %>inc/outapi/lottery/css/public.css">
		<link rel="stylesheet" href="<%=basePath %>inc/outapi/lottery/css/mobile.css">
		<script src="<%=basePath %>inc/outapi/lottery/js/libs.js"></script>
		<style>
			.gmu-media-detect{-webkit-transition: width 0.001ms; width: 0; position: absolute; top: -10000px;}
			@media screen and (width: 1440px) { #gmu-media-detect0 { width: 1px; } }
		</style>
		<script src="<%=basePath %>inc/outapi/lottery/js/xsscfirst.js"></script>
		<script type="text/javascript" src="<%=basePath %>inc/outapi/lottery/js/md5.js"></script>
		<script type="text/javascript">
			window.sessionStorage.setItem('pksperiod','${crep.currperiod}');
			window.sessionStorage.setItem('currendtimepks','${crep.currendtime}');
		</script>
	</head>
<body>
	<div class="wrap">
	  <div class="head">
	  	<h2 id="navtit">PK拾-
	  		<cite id="game"></cite>
	  		<span class="head-arr">
	  			<em></em>
	  		</span>
	  	</h2>
	  	<a href="<%=basePath %>lottery/index.action" class="btn-qgkj" id="sourceUrl"><span><em></em></span>购彩大厅</a>
	  	<a href="javascript:;" class="btn-menu">≡</a>
	  </div>
	  <div class="top-date">
	  </div>
	  <div class="w320">
	  	<div class="area1">
	      <div class="xq-tit">
	        <h2>第<cite id="actQH">${crep.currperiod}</cite>期</h2>
	        <em class="red" id="countdowm">截止时间：07-21(今日)19:57</em>
	      </div>
	    </div>
	      <div class="line-3"></div>
	    <div class="y11-txt" id="detxt">猜冠军：猜开奖号码的第一位，奖金10元。</div>
	    <div class="pick pick-11 pick-ssc">
	      <div class="myhelp none" id="myparam" style="margin-top:-6px;">
	      		<table width="100%">
	      			<thead>
	      				<tr>
	      					<th colspan="3">
	      						<div class="helphanle" sort="param">
	      							<span class="arr arr-on"><em></em></span>
	      							<strong>参数说明</strong>
	      						 </div>
	      					</th>
	      				</tr>
	      			</thead>
		      		<tbody class="none" id="box_param">
		      			<tr><td colspan="3" class="param" id="des"></td></tr>
		      		</tbody>
	      		</table>
	      </div>
	      <div class="ssqshow-tit none" style="margin:4px 0 8px 0;" id="chartscd"><span class="more red"><cite class="friends">距<em id="fq2">05</em>期截止:</cite> <cite class="countdown">07:27</cite></span><h2 id="chartsH2"></h2></div>
	      <!--选号投注区 begin-->
	      <div class="pick-box pick-red" id="star_d">
	        <dl class="" id="dl1">
        		<dt>
        			<span>第一名<em></em></span>
         		</dt>
	          <dd>
	            <ul>
	              <li class=""><span>01</span></li>
	              <li><span>02</span></li>
	              <li><span>03</span></li>
	              <li><span>04</span></li>
	              <li><span>05</span></li>
	              <li><span>06</span></li>
	              <li><span>07</span></li>
	              <li><span>08</span></li>
	              <li><span>09</span></li>
	              <li><span>10</span></li>
	            </ul>
	          </dd>
	        </dl>
	        <div class="line-3" id="line2" style="display:None"></div>
	        <dl class="" id="dl2" hidden>
	        	<dt>
        			<span>第二名<em></em></span>
         		</dt>
	          <dd>
	            <ul>
	              <li class=""><span>01</span></li>
	              <li><span>02</span></li>
	              <li><span>03</span></li>
	              <li><span>04</span></li>
	              <li><span>05</span></li>
	              <li><span>06</span></li>
	              <li><span>07</span></li>
	              <li><span>08</span></li>
	              <li><span>09</span></li>
	              <li><span>10</span></li>
	            </ul>
	          </dd>
	        </dl>
	        <div class="line-3" id="line3" style="display:None"></div>
	         <dl class="" id="dl3" hidden>
      			<dt>
     				<span>第三名<em></em></span>
      			</dt>
	          <dd >
	            <ul>
	              <li class=""><span>01</span></li>
	              <li><span>02</span></li>
	              <li><span>03</span></li>
	              <li><span>04</span></li>
	              <li><span>05</span></li>
	              <li><span>06</span></li>
	              <li><span>07</span></li>
	              <li><span>08</span></li>
	              <li><span>09</span></li>
	              <li><span>10</span></li>
	            </ul>
	          </dd>
	        </dl>
	        <div class="line-3" id="line4" style="display:None"></div>
	        <dl class="" id="dl4" hidden>
	        	<dt>
     				<span>第四名<em></em></span>
      			</dt>
	          <dd>
	            <ul>
	              <li class=""><span>01</span></li>
	              <li><span>02</span></li>
	              <li><span>03</span></li>
	              <li><span>04</span></li>
	              <li><span>05</span></li>
	              <li><span>06</span></li>
	              <li><span>07</span></li>
	              <li><span>08</span></li>
	              <li><span>09</span></li>
	              <li><span>10</span></li>
	            </ul>
	          </dd>
	        </dl>
	        <div class="line-3" id="line5" style="display:None"></div>
	        <dl class="" id="dl5" hidden>
	        	<dt>
     				<span>第五名<em></em></span>
      			</dt>
	          <dd>
	            <ul>
	              <li class=""><span>01</span></li>
	              <li><span>02</span></li>
	              <li><span>03</span></li>
	              <li><span>04</span></li>
	              <li><span>05</span></li>
	              <li><span>06</span></li>
	              <li><span>07</span></li>
	              <li><span>08</span></li>
	              <li><span>09</span></li>
	              <li><span>10</span></li>
	            </ul>
	          </dd>
	        </dl>
	        <div class="line-3" id="line6" style="display:None"></div>
	        <dl class="" id="dl6" hidden>
	        	<dt>
     				<span>第六名<em></em></span>
      			</dt>
	          <dd>
	            <ul>
	              <li class=""><span>01</span></li>
	              <li><span>02</span></li>
	              <li><span>03</span></li>
	              <li><span>04</span></li>
	              <li><span>05</span></li>
	              <li><span>06</span></li>
	              <li><span>07</span></li>
	              <li><span>08</span></li>
	              <li><span>09</span></li>
	              <li><span>10</span></li>
	            </ul>
	          </dd>
	        </dl>
	        <div class="line-3" id="line7" style="display:None"></div>
	        <dl class="" id="dl7" hidden>
	        	<dt>
     				<span>第七名<em></em></span>
      			</dt>
	          <dd>
	            <ul>
	              <li class=""><span>01</span></li>
	              <li><span>02</span></li>
	              <li><span>03</span></li>
	              <li><span>04</span></li>
	              <li><span>05</span></li>
	              <li><span>06</span></li>
	              <li><span>07</span></li>
	              <li><span>08</span></li>
	              <li><span>09</span></li>
	              <li><span>10</span></li>
	            </ul>
	          </dd>
	        </dl>
	        <div class="line-3" id="line8" style="display:None"></div>
	        <dl class="" id="dl8" hidden>
	        	<dt>
     				<span>第八名<em></em></span>
      			</dt>
	          <dd>
	            <ul>
	              <li class=""><span>01</span></li>
	              <li><span>02</span></li>
	              <li><span>03</span></li>
	              <li><span>04</span></li>
	              <li><span>05</span></li>
	              <li><span>06</span></li>
	              <li><span>07</span></li>
	              <li><span>08</span></li>
	              <li><span>09</span></li>
	              <li><span>10</span></li>
	            </ul>
	          </dd>
	        </dl>
	        <div class="line-3" id="line9" style="display:None"></div>
	        <dl class="" id="dl9" hidden>
	       		 <dt>
     				<span>第九名<em></em></span>
      			</dt>
	          <dd>
	            <ul>
	              <li class=""><span>01</span></li>
	              <li><span>02</span></li>
	              <li><span>03</span></li>
	              <li><span>04</span></li>
	              <li><span>05</span></li>
	              <li><span>06</span></li>
	              <li><span>07</span></li>
	              <li><span>08</span></li>
	              <li><span>09</span></li>
	              <li><span>10</span></li>
	            </ul>
	          </dd>
	        </dl>
	        <div class="line-3" id="line10" style="display:None"></div>
	        <dl class="" id="dl10" hidden>
	        	<dt>
     				<span>第十名<em></em></span>
      			</dt>
	          <dd>
	            <ul>
	              <li class=""><span>01</span></li>
	              <li><span>02</span></li>
	              <li><span>03</span></li>
	              <li><span>04</span></li>
	              <li><span>05</span></li>
	              <li><span>06</span></li>
	              <li><span>07</span></li>
	              <li><span>08</span></li>
	              <li><span>09</span></li>
	              <li><span>10</span></li>
	            </ul>
	          </dd>
	        </dl>
	      </div>
	     
	      <!--选号投注区 end-->
	      <div class="ht10"></div>
	    </div>
	    <!--中奖小助手 begin-->
	    <div class="myhelp" id="myhelp">
	      <table width="100%">
	      
	        <tbody class="none" id="box_help"><tr><td colspan="3" class="font14">loading...</td></tr></tbody>  
	      </table>
	      <div class="myhelpr none"><strong><a href="" class="blue">查看购彩记录</a></strong></div>
	    </div>
	    <!--中奖小助手 end-->
	  </div>
	  <div class="pick-b">
	    <div class="bet-top">
	      <p>共<strong class="red" id="count">0</strong>注,<strong class="red" id="price">0</strong>元 <cite id="calcu"></cite></p>
	    </div>
	    <div class="betting">
	      <button class="btn-addnmu" type="button" id="addcart"><span></span>添加到号码篮<em id="group">0</em></button>
	      <button class="btn-betting" type="button" id="bets">直接投注</button>
	    </div>
	  </div>
	  <div class="pop-box2 spill">
	    <div class="pop-box2-arr"></div>
	    <div class="filt-popc" id="tabs">
	      <div class="filt-popc-cont" style="-webkit-transform: translate3d(0px, 0px, 0px);">
	      	 <div class="filt-popc-entry" style="-webkit-transform: translate3d(0px, 0px, 0px); -webkit-backface-visibility: hidden; width: 858px;">
	         	<ul style="-webkit-transform: translate3d(0px, 0px, 0px); width: 286px;" action="normal" id="container">
	            	<li><a href="" class="btn-pop btn-pop-on" play="5D">猜冠军</a></li>
	                <li><a href="" class="btn-pop" play="5T">猜冠亚军</a></li>
	                <li><a href="" class="btn-pop" play="4D">猜前三名 </a></li>
	                <li><a href="" class="btn-pop" play="3D">猜前四名</a></li>
	                <li><a href="" class="btn-pop" play="F3">猜前五名 </a></li>
	                <li><a href="" class="btn-pop" play="F6">猜前六名</a></li>
	                <li><a href="" class="btn-pop" play="2D">猜前七名 </a></li>
	                <li><a href="" class="btn-pop" play="F2">猜前八名</a></li>
	                <li><a href="" class="btn-pop" play="1D">猜前九名</a></li>
	                <li><a href="" class="btn-pop" play="R1">猜前十名</a></li>
	            </ul>
	         </div>
	      </div>
	    </div>
	  </div>
	</div>
	<script src="<%=basePath %>inc/outapi/lottery/js/saved_resource"></script>
	<script>monitor.setProject('360_cp_m').getTrack().getClickAndKeydown();</script>
	</body>
</html>

