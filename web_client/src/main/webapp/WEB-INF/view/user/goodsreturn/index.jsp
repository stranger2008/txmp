<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
	<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
	<title>返修退换货</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" href="<%=rootPath %>css/public.css" />
    <link rel="stylesheet" href="<%=rootPath %>css/product.css" />
    <link rel="stylesheet" href="<%=rootPath %>css/account.css" />
    <script src="<%=rootPath %>js/jquery.min.js" type="text/javascript"></script>
    <script src="<%=rootPath %>js/core.js"></script>
    <script src="<%=rootPath %>js/account.js"></script>
	<script type="text/javascript">
	$(function(){
		$("#searchInfoBt").click(function(){
			$("#queryForm").submit();
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
		
		<sf:form action="user/goodsReturn_list.action" id="queryForm" modelAttribute="goodsorderQueryForm">
		
        <div class="user-info fr">
            <!--返修/退货/换货-->
            <div class="store">
                <h1 class="store-title">返修/退货/换货</h1>
                <div class="store-tab">
                    <a class="active" href="<%=basePath %>user/goodsReturn_list.action">申请返修/退换货<span></span></a>
                    <a href="<%=basePath %>user/goodsReturn_look.action">查看返修/退换货记录<span></span></a>
                    <a href="<%=basePath %>user/goodsReturnMoney_look.action">查看退款记录<span></span></a>
                </div>
                <p style="float: right;margin: 10px 15px 0 0;">
                	<sf:input path="keywords" cssClass="my-o-s-input" placeholder="商品名称、订单编号"/>
                    <a class="my-o-s-go" id="searchInfoBt" href="javascript:void(0);">查询</a>
                </p>
                <div class="store-list" id="store-show">
                    <!--申请返修/退换货-->
                    <div>
                        <table width="100%" border="0" class="account-repair-head" cellpadding="5" cellspacing="0">
                            <tr bgcolor="#f5f5f5">
                                <td width="155" align="center">订单编号</td>
                                <td align="center">订单商品</td>
                                <td width="290" align="center">
                                	<sf:select path="sear_days" onchange="$('#searchInfoBt').click();">
                                		<sf:option value="">下单时间</sf:option>
                                		<sf:options  items="${seardaysMap}" />
                                	</sf:select>
                                </td>
                            </tr>
                        </table>
                        <table width="100%" border="0" class="account-repair" cellpadding="0" cellspacing="0">
                        	<c:forEach items="${pageResult.list}" var="item">
                        		<tr>
                                <td width="155" align="center">
                                	<a href="<%=basePath%>user/order-${item.order_id }.action" class="account-order-num"> ${item.order_id }</a>
                                </td>
                                <td>
                                    <ul class="account-repair-item">
                                    	<c:forEach items="${item.ordergoods}" var="goods">
	                                        <li>
	                                            <img src="${goods.img }" title="${goods.name }"/>
	                                            <c:if test="${goods.canApply}">
	                                            	<a href="<%=basePath%>user/goodsReturn_refund-${goods.id }.action?orderId=${item.order_id }" >申请</a>
	                                            </c:if>
	                                            <c:if test="${!goods.canApply}">
	                                            	<a href="javascript:void(0)" style="background: #eee;border:1px solid #bbb;">已申请</a>
	                                            </c:if>
	                                        </li>
                                    	</c:forEach>
                                         
                                    </ul>
                                </td>
                                <td width="290" align="center">
                                <fmt:formatDate value="${item.order_time }" pattern="yyyy-MM-dd HH:mm:ss"/>
                                </td>
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
