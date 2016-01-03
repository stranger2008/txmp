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
    		//set_time = interval();
	        setUpTime();
    	}
    	
    	function setUpTime()
    	{
    		//$('#sendCodeNone').attr('disabled', 'disabled');
    		$.ajax({
				type: "get",
				async:false,
				url: "<%=basePath%>getPhoneBySendDate.action?phone="+$("#cellphone").val()+"&timeStamp" + new Date().getTime(),
				cache:false,
				success: function(data, textStatus){
					if(data != ""){
						if(60 > data){
							set_time =  data;
							//alert(set_time);
	        			}
					}else{
						set_time = 0;
					}
				}
			});
    		if(parseInt(set_time) < 60 && parseInt(set_time) >= 1)
    		{  
    			var set_time_s = 60 - parseInt(set_time);
    			set_time = parseInt(set_time) + 1;
    			$('#tip').html('校验码已发出，请注意查收短信，如果没有收到，你可以<span id="timeout"></span>重新发送');
    			$('#timeout').html("在"+set_time_s+"秒后要求系统");
    			setTimeout("setUpTime()",1000);
    			$("#sendCodeNone").attr('disabled', 'disabled');
    		}
    		else
    		{
    			$('#timeout').html('');
    			$("#sendCodeNone").html("<a href='javascript:void(0);' onclick='getCode();' id='sendCode' class='getnum'>获取短信验证码</a>");
    			$("#sendCodeNone").removeAttr("disabled");//启用按钮
    			
    		}
    	}
    	//获取验证码
    	var cell;
    	function getCode(){
    		cell =$("#cellphone").val();
    		if($("#cellphone").val()!="")
	    	{
    			$.ajax({
					type: "get",
					url: "<%=basePath%>safetycenter/bindmobile6.action?phone="+$("#cellphone").val()+"&timeStamp" + new Date().getTime(),
					cache:false,
					success: function(data, textStatus){
						if(data == '1'){
							$('#sendcode').val('再次发送，请等待');
			    			$("#sendCode").removeAttr("onclick");
			    			$('#sendCodeNone').attr('disabled', 'disabled');
							$.ajax({
								type: "get",
								url: "<%=basePath%>sendPhoneCode.action?phone="+$("#cellphone").val()+"&timeStamp" + new Date().getTime(),
								cache:false,
								success: function(data, textStatus){
									if(data == '1'){
										setUpTime();
									}else{
										$('#tip').text('发送失败');
									}
								}
							});
						}else if(data == '0'){
							$('#tip').text('该手机号码已绑定，请更换手机号码');
						}
					}
				});
	    	}
    		else{    			
    			$('#tip').text('您暂无手机号码可验证，请重试!');
    		}
    		//window.location.reload(); 
    	}
    	
		$(function () {
			 $("#testCode").click(function(){
			 	var confirmCode = $("#confirmCode").val();
			 	var cfm = new RegExp("^[0-9]*$");
		    	if(!cfm.test(confirmCode)){
		    		$('#codetip').text('请输入6位验证码!');
        			$("#confirmCode").val("");
      				document.getElementById("confirmCode").focus();
    			}
    			else if(confirmCode.length != 6)
    			{
    				$('#codetip').text('请输入6位验证码!');
      				document.getElementById("confirmCode").focus();
    			}
    			else
    			{
    				$.ajax({
						type: "get",
						url: "<%=basePath%>safetycenter/bindmobile4.action?cellphone="+$("#cellphone").val()+"&code="+confirmCode +"&timeStamp" + new Date().getTime(),
						cache:false,
						success: function(data, textStatus){
							if(data == "1"){
								location.href='<%=basePath %>safetycenter/bindmobile5.action';
							}else if(data == '2'){
								$('#tip').text('绑定手机与新手机号码相同，请更换手机号');
							}else{
								document.getElementById("confirmCode").focus();
								//$('#codetip').text('手机验证码不正确，请重试');
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
            	<h2>修改绑定手机</h2>
                <div class="step step01">
                	<img src="<%=basePath %>inc/membercenter/images/step2-psd.png"/>
                	<ul>
                    	<li class="fore">身份验证</li>
                    	<li class="fore-h">修改绑定手机</li>
                    	<li class="fore">完成</li>

                    </ul>
                </div>
                 
                 <div class="safe-serv">
             
                    <div class="form-psd btm-dh">
                     	<div class="item-psd">
                        	<span class="fl label">新的手机号码：</span>
                            <div class="number-psd fl">
                            	<input id="cellphone" type="text" ></input>
                                <i id="tip">校验码已发出，请注意查收短信，如果没有收到，你可以<span id="timeout"></span>重新发送</i>
                            </div>
                        </div>   
                        <div class="clearfix"></div>
                     	<div class="item-psd">
                        	<span class="fl label">&nbsp;</span>
                            <div class="number-psd fl">
                            	<div id="sendCodeNone"><a id="sendcode" href="javascript:void(0);" class="getnum">再次发送，请等待</a></div>
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
                        <div class="clearfix"></div>
                     	<div class="item-psd">
                        	<span class="fl label">&nbsp;</span>
                            <div class="number-psd fl">
                            	温馨提示：如果您的手机号码已遗失或停机等，请发送邮件至<i>www@qq.com</i>进行申诉
                            </div>
                        </div>
                           
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
