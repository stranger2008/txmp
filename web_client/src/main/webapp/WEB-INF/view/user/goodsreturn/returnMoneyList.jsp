<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>返修退换货</title>
	<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" href="<%=rootPath %>css/public.css" />
    <link rel="stylesheet" href="<%=rootPath %>css/product.css" />
    <link rel="stylesheet" href="<%=rootPath %>css/account.css" />
    <script src="<%=rootPath %>js/jquery.min.js" type="text/javascript"></script>
    <script src="<%=rootPath %>js/core.js"></script>
    <script src="<%=rootPath %>js/account.js"></script>
	<script type="text/javascript">
	$(function(){

		//查询
	    $("#searchInfoBt").click(function(){
	    	$("#goodsreturnQueryForm").submit();
	    });
	})			
	</script>

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
        	<a href="#"><strong>我的天下名品</strong></a><span></span><a href="#">账户安全</a>
        </div>
        
        <!--会员中心左边导航-->
		<%@ include file="/WEB-INF/view/user/inc/left.jsp" %>
		
		<sf:form action="user/goodsReturnMoney_look.action" modelAttribute="goodsreturnQueryForm">
		
        <div class="user-info fr">
            <!--返修/退货/换货-->
            <div class="store">
                <h1 class="store-title">返修/退货/换货</h1>
                <div class="store-tab">
                    <a href="<%=basePath %>user/goodsReturn_list.action">申请返修/退换货<span></span></a>
                    <a href="<%=basePath %>user/goodsReturn_look.action">查看返修/退换货记录<span></span></a>
                    <a class="active" href="<%=basePath %>user/goodsReturnMoney_look.action">查看退款记录<span></span></a>
                </div>
                <p style="float: right;margin: 10px 15px 0 0;">
                    <sf:input path="keywords" title="商品名称、订单编号" placeholder="商品名称、订单编号" cssClass="my-o-s-input"  />
                    <a class="my-o-s-go" href="javascript:void(0);" id="searchInfoBt">查询</a>
                </p>
                <div class="store-list" id="store-show">
                    
                    <!--查看退款记录-->
                    <div>
                        <table width="100%" border="0" class="account-repair-head" cellpadding="5" cellspacing="0">
                            <tr bgcolor="#f5f5f5">
                                <td width="120" align="center">服务单号</td>
                                <td width="120" align="center">订单编号</td>
                                <td align="center">商品名称</td>
                                <td width="130" align="center">退款明细</td>
                                <td width="130" align="center">退款申请时间</td>
                                <td width="130" align="center">退款状态</td>
                                <!-- 
                                <td width="110" align="center">操作</td>
                                 -->
                            </tr>
                        </table>
                        <table width="100%" border="0" class="account-repair-record" cellpadding="0" cellspacing="0">
                         	<c:forEach items="${pageResult.list}" var="item">
	                            <tr>
	                                <td width="120" align="center">
	                                	<a href="<%=basePath%>user/goodsReturn_prolist.action?trade_id=${item.trade_id }"  class="account-order-num">${item.trade_id }</a>
	                                </td>
	                                <td width="120" align="center">
	                                	<a href="<%=basePath%>user/order-${item.order_id }.action" class="account-order-num"> ${item.order_id }</a>
	                                </td>
	                                <td>
	                                	<a href="<%=basePath%>goods/${item.goods_id }.action" class="account-order-num"><span class="account-order-num" style="padding: 0 10px">${item.goods_name }</span></a>
	                                </td>
	                                <td width="130" align="center">
	                                	<c:if test="${item.refundMoney!=null }">
	                                    	退款金额<br/>
	                                    	<fmt:formatNumber type="number" pattern="¥0.00" value="${item.refundMoney }" maxFractionDigits="2"/>
	                                    </c:if>
	                                </td>
	                                <td width="130" align="center">
	                                	<fmt:formatDate value="${item.in_date }" pattern="yyyy-MM-dd HH:mm:ss"/>
	                                </td>
	                                <td width="130" align="center">${item.info_state_name }</td>
	                                <!-- 
	                                <td width="110" align="center">
	                                    <a href="<%=basePath%>user/goodsReturn_prolist.action?trade_id=${item.trade_id }">查看</a>
	                                </td>
	                                 -->
	                            </tr>
                         	</c:forEach>
                        </table>
                         <div class="page">${pageBar}</div>
                    </div>
                </div>
            </div>
            <!--退货常见问题-->
            <div class="account-repair-faq">
                <h6>返修/退换货常见问题</h6>
                <p>1.“申请”按钮若为灰色，可能是因为订单尚未完成或该商品正在返修/退换货中；</p>
                <p>2.查看<a>售后政策</a>；</p>
            </div>
        </div>
        </sf:form>
	</div>
</div>

<div class="clearfix"></div>


        
  <!--底部-->
<%@ include file="/WEB-INF/view/inc/footer.jsp" %>

</body>
</html>
