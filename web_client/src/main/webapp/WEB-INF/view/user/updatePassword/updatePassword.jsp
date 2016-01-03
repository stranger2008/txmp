<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
 	<title>修改登录密码</title>
    <%@ include file="/WEB-INF/view/inc/inc.jsp" %>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" href="<%=rootPath %>css/public.css" />
    <link rel="stylesheet" href="<%=rootPath %>css/product.css" />
    <link rel="stylesheet" href="<%=rootPath %>css/account.css" />
    <script src="<%=rootPath %>js/jquery.min.js" type="text/javascript"></script>
    <script src="<%=rootPath %>js/core.js"></script>
    <script src="<%=rootPath %>js/account.js"></script>
    <style type="text/css">
    	.selectpsd{ width:100%;font-size:14px; padding:4px 0;margin-top:5px;color:#666; border:1px solid #d7d7d7;}
    </style>
     <script type="text/javascript">
		$(function () {		    
			//
			 $("#updatePWD").click(function(){
			 	var old_passwd = $("#old_passwd").val();
			 	var new_passwd = $("#new_passwd").val();
			 	var confirmPW = $("#confirmPW").val();
			 	var cfm_passwd = /^(\w){6,20}$/;//只能输入6-20个字母、数字、下划线	    	
		    	if(old_passwd ==""){ 
        			alert("请输入旧密码!"); 
      				document.getElementById("old_passwd").focus();
    			}
    			else if(!cfm_passwd.test(new_passwd))
    			{
    				alert("登录密码只能由6-20个字母、数字、下划线组成"); 
    				$("#new_passwd").val("");
      				document.getElementById("new_passwd").focus();
    			}
    			else if(new_passwd.length<6 || new_passwd.length>20)
    			{
    				alert("登录密码只能由6-20个字母、数字、下划线组成");
    				$("#new_passwd").val("");
      				document.getElementById("new_passwd").focus();
    			}
    			else if(new_passwd != confirmPW)
    			{
    				alert("新密码与确认密码不一致");
    				$("#new_passwd").val("");
    				$("#confirmPW").val("");
      				document.getElementById("new_passwd").focus();
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
<!--头部开始-->
<%@ include file="/WEB-INF/view/inc/top.jsp" %>
<!--头部下搜索框-->
<%@ include file="/WEB-INF/view/user/inc/top.jsp" %>		 
<!--nav开始-->
<%@ include file="/WEB-INF/view/user/inc/nav.jsp" %>
<!--个人中心-->
<div class="w1200">
	<div class="usercenter">
    	<div class="position">
        	<a href="#"><strong>我的天下名品</strong></a><span></span><a href="#">账户安全</a>
        </div>
        
        <!--会员中心左边导航-->
		<%@ include file="/WEB-INF/view/user/inc/left.jsp" %>
			
        <div class="user-info fr">
            <div class="upd-psd">
            	<h2>修改登录密码</h2>
                <div class="step step01">
                	<img src="<%=basePath %>inc/membercenter/images/step2-psd.png"/>
                	<ul>
                    	<li class="fore">身份验证</li>
                    	<li class="fore-h">修改登录密码</li>
                    	<li class="fore">完成</li>
                    </ul>
                </div>
               <sf:form method="post" action="user/updatePW.action" modelAttribute="updatepasswd" id="udatePw_form">
                <div class="safe-serv">
                    <div class="form-psd">
                    <div class="form-psd">
                     	<div class="item-psd">
                        	<span class="fl label"><em>*</em>旧密码：</span>
                            <div class="number-psd fl">
                            	<input type="password" id="old_passwd" name="old_passwd" maxlength="30"/>
                                <i><sf:errors path="old_passwd" cssClass="error"/></i>
                            </div>
                        </div> 
                        <div class="item-psd">
                        	<span class="fl label"><em>*</em>新密码：</span>
                            <div class="number-psd fl">
                            	<input type="password" id="new_passwd"  name="new_passwd" maxlength="30"/>
                                <i></i>
                            </div>
                        </div>
                     	<div class="item-psd">
                        	<span class="fl label"><em>*</em>确认密码：</span>
                            <div class="number-psd fl">
                            	<input type="password" id="confirmPW" name="confirmPW" maxlength="30"/>
                                <i></i>
                            </div>
                        </div>                        	
                        <div class="clearfix"></div>
                     	<div class="item-psd">
                        	<span class="fl label">&nbsp;</span>
                            <div class="number-psd fl">
                            	<input type="button" id="updatePWD" class=" submit" value="提交" />
                            </div>
                        </div>
                    </div>
                </div>
          	  </sf:form>
          	
          	</div>
        </div>
	</div>
</div>

<div class="clearfix"></div>
<!--底部-->
<%@ include file="/WEB-INF/view/inc/footer.jsp" %>
  </body>
</html>
