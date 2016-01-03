<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<%@ include file="/WEB-INF/view/outapi/airline/com/com.jsp"%>
		<script type="text/javascript" src="<%=basePath%>inc/outapi/airline/js/airlinecompany.js"></script>
		<title>机票预订-填写订单</title>
	</head>

	<body>
		<!--头部开始-->
		<header>
		<div class="header margin-center">
			<div class="return-btn">
				<a href=""><img src="<%=basePath%>/inc/outapi/airline/images/return.png" /> </a>
			</div>
			<span class="header-txt2" onclick="javascript:reSearch();">航班列表</span>
			<div class="header-btn">
				<a href="javascript:reSearch();"><img src="<%=basePath%>/inc/outapi/airline/images/home.png" /> </a>
			</div>
		</div>
		</header>

		<section id="section">
		<div class="airticket">
			<div class="arkt-tit">
				<h2>
					填写订单信息
				</h2>
			</div>
			<div class="arkt-cont">
				<table class="p-top" cellpadding="0" cellspacing="0" width="100%">
		            <tr>
		                <td align="center"><span class="p-top-spn">${ws.depDate} <script>document.write( window.sessionStorage.getItem("orgAirportCode"))</script>-<script>document.write( window.sessionStorage.getItem("dstAirportCode"))</script></span></td>
		            </tr>
		        </table>
				<div class="air-info">
					<span><img src="<%=basePath%>inc/outapi/airline/images/${fn:substring(ws.flightNo,0,2)}.gif" /> </span>
					<span class="ft-id"> 
						<script type="text/javascript">
	              			document.write(company['${fn:substring(ws.flightNo,0,2)}']); 
	             		 </script> ${ws.flightNo} 
	             	</span>
					<br />
					<span><img src="<%=basePath%>/inc/outapi/airline/images/air-icon1.png" /> </span>
					<span> ${fn:substring(ws.depTime,0,2)}:${fn:substring(ws.depTime,2,4)} ${airPort.dstAirPort } ${wwc.orgJetquay} 航站楼</span>
					<br />
					<span><img src="<%=basePath%>/inc/outapi/airline/images/air-icon2.png" /> </span>
					<span>${fn:substring(ws.arrTime,0,2)}:${fn:substring(ws.arrTime,2,4)} ${airPort.arrAirPort } ${wwc.dstJetquay} 航站楼</span>
					<br />
					<span>机建/燃油：<span class="red-p">￥ ${aqf.airportTax}/ ￥${aqf.fuelTax}</span> <br /> <span>名品价：<span class="red-p">￥${aqf.parPrice}</span> <br />
				</div>
				
				<form action="airLine/submitOrder.action" method="post" id="subOrder">
					<input type="hidden" name="orgCity" id="orgCity" value="" />
					<input type="hidden" name="dstCity" id="dstCity" value="" />
					<input type="hidden" name="flightNo" value="${ws.flightNo}" />
					<input type="hidden" name="date" value="${ws.depDate}" />
					<input type="hidden" name="depTime" value="${fn:substring(ws.depTime,0,2)}:${fn:substring(ws.depTime,2,4)}" />
					<input type="hidden" name="arriTime" value="${fn:substring(ws.arrTime,0,2)}:${fn:substring(ws.arrTime,2,4)} " />
					<input type="hidden" name="is_need_nvoice" id="is_need_nvoice" value="${jsheet}" />
					<ul class="air-ul">
						<li>
							<a onclick="javascript:location.href='<%=basePath%>passengers/passengerList.action'" class="air-a tbl-type"> <span class="city tbl-cell">乘机人</span>
								<div class="city-txt tbl-cell">
									<span class="city-ipt" style="display: inline-block"><c:forEach items="${passengers}" var="pa">${pa.name}&nbsp;</c:forEach> </span><span class="arr arr2"></span>
								</div> </a>
						</li>

						<li>
							<a onclick="javascript:location.href='<%=basePath%>contact/contactList.action'" class="air-a tbl-type"> <span class="city tbl-cell">联系人</span>
								<div class="city-txt tbl-cell">
									<span class="city-ipt" style="display: inline-block">${contacts.linkman }</span><span class="arr arr2"></span>
								</div> </a>
						</li>

						<li>
							<a onclick="javascript:location.href='<%=basePath%>journeysheet/toJourneysheet.action'" class="air-a tbl-type"> <span class="city tbl-cell">配 送</span>
								<div class="city-txt tbl-cell">
									<span class="city-ipt" style="display: inline-block"><c:if test="${jsheet==1}">需要行程单</c:if> <c:if test="${jsheet==0}">不需要行程单</c:if> </span><span class="arr arr2"></span>
								</div> </a>
						</li>

					</ul>
					<p class="m10" style="margin-bottom: 30px;">
						<input type="submit" id="subOrder" class="sign_btn" value="提交订单" />
					</p>
				</form>
			</div>
		</div>

		<script type="text/javascript">
	$("#orgCity").val(window.sessionStorage.getItem("orgAirportCode"));
	$("#dstCity").val(window.sessionStorage.getItem("orgAirportCode"));
	function reSearch(){
		var depCity = window.sessionStorage.getItem("orgAirportCode");
				if(testSessionStorage()  && depCity!=""){
		    			var airStr = '<form action="airLine/dosearch.action" method="post" id="reseach">'
						        +'<input type="hidden" name="sid"  value="98fbda6b33136efe054f65e14ca58989"/>'
				 			 	+'<input type="hidden" name="orgAirportCode" value="'+ window.sessionStorage.getItem("orgAirport") +'"/>'
					        	+'<input type="hidden" name="dstAirportCode" value="'+ window.sessionStorage.getItem("dstAirport") +'"/>'
						        +'<input type="hidden" name="date" value="'+ window.sessionStorage.getItem("date") +'"/>'
						        +'</form>';
					    	$("#section").append(airStr);
					    	$("#reseach").submit();
				}
		}
	
	$("form").submit(function(e){
		 if('${passengers}'!=''){
		 	 $("#subOrder").submit()
		 }else{
		 	 alert('请选择乘机人！');
		 	 return false;
		 }
	});
	
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
		<%@ include file="/WEB-INF/view/inc/footer.jsp"%>

	</body>
</html>
