<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<jsp:useBean id="now" class="java.util.Date" />
<html>
<head>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
<title>商品回收站列表</title>
<script type="text/javascript">
$(document).ready(function(){
	$('#allChk').click(function(){
		var chk = $(this).attr('checked');
		if(chk){
			$('[name=goodsId]').attr('checked', true);
		}
		else{
			$('[name=goodsId]').removeAttr('checked');
		}
	});
	
	$('[name=goodsId]').click(function(){
		var chk = $(this).attr('checked');
		var num = $('[name=goodsId]').length;
		var chkNum = $('[name=goodsId]:checked').length;
		if(num == chkNum){
			$('#allChk').attr('checked', true);
		}
		else{
			$('#allChk').removeAttr('checked');
		}
	});
	$('#delBtn').click(function(){
		var $chk = $('[name=goodsId]:checked');
		var chkNum = $chk.length;
		if(chkNum == 0){
			window.alert('请选择要还原的商品！');
			return;
		}
		
		if(!window.confirm("您确认要还原所选择的商品吗？")){
			return;
		}
		
		var $form = $('#goodsForm');
		$form.attr('action', '<%=basePath%>goodsdel/batchreturn.action');
		$form.submit();
	});
});
function del(){
	if(window.confirm("您确认要删除选中的商品？")){
		return true;
	}
	return false;
}
function re(){
	if(window.confirm("您确认要还原选中的商品？")){
		return true;
	}
	return false;
}
</script>
</head>
<body>
<div class="position">商品列表</div>
	<sf:form action="goodsdel/list.action" id="goodsForm" modelAttribute="goodsQueryForm">
		<table class="search" cellpadding="0" cellspacing="10" width="100%">
               <tbody>
                   <tr>
                       <td>
                       	<sf:input path="key" placeholder="商品名称" maxlength="50" cssClass="w400"/>
                       	<input name="sub" value="搜索" class="sear-btn" type="submit"/>
                       	<input name="sub" value="批量还原" id="delBtn" class="tab_btn" type="submit"/>
					</td>
                   </tr>
               </tbody>
           </table>
            
            <table width="100%" cellpadding="0" cellspacing="0" class="info_tab">
                <tr class="tab_th">
                	<th width="5%"><input type="checkbox" id="allChk"></th>
                	<th width="40%">商品名称</th>
                	<th width="10%">操作</th>
                </tr>
                <c:if test="${not empty pageResult.list}">
                <c:forEach items="${pageResult.list}" var="item">
                <tr class="tab_td" >
                	<td><input type="checkbox" name="goodsId" value="${item['goods_id']}" /></td>
                	<td>
                  		<img src="<h:imgSubstr imgpath="${item['img_path']}"/>" />
                  		<span title="${item['goods_name']}">${fn:length(item['goods_name'])>10?fn:substring(item['goods_name'], 0, 20): item['goods_name']}</span>
                	</td>
                	<td>
                		<a href="<%=basePath%>goodsdel/return.action?goodsId=${item['goods_id']}" onclick="return re();">还原</a><br/>
                	</td>
                </tr>
                </c:forEach>
                </c:if>
            </table>
            <div class="page">${pageBar}</div>

            <c:if test="${empty pageResult.list}">
	            <center>无商品信息</center>
	        </c:if>
</sf:form>
</body>
</html>
