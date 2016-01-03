<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/view/outapi/airline/com/com.jsp" %>
	<script type="text/javascript" src="<%=basePath %>/inc/outapi/airline/js/aircity.js"></script>
	<script type="text/javascript" src="<%=basePath %>/inc/outapi/airline/js/jquery.js"></script>
	<script type="text/javascript" src="<%=basePath %>/inc/outapi/airline/js/j.suggest.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=basePath %>/inc/outapi/airline/css/base.css"  />
	<script type="text/javascript" src="<%=basePath %>/inc/outapi/airline/js/zepto.min.js"></script> 
	<script type="text/javascript">
	$(function(){
		$("#orgAirport").suggest(citys,{hot_list:commoncitys,dataContainer:'#orgAirportCode',onSelect:function(){}, attachObject:'#suggest'});
		 $("#dstAirport").suggest(citys,{hot_list:commoncitys,dataContainer:'#dstAirportCode',onSelect:function(){},attachObject:"#suggest2"});
	});
	</script>
	 <style type="text/css">
		#box{width:100%;text-align:left;padding-top:80px;}
		#suggest,#suggest2{width:180px;}
		.gray{color:gray;}
		.ac_results {background:#fff;border:1px solid #7f9db9;position: absolute;z-index: 10000;display: none;}
		.ac_results ul{margin:0;padding:0;list-style:none;}
		.ac_results li a{white-space: nowrap;text-decoration:none;display:block;color:#05a;padding:1px 3px;}
		.ac_results li{border:1px solid #fff;}
		.ac_over,.ac_results li a:hover {background:#c8e3fc;}
		.ac_results li a span{float:right;}
		.ac_result_tip{border-bottom:1px dashed #666;padding:3px;}
	</style>
<title>机票预订</title>
</head>

<body>
<!--头部开始-->
<header>
	<div class="header margin-center">
    	<span class="header-txt">机票预订</span><div class="header-btn"><a href="<%=basePath %>"><img src="<%=basePath %>/inc/outapi/airline/images/home.png" /></a></div>
    </div>
</header>
<section>
<form action="<%=basePath %>airLine/dosearch.action" method ="get" id="searchArgs" name="searchArgs">
			<div class="airticket">
				<div class="arkt-cont">
			    	<ul class="arkt-lst">
			        	<li class="arkt-li">
			            	<div class="tbl-type">
			                    <span class="city tbl-cell">出发城市</span>
			                    <div class="city-txt tbl-cell">
			                  		<input type="hidden"  name="orgAirportCode" id="orgAirportCode" />
									<input type="text" name="orgAirport" id="orgAirport" style="width:99.7%;border:1px solid #cecece;text-indent:10px;height:37px;"  />
									<div id='suggest' class="ac_results"></div>
			                    </div>
			                </div>
			            </li>
			            <li>
			            </li>
			        	<li class="arkt-li">
			            	<div class="tbl-type">
			                    <span class="city tbl-cell">到达城市</span>
			                    <div class="city-txt tbl-cell">
		                    			<input type="hidden" name="dstAirportCode" id="dstAirportCode" value="" />
										<input type="text" name="dstAirport" id="dstAirport" style="width:99.8%;border:1px solid #cecece;text-indent:10px;height:37px;"/>
								   		 <div id='suggest2' class="ac_results"></div>
			                    </div>
			                </div>
			            </li>
			        	<li class="arkt-li">
			            	<div class="tbl-type">
			                    <span class="city tbl-cell">出发时间</span>
			                    <div class="city-txt tbl-cell">
			                    	<div class="city-ipt">
			                        	<div id="checkInDate" data-tag="checkIn"> 
											  <span id="checkInShowVal" class="calendar"></span> 
										 </div> 
										 <input type="hidden" name="date" id="deptdate" /> 
			                        </div>	
			                         <div class="htl_cal" style="border:0px;" id="checkInDateDd"> 
											<div id="checkInCalendar" class="dp" style="display: none;"></div> 
									  </div>
			                    </div>
			                </div>
			            </li>
			           
			        	<li class="arkt-li">
			            	<div class="tbl-type">
			                    <span class="city tbl-cell">起飞时间</span>
			                    <div class="city-txt tbl-cell">
			                    <select class="time-sel" style="text-indent:5px;">
			                        <option>不限</option>
			                        <option>00:00-06:00</option>
			                        <option>06:00-12:00</option>
			                        <option>12:00-18:00</option>
			                        <option>18:00-24:00</option>
			                    </select>
			                    </div>
			               	</div> 
			            </li>
			            
			        </ul>
			        <p class="m10"><input type="button" class="sear-btn2" value="搜索"  onclick="searchAirLine();"/></p>
			    </div>
		</div> 
</form>	

<script type="text/javascript" src="<%=basePath %>/inc/outapi/airline/js/calendar.js"></script> 
  <script>
		  Date.prototype.dateAdd = function(interval, number) {
		    var d = this;
		    var k = {
		        y: "FullYear",
		        q: "Month",
		        m: "Month",
		        w: "Date",
		        d: "Date",
		        h: "Hours",
		        n: "Minutes",
		        s: "Seconds",
		        ms: "MilliSeconds"
		    };
		    var n = {
		        q: 3,
		        w: 7
		    };
		    eval("d.set" + k[interval] + "(d.get" + k[interval] + "()+" + ((n[interval] || 1) * number) + ")");
		    return d
		};
		  
		  </script>
		  <script type="text/javascript">
		
		    //日期选择器
			var myDate = new Date();
			myDate = myDate.dateAdd('d',1);
			var month = String(myDate.getMonth()+1);
		    var _currentDate = String(myDate.getDate());
		    month = month.length==1 ? "0"+month : month;
		    _currentDate = _currentDate.length ==1 ?  "0"+_currentDate : _currentDate;
		    $("#deptdate").val(myDate.getFullYear()+"-"+month+"-"+_currentDate);
			//Date.prototype.dateAdd(_currentDate,1);
			new Calendar().init("checkIn","checkOut_First",6,"",myDate);
		  
		   
		</script> 
   	   <script type="text/javascript">
   	   			function searchAirLine(){
   	   				if($("#orgAirportCode").val().length<1){
   	   					alert("请选择出发城市"); 
   	   					return;
   	   				} 
   	   				if($("#dstAirportCode").val().length<1){
   	   					alert("请选择目的城市");
   	   					return; 
   	   				}
   	   				if($("#deptdate").val().length<1){
   	   					alert("请选出发时间！");
   	   					return; 
   	   				} 
   	   			 	 $("#deptdate").val($("#checkInShowVal").text());
  	   			 	if(testSessionStorage()){
						window.sessionStorage.setItem("orgAirportCode",$("#orgAirport").val());
						window.sessionStorage.setItem("dstAirportCode",$("#dstAirport").val());
						window.sessionStorage.setItem("date",$("#deptdate").val());
						window.sessionStorage.setItem("orgAirport",$("#orgAirportCode").val());
						window.sessionStorage.setItem("dstAirport",$("#dstAirportCode").val());
						
					}
   	   				$("#searchArgs").submit();
   	   			}
   	   				
   	   			function testSessionStorage() {
				    if ( !! window.sessionStorage) {
				        testSessionStorage = function() {
				            return true
				        }
				    } else {
				        testSessionStorage = function() {
				            return false
				        }
				    }
				    return testSessionStorage()
				}	
   	   	
   	   </script>
</section>
        
<!--底部开始-->
<%@ include file="/WEB-INF/view/inc/footer.jsp" %>
        
</body>
</html>
