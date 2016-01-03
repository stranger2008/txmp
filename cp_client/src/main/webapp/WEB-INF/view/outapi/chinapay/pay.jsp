<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.xingfugo.outapi.chinapay.PayUtil"%>
<!DOCTYPE html>
<html>
<head>
<title>chinapay</title>
</head>

<body>
	<!-- 
		测试环境：http://payment-test.chinapay.com/pay/TransGet 
		生产环境：https://payment.chinapay.com/pay/TransGet 
	 -->
	<form action="https://payment.chinapay.com/pay/TransGet" METHOD="POST" id="payForm">
		<input type="hidden" name="MerId" value="${payment.MerId}" />
		<input type="hidden" name="OrdId" value="${payment.OrdId}" />
		<input type="hidden" name="TransAmt" value="${payment.TransAmt}" />
		<input type="hidden" name="CuryId" value="${payment.CuryId}" />
		<input type="hidden" name="TransDate" value="${payment.TransDate}" />
		<input type="hidden" name="TransType" value="${payment.TransType}" />
		<input type="hidden" name="Version" value="${payment.Version}" />
		<input type="hidden" name="BgRetUrl" value="${payment.BgRetUrl}" />
		<input type="hidden" name="PageRetUrl" value="${payment.PageRetUrl}" />
		<input type="hidden" name="GateId" value="${payment.GateId}" />
		<input type="hidden" name="Priv1" value="${payment.Priv1}" />
		<input type="hidden" name="ClientIp" value="${payment.ClientIp}" />
		<input type="hidden" name="ChkValue" value="${payment.ChkValue}" />
	</form> 

	<script>
		document.getElementById('payForm').submit();
	</script>

</body>
</html>
