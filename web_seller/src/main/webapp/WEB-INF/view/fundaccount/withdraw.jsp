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
        	<li class="step-h">1 填写提现金额</li>
        	<li class="step">2 提现信息</li>
        	<li class="step">3 提现完成</li>
        </ul>
    </div>
</div>
<div class="clearfix"></div>
<div class="w1200 recharge">
    <div class="recharge-cont">
    	<div class="recharge-thead">
			填写提现金额
        </div>
        <sf:form action="fundaccount/withdraw.action" method="post" id="queryForm" modelAttribute="fundwithdraw">
        <div class="recharge-ul">
            <ul>
                <li><span>资金账号：</span><sf:hidden path="account_no"/><i>${fundwithdraw.account_no }</i></li>
                <li><span>提现金额：</span><sf:input path="trans_amt"/><sf:errors path="trans_amt" cssStyle="width:auto;color:red;" /><em>请注意：你最多可提现<sf:hidden path="fund_use_num"/>${fundwithdraw.fund_use_num }元</em></li>
                <li><span>收款卡号：</span><sf:input path="card_no"/><sf:errors path="card_no" cssStyle="width:auto;color:red;" /></li>
                <li><span>收款人：</span><sf:input path="usr_name"/><sf:errors path="usr_name" cssStyle="width:auto;color:red;" /></li>
                <li><span>开户银行：</span><sf:select path="open_bank" items="${chinapay_banks }"/><sf:errors path="open_bank" cssStyle="width:auto;color:red;" /></li>
                <li><span>开户支行：</span><sf:input path="sub_bank"/><sf:errors path="sub_bank" cssStyle="width:auto;color:red;" /></li>
                <li><span>开户省份：</span><sf:input path="prov"/><sf:errors path="prov" cssStyle="width:auto;color:red;" /></li>
                <li><span>开户城市：</span><sf:input path="city"/><sf:errors path="city" cssStyle="width:auto;color:red;" /></li>
                <li><span>&nbsp;</span><a class="btn-red" onclick="document.getElementById('queryForm').submit();"><span style="width: 15px"></span>确定</a></li>
            </ul>
		</div>
		</sf:form>
    </div>
</div>
</body>
</html>
