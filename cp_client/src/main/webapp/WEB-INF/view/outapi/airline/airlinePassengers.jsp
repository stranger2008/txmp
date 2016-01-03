<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>

<%@ include file="/WEB-INF/view/outapi/airline/com/com.jsp" %>

<title>机票预订-填写乘机人信息-常用乘机人</title>
</head>

<body>
<!--头部开始-->
<header>
	<div class="header margin-center">
    	<div class="return-btn"><a href="<%=basePath %>airLine/searchairline.action"><img src="<%=basePath %>inc/outapi/airline/images/return.png" /></a></div>
        <span class="header-txt2" onclick="javascript:location.href='<%=basePath %>contact/orderInfo.action'">机票预订</span>
        <div class="header-btn"><a href="<%=basePath %>"><img src="<%=basePath %>/inc/outapi/airline/images/home.png" /></a></div>
    </div>
</header>

<section>
<div class="airticket">
    <div class="arkt-tit"><h2>填写乘机人信息</h2><a class="add-user" href="<%=basePath %>passengers/toaddPassenger.action">添加联系人</a></div>
    <form action="<%=basePath %>passengers/orderInfo.action" id="subform" method="get" >
	<div class="arkt-cont">
		<input type="hidden" name="selectPassengers" id ="selectPassengers"/>
		<div id="Error" style="display:block;color:red"></div>
		<div class="user-comm">
        	<ul>
        		<c:forEach items="${passengerlist}" var="pl">
                <li>
                    <span><input type="checkbox" class="chkbox" value="${pl.passenger_id}"  name="passengers" />${pl.name}</span>
                    <a class="fr" href="<%=basePath %>passengers/deletePassenger.action?id=${pl.passenger_id}">[删除]</a><br />
                    <span class="num2">其他：${pl.identityNo }</span>
                </li>
                </c:forEach>
            </ul>
        </div>
        <p class="plr10"><input type="button" class="sign_btn" value="确定" onclick="subform();" /></p>
    </div>
    </form>
</div> 
	<script type="text/javascript">
		function subform(){
			   if($("input[name='passengers']:checked").length > 9){
			     $("#Error").html("乘机人数量最多不能超过九个");
				 return false;
			    }
				if(validateCheckNum()){
				 $("#Error").html("还没有乘机人哦,请先添加乘机人");
				 return false;		
				}
				 var selectvalue='';
				 $("input[name='passengers']:checked").each(function(){
				     if($(this).is(":checked")){
					    selectvalue+=$(this).val()+',';
					  }	     
			   	});
			   	$("#selectPassengers").val(selectvalue);
			   $("#Error").html("");
		       $("#subform").submit();
		        return true;
		    }
		    
		 //验证一个也没有选择就提交
    	function validateCheckNum(){
			  var  num=0;
			  $("input[name='passengers']:checked").each(function(){
			     if($(this).is(":checked")){
				    num=num+1;
				  }	     
			   });
			  if(num<=0){ 
			    return true;
			  }
			  return false;
	    }
		</script>

</section>

<!--底部开始-->
<%@ include file="/WEB-INF/view/inc/footer.jsp" %>
</body>
</html>

