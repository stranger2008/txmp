<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
  <title>我的会员中心</title>
    <%@ include file="/WEB-INF/view/inc/inc.jsp" %>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" href="<%=rootPath %>css/public.css" />
    <link rel="stylesheet" href="<%=rootPath %>css/product.css" />
    <link rel="stylesheet" href="<%=rootPath %>css/account.css" />
    <script src="<%=rootPath %>js/jquery.min.js" type="text/javascript"></script>
    <script src="<%=rootPath %>js/core.js"></script>
    <script src="<%=rootPath %>js/account.js"></script>
    <script language="javaScript"> 
		$(document).ready(function () {
	        now = new Date();
	        hour = now.getHours();
			if(hour < 6){$("#category_time").append("凌晨");} 
			else if (hour < 9){$("#category_time").append("早上");} 
			else if (hour < 12){$("#category_time").append("上午");} 
			else if (hour < 14){$("#category_time").append("中午");} 
			else if (hour < 17){$("#category_time").append("下午");} 
			else if (hour < 19){$("#category_time").append("傍晚");} 
			else if (hour < 22){$("#category_time").append("晚上");} 
			else {$("#category_time").append("夜里");} 
    	});
		
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
        <div class="user-info fr">
                   
                   
        	<div class="user-info fr">
	        	<h2 class="user-hello"><a href="#"></a>${user_name}，<span id="category_time"></span>好！</h2>
	        	<div class="myorder">
	            	<h3 class="myorder-tit">
	                    <b>我的订单</b>
	                    <span class="mes-a">
	                        <a href="<%=basePath %>user/nonPayment.action">待付款（<em>${noPayNums}</em>）</a><a href="<%=basePath %>user/notToConfirmReceipt.action">待确认收货（<em>${cfmGoodsNums}</em>）</a>
	                    </span>
	                    <a href="<%=basePath %>user/orderlist.action" class="all">查看所有订单</a>
	                </h3>
             <table class="user-tab" width="100%" cellpadding="0" cellspacing="20" border="0">
             <c:if test="${!empty goodsResult.list}">
              <c:forEach items="${goodsResult.list}" var="item">
                <tr class="tab_td">
                	<td>
                		<c:forEach items="${item.orderdetails}" var="orderdetail">
                			<table width="100%">
                				<tr>
                					<td class="productimg">
                						<c:if test="${!empty orderdetail.img_path}">
			                        		<a href="<%=basePath %>goods/${orderdetail.goods_id }.action"><img src="<h:imgSubstr imgpath="${orderdetail.img_path}"/>"/></a>
			                       		</c:if>
                					</td>
                					<td class="productintro">
                						<a href="<%=basePath %>goods/${orderdetail.goods_id }.action">${orderdetail.goods_name}</a>
                					</td>
                				</tr>
                			</table>
					 	</c:forEach>
                	</td>
                	<td class="pay-onl">¥${item.tatal_amount}<br />在线支付</td>
                	<td class="pay-time"><fmt:formatDate value="${item.order_time}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                	<td class="pay-sta">${item.order_state_name}</td>
                	<td>
                		<a href="<%=basePath%>user/order-${item.order_id}.action">查看详细</a><br/>
                	</td>
                	
                </tr>
                </c:forEach>
                 </c:if>
                   <c:if test="${empty goodsResult.list}">
          				<center>暂无我的订单信息</center>
      				</c:if>
                 </table>
	            </div>
	            <div class="clearfix"></div>
	
	            <!--我收藏的商品-->
	            <div class="mylike">
	                <h2><b>我收藏的商品</b><a href="<%=basePath %>user/collectgoods.action" class="all">查看全部收藏商品</a></h2>
	                    <ul>
	                     	<c:if test="${!empty collectGoodsResult.list}">
		                    <c:forEach items="${collectGoodsResult.list}" var="goods" varStatus="status">
			                    <li>
			                        <span class="img-collect">
			                        <a href="<%=basePath %>goods/${goods.info_id}.action" target="_blank">
				                        <c:if test="${!empty goods.img_path}">
			                        		<img src="<h:imgSubstr imgpath="${goods.img_path}"/>"/>
			                       		</c:if>
			                        </a>
			                        </span>
			                        <div class="ll">
			                            <a href="<%=basePath %>goods/${goods.info_id}.action" target="_blank">
			                            <c:choose> 
		                                    <c:when test="${fn:length(goods.goods_name) > 27}">  
									        	${fn:substring(goods.goods_name, 0, 27)}...
										    </c:when>  
										    <c:otherwise>
										    	${goods.goods_name}
										    </c:otherwise>
									    </c:choose> 
			                            </a>
			                            <div class="red">¥${goods.sale_price}</div>
			                        </div>
			                    </li>
		                    </c:forEach>
		                    </c:if>
		                    <c:if test="${empty collectGoodsResult.list}">
	            				<center>暂无我收藏的商品</center>
	        				</c:if>
	                </ul>
	            </div>
	    
	            <!--我收藏的店铺-->
	            <div class="mylike-shop">
	                	<c:if test="${!empty collectShopResult.list}">
			                <h2><b>我收藏的店铺</b><a href="<%=basePath %>user/collectshop.action" class="all">查看全部收藏店铺</a></h2>
			                <ul>
			                	<c:forEach items="${collectShopResult.list}" var="shopconfig" varStatus="status">
			                    <li>
			                        <span class="shopimg">
			                        <a href="<%=basePath %>shop/${shopconfig.info_id}.action" target="_blank">
			                        	<c:if test="${!empty shopconfig.shop_logo}">
							               <img src="${shopconfig.shop_logo}"/>
						                </c:if>
			                        </a></span>
			                        <a href="<%=basePath %>shop/${shopconfig.info_id}.action" target="_blank" class="shop-name">${shopconfig.shop_name}</a>
			                        <p>${shopconfig.popular}人关注</p>
			                    </li>
			                   </c:forEach>
			                </ul>
	            	   </c:if>
                       <c:if test="${empty collectShopResult.list}">
                       	<h2><b>我收藏的店铺</b></h2>
           			   		<center>暂无我收藏的商铺</center>
       				   </c:if>
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
