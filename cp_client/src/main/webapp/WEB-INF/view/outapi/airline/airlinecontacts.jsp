<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/view/outapi/airline/com/com.jsp" %>

<title>机票预订-填写联系人信息</title>
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
    <div class="arkt-tit"><h2>填写联系人信息</h2><a class="add-user" href="<%=basePath %>contact/toaAddContact.action">添加联系人</a></div>
    <form action="<%=basePath %>contact/orderInfo.action" id="form" method="post">
	<div class="arkt-cont">
		<div class="user-comm">
        	<ul>
        		<c:forEach items="${contactList}" var="pl">
	                <li>
	                    <span><input type="radio" class="chkbox" name ="rdoContact"  contact_id="${pl.contact_id }" linkman="${pl.linkman }" linkphone="${pl.linkphone }" linkmail="${pl.linkmail }" otherLinkMethod="${pl.otherLinkMethod }" defaultlinkman="${pl.defaultlinkman }"  />${pl.linkman}</span>
	                    <a class="fr" href="contact/deleteContact.action?id=${pl.contact_id}">[删除]</a><br />
	                    <span class="num2">${pl.linkphone }</span>
	                </li>
                 </c:forEach>
                 <input type="hidden" name="contact_id" id="contact_id"/>
		  		<input type="hidden" name="linkman" id="linkman"/>
		  		<input type="hidden" name="linkphone" id="linkphone"/>
		  		<input type="hidden" name="linkmail" id="linkmail"/>
		  		<input type="hidden" name="otherLinkMethod" id="otherLinkMethod"/>
		  		<input type="hidden" name="defaultlinkman" id="defaultlinkman"/>
            </ul>
            <div id="Error" style="display:block;"></div>
        </div>
        <p class="plr10"><input type="button" class="sign_btn" value="确定"  onclick="subform();"/></p>
    </div>
    </form>
</div> 
	<script type="text/javascript">
				$('input[type="radio"]').click(function(){
				$("#contact_id").val($(this).attr("contact_id"));
				$("#linkman").val($(this).attr("linkman"));
				$("#linkphone").val($(this).attr("linkphone"));
				$("#linkmail").val($(this).attr("linkmail"));
				$("#otherLinkMethod").val($(this).attr("otherLinkMethod"));
				$("#defaultlinkman").val($(this).attr("defaultlinkman"));
				$("#Error").html("");
			});
			function subform(){
			         var  num = 0;
			         $(".chkbox").each(function(){
			         if($(this).is(":checked")){
				   		 num=num+1;
				      }	     
			         });
			         if(num<=0){ 
						$("#Error").html("请选择联系人");
						return false;
			         }
			         $('#form').submit();
			   }
		</script>

</section>

        
<!--底部开始-->
<%@ include file="/WEB-INF/view/inc/footer.jsp" %>
        
</body>
</html>
