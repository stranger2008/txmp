<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>我的资金账户</title>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="<%=rootPath %>css/public.css" />
<link rel="stylesheet" href="<%=rootPath %>css/product.css" />
<script src="<%=rootPath %>js/core.js"></script>
<script src="<%=rootPath %>js/account.js"></script>
</head>
  
<body>
<!--头部开始-->
<%@ include file="/WEB-INF/view/inc/top.jsp" %>
<!--充值页面-->
<div class="w1200">
    <div class="logo-sign fl">
        <a href="###"><img src="<%=basePath %>inc/membercenter/images/logo-sign.png"></a>
    </div>
    <div class="progress fr">
    	<ul class="progress2">                    
        	<li class="step">1 填写提现信息</li>
        	<li class="step-h">2 审核</li>
        	<li class="step">3 提现完成</li>
        </ul>
    </div>
</div>

<div class="clearfix"></div>

<!--订单提交成功-->
<div class="w1200">
    <div class="order-ok">
        <span class="order-o-icon"></span>
        <div class="order-msg">
            <div style="color: #5d5d5d;line-height: 24px;;">
                您已付款申请提现，正在为您审核。<br/>
                如遇问题请拨打天下名品客服电话：400-123-5555；010-87653255<br/>
                服务时间：周一至周日 0:00-24:00
            </div>
        </div>
    </div>
</div>
		
<div class="clearfix"></div>
<!--底部-->
<%@ include file="/WEB-INF/view/inc/footer.jsp" %>
</body>
</html>
