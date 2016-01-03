<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
<title>地区管理列表</title>
<script type="text/javascript">
	function arealink(area_id){
		$("#parent_id").val(area_id);
		$("#queryForm").submit();
	}
	
	$(function(){
		//删除分类
		$("#deleteareaInfo").click(function(){
			var ret = 0;
			$(".tab_td input[type='checkbox']").each(function(){
				if($(this).attr("checked") == true){
					ret = 1;
				}
			});
			if(ret == 0){
				alert("请至少选择一条信息");
			}else{
				if(confirm("删除会同时删除子分类，您确定删除已选择的信息吗？")){
					var actionVal = $("#queryForm").attr("action");
					actionVal = actionVal.replace("index","delete");
					$("#queryForm").attr("action",actionVal);
					$("#queryForm").submit();
				}
			}
		}); 
	});
	
</script>
</head>
<body>

<div class="position">地区管理列表</div>

<sf:form action="area/index.action" id="queryForm" modelAttribute="areaQueryForm">

<div class="searchdiv">
	<ul>
		
		<li>
			<span>地区名称：</span>
			<sf:input path="area_name"/>
		</li>
		<li>
			<span>是否开通子站：</span>
			<sf:select path="is_open" >  
                   <option ></option>  
                   <option value="0" >没开通</option>  
                   <option value="1" >开通</option>  
               </sf:select>  
		</li>
		
	</ul>
</div>

<div class="clearfix"></div>

<div class="kjbutdiv">
	<a href="javascript:void(0);" id="deleteareaInfo">删除</a>
	<a href="<%=basePath%>area/add.action?up_area_id=${areaQueryForm.up_area_id}">新增</a>
	<a href="javascript:void(0);" id="searchInfo">查询</a>
</div>

<div style="border-top:1px solid #cccccc ; padding:5px 0 ; " >
<sf:hidden path="up_area_id" id="parent_id"/>
		
		<c:forEach items="${parentareaList}" var="item" varStatus="status">
		<span style="width:100px;"> 
			<a href="javascript:void(0);"  onclick="arealink('${item.area_id}');" 
			
			<c:if test="${item.area_id==areaQueryForm.up_area_id}">style="color:red;"</c:if> >${item.area_name}</a>
			<c:if test="${!status.last}"> > </c:if>
		</span>
		</c:forEach>
	 
</div>

<c:if test="${!empty pageResult.list}">

<table width="100%" cellpadding="0" cellspacing="0" class="info_tab">
	<tr class="tab_th">
		<th width="5%"><input type="checkbox"></th>
		<th>地区名称</th>
		<th>排序</th>
		<th>查看下级</th>
		<th>修改</th>
	</tr>
	
	<c:forEach items="${pageResult.list}" var="item">

	<tr class="tab_td">
		<td><input type="checkbox" name="id" value="${item.area_id}"></td>
		
		<td>${item.area_name}</td>
		<td>${item.sort_no}</td>
		
		<td><a href="<%=basePath%>area/index.action?up_area_id=${item.area_id}">查看下级</a></td>
		
		<td><a href="<%=basePath%>area/update.action?id=${item.area_id}">修改</a></td>
	</tr>

	</c:forEach>

</table>

</c:if>
		
<div class="page">${pageBar}</div>

</sf:form>
            
</body>
</html>

