<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/view/outapi/airline/com/com.jsp" %>

<link rel="stylesheet" type="text/css"	href="<%=basePath %>/inc/outapi/airline/css/base.css" charset="utf-8">
<script type="text/javascript" src="<%=basePath %>/inc/outapi/airline/js/zepto.min.js"></script> 
<script type="text/javascript" src="<%=basePath %>/inc/outapi/airline/js/card.js"></script>

<title>机票预订-填写乘机人信息</title>
</head>

<body>
	<header>
		<div class="header margin-center">
	    	<div class="return-btn"><a href=""><img src="<%=basePath %>inc/outapi/airline/images/return.png" /></a></div><span class="header-txt2" onclick="javascript:location.href='<%=basePath %>passengers/passengerList.action'">返回</span>
	    </div>
	</header>
	<section>
	<form action="<%=basePath %>passengers/addPassenger.action" method ="post" id="searchArgs" name="searchArgs">
	<div class="airticket">
    <div class="arkt-tit"><h2>填写乘机人信息</h2></div>
	<div class="arkt-cont">
		<ul class="air-ul">
        	<li>
            	<div class="air-div">
                	<div class="tbl-type">
                        <span class="city tbl-cell">购票类型</span>
                        <div class="city-txt tbl-cell"><input type="radio" class="rdo" value="0" name="type" checked/><span class="rdo-ct">成人</span><input type="radio" class="rdo"  value="1" name="type"/><span class="rdo-ct">儿童</span></div>
                    </div>
                </div>
            </li>
        	<li>
            	<div class="air-div">
                	<div class="tbl-type">
                        <span class="city tbl-cell">真实姓名</span>
                        <div class="city-txt tbl-cell"><input type="text" class="city-ipt" id="psgname" name="name"   onblur="checkName()"   maxlength="30"><span class="star">*</span></div>
                    </div>
                </div>
            </li>
        	<li>
            	<div class="air-div">
                	<div class="tbl-type">
                        <span class="city tbl-cell">证件类型</span>
                        <div class="city-txt tbl-cell">
                        <select class="city-ipt" id="certtype" name="identityType">
                            <option value="1">身份证</option>
	                        <option value="2">护照</option>
							<option value="3">军官证</option>
	                        <option value="4">士兵证</option>
	                        <option value="5">台胞证</option>
	                        <option value="6">其他</option>
                        </select><span class="star2">*</span></div>	
                    </div>
                </div>
            </li>
        	<li>
            	<div class="air-div">
                	<div class="tbl-type">
                        <span class="city tbl-cell">证件号码</span>
                        <div class="city-txt tbl-cell">
                            <input type="text" class="city-ipt" id="certid" name="identityNo" placeholder="请输入证件号码" onblur="checkCertid()" maxlength="18" ><span class="star">*</span>
                        </div>	
                    </div>
                </div>
            </li>
        	<li><div align="left"> 
            	</div><div class="air-div">
                	<div class="tbl-type">
                        <span class="city tbl-cell">出生日期</span>
                         <div class="city-txt tbl-cell">
                              <div  id="checkInDate" class="srch_sel common-input" data-tag="checkIn">
	                             <span id="checkInShowVal" class="calendar" ></span>
	                        	</div>
	                             <div class="htl_cal" style="border:0px;" id="checkInDateDd">
										<input value="" name="birthday" id="checkInInputHiddenCalendar" type="hidden">
						 		 		<div id="checkInCalendar" class="dp" style="transform-origin: 0px 0px 0px; opacity: 1; transform: scale(1, 1); display: none;"></div>
		                     	</div>
		                     	<div id="psgnameError" class="txt13" style="display:block;color:red"></div>
                        </div>
                        
                    </div>
                </div>
            </li>
        	<li>
            	<div class="air-div">
                	<div class="tbl-type">
                        <span class="city tbl-cell">默认乘机人</span>
                        <div class="city-txt tbl-cell">
	                        <input type="radio" class="rdo"  id="rdo3" name="defaultbyair" value="0" checked="true"/>
	                        <span class="rdo-ct">是</span>
	                        <input type="radio" class="rdo"   id="rdo2" name="defaultbyair" value="1"/>
	                        <span class="rdo-ct">否</span>
                        </div>
                    </div>
                </div>
            </li>
        </ul>
        <div class="tbl-type">
            <div class="tbl-cell plr10"><input type="submit" class="sign_btn" value="确定" /></div>
            <div class="tbl-cell plr10"><input type="button" class="cal-btn" value="取消" onclick="javascript:location.href='<%=basePath %>passengers/passengerList.action'"/></div>
        </div>
    </div>
	</div> 
</form>
<script type="text/javascript" src="<%=basePath %>/inc/outapi/airline/js/calendar.js"></script> 
  		<script type="text/javascript">
		    //日期选择器
			var myDate = new Date();
		    var month = String(myDate.getMonth()+1);
		    month = month.length==1 ? "0"+month : month;
			new Calendar(true,true).init("checkIn","checkOut_First",6,"");
		</script>
  		 
		<script type="text/javascript">
			 //真实姓名
			function checkName(){
			  var psgname=$("#psgname").val().trim();
		        $("#psgnameError").html("");
				if(isNull(psgname)){
				   $("#psgnameError").html("真实姓名不能为空");
				   return false;
				}
				if((psgname.length)*2==psgname.replace(/[^\x00-\xff]/gi, "--").length){
		           if(psgname.length>10){
		     
				     $("#psgnameError").html("真实姓名长度错误");
		                return false;
		            }
		        }
		        else if(psgname.replace(/[^\x00-\xff]/gi, "--").length>26){
		
		           $("#psgnameError").html("真实姓名长度错误");
		            return false;
		        }
				
			    if(!psgname.match(/^[\u4e00-\u9fa5]{1,100}$|^[A-Za-z]+\/[A-Za-z]+( [A-Za-z]+)?$|^[\u4e00-\u9fa5]+[a-zA-Z]+$/)){
				   $("#psgnameError").html("真实姓名输写错误");
				   return false;
				}
				 return true;
			}
			
			//证件号验证
			function checkCertid() {
			    $("#certidError").html("");
			    var certtype = $("#certtype").val();
			    var certid = $("#certid").val().trim();
			
			    if (isNull(certid)) {
			        $("#certidError").html("证件号码不能为空");
			        return false;
			    }
			
			    if (certtype == "1") {
			        if (!isCardNo()) {
			            $("#certidError").html("身份证号码错误(只支持2代身份证)");
			            return false;
			        }
			        var isBirthday = checkBirthday(certid); //生日字段校验		
			        var isParity = checkParity(certid); //校验位校验
			        var isProvince = checkProvince(certid); //区域字段校验
			        if (! (isProvince && isBirthday && isParity)) {
			
			            $("#certidError").html("身份证号码错误");
			            return false;
			
			        }
			    }
			
			    if (certtype == "6") {
			        if (certid.length < 3) {　　$("#certidError").html("证件长度必须大于3");
			            return false;
			        }
			
			    }
			
			    if (certtype != "1" && certtype != "6" && !certid.match(/^[A-Za-z0-9]*$/)) {
			        $("#certidError").html("证号码错误,请输入英文和数字");
			        return false;
			    }
			  
			    return true;
		}
		
		
		//检查号码是否符合规范，包括长度，类型
		 function   isCardNo(){
			    var card=$("#certid").val().trim();
				//身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X
			    var reg = /(^\d{17}(\d|X|x)$)/;
			    if(reg.test(card) == false)
			    {
			        return false;
			    }
			
			    return true;
		}
		</script>
</section>
<%@ include file="/WEB-INF/view/inc/footer.jsp" %>
        
</body>
</html>
