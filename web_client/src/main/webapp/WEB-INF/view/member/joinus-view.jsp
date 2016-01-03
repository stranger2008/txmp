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
        <div class="settle"><a href="javascript:void(0);">入驻天下名品</a></div>
        <div class="nav-business">
            <ul>                                
                <li><a href="商家入驻流程.html">首页</a></li>
                <li class="nav-b-hover"><a href="<%=basePath %>member/joinus.action">商家入驻</a></li>
                <li><a href="#">帮助&资费 </a> </li>
                <li><a href="#">联系我们</a></li>
            </ul>
        </div>
    </div>
</div>
<div class="clearfix"></div>
<sf:form method="post" id="dataForm" action="member/joinus-progress.action" modelAttribute="member">
<div class="seller">
	<div class="seller-step">
		<div class="step-seepro">
			<img src="<%=basePath %>inc/member/images/seller-setp1.png" />
			<ul>
				<li class="fore-h">填写联系方式</li>
				<li class="fore">完善商家基本信息</li>
				<li class="fore">资料审核</li>
				<li class="fore">通知联系并签订合同</li>
				<li class="fore">办理后续手续，店铺上线</li>
			</ul>
		</div>
		<div class="seller-cont">
			<h1 class="seller-title">公司及联系方式：</h1>
			<ul class="seller-c-list">
				<li>
					<label>
						<span>*</span>联系人姓名：
					</label>
					<sf:input path="contact_name" />
					<sf:errors path="contact_name" cssClass="error"/>
				</li>
				<li>
					<label>
						<span>*</span>联系人电话：
					</label>
					<sf:input path="contact_phone" />
					<sf:errors path="contact_phone" cssClass="error"/>
				</li>
				<li>
					<label></label>
					<a href="javascript:void(0);" onclick="document.getElementById('dataForm').submit();" class="btn-huang" style="margin-top: 15px;">下一步</a>
				</li>
			</ul>
		</div>
	</div>
</div>
</sf:form>

<!--底部-->
<%@ include file="/WEB-INF/view/inc/footer.jsp"%>
</body>
</html>