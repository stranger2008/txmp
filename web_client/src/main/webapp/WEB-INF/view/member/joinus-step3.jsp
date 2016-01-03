<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/WEB-INF/view/inc/inc.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>商家入驻</title>
<link rel="stylesheet" href="<%=basePath%>inc/css/public.css" />
<link rel="stylesheet" href="<%=basePath%>inc/css/account.css" />
<link rel="stylesheet" href="<%=basePath%>inc/member/css/business.css" />
</head>
<body>
<!--头部开始-->
<%@ include file="/WEB-INF/view/inc/top.jsp"%>

<!--商家入驻流程-->
<div class="border-b">
    <div class="w1200" style="position:relative;">
        <div class="logo-business">
            <a href="###"><img src="<%=basePath %>inc/images/logo-shop.png"></a>
        </div>
        <div class="settle"><a href="#">入驻天下名品</a></div>
        <div class="nav-business">
            <ul>                                
                <li><a href="商家入驻流程.html">首页</a></li>
                <li class="nav-b-hover"><a href="javascript:void(0);">商家入驻</a></li>
                <li><a href="#">帮助&资费 </a> </li>
                <li><a href="#">联系我们</a></li>
            </ul>
        </div>
    </div>
</div>
<div class="clearfix"></div>
<div class="seller">
	<div class="seller-step">
		<div class="step-seepro">
			<img src="<%=basePath %>inc/member/images/seller-setp3.png" />
			<ul>
				<li class="fore">填写联系方式</li>
				<li class="fore">完善商家基本信息</li>
				<li class="fore-h">资料审核</li>
				<li class="fore">通知联系并签订合同</li>
				<li class="fore">办理后续手续，店铺上线</li>
			</ul>
		</div>
		<div class="seller-cont">
			<div style="text-align:center;">
				<div class="seller-cue">
					<strong>恭喜您！资料已经提交审核！天下名品将在7个工作日内审核完毕！请将您联系电话保持畅通~</strong>
					<p>（如提交资质未通过审核，请在15个工作日内完成修改并提交，逾期未提交，申请状态将被重置。）</p>
				</div>
				<a href="<%=basePath %>member/joinus-view.action" class="btn-huang">查看进度</a>
			</div>
		</div>
	</div>
</div>

<!--底部-->
<%@ include file="/WEB-INF/view/inc/footer.jsp"%>
</body>
</html>