<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>安全中心</title>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="<%=rootPath %>css/public.css" />
<link rel="stylesheet" href="<%=rootPath %>css/product.css" />
<link rel="stylesheet" href="<%=rootPath %>css/account.css" />
<script src="<%=rootPath %>js/jquery.min.js" type="text/javascript"></script>
<script src="<%=rootPath %>js/core.js"></script>
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
			<a href="#"><strong>安全中心</strong></a><span></span><a href="#">安全中心</a>
		</div>
        <!--会员中心左边导航-->
		<%@ include file="/WEB-INF/view/user/inc/left.jsp" %>
                <div class="user-info fr">
            <!--安全中心-->
            <div class="account-voucher">
                <h1 class="store-title">安全中心</h1>
                <p class="account-safety-score account-safety-score2">您的安全服务：<span>中40分<em></em></span></p>
                <div class="account-safety">
                    <div class="account-safety-item">
                        <span class="account-s-i-left">
                            <em class="account-safety-ok"></em>
                            <strong>登录密码</strong>
                        </span>
                        <div class="account-s-i-msg">
                            <table width="100%" cellpadding="0" cellspacing="0" border="0">
                                <tr>
                                    <td style="color: red" width="612" valign="middle">互联网账号存在被盗风险，安全性高的密码可以使账号更安全。建议您定期更换密码，且设置一个包含数字和字母，并长度超过6位以上的密码。
                                    </td>
                                    <td align="center">
                                        <a href="<%=basePath %>user/updatePwSendPhone.action" class="a-lan">修改</a>
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </div>
                    <div class="account-safety-item">
                        <span class="account-s-i-left">
                            <em class="account-safety-ok"></em>
                            <strong>绑定手机</strong>
                        </span>
                        <div class="account-s-i-msg">
                            <table width="100%" cellpadding="0" cellspacing="0" border="0">
                                <tr>
                                    <td width="612" valign="middle">您验证的手机： ${cellphone }   若已丢失或停用，请立即更换，避免账户被盗</td>
                                    <td align="center">
                                        <a class="a-lan" href="<%=basePath %>safetycenter/bindmobile.action">修改</a>
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </div>
                    <div class="account-safety-item">
                        <span class="account-s-i-left">
                            <em class="account-safety-ok"></em>
                            <strong>绑定邮箱</strong>
                        </span>
                        <div class="account-s-i-msg">
                            <table width="100%" cellpadding="0" cellspacing="0" border="0">
                                <tr>
                                    <td valign="middle" width="612">验证后，可用于快速找回登录密码，接收账户余额变动提醒等</td>
                                    <td align="center">
                                        <a class="a-lan">修改</a>
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </div>
                    <div class="account-safety-item">
                        <span class="account-s-i-left">
                            <em class="account-safety-ok"></em>
                            <strong>支付密码</strong>
                        </span>
                        <div class="account-s-i-msg">
                            <table width="100%" cellpadding="0" cellspacing="0" border="0">
                                <tr>
                                    <td valign="middle" width="612">启用支付密码后，在使用账户资产进行提现，支付等操作时，需通过支付密码进行身份认证。</td>
                                    <td align="center">
                                        <a class="a-lan">修改</a>
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </div>
                    <div class="account-safety-item">
                        <span class="account-s-i-left">
                            <em class="account-safety-warn"></em>
                            <strong>密码保护</strong>
                        </span>
                        <div class="account-s-i-msg">
                            <table width="100%" cellpadding="0" cellspacing="0" border="0">
                                <tr>
                                    <td valign="middle" width="612">密保问题是由您自己选定的3个问题及对应答案组成。密保问题的范围是您个人的私有信息，其他人一般无法回答，
                                        如：父母的生日、您的出生地等。为了避免遗忘，在填写问题答案时请您尽量填写真实信息。（注：天下名品会对
                                        您的个人信息保密。）</td>
                                    <td align="center">
                                        <a class="go-trade"><span></span>立即启用</a>
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            <div class="account-cust" style="margin: 25px 0 0">
                <h6 class="account-cust-title">常见问题</h6>
                <div class="account-safety-faq">
                    <a class="a-lan">1.如何设置和开启支付密码？</a>
                    <a class="a-lan">1.如何设置和开启支付密码？</a>
                    <a class="a-lan">1.如何设置和开启支付密码？</a>
                    <a class="a-lan">1.如何设置和开启支付密码？</a>
                    <a class="a-lan">1.如何设置和开启支付密码？</a>
                    <a class="a-lan">1.如何设置和开启支付密码？</a>
                </div>
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
