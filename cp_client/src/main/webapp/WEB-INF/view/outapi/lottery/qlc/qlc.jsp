<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html >
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK">
		<title>天下名品-七乐彩</title>
		<meta charset="GBK">
		<meta name="keywords" content="天下名品彩票,天下名品彩票触屏版">
		<meta name="description" content="天下名品彩票,天下名品彩票触屏版">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
		<meta name="copyright" content="Copyright @xingfugou.com 版权所有">
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
		<script src="<%=basePath %>inc/outapi/lottery/js/qlcfirst.js"></script>
		<script type="text/javascript" src="<%=basePath %>inc/outapi/lottery/js/md5.js"></script>
	</head>
<body>
	<div class="wrap">
	  <div class="head"><h2 id="navtit">七乐彩-<cite id="game">自选</cite>
	  		<span class="head-arr"><em></em></span></h2>
	  			<a href="<%=basePath %>/lottery/index.action" class="btn-qgkj" id="sourceUrl">
	  		<span>
	  			<em></em>
	  		</span>购彩大厅</a><a href="javascript:;" class="btn-menu">≡</a>
	  </div>
	  <div class="w320">
	    <div class="area1">
	      <div class="xq-tit">
	        <h2>第<cite id="actQH">${crep.currperiod}</cite>期</h2>
	        <em class="red" id="countdowm">截止时间：07-21(今日)19:57</em>
	      </div>
	    </div>
	    <div class="line-3"></div>
	    <div class="pick">
	      <div class="pick-tit">
	      	<span class="deal">
	      		<a href="javascript:;" class="btn2 del" dir="red">清 空</a>
	      	</span> 至少选择<strong class="red">7</strong>个号码，最高奖金500万
	      </div>
	      <div class="pick-box pick-red">
	        <ul id="r">
	          <li><span>01</span></li>
	          <li><span>02</span></li>
	          <li><span>03</span></li>
	          <li><span>04</span></li>
	          <li><span>05</span></li>
	          <li><span>06</span></li>
	          <li><span>07</span></li>
	          <li><span>08</span></li>
	          <li><span>09</span></li>
	          <li><span>10</span></li>
	          <li><span>11</span></li>
	          <li><span>12</span></li>
	          <li><span>13</span></li>
	          <li><span>14</span></li>
	          <li><span>15</span></li>
	          <li><span>16</span></li>
	          <li><span>17</span></li>
	          <li><span>18</span></li>
	          <li><span>19</span></li>
	          <li><span>20</span></li>
	          <li><span>21</span></li>
	          <li><span>22</span></li>
	          <li><span>23</span></li>
	          <li><span>24</span></li>
	          <li><span>25</span></li>
	          <li><span>26</span></li>
	          <li><span>27</span></li>
	          <li><span>28</span></li>
	          <li><span>29</span></li>
	          <li><span>30</span></li>
	        </ul>
	      </div>
	    </div>
	    <div class="pick pick-box2 none">
	      <div class="pick-tit2"><span><a href="javascript:;" class="btn2" id="reflash">换一组</a></span> 我要机选
	        <select class="sel-pick">
	        	<option value="1">1</option>
	        	<option value="2">2</option>
	        	<option value="3">3</option>
	        	<option value="5" selected="selected">5</option>
	        	<option value="10">10</option>
	        </select>
	        注 </div>
	      <ul id="myrnd"></ul>
	    </div>
	    <div class="pick none">
	      <div class="pick-tit"><strong class="red">胆码：</strong>至少选<strong class="red">1</strong>个，最多<strong class="red">6</strong>个</div>
	      <div class="pick-box pick-red">
	        <ul id="rd">
	          <li><span>01</span></li>
	          <li><span>02</span></li>
	          <li><span>03</span></li>
	          <li><span>04</span></li>
	          <li><span>05</span></li>
	          <li><span>06</span></li>
	          <li><span>07</span></li>
	          <li><span>08</span></li>
	          <li><span>09</span></li>
	          <li><span>10</span></li>
	          <li><span>11</span></li>
	          <li><span>12</span></li>
	          <li><span>13</span></li>
	          <li><span>14</span></li>
	          <li><span>15</span></li>
	          <li><span>16</span></li>
	          <li><span>17</span></li>
	          <li><span>18</span></li>
	          <li><span>19</span></li>
	          <li><span>20</span></li>
	          <li><span>21</span></li>
	          <li><span>22</span></li>
	          <li><span>23</span></li>
	          <li><span>24</span></li>
	          <li><span>25</span></li>
	          <li><span>26</span></li>
	          <li><span>27</span></li>
	          <li><span>28</span></li>
	          <li><span>29</span></li>
	          <li><span>30</span></li>
	        </ul>
	      </div>
	      <div class="line-3"></div>
	      <div class="pick-tit">
	      	<span class="deal">
	      		<a href="javascript:;" class="btn2 all">全 选</a>
	      	</span>
	      		<strong class="red">拖码：</strong>胆码加拖码至少<strong class="red">7</strong>个
	      </div>
	      <div class="pick-box pick-red">
	        <ul id="rt">
	          <li><span>01</span></li>
	          <li><span>02</span></li>
	          <li><span>03</span></li>
	          <li><span>04</span></li>
	          <li><span>05</span></li>
	          <li><span>06</span></li>
	          <li><span>07</span></li>
	          <li><span>08</span></li>
	          <li><span>09</span></li>
	          <li><span>10</span></li>
	          <li><span>11</span></li>
	          <li><span>12</span></li>
	          <li><span>13</span></li>
	          <li><span>14</span></li>
	          <li><span>15</span></li>
	          <li><span>16</span></li>
	          <li><span>17</span></li>
	          <li><span>18</span></li>
	          <li><span>19</span></li>
	          <li><span>20</span></li>
	          <li><span>21</span></li>
	          <li><span>22</span></li>
	          <li><span>23</span></li>
	          <li><span>24</span></li>
	          <li><span>25</span></li>
	          <li><span>26</span></li>
	          <li><span>27</span></li>
	          <li><span>28</span></li>
	          <li><span>29</span></li>
	          <li><span>30</span></li>
	        </ul>
	      </div>
	    </div>
	  </div>
	  <div class="pick-b">
	  	<div class="bet-top" id="pdeal">
	    	<p>您选择了<strong class="red">0</strong>个号码 共<strong class="red">0</strong>注,<strong class="red">0</strong>元</p>
	        <p class="none">您选择了<strong class="red">0</strong>注号码,<strong class="red">0</strong>元<em class="arr-4"><i></i></em></p>
	        <p class="none">您选择了<strong class="red">0</strong>个胆码,<strong class="red">0</strong>个拖码 共<strong class="red">0</strong>注,<strong class="red">0</strong>元<em class="arr-4"><i></i></em></p></div>
	      <div class="betting"><button class="btn-addnmu" type="button" id="addcart"><span></span>添加到号码篮<em id="group">0</em></button><button class="btn-betting" type="button" id="bets">直接投注</button></div></div>
	  <div class="pop-box2 none">
	    <div class="pop-box2-arr"></div>
	    <div class="filt-popc">
    		<ul>
    			<li><a href="javascript:;" class="btn-pop btn-pop-on plays" action="0" txt="自选">自选号码</a></li>
    			<li><a href="javascript:;" class="btn-pop plays" action="1" txt="机选">机选号码</a></li>
    		</ul>
	    </div>
	  </div>
	  <div class="h75"></div>
	</div>
		<script src="<%=basePath %>inc/outapi/lottery/js/saved_resource"></script>
		<script>
			window.sessionStorage.setItem('periodqlc' ,${crep.currperiod});
			window.sessionStorage.setItem("currendtimeqlc",'${crep.currendtime}');
		</script>
	</body>
</html>