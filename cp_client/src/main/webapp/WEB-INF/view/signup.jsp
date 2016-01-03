<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
<title>注册-<%=web_title %></title>
</head>

<body>

<div class="w480">
    
    <%@ include file="/WEB-INF/view/inc/top.jsp" %>
    
    
	<div class="content">
        
    	<!--当前位置开始-->
    	<div class="module">
            <section>
                <div class="position">
                    <a href="<%=basePath %>">首页</a> > <a href="javascript:void(0);">免费注册</a>
                </div>
            </section>
        </div>
        
        <div class="module">
	        <section class="box_sign">
	            <sf:form method="post" action="regon.action" modelAttribute="userReg" cssClass="reg_cont">
	                <span class="sign_span">请输入手机号</span>
	                <span class="input_span"><sf:input cssClass="usrname" path="cellphone" /><a href="javascript:void(0);" id="sendCode" class="get_btn">获取验证码</a></span>
	                <sf:errors path="cellphone" cssClass="error"/>
	                
	                <span class="sign_span">请输入验证码</span>
	                <span><sf:input cssClass="psd" path="check_code" /></span>
	                <sf:errors path="check_code" cssClass="error" />
	                
	                <span class="sign_span">请输入密码</span>
	                <span><sf:password cssClass="psd" path="passwd" /></span>
	                <sf:errors path="passwd" cssClass="error" />
	                
	                <span class="sign_span">请确认密码</span>
	                <span><sf:password cssClass="psd" path="sure_passwd" /></span>
	                <sf:errors path="sure_passwd" cssClass="error" />
	                
	                <span><input type="submit" class="sign_btn" value="注 册"></span>
	            </sf:form>
	        
	        </section>
	    </div>
        

		<script type="text/javascript">
			$("#sendCode").click(function(){
				if($("#cellphone").val() == ""){
					alert("请输入手机号");
					$("#cellphone").focus();
				}else{
					$.ajax({
						type: "get",
						url: "sendPhoneCode.action?phone="+$("#cellphone").val(),
						success: function(data, textStatus){
							if(data == "1"){
								alert("发送成功");
							}else{
								alert("发送失败");
							}
						}
					});
				}
			});
		</script>
                

    </div>
    
    <!--底部开始-->
    <%@ include file="/WEB-INF/view/inc/footer.jsp" %>
</div>

</body>
</html>

