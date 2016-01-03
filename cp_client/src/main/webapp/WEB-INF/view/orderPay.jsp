<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta name="author" content="uc">
		<meta http-equiv="Content-Type" content="application/vnd.wap.xhtml+xml; charset=utf-8">
		<meta http-equiv="Cache-Control" content="no-cache">
		<meta name="viewport" content="width=device-width; initial-scale=1.0; minimum-scale=1.0; maximum-scale=2.0">
		<title>订单支付</title>
		<style type="text/css">
			body {TEXT-ALIGN: center;}
		</style>
		<script type="text/javascript">
		function autoclick(name)
		{  
		    if(document.all)  
		    {  
		        document.getElementById(name).click();  
		    }  
		    else  
		    {  
		        var evt = document.createEvent("MouseEvents");  
		        evt.initEvent("click", true, true);  
		        document.getElementById(name).dispatchEvent(evt);  
		    }  
		}

		</script>
	</head>
<body onload="javascript:autoclick('autoRedirect');">
	<div>
		<a id="autoRedirect" href="uppay://uppayservice/?style=token&paydata=${paydata}"><img src="<%=basePath%>/inc/images/loading.gif"/></a>
	</div>
</body>
</html>