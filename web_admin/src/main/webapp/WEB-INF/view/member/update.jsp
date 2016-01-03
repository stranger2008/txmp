<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<title>修改商家信息</title>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
<%@ include file="/WEB-INF/view/inc/uploadify/imgInc.jsp" %>
<script type="text/javascript" src="<%=basePath%>inc/com/calendar/WdatePicker.js"></script> 
</head>
<body>

<div class="position">修改商家信息</div>

<sf:form method="post" action="member/update.action" modelAttribute="member">

<table width="100%" cellpadding="0" cellspacing="8" class="add_tab tr td">

	
	<tr>
		<td class="addtab_tit">商家名称<span>*</span></td>
		<td>
			<sf:input path="cust_name" />
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="cust_name" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">用户名<span>*</span></td>
		<td>
			<sf:input path="user_name"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="user_name" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">公司logo<span>&nbsp;</span></td>
		<td>
			<input type="hidden" name="logo_img_NumLimit" id="logo_img_NumLimit" value="1"/>
			<sf:hidden path="logo_img" />
			<input id="logo_img_file_adv" name="logo_img_file_adv" type="file" />
			<div id="logo_img_fileQueue"></div>
			<div class="img-lst"><ul id="logo_img_imgView"></ul></div>
	        <script>
	        	$(function(){
	        		uploadImgComponent('<%=basePath%>','logo_img_NumLimit','logo_img_file_adv','logo_img_fileQueue','logo_img','logo_img_imgView','member');
	        	})
	        </script>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="logo_img" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">公司所在县区<span>*</span></td>
		<td>
			<span id="selectDivModel"></span>
			<script type="text/javascript">
				showSelectCascade("<%=basePath %>","area","selectDivModel","area_attr","${member.area_attr }");
			</script>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="area_attr" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">公司所在街道<span>*</span></td>
		<td>
			<sf:input path="address"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="address" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">公司电话<span>*</span></td>
		<td>
			<sf:input path="phone"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="phone" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">公司注册资金<span>*</span></td>
		<td>
			<sf:input path="reg_money"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="reg_money" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">注册资金币种<span>*</span></td>
		<td>
			<sf:input path="reg_money_type"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="reg_money_type" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">营业执照号码<span>*</span></td>
		<td>
			<sf:input path="lic_no"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="lic_no" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">营业执照所在地<span>*</span></td>
		<td>
			<sf:input path="lic_address"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="lic_address" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">营业执照有效时间<span>*</span></td>
		<td>
			<sf:input path="lic_start_time" id="lic_start_time" readonly="readonly" cssClass="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\\'lic_end_time\\',{d:-1})}'})" />
			&nbsp;-&nbsp;
			<sf:input path="lic_end_time" id="lic_end_time" readonly="readonly" cssClass="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\\'lic_start_time\\',{d:1})}'})" />
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="lic_start_time" cssClass="error"/><sf:errors path="lic_end_time" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">营业执照<span>*</span></td>
		<td>
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
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="lic_img" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">经营范围<span>*</span></td>
		<td>
			<sf:textarea path="product" cssClass="txt4x1" />
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="product" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">法人身份证<span>*</span></td>
		<td>
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
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="person_id_img" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">组织机构代码证件<span>*</span></td>
		<td>
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
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="org_img" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">税务登记证<span>*</span></td>
		<td>
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
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="tax_img" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">公司电子邮箱<span>*</span></td>
		<td>
			<sf:input path="email"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="email" cssClass="error"/></td></tr>
		
	<tr>
		<td class="addtab_tit">银行开户许可证<span>*</span></td>
		<td>
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
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="bank_img" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">开户行名称<span>*</span></td>
		<td>
			<sf:input path="bank_name"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="bank_name" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">银行账号<span>*</span></td>
		<td>
			<sf:input path="bank_id"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="bank_id" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">联系人<span>*</span></td>
		<td>
			<sf:input path="contact_name"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="contact_name" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">联系人电话<span>*</span></td>
		<td>
			<sf:input path="contact_phone"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="contact_phone" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">联系人邮箱<span>*</span></td>
		<td>
			<sf:input path="contact_email"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="contact_email" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit"></td>
		<td>
			<sf:hidden path="cust_id"/>
			<button class="tab_btn" type="submit"/>提交</button>
		</td>
	</tr>
</table>

</sf:form>

</body>
</html>

