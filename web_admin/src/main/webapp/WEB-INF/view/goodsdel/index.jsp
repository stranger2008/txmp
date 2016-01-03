<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
<title>商品列表</title>

<script type="text/javascript">
$(document).ready(function() {
	$("#deleteInfo").click(function(){
		var ret = 0;
		$(".tab_td input[type='checkbox']").each(function(){
			if($(this).attr("checked") == true){
				ret = 1;
			}
		});
		if(ret == 0){
			alert("请至少选择一条信息");
		}else{
			if(confirm("确定删除已选择的信息吗？")){
				var actionVal = $("#queryForm").attr("action");
				actionVal = actionVal.replace("index","delete");
				$("#queryForm").attr("action",actionVal);
				$("#queryForm").submit();
			}
		}
	});
	$('#bacthreturn').click(function(){
		var $chk = $('[name=goodsId]:checked');
		var chkNum = $chk.length;
		if(chkNum == 0){
			window.alert('请选择要还原的商品！');
			return;
		}
		
		if(!window.confirm("您确认要还原所选择的商品吗？")){
			return;
		}
		
		var $form = $('#queryForm');
		$form.attr('action', '<%=basePath%>goodsdel/batchreturn.action');
		$form.submit();
	});
});

</script>

<script type="text/javascript">
	//分类模块类型名
	var MODULE_TYPE = 'goods';
	
	//初始化商品分类
	function initData(id, up_cat_id) {
		if(id == null || id == '' || up_cat_id == null || up_cat_id == '') {
			return;
		}
		$.ajax({
			type: 'POST',
			async: false,
			url: '${pageContext.request.contextPath }/category/getcategorys.action',
			data: {up_cat_id: up_cat_id, module_type: MODULE_TYPE},
			dataType: 'json',
			success: function(data) {
				if(data == null) {
					return;
				}
				$.each(data,function(entryIndex,entry){
					$('#' + id).append('<option value="' + entry.cat_id + '">' + entry.cat_name + '</option>');
				});
			},
			error: function(XMLHttpRequest, textStatus, errorThrown) {
				//alert(XMLHttpRequest.status);
				//alert(XMLHttpRequest.readyState);
				//alert(textStatus);
				alert('加载数据失败,请稍后再试');
			}
		});
	}
	
	//页面加载,初始化商品分类查询列表
	$(document).ready(function() {
		var cat_ids = '${goodsQueryForm.cat_attr }';
		if(cat_ids != null && cat_ids != '') {
			var cat_id_Arr = cat_ids.split(',');
			for(var i=0; i<cat_id_Arr.length; i++) {
				$('#cat_attr' + i).val(cat_id_Arr[i]);
				up_cat_id = cat_id_Arr[i];
				initData('cat_attr' + (i + 1), cat_id_Arr[i]);
			}
		}
		
		//启用
		$("#enableInfo").click(function(){
			var ret = 0;
			$(".tab_td input[type='checkbox']").each(function(){
				if($(this).attr("checked") == true){
					ret = 1;
				}
			});
			if(ret == 0){
				alert("请至少选择一条信息");
			}else{
				if(confirm("确定启用已选择的商品吗？")){
					var actionVal = $("#queryForm").attr("action");
					actionVal = actionVal.replace("index","enable");
					$("#queryForm").attr("action",actionVal);
					$("#queryForm").submit();
				}
			}
		});
		
		//禁用
		$("#disableInfo").click(function(){
			var ret = 0;
			$(".tab_td input[type='checkbox']").each(function(){
				if($(this).attr("checked") == true){
					ret = 1;
				}
			});
			if(ret == 0){
				alert("请至少选择一条信息");
			}else{
				if(confirm("确定禁用已选择的商品吗？")){
					var actionVal = $("#queryForm").attr("action");
					actionVal = actionVal.replace("index","disable");
					$("#queryForm").attr("action",actionVal);
					$("#queryForm").submit();
				}
			}
		});
		
	});
	
	//商品分类的change事件
	function loadData(id) {
		//清空子节点的值
		clearChildrenValueCascade(id);
		
		var val = $('#' + id).val();
		
		if(val != null && val != '') {
			$.ajax({
				type: 'POST',
				url: '${pageContext.request.contextPath }/category/getcategorys.action',
				data: {up_cat_id: val, module_type: MODULE_TYPE},
				dataType: 'json',
				success: function(data) {
					if(data == null) {
						return;
					}
					$.each(data,function(entryIndex,entry){
						var children = $('select[pid="' + id + '"]');
						if(children) {
							children.each(function(index, element){
								var cid = element.id;
								$('#' + cid).append('<option value="' + entry.cat_id + '">' + entry.cat_name + '</option>');
							});
						}
		             });
				},
				error: function(XMLHttpRequest, textStatus, errorThrown) {
					//alert(XMLHttpRequest.status);
	                //alert(XMLHttpRequest.readyState);
					//alert(textStatus);
					alert('加载数据失败,请稍后再试');
				}
			});
		}
	}
	
	//清空子节点的值
	function clearChildrenValueCascade(id) {
	
		var children = $('select[pid="' + id + '"]');
		if(children) {
			children.each(function(index, element){
				var cid = element.id;
				clearChildrenValueCascade(cid);
				$('#' + cid).empty();
				$('#' + cid).append('<option value="">请选择</option>');
			});
		}
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

<sf:form action="goodsdel/index.action" id="queryForm" modelAttribute="goodsQueryForm">

<div class="searchdiv">
	<ul>
		
		<li>
			<span>商品名称：</span>
			<sf:input path="goods_name"/>
		</li>
		
		<li>
			<span>商品编号：</span>
			<sf:input path="goods_no"/>
		</li>
		
		<li style="width:36%">
			<span>所属分类：</span>
			<select id="cat_attr0" name="cat_attr" style="width:100px;" onchange="loadData(this.id);">
				<option value="">请选择</option>
				<c:forEach items="${categorys }" var="category">
				<option value="${category.cat_id }">${category.cat_name }</option>
				</c:forEach>
			</select>
			<select id="cat_attr1" name="cat_attr" pid="cat_attr0" style="width:100px;" onchange="loadData(this.id);">
				<option value="">请选择</option>
			</select>
			<select id="cat_attr2" name="cat_attr" pid="cat_attr1" style="width:100px;" onchange="loadData(this.id);">
				<option value="">请选择</option>
			</select>
		</li>
		
	</ul>
</div>

<div class="clearfix"></div>

<div class="kjbutdiv">
	<a href="javascript:void(0);" id="searchInfo">查询</a>
	<a href="javascript:void(0);" id="bacthreturn">批量还原</a>
</div>

<c:if test="${!empty pageResult.list}">

<table width="100%" cellpadding="0" cellspacing="0" class="info_tab">
	<tr class="tab_th">
		<th width="5%"><input type="checkbox" ></th>
		
		<th>商品名称</th>
		
		<th>商品编号</th>
		
		<th>商家名称</th>
		
		<th>所属分类</th>
		
		<th>销售价</th>
		
		<th>查看</th>
	</tr>
	
	<c:forEach items="${pageResult.list}" var="item">

	<tr class="tab_td">
		<td><input type="checkbox" name="goodsId" value="${item.goods_id}"></td>
		
		<td>${item.goods_name}</td>
		
		<td>${item.goods_no}</td>
		
		<td>${item.cust_name}</td>
		
		<td>${item.cate_names}</td>
		
		<td>${item.sale_price}</td>
		
		
		<td>
		<a href="<%=basePath%>goodsdel/view.action?id=${item.goods_id}">查看</a><br/>
		<a href="<%=basePath%>goodsdel/return.action?goodsId=${item.goods_id}" onclick="return re();">还原</a>
		</td>
	</tr>

	</c:forEach>

</table>

</c:if>
		
<div class="page">${pageBar}</div>

</sf:form>
            
</body>
</html>

