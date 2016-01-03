<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
<title>个人会员列表</title>
<script type="text/javascript" src="<%=basePath%>inc/com/calendar/WdatePicker.js"></script> 

</head>
<body>

<div class="position">个人会员列表</div>

<sf:form action="memberuser/index.action" id="queryForm" modelAttribute="memberuserQueryForm">

<div class="searchdiv">
	<ul>
		
		<li>
			<span>用户等级：</span>
			<sf:select path="user_level" items="${memlist}" />
		</li>
		
		<li>
			<span>用户名：</span>
			<sf:input path="user_name"/>
		</li>
		
		<li>
			<span>状态：</span>
				<sf:select path="state_code" >  
                   <option ></option>  
                   <option value="0" >正常</option>  
                   <option value="1" >禁用</option>  
               </sf:select>  
			
		</li>
		
		<li>
			<span>Email：</span>
			<sf:input path="email"/>
		</li>
		
		<li>
			<span>真实姓名：</span>
			<sf:input path="real_name"/>
		</li>
		
		<li>
			<span>手机：</span>
			<sf:input path="cellphone"/>
		</li>
		
		<li>
			<span>注册时间从：</span>
			<sf:input path="from_time" id="from_time" cssClass="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\\'to_time\\',{d:-1})}'})" />
		</li>
		
		<li>
			<span>到：</span>
			
			<sf:input path="to_time" id="to_time" cssClass="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\\'from_time\\',{d:1})}'})" />
		</li>
		
	</ul>
</div>

<div class="clearfix"></div>

<div class="kjbutdiv">
	<a href="javascript:void(0);" id="deleteInfo">删除</a>
	<a href="<%=basePath%>memberuser/add.action">新增</a>
	<a href="javascript:void(0);" id="searchInfo">查询</a>
</div>

<c:if test="${!empty pageResult.list}">

<table width="100%" cellpadding="0" cellspacing="0" class="info_tab">
	<tr class="tab_th">
		<th width="5%"><input type="checkbox"></th>
		
		<th>用户名</th>
		
		<th>用户等级</th>
		
		<th>状态</th>
		
		<th>Email</th>
		
		<th>真实姓名</th>
		
		<th>性别</th>
		
		<th>手机</th>
		
		<th>注册时间</th>
		
		<th>修改密码</th>
		
		<th>修改</th>
	</tr>
	
	<c:forEach items="${pageResult.list}" var="item">

	<tr class="tab_td">
		<td><input type="checkbox" name="id" value="${item.user_id}"></td>
		
		<td>${item.user_name}</td>
		
		<td>${item.user_level}</td>
		
		<td>
			<c:if test="${item.state_code == 0}">
				正常
			</c:if>
			<c:if test="${item.state_code == 1}">
				禁用
			</c:if>
		</td>
		
		<td>${item.email}</td>
		
		<td>${item.real_name}</td>
		
		<td>${item.sex}</td>
		
		<td>${item.cellphone}</td>
		
		<td>
			<fmt:formatDate value="${item.login_time}" pattern="yyyy-MM-dd HH:mm:ss"/>
		</td>
		
		<td><a href="<%=basePath%>memberuser/updatepwd.action?id=${item.user_id}">修改密码</a></td>
		<td><a href="<%=basePath%>memberuser/update.action?id=${item.user_id}&&user_level=${item.user_level}&&login_time=${item.login_time}">修改</a></td>
		
	</tr>

	</c:forEach>

</table>

</c:if>
		
<div class="page">${pageBar}</div>

</sf:form>
            
</body>
</html>

