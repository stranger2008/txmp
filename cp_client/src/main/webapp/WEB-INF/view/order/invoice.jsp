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
                <span style=display:none id="topInv">2</span>
            </ul>
            <div id="invo-cont1">
                <div class="invo-title">发票抬头</div>
                <div class="invo-info">
                	<div class="invo-ct border-b-none">
                    	<c:if test="${invoice.invoice_top == '个人' || invoice.invoice_top == ''}">
                        	<sf:radiobutton checked="true" path="invoice_top" value="个人"/>&nbsp;<span>个人</span>
                        </c:if>
                        <c:if test="${invoice.invoice_top != '个人' }">
                        	<sf:radiobutton  path="invoice_top" value="个人"/>&nbsp;<span>个人</span>
                        </c:if>
                    </div>
                    <div class="invo-ct">
                        <c:if test="${invoice.invoice_top == '单位'}">
                        <sf:radiobutton checked="true" path="invoice_top" value="单位"/>&nbsp;
                        <span>单位</span>
                        <span style="line-height: 30px; -webkit-transform-origin: 0px 0px; opacity: 1; -webkit-transform: scale(1, 1);">
                        	<sf:input path="company_name" placeholder="请输入单位名称" class="invo-comp" style=" width:70%;" value='${invoice. company_name }' ></sf:input>
                        </span>
                        </c:if>
                        <c:if test="${invoice.invoice_top != '单位'}">
                        <sf:radiobutton  path="invoice_top" value="单位"/>&nbsp;
                        <span>单位</span>
                        <span style="line-height: 30px; -webkit-transform-origin: 0px 0px; opacity: 1; -webkit-transform: scale(1, 1);">
                        	<sf:input path="company_name" placeholder="请输入单位名称" class="invo-comp" style=" width:70%;" ></sf:input>
                        </span>
                        </c:if>
                         <br/>
                        <span id="companyname" style="color:red"></span>
                    </div>
                    
                </div>
                <div class="invo-title">发票内容</div>
                <div class="invo-info">
                    <div class="invo-ct">
                        <sf:radiobutton checked="true" path="invoice_content" value="明细"/>&nbsp;<span>明细</span>
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
                    <script type="text/javascript">
                    	$(".invo-ct radiobutton[value='${post_work_template.post_id}']").attr("checked","true");
                    </script>
                </div>
            </div>
            
            <div id="invo-cont2">
                <div class="invo-title">增值税发票信息</div>
                <div class="invo-info border-b">
                    <div class="invo-ct border-b-none">
                        <span>纳税人识别号：</span>
                        <span style="line-height: 30px; -webkit-transform-origin: 0px 0px; opacity: 1; -webkit-transform: scale(1, 1);">
                        	<sf:input path="ident_number" placeholder="请输入纳税人识别号" class="invo-comp" style="width:50%;"/>
                        </span>
                        <br/>
                        <span id="number" style="color:red"></span>
                        
                    </div>
                    <div class="invo-ct border-b-none">
                        <span>注册地址：</span>
                        <span style="line-height: 30px; -webkit-transform-origin: 0px 0px; opacity: 1; -webkit-transform: scale(1, 1);">
                        	<sf:input path="regis_address" placeholder="请输入注册地址" class="invo-comp" style="width:60%;"/>
                        </span>
                        <br/>
                        <span id="regisaddress" style="color:red"></span>
                    </div>
                    <div class="invo-ct border-b-none">
                        <span>注册电话：</span>
                        <span style="line-height: 30px; -webkit-transform-origin: 0px 0px; opacity: 1; -webkit-transform: scale(1, 1);">
                        	<sf:input path="regis_tel" placeholder="请输入注册电话" class="invo-comp" style="width:60%;"/>
                        </span>
                    </div>
                    <br/>
                        <span id="tel" style="color:red"></span>
                    <div class="invo-ct border-b-none">
                        <span>开户银行：</span>
                        <span style="line-height: 30px; -webkit-transform-origin: 0px 0px; opacity: 1; -webkit-transform: scale(1, 1);">
                        	<sf:input path="open_bank" placeholder="请输入开户银行" class="invo-comp" style="width:60%;"/>
                        </span>
                    </div>
                    <br/>
                        <span id="bank" style="color:red"></span>
                    <div class="invo-ct border-b-none">
                        <span>银行帐户：</span>
                        <span style="line-height: 30px; -webkit-transform-origin: 0px 0px; opacity: 1; -webkit-transform: scale(1, 1);">
                        	<sf:input path="bank_account" placeholder="请输入银行帐户" class="invo-comp" style="width:60%;"/>
                        </span>
                    </div>
                    <br/>
                        <span id="account" style="color:red"></span>
                </div>
            </div>
            
            <div><input type="button" class="sign_btn mgn" value="确认" onclick="javascript:setInvoice();"/></div>
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
             		$("#topInv").val(val);
             	}
             	
             	setInvoiceType('${invoice.invoice_type}');
             	
             	
             //设置发票
			function setInvoice(){
				var invoice_type=$("#topInv").val(),
				ident_number=$("#ident_number").val(),
				regis_address=$("#regis_address").val(),
				regis_tel=$("#regis_tel").val(),
				open_bank=$("#open_bank").val(),
				bank_account=$("#bank_account").val(),
				invoice_top=$("input[name='invoice_top']:checked").val(),
				company_name=$("#company_name").val(),
				invoice_content=$("input[name='invoice_content']:checked").val();
				
				if(typeof invoice_type == 'undefined'){
					alert("请选择发票开具方式!");
					return false;
				}
				var invoice_title='';
				if(invoice_top=='单位'){
					invoice_title=company_name;
				}else{
					invoice_title=invoice_top;
				}
				if(invoice_type=='2'||invoice_type==2){
					$("#invoice_content").html("不开发票");
				}
				if(invoice_type=='0'||invoice_type==0){
					$("#invoice_content").html("普通发票   发票抬头 "+invoice_title+" 发票内容 "+invoice_content);
				}
				if(invoice_type=='1'||invoice_type=='0'){
					if($("input[name='invoice_top']:checked").val()=='单位'){
						if(company_name.length<1){
							$("#company_name").focus();
							$("#companyname").text("请输入单位名称");
							return false;
							}else if(company_name.length>20){
								$("#companyname").text("单位名称不能超过20个字符");
								return false;
							}else{
								$("#companyname").text("");
							}
						}
				}
				
				if(invoice_type=='1'||invoice_type==1){
					if(ident_number.length<1){
						$("#ident_number").focus();
						$("#number").text("请输入纳税人识别号");
						return false;
					}else if(ident_number.length>25){
						$("#number").text("纳税人识别号太长不能超过25个字符");
						return false;
					}else{
						$("#number").text("");
					}
					if(regis_address.length<1){
						$("#regis_address").focus();
						$("#regisaddress").text("请输入注册地址");
						return false;
					}else if(regis_address.length>50){
						$("#regis_address").focus();
						$("#regisaddress").text("注册地址不能超过50个字符");
						return false;
					}else{
						$("#regisaddress").text("");
					}
					if(regis_tel.length<1){
						$("#regis_tel").focus();
						$("#tel").text("请输入注册电话号码");
						return false;
					}else if(regis_tel.length>20){
						$("#regis_tel").focus();
						$("#tel").text("注册电话号码太长");
						return false;
					}else{
						$("#tel").text("");
					}
					if(open_bank.length<1){
						$("#open_bank").focus();
						$("#bank").text("请输入开户银行");
						return false;
					}else if(open_bank.length>22){
						$("#open_bank").focus();
						$("#bank").text("开户银行输入太长!不能超过20个字符");
						return false;
					}else{
						$("#bank").text("");
					}
					if(bank_account.length<1){
						$("#bank_account").focus();
						$("#account").text("请输入银行账户");
						return false;
					}else if(bank_account.length>22){
						$("#bank_account").focus();
						$("#account").text("银行账户输入太长! 不能超过20个字符");
						return false;
					}else{
						$("#account").text("");
					}
					$("#invoice_content").html("增值税发票   发票抬头 "+invoice_title+" 发票内容 "+invoice_content);
				}
				if(invoice_type=='2'||invoice_type==2){
					$("#invoice_content").html("不开发票");
				}
				if(invoice_type=='0'||invoice_type==0){
					$("#invoice_content").html("普通发票   发票抬头 "+invoice_title+" 发票内容 "+invoice_content);
				}
				if(invoice_type=='1'||invoice_type=='0'){
					if($("input[name='invoice_top']:checked").val()=='单位'){
						if(company_name.length<1){
							$("#company_name").focus();
							$("#companyname").text("请输入单位名称");
							return false;
							}else if(company_name.length>20){
								$("#companyname").text("单位名称不能超过20个字符");
								return false;
							}else{
								$("#companyname").text("");
							}
						}
				}
			//ajax 提交选择的发票
			$.ajax({
					type: 'post',
					data :{'invoice_type':invoice_type,'ident_number':ident_number
						,'regis_address':regis_address,'regis_tel':regis_tel
						,'open_bank':open_bank,'bank_account':bank_account
						,'invoice_top':invoice_top,'company_name':company_name
						,'invoice_content':invoice_content},
					url: "<%=basePath%>order/setInvoice.action",
					success: function(data, textStatus){
						if(data ==1){
							url = "<%=basePath %>order/orderinc.action";
							window.location = url;
						}
					}
				});
			}
             </script>
        
                
    <!--底部开始-->
    <%@ include file="/WEB-INF/view/inc/footer.jsp" %>
    

</body>
</html>
