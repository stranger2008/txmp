<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
<title>修改密码-<%=web_title %></title>
 	<script type="text/javascript">
		$(function () {		    
			//
			 $("#updatePWD").click(function(){
			 	var old_passwd = $("#oldPwd").val();
			 	var new_passwd = $("#newPwd").val();
			 	var confirmPW = $("#confirmPW").val();
			 	var cfm_passwd = /^(\w){6,20}$/;//只能输入6-20个字母、数字、下划线	    	
		    	if(old_passwd ==""){ 
        			alert("请输入旧密码!"); 
      				document.getElementById("oldPwd").focus();
    			}
    			else if(new_passwd ==""){ 
        			alert("请输入新密码!"); 
      				document.getElementById("newPwd").focus();
    			}
    			else if(!cfm_passwd.test(new_passwd))
    			{
    				alert("新密码只能由6-20个字母、数字、下划线组成"); 
    				$("#newPwd").val("");
      				document.getElementById("newPwd").focus();
    			}
    			else if(new_passwd.length<6 || new_passwd.length>20)
    			{
    				alert("新密码只能由6-20个字母、数字、下划线组成");
    				$("#newPwd").val("");
      				document.getElementById("newPwd").focus();
    			}
    			else if(new_passwd != confirmPW)
    			{
    				alert("新密码与确认密码不一致");
    				$("#newPwd").val("");
    				$("#confirmPW").val("");
      				document.getElementById("newPwd").focus();
    			}
    			else
    			{
    				document.getElementById("udatePw_form").submit();
    			}				
			});
		})
	</script>
</head>

<body>

<div class="w480">
    
    <%@ include file="/WEB-INF/view/inc/top.jsp" %>
        
    <div class="content">
    
	    <!--当前位置开始-->
	    <div class="module">
	        <section>
	            <div class="position mt5">
	                <a href="<%=basePath %>">首页</a> > <a href="<%=basePath %>user/uccenter.action">用户中心</a> > <a href="<%=basePath %>user/updatePassword.action">修改密码</a>
	            </div>
	        </section>
	    </div>
	    
	    <!--内容开始-->
	    <div class="module">
	        <section class="box_sign">
	            <sf:form method="post" action="user/updatePW.action" modelAttribute="setNewPwd" id="udatePw_form">
	                <span class="sign_span">旧密码</span>
	                <span><sf:password path="oldPwd"  cssClass="usrname" maxlength="20"/></span>
	                <sf:errors path="oldPwd" cssClass="error"/>
	                <span class="sign_span">新密码</span>
	                <span><sf:password path="newPwd"  cssClass="usrname" maxlength="20"/></span>
	                <sf:errors path="newPwd" cssClass="error"/>
	                <span class="sign_span">确认密码</span>
	                <span><input type="password" id="confirmPW" name="confirmPW" class="usrname" maxlength="30"/></span>
	                <span><input type="button" class="sign_btn" id="updatePWD" value="下一步"></span>
	            </sf:form>
	        </section>
	    </div>
	    
	</div>
    
    
    <%@ include file="/WEB-INF/view/inc/footer.jsp" %>
</div>


</body>
</html>
