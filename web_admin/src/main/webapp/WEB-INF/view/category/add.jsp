<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<title>添加${typeName }分类</title>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
<script type="text/javascript"  src="inc/js/pinyin.js"></script>
<script type="text/javascript">
$(function(){
	$(":radio[name='is_display'][value='0']").attr("checked", true);
	$(":radio[name='member_add'][value='0']").attr("checked", true);
	$("#sort_no").val(0);
	var cat_value=$('#cat_name').val(); 
    $('#cat_name').bind('keyup', function(){   
    	var cat_value=$('#cat_name').val();   
    	var getword=Topingyin(cat_value);
    	var en_word="";
    	if(getword.length>50){
    		en_word=getword.substring(0,49);
		}else{
			en_word=getword;
	  	}
     	var word=getword.substring(0,1);            
     	$('#en_name').val(en_word);
     	$('#word_index').val(word);
    })         
});

</script>
</head>
<body>
 
<div class="position">添加${typeName }分类</div>

<sf:form method="post" action="category/${category.module_type }Add.action" modelAttribute="category">
<sf:hidden path="module_type" />
<table width="100%" cellpadding="0" cellspacing="8" class="add_tab tr td">

	
	<tr>
		<td class="addtab_tit">分类名称<span>*</span></td>
		<td>
			<sf:input path="cat_name"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="cat_name" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">英文名称<span>*</span></td>
		<td>
			<sf:input path="en_name"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="en_name" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">字母索引<span>*</span></td>
		<td>
			<sf:input path="word_index" readonly="true"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="word_index" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">上级分类<span>*</span></td>
		<td> ${parentCatName}
			<sf:hidden path="up_cat_id"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="up_cat_id" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">分类级别<span>*</span></td>
		<td>${category.cat_level} 级
			<sf:hidden path="cat_level" />
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="cat_level" cssClass="error"/></td></tr>
	 
	<tr>
		<td class="addtab_tit">是否显示<span>*</span></td>
		<td>
			<sf:radiobutton path="is_display" value="0"/> 是 &nbsp;&nbsp;&nbsp;&nbsp;
			<sf:radiobutton path="is_display" value="1"/> 否
			
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="is_display" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">排序<span>*</span></td>
		<td>
			<sf:input path="sort_no"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="sort_no" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">是否允许会员发布信息<span>*</span></td>
		<td>
			<sf:radiobutton path="member_add" value="0"/> 允许 &nbsp;&nbsp;&nbsp;&nbsp;
			<sf:radiobutton path="member_add" value="1"/> 不允许
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="member_add" cssClass="error"/></td></tr>
	
	<tr style="display:none;">
		<td class="addtab_tit">会员类型<span>&nbsp;</span></td>
		<td>
			<sf:radiobutton path="mem_type" value="0"/> 企业 &nbsp;&nbsp;&nbsp;
			<sf:radiobutton path="mem_type" value="1"/> 个人&nbsp;&nbsp;&nbsp;
			<sf:radiobutton path="mem_type" value="2"/> 不限
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="mem_type" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">分类简价<span>&nbsp;</span></td>
		<td>
			<sf:textarea path="cat_simple" cssClass="txt4x1"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="cat_simple" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">seo标题<span>&nbsp;</span></td>
		<td>
			<sf:input path="seotitle" cssClass="w400"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="seotitle" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">seo关键字<span>&nbsp;</span></td>
		<td>
			<sf:input path="seokeyword" cssClass="w400"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="seokeyword" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">seo描述<span>&nbsp;</span></td>
		<td>
			<sf:textarea path="seodesc" cssClass="txt4x1"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="seodesc" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">分类描述<span>&nbsp;</span></td>
		<td>
			<sf:textarea path="cat_intr" cssClass="txt4x1"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="cat_intr" cssClass="error"/></td></tr>
	
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

