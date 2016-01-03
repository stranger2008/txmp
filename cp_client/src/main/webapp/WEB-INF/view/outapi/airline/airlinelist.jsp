<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.xingfugo.business.outapi.airline.module.AvailableFlightWithPriceAndCommisionReply" %>
<%@ page import="com.xingfugo.business.outapi.airline.module.WsFlightWithPriceAndCommision" %>
<%@ page import="com.xingfugo.business.outapi.airline.module.WsSeatWithPriceAndComisionItem" %>
<%@ page import="java.util.Collections" %>
<%@ page import="java.math.BigDecimal" %>
<%@page import="com.xingfugo.business.redis.JedisService"%>
<%@page import="com.xingfugo.util.CreateSpringContext"%>


<!DOCTYPE html>
<html>
<head>

<%@ include file="/WEB-INF/view/outapi/airline/com/com.jsp" %>
<script type="text/javascript" src="<%=basePath %>/inc/outapi/airline/js/airlinecompany.js"></script>
<title>机票预订-航班列表</title>
</head>

<body>
<!--头部开始-->

<header>
	<div class="header margin-center">
    	<div class="return-btn">
    		<a href="<%=basePath %>airLine/searchairline.action"><img src="<%=basePath %>/inc/outapi/airline/images/return.png" /></a>
    	</div>
    	<span class="header-txt2" onclick="javascript:location.href='<%=basePath %>airLine/searchairline.action'">机票预订</span>
    	<span class="header-txt">航班列表</span>
    </div>
</header>

<section>
<div class="airticket">
	<div class="arkt-cont">
		<% 
		AvailableFlightWithPriceAndCommisionReply aReply =(AvailableFlightWithPriceAndCommisionReply)request.getAttribute("aReply");
    		if(aReply != null && aReply.getReturnCode().equals("S")){
    			JedisService js =(JedisService) CreateSpringContext.getContext().getBean("jedisService");
    		  %>
        <table class="p-top" cellpadding="0" cellspacing="0" width="100%">
      	  <input type="hidden" name ="beforeToday" id="beforeToday" value="${aReply.flightItems.date}"/>
        	<tr>
                <td width="80"><a onclick="javascript:beforeToday();" class="p-prev" id="beforeTodayEvent"><b></b>前一天</a></td>
                <td align="center"><span class="">${aReply.flightItems.date}<br><script>document.write(window.sessionStorage.getItem("orgAirportCode"))</script>-<script>document.write(window.sessionStorage.getItem("dstAirportCode"))</script></span></td>
                <td width="80"><a onclick="javascript:yesterday();" class="p-next" id="yesterdayEvent">后一天<b></b></a></td>
            </tr>
            <input type="hidden" name ="yesterday" id="yesterday" value="${aReply.flightItems.date}">
        </table>
        
    	<ul class="flight-lst">
    		<% 
         		for(int i=0;i<aReply.getFlightItems().getWFlightWithPriceAndCommisionList().size();i++){
						WsFlightWithPriceAndCommision ws =aReply.getFlightItems().getWFlightWithPriceAndCommisionList().get(i);
         	%>
        	<li class="flight-li repons" onclick="showDiv('resultDiv<%=i%>');" style="cursor:hand">
            	<a >
				<span class="ft-img"><img src="<%=basePath %>/inc/outapi/airline/images/<%=ws.getFlightNo().substring(0,2)%>.gif" /></span>
                <span class="info">
                	<span class="ft-id">
                		<%if(ws.getFlightNo()!=null){ %>
		                	 <script type="text/javascript">
		                		document.write(company['<%=ws.getFlightNo().substring(0,2)%>']); 
		                	 </script> 
	                	<%}%>  <%=ws.getFlightNo()%>
                	 </span><br />
                	<span><%=ws.getDepTime().substring(0,2)%>:<%=ws.getDepTime().substring(2,ws.getDepTime().length())%>  <%=js.SETS.smembers(ws.getOrgCity()) %></span><br />
                	<span><%=ws.getArriTime().substring(0,2)%>:<%=ws.getArriTime().substring(2,ws.getArriTime().length())%>  <%=js.SETS.smembers(ws.getDstCity()) %> </span>
                </span>
         		<%
				  Collections.sort(ws.getSeatItems(), new Comparator<WsSeatWithPriceAndComisionItem>() {
			            public int compare(WsSeatWithPriceAndComisionItem arg0, WsSeatWithPriceAndComisionItem arg1) {
			                return   arg0.getDiscount().compareTo(arg1.getDiscount());
			            }
			        });
			 	%>
                <span class="ft-price">
                	<span class="red-pri">￥<%=ws.getSeatItems().get(0).getParPrice() %>起</span><br />
                	<%
	               		double airlineDiscount = Double.valueOf(ws.getSeatItems().get(0).getDiscount()) ;
	               		if(airlineDiscount<1){
	               			BigDecimal   b   =   new   BigDecimal(airlineDiscount);  
	               			airlineDiscount=b.multiply(new BigDecimal(10)).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
                	%>
                		<%=airlineDiscount%>折起
                	<%}%>
                </span>
                <b></b>
                </a>
            </li>
	            <div id="resultDiv<%=i%>" style="display:none" >
	            	<%for(int j =0;j<ws.getSeatItems().size();j++){ 
						WsSeatWithPriceAndComisionItem wwaci =ws.getSeatItems().get(j);
					%>
						<form action="<%=basePath %>airLine/order.action" method="get" id="forms<%=i%><%=j%>" >
				        	<li class="flight-li repons">&nbsp;<span class="info"><span class="red-pri">￥<%=wwaci.getParPrice() %></span><br />
				                	<span><%=wwaci.getSeatMsg() %></span><br />
				                	<span class="red-pri">
				                <%
			               		double discount = Double.valueOf(wwaci.getDiscount()) ;
			               		if(discount<1){
			               			BigDecimal   b   =   new   BigDecimal(discount);  
			               			discount=b.multiply(new BigDecimal(10)).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
		                		%>
		                			<%=discount%>折
		                		<%}else{%>
		                			全价
		                		<%} %>
				                	</span><br />
				                </span>
				                <span class="fr">
				                
				                	<input type ="hidden" name ="orgJetquay" id ="orgJetquay" 		value ="<%=ws.getOrgJetquay() %>" >
				                	<input type ="hidden" name ="dstJetquay" id ="dstJetquay" 		value ="<%=ws.getDstJetquay() %>" >
				                	<input type ="hidden" name ="flightNo" id ="flightNo" 		value ="<%=ws.getFlightNo() %>" >
									<input type ="hidden" name ="depCode" id ="depCode" 		value ="<%=ws.getOrgCity() %>" >
									<input type ="hidden" name ="policyId" id ="policyId" 		value ="<%=wwaci.getPolicyData().getPolicyId() %>" >
									<input type ="hidden" name ="arrCode" id ="arrCode" 		value ="<%=ws.getDstCity() %>" >
									<input type ="hidden" name ="seatClass" id ="seatClass" 	value ="<%=wwaci.getSeatCode() %>" >
									<input type ="hidden" name ="depDate" id ="depDate" 		value ="<%=aReply.getFlightItems().getDate() %>" >
									<input type ="hidden" name ="depTime" id ="depTime" 		value ="<%=ws.getDepTime() %>" >
									<input type ="hidden" name ="arrTime" id ="arrTime" 		value ="<%=ws.getArriTime() %>" >
									<input type ="hidden" name ="planeModel" id ="planeModel" 	value ="<%=ws.getPlaneType() %>" >
									<input type ="hidden" name ="parPrice" id ="parPrice" 		value ="<%=wwaci.getParPrice() %>" >
									<input type ="hidden" name ="fuelTax" id ="fuelTax" 		value ="<%=aReply.getFlightItems().getAudletFuelTax() %>" >
									<input type ="hidden" name ="airportTax" id ="airportTax" 	value ="<%=aReply.getFlightItems().getAudletAirportTax() %>" >
									<input type="submit" value="预订" class="book-btn"  name ="bookAir" id ="bookAir"/>
				                </span>
				            <br></li>
		             </form>
		            <%
		            	}
		             %>
	            </div>

        </ul>
         <%} 
          }else{%>
          	没有您要乘坐的航班！请另选日期！
          <%} %>
    </div>
</div> 
</section>
<script>
	function showDiv(divId){
		var divBlock =document.getElementById(divId).style.display;
		if(divBlock=='block'){
			document.getElementById(divId).style.display='none';
		}else{
			document.getElementById(divId).style.display='block'
		}
	}
	
	function beforeToday(){
		var orgCode=window.sessionStorage.getItem("orgAirport"),
			dstCode=window.sessionStorage.getItem("dstAirport"),
			d = new Date($("#beforeToday").val()),
			nowDay=new Date();
			if(d<nowDay){
				 $("#beforeTodayEvent").removeAttr("onclick");
				 return false;
			}else{
				 $("#beforeTodayEvent").bind('click', beforeToday);
			}
			d.setTime(d.getTime()-24*60*60*1000);
			var bDay = d.getFullYear()+"-" + (d.getMonth()+1) + "-" + d.getDate();
			window.sessionStorage.setItem("date",bDay);
			$("#beforeToday").val(bDay);
			location.href="<%=basePath %>airLine/dosearch.action?orgAirportCode="+orgCode+"&dstAirportCode="+dstCode+"&date="+bDay;
	}
	
	$('#yesterdayEvent').click(function(){
		   yesterday();
		});
	
	function yesterday(){
		var orgCode=window.sessionStorage.getItem("orgAirport"),
			dstCode=window.sessionStorage.getItem("dstAirport"),
			d = new Date($("#yesterday").val());
		d.setTime(d.getTime()+24*60*60*1000);
		var yDay = d.getFullYear()+"-" + (d.getMonth()+1) + "-" + d.getDate();
		window.sessionStorage.setItem("date",yDay);
		$("#beforeToday").val(yDay);
		location.href="<%=basePath %>airLine/dosearch.action?orgAirportCode="+orgCode+"&dstAirportCode="+dstCode+"&date="+yDay;
	}
	
</script>
        
<!--底部开始-->
<%@ include file="/WEB-INF/view/inc/footer.jsp" %>
</body>
</html>
