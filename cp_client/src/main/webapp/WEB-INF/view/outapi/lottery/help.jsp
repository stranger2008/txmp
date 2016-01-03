<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/view/outapi/lottery/inc.jsp" %>
<title>天下名品彩票</title>
</head>

<body>

<%@ include file="/WEB-INF/view/outapi/lottery/top.jsp" %>

<section>
    <div class="lot-bg">
        <div class="lottery">
            <div class="lottery-tit">
            	<ul class="tbl-type">
                	<li class="tbl-cell"><a href="<%=basePath %>lottery/index.action">购彩大厅</a></li>
                	<li class="tbl-cell"><a href="<%=basePath %>lottery/notice.action">开奖公告</a></li>
                	<li class="tbl-cell"><a href="<%=basePath %>lottery/desc.action">彩种介绍</a></li>
                	<li class="lot-hover tbl-cell"><a href="<%=basePath %>lottery/help.action">领奖规则</a></li>
                </ul>
            </div>
            <div class="lott-bar">
            	
                <!--彩种介绍-->
                <div class="lottery-cont clearfix">
                    <ul>
                        <li style="height:auto;padding:10px 0px;">
                            <div class="lott-info" style="padding-left:10px;">
                                <p style="line-height:26px;">1、当订单累计奖金≤￥200时，系统自动派发至余额；</p>
								<p style="line-height:26px;">2、当￥200&lt;订单累计奖金≤￥1000时，用户在两种领奖方式中任选其一（可以派发至余额，也可以派发至用户指定的实名身份对应的银行卡中）。</p>
								<p style="line-height:26px;">3、当￥1000&lt;订单累计奖金&lt;￥10000时，派发至用户指定的实名身份对应的银行卡中。</p>
								<p style="line-height:26px;">4、当￥1万≤单注奖金＜￥100万时，需中奖者按彩票发行中心要求填写委托函及身份证复印委托天下名品代为领奖，天下名品直接把中奖奖金扣税后发放到中奖者在授权委托书中指定的银行账户中。</p>
								<p style="line-height:26px;">5、即当单注奖金≥￥100万时，天下名品工作人员陪同中奖本人亲自到彩票发行中心指定兑奖处进行兑奖。</p>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
        </div> 

    </div>
    
</section>
    
<%@ include file="/WEB-INF/view/inc/footer.jsp" %>

</body>
</html>
