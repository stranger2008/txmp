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
                	<li class="lot-hover tbl-cell"><a href="<%=basePath %>lottery/desc.action">彩种介绍</a></li>
                	<li class="tbl-cell"><a href="<%=basePath %>lottery/help.action">领奖规则</a></li>
                </ul>
            </div>
            <div class="lott-bar">
            	
                <!--彩种介绍-->
                <div class="lottery-cont clearfix">
                    <ul>
                        <li><a href="#">
                            <div class="lott-img fl"><img src="<%=basePath %>inc/outapi/lottery/images/happy8.jpg" /></div>
                            <div class="lott-info">
                                <h3>快乐8</h3>
                                <p>快乐8采用组合式玩法，投注者从1至80个号码中任意选择1至10个号码进行投注，每一组1个至10个号码的组合称为一注彩票。开奖时从1至80的号码中摇出20个号码。 快乐8有10种玩法。其中“选十”玩法采用浮动奖金设奖，其它玩法采用固定奖金设奖，按所设奖金等级兑奖。
                                </p>
                            </div>
                            <span class="arr-lott"></span>
                            </a>
                        </li>
                        <li><a href="#">
                            <div class="lott-img fl"><img src="<%=basePath %>inc/outapi/lottery/images/pk10.jpg" /></div>
                            <div class="lott-info">
                                <h3>PK拾</h3>
                                <p>北京“PK拾”游戏属于福利彩票视频赛车游戏，按期销售，每5分钟销售一期，与北京市福彩中心现有的视频类彩票“快乐8”在开奖时间上间隔2分30秒。
                                </p>
                            </div>
                            <span class="arr-lott"></span>
                            </a>
                        </li>
                        <li><a href="#">
                            <div class="lott-img fl"><img src="<%=basePath %>inc/outapi/lottery/images/double.jpg" /></div>
                            <div class="lott-info">
                                <h3>双色球</h3>
                                <p>双色球属于双区投注的乐透型彩票。投注时需同时从两个号码区中分别选取号码，即从红色球号码区的33个号码中选择6个号码，再从蓝色球号码区的16个号码中选择1个号码，组合成一注进行投注。“双色球”有自选和机选两种投注方法，有单式和复式两种投注后台操作方式。
                                </p>
                            </div>
                            <span class="arr-lott"></span>
                            </a>
                        </li>
                        <li><a href="#">
                            <div class="lott-img fl"><img src="<%=basePath %>inc/outapi/lottery/images/7color.jpg" /></div>
                            <div class="lott-info">
                                <h3>7乐彩</h3>
                                <p>七乐彩采用组合式玩法，从01—30共30个号码中选择7个号码组合为一注投注号码。每注金额人民币2元。
                                </p>
                            </div>
                            <span class="arr-lott"></span>
                            </a>
                        </li>
                        <li><a href="#">
                            <div class="lott-img fl"><img src="<%=basePath %>inc/outapi/lottery/images/3D.jpg" /></div>
                            <div class="lott-info">
                                <h3>福彩3D</h3>
                                <p>福利彩票3D是以一个3位自然数为投注号码的彩票，投注者从000-999的数字中选择一个3位数进行投注。3D在各省（区、市）保留各自奖池、单独派奖的基础上实行三统一，即统一名称标识、统一游戏规则、统一开奖号码。
                                </p>
                            </div>
                            <span class="arr-lott"></span>
                            </a>
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
