<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class="leftnav fl">
    <h2>我的天下名品</h2>
    <ul>
        <li><a href="<%=basePath %>user/orderlist.action">我的订单</a></li>
        <li><a href="<%=basePath %>user/addrlist.action">我的收货地址</a></li>
        <li><a href="<%=basePath %>user/collectgoods.action">我的收藏</a></li>
        <li><a href="<%=basePath %>user/goodsasklist.action">我的消息</a></li>
        <li><a href="<%=basePath %>user/updatePwSendPhone.action">修改密码</a></li>
    </ul>
    <h2>我的资金账户</h2>
    <ul>
        <li><a href="<%=basePath %>fundaccount/balance.action">账户余额</a></li>
        <li><a href="<%=basePath %>interhistory/index.action">我的积分</a></li>
        <li><a href="<%=basePath %>safetycenter/index.action">安全中心</a></li>
        <!-- 
        <li><a href="<%=basePath %>fundaccount/index.action">账户总览</a></li>
        <li><a href="<%=basePath %>user/collectgoods.action">代金券</a></li>
        <li><a href="<%=basePath %>user/goodsasklist.action">我的积分（0）</a></li>
        <li><a href="<%=basePath %>user/updatePwSendPhone.action">安全中心</a></li>
         -->
    </ul>
    <h2>售后维权</h2>
    <ul>
        <li><a href="<%=basePath %>user/goodsReturn_list.action">返修/退货/换货</a></li>
        <li><a href="<%=basePath %>user/buyer_complaint.action">投诉管理</a></li>
        <li><a href="<%=basePath %>user/buyer_report.action">举报管理</a></li>
    </ul>
</div>