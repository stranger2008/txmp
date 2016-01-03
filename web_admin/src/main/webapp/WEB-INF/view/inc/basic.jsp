<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>
<%@ include file="/WEB-INF/view/inc/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"  
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">  
<html>
<head>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<meta http-equiv="Content-type" content="text/html; charset=UTF-8"/>
<link rel="stylesheet" href="<%=basePath %>inc/css/index.css"/>
<script type="text/javascript" src="<%=basePath %>inc/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="<%=basePath %>inc/js/selectlink.js"></script>
<script type="text/javascript" src="<%=basePath %>inc/js/imgmgr.js"></script>
<link rel="shortcut icon" href="<%=basePath %>favicon.ico" />
<decorator:head />
<title>
	<decorator:title default="Welcome" />-天下名品管理后台
</title>
</head>

<script>
	//高亮一级菜单，显示二级菜单
	function showTwoMenu(val,obj){
		$.ajax({
			type: "get",
			url: "<%=basePath %>public/getdownmenu.action?up_menu_id="+val,
			cache:false,
			success: function(data, textStatus){
				var obj = jQuery.parseJSON(data);
				var str = "";
				for(var i=0;i<obj.length;i++){
					str += "<li><a href='<%=basePath %>"+obj[i].url+"'  class='ltnav-tit'>"+obj[i].menu_name+"</a></li>";
				}
				$(".leftnav-ul").html(str);
			}
		});
		$(".nav ul li a").attr("class","");
		$(obj).attr("class","a_hover");
	}
</script>

<body>

<div class="w980">
    
    <!--头部开始-->
    <div class="top">
        <div class="logo">
            <a href="javascript:void(0);"><img src="<%=basePath %>inc/images/logoa.png"></a>	
        </div>
        <div class="nav">
            <ul>
            	<c:forEach items="${oneMenuList}" var="item" varStatus="status">
            	<li><a href="javascript:void(0);" onclick="showTwoMenu('${item.menu_id }',this);" <c:if test="${item.menu_id==one_menu_id}">class="a_hover"</c:if>>${item.menu_name }</a></li>
            	</c:forEach>
            </ul>
        </div>
        <div class="topa">
        	<!-- 提交成功提示 -->
        	<c:if test="${!empty promptMessage}">
             	<span class="topsuctip">${promptMessage}</span>
             	<script>
             		$(".topsuctip").fadeOut(4000);
             	</script>
            </c:if>
        	<span>你好，${sessionScope.session_user_name}</span><a href="<%=basePath %>logout.action">[退出]</a><a href="<%=basePath %>member/index.action">桌面</a><a href="<%=basePath %>sysuser/update-personal.action">个人资料</a>
        </div>
    </div>
	
    <!--左边导航开始-->
    <div class="tbl-type">
        <div class="leftnav tbl-cell">
            <ul class="leftnav-ul">
                <c:forEach items="${twoMenuList}" var="item" varStatus="status">
            	<li><a href="<%=basePath %>${item.url }"  class="ltnav-tit">${item.menu_name }</a></li>
            	</c:forEach>
            	
            </ul>
        </div>
        <div class="tbl-cell">
            <div class="sear-info">
            	<decorator:body />
            </div>
        </div>
    </div>
    <div class="footer">
        <div class="footer-cont"><p>©2014 天下名品<!--  400 400 4000 --></p></div>
    </div>
    
</div>

<script type="text/javascript">

	//列表滑过变色
	$(".tab_td").mouseover(function(){
		$(this).attr("class","tab_td tab_td_hover")
	});
	$(".tab_td").mouseout(function(){
		$(this).attr("class","tab_td")
	});

	//列表选择checkbox，实现多选
	$(".tab_th input[type='checkbox']").click(function(){
		if($(this).attr("checked") == true){
			$(".tab_td input[type='checkbox']").attr("checked","checked");
		}else{
			$(".tab_td input[type='checkbox']").attr("checked","");
		}
	});
	
	//删除按钮通用事件
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
	
	//搜索按钮点击提交
	$("#searchInfo").click(function(){
		$("#queryForm").submit();
	});
</script>

</body>
</html>
