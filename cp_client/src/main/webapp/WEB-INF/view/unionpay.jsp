<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
<!DOCTYPE html>
<!-- saved from url=(0115)https://pay.m.jd.com/checkstand/success.action?sid=38d8caf659cb5284a52218a6c801dd94&orderId=1783308732&orderKind=36 -->
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
		<!--meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0;"/-->
	<title>天下名品收银台</title>
	<meta name="keywords" content="天下名品收银台 – 触屏版">
	<meta name="description" content="天下名品收银台 – 触屏版">
	<meta name="msapplication-tap-highlight" content="no">
	<meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">
	<link rel="stylesheet" type="text/css" href="<%=basePath %>inc/outapi/lottery/css/base2013.src.css" charset="utf-8">
	<link rel="stylesheet" type="text/css" href="<%=basePath %>inc/outapi/lottery/css/pay.css" charset="utf-8">
	<link rel="stylesheet" type="text/css" href="<%=basePath %>inc/outapi/lottery/css/doubleselect.css">
	<script type="text/javascript" src="<%=basePath %>inc/outapi/lottery/js/jdmobile.src.js"></script>
	<script type="text/javascript" src="<%=basePath %>inc/outapi/lottery/js/exportselect.js"></script>
<body>
	<header>
        <div class="new-header" id="newHeaderPayId">
            <h2>天下名品收银台</h2>
            <a href="" class="btn-myjd"><span>我的</span></a>
            <a href="" class="btn-home" style="right:40px"><span>首页</span></a>
        </div>
     </header>
<div class="new-ct pay">
	<div class="msg-line" style="padding-bottom:10px;">
        <div class="msg-tip">
            <p class="new-txt-rd2">
              <c:if test="${rspCode==-1}">
              		支付取消，请重新支付!
              </c:if>
              <c:if test="${rspCode==0}">
              		支付成功！
              </c:if>
            </p>
        </div>        
	</div>
    <div class="order-info2">
    </div>
    <!--[D] 默认时加  new-abtn-default 把a标签换成span-->
	    	<a href="http://m.jd.com/index.html?sid=38d8caf659cb5284a52218a6c801dd94" class="new-abtn-type new-mg-t30">返回首页</a>
	</div>

<script type="text/javascript">

    var viewAppSuccessPage = function(){ // for apple (处理手机客户端屏幕下方回退按钮，只针对支付成功页面)
        return "closePaySuccessPage";
    }

    var client = '';
    if (jdm.isNotBlank(client) && client == 'android') { // for android (处理手机客户端屏幕下方回退按钮，只针对支付成功页面)
        if (window.JdAndroid) {
            window.JdAndroid.setPayCompleted();
        }
    }

</script>

<script type="text/javascript">
var _loadScript = function(url, options,cb){
	var script = document.createElement("script");
	var def = {
		type: "text/javascript",
		charset:"utf-8"
	}
	options= options|| {
	}
	for(var i in options){
		def[i] = options[i];
	}
	script.src = url;
	
	for(var i in def){
		script.setAttribute(i,def[i]);
	}
	script.addEventListener("load",function(){
		cb && cb();
	},false)
	document.getElementsByTagName("head")[0].appendChild(script);
}
</script>
</body>
</html>