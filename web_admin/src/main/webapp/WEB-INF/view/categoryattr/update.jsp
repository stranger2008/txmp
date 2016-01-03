<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<title>修改分类属性</title>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
<style type="text/css">
.ul_li_input li input {
	margin-bottom: 5px;
}
</style>
<script type="text/javascript">
//保存新增修改
function saveAttrValue(trade_id) {
	//清空错误提醒
	$('#attrValueError').text('');
	
	
	var attr_id = '';
	var attr_value = '';
	var url = '${pageContext.request.contextPath }/attrvalue/';
	if(trade_id == '') {
		url = url + 'insert.action';
		attr_id = $('#attr_id').val();
		attr_value = $('#insert_attr_value').val();
	} else {
		url = url + 'update.action';
		attr_value = $('#trade_id_' + trade_id).val();
	}
	$.ajax({
		type: 'POST',
		async: false,
		url: url,
		data: {trade_id: trade_id, attr_id: attr_id, attr_value: attr_value},
		dataType: 'json',
		success: function(data) {
			if(data == null) {
				return;
			}
			if(data.isSuccess) {
				//新增
				var insertForm = '<li>';
				insertForm = insertForm + '<input type="text" id="insert_attr_value" value="" />';
				insertForm = insertForm + '&nbsp;&nbsp;<a href="javascript:void(0);" onclick="saveAttrValue(\'\');">新增</a>';
				insertForm = insertForm + '</li>';
				
				//属性值
				var attrValues = '';
				$.each(data.attrValues,function(entryIndex,entry){
					attrValues = attrValues + '<li>';
					attrValues = attrValues + '<input type="text" id="trade_id_' + entry.trade_id + '" value="' + entry.attr_value + '" />';
					attrValues = attrValues + '&nbsp;&nbsp;<a href="javascript:void(0);" onclick="saveAttrValue(\'' + entry.trade_id + '\');">修改</a>';
					attrValues = attrValues + '&nbsp;&nbsp;<a href="javascript:void(0);" onclick="deleteAttrValue(\'' + entry.trade_id + '\');">删除</a>';
					attrValues = attrValues + '</li>';
				});
				
				//错误信息
				var attrValueError = '<li><span id="attrValueError" class="error"></span></li>';
				
				//刷新属性值
				$('#attrValues').html(insertForm + attrValues + attrValueError);
				
				//提示
				$('.topa').prepend('<span class="topsuctip">' + data.info + '</span>');
				$('.topsuctip').fadeOut(4000);
			} else {
				$('#attrValueError').text(data.info);
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			alert('加载数据失败,请稍后再试');
		}
	});
}

//删除
function deleteAttrValue(trade_id) {
	if(!confirm("确定删除属性值吗？")) {
		return;
	}
	$.ajax({
		type: 'GET',
		async: false,
		url: '${pageContext.request.contextPath }/attrvalue/delete.action',
		data: {trade_id: trade_id},
		dataType: 'json',
		success: function(data) {
			if(data == null) {
				return;
			}
			if(data.isSuccess) {
				//新增
				var insertForm = '<li>';
				insertForm = insertForm + '<input type="text" id="insert_attr_value" value="" />';
				insertForm = insertForm + '&nbsp;&nbsp;<a href="javascript:void(0);" onclick="saveAttrValue(\'\');">新增</a>';
				insertForm = insertForm + '</li>';
				
				//属性值
				var attrValues = '';
				$.each(data.attrValues,function(entryIndex,entry){
					attrValues = attrValues + '<li>';
					attrValues = attrValues + '<input type="text" id="trade_id_' + entry.trade_id + '" value="' + entry.attr_value + '" />';
					attrValues = attrValues + '&nbsp;&nbsp;<a href="javascript:void(0);" onclick="saveAttrValue(\'' + entry.trade_id + '\');">修改</a>';
					attrValues = attrValues + '&nbsp;&nbsp;<a href="javascript:void(0);" onclick="deleteAttrValue(\'' + entry.trade_id + '\');">删除</a>';
					attrValues = attrValues + '</li>';
				});
				
				//错误信息
				var attrValueError = '<li><span id="attrValueError" class="error"></span></li>';
				
				//刷新属性值
				$('#attrValues').html(insertForm + attrValues + attrValueError);
			}
			//提示
			$('.topa').prepend('<span class="topsuctip">' + data.info + '</span>');
			$('.topsuctip').fadeOut(4000);
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			alert('加载数据失败,请稍后再试');
		}
	});
}

//取消事件
function cancel() {
	$('#attrValueForm').html('');
	$('#attrValueError').text('');
}
</script>
</head>
<body>

<div class="position">修改分类属性</div>

<sf:form method="post" action="categoryattr/update.action" modelAttribute="categoryAttr">

<table width="100%" cellpadding="0" cellspacing="8" class="add_tab tr td">

	
	<tr>
		<td class="addtab_tit">属性名称<span>*</span></td>
		<td>
			<sf:input path="attrName"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="attrName" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">所属分类<span>&nbsp;</span></td>
		<td>
			<c:forEach items="${categorys }" var="category" varStatus="vs">
				<c:if test="${vs.index==0 }">
					${category.cat_name }
				</c:if>
				<c:if test="${vs.index!=0 }">
					>${category.cat_name }
				</c:if>
			</c:forEach>
			<sf:hidden path="catAttr" />
			<sf:hidden path="catId" />
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="catAttr" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">属性类型<span>&nbsp;</span></td>
		<td>
			<c:if test="${categoryAttr.attrType == '0' }">单行文本框</c:if>
			<c:if test="${categoryAttr.attrType == '1' }">多行文本框</c:if>
			<c:if test="${categoryAttr.attrType == '2' }">单选按钮</c:if>
			<c:if test="${categoryAttr.attrType == '3' }">复选框</c:if>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="attrType" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">是否必须<span>*</span></td>
		<td>
			<sf:radiobutton path="isMust" value="0"/>非必须
            <sf:radiobutton path="isMust" value="1"/>必须
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="isMust" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">排序号<span>*</span></td>
		<td>
			<sf:input path="sortNo"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="sortNo" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">属性值<span>&nbsp;</span></td>
		<td>
			<!-- 单行文本框 -->
			<c:if test="${categoryAttr.attrType == '0' || categoryAttr.attrType == '1' }">
				<c:if test="${empty attrValues}">
					<textarea name="defaultVal" cssClass="txt4x1"></textarea>
				</c:if>
					
				<c:if test="${fn:length(attrValues) == 1}">
					<textarea name="defaultVal" cssClass="txt4x1">${attrValues[0].attr_value }</textarea>
				</c:if>
					
				<c:if test="${fn:length(attrValues) > 1}">
					<textarea name="defaultVal" cssClass="txt4x1">
						<c:forEach items="${attrValues }" var="attrValue" varStatus="vs">
							${attrValue.attr_value }<c:if test="${vs.count != fn:length(attrValues) }">,</c:if>
						</c:forEach>
					</textarea>(值多于1个,请修正)
				</c:if>
			</c:if>
			
			<!-- 单选按钮/复选框 -->
			<c:if test="${categoryAttr.attrType == '2' || categoryAttr.attrType == '3' }">
			<ul id="attrValues" class="ul_li_input">
				<li>
					<input type="text" id="insert_attr_value" value="" />&nbsp;&nbsp;<a href="javascript:void(0);" onclick="saveAttrValue('');">新增</a>
				</li>
				
				<c:forEach items="${attrValues }" var="attrValue">
				<li>
					<input type="text" id="trade_id_${attrValue.trade_id }" value="${attrValue.attr_value }" />&nbsp;&nbsp;<a href="javascript:void(0);" onclick="saveAttrValue('${attrValue.trade_id }');">修改</a>&nbsp;&nbsp;<a href="javascript:void(0);" onclick="deleteAttrValue('${attrValue.trade_id }');">删除</a>
				</li>
				</c:forEach>
				<li><span id="attrValueError" class="error"></span></li>
			</ul>
			</c:if>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><br></td></tr>
	
	<tr>
		<td class="addtab_tit"><br></td>
		<td>
			<sf:hidden path="attrType" />
			<sf:hidden path="moduleType" />
			<sf:hidden path="attrId" id="attr_id" />
			<button class="tab_btn" type="submit"/>提交</button>
		</td>
	</tr>
</table>

</sf:form>

</body>
</html>

