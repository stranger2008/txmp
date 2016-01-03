<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<img src="<%=basePath %>checkCode.action" width="100" height="25" id="Verify" style="cursor:pointer;vertical-align:middle;" title="看不清，换一张" onclick="reloadCheckcode();"/>&nbsp;
<a href="javascript:void(0);" onclick="reloadCheckcode();" style="text-decoration:underline;">换一张</a>
<script>
   function reloadCheckcode(){
      $("#Verify").attr("src","<%=basePath %>checkCode.action?timestamp="+new Date().getTime());
   }
</script>