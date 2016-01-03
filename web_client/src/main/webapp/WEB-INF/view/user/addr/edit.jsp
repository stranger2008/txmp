<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
  	<title>我的收货地址</title>
    <%@ include file="/WEB-INF/view/inc/inc.jsp" %>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" href="<%=rootPath %>css/public.css" />
    <link rel="stylesheet" href="<%=rootPath %>css/product.css" />
    <link rel="stylesheet" href="<%=rootPath %>css/account.css" />
    <script src="<%=rootPath %>js/jquery.min.js" type="text/javascript"></script>
    <script src="<%=rootPath %>js/core.js"></script>
    <script src="<%=rootPath %>js/account.js"></script>
    <style type="text/css">
    	.selectpsd{padding:4px 0;margin-top:5px;color:#666; border:1px solid #d7d7d7;}
    	.li_is_default{padding-left:78px;}
    </style>
    <script type="text/javascript">	
    	$(document).ready(function () {
        	var s = $("#is_default").val();
        	if(s=="1")
	        	document.getElementById("is_default_btn").checked = true;
	        else
	        	document.getElementById("is_default_btn").checked = false; 
    	});	
    	//	
		$(function () {
		    $("#is_default_btn").click(function () {
		    	var s = document.getElementById("is_default_btn").checked;
		        if (s == true) {
		            $("#is_default").val("1");
		        }
		        else {
		            $("#is_default").val("0");
		        }
		    });
		})
		//		
	</script>
  </head>
  <body>
<!--头部开始-->
<%@ include file="/WEB-INF/view/inc/top.jsp" %>
<!--头部下搜索框-->
<%@ include file="/WEB-INF/view/user/inc/top.jsp" %>		 
<!--nav开始-->
<%@ include file="/WEB-INF/view/user/inc/nav.jsp" %>		
<!--个人中心-->
<div class="w1200">
	<div class="usercenter">
    	<div class="position">
        	<a href="#"><strong>我的天下名品</strong></a><span></span><a href="#">账户安全</a>
        </div>
        <!--会员中心左边导航-->
		<%@ include file="/WEB-INF/view/user/inc/left.jsp" %>
				
        <div class="user-info fr">
            <div class="add-count">
                <h1>修改收货地址</h1>
               <!--修改收货地址-->
				<div id="add-address">
				<sf:form method="post" action="user/updateBuyerAddr.action" modelAttribute="buyeraddr">
				    <div class="account-add-site" style="padding-top:10px;">
				        <ul>
				            <li>
				                <span>*</span>&nbsp;&nbsp;收货人：
				                <sf:hidden path="addr_id"/>
				                <sf:input path="cust_name" maxlength="20"/>
				                <em><sf:errors path="cust_name" cssClass="error"/></em>
				            </li>
				            <li>
				                <span>*</span>所在地区：
				                <span id="selectDivModel"></span>
				            	<script type="text/javascript">
									showSelectCascade("<%=basePath %>","area","selectDivModel","area_attr","${buyeraddr.area_attr}");
									$("select[name='select_area_attr']").addClass("selectpsd");
								</script>
				                <em><sf:errors path="area_attr" cssClass="error"/></em>
				            </li>
				            <li>
				                <span>*</span>详细地址：
				                 <sf:input path="address" style="width:350px;" maxlength="100"/><em><sf:errors path="address" cssClass="error"/></em>
				            </li>
				            <li>
				                <span>*</span>手机号码：
				               <sf:input path="cell_phone" maxlength="15"/><em><sf:errors path="cell_phone" cssClass="error"/></em>
				            </li>
				            <li class="li_is_default">
				                <input id="is_default_btn" type="checkbox"/>&nbsp;设为默认
				                <input name="is_default" id="is_default" type="hidden" value="${buyeraddr.is_default}"/>
				            </li>
				            <li>
				                <button type="submit" style="padding:3px 15px;margin:20px 0 30px 80px;"/>提交</button>
				            </li>
				        </ul>
				    </div>
				</sf:form>
				</div>
            </div> 
        </div>
	</div>
</div>

<div class="clearfix"></div>
<!--底部-->
<%@ include file="/WEB-INF/view/inc/footer.jsp" %>
  </body>
</html>
