<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.xingfugo.util.DateUtils"%>
<%@ include file="/WEB-INF/view/outapi/lottery/inc/inc.jsp" %>
<!DOCTYPE html>
<html >
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>彩票开奖公告  </title>
		<meta name="author" content="xingfugou.com">
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

	<body>
		<header>	
			<div class="new-header">
	    	    <a id="backIndex" onclick="history.back();" class="new-a-back"> <span>返回</span>   </a>
			  	<h2>开奖公告</h2>
			    <a href="" class="new-a-jd" id="btnJdkey"><span>名品键</span></a>
			</div>
		</header>
		<link rel="stylesheet" type="text/css" href="<%=basePath %>/inc/outapi/lottery/css/kuai3.css">
		<style type="text/css">
			body {
				background: white;
				background-color: white;
			}
		</style>
		<div class="sliding list">
			<div class="k3-tab">
				<div class="new-tbl-type">
					<div class="new-tbl-cell">
						<a href="<%=basePath %>lottery/index.action">彩票大厅</a>
					</div>
					<div class="new-tbl-cell">
						<a class="on" href="">开奖公告</a>
					</div>
					<div class="new-tbl-cell">
						<a href="">我的彩票</a>
					</div>
				</div>
			</div>
			<div class="mc">
				<ul>
					<a href="">
						<li class="clear">
							<div class="fl ga">
								<dl>
									<dt><img src="<%=basePath %>inc/outapi/lottery/images/3D.jpg" width="50" height="50"></dt>
									<dd>福彩3D</dd>
								</dl>
							</div>
							<div class="fl">
								<p>第<span class="red">${fc3dInfo.currperiod-1 }</span><span class="ga">期</span>[${fn:substring(fc3dInfo.currendtime, 5,10)}]</p>
								<ul class="ball-list clear">
									<li><div class="red-small"></div></li>
									<li><div class="red-small"></div></li>
									<li><div class="red-small"></div></li>
								</ul>
							</div>
							<p>
								<span class="menu-botton-arrow"></span>
							</p>
						</li>
					</a>
				
					<a href="">
						<li class="clear">
							<div class="fl ga">
								<dl>
									<dt><img src="<%=basePath %>inc/outapi/lottery/images/double.jpg" width="50" height="50"></dt>
									<dd>双色球</dd>
								</dl>

							</div>
							<div class="fl">
								<p>第<span class="red">${ssqInfo.currperiod-1 }</span><span class="ga">期</span>[07-22]
								</p>
								<ul class="ball-list clear">
									<li><div class="red-small"></div></li>
									<li><div class="red-small"></div></li>
									<li><div class="red-small"></div></li>
									<li><div class="red-small"></div></li>
									<li><div class="red-small"></div></li>
									<li><div class="red-small"></div></li>
									<li><div class="blue-small"></div></li>
								</ul>
							</div>
							<p>
								<span class="menu-botton-arrow"></span>
							</p>
						</li> 
					</a>
					
					<a href="">
						<li class="clear">
							<div class="fl ga">
								<dl>
									<dt><img src="<%=basePath %>inc/outapi/lottery/images/7color.jpg" width="50" height="50"></dt>
									<dd>七乐彩</dd>
								</dl>
							</div>
							<div class="fl">
								<p>第<span class="red">${qlcInfo.currperiod -1}</span><span class="ga">期</span>[07-23]</p>
								<ul class="ball-list clear">
									<li><div class="red-small"></div></li>
									<li><div class="red-small"></div></li>
									<li><div class="red-small"></div></li>
								</ul>
							</div>
							<p>
								<span class="menu-botton-arrow"></span>
							</p>
						</li>
					</a>
					
					<a href="">
						<li class="clear">
							<div class="fl ga">
								<dl>
									<dt><img src="<%=basePath %>inc/outapi/lottery/images/happy8.jpg" width="50" height="50"></dt>
									<dd>快乐8</dd>
								</dl>
							</div>
							<div class="fl">
								<p>第<span class="red">${klbInfo.currperiod -1}</span><span class="ga">期</span>[07-23]</p>
								<ul class="ball-list clear">
									<li><div class="red-small"></div></li>
									<li><div class="red-small"></div></li>
									<li><div class="red-small"></div></li>
								</ul>
							</div>
							<p>
								<span class="menu-botton-arrow"></span>
							</p>
						</li>
					</a>
					
					<a href="">
						<li class="clear">
							<div class="fl ga">
								<dl>
									<dt><img src="<%=basePath %>inc/outapi/lottery/images/pk10.jpg" width="50" height="50"></dt>
									<dd>PK拾</dd>
								</dl>
							</div>
							<div class="fl">
								<p>第<span class="red">${pksInfo.currperiod-1 }</span><span class="ga">期</span>[07-23]</p>
								<ul class="ball-list clear">
									<li><div class="red-small"></div></li>
									<li><div class="red-small"></div></li>
									<li><div class="red-small"></div></li>
								</ul>
							</div>
							<p>
								<span class="menu-botton-arrow"></span>
							</p>
						</li>
					</a>
				</ul>
			</div>
		</div>
		<%@ include file="/WEB-INF/view/inc/footer.jsp"%>
	</body>
</html>
