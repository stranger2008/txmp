<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta charset="utf-8">	
	     <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
		<title>确认投注</title>
		<meta name="keywords" content="彩票">
		<meta name="description" content="彩票">
		<link rel="stylesheet" type="text/css" href="<%=basePath %>/inc/outapi/lottery/css/base2013.css" charset="utf-8">
		<script type="text/javascript" src="<%=basePath %>inc/outapi/lottery/js/jquery-1.6.2.min.js"></script>
	    <script type="text/javascript" src="<%=basePath %>inc/outapi/lottery/js/common.js"></script>
	    <script type="text/javascript" src="<%=basePath %>inc/outapi/lottery/js/spin.min.js"></script><style></style>
	</head>
	<body>
		<style type="text/css">
			/* Common */
			.new-header-append{font-size:16px;line-height:1.25em;min-width:320px;font-size:1em;font-family:'microsoft yahei',Verdana,Arial,Helvetica,sans-serif;color:#000;-webkit-text-size-adjust:none}
			.new-header-append,p,h1,h2,h3,h4,h5,h6,ul,ol,li,dl,dt,dd,table,th,td,form,fieldset,legend,input,textarea,button,select{margin:0;padding:0}
			.new-tbl-type{display:table;width:100%}
			.new-tbl-cell{display:table-cell}
		    /* header */
		    .new-jd-logo{position:relative;padding:0 10px}
		    .new-hlogo-btn{position:absolute;top:0;right:10px}
		    .new-m-myjd,.new-m-cart{display:inline-block;width:30px;height:39px}
		    .new-m-myjd span,.new-m-cart span{display:inline-block;width:26px;height:21px;margin-top:13px;background:url(<%=basePath %>/inc/outapi/lottery/images/icon2b.png) 4px 0 no-repeat;background-size:180px 180px;text-indent:-9999px}
		    .new-m-cart span{width:24px;height:21px;background-position:-24px 0}
		    .new-header{position:relative;z-index:8888;height:44px;background:#e4393c}
		    .new-header-v1{background:#edecec}
		    .new-header h2{height:44px;line-height:44px;font-weight:normal;font-size:16px;color:#fff;text-align:center}
		    .new-header-v1 h2{color:#000}
		    .new-a-home{position:absolute;top:6px;left:6px;width:56px;height:32px;background:url(<%=basePath %>/inc/outapi/lottery/images/icon.png) 0 0 no-repeat;font-size:14px;line-height:32px;color:#6e6e6e;text-indent:18px}
		    .new-a-out{position:absolute;top:6px;right:12px;width:32px;height:30px;background:url(<%=basePath %>/inc/outapi/lottery/images/icon.png) -23px -1474px;text-indent:-9999px}
		    .new-a-out .new-logo{display:inline-block;width:52px;height:29px;background:url(<%=basePath %>/inc/outapi/lottery/images/icon.png) 1px -546px no-repeat}
		    .new-a-back{position:absolute;top:6px;left:6px;width:30px;height:32px}
		    .new-a-back span{display:inline-block;width:10px;height:18px;margin-top:6px;background:url(<%=basePath %>/inc/outapi/lottery/images/icon2b.png) -60px 0 no-repeat;background-size:180px 180px;text-indent:-9999px}
		    .new-a-back2{background:url(<%=basePath %>/inc/outapi/lottery/images/icon.png) no-repeat scroll 3px -1431px;height: 32px;left: 6px;position: absolute;text-indent: -9999px;top: 6px;width: 30px}
		    .new-a-back-v1{background-position:3px -1431px}
		    .new-a-edit{position:absolute;top:6px;right:12px;width:37px;height:30px;background:url(<%=basePath %>/inc/outapi/lottery/images/icon.png) 7px -669px no-repeat;font-size:14px;line-height:32px;color:#6e6e6e;text-align:center;text-indent:-9999px}
		    .new-a-jd{position:absolute;top:6px;right:7px;width:37px;height:30px}
		    .new-a-jd span{display:inline-block;width:21px;height:21px;margin:5px 0 0 8px;background:url(<%=basePath %>/inc/outapi/lottery/images/icon2b.png) -125px -24px no-repeat;background-size:180px 180px;text-indent:-9999px}
		    .new-a-edit{background-position:16px -605px}
		    .new-header .new-srch-box{width:auto;margin:0 70px 0 12px;padding-right:90px;background-color:#fff}
		    .new-header .new-srch-box-v1{width:84%;margin-left:40px;padding-right:0}
		    .new-header .new-srch-box-v2{padding-right:30px}
		    .new-header .new-srch-box-v3{width:62%;margin-left:40px;padding-right:30px}
		    .new-header .new-srch-box-v1 .new-srch-input{margin-right:0}
		    .new-header .new-srch-lst{position:absolute;top:31px;left:-1px;z-index:100;width:100%}
		    .new-a-cancel{position:absolute;top:0;left:0;width:40px;height:44px;line-height:44px;font-size:14px;color:#fff;text-align:center}
		    .new-header .new-s-close{right:3px}
		    .new-header .new-s-close-v1{right:55px}
		    .new-a-search{position:absolute;top:6px;right:16px;width:37px;height:30px;line-height:30px;font-size:16px;font-weight:bold;color:#fff}
			/* tab */
		    .new-jd-tab{border-bottom:1px solid #d0cece;background-color:#fff}
		    .new-jd-tab .new-tbl-cell{width:25%;padding:9px 0;font-size:12px;color:#fff;text-align:center}
		    .new-jd-tab .new-tbl-cell span{vertical-align:text-top}
		    .new-jd-tab .new-tbl-cell .icon,.new-jd-tab .new-tbl-cell .icon2,.new-jd-tab .new-tbl-cell .icon3,.new-jd-tab .new-tbl-cell .icon4{display:inline-block;width:22px;height:22px;background:url(<%=basePath %>/inc/outapi/lottery/images/icon2b.png) -60px -25px no-repeat;background-size:180px 180px;text-indent:-9999px}
		    .new-jd-tab .new-tbl-cell .icon2{width:26px;background-position:0 -25px}
		    .new-jd-tab .new-tbl-cell .icon3{width:25px;background-position:-29px -25px}
		    .new-jd-tab .new-tbl-cell .icon4{width:22px;background-position:-85px -25px}
		    .new-jd-tab .new-tbl-cell .icon.on{background-position:-157px 0}
		    .new-jd-tab .new-tbl-cell .icon2.on{background-position:-154px -24px}
		    .new-jd-tab .new-tbl-cell .icon3.on{background-position:-154px -49px}
		    .new-jd-tab .new-tbl-cell .icon4.on{background-position:-155px -74px}
		    .new-jd-tab .new-tbl-cell .txt{display:block}
		</style>
		<div class="new-header new-header-append">
			<a href="<%=basePath %>/lottery/lotteryInfo.action?gameid=6" class="new-a-back"><span>返回</span></a>
					<h2>确认投注</h2>
			    <a href="javascript:void(0)" id="btnJdkey" onclick="_toggleJdKey()" class="new-a-jd"><span>名品键</span></a>
		</div>
		
		 <meta name="format-detection" content="telephone=no">
		<link rel="stylesheet" type="text/css" href="<%=basePath %>/inc/outapi/lottery/css/kuai3.css">
		<script src="<%=basePath %>inc/outapi/lottery/js/ball.js" charset="gbk"></script>
		<script src="<%=basePath %>inc/outapi/lottery/js/lottery.ssq.js" charset="gbk"></script>
		<script src="<%=basePath %>inc/outapi/lottery/js/lottery.dlt.js" charset="gbk"></script>
		<script src="<%=basePath %>inc/outapi/lottery/js/lottery.xssc.1002.js" charset="gbk"></script>
		<script src="<%=basePath %>inc/outapi/lottery/js/lottery.k3.base.js" charset="gbk"></script>
		<form id="baseForm" action="<%=basePath %>lottery/fucai3dorder.action" method="get">
			<input type="hidden" name="sid" value="b34e157060195c4aac29bf7216cd1059">
			<input type="hidden" name="lotteryCategory" value="2" id="lotteryCategory">
			<input type="hidden" name="canSell" value="1" id="canSell">
			<input type="hidden" name="addByUserHref" value="<%=basePath %>/lottery/lotteryInfo.action?gameid=6" id="addByUserHref">
			<input type="hidden" name="appendType" value="3" id="appendType">
			<input type="hidden" name="iAgreementFlag" value="1" id="iAgreementFlag">
			<input type="hidden" id="alertMessage" value="">
			<input type="hidden" id="currendtime" >
		
		<div class="new-ct kuai3">
			<div class="order-list">
				<!--号码区-->
				<div id="numberInfo"><div class="new-tbl-type" id="numberrow16"><div class="new-tbl-cell w80"><div class="choose-box"><div class="choose-num"><span style="padding-right:0px;">3 4 5</span></div><div class="total"><span>组六单式</span><span class="new-fr">1注 2元</span></div></div></div><div class="new-tbl-cell"><a href="javascript:void(0);" onclick="deleteRow(16)" class="btn-del">删除</a></div></div><div class="new-tbl-type" id="numberrow15"><div class="new-tbl-cell w80"><div class="choose-box"><div class="choose-num"><span style="padding-right:0px;"><span style="color:red;">3|</span><span style="color:blue;">5|</span><span style="color:black;">3|</span></span></div><div class="total"><span>直选单式</span><span class="new-fr">1注 2元</span></div></div></div><div class="new-tbl-cell"><a href="javascript:void(0);" onclick="deleteRow(15)" class="btn-del">删除</a></div></div><div class="new-tbl-type" id="numberrow14"><div class="new-tbl-cell w80"><div class="choose-box"><div class="choose-num"><span style="padding-right:0px;">5 6 7</span></div><div class="total"><span>组六单式</span><span class="new-fr">1注 2元</span></div></div></div><div class="new-tbl-cell"><a href="javascript:void(0);" onclick="deleteRow(14)" class="btn-del">删除</a></div></div><div class="new-tbl-type" id="numberrow13"><div class="new-tbl-cell w80"><div class="choose-box"><div class="choose-num"><span style="padding-right:0px;">4 6 7</span></div><div class="total"><span>组六单式</span><span class="new-fr">1注 2元</span></div></div></div><div class="new-tbl-cell"><a href="javascript:void(0);" onclick="deleteRow(13)" class="btn-del">删除</a></div></div><div class="new-tbl-type" id="numberrow12"><div class="new-tbl-cell w80"><div class="choose-box"><div class="choose-num"><span style="padding-right:0px;">4 5 7</span></div><div class="total"><span>组六单式</span><span class="new-fr">1注 2元</span></div></div></div><div class="new-tbl-cell"><a href="javascript:void(0);" onclick="deleteRow(12)" class="btn-del">删除</a></div></div><div class="new-tbl-type" style="display: none;" id="numberrow11"><div class="new-tbl-cell w80"><div class="choose-box"><div class="choose-num"><span style="padding-right:0px;">4 5 6</span></div><div class="total"><span>组六单式</span><span class="new-fr">1注 2元</span></div></div></div><div class="new-tbl-cell"><a href="javascript:void(0);" onclick="deleteRow(11)" class="btn-del">删除</a></div></div><div class="new-tbl-type" style="display: none;" id="numberrow10"><div class="new-tbl-cell w80"><div class="choose-box"><div class="choose-num"><span style="padding-right:0px;">6 6 7</span></div><div class="total"><span>组三单式</span><span class="new-fr">1注 2元</span></div></div></div><div class="new-tbl-cell"><a href="javascript:void(0);" onclick="deleteRow(10)" class="btn-del">删除</a></div></div><div class="new-tbl-type" style="display: none;" id="numberrow9"><div class="new-tbl-cell w80"><div class="choose-box"><div class="choose-num"><span style="padding-right:0px;">6 7 7</span></div><div class="total"><span>组三单式</span><span class="new-fr">1注 2元</span></div></div></div><div class="new-tbl-cell"><a href="javascript:void(0);" onclick="deleteRow(9)" class="btn-del">删除</a></div></div><div class="new-tbl-type" style="display: none;" id="numberrow8"><div class="new-tbl-cell w80"><div class="choose-box"><div class="choose-num"><span style="padding-right:0px;"><span style="color:red;">3|</span><span style="color:blue;">3|</span><span style="color:black;">4|</span></span></div><div class="total"><span>直选单式</span><span class="new-fr">1注 2元</span></div></div></div><div class="new-tbl-cell"><a href="javascript:void(0);" onclick="deleteRow(8)" class="btn-del">删除</a></div></div><div class="new-tbl-type" style="display: none;" id="numberrow5"><div class="new-tbl-cell w80"><div class="choose-box"><div class="choose-num"><span style="padding-right:0px;"><span style="color:red;">3|</span><span style="color:blue;">2|</span><span style="color:black;">2|</span></span></div><div class="total"><span>直选单式</span><span class="new-fr">1注 2元</span></div></div></div><div class="new-tbl-cell"><a href="javascript:void(0);" onclick="deleteRow(5)" class="btn-del">删除</a></div></div><div class="new-tbl-type" style="display: none;" id="numberrow4"><div class="new-tbl-cell w80"><div class="choose-box"><div class="choose-num"><span style="padding-right:0px;"><span style="color:red;">2|</span><span style="color:blue;">3|</span><span style="color:black;">4|</span></span></div><div class="total"><span>直选单式</span><span class="new-fr">1注 2元</span></div></div></div><div class="new-tbl-cell"><a href="javascript:void(0);" onclick="deleteRow(4)" class="btn-del">删除</a></div></div><div class="new-tbl-type" style="display: none;" id="numberrow3"><div class="new-tbl-cell w80"><div class="choose-box"><div class="choose-num"><span style="padding-right:0px;"><span style="color:red;">2|</span><span style="color:blue;">3|</span><span style="color:black;">2|</span></span></div><div class="total"><span>直选单式</span><span class="new-fr">1注 2元</span></div></div></div><div class="new-tbl-cell"><a href="javascript:void(0);" onclick="deleteRow(3)" class="btn-del">删除</a></div></div><div class="new-tbl-type" style="display: none;" id="numberrow2"><div class="new-tbl-cell w80"><div class="choose-box"><div class="choose-num"><span style="padding-right:0px;"><span style="color:red;">2|</span><span style="color:blue;">2|</span><span style="color:black;">4|</span></span></div><div class="total"><span>直选单式</span><span class="new-fr">1注 2元</span></div></div></div><div class="new-tbl-cell"><a href="javascript:void(0);" onclick="deleteRow(2)" class="btn-del">删除</a></div></div><div class="new-tbl-type" style="display: none;" id="numberrow1"><div class="new-tbl-cell w80"><div class="choose-box"><div class="choose-num"><span style="padding-right:0px;"><span style="color:red;">2|</span><span style="color:blue;">2|</span><span style="color:black;">2|</span></span></div><div class="total"><span>直选单式</span><span class="new-fr">1注 2元</span></div></div></div><div class="new-tbl-cell"><a href="javascript:void(0);" onclick="deleteRow(1)" class="btn-del">删除</a></div></div><a href="javascript:void(0)" onclick="changeShowType()" class="link-more">更多投注》</a></div>
				
		</div>
		<!--功能区1-->
				<div class="option-area2">
		        	<div class="input-area new-tbl-type">
		            	<input type="hidden" name="" id="issue" value="1" class="new-input" maxlength="3">
		                <span class="new-tbl-cell">倍数<input type="text" name="" id="multi" value="1" class="new-input" maxlength="3">倍</span>
		            </div>
		        	<div class="check-area new-tbl-type">
						<span class="new-tbl-cell">&nbsp;</span>
		                <!--<span class="new-tbl-cell"><a href="#" class="btn-chk"></a><span>增加投注</span></span>-->
		            </div>
		            <div class="btn-tbl">
		                <div class="new-tbl-type">
		                    <div class="new-tbl-cell">
		                        <a href="javascript:void(0);" class="btn-type1" id="clear-all">清空</a>
		                    </div>
		                    <div class="new-tbl-cell">
		                        <a href="javascript:void(0);" class="btn-type1" id="go-on-select">添加手选</a>
		                    </div>
		                    <div class="new-tbl-cell">
		                        <a href="javascript:void(0);" class="btn-type1" id="btn-random1">添加机选</a>
		                    </div>
		                </div>
		            </div>
		        </div>
				<a name="touzhu"></a>
				<!--功能区2-->
		        <div class="pay-total">
		        	<div id="payTotal">总金额:<span class="new-txt-rd2">元</span><span class="new-fr">注期倍</span></div>
		            <div class="check-cont">
		                <a href="javascript:void(0);" class="btn-chk on" id="iAgreement"></a>我已经阅读并同意了<a href="" class="link-more">《客户端购彩协议》</a>
		            </div>
		            <div class="btn-area">
		            	<a href="javascript:void(0);" class="btn-type2" id="sub_btn">立即投注</a>
		            </div>
		        </div>
		    </div>    
			 
			<div class="shade" style="display:none" id="shade"></div>
			<div class="pop" style="display:none;height:85px" id="confirmPop"><span id="confirmPopHtml"></span> <div class="new-tbl-type">
		    <span class="new-tbl-cell"><a href="javascript:void(0);" class="btn-type6" id="confirmPopBqButton">取消</a></span>
		    <span class="new-tbl-cell"><a href="javascript:void(0);" class="btn-type2" id="confirmPopTzButton">确认</a> </span>
		    </div>
		</div>
			<div class="pop" style="display:none" id="alertPop">
				<span id="alertPopHtml"></span>
				<a href="javascript:void(0);" class="btn-type2 bt2-v1" id="alertPopButton" onclick="closeAlertTip1();">确认</a>            
			</div>
		</form>  
		 <script src="<%=basePath %>inc/outapi/lottery/js/lottery.pre.js" charset="gbk"></script>
		
		
		
		<style type="text/css">
			.new-footer{margin-top:10px;background-color:#f3f2f2;font-size:14px;color:#6e6e6e;text-align:center}
			.new-footer .new-f-login{position:relative;padding:0 12px;background-color:#a8a8a8;line-height:27px;color:#fff;text-align:left;heigth:27px}
			.new-footer .new-f-login .new-back-top{position:absolute;right:12px}
			.new-footer .new-f-login .new-bar2{margin:0 5px}
			.new-footer .new-f-login a{color:#fff}
			.new-footer .new-f-section a{margin-left:20px;color:#6e6e6e}
			.new-footer .new-f-section .on{color:#c30202}
			.new-footer .new-f-section a:first-child{margin-left:0}
			.new-bl{padding:0 15px}
			.new-footer .new-f-section,.new-footer .new-f-section2{padding:10px 0}
			.new-footer .new-f-section2{padding-top:0;font-size:12px;color:#6e6e6e}
			.new-f-banner{background-color:#fff}
			.new-download-app{display:block;width:320px;height:61px;margin:0 auto;border-bottom:1px solid #dad4cf;border-top:1px solid #fcfaf9;background-color:#fff;font-size:.875em;line-height:44px;text-align:center}
		</style>
		
		<footer>
			<div class="new-footer">
		    	<div class="new-f-login">
					<a href="https://passport.m.jd.com/user/login.action?sid=b34e157060195c4aac29bf7216cd1059">登录</a><span class="new-bar2">|</span><a href="https://passport.m.jd.com/user/mobileRegister.action?sid=b34e157060195c4aac29bf7216cd1059">注册</a>
					<span class="new-back-top"><a href="http://m.jd.com/showvote.html?sid=b34e157060195c4aac29bf7216cd1059">反馈</a><span class="new-bar2">|</span><a href="http://caipiao.m.jd.com/pick/pre?lotteryCategory=2&sid=b34e157060195c4aac29bf7216cd1059#top">回到顶部</a></span>
		        </div>
		    	<div class="new-f-section"><a href="http://m.jd.com/index.html?v=w&sid=b34e157060195c4aac29bf7216cd1059">标准版</a><a href="javascript:void(0)" class="on">触屏版</a><a href="http://m.jd.com/index/pcsite.action">电脑版</a></div>
		        <div class="new-f-section2">Copyright © 2012-2014  天下名品 版权所有</div>
		    </div>
		</footer>
		<script type="text/javascript">
			$("#currendtime").val(window.sessionStorage.getItem("currendtime3d"));
		</script>
	 </body>
</html>