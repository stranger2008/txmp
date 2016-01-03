<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<title>选择商品分类</title>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
<script type="text/javascript">
$(document).ready(function(){
	var chkCategory = function(){
		var $div = $('#selectDivModel');
		var $catList = $('[name=select_catAttr]', $div);
		var size = $catList.length;
		var catAttrName = '';
		for(var i = 1; i <= size; i++){
			var $sel = $('#catAttr' + i, $div);
			var haveOption = $sel.find('option').length > 1;
			if(haveOption && !$sel.val()){
				$sel.focus();
				window.alert('请选择商品所属分类！');
				return false;
			}
		}
		
		var $cat = $('#field_catAttr', $div);
		var selCatVal = $cat.val();
		if(selCatVal.lastIndexOf(',') == selCatVal.length - 1){
			$cat.val(selCatVal.substring(0, selCatVal.length - 1));
		}
		
		return true;
	};
	
	$('#categoryForm').submit(function(e){
		if(!chkCategory()){
			e.preventDefault();
		}
	});
});
</script>
</head>
<body>
<div class="position">选择商品分类</div>
<c:set var="url">goods/new.action</c:set>
<c:if test="${not empty goodsId}"><c:set var="url">goods/detail/${goodsId}.action</c:set></c:if>
<form method="post" action="${url}" id="categoryForm" >
<input type="hidden" name="goodsId" value="${goodsId}" />
<table width="100%" cellpadding="0" cellspacing="8" class="add_tab tr td">
	<tr>
		<td width="25%" class="addtab_tit">请选择商品所属分类<span>*</span></td>
		<td width="75%">
		</td>
	</tr>
	<tr>
		<td class="addtab_tit"></td>
		<td>
			<span id="selectDivModel"></span>
			<script type="text/javascript">
			showSelectCascade("<%=basePath %>","category","selectDivModel","catAttr","${catAttr}");
			</script>
		</td>
	</tr>
	<tr>
		<td class="addtab_tit"></td>
		<td>
			<button class="tab_btn" type="submit"/>下一步</button>
		</td>
	</tr>
</table>
</form>
</body>
</html>
