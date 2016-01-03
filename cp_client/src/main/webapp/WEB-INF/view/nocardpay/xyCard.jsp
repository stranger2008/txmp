<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="Content-type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, target-densitydpi=high-dpi, maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<link rel="stylesheet" href="<%=basePath%>inc/outapi/nocardpay/css/pay.css">
		<title>填写其他验证信息</title>
	</head>
	<body>
	    <%@ include file="/WEB-INF/view/inc/top.jsp" %>
		<section>
		<div class="pay">
			<div class="progress">
				<ul class="clearfix">
					<li class="step">
						1 填写银行卡号
						<b>></b>
					</li>
					<li class="step-h">
						2 填写其他验证信息
						<b>></b>
					</li>
					<li class="step">
						3 支付成功
					</li>
				</ul>
			</div>
		<form action="<%=basePath %>nocardpay/payOrder.action" id="cardInfo" method="post">
			<div class="payok">
				<ul class="payok-ul">
					<li>
						<span>手机号：</span>
						<label>
							<input type="text" class="date-txt" name="phone" id="xykPhone" style="width:30%" onkeyup="check()" >
						</label>
					</li>
					<li>
						<span>有效期：</span>
						<input type="text" class="date-txt" name="xykCardMouth" id="xykCardMouth" onkeyup="check()">
						<label class="mlr5">
							月
						</label>
						<input type="text" class="date-txt" name="xykCardYear" id="xykCardYear" onkeyup="check()">
						<label class="mlr5">
							年
						</label>
					</li>
					<li>
						<span>卡背面末三位数字：</span>
						<input type="password" class="date-txt" id="cvn2" name="cvn2" onkeyup="check()">
					</li>
					<li>
						<span>持卡人姓名：</span>
						<input type="text" class="psd-txt" id="userName" name="name"  style="width:30%" >
					</li>
					<li>
						<span>持卡人身份证号：</span>
						<input type="text" class="psd-txt" id="xykSfzh" name="sfzh"  style="width:30%" onkeyup="check()">
					</li>
						<input type ="hidden" name="orderNo" id="orderNo" value="${orderNo}"/>
						<input type ="hidden" name="trk" 	 id="trk" 	  value="${cardNo}"/>
						<input type ="hidden" name="cartType" 	 id="cartType" 	  value="${cartType}"/>
						<input type ="hidden" name="yymm" 	 id="yymm" />
					<li>
						<span></span>
						<input type="button" class="next-btn" value="确认支付" onclick="checkValus();" />
					</li>
				</ul>
			</div>
			</form>
		</div>
		</section>
		<script type="text/javascript">
            function check(e){
				var e=e? e:window.event;
				var tarobj=event.srcElement? event.srcElement:event.target;
				if(!(event.keyCode>=48&&event.keyCode<=57)){
					tarobj.value=tarobj.value.replace(/[^\d]/g,'');
				}
			}
			function checkValus(){
				var xykPhone =$("#xykPhone"),
					xykCardMouth=$("#xykCardMouth"),
					xykCardYear=$("#xykCardYear"),
					cvn2=$("#cvn2"),
					userName=$("#userName"),
					xykSfzh=$("#xykSfzh");
					
				if(xykPhone.val().length<1){
					alert("请输入手机号码");
					xykPhone.focus();
					return false;
				}
				if(xykCardMouth.val().length<1){
					alert("请输入开户月");
					xykCardMouth.focus();
					return false;
				}
				
				if(xykCardYear.val().length<1){
					alert("请输入开户年");
					xykCardYear.focus();
					return false;
				}
				
				if(cvn2.val().length<1){
					alert("请输入卡背面末三位数字");
					cvn2.focus();
					return false;
				}
				
				if(userName.val().length<1){
					alert("请输入持卡人姓名");
					userName.focus();
					return false;
				}
				
				if(xykSfzh.val().length<1){
					alert("请输入身份证号码");
					xykSfzh.focus();
					return false;
				}
				
				 if(!xykPhone.val().match(/^1[3|4|5|8][0-9]\d{4,8}$/)){ 
				     xykPhone.focus(); 
				     alert("手机号码输入错误");
				     return false; 
				   } 
				   
				  if(!identityCodeValid(xykSfzh.val())){
				  	 xykSfzh.focus();
				  	 alert("身份证号码输入错误"); 
				     return false; 
				  } 
				  
				$("#yymm").val(xykCardMouth.val()+xykCardYear.val());
				$("#cardInfo").submit();
			}
			
			
			
		function identityCodeValid(code) { 
            var city={11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江 ",31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北 ",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏 ",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外 "};
            var tip = "";
            var pass= true;
            
            if(!code || !/^\d{6}(18|19|20)?\d{2}(0[1-9]|1[12])(0[1-9]|[12]\d|3[01])\d{3}(\d|X)$/i.test(code)){
                tip = "身份证号格式错误";
                pass = false;
            }
            
           else if(!city[code.substr(0,2)]){
                tip = "地址编码错误";
                pass = false;
            }
            else{
                //18位身份证需要验证最后一位校验位
                if(code.length == 18){
                    code = code.split('');
                    //∑(ai×Wi)(mod 11)
                    //加权因子
                    var factor = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 ];
                    //校验位
                    var parity = [ 1, 0, 'X', 9, 8, 7, 6, 5, 4, 3, 2 ];
                    var sum = 0;
                    var ai = 0;
                    var wi = 0;
                    for (var i = 0; i < 17; i++)
                    {
                        ai = code[i];
                        wi = factor[i];
                        sum += ai * wi;
                    }
                    var last = parity[sum % 11];
                    if(parity[sum % 11] != code[17]){
                        tip = "校验位错误";
                        pass =false;
                    }
                }
            }
            return pass;
        }
		</script>
		    <!--搜索开始-->
    <!--底部开始-->
    <%@ include file="/WEB-INF/view/inc/footer.jsp" %>
	</body>
</html>
