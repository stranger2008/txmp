<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<title>商品详情</title>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
<script type="text/javascript" src="<%=basePath %>inc/js/calendar/WdatePicker.js"></script> 
<script type="text/javascript" src="<%=basePath %>inc/js/ckeditor/ckeditor.js"></script>
<%@ include file="/WEB-INF/view/inc/uploadify/resource.jsp" %>

<script type="text/javascript">
$(document).ready(function(){
	CKEDITOR.replace('goodsDetail');
	$('#goodsForm').submit(function(e){
		
		//e.preventDefault();
	});
	$('#upDate').focus(function(){
		WdatePicker({
			maxDate: '#F{$dp.$D(\'downDate\',{d:1})}',
			readOnly:true
		});
	});
	$('#downDate').click(function(){
		WdatePicker({
			minDate:'#F{$dp.$D(\'upDate\',{d:1})}',
			readOnly:true
		});
	});
	$('#isShip0').click(function(){
		$('#shipPrice').hide();
	});
	$('#isShip1').click(function(){
		$('#shipPrice').show();
	});
	
	var permitTotalImage = 5;
	var canUploadNumber = function(){return permitTotalImage};
	var exceedFunc = function(m, cur){
		if(m <= 0){
			window.alert('您上传的图片数量已达上限，不能再上传！');
			return;
		}
		window.alert('您最多还可以再上传'+m+'张图片！');
	};
	var uploadDone = function(resultObj){
		if(resultObj.isErr()){
			window.alert(resultObj.getErrMsg());
			return;
		}
		addImg(resultObj.getUploadedFileURL());
	};
	
	var removeImageFromInputField = function(imgIndex){
		var $imgInput = $('#imgInput');
		var imgInputVal = $imgInput.val();
		if(!imgInputVal){return;}
		
		var images = imgInputVal.split(',');
		if(imgIndex >= images.length){return;}
		images.splice(imgIndex, 1);
		$imgInput.val(images.length == 0 ? '' : images.join(','));
	};
	var resetImageLiId = function(){
		$('li', $('#imgView')).each(function(i, o){
			$(this).attr('id', 'img' + i);
		});
	};
	var delImg = function(liObject){
		var imgIndex = $(liObject).attr('id').substring(3);
		$('#img' + imgIndex).remove();
		removeImageFromInputField(imgIndex);
		resetImageLiId();

		permitTotalImage++;
		if(permitTotalImage > 0){
			$('#' + setting.fileFieldId).uploadify('disable', false);
		}
	};
	var addImg = function(imgPath){
		var $imgInput = $('#imgInput');
		var imgInputVal = $imgInput.val();
		var imgIndex = 0;
		if(imgInputVal){
			var images = imgInputVal.split(',');
			imgIndex = images.length;
		}
		
		if(imgIndex == 0){
			imgInputVal = '';
		}
		else{
			imgInputVal += ',';
		}
		imgInputVal += imgPath;
		$imgInput.val(imgInputVal);
		 
		showImage(imgPath, imgIndex);
		permitTotalImage--;
		if(permitTotalImage <= 0){
			$('#' + setting.fileFieldId).uploadify('disable', true);
		}
	};
	var showImage = function(imgPath, imgIndex){
		var $imgHtml = $('<li id="img' + imgIndex +'"><img src="'+imgPath+'" width="100" height="100" /><div class="img-button"><a href="'+imgPath+'" target="_blank">查看</a></div></li>');
		var $delImgHtml = $('<a href="javascript:void(0);">删除</a>');
		$imgHtml.find('div').append($delImgHtml);
		$('#imgView').append($imgHtml);
		$delImgHtml.click(function(){delImg($imgHtml[0]);});
	};
	var initImage = function(){
		var $imgInput = $('#imgInput');
		var imgInputVal = $imgInput.val();
		var imgCount = 0;
		var images = [];
		if(imgInputVal){
			images = imgInputVal.split(',');
			imgCount = images.length;
		}
		
		permitTotalImage -= imgCount;
		if(imgCount == 0){return;}
		
		for(var i = 0; i < images.length; i++){
			showImage(images[i], i);
		}
		
	};
	var initFlashState = function(){
		if(permitTotalImage <= 0){
			$('#' + setting.fileFieldId).uploadify('disable', true);
		}
	};
	
	<jsp:include page="/WEB-INF/view/inc/uploadify/setting.jsp" flush="true">
		<jsp:param name="subDirValue" value="goods" />
		<jsp:param name="fileFieldId" value="file" />
		<jsp:param name="exceedMaxSelectedFunc" value="exceedFunc" />
		<jsp:param name="maxSelectedFileFunc" value="canUploadNumber" />
		<jsp:param name="onSWFReadyFunc" value="initFlashState" />
		<jsp:param name="uploadDoneFunc" value="uploadDone" />
	</jsp:include>
	initImage();
});
</script>
</head>
<body>
<div class="position">商品详情</div>
<sf:form method="post" action="goods/save.action" modelAttribute="sellerGoodsWithBLOBs" id="goodsForm" >
<sf:hidden path="goodsId" />
<sf:hidden path="infoattrId" />
<table width="100%" cellpadding="0" cellspacing="8" class="add_tab tr td">
	<tr>
		<td width="15%" class="addtab_tit">所属分类<span>*</span></td>
		<td width="85%">
			${catAttrName}<a href="<%=basePath %>goods/category.action?catAttr=${sellerGoodsWithBLOBs.catAttr}&goodsId=${sellerGoodsWithBLOBs.goodsId}">[重新选择]</a>
			<sf:hidden path="catAttr" />
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td></td></tr>

	<%-- 分类属性列表 --%>
	<c:forEach items="${catAttrList}" var="item" varStatus="loop">
	<tr>
		<td width="15%" class="addtab_tit">${item.attrName}<c:if test="${item.isMust == '1'}"><span>*</span></c:if></td>
		<td width="85%">
			<c:choose>
				<c:when test="${item.attrType == '0'}">
					<input type="hidden" name="goodsAttrList[${loop.index}].attrType" value="0">
					<input type="hidden" name="goodsAttrList[${loop.index}].required" value="${item.isMust == '1' ? true : false}">
					<input type="hidden" name="goodsAttrList[${loop.index}].attrId" value="${item.attrId}">
					<input type="hidden" name="goodsAttrList[${loop.index}].attrName" value="${item.attrName}">
					<input type="hidden" name="goodsAttrList[${loop.index}].sortNo" value="${item.sortNo}">
					<input type="hidden" name="goodsAttrList[${loop.index}].attrValueList[0].tradeId" value="${item.attrValueList[0].tradeId}">
					<input type="text"   name="goodsAttrList[${loop.index}].attrValueList[0].attrValue" class="w400"
						value="${sellerGoodsWithBLOBs.goodsAttrList[loop.index].attrValueList[0].attrValue}">
				</c:when>
				<c:when test="${item.attrType == '2'}">
					<input type="hidden" name="goodsAttrList[${loop.index}].attrType" value="2">
					<input type="hidden" name="goodsAttrList[${loop.index}].required" value="${item.isMust == '1' ? true : false}">
					<input type="hidden" name="goodsAttrList[${loop.index}].attrId" value="${item.attrId}">
					<input type="hidden" name="goodsAttrList[${loop.index}].attrName" value="${item.attrName}">
					<input type="hidden" name="goodsAttrList[${loop.index}].sortNo" value="${item.sortNo}">
					<c:forEach items="${item.attrValueList}" var="it" varStatus="lo">
					<input type="radio" id="tradeId_${loop.index}_${lo.index}" 
						name="goodsAttrList[${loop.index}].attrValueList[0].tradeId" 
						value="${it.tradeId}" class="chkbox"
						${sellerGoodsWithBLOBs.goodsAttrList[loop.index].attrValueList[0].tradeId == it.tradeId ? 'checked' : ''} />
					<label for="tradeId_${loop.index}_${lo.index}" class="lab-txt">${it.attrValue}</label>
					</c:forEach>
				</c:when>
				<c:when test="${item.attrType == '3'}">
					<input type="hidden" name="goodsAttrList[${loop.index}].attrType" value="3">
					<input type="hidden" name="goodsAttrList[${loop.index}].required" value="${item.isMust == '1' ? true : false}">
					<input type="hidden" name="goodsAttrList[${loop.index}].attrId" value="${item.attrId}">
					<input type="hidden" name="goodsAttrList[${loop.index}].attrName" value="${item.attrName}">
					<input type="hidden" name="goodsAttrList[${loop.index}].sortNo" value="${item.sortNo}">
					<c:set var="valList" value="${fn:split(sellerGoodsWithBLOBs.goodsAttrList[loop.index].attrValueList[0].tradeId, ',')}" />
					<c:forEach items="${item.attrValueList}" var="it" varStatus="lo">
					<input type="checkbox" id="tradeId_${loop.index}_${lo.index}" 
						name="goodsAttrList[${loop.index}].attrValueList[0].tradeId" 
						value="${it.tradeId}" class="chkbox"
						<c:forEach items="${valList}" var="v"><c:if test="${v == it.tradeId}">checked</c:if></c:forEach> />
					<label for="tradeId_${loop.index}_${lo.index}" class="lab-txt">${it.attrValue}</label>
					</c:forEach>
				</c:when>
				<c:otherwise></c:otherwise>
			</c:choose>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="goodsAttrList[${loop.index}]" cssClass="error"/></td></tr>
	</c:forEach>
	<tr>
		<td class="addtab_tit">商品编号<span>*</span></td>
		<td>
			<sf:input path="goodsNo" cssClass="w400" readonly="true" />
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td></td></tr>
	<tr>
		<td class="addtab_tit">商品名称<span>*</span></td>
		<td>
			<sf:input path="goodsName" cssClass="w400" />
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="goodsName" cssClass="error"/></td></tr>
	<tr>
		<td class="addtab_tit">商品品牌</td>
		<td>
			<sf:select path="brandId" items="${brandList }" itemLabel="brandName" itemValue="brandId" />
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td></td></tr>
	<tr>
		<td class="addtab_tit">是否虚拟商品<span>*</span></td>
		<td>
			<sf:radiobutton path="isVirtual" value="0" id="isVirtual0" cssClass="chkbox"/>
			<label for="isVirtual0" class="lab-txt" >是</label>
			<sf:radiobutton path="isVirtual" value="1" id="isVirtual1" cssClass="chkbox" />
			<label for="isVirtual1" class="lab-txt" >否</label>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="isVirtual" cssClass="error"/></td></tr>
	<tr>
		<td class="addtab_tit">销售价<span>*</span></td>
		<td>
			<fmt:formatNumber value="${sellerGoodsWithBLOBs.salePrice}" pattern="#0.00" var="salePriceString" />
			<sf:input path="salePrice" value="${salePriceString}" />
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="salePrice" cssClass="error"/></td></tr>
	<tr>
		<td class="addtab_tit">市场价</td>
		<td>
			<fmt:formatNumber value="${sellerGoodsWithBLOBs.marketPrice}" pattern="#0.00" var="marketPriceString" />
			<sf:input path="marketPrice" value="${marketPriceString}" />
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="marketPrice" cssClass="error"/></td></tr>
	<tr>
		<td class="addtab_tit">运费类型</td>
		<td>
			<sf:radiobutton path="isShip" value="0" id="isShip0" cssClass="chkbox" />
			<label for="isShip0" class="lab-txt" >免运维</label>
			<sf:radiobutton path="isShip" value="1" id="isShip1" cssClass="chkbox" />
			<label for="isShip1" class="lab-txt" >固定费用</label>
			<sf:input path="shipPrice" id="shipPrice" cssStyle="${sellerGoodsWithBLOBs.isShip == '0' ? 'display:none;' : ''}" />
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="shipPrice" cssClass="error"/></td></tr>
	<tr>
		<td class="addtab_tit">上架时间<span>*</span></td>
		<td>
			<sf:input path="upDate" id="upDate" />-
			<sf:input path="downDate" id="downDate"  />
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="upDate" cssClass="error"/><sf:errors path="downDate" cssClass="error"/></td></tr>
	<tr>
		<td class="addtab_tit">初始库存<span>*</span></td>
		<td>
			<sf:input path="nowStock" />
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="nowStock" cssClass="error"/></td></tr>
	<tr>
		<td class="addtab_tit">提醒库存</td>
		<td>
			<sf:input path="warnStock" />
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="warnStock" cssClass="error"/></td></tr>
	<tr>
		<td class="addtab_tit">商品关键字</td>
		<td>
			<sf:input path="goodsWd" cssClass="w400" />
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="goodsWd" cssClass="error"/></td></tr>
	<tr>
		<td class="addtab_tit">商品简介</td>
		<td>
			<sf:textarea path="goodsDesc" cssClass="txt4x1" />
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="goodsDesc" cssClass="error"/></td></tr>
	<tr>
		<td class="addtab_tit">商品图片</td>
		<td>
			<sf:input path="imgPath" cssClass="w400" id="imgInput" style="display:none;"/>
			<input id="file" name="upload_file" type="file" />
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="imgPath" cssClass="error"/></td></tr>
	<tr><td class="addtab_tit"></td><td><div class="img-lst"><ul id="imgView"></ul></div></td></tr>
	<tr>
		<td class="addtab_tit">商品描述</td>
		<td>
			<sf:textarea path="goodsDetail" id="goodsDetail"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="goodsDetail" cssClass="error"/></td></tr>
	<tr>
		<td class="addtab_tit">商家备注</td>
		<td>
			<sf:textarea path="busiRemark" cssClass="txt4x1" />
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="busiRemark" cssClass="error"/></td></tr>
	<tr>
		<td class="addtab_tit"></td>
		<td>
			<button class="tab_btn" type="submit"/>提交</button>
		</td>
	</tr>
</table>
</sf:form>
</body>
</html>
