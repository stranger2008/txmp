<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>

<%@ include file="/WEB-INF/view/outapi/airline/com/com.jsp" %>

<title>机票预订-填写行程单</title>
</head>
<body>
<!--头部开始-->
<header>
	<div class="header margin-center">
    	<div class="return-btn"><a href="<%=basePath %>airLine/searchairline.action"><img src="<%=basePath %>/inc/outapi/airline/images/return.png" /></a></div>
        <span class="header-txt2" onclick="javascript:location.href='<%=basePath %>contact/orderInfo.action'">机票预订</span>
        <div class="header-btn"><a href="<%=basePath %>"><img src="<%=basePath %>/inc/outapi/airline/images/home.png" /></a></div>
    </div>
</header>

<section>
<div class="airticket">
    <div class="arkt-tit"><h2>填写行程单</h2></div>
    <form action="" id="form" mehtod="post">
	<div class="arkt-cont">
		<div class="user-comm">
        	<ul>
	                <li>
	                    <span><input type="radio" class="chkbox"   name="jsheet"  value="0" checked />不需要行程单</span>
	                </li>
	                 <li>
	                    <span><input type="radio" class="chkbox"   name="jsheet"  value="1"/>配送行程单</span>
	                </li>
            </ul>
            <div id="Error" style="display:block;"></div>
        </div>
        <p class="plr10"><input type="button" class="sign_btn" value="确定"  onclick="subform();"/></p>
    </div>
    </form>
    <script type="text/javascript">
 			function subform(){
 				if($("input[name='jsheet']:checked").val()==1||$("input[name='jsheet']:checked").val()=='1'){
 					location.href="<%=basePath %>journeysheet/toAddJourneysheet.action?jsheet=1";
 				}else{
 					location.href="<%=basePath %>journeysheet/orderInfo.action?jsheet=0";
 				}
 			}
 	</script>
</div> 
</section>

        
<!--底部开始-->
<%@ include file="/WEB-INF/view/inc/footer.jsp" %>
        
</body>
</html>
