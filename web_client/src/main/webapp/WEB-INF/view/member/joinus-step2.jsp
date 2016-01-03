<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/WEB-INF/view/inc/inc.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>商家入驻</title>
<%@ include file="/WEB-INF/view/inc/uploadify/imgInc.jsp" %>
<script type="text/javascript" src="<%=basePath%>inc/com/calendar/WdatePicker.js"></script>
<link rel="stylesheet" href="<%=basePath%>inc/css/public.css" />
<link rel="stylesheet" href="<%=basePath%>inc/css/account.css" />
<link rel="stylesheet" href="<%=basePath%>inc/member/css/business.css" />
</head>
<body>
<!--头部开始-->
<%@ include file="/WEB-INF/view/inc/top.jsp"%>

<!--商家入驻流程-->
<sf:form method="post" id="dataForm" action="member/joinus-step3.action" modelAttribute="member">
<div class="border-b">
    <div class="w1200" style="position:relative;">
        <div class="logo-business">
            <a href="###"><img src="<%=basePath %>inc/images/logo-shop.png"></a>
        </div>
        <div class="settle"><a href="javascript:void(0);">入驻天下名品</a></div>
        <div class="nav-business">
            <ul>                                
                <li><a href="商家入驻流程.html">首页</a></li>
                <li class="nav-b-hover"><a href="<%=basePath %>member/joinus.action">商家入驻</a></li>
                <li><a href="#">帮助&资费 </a> </li>
                <li><a href="#">联系我们</a></li>
            </ul>
        </div>
    </div>
</div>
<div class="clearfix"></div>
<div class="seller">
	<div class="seller-step">
		<div class="step-seepro">
			<img src="<%=basePath %>inc/member/images/seller-setp2.png" />
			<ul>
				<li class="fore-h">填写联系方式</li>
				<li class="fore">完善商家基本信息</li>
				<li class="fore">资料审核</li>
				<li class="fore">通知联系并签订合同</li>
				<li class="fore">办理后续手续，店铺上线</li>
			</ul>
		</div>
		<div class="seller-cont">
			
			<h1 class="seller-title">公司及联系方式：</h1>
			<ul class="seller-c-list seller-c-list1">
				<li>
					<label>
						<span>*</span>登录账号：
					</label>
					<sf:input path="user_name"/>
					<sf:errors path="user_name" cssClass="error"/>
				</li>
				<li>
					<label>
						<span>*</span>登录密码：
					</label>
					<sf:password path="passwd"/>
					<sf:errors path="passwd" cssClass="error"/>
				</li>
				<li>
					<label>
						<span>*</span>商家公司名称：
					</label>
					<sf:input path="cust_name" />
					<sf:errors path="cust_name" cssClass="error"/>
				</li>
				<li>
					<label>
						<span>*</span>公司地址：
					</label>
					<span id="selectDivModel"></span>
					<script type="text/javascript">
						showSelectCascade("<%=basePath %>","area","selectDivModel","area_attr","${member.area_attr }");
					</script>
					<sf:errors path="area_attr" cssClass="error"/>
				</li>
				<li>
					<label>
						<span>*</span>公司详细地址：
					</label>
					<sf:input path="address"/>
					<sf:errors path="address" cssClass="error"/>
				</li>
				<li>
					<label>
						<span>*</span>公司电话：
					</label>
					<sf:input path="phone"/>
					<sf:errors path="phone" cssClass="error"/>
				</li>
				<li>
					<label>
						<span>*</span>公司注册资金：
					</label>
					<sf:input path="reg_money"/>
					<sf:errors path="reg_money" cssClass="error"/>
				</li>
				<li>
					<label>
						<span>*</span>公司注册资金单位：
					</label>
					<sf:input path="reg_money_type"/>
					<sf:errors path="reg_money_type" cssClass="error"/>
				</li>
				<li>
					<label>
						<span>*</span>营业执照号码：
					</label>
					<sf:input path="lic_no"/>
					<sf:errors path="lic_no" cssClass="error"/>
				</li>
				<li>
					<label>
						<span>*</span>营业执照所在地：
					</label>
					<sf:input path="lic_address"/>
					<sf:errors path="lic_address" cssClass="error"/>
				</li>
				<li>
					<label>
						<span>*</span>营业执照有效期：
					</label>
					<sf:input path="lic_start_time" id="lic_start_time" readonly="readonly" cssClass="Wdate" style="width:164px" onclick="WdatePicker({maxDate:'#F{$dp.$D(\\'lic_end_time\\',{d:-1})}'})" />
					&nbsp;-&nbsp;
					<sf:input path="lic_end_time" id="lic_end_time" readonly="readonly" cssClass="Wdate" style="width:164px" onclick="WdatePicker({minDate:'#F{$dp.$D(\\'lic_start_time\\',{d:1})}'})" />
					<sf:errors path="lic_start_time" cssClass="error"/><sf:errors path="lic_end_time" cssClass="error"/>
				</li>
				<li>
					<label style="float: left;margin-right: 6px;">
						<span>*</span>法定经营范围：
					</label>
					<sf:textarea path="product" cssStyle="width: 405px;height:70px;border:1px solid #ccc;background:#fbfbfb; font-family: '宋体';font-size: 14px;" />
					<sf:errors path="product" cssClass="error"/>
				</li>
				<li>
					<table width="100%" cellpadding=0 cellspacing=0>
						<tr>
							<td width=185 align=right valign=top>
								<label><span>*</span>法人身份证复印件：</label>
							</td>
							<td valign=top>
								<div style='margin-top:-5px;'>
									<input type="hidden" name="person_id_img_NumLimit" id="person_id_img_NumLimit" value="1"/>
									<sf:hidden path="person_id_img" />
									<input id="person_id_img_file_adv" name="person_id_img_file_adv" type="file" />
									<div id="person_id_img_fileQueue"></div>
									<div class="img-lst"><ul id="person_id_img_imgView"></ul></div>
							        <script>
							        	$(function(){
							        		uploadImgComponent('<%=basePath%>','person_id_img_NumLimit','person_id_img_file_adv','person_id_img_fileQueue','person_id_img','person_id_img_imgView','member');
							        	})
							        </script>
							        <sf:errors path="person_id_img" cssClass="error"/>
								</div>
							</td>
						</tr>
					</table>
				</li>
				<li>
					<table width="100%" cellpadding=0 cellspacing=0>
						<tr>
							<td width=185 align=right valign=top>
								<label><span>*</span>营业执照电子版：</label>
							</td>
							<td valign=top>
								<div style='margin-top:-5px;'>
									<input type="hidden" name="lic_img_NumLimit" id="lic_img_NumLimit" value="1"/>
									<sf:hidden path="lic_img" />
									<input id="lic_img_file_adv" name="lic_img_file_adv" type="file" />
									<div id="lic_img_fileQueue"></div>
									<div class="img-lst"><ul id="lic_img_imgView"></ul></div>
							        <script>
							        	$(function(){
							        		uploadImgComponent('<%=basePath%>','lic_img_NumLimit','lic_img_file_adv','lic_img_fileQueue','lic_img','lic_img_imgView','member');
							        	})
							        </script>
							        <sf:errors path="lic_img" cssClass="error"/>
								</div>
							</td>
						</tr>
					</table>
				</li>
				<li>
					<label>
					</label>
					<em class="a-lan">请使用彩色扫描件，并保证文字与公章的清晰</em>
				</li>
				<li>
					<table width="100%" cellpadding=0 cellspacing=0>
						<tr>
							<td width=185 align=right valign=top>
								<label>
									<span>*</span>组织机构代码证复印件：
								</label>
							</td>
							<td valign=top>
								<div style='margin-top:-5px;'>
									<input type="hidden" name="org_img_NumLimit" id="org_img_NumLimit" value="1"/>
									<sf:hidden path="org_img" />
									<input id="org_img_file_adv" name="org_img_file_adv" type="file" />
									<div id="org_img_fileQueue"></div>
									<div class="img-lst"><ul id="org_img_imgView"></ul></div>
							        <script>
							        	$(function(){
							        		uploadImgComponent('<%=basePath%>','org_img_NumLimit','org_img_file_adv','org_img_fileQueue','org_img','org_img_imgView','member');
							        	})
							        </script>
							        <sf:errors path="org_img" cssClass="error"/>
								</div>
							</td>
						</tr>
					</table>
				</li>
				<li>
					<table width="100%" cellpadding=0 cellspacing=0>
						<tr>
							<td width=185 align=right valign=top>
								<label>
									<span>*</span>税务登记证复印件：
								</label>
							</td>
							<td valign=top>
								<div style='margin-top:-5px;'>
									<input type="hidden" name="tax_img_NumLimit" id="tax_img_NumLimit" value="1"/>
									<sf:hidden path="tax_img" />
									<input id="tax_img_file_adv" name="tax_img_file_adv" type="file" />
									<div id="tax_img_fileQueue"></div>
									<div class="img-lst"><ul id="tax_img_imgView"></ul></div>
							        <script>
							        	$(function(){
							        		uploadImgComponent('<%=basePath%>','tax_img_NumLimit','tax_img_file_adv','tax_img_fileQueue','tax_img','tax_img_imgView','member');
							        	})
							        </script>
							        <sf:errors path="tax_img" cssClass="error"/>
								</div>
							</td>
						</tr>
					</table>
				</li>
				<li>
					<label>
						<span>*</span>企业电子邮箱：
					</label>
					<sf:input path="email"/>
					<sf:errors path="email" cssClass="error"/>
				</li>
				<li>
					<h1 class="seller-title" style="margin: 60px 0;">银行基本信息：</h1>
				</li>
				<li>
					<table width="100%" cellpadding=0 cellspacing=0>
						<tr>
							<td width=185 align=right valign=top>
								<label>
									<span>*</span>银行开户许可证电子版：
								</label>
							</td>
							<td valign=top>
								<div style='margin-top:-5px;'>
									<input type="hidden" name="bank_img_NumLimit" id="bank_img_NumLimit" value="1"/>
									<sf:hidden path="bank_img" />
									<input id="bank_img_file_adv" name="bank_img_file_adv" type="file" />
									<div id="bank_img_fileQueue"></div>
									<div class="img-lst"><ul id="bank_img_imgView"></ul></div>
							        <script>
							        	$(function(){
							        		uploadImgComponent('<%=basePath%>','bank_img_NumLimit','bank_img_file_adv','bank_img_fileQueue','bank_img','bank_img_imgView','member');
							        	})
							        </script>
							        <sf:errors path="bank_img" cssClass="error"/>
								</div>
							</td>
						</tr>
					</table>
				</li>
				<li>
					<label>
						<span>*</span>开户行名称：
					</label>
					<sf:input path="bank_name"/>
					<sf:errors path="bank_name" cssClass="error"/>
				</li>
				<li>
					<label>
						<span>*</span>开户行账号（对公账号）：
					</label>
					<sf:input path="bank_id"/>
					<sf:errors path="bank_id" cssClass="error"/>
				</li>
				<li>
					<label></label>
					<a href="javascript:void(0);" onclick="document.getElementById('dataForm').submit();" class="btn-huang" style="margin-top: 15px;">提&nbsp;交</a>
				</li>
			</ul>
		</div>
	</div>
</div>
</sf:form>

<!--底部-->
<%@ include file="/WEB-INF/view/inc/footer.jsp"%>
</body>
</html>