<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
<title>找回密码-<%=web_title %></title>
<script type="text/javascript">
			$(function () {		    
				//
				 $("#updatePWD").click(function(){
				 	var old_passwd = $("#oldPwd").val();//新登录密码
				 	var new_passwd = $("#newPwd").val();//确认新密码
				 	var cfm_passwd = /^(\w){6,20}$/;//只能输入6-20个字母、数字、下划线	
			    	if(old_passwd ==""){ 
	        			alert("请输入新登录密码!"); 
	      				document.getElementById("oldPwd").focus();
	    			}
	    			else if(!cfm_passwd.test(old_passwd))
	    			{
	    				alert("新登录密码只能由6-20个字母、数字、下划线组成"); 
	    				$("#oldPwd").val("");
	      				document.getElementById("oldPwd").focus();
	    			}
	    			else if(old_passwd.length<6 || old_passwd.length>20)
	    			{
	    				alert("新登录密码只能由6-20个字母、数字、下划线组成");
	    				$("#oldPwd").val("");
	      				document.getElementById("oldPwd").focus();
	    			}
	    			else if(old_passwd != new_passwd)
	    			{
	    				alert("新登录密码与确认新密码不一致");
	    				$("#oldPwd").val("");
	    				$("#newPwd").val("");
	      				document.getElementById("oldPwd").focus();
	    			}
	    			else
	    			{
	    				document.getElementById("setNewPwdForm").submit();
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
	                <a href="<%=basePath %>findPwd/index.action">找回密码</a> > <a href="javascript:void(0);">填写手机号码</a> > <a href="javascript:void(0);">验证身份</a> > <a href="javascript:void(0);">设置新密码</a>
	            </div>
	        </section>
	    </div>
	    
	    <!--内容开始-->
	    <div class="module">
	        <section class="box_sign">
	            <sf:form method="post" action="findPwd/setNewPwd.action" modelAttribute="setNewPwd" id="setNewPwdForm">
	                <span class="sign_span">新登录密码</span>
	                <span><sf:password path="oldPwd"  cssClass="usrname" maxlength="20"/></span>
	                <sf:errors path="oldPwd" cssClass="error"/>
	                 <span class="sign_span">确认新密码</span>
	                <span><sf:password path="newPwd"  cssClass="usrname" maxlength="20"/></span>
	                <sf:errors path="newPwd" cssClass="error"/>
	                <span><input type="button" class="sign_btn" id="updatePWD" value="下一步"></span>
	            </sf:form>
	        </section>
	    </div>
	    
	</div>
    
    
    <%@ include file="/WEB-INF/view/inc/footer.jsp" %>
</div>


</body>
</html>
