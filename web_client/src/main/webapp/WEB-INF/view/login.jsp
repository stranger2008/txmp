<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link rel="stylesheet" href="<%=basePath %>inc/css/public.css" />
		<link rel="stylesheet" href="<%=basePath %>inc/css/sign.css"/>
		<title>登录</title>
		<script type="text/javascript">
			function onfocusInfo(divNm){
				var infoObj = $('#warmInfo'+divNm);
				 infoObj.show();
				 infoObj.addClass('caution');
				 infoObj.removeClass('caution-err');
				 infoObj.html('<b></b>不能为空');
				 $('#rightInfo'+divNm).removeClass('right-ipt');
			}
			
			function checkInfo(divNm , typeNM){
				var infoObj = $('#warmInfo'+divNm);
				var userNm = $("#"+divNm).val();
				var reg = /^\s*$/g; 
				infoObj.html('<b></b>'+typeNM+'不能为空');
				if(userNm =='' || reg.test(userNm)){
					infoObj.show();
				 	infoObj.removeClass('caution');
				 	infoObj.addClass('caution-err');
				 	$('#rightInfo'+divNm).removeClass('right-ipt');
				}else{
					infoObj.hide();
				 	infoObj.removeClass('caution');
				 	infoObj.removeClass('caution-err');
					$('#rightInfo'+divNm).addClass('right-ipt');
				}
			}
			
			$(function(){
				var info = $("#error_nm").text();
				var infoObj = $('#warmInfo_nm');
				if(info==null || info==''){
					infoObj.hide();
					infoObj.removeClass('caution-err');
					infoObj.removeClass('caution');
					infoObj.html('<b></b>用户名不能为空');
				}else{
					infoObj.removeClass('caution');
				 	infoObj.addClass('caution-err');
				 	infoObj.html('<b></b>'+info);
				 	infoObj.show();
				}
				 
				var pwd_info = $("#error_pwd").text();
				var pwd_infoObj = $('#warmInfo_pwd');
				if(pwd_info==null || pwd_info==''){
					pwd_infoObj.hide();
					pwd_infoObj.removeClass('caution-err');
					pwd_infoObj.removeClass('caution');
					pwd_infoObj.html('<b></b>密码不能为空');
				}else{
					pwd_infoObj.removeClass('caution');
				 	pwd_infoObj.addClass('caution-err');
				 	pwd_infoObj.html('<b></b>'+pwd_info);
				 	pwd_infoObj.show();
				}
			});
			
			$(document).keyup(function(event){
			  if(event.keyCode ==13){
			    $("#nowLogin").trigger("click");
			  }
			});
		</script>
	</head>
	<body>
		<!--头部开始-->
		 <%@ include file="/WEB-INF/view/inc/top.jsp" %>
		<!--登录-->
		<div class="w1200">
			<div class="logo-sign">
				<a href="<%=basePath %>index.action"><img src="<%=basePath %>inc/images/logo-sign.png">
				</a>
				<b>欢迎登录</b>
			</div>
			 <sf:form method="post" action="logon.action" modelAttribute="userLogin">
				<div class="box-sign">
					<div class="form-sn">
						<h2>
							用户登录
						</h2>
						<div class="sign-info">
							<span class="fl"><i>*</i>用户名:</span>
							<div class="fl sign-ipt">
								<sf:input path="user_name" id="_nm" cssClass="sign-txt"  onblur="checkInfo('_nm','用户名');"/>
								<b id="rightInfo_nm"></b>
							</div>
							<div class="fl" id="warmInfo_nm" style="display:none"><b></b>用户名不能为空</div>
							<sf:errors path="user_name" id="error_nm"  cssStyle="display:none"/>
						</div>
						<div class="sign-info">
							<span class="fl"><i>*</i>密码:</span>
							<div class="fl sign-ipt sign-ipt2">
								<sf:password path="passwd" cssClass="sign-txt" id="_pwd"  onblur="checkInfo('_pwd','密码');"/>
								<b id="rightInfo_pwd"></b>
							</div>
							<div class="fl" id="warmInfo_pwd" style="display:none"><b></b>密码不能为空</div>
							<sf:errors path="passwd" id="error_pwd" cssStyle="display:none" />
						</div>
						<div class="sign-info2">
							<input type="checkbox" class="sign-chk" name="autoLogin" id="autoLogin"  value="0"/>
							<label class="at">自动登录</label>
							<label>
								<a href="<%=basePath %>findPwd/verifyUser.action" class="fgps">忘记密码</a>
							</label>
						</div>
						<div class="sign-info2">
							<input type="button" class="sign-btn" value="立即登录" id="nowLogin"/>
						</div>
						<div class="sign-info3">
							<a href="<%=basePath %>signup.action" class="free-lg">免费注册</a>
						</div>
					</div>
					<div class="sign-bg">
						<img src="<%=basePath %>inc/images/sign-img.png"/>
					</div>
				</div>
			</sf:form>
			</div>
		<!--底部-->
		<script >
		$("#nowLogin").click(function(e){
				var username = $("#_nm").val();
				var pwd = $("#_pwd").val();
				var reg = /^\s*$/g; 
				var ok = true;
				if(username =='' || reg.test(username)){
					var infoObj = $('#warmInfo_nm');
					infoObj.show();
				 	infoObj.removeClass('caution');
				 	infoObj.addClass('caution-err');
				 	$('#rightInfo_nm').removeClass('right-ipt');
					ok = false;
				} 
				if(pwd =='' || reg.test(pwd)){
					var infoObj = $('#warmInfo_pwd');
					infoObj.show();
				 	infoObj.removeClass('caution');
				 	infoObj.addClass('caution-err');
				 	$('#rightInfo_pwd').removeClass('right-ipt');
				 	ok = false;
				}
				if(ok){
					$("#userLogin").submit();
				}
			});
		
		</script>
		 <%@ include file="/WEB-INF/view/inc/footer.jsp" %>
	</body>
</html>
