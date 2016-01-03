<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<jsp:useBean id="now" class="java.util.Date" />
<html>
<head>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
<title>商品列表</title>
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
	
	$('#newBtn').click(function(){
		window.location.href = '<%=basePath%>goods/category.action';
	});
	
	$('#delBtn').click(function(){
		var $chk = $('[name=goodsId]:checked');
		var chkNum = $chk.length;
		if(chkNum == 0){
			window.alert('请选择要删除的商品！');
			return;
		}
		
		if(!window.confirm("您确认要删除所选择的商品吗？")){
			return;
		}
		
		var $form = $('#goodsForm');
		$form.attr('action', '<%=basePath%>goods/batchDel.action');
		$form.submit();
	});
});
function del(){
	if(window.confirm("您确认要删除选中的商品？")){
		return true;
	}
	return false;
}
</script>
</head>
<body>
<div class="position">商品列表</div>
	<sf:form action="goods/list.action" id="goodsForm" modelAttribute="goodsQueryForm">
		<table class="search" cellpadding="0" cellspacing="10" width="100%">
               <tbody>
					<tr>
						<td>
							<sf:hidden path="info_state" id="info_state" />
							<c:forEach items="${goodsStatusCounts}" var="item">
                        		<a href="javascript:void(0);" onclick="document.getElementById('info_state').value='${item.info_state }';document.getElementById('goodsForm').submit();" <c:if test="${item.info_state == goodsQueryForm.info_state}">style="color:red;"</c:if>>
                        		<c:if test="${item.info_state == '0' }">待审核</c:if>
                        		<c:if test="${item.info_state == '1' }">正常</c:if>
                        		<c:if test="${item.info_state == '2' }">审核未通过</c:if>
                        		<c:if test="${item.info_state == '3' }">禁用</c:if>
                        		(${item.cnt})</a>&nbsp;
                        	</c:forEach>
						</td>
					</tr>
                   <tr>
                       <td>
                       	<sf:input path="key" placeholder="商品名称" maxlength="50" cssClass="w400"/>
                       	<input name="sub" value="搜索" class="sear-btn" type="submit"/>
                       	<input name="sub" value="新增商品" id="newBtn" class="tab_btn" type="button"/>
					</td>
                   </tr>
               </tbody>
           </table>
            
            <table width="100%" cellpadding="0" cellspacing="0" class="info_tab">
                <tr class="tab_th">
                	<th width="40%">商品名称</th>
                	<th width="10%">商品售价</th>
                	<th width="10%">当前库存</th>
                	<th width="10%">状态</th>
                	<th width="10%">上架时间</th>
                	<th width="10%">操作</th>
                </tr>
                <c:if test="${not empty pageResult.list}">
                <c:forEach items="${pageResult.list}" var="item">
                <tr class="tab_td" >
                	<td>
                  		<img src="<h:imgSubstr imgpath="${item['img_path']}"/>" />
                  		<span title="${item['goods_name']}">${fn:length(item['goods_name'])>10?fn:substring(item['goods_name'], 0, 20): item['goods_name']}</span>
                	</td>
                	<td>￥${item['sale_price'] }</td>
                	<td>${item['now_stock'] }</td>
                	<td>
                		<c:if test="${item['info_state'] == '0' }">待审核</c:if>
                		<c:if test="${item['info_state'] == '1' }">
                			${(now lt item['up_date']) ? '未上架' : ((now gt item['down_date']) ? '已下架' : '已上架') }
						</c:if>
                		<c:if test="${item['info_state'] == '2' }">审核未通过</c:if>
                		<c:if test="${item['info_state'] == '3' }">禁用</c:if>
                		</td>
                	<td><fmt:formatDate value="${item['in_date']}" pattern="yyyy-MM-dd" /></td>
                	<td>
                		<a href="<%=basePath%>goods/detail/${item['goods_id']}.action">修改</a><br/>
                		<a href="<%=basePath%>goods/del.action?goodsId=${item['goods_id']}" onclick="return del();">删除</a>
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
