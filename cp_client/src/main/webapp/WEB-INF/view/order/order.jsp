<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
<title>提交订单</title>
</head>

<body>
    
    <!--头部开始-->
    <%@ include file="/WEB-INF/view/inc/top.jsp" %>
    
    <!--搜索开始-->
    <%@ include file="/WEB-INF/view/inc/search.jsp" %>
    
    	<!--当前位置开始-->
    	<div class="module">
            <section>
                <div class="position">
                    <a href="<%=basePath %>">首页</a> > <a href="javascript:void(0);">提交订单</a>
                </div>
            </section>
        </div>
        
<div class="module">
    <section>
    	<div class="cont mt5">
            <div class="rece-title">收货人信息</div>
            <div class="rece-info">
            	<a href="<%=basePath %>order/address.action">
                	<span class="rece-usr">${addr.cust_name}<span class="rece-num">${addr.cell_phone}</span></span>
                    <span class="rece-addr">${addr.area_name_str}${addr.address}</span>
                </a>
                <span class="arr" style="top:15px;"></span>
            </div>
            <div class="rece-title">支付信息</div>
            <div class="rece-info">
            	<a href="javascript:void(0);">
                    <span class="rece-addr">在线支付</span>
                </a>
                <span class="arr" style="top:15px;"></span>
            </div>
            <!-- 
            <div class="rece-info">
            	<a href="###">
                    <span class="rece-addr">优惠券</span>
                </a>
                <span class="arr" style="top:15px;"></span>
            </div>
             
            <div class="rece-title">快递信息</div>
            <div class="rece-info">
            	<a href="###">
                	<span class="rece-usr">送货时间<span class="rece-num">2014-06-06 8:00-20:00</span></span>
                </a>
                <span class="arr" style="top:15px;"></span>
            </div>
            -->
            <div class="rece-title">发票信息</div>
            <div class="rece-info">
            	<a href="<%=basePath %>order/invoice.action">
            	
            		<c:if test="${invoice.invoice_type==0}">
                	<span class="rece-usr">普通发票</span>
                	<span class="rece-usr">
                		发票抬头：${invoice.invoice_top}
                		<c:if test="${invoice.invoice_top=='单位'}">
	                		&nbsp;${invoice.company_name}
	                	</c:if>
                	</span>
                	
                	<span class="rece-usr">发票内容：${invoice.invoice_content}</span>
	                </c:if>
	                <c:if test="${invoice.invoice_type==1}">
	                	<span class="rece-usr">增值税发票</span>
	                	<c:if test="${invoice.invoice_top=='单位'}">
		                	<span class="rece-usr">单位名称：${invoice.company_name}</span>
	                	</c:if>
	                	<c:if test="${invoice.invoice_top=='个人'}">
		                	<span class="rece-usr">发票抬头：${invoice.invoice_top}</span>
	                	</c:if>
	                	<span class="rece-usr">纳税人识别号：${invoice.ident_number}</span>
	                	<span class="rece-usr">注册地址：${invoice.regis_address}</span>
	                	<span class="rece-usr">注册电话：${invoice.regis_tel}</span>
	                	<span class="rece-usr">开户银行：${invoice.open_bank}</span>
	                	<span class="rece-usr">银行帐户：${invoice.bank_account}</span>
	                	<span class="rece-usr">发票内容：${invoice.invoice_content}</span>
	                	
	                </c:if>
	                <c:if test="${invoice.invoice_type==2}">
	                	<span class="rece-usr">不开发票</span>
	                </c:if>
            	
                </a>
                <span class="arr" style="top:15px;"></span>
            </div>
            <div class="rece-totle">
            	<div class="tbl-type">
                    <span class="tbl-cell">商品金额</span>
                    <span class="tbl-cell text-right">￥${goodsTotalPrice}</span>
                </div>
                <!-- 
            	<div class="tbl-type">
                    <span class="tbl-cell">应付金额</span>
                    <span class="tbl-cell text-right"><span class="text-red">￥99.00</span></span></span>
                </div>
                -->
            </div>
            <div><input type="button" class="sign_btn mgn" onclick="window.location.href='<%=basePath %>order/orderok.action';" value="提交订单" /></div>
        </div>
    </section>
</div>
                
        
    <!--底部开始-->
    <%@ include file="/WEB-INF/view/inc/footer.jsp" %>
    

</body>
</html>
