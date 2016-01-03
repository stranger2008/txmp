<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <script type="text/javascript" src="<%=basePath %>inc/js/jquery-1.4.4.min.js"></script>
    <title>页面管理</title>
    <script type="text/javascript">
    	function parseIndex(){
    		$.ajax({
    			type:"get",
    			url:"/xfg_static_html/parseWebIndex.action"
    		});
    		$("#showMessage").html("更新成功");
    		$("#showMessage").fadeOut(2000,function(){
	    		$("#showMessage").html("");
	    		$("#showMessage").show();
    		});
    	}
    </script>
  </head>
  
  <body>
    <a href="javascript:parseIndex();">更新首页</a>
    <span id="showMessage"></span>
  </body>
</html>
