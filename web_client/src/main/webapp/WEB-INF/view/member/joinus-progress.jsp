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
                <li class="nav-b-hover"><a href="<%=basePath %>member/joinus-step0.action">商家入驻</a></li>
                <li><a href="#">帮助&资费 </a> </li>
                <li><a href="#">联系我们</a></li>
            </ul>
        </div>
    </div>
</div>
<div class="clearfix"></div>
<div class="seller">
	<div class="seller-step">
	<div style="color:red; text-align:center; font-size:20px; font-family: '微软雅黑';">
		<span>
			<c:if test="${member.audit_status == null }">
				未查询到入驻申请信息,请检查联系人和联系电话
				&nbsp;
				<a href="<%=basePath %>member/joinus-view.action" style="color:black; font-size:14px;">返回</a>
			</c:if>
			<c:if test="${member.audit_status == '0' }">
				审核进行中
			</c:if>
			<c:if test="${member.audit_status == '2' }">
				审核不通过,原因:${member.no_reason }
			</c:if>
			<c:if test="${member.audit_status == '1' }">
				恭喜您,审核通过!
			</c:if>
		</span>
	</div>
		<div class="step-seepro">
			<img src="<%=basePath %>inc/member/images/step-seepro.png" />
				<ul>
					<li class="fore">填写联系方式</li>
					<li class="fore">完善商家基本信息</li>
					<li class="fore-h">资料审核</li>
					<li class="fore">通知联系并签订合同</li>
					<li class="fore">办理后续手续，店铺上线</li>
				</ul>
			</div>
			<div class="seller-cont2">
			<div class="step-seepro">
				<ul>
					<li>&nbsp;</li>
					<li>&nbsp;</li>
					<li>
						<div class="seepro-p">
							<p>天下名品将在7个工作日内给到审核结果。如提交资质未通过审核，请联系400-680-9955。</p>
						</div>
					</li>
					<li>
						<div class="seepro-p">
							<p>审核通过后，天下名品将有专人和您联系签订合作协议的相关事宜。</p>
						</div>
					</li>
					<li><div class="seepro-p"><p>1. 冻结保证金，缴纳技术服务年费 </p><p>2. 发布商品，店铺上线</p></div></li>
				</ul>
			</div>
		</div>
	</div>
</div>

<!--底部-->
<%@ include file="/WEB-INF/view/inc/footer.jsp"%>
</body>
</html>