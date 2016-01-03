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
                <li class="nav-b-hover"><a href="商家入驻流程1.html">商家入驻</a></li>
                <li><a href="#">帮助&资费 </a> </li>
                <li><a href="#">联系我们</a></li>
            </ul>
        </div>
    </div>
</div>
<div class="clearfix"></div>
<div class="w1200">
	<div class="agreement">
		<div class="agreement-cont">
			<h2>天下名品商家入驻协议</h2>
			<div class="agreement-p">
            替代文字1、开店资格条件判断：<br />
            阿里巴巴工作人员无法创建淘宝店铺<br />
            一个身份证只能创建一个淘宝店铺<br />
            同账户如创建过U站或其他站点则无法创建淘宝店铺，可更换账户开店<br />
            同账户如创建过天猫店铺则无法创建淘宝店铺，可更换账户开店<br />
            同账户如在1688有过经营行为（发过供应产品信息、下单订购诚信通服务、卖家发起订单、报价、下单订购实地认证、开通旺铺、企业账户注册入口注册的企业账户）则无法创建淘宝店铺，可更换账户开店
            淘宝账户如果违规被淘宝处罚永久禁止创建店铺，则无法创建淘宝店铺<br />
            2、店铺创建成功后会出现的三种店铺异常规则：<br />
            A、出售中的宝贝数量连续3周为0件，系统会发送旺旺及邮件提醒您“宝贝数量连续3周为0件，必须发布宝贝，否则您的店铺将有可能暂时释放”；<br /> 
            B、出售中的宝贝数量连续5周为0件，店铺会暂时释放，此时点击“查看我的店铺”店铺不能正常显示；系统会发送旺旺及邮件告诉您“店铺已经暂时释放，但是我们将为您的店铺名保留一周，请任意发布一件闲置宝贝或上架仓库中的宝贝，24小时后，店铺即可恢复之前开店状态”， 店铺激活后店铺信誉、装修、订购的服务等都不受影响；<br />
            C、出售中的宝贝数量连续6周为0件，店铺会彻底释放，系统会发送旺旺及邮件告诉您“店铺已经彻底释放，任何人都可以申请并使用您的店铺名称”。您需要登陆卖家中心操作激活店铺，按照提示完成指定操作，店铺就可重新开张。店铺激活后店铺信誉、装修、订购的服务等都不受影响；<br />
            3、天下名品开店认证注意事项：<br />
            当您完成支付宝实名认证操作之后，请返回淘宝开店页面，点击“立即认证”后，您会进入淘宝开店认证的页面，请仔细阅读<br />
            替代文字1、开店资格条件判断：<br />
            阿里巴巴工作人员无法创建淘宝店铺<br />
            一个身份证只能创建一个淘宝店铺<br />
            同账户如创建过U站或其他站点则无法创建淘宝店铺，可更换账户开店<br />
            同账户如创建过天猫店铺则无法创建淘宝店铺，可更换账户开店<br />
            同账户如在1688有过经营行为（发过供应产品信息、下单订购诚信通服务、卖家发起订单、报价、下单订购实地认证、开通旺铺、企业账户注册入口注册的企业账户）则无法创建淘宝店铺，可更换账户开店<br />
            淘宝账户如果违规被淘宝处罚永久禁止创建店铺，则无法创建淘宝店铺<br />
            2、店铺创建成功后会出现的三种店铺异常规则：<br />
            A、出售中的宝贝数量连续3周为0件，系统会发送旺旺及邮件提醒您“宝贝数量连续3周为0件，必须发布宝贝，否则您的店铺将有可能暂时释放”；<br /> 
            B、出售中的宝贝数量连续5周为0件，店铺会暂时释放，此时点击“查看我的店铺”店铺不能正常显示；系统会发送旺旺及邮件告诉您“店铺已经暂时释放，但是我们将为您的店铺名保留一周，请任意发布一件闲置宝贝或上架仓库中的宝贝，24小时后，店铺即可恢复之前开店状态”， 店铺激活后店铺信誉、装修、订购的服务等都不受影响；<br />
            C、出售中的宝贝数量连续6周为0件，店铺会彻底释放，系统会发送旺旺及邮件告诉您“店铺已经彻底释放，任何人都可以申请并使用您的店铺名称”。您需要登陆卖家中心操作激活店铺，按照提示完成指定操作，店铺就可重新开张。店铺激活后店铺信誉、装修、订购的服务等都不受影响；<br />
            3、天下名品开店认证注意事项：<br />
            当您完成支付宝实名认证操作之后，请返回淘宝开店页面，点击“立即认证”后，您会进入淘宝开店认证的页面，请仔细阅读
			</div>
		</div>
		<div class="agreement-btns"><a href="<%=basePath %>member/joinus-step1.action?agree=1" class="agreement-btn-y">同意</a><a href="<%=basePath %>index.action" class="agreement-btn-n">不同意</a></div>
	</div>
</div>


<!--底部-->
<%@ include file="/WEB-INF/view/inc/footer.jsp"%>
</body>
</html>