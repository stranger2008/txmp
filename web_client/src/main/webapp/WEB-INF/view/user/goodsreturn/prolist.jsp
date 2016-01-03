<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xmlns="http://www.w3.org/1999/html">
<head>
    <%@ include file="/WEB-INF/view/inc/inc.jsp" %>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" href="<%=rootPath %>css/public.css" /> 
    <link rel="stylesheet" href="<%=rootPath %>css/product.css" />
    <link rel="stylesheet" href="<%=rootPath %>css/account.css" />
    <script src="<%=rootPath %>js/jquery.min.js" type="text/javascript"></script>
    <script src="<%=rootPath %>js/core.js"></script>
    <script src="<%=rootPath %>js/account.js"></script>
    <title>售后服务单详情</title>
    <script type="text/javascript">
    	function confReceive(){
    		$("#updateForm").submit();
    	}
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
            <!--售后服务单详情-->
            <div class="account-cust">
                <h6 class="account-cust-title">售后服务单</h6>
                <p class="account-cust-num">
                状态：${goodsreturn.info_state_name}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                服务单号：${goodsreturn.trade_id}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                商品名称：${goodsreturn.goods_name} 
                <c:if test="${goodsreturn.no_reason!=null}"><br/><br/>
                 原因：${goodsreturn.no_reason}
                </c:if>
                </p>
            </div>
            
            <div class="account-cust">
                <h6 class="account-cust-title">信息跟踪</h6>
                <table width="100%" border="0" cellspacing="0" class="account-cust-msg" cellpadding="0">
                    <tr>
                        <td bgcolor="#f5f5f5" width="160">处理时间</td>
                        <td bgcolor="#f5f5f5">处理信息</td>
                        <td bgcolor="#f5f5f5" width="130">操作人</td>
                    </tr>
                    <c:forEach items="${returnHisList}" var="history" varStatus="status">
	                    <tr <c:if test="${status.last}">style="color:red"</c:if> >
	                        <td bgcolor="#f5f5f5" width="160">
	                        	<fmt:formatDate value="${history.in_date}" pattern="yyyy-MM-dd HH:mm:ss"/>		
							</td>
	                        <td bgcolor="#f5f5f5">${history.oper_name}
	                        <c:if test="${history.oper_code=='refund_apply'}">等待卖家审核</c:if>
	                         </td>
	                        <td bgcolor="#f5f5f5" width="130">
	                         <c:if test="${history.user_id==-99}">-</c:if>
	                         <c:if test="${history.user_id>=0}">${history.user_name }</c:if>
	                        </td>
	                    </tr>
                	</c:forEach>
                </table>
            </div>
           
            <div class="account-cust">
                <h6 class="account-cust-title">服务单信息</h6>
                <table width="100%" border="0" cellspacing="0" class="account-cust-msg" cellpadding="0">
                    <tr>
                        <td bgcolor="#fafbfb" width="150">商品返回方式</td>
                        <td> 
                        	<c:if test="${goodsreturn.returnType==0}">上门取件</c:if>
                        	<c:if test="${goodsreturn.returnType==1}">快递至天下名品 </c:if>
						</td>
                    </tr>
                    <c:if test="${goodsreturn.info_state>0}">
                    <tr>
                        <td bgcolor="#fafbfb">商品处理方式</td>
                        <td>
							${goodsreturn.biz_type_name}
						</td>
                    </tr>
                    </c:if>
                    <tr>
                        <td bgcolor="#fafbfb">问题描述</td>
                        <td>${goodsreturn.cont_desc }</td>
                    </tr>
                    <tr>
                        <td bgcolor="#fafbfb">收货地址</td>
                        <td>${returnAddr.area_attr_name } ${returnAddr.addr}</td>
                    </tr>
                    <tr>
                        <td bgcolor="#fafbfb">联系人</td>
                        <td>${returnAddr.name}</td>
                    </tr>
                    <tr>
                        <td bgcolor="#fafbfb">电话</td>
                        <td>${returnAddr.phone}</td>
                    </tr>
                </table>
            </div>
            
            <!-- 等待买家确认收货 -->
        	 <c:if test="${goodsreturn.info_state==4}">
             <div class="account-cust">
                <h6 class="account-cust-title">物流信息</h6>
                <table width="100%" border="0" cellspacing="0" class="account-cust-msg" cellpadding="0">
                    <tr>
                        <td bgcolor="#f5f5f5" width="160">物流公司</td>
                        <td bgcolor="#f5f5f5">运单号</td>
                        <td bgcolor="#f5f5f5" width="130">发货时间</td>
                    </tr>
                    <tr >
                        <td bgcolor="#f5f5f5" width="160">${shipInfo.ship_name}
						</td>
                        <td bgcolor="#f5f5f5">${shipInfo.ship_no}
                         </td>
                        <td bgcolor="#f5f5f5" width="130">
                        	<fmt:formatDate value="${shipInfo.in_date}" pattern="yyyy-MM-dd HH:mm:ss"/>	
                        </td>
	                </tr>
                </table>
            </div>
            <div style="text-align:center;">
	            <sf:form action="user/goodsReturn_receive.action" id="updateForm" modelAttribute="goodsreturnObj">
	            	<sf:hidden path="trade_id" value="${goodsreturn.trade_id}"/>
	            	<sf:hidden path="biz_type" value="${goodsreturn.biz_type}"/>
	            	<sf:hidden path="info_state" value="${goodsreturn.info_state}"/>
	            </sf:form>
	            <a href="javascript:void(0);" class="applybtn" onclick="confReceive();">确认收货</a>
            </div>
            </c:if>
            
        </div>
	</div>
</div>
<div class="clearfix"></div>
<!--底部-->
<%@ include file="/WEB-INF/view/inc/footer.jsp" %>


</body>
</html>
