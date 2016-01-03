<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>申请修退换详细页</title>
	<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link rel="stylesheet" href="<%=rootPath %>css/public.css" />
	<link rel="stylesheet" href="<%=rootPath %>css/account.css" />
	 
	<script src="<%=rootPath %>js/jquery.min.js" type="text/javascript"></script>
	<script src="<%=rootPath %>js/core.js"></script>
	<script src="<%=rootPath %>js/account.js"></script>
	<%@ include file="/WEB-INF/view/inc/uploadify/imgInc.jsp" %>
	<style type="text/css">
	.erInfo{
		display:none;color:red;
	}
	.error{
		color:red;
	}
	</style>
	<script type="text/javascript">
		//改变提交数量
		function changeApplyNum(num){
			num = parseInt(num)
			var applyNmObj = $("#applyNum");
			var maxNum = $("#maxApplyNum").val();
			var inputNum = applyNmObj.val();
			var nowNum =  parseInt(inputNum);
			var msg = "";
			if(nowNum > maxNum && num > 1){
				applyNmObj.val(maxNum);
				msg = "可提交数量不能超过" + maxNum;
			}else if(nowNum < 1 && num > 1){
				applyNmObj.val(1);
				msg = "可提交数量不能小于1" ;
			}else if(num==1||num==-1){
				nowNum = nowNum+num;
				if(nowNum<1){
					nowNum = 1;
				}
				if(nowNum>maxNum){
					msg = "可提交数量不能超过" + maxNum;
					nowNum = maxNum;
				}
				applyNmObj.val(nowNum);
			}
			if(msg!=""){
				alert(msg); 
			}
		}
		//验证提单凭证是否选择
		function checkProof(){
			var ok = false; 
			var proof_Num = $(":checkbox[name='applyProof'][checked]").length;
			ok = proof_Num > 0 ;
			if(ok){
				$("#proof_error_info").text("");
				$("#proof_error_info").hide();
				 
			}else{
				$("#proof_error_info").text("请勾选申请凭据");
				$("#proof_error_info").show();
			}
			return ok;
		}
		//验证问题描述信息
		function checkReasonCon(){
			var ok = false; 
			var info = $("#cont_desc").html();
			info = info==""? ($("#cont_desc").val()):info ;
			var reg = /^\s*$/g; 
			ok = info!=null && !(reg.test(info)) ;
			if(ok){
				if(info.length>500){
					$("#cont_error_info").html("问题描述信息不能超过500字");
					$("#cont_error_info").show();
					return false;
				}else{
					$("#cont_error_info").html("");
					$("#cont_error_info").hide();
					return true;
				}
				 
			}else{
				$("#cont_error_info").html("请输入问题描述信息");
				$("#cont_error_info").show();
				return false;
			}
		}
		//验证输入框信息不能为空
		function checkAddressInfo(inputId,nm , errorId){
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
				errorSpan.text("请输入"+nm+"信息");
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
			var info = $("#Addr_phone").val();
			var reg = /^\s*$/g; 
			var ok = info!=null && !(reg.test(info)) ;
			var errorSpan = $("#Addr_phone_info");
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
		function checkInfo(){
			var ok = checkProof()& checkReasonCon()&checkAddressInfo('addr','详细地址')&checkAddressInfo('Addr_name','姓名')&checkPhone()&checkArea(); 
			return ok;
		}
		$(function(){
			//默认选择换货
			$(":radio[name='biz_type'][value='0']").attr("checked", true);
			$(":radio[name='returnType'][value='0']").attr("checked", true);
		 
			$("#applyReturnBt").click(function (){
				if(checkInfo()){
					$("#applyReturnForm").submit();
				}
			});
			//手动修改申请数量
			$("#applyNum").bind('change', function () {
				changeApplyNum(2);
			});
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
        	<div class="salerule">
            	<h3>售后服务规则</h3>
                <table width="100%" cellpadding="0" cellspacing="0" class="salerule-tab">

                	<tr>
                    	<th>退换类别</th><th>退换原因</th><th class="wdh">商品签收7天内是否支持退换货</th><th class="wdh">商品签收8-15天是否支持换货</th><th class="wdh2 noborder-r">商品签收15天以上在质保期内是否支持保修</th>
                    </tr>
                	<tr>
                    	<td>产品瑕疵</td><td>服饰有明显破洞、严重脱线；</td><td>是</td><td>是</td><td class="noborder-r">是</td>
                    </tr>
                	<tr>
                    	<td>使用安全</td><td>不符合国家《三包规定》《食品安全法》规定的商品</td><td>是</td><td>是</td><td class="noborder-r">是</td>
                    </tr>
                	<tr>
                    	<td>产品过期</td><td>实际收到商品时间距离产品保质期不足三个月</td><td>是</td><td>是</td><td class="noborder-r">是</td>
                    </tr>
                	<tr>
                    	<td>物流损</td><td>因物流运输导致商品损坏、残缺，无法正常使用</td><td>是</td><td>是</td><td class="noborder-r">是</td>
                    </tr>
                	<tr>
                    	<td>商品实物与网站不符</td><td>实际收到 商品实物与网页介绍规格参数中的内容不符</td><td>是</td><td>是</td><td class="noborder-r">是</td>
                    </tr>
                	<tr>
                    	<td>错买、多买</td><td>错买、多买商品</td><td>是</td><td>是</td><td class="noborder-r">是</td>
                    </tr>
                </table>
            	<h3>服务说明:</h3>
                <p>1. 出于安全和卫生考虑，该商品为特殊商品，一经售出，无质量问题无法办理退换货，经权威部门检测商品存在内在质量问题者除外。<br />
                2. 商品外包装、附件、赠品及发票，退货时请一并退回，换货时，赠品及发票无需返回。<br />
                3. 关于物流损：请您在收货时务必仔细验货，如发现商品外包装或商品本身外观存在异常，需当场向配送人员指出，并拒收整个包裹；如您在收货后发现外观异常，请在收货24小时内
                   提交退换货申请。如超时未申请，将无法受理。<br />
                4. 关于商品实物与网站描述不符：天下名品保证所出售的商品均为正品行货，并与时下市场上同样主流新品一致。但因厂家会在没有任何提前通知的情况下更改产品包装、产地或者一
                   些附件，所以我们无法确保您收到的货物与商城图片、产地、附件说明完全一致。<br />
                5. 如果您在使用时对商品质量表示质疑，您可出具相关书面鉴定，我们会按照国家法律规定予以处理。
                
                </p>
            </div>
            <table width="100%" cellpadding="0" cellspacing="0" class="goods-tab">

                <tr>
                    <th>商品名称</th><th>包装清单</th><th>赠送清单</th><th>购买数量</th>  
                </tr>     
                <tr>
                    <td class="goods-img">
                    <a href="###"><img src="${orderdetailMap.img_path }"><span> ${orderdetailMap.goods_name } </span></a></td>
                    <td class="give">-</td>
                    <td>-</td>
                    <td class="noborder-r">${orderdetailMap.order_num }
                    	<input type="hidden"  id="maxApplyNum" value="${orderdetailMap.order_num }"/>
                    </td>
                </tr>
            </table>
            
            <sf:form action="user/goodsReturn_refund.action" id="applyReturnForm" modelAttribute="goodsreturn">
            
            <sf:hidden path="order_id" value="${orderdetailMap.order_id}"/>
            <sf:hidden path="goods_id" value="${orderdetailMap.goods_id}"/>
            <sf:hidden path="trade_id" value="${orderdetailMap.trade_id}"/>
        	<div class="salerule">
            	<h3>服务单明细</h3>
                <div class="detailed">
                	<div class="form-dtl">
                    	<strong><em>*</em>服务类型：</strong><span>
                    	<sf:radiobuttons path="biz_type" items="${returnTypeMap}"/>
                    	</span>
                    	<sf:errors path="biz_type" cssClass="error"/>
                    </div>
                	<div class="form-dtl">
                    	<strong><em>*</em>提交数量：</strong><span><a href="javascript:void(0);" class="add" onclick="changeApplyNum(-1);">-</a>
                    	<sf:input path="applyNum" cssClass="num-dtl" value="1"/>
                    	<a href="javascript:void(0);" class="substract" onclick="changeApplyNum(1)">+</a>
                    	</span>
                    	<sf:errors path="applyNum" cssClass="error"/>
                    </div>
                	<div class="form-dtl">
                    	<strong><em>*</em>申请凭据：</strong><span>
                    	<sf:checkbox path="applyProof" value="0" onclick="checkProof();"/><label>有发票</label>
                    	<sf:checkbox path="applyProof" value="1" onclick="checkProof();"/><label>有检测报告</label>
                    	</span>
                    	<sf:errors path="applyProof" cssClass="error"/>
                    	<span id="proof_error_info" class="erInfo"></span>
                    </div>
                	<div class="form-dtl">
                    	<div><strong class="tit"><em>*</em>问题描述：</strong>
                    	<span> <sf:textarea path="cont_desc" cssClass="dtl-texta" onblur="checkReasonCon();"/></span>
                    	<sf:errors path="cont_desc" cssClass="error"/>
                    	</div>
                    	<div><strong></strong>
                    	<span id="cont_error_info" class="erInfo"></span>
                    	<span>请您如实填写申请原因及商品情况，字数在500字内。</span></div>
                    </div>
                	<div >
                    	<div class="form-dtl fl"><strong>图片信息：</strong>
                    	</div>
                    	<div class="fl" style="margin-top:10px; width:550px;">
	                    	<input type="hidden" name="imgNumLimit" id="imgNumLimit" value="5"/>
					        <sf:hidden path="img_path" id="img_path"/>
							<input id="file" name="file" type="file" />
					        <div id="fileQueue"></div>
							<div class="img-lst"><ul id="imgView"></ul></div>
					        <script>
					        	uploadImgComponent('<%=basePath%>','imgNumLimit','file','fileQueue','img_path','imgView','refund')
					        </script>
                    	</div>
                    	<div class="clearfix"></div>
                    	<div class="form-dtl">
	                    	<div><strong></strong><span>为了帮助我们更好的解决问题，请您上传图片</span></div>
	                    	<!-- 最多可上传5张图片，每张图片大小不超过5M， -->
	                    	<div><strong></strong><span><i>支持bmp,gif,jpg,png,jpeg格式文件</i></span></div>
                    	</div>
                    </div>
                    
                	<div class="form-dtl">
                    	<div><strong><em>*</em>商品返回方式：</strong><span>
                    		<sf:radiobutton path="returnType" value="0"/> <label>上门取件</label>
                    		<i>（天下名品将在1-3天内为您上门取回商品）</i></span>
                    	</div>
                    	<div><strong></strong><span>
                    	<sf:radiobutton path="returnType" value="1"/> <label>快递至天下名品</label>
                    	<i>（邮寄地址网站将在审核通过后以短信形式告知您，您可以“查看返修/退换货记录”中查询，暂时不支持邮寄的货到付款）</i></span></div>
                    </div>
                	 
                	<div class="form-dtl">
                    	<div>
                        <strong><em>*</em>收货地址：</strong>
                        <span id="selectDivModel"></span>
						<script type="text/javascript">
						$(function(){
							showSelectCascade('<%=basePath %>','area','selectDivModel','goodsReturnAddr.area_attr','${defultAddr.area_attr}');
						});
						</script>
							<sf:errors path="goodsReturnAddr.area_attr" cssClass="error"/>
							<span id="area_attr_info" class="erInfo"></span>
                        </div>
                    	<div><strong></strong>
                    		<span>
                    		<sf:input path="goodsReturnAddr.addr" id="addr" cssClass="adres-txt" value="${defultAddr.address }" onblur="checkAddressInfo('addr','详细地址')" maxlength="100"/>
                    		</span>
                    		<sf:errors path="goodsReturnAddr.addr" cssClass="error"/>
                    		<span id="addr_info" class="erInfo"></span>
                    	</div>
                    </div>
                	<div class="form-dtl">
                    	<div><strong><em>*</em>姓名：</strong><span>
                    	<sf:input path="goodsReturnAddr.name" id="Addr_name" cssClass="name-txt" value="${defultAddr.cust_name }" onblur="checkAddressInfo('Addr_name','姓名');" maxlength="20"/>
                    	</span>
                    	<sf:errors path="goodsReturnAddr.name" cssClass="error"/>
                    	<span id="Addr_name_info" class="erInfo"></span>
                    	</div>
                    	<div><strong><em>*</em>手机号码：</strong><span>
                    	<sf:input path="goodsReturnAddr.phone" id="Addr_phone" cssClass="name-txt"  value="${defultAddr.cell_phone }" onblur="checkPhone();" maxlength="20"/>
                    	</span>
                    	<sf:errors path="goodsReturnAddr.phone" cssClass="error"/>
                    	<span id="Addr_phone_info" class="erInfo"></span>
                    	</div>
                    </div>
                	<div class="form-dtl">
                    	<div><strong></strong><span>
                    	<a href="javascript:void(0);" class="applybtn" id="applyReturnBt">提交申请</a>
                    	</span></div>
                    </div>
                    
                </div>
			</div>
			</sf:form>
        </div>
	</div>
</div>

<div class="clearfix"></div>    
  <!--底部-->
<%@ include file="/WEB-INF/view/inc/footer.jsp" %>

</body>
</html>
