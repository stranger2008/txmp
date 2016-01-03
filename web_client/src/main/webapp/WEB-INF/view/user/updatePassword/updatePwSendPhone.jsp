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
    	var set_time; 
    	$(document).ready(function () {
    		readySetUpTime();
    	});
    	
    	function readySetUpTime()
    	{
    		set_time = interval();
	        setUpTime();
    	}
    	
    	function setUpTime()
    	{
    		if(parseInt(set_time) < 60 && parseInt(set_time) >= 1)
    		{  
    			var set_time_s = 60 - parseInt(set_time);
    			set_time = parseInt(set_time) + 1;
    			document.getElementById("timeout").innerHTML = "在"+set_time_s+"秒后要求系统";
    			setTimeout("setUpTime()",1000);
    		}
    		else
    		{
    			document.getElementById("timeout").innerHTML = "";
    			document.getElementById("sendCodeNone").innerHTML ="<a href='javascript:void(0);' onclick='getCode();' id='sendCode' class='getnum'>获取短信验证码</a>";
    		}
    	}
    	
    	function interval()
    	{
    		var time_s = 0;
    		$.ajax({
				type: "get",
				async:false,
				url: "<%=basePath%>getPhoneBySendDate.action?phone="+$("#cellphone").val(),
				cache:false,
				success: function(data, textStatus){
					if(data != ""){
						if(60 > data){
	        				time_s =  data;
	        			}
					}
				}
			});
			return time_s;
    	}
    	//获取验证码
    	function getCode(){
    		if($("#cellphone").val()!="")
	    	{
    			$("#sendCode").removeAttr("onclick")
    			$("#sendCodeNone").attr("disabled", "disabled");
    			$.ajax({
					type: "get",
					async:false,
					url: "<%=basePath%>sendPhoneCode.action?phone="+$("#cellphone").val(),
					cache:false,
					success: function(data, textStatus){
						if(data == "1"){
							//alert("发送成功");
						}else{
							$('#tip').text("发送失败");
						}
					}
				});
	    	}
	    	else
	    		$('#tip').text("您暂无手机号码可验证，请重试!");
	    	 window.location.reload(); 
    	}
    	
		$(function () {
			 $("#testCode").click(function(){
			 	var confirmCode = $("#confirmCode").val();
			 	var cfm = new RegExp("^[0-9]*$");
		    	if(!cfm.test(confirmCode)){
        			$('#codetip').text("请输入6位验证码!");
        			$("#confirmCode").val("");
      				document.getElementById("confirmCode").focus();
    			}
    			else if(confirmCode.length != 6)
    			{
    				$('#codetip').text("请输入6位验证码!");
      				document.getElementById("confirmCode").focus();
    			}
    			else
    			{
    				$.ajax({
						type: "get",
						url: "<%=basePath%>user/updatePwTestPhone.action?phone="+$("#cellphone").val()+"&confirmCode="+confirmCode,
						cache:false,
						success: function(data, textStatus){
							if(data == "1"){
								location.href='<%=basePath %>user/updatePassword.action';
							}else{
								document.getElementById("confirmCode").focus();
								alert("手机验证码不正确，请重试");
							}
						}
					});
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
                	<img src="<%=basePath %>inc/membercenter/images/step1-psd.png"/>
                	<ul>
                    	<li class="fore-h">身份验证</li>
                    	<li class="fore">修改登录密码</li>
                    	<li class="fore">完成</li>
                    </ul>
                </div>
                 
                 <div class="safe-serv">
             
                 <c:if test="${!empty user.cellphone}">
                    <div class="form-psd btm-dh">
                     	<div class="item-psd">
                        	<span class="fl label">已验证手机：</span>
                            <div class="number-psd fl">
                            	<strong>${cellphone_s}</strong> <input type="hidden" id="cellphone" value="${user.cellphone}"/>
                                <i id="tip">校验码已发出，请注意查收短信，如果没有收到，你可以<span id="timeout"></span>重新发送</i>
                            </div>
                        </div>   
                        <div class="clearfix"></div>
                     	<div class="item-psd">
                        	<span class="fl label">&nbsp;</span>
                            <div class="number-psd fl">
                            	<div id="sendCodeNone"><a href="javascript:void(0);" class="getnum">再次发送，请等待</a></div>
                            </div>
                        </div>   
                    </div>
                    <div class="form-psd">
                     	<div class="item-psd">
                        	<span class="fl label">请填写短信验证码：</span>
                            <div class="number-psd fl">
                            	<input type="text" id="confirmCode" maxlength="6" />
                                <i id="codetip"></i>
                            </div>
                        </div>   
                        <div class="clearfix"></div>
                     	<div class="item-psd">
                        	<span class="fl label">&nbsp;</span>
                            <div class="number-psd fl">
                            	<a href="javascript:void(0);" id="testCode" class=" submit">提交</a>
                            </div>
                        </div>   
                    </div>
                  </c:if>
                  <c:if test="${empty user.cellphone}">
                  	<center>您暂时无法通过手机号码验证，修改登录密码</center>
                  </c:if>
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
