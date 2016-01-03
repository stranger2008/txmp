<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>

<%@ include file="/WEB-INF/view/outapi/airline/com/com.jsp" %>
<script type="text/javascript" src="<%=basePath %>inc/com/selectCascade/selectlink.js"></script>
</head>
<body>
	
<!--头部开始--> 
    <header>
	<div class="header margin-center">
    	<div class="return-btn"><a href="<%=basePath %>journeysheet/contactList.action"><img src="<%=basePath %>inc/outapi/airline/images/return.png" /></a></div><span class="header-txt2" onclick="javascript:location.href='<%=basePath %>journeysheet/toJourneysheet.action'">返回</span>
    </div>
</header>
<section>
	<form action="<%=basePath %>journeysheet/insertJourneysheet.action" method ="post" id="searchArgs" name="searchArgs">
	<div class="airticket">
    <div class="arkt-tit"><h2>填写行程单</h2></div>
	<div class="arkt-cont">
		<ul class="air-ul">
        
        	<li>
            	<div class="air-div">
                	<div class="tbl-type">
                        <span class="city tbl-cell">姓 名</span>
                        <div class="city-txt tbl-cell"><input type="text" class="city-ipt" name ="name" id="name" value="${js.name}"><span class="star">*</span>
                        <input type="hidden" name ="journeysheet_id" id="journeysheet_id" value="${js.journeysheet_id}"/></div>
                    </div>
                </div>
            </li>
        	<li>
            	<div class="air-div">
                	<div class="tbl-type">
                        <span class="city tbl-cell">手机号码</span>
                        <div class="city-txt tbl-cell"><input type="text" class="city-ipt" name ="phone" id="phone" value="${js.phone}"><span class="star">*</span></div>
                    </div>
                </div>
            </li>
             <li>
            	<div class="air-div">
                	<div class="tbl-type">
                        <span class="city tbl-cell">收货地址</span>
                         <div class="city-txt tbl-cell">
                       	 	<span id="selectDivModel"></span>
                       		<script type="text/javascript">
								showSelectCascade("<%=basePath %>","area","selectDivModel","area_attr","${js.area_attr}");
								$("select[name='select_area_attr']").addClass("city-ipt");
								$("select[name='select_area_attr']").height("40px")
							</script>
  							</div>
	  				</div>
                </div>
            </li>
              <li>
            	<div class="air-div">
                	<div class="tbl-type">
                        <span class="city tbl-cell">详细地址</span>
                        <div class="city-txt tbl-cell"><input type="text" class="city-ipt"  name ="address" id="address" value="${js.address}"/><span class="star">*</span></div>
                    </div>
                </div>
            </li>
            <li>
            	<div class="air-div">
                	<div class="tbl-type">
                        <span class="city tbl-cell">邮政编码</span>
                        <div class="city-txt tbl-cell"><input type="text" class="city-ipt"  name ="zipcode" id="zipcode" value="${js.zipcode}"><span class="star">*</span></div>
                    </div>
                </div>
            </li>
             <li>
            	<div class="air-div">
                	<div class="tbl-type">
                        <span class="city tbl-cell">固定电话</span>
                        <div class="city-txt tbl-cell"><input type="text" class="city-ipt"    name ="tel" id="tel" value="${js.tel}"></div>
                    </div>
                </div>
            </li>
             <li>
            	<div class="air-div">
                	<div class="tbl-type">
                        <span class="city tbl-cell">是否默认</span>
                        <div class="city-txt tbl-cell">
	                        <input type="radio" class="rdo" id="rdo3" name="distributioninfo" class="rdo" value="0" checked="true"/>
	                        <span class="rdo-ct">是</span>
	                        <input type="radio" class="rdo"   id="rdo4" name="distributioninfo" class="rdo" value="1"/>
	                        <span class="rdo-ct">否</span>
                        </div>
                    </div>
                </div>
            </li>
        	
        </ul>
        <div class="tbl-type">
            <div class="tbl-cell plr10"><input type="submit" class="sign_btn" value="确定" /></div>
            <div class="tbl-cell plr10"><input type="button" class="cal-btn" value="取消" onclick="javascript:location.href='<%=basePath %>journeysheet/toJourneysheet.action'"/></div>
        </div>
    </div>
	</div> 
</form>
</section>
<%@ include file="/WEB-INF/view/inc/footer.jsp" %>
</body>
</html>
