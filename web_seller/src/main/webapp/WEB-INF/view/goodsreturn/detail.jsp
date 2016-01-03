<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<title>查看退换货详细</title>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
<%@ include file="/WEB-INF/view/inc/uploadify/resource.jsp" %>
<style type="text/css">
.warnRed{
	color:red;
}
.erInfo{
		display:none;color:red;
	}
</style>
<script type="text/javascript">
	$(function(){
		//通过
		$("#passBt").click(function(){
			$("#applyPass").show();
			$("#noapplyPass").hide();
			$("#no_reason").val('');
			$("#audit_code").val(0);
			$(".erInfo").hide();
		});
		//不通过
		$("#nopassBt").click(function(){
			$("#applyPass").hide();
			$("#noapplyPass").show();
			$("#audit_code").val(1);
			$(".erInfo").hide();
		});
		//设置地区下拉框
		showSelectCascade('<%=basePath %>','area','selectAreaDiv','goodsReturnAddr.area_attr','${selleraddr.area_attr }');
		
		//点击提交
		$("button[name='applyBt']").click(function(){
			var audit = $("#audit_code").val();
			if(audit==0&checkArea()&checkInputInfo('gr_addr','详细地址')&checkInputInfo('gr_name','收货人姓名')&checkPhone()){
				//选择通过，验证信息通过后提单表单
				$("#goodsreturnForm").submit();
			}
			if(audit==1&checkInputInfo('no_reason','不通过的理由')){
				//选择不通过，验证信息通过后提单表单
				$("#goodsreturnForm").submit();
			}
		});
		var errInf = $("#no_reason_error").val();
		if(errInf!=null && errInf!=''){
			$("#nopassBt").click();
		}
	});
	//验证文本信息不能为空
	function checkInputInfo(inputId,nm , errorId){
			var ok = false; 
			var info = $("#"+inputId).val();
			var reg = /^\s*$/g; 
			ok = info!=null && !(reg.test(info)) ;
			errorId = errorId==null? (inputId+"_info"):errorId ;
			var errorSpan = $("#"+errorId);
			if(ok){
				errorSpan.text("");
				errorSpan.hide();
				 
			}else{
				errorSpan.text("请输入"+nm);
				errorSpan.show();
			}
			return ok;
		}
		//验证地区选择不能为空
		function checkArea(){
			var info = $("#field_goodsReturnAddr_area_attr").val();
			var ok = info.length>0;
			if(ok){
				$("#area_attr_info").text("");
				$("#area_attr_info").hide();
				 
			}else{
				$("#area_attr_info").text("请选择收货地区信息");
				$("#area_attr_info").show();
			}
			return ok;
		}
		//验证手机号码
		function checkPhone(){
			var info = $("#gr_phone").val();
			var reg = /^\s*$/g; 
			var ok = info!=null && !(reg.test(info)) ;
			var errorSpan = $("#gr_phone_info");
			var ph_reg = /^1\d{10}$/;
			if(!ok){
				errorSpan.text("请输入手机号码");
				errorSpan.show();
				return false;
			}else if(!ph_reg.test(info)){
				errorSpan.text("手机号码不正确");
				errorSpan.show();
				return false;
			}else{
				errorSpan.text("");
				errorSpan.hide();
				return true;
			}
			return true;
			
		}
		//确认收货
		function updateReturnState(state){
			$("#hanle_code").val(state);
			$("#goodsreturnForm").submit();
		}
		//确认发货
		function sellerResend(){
			var ok = checkInputInfo('ship_id','物流公司')&checkInputInfo('ship_no','运单号');
			if(ok){
				$("#sellerResendForm").submit();
			}
		}
		//退款
		function refund(){
			var maxMoney = '${goodsreturnMap.refundMoney }';
			var curMoney = $("#refundMoney").val();
			if(parseFloat(curMoney)>parseFloat(maxMoney)){
				if(confirm("退款金额大于商品出售总金额（"+maxMoney+"）,您确定要退款吗？"))
				 {
					$("#goodsreturnForm").submit();
				 }
			}else{
				$("#goodsreturnForm").submit();
			}
		}
</script>
</head>
<body>

<div class="position">查看退换货详细</div>

<style>
	.ordertab{background:#E7CA96;margin-top:10px;line-height:24px;}
	.ordertab h1{font-weight:bold;font-size:14px;}
	.ordertab tr{background:white;}
	.ordertab td{padding:6px;}
</style>

<table class="ordertab" width="100%" cellspacing="1">
	<tr>
		<td colspan="4">
			<h1>商品信息</h1>
		</td>
	</tr>
	<tr>
		<td>
			订单编号：${goodsreturnMap.order_id }
			&nbsp;<a href="<%=basePath%>order/detail-${goodsreturnMap.order_id}.action" target="_blank">查看订单</a>
			<br/>
			商品名称：${goodsreturnMap.goods_name }<br/>
			状态：${goodsreturnMap.info_state_name }<br/>
		</td>
	</tr>
</table>

<table class="ordertab" width="100%" cellspacing="1">
	<tr>
		<td colspan="4">
			<h1>${goodsreturnMap.biz_type_name }申请</h1>
		</td>
	</tr>
	<tr>
		<td>
			问题描述：<br/>
			${goodsreturnMap.cont_desc }<br/>
			<c:if test="${! empty goodsreturnMap.img_path}">
				商品图片：
				<br/>
				<c:set value="${fn:split(goodsreturnMap.img_path,',')}" var="imgone" />
				<c:forEach items="${imgone}" var="s">
					<a href="${s}" target="_blank"><img src="${s}" width="100" height="100"/></a>&nbsp;
				</c:forEach>
				<br/>
			</c:if>
			${goodsreturnMap.biz_type_name }数量：${goodsreturnMap.applyNum }<br/>
			申请人：${goodsreturnMap.user_name }<br/>
			手机号：${goodsreturnMap.cellphone }<br/>
			申请时间：<fmt:formatDate value="${goodsreturnMap.in_date}" pattern="yyyy-MM-dd HH:mm:ss" /><br/>
		</td>
	</tr>
	
</table>

<!-- 待审核 -->
<c:if test="${goodsreturnMap.info_state==0}">
<sf:form action="goodsreturn/audit.action" id="goodsreturnForm" modelAttribute="goodsreturn">

<div style="margin-top:10px;">
	<button class="tab_btn" type="button" id="passBt"/>通过</button>
	<button class="tab_btn" type="button" id="nopassBt"/>不通过</button>
</div>
 
<table id="noapplyPass" class="ordertab" style="background:white;display:none;" width="100%" cellspacing="1">
	<tr>
		<td  align="left"  width="80px">
			<span class="warnRed">*</span>不通过理由：
		</td>
		<td  align="left">
			<sf:input path="no_reason" cssClass="w400" onblur="checkInputInfo('no_reason','不通过的理由');" maxlength="300"/>
			<sf:errors path="no_reason" id="no_reason_error" cssClass="error"/>
			<span id="no_reason_info" class="erInfo"></span>
		</td>
	</tr>
	<tr>
		<td  align="center"  colspan="2">
			<button class="tab_btn" type="button" name="applyBt"/>提交</button>
		</td>
	</tr>
</table>
<table id="applyPass" class="ordertab" style="background:white;display:none;" width="100%" cellspacing="1">
	<tr>
		<td colspan="2" align="left">
			<strong>您的收获地址</strong>
		</td>
	</tr>
	<tr>
		<td  align="left" width="80px">
			<span class="warnRed">*</span>收货地址：
		</td>
		<td  align="left">
			<span id="selectAreaDiv"></span>
			<span id="area_attr_info" class="erInfo"></span>
		</td>
	</tr>
	<tr>
		<td  align="left">
			<span class="warnRed">*</span>详细地址：
		</td>
		<td  align="left">
			<sf:input path="goodsReturnAddr.addr" id="gr_addr" cssClass="w400" value="${selleraddr.address }" onblur="checkInputInfo('gr_addr','详细地址');"  maxlength="100"/>
			<span id="gr_addr_info" class="erInfo"></span>
		</td>
	</tr>
	<tr>
		<td align="left">
			<span class="warnRed">*</span>姓　　名：
		</td>
		<td align="left">
			<sf:input path="goodsReturnAddr.name" id="gr_name" cssClass="w400" value="${selleraddr.cust_name }" onblur="checkInputInfo('gr_name','收货人姓名');"  maxlength="20"/>
			<span id="gr_name_info" class="erInfo"></span>
		</td>
	</tr>
	<tr>
		<td align="left">
			<span class="warnRed">*</span>手机号码：
		</td>
		<td align="left">
			<sf:input path="goodsReturnAddr.phone" id="gr_phone" cssClass="w400" value="${selleraddr.cell_phone }" onblur="checkPhone();" maxlength="11"/>
			<span id="gr_phone_info" class="erInfo"></span>
		</td>
	</tr>
	<tr>
		<td  colspan="2" align="center">
			<button class="tab_btn" type="button" name="applyBt"/>提交</button>
		</td>
	</tr>
</table>

<sf:hidden path="trade_id" value="${goodsreturnMap.trade_id }"/>
<sf:hidden path="biz_type" value="${goodsreturnMap.biz_type }"/>
<input name="audit_code" type="hidden" id="audit_code"/>

</sf:form>
</c:if>


<!-- 等待买家发货 -->
<c:if test="${goodsreturnMap.info_state==2}">
<sf:form action="goodsreturn/handle.action" id="goodsreturnForm" modelAttribute="goodsreturn">
<sf:hidden path="trade_id" value="${goodsreturnMap.trade_id }"/>
<sf:hidden path="biz_type" value="${goodsreturnMap.biz_type }"/>
<sf:hidden path="applyNum" value="${goodsreturnMap.applyNum }"/>
<input name="hanle_code" type="hidden" id="hanle_code"/>
<table class="ordertab"  width="100%" cellspacing="1">
	<tr>
		<td >
		<h1>提示信息</h1>	 
		</td>
	</tr>
	<tr>
		<td>
			您已同意买家退换货申请，您的收货地址将通过短信发送给买家，您可以电话联系买家发货。收到货物后请点击确认收货按钮。
		</td>
	</tr>
</table>
<table class="ordertab" style="background:white;" width="100%" cellspacing="1">
	<tr>
		<td colspan="4" align="center">
			<button class="tab_btn" type="button" onclick="updateReturnState(2)"/>确认收货</button>
		</td>
	</tr>
</table>
</sf:form>
</c:if>
<!-- 等待卖家发货 -->
<c:if test="${goodsreturnMap.info_state==3}">
<sf:form action="goodsreturn/resend.action" id="sellerResendForm" modelAttribute="inc_shipinfo">
<sf:hidden path="link_id" value="${goodsreturnMap.trade_id }"/>
<input type="hidden" name="biz_type" value="${goodsreturnMap.biz_type }"/>
<table class="ordertab" style="background:white;" width="100%" cellspacing="1">
	<tr>
		<td colspan="2" align="left">
			<strong>物流单信息</strong>
		</td>
	</tr>
	<tr>
		<td colspan="2">
			请填写您的发货物流信息，以便会员在发货过程中查看物流当前运送状态。
		</td>
	</tr>
	<tr>
		<td  align="left" width="80px">
			<span class="warnRed">*</span>物流公司：
		</td>
		<td  align="left">
			<sf:select path="ship_id" onblur="checkInputInfo('ship_id','物流公司');">
       			<sf:option value="">请选择</sf:option>
				<sf:options items="${shipTypeMap}"></sf:options>
       		</sf:select>
       		<sf:errors path="ship_id" cssClass="error"/>
			<span id="ship_id_info" class="erInfo"></span>
		</td>
	</tr>
	<tr>
		<td  align="left">
			<span class="warnRed">*</span>运单号：
		</td>
		<td  align="left">
			<sf:input path="ship_no" onblur="checkInputInfo('ship_no','运单号');" maxlength="30"/>
			<sf:errors path="ship_no" cssClass="error"/>
			<span id="ship_no_info" class="erInfo"></span>
		</td>
	</tr>
	<tr>
		<td align="left">
			&nbsp;发货说明：
		</td>
		<td align="left">
			<sf:textarea path="ship_desc"  cssClass="w400" maxlength="600"/>
			<sf:errors path="ship_desc" cssClass="error"/>
			<span id="ship_desc_info" class="erInfo"></span>
		</td>
	</tr>
	<tr>
		<td align="left">
			 &nbsp;发货凭证：
		</td>
		<td align="left">
			<input type="hidden" name="imgNumLimit" id="imgNumLimit" value="2"/>
			<sf:hidden path="ship_img" maxlength="600"/>
			<input id="file" name="file" type="file" />
			<div id="fileQueue"></div>
			<div class="img-lst"><ul id="imgView"></ul></div>
	        <script>
	        	$(function(){
	        		uploadImgComponent('<%=basePath%>','imgNumLimit','file','fileQueue','ship_img','imgView','seller_resend');
	        	})
	        </script>
	        <sf:errors path="ship_img" cssClass="error"/>
			<span id="ship_img_info" class="erInfo"></span>
		</td>
	</tr>
	<tr>
		<td colspan="2" align="center">
			<button class="tab_btn" type="button" onclick="sellerResend();"/>发货</button>
		</td>
	</tr>
</table>
</sf:form>
</c:if>

<!-- 等待卖家退款 -->
<c:if test="${goodsreturnMap.info_state==6}">
<sf:form action="goodsreturn/sellerRefund.action" id="goodsreturnForm" modelAttribute="goodsreturn">
<sf:hidden path="trade_id" value="${goodsreturnMap.trade_id }"/>
<sf:hidden path="biz_type" value="${goodsreturnMap.biz_type }"/>
<sf:hidden path="info_state" value="${goodsreturnMap.info_state }"/>
<table class="ordertab" style="background:white;" width="100%" cellspacing="1">
	<tr>
		<td align="left" width="80">
			商品价格：
		</td>
		<td align="left">
			${goodsreturnMap.order_price }
		</td>
	</tr>
	<tr>
		<td align="left">
			${goodsreturnMap.biz_type_name }数量：
		</td>
		<td align="left">
			${goodsreturnMap.applyNum }
		</td>
	</tr>
	<tr>
		<td align="left">
			退款金额：
		</td>
		<td align="left">
			<sf:input path="refundMoney"   maxlength="11" value="${goodsreturnMap.refundMoney }"/>
			<sf:errors path="refundMoney" cssClass="error"/>
			<span id="refundMoney_info" class="erInfo"></span>
		</td>
	</tr>
	<tr>
		<td align="left">
			备　　注：
		</td>
		<td align="left">
			<sf:textarea path="refundRemark"  cssClass="w400" maxlength="300"/>
			<sf:errors path="refundRemark" cssClass="error"/>
			<span id="refundRemark_info" class="erInfo"></span>
		</td>
	</tr>
	<tr>
		<td colspan="2" align="center">
			<button class="tab_btn" type="button" onclick="refund();"/>退款</button>
		</td>
	</tr>
</table>
</sf:form>
</c:if>

</body>
</html>
