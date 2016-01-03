<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<title>商品详情</title>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
<script type="text/javascript" src="<%=basePath %>inc/js/calendar/WdatePicker.js"></script> 
<script type="text/javascript" src="<%=basePath %>inc/js/ckeditor/ckeditor.js"></script>
<%@ include file="/WEB-INF/view/inc/uploadify/resource.jsp" %>

<script type="text/javascript">
	CKEDITOR.on( 'instanceReady', function( ev ){       
		this.config.filebrowserImageUploadUrl = '<%=basePath%>ckupload.action'; //固定路径
	});

$(document).ready(function(){
	CKEDITOR.replace('goodsDetail');
	$('#goodsForm').submit(function(e){
		
		//e.preventDefault();
	});
	$('#upDate').focus(function(){
		WdatePicker({
			maxDate: '#F{$dp.$D(\'downDate\',{d:1})}',
			readOnly:true
		});
	});
	$('#downDate').click(function(){
		WdatePicker({
			minDate:'#F{$dp.$D(\'upDate\',{d:1})}',
			readOnly:true
		});
	});
	$('#isShip0').click(function(){
		$('#shipPrice').hide();
	});
	$('#isShip1').click(function(){
		$('#shipPrice').show();
	});
	
});
</script>
</head>
<body>
<div class="position">商品详情</div>
<sf:form method="post" action="goods/save.action" modelAttribute="sellerGoodsWithBLOBs" id="goodsForm" >
<sf:hidden path="goodsId" />
<sf:hidden path="infoattrId" />
<table width="100%" cellpadding="0" cellspacing="8" class="add_tab tr td">
	<tr>
		<td width="15%" class="addtab_tit">所属分类<span>*</span></td>
		<td width="85%">
			${catAttrName}<a href="<%=basePath %>goods/category.action?catAttr=${sellerGoodsWithBLOBs.catAttr}&goodsId=${sellerGoodsWithBLOBs.goodsId}">[重新选择]</a>
			<sf:hidden path="catAttr" />
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td></td></tr>
	
	<tr>
		<td width="15%" class="addtab_tit">自定义分类</td>
		<td width="85%">
			<select id="membercatSelect"><option value="">请选择</option></select>&nbsp;&nbsp;&nbsp;&nbsp;<button type="button" id="addMembercat">添加</button>
			<ul id="membercatList" style="padding-top:5px;">
				<c:forEach items="${membercatList}" var="item" varStatus="status">
					<li id='attr_li_${status.count}' title='${item.mambercat_id}' style='padding-top:3px;'>${item.mambercat_name} &nbsp;&nbsp;<button type='button' onclick='reduce_goods_attr("attr_li_${status.count}");'>删除</button></li>
				</c:forEach>
			</ul>
			<sf:hidden  path="membercat" />
		</td>
	</tr>
	<tr><td class="membercat"></td><td></td></tr>
	
	<%-- 分类属性列表 --%>
	<c:forEach items="${categoryAttrs }" var="iamap" varStatus="p">
	<tr>
		<td class="addtab_tit">
			${iamap.value[0].attr_name }
			<span>
				<c:if test="${iamap.value[0].is_must == '1'}">*</c:if>
				<c:if test="${iamap.value[0].is_must != '1'}">&nbsp;</c:if>
			</span>
		</td>
		<td>
			<input type="hidden" name="goodsAttrList[${p.index}].required" value="${iamap.value[0].is_must == '1' ? true : false}">
			<input type="hidden" name="goodsAttrList[${p.index}].attrId" value="${iamap.value[0].attr_id}">
			<input type="hidden" name="goodsAttrList[${p.index}].attrName" value="${iamap.value[0].attr_name}">
			<input type="hidden" name="goodsAttrList[${p.index}].sortNo" value="${iamap.value[0].sort_no}">
			<c:if test="${iamap.value[0].attr_type == '0' }">
				<input type="hidden" name="goodsAttrList[${p.index}].attrType" value="0">
			</c:if>
			<c:if test="${iamap.value[0].attr_type == '2' }">
				<input type="hidden" name="goodsAttrList[${p.index}].attrType" value="2">
			</c:if>
			<c:if test="${iamap.value[0].attr_type == '3' }">
				<input type="hidden" name="goodsAttrList[${p.index}].attrType" value="3">
			</c:if>
			
			<c:forEach items="${iamap.value }" var="item" varStatus="c">
				<c:if test="${item.attr_type == '0' }">
					<input type="text" name="goodsAttrList[${p.index}].attrValueList[0].attrValue" class="w400" value="${item.attr_value }">
				</c:if>
				<c:if test="${item.attr_type == '2' }">
					<span style="display:inline-block;">
						<input type="radio" id="tradeId_${p.index}_${c.index}" 
							name="goodsAttrList[${p.index}].attrValueList[0].tradeId" 
							value="${item.trade_id}" class="chkbox"
							<c:if test="${item.is_checked == true }">checked="checked"</c:if> />
						<label for="tradeId_${p.index}_${c.index}" class="lab-txt">${item.attr_value}</label>
					</span>
				</c:if>
				<c:if test="${item.attr_type == '3' }">
					<span style="display:inline-block;">
						<input type="checkbox" id="tradeId_${pp.index}_${c.index}" 
							name="goodsAttrList[${p.index}].attrValueList[0].tradeId" 
							value="${item.trade_id}" class="chkbox"
							<c:if test="${item.is_checked == true }">checked="checked"</c:if> />
						<label for="tradeId_${p.index}_${c.index}" class="lab-txt">${item.attr_value}</label>
					</span>
				</c:if>
			</c:forEach>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="goodsAttrList[${p.index}]" cssClass="error"/></td></tr>
	</c:forEach>
	
	<tr>
		<td class="addtab_tit">商品编号<span>*</span></td>
		<td>
			<sf:input path="goodsNo" cssClass="w400" readonly="true" />
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td></td></tr>
	<tr>
		<td class="addtab_tit">商品名称<span>*</span></td>
		<td>
			<sf:input path="goodsName" cssClass="w400" maxlength="100"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="goodsName" cssClass="error"/></td></tr>
	<tr>
		<td class="addtab_tit">商品品牌</td>
		<td>
			<sf:select path="brand_id" items="${brandMap}" />
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td></td></tr>
	<tr>
		<td class="addtab_tit">是否虚拟商品<span>*</span></td>
		<td>
			<sf:radiobutton path="isVirtual" value="0" id="isVirtual0" cssClass="chkbox"/>
			<label for="isVirtual0" class="lab-txt" >是</label>
			<sf:radiobutton path="isVirtual" value="1" id="isVirtual1" cssClass="chkbox" />
			<label for="isVirtual1" class="lab-txt" >否</label>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="isVirtual" cssClass="error"/></td></tr>
	<tr>
		<td class="addtab_tit">销售价<span>*</span></td>
		<td>
			<fmt:formatNumber value="${sellerGoodsWithBLOBs.salePrice}" pattern="#0.00" var="salePriceString" />
			<sf:input path="salePrice" value="${salePriceString}" />
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="salePrice" cssClass="error"/></td></tr>
	<tr>
		<td class="addtab_tit">市场价</td>
		<td>
			<fmt:formatNumber value="${sellerGoodsWithBLOBs.marketPrice}" pattern="#0.00" var="marketPriceString" />
			<sf:input path="marketPrice" value="${marketPriceString}" />
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="marketPrice" cssClass="error"/></td></tr>
	<tr>
		<td class="addtab_tit">运费类型</td>
		<td>
			<sf:radiobutton path="isShip" value="0" id="isShip0" cssClass="chkbox" />
			<label for="isShip0" class="lab-txt" >免运维</label>
			<sf:radiobutton path="isShip" value="1" id="isShip1" cssClass="chkbox" />
			<label for="isShip1" class="lab-txt" >固定费用</label>
			<sf:input path="shipPrice" id="shipPrice" cssStyle="${sellerGoodsWithBLOBs.isShip == '0' ? 'display:none;' : ''}" />
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="shipPrice" cssClass="error"/></td></tr>
	<tr>
		<td class="addtab_tit">上架时间<span>*</span></td>
		<td>
			<sf:input path="upDate" id="upDate" />-
			<sf:input path="downDate" id="downDate"  />
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="upDate" cssClass="error"/><sf:errors path="downDate" cssClass="error"/></td></tr>
	<tr>
		<td class="addtab_tit">初始库存<span>*</span></td>
		<td>
			<sf:input path="nowStock" />
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="nowStock" cssClass="error"/></td></tr>
	<tr>
		<td class="addtab_tit">提醒库存</td>
		<td>
			<sf:input path="warnStock" />
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="warnStock" cssClass="error"/></td></tr>
	<tr>
		<td class="addtab_tit">商品关键字</td>
		<td>
			<sf:input path="goodsWd" cssClass="w400" maxlength="100"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="goodsWd" cssClass="error"/></td></tr>
	<tr>
		<td class="addtab_tit">商品简介</td>
		<td>
			<sf:textarea path="goodsDesc" cssClass="txt4x1" maxlength="200"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="goodsDesc" cssClass="error"/></td></tr>
	<tr>
		<td class="addtab_tit">商品图片</td>
		<td>
			<input type="hidden" name="imgNumLimit" id="imgNumLimit" value="5"/>
			<input id="file" name="upload_file" type="file" />
			<div id="fileQueue"></div>
			<sf:hidden path="imgPath"/>
			<div class="img-lst"><ul id="imgView"></ul></div>
	        <script>
	        	$(function(){
	        		uploadImgComponent('<%=basePath%>','imgNumLimit','file','fileQueue','imgPath','imgView','goods');
	        	})
	        </script>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="imgPath" cssClass="error"/></td></tr>
	<tr><td class="addtab_tit"></td><td><div class="img-lst"><ul id="imgView"></ul></div></td></tr>
	<tr>
		<td class="addtab_tit">商品描述</td>
		<td>
			<sf:textarea path="goodsDetail" id="goodsDetail" htmlEscape="false" maxlength="30000"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="goodsDetail" cssClass="error"/></td></tr>
	<tr>
		<td class="addtab_tit">商家备注</td>
		<td>
			<sf:textarea path="busiRemark" cssClass="txt4x1" maxlength="300"/>
		</td>
	</tr>
	<tr><td class="addtab_tit"></td><td><sf:errors path="busiRemark" cssClass="error"/></td></tr>
	
	<c:if test="${sellerGoodsWithBLOBs.infoState == '2' }">
	<tr>
		<td class="addtab_tit" id="no_reason_titile">审核不通过原因<span>&nbsp;</span></td>
		<td>
			${sellerGoodsWithBLOBs.noReason }
			<sf:hidden path="noReason" />
		</td>
	</tr>
	</c:if>
	
	<tr>
		<td class="addtab_tit"><br></td>
		<td>
			<button class="tab_btn" type="submit"/>提交</button>
		</td>
	</tr>
</table>
</sf:form>
<script>

   $(document).ready(function () {
      getMembercatList();
   });
   
	function getMembercatList()
    {
    	$.ajax({
	  	type: "get",
		async:false,
		cache:false,
		url: "<%=basePath%>public/membercat/getMembercatList.action",
		success: function(data, textStatus){
			data = data.substring(0,data.length-1);
			var cat = data.split("|");
			for(var i=0;i<cat.length;i++)
			{
				var cat_s = cat[i].split(">");
      			var bigCat_id="",bigCat_name="";
				for(var j=0;j<cat_s.length;j++)
				{
					if(cat_s[j]!="")
					{
						var cat_arr = cat_s[j].split(",");
						if(j==0)
						{
							bigCat_id = cat_arr[0];
							bigCat_name = cat_arr[1];
							var varItem = new Option(bigCat_name, bigCat_id);
        					document.getElementById("membercatSelect").options.add(varItem);
						}
						else
						{
							var varItem = new Option("└"+bigCat_name+">"+cat_arr[1],bigCat_id+","+cat_arr[0]);
        					document.getElementById("membercatSelect").options.add(varItem);
						}
					}
				}
			}
		}
	  });
    }
    
     $("#addMembercat").click(function (){
    	var str_name="";
    	var li_num = $("#membercatList").children().length + 1;//ul下li的个数 增1
    	var exit_num = 0;
    	var attr_list=[];
    	var membercat = $("#membercat").val();
    	var membercat_id = $("#membercatSelect").val();
    	if(membercat_id != "")
    	{
	    	attr_list = membercat.split("|");
	    	for(var i=0;i<attr_list.length;i++){
	    	  if(attr_list[i]== membercat_id)
	    	  {
	    	  	exit_num++;
	    	  }
	    	}
	    }
	    else{
	    	exit_num++;
	    }
	    //if(membercat_id.lastIndexOf(",")==-1)
	    //{
	    	//exit_num++;
	    //}
    	if(exit_num ==0){
	   		str_name= $("#membercatSelect").find("option:selected").text().replace("└","");
	        if(str_name!=""){
	        	$("#membercatList").append("<li id='attr_li_"+li_num+"' title='"+membercat_id+"' style='padding-top:3px;'>"+str_name+"&nbsp;&nbsp;<button type='button' onclick='reduce_goods_attr(\"attr_li_"+li_num+"\");'>删除</button></li>");
	        }
	     	membercat_string();
        }
    });
	function reduce_goods_attr(str) {
        $("#"+str).remove();
        membercat_string();
    };
	function membercat_string() {
        var membercat_list_s = "";
        $("#membercatList li").each(function (){
            membercat_list_s = membercat_list_s +this.getAttribute('title')+"|";
        });
		$("#membercat").val(membercat_list_s);
    };


</script> 
</body>
</html>
