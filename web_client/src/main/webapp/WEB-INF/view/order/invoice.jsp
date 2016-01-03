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
                    <a href="<%=basePath %>">首页</a> > <a href="javascript:void(0);">提交订单</a> > <a href="javascript:void(0);">发票编辑</a>
                </div>
            </section>
        </div>
        
<div class="module">
    <section>
    
    	<sf:form method="get" id="invoiceForm" action="order/order.action" modelAttribute="invoice">
    
    	<sf:hidden path="invoice_type"/>
    
    	<div class="cont mt5">
            <ul class="sub-nav tbl-type">
                <li class="tbl-cell w33"><a href="javascript:setInvoiceType(2);" id="topInv2" class="current">不开发票</a></li>
                <li class="tbl-cell w33"><a href="javascript:setInvoiceType(0);" id="topInv0">普通发票</a></li>
                <li class="tbl-cell w33"><a href="javascript:setInvoiceType(1);" id="topInv1">增值税发票</a></li>
            </ul>
            <div id="invo-cont1">
                <div class="invo-title">发票抬头</div>
                <div class="invo-info">
                    <div class="invo-ct">
                        <sf:radiobutton path="invoice_top" value="单位"/>&nbsp;
                        <span>单位</span>
                        <span style="line-height: 30px; -webkit-transform-origin: 0px 0px; opacity: 1; -webkit-transform: scale(1, 1);">
                        	<sf:input path="company_name" placeholder="请输入单位名称" class="invo-comp" style=" width:70%;" />
                        </span>
                    </div>
                    <div class="invo-ct border-b-none">
                        <sf:radiobutton path="invoice_top" value="个人"/>&nbsp;<span>个人</span>
                    </div>
                </div>
                <div class="invo-title">发票内容</div>
                <div class="invo-info">
                    <div class="invo-ct">
                        <sf:radiobutton path="invoice_content" value="明细"/>&nbsp;<span>明细</span>
                    </div>
                    <div class="invo-ct">
                        <sf:radiobutton path="invoice_content" value="办公用品"/>&nbsp;<span>办公用品</span>
                    </div>
                    <div class="invo-ct">
                        <sf:radiobutton path="invoice_content" value="电脑配件"/>&nbsp;<span>电脑配件</span>
                    </div>
                    <div class="invo-ct border-b-none">
                        <sf:radiobutton path="invoice_content" value="耗材"/>&nbsp;<span>耗材</span>
                    </div>
                </div>
            </div>
            
            <div id="invo-cont2">
                <div class="invo-title">增值税发票信息</div>
                <div class="invo-info border-b">
                    <div class="invo-ct border-b-none">
                        <span>纳税人识别号：</span>
                        <span style="line-height: 30px; -webkit-transform-origin: 0px 0px; opacity: 1; -webkit-transform: scale(1, 1);">
                        	<sf:input path="ident_number" placeholder="请输入纳税人识别号" class="invo-comp" style="width:60%;"/>
                        </span>
                    </div>
                    <div class="invo-ct border-b-none">
                        <span>注册地址：</span>
                        <span style="line-height: 30px; -webkit-transform-origin: 0px 0px; opacity: 1; -webkit-transform: scale(1, 1);">
                        	<sf:input path="regis_address" placeholder="请输入注册地址" class="invo-comp" style="width:60%;"/>
                        </span>
                    </div>
                    <div class="invo-ct border-b-none">
                        <span>注册电话：</span>
                        <span style="line-height: 30px; -webkit-transform-origin: 0px 0px; opacity: 1; -webkit-transform: scale(1, 1);">
                        	<sf:input path="regis_tel" placeholder="请输入注册电话" class="invo-comp" style="width:60%;"/>
                        </span>
                    </div>
                    <div class="invo-ct border-b-none">
                        <span>开户银行：</span>
                        <span style="line-height: 30px; -webkit-transform-origin: 0px 0px; opacity: 1; -webkit-transform: scale(1, 1);">
                        	<sf:input path="open_bank" placeholder="请输入开户银行" class="invo-comp" style="width:60%;"/>
                        </span>
                    </div>
                    <div class="invo-ct border-b-none">
                        <span>银行帐户：</span>
                        <span style="line-height: 30px; -webkit-transform-origin: 0px 0px; opacity: 1; -webkit-transform: scale(1, 1);">
                        	<sf:input path="bank_account" placeholder="请输入银行帐户" class="invo-comp" style="width:60%;"/>
                        </span>
                    </div>
                </div>
            </div>
            
            <div><input type="submit" class="sign_btn mgn" value="确认" /></div>
        </div>
        
        </sf:form>
        
    </section>
</div>

		<script type="text/javascript">
             	//根据发票抬头是否显示公司名称，在普通发票的情况下
             	$("#invoiceForm input[name='invoice_top']").click(function(){
             		var tval = $(this).val();
             		if(tval == "单位"){
             			$("#company_name").show();
             		}else{
             			$("#company_name").hide();
             		}
             	});
             	//设置各自发票类型的显示/隐藏
             	function setShow(tval){
             		if(tval == 0){
             			$("#invo-cont1").show();
             			$("#invo-cont2").hide();
             			if($("input[name='invoice_top']:checked").val() == "单位"){
             				$("#company_name").show();
             			}else{
             				$("#company_name").hide();
             			}
             		}
             		if(tval == 2){
             			$("div[id^='invo-cont']").hide();
             		}
             		if(tval == 1){
             			$("#invo-cont1").show();
             			$("#invo-cont2").show();
             		}
             	}
             	
             	function setInvoiceType(val){
             		$(".tbl-cell a").attr("class","");
             		$("#topInv"+val).attr("class","current");
             		$("#invoice_type").val(val);
             		setShow(val);
             	}
             	
             	setInvoiceType('${invoice.invoice_type}');
             	
             </script>
        
                
    <!--底部开始-->
    <%@ include file="/WEB-INF/view/inc/footer.jsp" %>
    

</body>
</html>
