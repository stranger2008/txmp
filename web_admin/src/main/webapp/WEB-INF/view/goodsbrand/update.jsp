<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page   import="com.xingfugo.web.admin.common.ConstantUtil" %>
<html>
<head>
<title>修改商品品牌</title>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
<script type="text/javascript">	
 $(document).ready(function () {
        $.ajax({
			type: "get",
			url: "<%=basePath %>public/setCatAttrName.action?act_attr_s="+$("#goods_attr").val(),
			cache:false,
			success: function(data, textStatus){
				var obj = jQuery.parseJSON(data);
				$.each(obj, function(index) {
					if(obj[index]!="")
					{
                	 var attr_list = obj[index].split("|");
                	 $("#goods_attr_name").append("<li id='attr_li_"+index+"' title='"+attr_list[0]+"'>"+attr_list[1]+"&nbsp;&nbsp;<button type='button' onclick='reduce_goods_attr(\"attr_li_"+index+"\");'>删除</button></li>");
             		}
             	});
			}
		});
    });
$(function () {
    $("#add_goods_attr").click(function () {
    	var str_name=""; //商品品牌名拼接
    	var li_num = $("#goods_attr_name").children().length + 1;//ul下li的个数 增1
    	var exit_num = 0; //判断品牌串是否已存在
    	var attr_list=[];
    	attr_list = $("#goods_attr").val().split("|");
    	for(var i=0;i<attr_list.length;i++){    	  
    	  //alert(attr_list[i]+"=="+$("#field_goods_attr_s").val().substring(0,$("#field_goods_attr_s").val().length-1));
    	  if(attr_list[i]==($("#field_goods_attr_s").val().substring(0,$("#field_goods_attr_s").val().length-1)))
    	  	exit_num++;
    	}
    	if(exit_num ==0){
		   	for(var i=1;i<4;i++){
	   		  if($("#goods_attr_s"+i).val()!=""){
	   			str_name=str_name+$("#goods_attr_s"+i).find("option:selected").text()+">";
	   		  }
		   	}
	        if(str_name!=""){
	        	$("#goods_attr_name").append("<li id='attr_li_"+li_num+"' title='"+$("#field_goods_attr_s").val().substring(0,$("#field_goods_attr_s").val().length-1)+"'>"+str_name+"&nbsp;&nbsp;<button type='button' onclick='reduce_goods_attr(\"attr_li_"+li_num+"\");'>删除</button></li>");
	        } 
	        string_goods_attr();
        }
    });    
});
function reduce_goods_attr(str) {
        $("#"+str).remove();
        string_goods_attr();
    };
    
function string_goods_attr() {
        var goods_attr_list = "";
        $("#goods_attr_name li").each(function (){
            goods_attr_list = goods_attr_list +this.getAttribute('title')+"|";
        });
		$("#goods_attr").val(goods_attr_list);
    };
</script>
</head>
<body>

<div class="position">修改商品品牌</div>

<sf:form method="post" action="goodsbrand/update.action" modelAttribute="goodsbrand">

<table width="100%" cellpadding="0" cellspacing="8" class="add_tab tr td">

	
	<tr>
		<td class="addtab_tit">品牌名称<span>*</span></td>
		<td>
			<sf:input path="brand_name"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="brand_name" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">品牌网址<span>&nbsp;</span></td>
		<td>
			<sf:input path="brand_site"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="brand_site" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">品牌logo<span>&nbsp;</span></td>
		<td>
			<input type="hidden" name="imgNumLimit" id="imgNumLimit" value="1"/>
			<sf:hidden path="logo"/>
			<input id="file_adv" name="upload_file" type="file" />
			<div id="fileQueue"></div>
			<div class="img-lst"><ul id="imgView"></ul></div>
			<%@ include file="/WEB-INF/view/inc/uploadify/imgInc.jsp" %>
	        <script>
	        	$(function(){
	        		uploadImgComponent('<%=basePath%>','imgNumLimit','file_adv','fileQueue','logo','imgView','goodsbrand');
	        	})
	        </script>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="logo" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">详细说明<span>&nbsp;</span></td>
		<td>
			<sf:textarea path="content" cssClass="txt4x1" />
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="content" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">品牌分类<span>*</span></td>
		<td>
		    <sf:hidden path="goods_attr"/>
			<span id="selectDivModel"></span>&nbsp;&nbsp;&nbsp;&nbsp;<button type="button" id="add_goods_attr">添加</button>
			<script type="text/javascript">
			$(function(){
			showSelectCascade("<%=basePath %>","category","selectDivModel","goods_attr_s","goods_attr_s","<%=ConstantUtil.GOODS_MODULE_TYPE%>");
			});
			</script>
			
			<ul id="goods_attr_name">${cat_attr_names}</ul>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="goods_attr" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">排序<span>*</span></td>
		<td>
			<sf:input path="sort_no"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="sort_no" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">状态<span>&nbsp;</span></td>
		<td>
			<sf:radiobutton path="info_state" value="0" checked="true"/>正常 &nbsp;&nbsp;&nbsp;&nbsp;
            <sf:radiobutton path="info_state" value="1"/>禁止
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="info_state" cssClass="error"/></td></tr>
	
	<tr>
		<td class="addtab_tit">是否推荐<span>*</span></td>
		<td>
			<sf:radiobutton path="is_recom" value="0" checked="true"/>是 &nbsp;&nbsp;&nbsp;&nbsp;
            <sf:radiobutton path="is_recom" value="1"/>否
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="is_recom" cssClass="error"/></td></tr>
	
	
	<tr>
		<td class="addtab_tit"></td>
		<td>
			<sf:hidden path="brand_id"/>
			<button class="tab_btn" type="submit"/>提交</button>
		</td>
	</tr>
</table>

</sf:form>

</body>
</html>

