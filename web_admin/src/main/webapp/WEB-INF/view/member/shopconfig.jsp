<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<title>商铺资料修改</title>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
</head>
<body>

<div class="position">商铺资料修改</div>

<script type="text/javascript" src="<%=basePath %>inc/js/ajaxfileupload.js"></script>

	<script type="text/javascript">
			$(document).ready(function(){
				var uploadFileURL = '';
				//获取上传文件URL，与应用名称相关
				$.getJSON('http://192.168.1.206:10000/xfgseller.json', function(data){
        			if(data.code == 0){
						var err = data.errs[0];
						//提示错误消息
						window.alert(err.msg);
						return;
					}
					
					uploadFileURL = data.data;
					//alert(uploadFileURL);
				});

				//执行文件上传操作
				function ajaxFileUpload() {
					$.ajaxFileUpload({
						dataType: 'json',
						secureuri: false,
						url: uploadFileURL,       //文件上传的提交地址，上步操作中获取
						fileElementId: 'file',    //文件上传表单域名称，名称必须为file
						data: {sub: "shopconfig"}, //上传文件子目录，名称必须为sub
						success: null,
						error: null
					});
		
					return false;
				}

				//绑定按钮点击执行文件上传操作
				$('#buttonUpload').click(function(){
					return ajaxFileUpload();
				});
			});

			//应用客户端在文件上传完成后的回调函数
			function handleUploadResult(data){
				//清除上传时创建的HTML内容
				$.clearUploadFrame();
				//code，0：表示失败；1：表示成功
				if(data.code == 0){
					var err = data.errs[0];
					//提示错误消息
					window.alert(err.msg);
					return;
				}
				
				//上传成功，获取图片访问地址
				var fileURL = data.data;
				$("#shop_logo").val(fileURL);
			}
		</script>

<sf:form method="post" action="member/shopconfig.action" modelAttribute="shopconfig">

<table width="100%" cellpadding="0" cellspacing="8" class="add_tab tr td">
	<tr>
		<td class="addtab_tit">店铺名称<span>*</span></td>
		<td>
			<sf:input path="shop_name" maxlength="50" cssClass="w400"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="shop_name" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">店铺LOGO<span>*</span></td>
		<td>
			<sf:input path="shop_logo" id="shop_logo"/>
			
			<input id="file" type="file" size="45" name="file" />
			<button class="button" id="buttonUpload">Upload</button>
			
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="shop_logo" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">店铺简介<span>*</span></td>
		<td>
			<sf:textarea path="shop_intro" cssClass="txt4x1"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="shop_intro" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">经营范围<span>*</span></td>
		<td>
			<sf:input path="busi_range" cssClass="w400"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="busi_range" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">联系人<span>*</span></td>
		<td>
			<sf:input path="contant_man"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="contant_man" cssClass="error"/></td></tr>
	
	
	<tr>
		<td class="addtab_tit">手机<span>&nbsp;</span></td>
		<td>
			<sf:input path="mobile"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="mobile" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">电话<span>&nbsp;</span></td>
		<td>
			<sf:input path="phone"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="phone" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">邮箱<span>&nbsp;</span></td>
		<td>
			<sf:input path="email" cssClass="w400"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="email" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">QQ<span>&nbsp;</span></td>
		<td>
			<sf:input path="qq" cssClass="w400"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="qq" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">MSN<span>&nbsp;</span></td>
		<td>
			<sf:input path="msn" cssClass="w400"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="msn" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">地区<span>&nbsp;</span></td>
		<td>
			<span id="selectDivModel"></span>
			<script type="text/javascript">
				showSelectCascade("<%=basePath %>","area","selectDivModel","area_attr","${shopconfig.area_attr}");
			</script>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="area_attr" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">详细地址<span>&nbsp;</span></td>
		<td>
			<sf:input path="address" cssClass="w400"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="address" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit"></td>
		<td>
			<button class="tab_btn" type="submit"/>提交</button>
		</td>
	</tr>
</table>

</sf:form>

</body>
</html>
