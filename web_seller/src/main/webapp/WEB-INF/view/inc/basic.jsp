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
	<decorator:title default="Welcome" />-天下名品商家后台
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
        	<span>你好，${sessionScope.session_user_name}&nbsp;&nbsp;${sessionScope.session_cust_level_name }</span><a href="<%=basePath %>logout.action">[退出]</a><a href="<%=basePath %>member/index.action">桌面</a>
        </div>
    </div>
	
    <!--左边导航开始-->
    <div class="tbl-type">
        <div class="leftnav tbl-cell">
            <ul class="leftnav-ul">
            	<!-- 
                <li>
                    <a href="Index.html" class="ltnav-tit">商品管理</a>
                    <ul class="subnav-ul">
                        <li class="li_hover"><a href="#" class="subnav-tit">新增商品</a></li>
                        <li><a href="#" class="subnav-tit">商品品牌</a></li>
                        <li><a href="#" class="subnav-tit">商品管理</a></li>
                        <li><a href="#" class="subnav-tit">审核商品</a></li>
                        <li><a href="#" class="subnav-tit">商品灵感</a></li>
                        <li><a href="#" class="subnav-tit">商品咨询</a></li>
                        <li><a href="#" class="subnav-tit">商品评价</a></li>
                    </ul>
                </li>
                -->
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
            </script>

</body>
</html>
