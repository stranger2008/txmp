<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/WEB-INF/view/inc/inc.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>商家入驻</title>
<link rel="stylesheet" href="<%=basePath%>inc/css/public.css" />
<link rel="stylesheet" href="<%=basePath%>inc/member/css/business.css" />
<script type="text/javascript" src="<%=basePath%>inc/js/jquery.cycle.js"></script>
<script type="text/javascript">
$(function () {
	$('#banner').cycle({
		fx: 'scrollLeft',
		pager: '#btn-busi'
	});
})

</script>
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
                <li class="nav-b-hover"><a href="商家入驻流程.html">首页</a></li>
                <li><a href="<%=basePath %>member/joinus-step0.action">商家入驻</a></li>
                <li><a href="#help">帮助&资费 </a></li>
                <li><a href="#contact">联系我们</a></li>
            </ul>
        </div>
    </div>
</div>
<div class="clearfix"></div>

<div class="w1200 clearfix">

    <div class="fl" style="position: relative;">
        <div style="width:901px;height:210px;" id="banner">
            <a href="###" target="_blank"><img width="901" height="210" src="<%=basePath %>inc/member/images/banner-bus01.png"></a>
            <a href="###" target="_blank"><img width="901" height="210" src="<%=basePath %>inc/member/images/banner-bus01.png"></a>
        </div>
        <div id="btn-busi" class="new-product-num"></div>
    </div>
    <div class="box-busi fr">
        <a href="<%=basePath %>member/joinus-step0.action" class="imm-settle">马上入驻</a>
        <a href="<%=basePath %>member/joinus-view.action" class="sche-query">进度查询</a>
    </div>
</div>

<div class="w1200">
    <div class="inprocess">
        <h2><a name="inbusi">入驻流程</a></h2>

        <div class="inprocess-cont clearfix">

            <div class="inprocess-box fl">
                <h3 class="inprocess-tit1"><b></b>申请入驻</h3>
                <ul>
                    <li>1. 商家准备商家简介、商家主营名录、业资质证明文件资料</li>
                    <li>2. 在线提交商家合作信息</li>
                </ul>
            </div>
            <div class="arr-img fl"><img src="<%=basePath %>inc/member/images/arr-img.png"/></div>
            <div class="inprocess-box fl">
                <h3 class="inprocess-tit2"><b></b>签订协议</h3>
                <ul>
                    <li>1. 确认合作意向后，天下名品快递合同至商家</li>
                    <li>2. 商家按照要求提交合同、资质</li>
                    <li>3. 天下名品审核合同与资质文件</li>
                </ul>
            </div>
            <div class="arr-img fl"><img src="<%=basePath %>inc/member/images/arr-img.png"/></div>
            <div class="inprocess-box fl">
                <h3 class="inprocess-tit3"><b></b>开店准备</h3>
                <ul>
                    <li>1. 商家交纳费用</li>
                    <li>2. 开通商家管理后台</li>
                    <li>3. 上线前培训</li>
                </ul>
            </div>
            <div class="arr-img fl"><img src="<%=basePath %>inc/member/images/arr-img.png"/></div>
            <div class="inprocess-box fl">
                <h3 class="inprocess-tit4"><b></b>商品销售</h3>
                <ul>
                    <li>1. 商品录入</li>
                    <li>2. 装修店铺</li>
                    <li>3. 店铺上线销售</li>
                </ul>
            </div>

            <div class="settle-btns"><a href="<%=basePath %>member/joinus-step0.action" class="settle-btn-red">立即入驻</a></div>
        </div>
    </div>

    <div class="inprocess">
        <h2><a name="help">入驻帮助&资费 </a></h2>

        <div class="inprocess-cont">
            <div class="inprocess-help">
                <h3>商家企业资质审核</h3>
                <ul>
                    <li>(1) 商家企业资质要求：注册资金？？万元(含)以上人民币</li>
                    <li>(2) 需要商家提供以下资料电子版：
                        法人身份证、公司营业执照信息、组织机构代码证、税务登记证、开户许可证复印件
                    </li>
                </ul>
            </div>
            <table cellpadding="0" cellspacing="0" class="inprocess-tab">
                <tr>
                    <th>行业</th>
                    <th>二级分类</th>
                    <th>佣金</th>
                    <th>平台使用费（元）</th>
                    <th>保证金（元）</th>
                </tr>
                <tr>
                    <td class="tea-td">茶叶</td>
                    <td colspan="4">
                        <table cellpadding="0" cellspacing="0" width="100%" class="inprocess-tab-tab">
                            <tr>
                                <td>红茶</td>
                                <td> 1%</td>
                                <td>0/年</td>
                                <td>10000/年</td>
                            </tr>
                            <tr>
                                <td>红茶</td>
                                <td> 1%</td>
                                <td>0/年</td>
                                <td>10000/年</td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
            <div class="settle-btns"><a href="#" class="settle-btn-gray">查看所有</a><a href="商家入驻协议.html" class="settle-btn-red">立即入驻</a></div>
        </div>
    </div>

    <div class="inprocess">
        <h2><a name="contact">联系方式</a></h2>

        <div class="inprocess-cont">
            <div class="address">
                <h3>朝阳分公司</h3>
                <ul>
                    <li>地址：北京市朝阳区东三环中路39号建外SOHOA座2903-2905</li>
                    <li>电话：(+8610)-58699138／58693015 传真：(+8610)-58699138转8006</li>
                    <li>E-mail: cfcm@cfcm.com.cn 邮编：100022</li>
                </ul>
            </div>
            <div class="settle-btns"><a href="<%=basePath %>member/joinus-step0.action" class="settle-btn-red">立即入驻</a></div>
        </div>
    </div>

</div>

<!--底部-->
<%@ include file="/WEB-INF/view/inc/footer.jsp"%>
</body>
</html>