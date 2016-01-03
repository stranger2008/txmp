<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>我的资金账户</title>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
<link rel="stylesheet" href="<%=basePath %>inc/fundaccount/css/recharge.css" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
  
<body>
<!--充值页面-->
<div>
    <div class="progress fr">
    	<ul class="progress1">                    
        	<li class="step-h">1 填写充值金额</li>
        	<li class="step">2 在线支付</li>
        	<li class="step">3 充值完成</li>
        </ul>
    </div>
</div>
<div class="clearfix"></div>
<div class="w1200 recharge">
    <div class="recharge-cont">
    	<div class="recharge-thead">
			填写充值金额
        </div>
        <sf:form action="fundaccount/recharge.action" method="post" id="queryForm" modelAttribute="fundrecharge">
        <div class="recharge-ul">
            <ul>
                <li><span>充值账户：</span><i><sf:hidden path="account_no" />${fundrecharge.account_no }</i></li>
                <li><span>充值金额：</span><sf:input path="fund_num" cssClass="recharge-txt" /><sf:errors path="fund_num" cssStyle="width:auto;color:red;" /></li>
                <li><span>&nbsp;</span><em>请注意：您的充值金额超出部分银行单笔支付额度，建议每次充值小于499元</em></li>
                <li><span>&nbsp;</span><input type="radio" checked="checked" /><label>银联支付</label></li>
                <li><span>&nbsp;</span><a class="btn-red" onclick="document.getElementById('queryForm').submit();"><span style="width: 15px"></span>下一步</a></li>
            </ul>
		</div>
		</sf:form>
    </div>
</div>
</body>
</html>
