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
		    $("#sendCode").click(function(){
				if($("#cellphone").val() != ""){		
					$.ajax({
						type: "get",
						url: "sendPhoneCode.action?phone="+$("#cellphone").val(),
						success: function(data, textStatus){
							if(data == "1"){
								alert("发送成功，请注意查收");
							}else{
								alert("发送失败，请重试！");
							}
						}
					});
				}
				else
				{
					alert("您暂无手机号码！");
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
                	<img src="<%=basePath %>inc/membercenter/images/step3-psd.png" />
                	<ul>
                    	<li class="fore">身份验证</li>
                    	<li class="fore">修改登录密码</li>
                    	<li class="fore-h">完成</li>
                    </ul>
                </div>
                
                <div class="safe-serv">
                	<div class="form-psd2">
                    <p class=" "><b></b>恭喜您，修改密码成功！</p>
                    <a href="<%=basePath %>user/updatePwSendPhone.action" class=" submit">返回</a>
                    </div>
                </div>
            </div>
        </div>
	</div>
</div>

<div class="clearfix"></div>
<!--底部-->
<%@ include file="/WEB-INF/view/inc/footer.jsp" %>
  </body>
</html>
