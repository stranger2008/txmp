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
                	<li class="lot-hover tbl-cell"><a href="<%=basePath %>lottery/index.action">购彩大厅</a></li>
                	<li class="tbl-cell"><a href="<%=basePath %>lottery/notice.action">开奖公告</a></li>
                	<li class="tbl-cell"><a href="<%=basePath %>lottery/desc.action">彩种介绍</a></li>
                	<li class="tbl-cell"><a href="<%=basePath %>lottery/help.action">领奖规则</a></li>
                </ul>
            </div>
            <div class="lott-bar">
            	<!--购彩大厅-->
                <div class="lottery-cont clearfix">
                    <ul>
                    
                    	<li><a href="<%=basePath %>lottery/lotteryInfo.action?gameid=6">
                            <div class="lott-img fl"><img src="<%=basePath %>inc/outapi/lottery/images/3D.jpg" /></div>
                            <div class="lott-info fl">
                                <h3>福彩3D</h3>
                                <p>每日开奖<br />截止：2小时31分24秒<br />
                                </p>
                            </div>
                            <span class="arr-lott"></span>
                            </a>
                        </li>
                        
                           <li><a href="<%=basePath %>lottery/lotteryInfo.action?gameid=3">
                            <div class="lott-img fl"><img src="<%=basePath %>inc/outapi/lottery/images/double.jpg" /></div>
                            <div class="lott-info fl">
                                <h3>双色球</h3>
                                <p>每周二、四、日开奖<br />截止：2小时31分24秒<br />
                                    奖池滚存：211,476,892<br />
                                </p>
                            </div>
                            <span class="arr-lott"></span>
                            </a>
                        </li>
                        
                        
                          <li><a href="<%=basePath %>lottery/lotteryInfo.action?gameid=4">
                            <div class="lott-img fl"><img src="<%=basePath %>inc/outapi/lottery/images/7color.jpg" /></div>
                            <div class="lott-info fl">
                                <h3>7乐彩</h3>
                                <p>每周二、四、日开奖<br />
                                    截止：2小时31分24秒<br />
                                    奖池滚存：211,476,892<br />
                                </p>
                            </div>
                            <span class="arr-lott"></span>
                            </a>
                        </li>
                        
                        <li><a href="<%=basePath %>lottery/lotteryInfo.action?gameid=1">
                            <div class="lott-img fl"><img src="<%=basePath %>inc/outapi/lottery/images/happy8.jpg" /></div>
                            <div class="lott-info fl">
                                <h3>快乐8</h3>
                                <p>每周二、四、日开奖<br />截止：2小时31分24秒<br />
                                    奖池滚存：211,476,892<br />
                                </p>
                            </div>
                            <span class="arr-lott"></span>
                            </a>
                        </li>
                        
                        <li><a href="<%=basePath %>lottery/lotteryInfo.action?gameid=2">
                            <div class="lott-img fl"><img src="<%=basePath %>inc/outapi/lottery/images/pk10.jpg" /></div>
                            <div class="lott-info fl">
                                <h3>PK拾</h3>
                                <p>每周二、四、日开奖<br />截止：2小时31分24秒<br />
                                    奖池滚存：211,476,892<br />
                                </p>
                            </div>
                            <span class="arr-lott"></span>
                            </a>
                        </li>
                        
                    </ul>
                </div>
            </div>
        </div> 
        <div class="lottBuy">
			<h3>购彩流程</h3>
            <div class="lottBuy-img"><img class="full-img" src="<%=basePath %>inc/outapi/lottery/images/buylot-process.jpg" /></div>
        </div>
    </div>
</section>
    
<%@ include file="/WEB-INF/view/inc/footer.jsp" %>

</body>
</html>
