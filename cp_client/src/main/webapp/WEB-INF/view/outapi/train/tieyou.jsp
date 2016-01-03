<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>火车票预订</title>
    <meta http-equiv="Content-type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
    <link rel="stylesheet" type="text/css" href="http://img.union.tieyou.com/code/css/style.css" />
	<link rel="stylesheet" type="text/css" href="http://img3.tieyou.com/css/calendar.css" />
	<link rel="stylesheet" href="<%=basePath %>/inc/outapi/airline/css/airline.css">
  </head>
  
  <body>
   
   <style>
   	.box_con ul li label{
   		font-size:16px;
   		width:90px;
   		font-family: '微软雅黑';
   		color: #000;
   	}
   	.b_300_170{
   		width:100%;
   		padding:0px;
   	}
   	.box_con td{
   		border-bottom: 1px solid #ccc;
   		padding: 4px 0 9px 0;
   	}
   	.input_box{
   		width:100%;
   		background:none;
   		border:1px solid #ccc;
   		padding: 5px 0;
   	}
   	.btn3{
   		margin-left:10px;
   	}
   	.input_box input{
   		width:100%;
   		padding-left: 11px;
   		color:#aaa;
   		top:7px;
   	}
   	.input_box div{
   		top: 7px;
   	}
	.sear-btn211 {
		display: block;
		border: none;
		width: 100%;
		height: 40px;
		line-height: 40px;
		color: #fff;
		font-size: 20px;
		font-family: "微软雅黑";
		border-radius: 2px;
		-webkit-border-radius: 2px;
		border: 1px solid #d44300;
		background: -webkit-gradient(linear,left top,left bottom,from(#ff8642),color-stop(.5,#ff8642),to(#ff6e36));
		font-size: 16px;
		cursor: pointer;
	}
	.box_con ul li{
		float: none;
	}
   </style>
   <header>
	<div class="header margin-center">
    	<span class="header-txt">火车票预订</span><div class="header-btn"><a href="http://m.txmp.com.cn:80/"><img src="http://m.txmp.com.cn:80//inc/outapi/airline/images/home.png"></a></div>
    </div>
</header>
<!-- 在线预定-2 300*170 star -->
<div style="border:1px solid #d7d7d7;margin: 10px;border-bottom:0">
<div class="b_300_170" style="float:none;height:239px;">
	<div class="box_con">
		<form name="chepiaoForm" id="cityForm" method="post" >
		<ul>
			<li>
				<table cellspacing="0" cellpadding="0" width="100%">
				  <tbody><tr>
				    <td width="10"></td>
				    <td width="90" align="center">
						<label>出发城市</label>
				    </td>
				    <td>
						<div class="input_box"><input type="text" name="from" value="请选择出发城市" id="begin" onfocus="showCity('begin');focusIt(this.id)" onblur="hideCity()"><div class="hotcity_ico" onclick="$('#begin').focus();"></div></div>
				    </td>
				    <td width="10"></td>
				  </tr>
				  </tbody>
				</table>
			</li>
			<li>
			
				<table cellspacing="0" cellpadding="0" width="100%">
				  <tbody><tr>
				    <td width="10"></td>
				    <td width="90" align="center">
						<label>到达城市</label>
				    </td>
				    <td>
						<div class="input_box"><input name="to"   type="text" value="请选择到达城市" id="end"   onfocus="showCity('end'  );focusIt(this.id)" onblur="hideCity()"/><div class="hotcity_ico" onclick="$('#end'  ).focus();"></div></div>
				    </td>
				    <td width="10"></td>
				  </tr>
				  </tbody>
				</table>
				
			</li>
			<li>
			
				<table cellspacing="0" cellpadding="0" width="100%">
				  <tbody><tr>
				    <td width="10"></td>
				    <td width="90" align="center">
						<label>出行日期</label>
				    </td>
				    <td>
						<div class="input_box"><input name="date" type="text" value="请选择出行日期" id="date"  class="trainCalendar" onfocus="WdatePicker({readOnly:true})"/><div class="date_ico"></div></div>
				    </td>
				    <td width="10"></td>
				  </tr>
				  </tbody>
				</table>
			
			</li>
			<li>
			<table cellspacing="0" cellpadding="0" width="100%">
				  <tbody><tr>
				  <td width=10 style="height:45px;"></td>
				  <td colspan=2 style="border-bottom:1px solid #ccc;padding-bottom:0;height:45px"><input style="margin-bottom:10px;" type="submit" value="预订车票" class="sear-btn211" /></td>
				  <td width=10 style="height:45px;"></td>
				  </tr>
				  </tbody>
				  </table>
				<input type="hidden" value="xfhx20141023" name="c"/>
				<input type="hidden" value="4560" name="s"/>
				<input type="hidden" value="260" name="p"/>
				<input type="hidden" value="yuding" name="type" />
				
				
			</li>
		</ul>
		</form>
	</div>
</div>
</div>
<%@ include file="/WEB-INF/view/inc/footer.jsp" %>
<br class="clear" />
<script type="text/javascript" charset="UTF-8" src="http://img.union.tieyou.com/code/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" charset="UTF-8" src="<%=basePath %>/inc/outapi/train/listCity.js"></script>
<script type="text/javascript" charset="UTF-8" src="http://img.union.tieyou.com/code/js/yuding.js"></script>
<script type="text/javascript" charset="UTF-8" src="<%=basePath %>/inc/outapi/train/calendar/calendar.js"></script>
<script type="text/javascript" charset="UTF-8" src="<%=basePath %>/inc/outapi/train/calendar/WdatePicker.js"></script>
  </body>
</html>
