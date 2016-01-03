<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/WEB-INF/view/inc/inc.jsp"%>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link rel="stylesheet" href="<%=basePath%>inc/css/public.css" />
		<link rel="stylesheet" href="<%=basePath%>inc/css/product.css" />
		<title>商品列表页</title>
		<script>
    $(function(){
    
    	//图片懒加载
    	var obj = $("img");
		if(obj){
			obj.lazyload({ 
				effect : "fadeIn" 
			}); 
		}
		
		$(".brand-ok").hide();
		$(".brand-canel").hide();
        $('.gd').click(function(){
        	var cLen=($(this).parent().find('a').find('input').length);
        	if(cLen>0) return;
            if($(this).text()=='更多'){
                $(this).prev().prev().css('height', 'auto');
                $(this).text('收起');
            }else{
                $(this).prev().prev().css('height', '24px');
                $(this).text('更多');
            }
        });
        $('.brand-canel').click(function(){
            $(this).parent().find('.brand-list').css('height', '24px');
            $(this).hide().prev().hide();
            $(this).parent().find(".gd").text('更多');
             $(this).parent().parent().find('.brand-list').find("a").each(function (i,v){
             	$(v).find('input').remove()
             	var a_fun = $(v).attr("fun");
             	a_fun = a_fun.replace(",true,this)",")");
             	$(v).attr("href",a_fun);
             	$(v).removeAttr("fun");
             });
 			$(this).parent().find("input").val('');
        });
        
         $('.brand-ok').click(function(){
         	var fromdate = $("#goodsqueryForm").serialize(); 
			location.href = "<%=basePath%>goodslist.action?"+fromdate;
         });
         
        $('.dx').click(function(){
        	var a_obj = $(this).prev().find('a');
        	var cLen=(a_obj.find('input').length);
        	if(cLen>0) return;
        	
        	$(".brand-list").find('a').each(function(i,v){
        		$(v).find('input').remove()
             	var a_fun = $(v).attr("href");
             	a_fun = a_fun.replace(",true,this)",")");
             	$(v).attr("href",a_fun);
        	});
        	$(".brand-list").parent().find("input").val('');
        	$(".brand-list").css('height', '24px');
           	$(".brand-list").parent().find('.brand-canel').hide().prev().hide();
            $(".brand-list").parent().find(".gd").text('更多');
        	
        	a_obj.each(function (i,v){
	        	var a_fun = $(v).attr("href");
	        	a_fun = a_fun.replace(")",",true,this)");
	        	$(v).attr("href","javascript:void(0);");
	        	$(v).attr("fun",a_fun);
	            $(v).prepend('<input style="margin-right:5px;" type="checkbox" onclick="'+a_fun+'"/>');
        	});
        	
	        $(this).prev().css('height', 'auto');
            $(this).parent().find('.brand-canel').show();
            
        })
    })
</script>
	</head>
	<body>

		<!--头部开始-->
		<%@ include file="/WEB-INF/view/inc/top.jsp" %>
		
		<%@ include file="/WEB-INF/view/inc/search.jsp" %>
		
		<%@ include file="/WEB-INF/view/inc/nav.jsp" %>
		<sf:form action="goodslist.action" id="goodsqueryForm" modelAttribute="goodsQueryForm">
			<sf:hidden path="label"/>
			<sf:hidden path="pg.sortCode" id="pg_sortCode"/>
			<sf:hidden path="cat_id"/>
			<!--nav开始-->
			<div class="w1200">
				<div class="pro-head clearfix">
					<div class="position-b fl">
						 
						 <a href="<%=basePath %>">首页</a> 
						<c:forEach items="${posTreeList}" var="item" varStatus="status">
							><span style="width:100px;"> 
								<a href="<%=basePath%>goodslist.action?cat_id=${item.cat_id}" <c:if test="${status.last}">style="color:red;"</c:if> >
									${item.cat_name}
								</a>
							</span>
						</c:forEach>
					</div>
					<div class="search-pro fl">
						<input type="text" class="searpro-txt" value="在结果中搜索" />
						<b></b>
					</div>
				</div>
				<c:if test="${!empty goodsBands || !empty goodsAttr || !empty selInfoMap}">
				<table class="sp-wrap" cellpadding="0" cellspacing="0" width="100%">
					<tr id="searchTr" <c:if test='${empty selInfoMap}'>style="display:none"</c:if>>
						<td class="sp-key">
							已选择:
						</td>
						<td class="sp-value" >
							<c:forEach items="${selInfoMap}" var="attrInfo" varStatus="st">
							<div class="sp-sel fl" id="seldiv_${st.index }" onclick="javascript:deleteSearch('${st.index }');">
								<span title="${attrInfo.attr_name}">
								${fn:substring(attrInfo.attr_name, 0, 10)}
								  </span><b></b>
								<c:if test="${attrInfo.attr_id!=null}">
								<input name="tradeIds" type="hidden" value="${attrInfo.trade_id}"/>
								<input type="hidden" name="attr_id" value="${attrInfo.attr_id}"/>
								</c:if>
								<c:if test="${attrInfo.attr_id==null}">
								<input type="hidden" name="brand_name" value="${attrInfo.attr_name}"/>
								<input type="hidden"  name="brand_id" value="${attrInfo.trade_id}"/>
								</c:if>
							</div>
							</c:forEach>
						</td>
					</tr>
					<c:forEach items="${goodsAttr}" var="gattrs" varStatus="status">
						<tr id="attr_${status.count }">
							<td class="sp-key">
								${gattrs.attr_name}:
							</td>
							<td class="sp-value">
								<div class="brand-list">
									<c:forEach items="${gattrs.attrValue}" var="maps" varStatus="child_status">
											<a href="javascript:searchInfo_attr('${maps.trade_id}','${gattrs.attr_id}', '${status.index }');">${maps.attr_value}</a> 
									</c:forEach>
								</div>
								<c:if test="${gattrs.attr_type==3}">
									<em class="dx">多选</em>
								</c:if>
				                <span class="gd" style="visibility:hidden;">更多</span>
				                <p style="text-align:center; margin:10px 0;">
				                <span class="brand-ok none">确定</span>
				                <span class="brand-canel none">取消</span>
				                </p>
								<input id="tradeId_${status.index }" name="tradeIds" type="hidden"/>
								<input id="attr_id_${status.index }" type="hidden" name="attr_id"/>
							</td>
						</tr>
					</c:forEach>
					<c:if test="${!empty goodsBands}">
					<tr id="brand_tr">
						<td class="sp-key">
							品牌:
						</td>
						<td class="sp-value">
							 <div class="brand-list">
								<c:forEach items="${goodsBands}" var="brand">
									
									<a href="javascript:searchInfo_br('${brand.brand_name}','${brand.brand_id}');">
									${brand.brand_name}
									</a>
								</c:forEach>
							</div>
							<em class="dx">多选</em>
	                		<span class="gd"  style="visibility:hidden;">更多</span>
	                		<p style="text-align:center; margin:10px 0;">
			                <span class="brand-ok">确定</span>
			                <span class="brand-canel">取消</span>
			                </p>
							<sf:hidden path="brand_id"/>
							<sf:hidden path="brand_name"/>
						</td>
					</tr>
					</c:if>
					<!-- 
					<tr>
						<td class="sp-key noborder">
							价格:
						</td>
						<td class="sp-value noborder">
							<a href="javascript:findGoodsByPrice(0,199);">0-199</a>
							<a href="javascript:findGoodsByPrice(200,399);">200-399</a>
							<a href="javascript:findGoodsByPrice(400,699);">400-699</a>
							<a href="javascript:findGoodsByPrice(700,1199);">700-1199</a>
							<a href="javascript:findGoodsByPrice(1200,2699);">1200-2699</a><a href="javascript:findGoodsByPrice(2700,3099);">2700-3099</a>
							<a href="javascript:findGoodsByPrice(3100,'');">3100以上</a>
							<span class="pri-ipt">
								<input type="text" class="pri-txt" id="minPrice" value="${g.minPrice}"/>
								<em>-</em> 
								<input type="text" class="pri-txt" id="maxPrice" value="${g.maxPrice }"/> 
								<input type="button" value="确定" class="pri-btn" onclick="javascript:searchGoodsByPrice();" /> 
							</span>
						</td>
					</tr>
					 -->
				</table>
				</c:if>
				<div class="promain">
					<div class="promain-head">
						 
						<span>
							<a <c:if test="${goodsQueryForm.pg.sortCode==11}" >class="a-hover" </c:if> href="javascript:void(0);" onclick="sortClick('11','${goodsQueryForm.label}');">人气</a> 
						</span>
						<span>
							<a <c:if test="${goodsQueryForm.pg.sortCode==0}">class="a-hover" </c:if> href="javascript:void(0);" onclick="sortClick('0','${goodsQueryForm.label}');">新品</a> 
						</span>
						<span> 
							<c:choose>
								<c:when test="${goodsQueryForm.pg.sortCode==20}">
									<a class="a-hover"  onclick="sortClick('21','${goodsQueryForm.label}');" href="javascript:void(0);">价格<span class="p_asc"></span></a>
								</c:when>
								<c:when test="${goodsQueryForm.pg.sortCode==21}">
									<a class="a-hover" onclick="sortClick('20','${goodsQueryForm.label}');" href="javascript:void(0);">价格<span class="p_desc"></span></a>
								</c:when>
								<c:otherwise>
									<a  onclick="sortClick('20','${goodsQueryForm.label}');" href="javascript:void(0);">价格 </a>
								</c:otherwise>
							</c:choose>
						</span>

					</div>
					<div class="goodslist">
						<c:if test="${empty pageResult.list}">暂没有符合条件的商品</c:if>
						<ul class="clearfix">
							<c:forEach items="${pageResult.list}" var="goods">
								<li >
									<div class="goods-img">
										<span class="fl list_img"> <c:if test="${!empty goods.img_path}">
												<a href="<%=basePath%>goods/${goods.goods_id}.action"> <img src="<h:imgSubstr imgpath="${goods.img_path}"/>" /> </a>
											</c:if> </span>
									</div>
									<div class="goods-name">
										<a href="<%=basePath%>goods/${goods.goods_id}.action" class="product-h4" title="${goods.goods_name}"><b> <c:choose>
													<c:when test="${fn:length(goods.goods_name) > 35}">
														<c:out value="${fn:substring(goods.goods_name, 0, 35)}" />
													</c:when>
													<c:otherwise>
														<c:out value="${goods.goods_name}" />
													</c:otherwise>
												</c:choose> </b> </a>
									</div>
									<div class="goods-price">
										¥${goods.sale_price}
										<span>¥${goods.market_price}</span>
									</div>
								</li>
							</c:forEach>
						</ul>
						<div class="add_more margin-center">
							${pageBar}
						</div>
					</div>

				</div>
			</div>


			<div class="w1200">
				<div class="clearfix">
					<!--热销产品 热门推荐-->
					<div class="hotsell">
						<h2 class="hotsell-h2">
							<ul>
								<li class="hover">
									<a href="###">热销产品</a>
								</li>
							</ul>
						</h2>
						<div class="hotsell-cont">
						
							<c:forEach items="${saleGoods}" var="goods">
								<div class="hot-box">
									<span class="img-like"><a href="<%=basePath%>goods/${goods.goods_id}.action"> <img src="<h:imgSubstr imgpath="${goods.img_path}"/>" /> </a> </span>
									<div class="ll">
										<p>
											<a href="<%=basePath%>goods/${goods.goods_id}.action" class="product-h4" title="${goods.goods_name}"><b> <c:choose>
													<c:when test="${fn:length(goods.goods_name) > 20}">
														<c:out value="${fn:substring(goods.goods_name, 0, 20)}" />
													</c:when>
													<c:otherwise>
														<c:out value="${goods.goods_name}" />
													</c:otherwise>
												</c:choose> </b> </a>
										</p>
										<div class="red">
											¥${goods.sale_price}
										</div>
									</div>
								</div>
							</c:forEach>	
						
						</div>
					</div>
				</div>
			</div>
			 
		</sf:form>

		<%@ include file="/WEB-INF/view/inc/footer.jsp" %>
		<script >
		//条件查询
		function searchInfo_attr(trade_id,attr_id ,index, ismore,obj){
			if(ismore){
				var ck = $(obj).attr("checked");
				var tradeidobj = $("#tradeId_"+index);
				var cur_trade_id = tradeidobj.val();
				$("#attr_id_"+index).val(attr_id);
				if(ck){
					if(trade_id!=null){
						if(cur_trade_id!=null && cur_trade_id!=''){
							if(cur_trade_id.indexOf(trade_id)!=-1){
								return;
							}
							cur_trade_id = cur_trade_id+","
						}
						cur_trade_id = cur_trade_id+trade_id;
						tradeidobj.val(cur_trade_id);
					}
				}else{
					if(cur_trade_id.indexOf(trade_id)==-1){
						return;
					}
					var tempids = cur_trade_id.split(",");
					var res_id = new Array();
					for (i=0;i<tempids.length ;i++ ){
						if(tempids[i]==trade_id){
							continue;
						}
						res_id.push(tempids[i]);
					}
					if(tempids.length>0){
						tradeidobj.val(res_id.join(","));
					}else{
						tradeidobj.val('');
					}
				}
				var cklen=$(obj).parent().parent().find('input:checked').length ;
				if(cklen>0){
					$(obj).parent().parent().parent().find('.brand-ok').show();
				}else{
					$(obj).parent().parent().parent().find('.brand-ok').hide();
				}
			}else{
				if(trade_id!=null){
					$("#tradeId_"+index).val(trade_id);
				}
				if(attr_id!=null){
					$("#attr_id_"+index).val(attr_id);
				}
				
				var fromdate = $("#goodsqueryForm").serialize(); 
				//alert(fromdate);
				//$("#goodsqueryForm").submit();
				location.href = "<%=basePath%>goodslist.action?"+fromdate;		
			}
			
		}
		
		function searchInfo_br(brand_name,brand_id , ismore ,obj){
			 if(ismore){
			 	var ck = $(obj).attr("checked")||$(obj).find("input").attr("checked");
			 	var cur_brand_name = $("#brand_name").val();
			 	var cur_brand_id = $("#brand_id").val();
			 	if(brand_id!=null){
			 		if(ck){
			 			if(cur_brand_id!=null&&cur_brand_id!=''){
							if(cur_brand_id.indexOf(brand_id)!=-1){
								return;
							}
							cur_brand_id = cur_brand_id+",";
						}
						cur_brand_id += brand_id;
						$("#brand_id").val(cur_brand_id);
						
						if(brand_name!=null){
					 		if(cur_brand_name!=null&&cur_brand_name!=''){
					 			cur_brand_name += "、"
					 		}
					 		cur_brand_name += brand_name;
							$("#brand_name").val(cur_brand_name);
						}
					}else{
						if(cur_brand_id.indexOf(brand_id)==-1){
							return;
						}
						var tempids = cur_brand_id.split(",");
						var res_id = new Array();
						var i = 0;
						for (i=0;i<tempids.length ;i++ ){
							if(tempids[i]==brand_id){
								continue;
							}
							res_id.push(tempids[i]);
						}
						if(tempids.length>0){
							$("#brand_id").val(res_id.join(","));
						}else{
							$("#brand_id").val('');
						}
						
						
						if(cur_brand_name.indexOf(brand_name)==-1){
							return ;
						}
						var tempNms = cur_brand_name.split("、");
						var res_nm = new Array();
						
						for (i=0;i<tempNms.length ;i++ ){
							if(tempNms[i]==brand_name){
								continue;
							}
							res_nm.push(tempNms[i]);
						}
						if(res_nm.length>0){
							$("#brand_name").val(res_nm.join("、"));
						}else{
							$("#brand_name").val('');
						}
				 		 
					}
					
				}
				//隐藏或显示确定按钮
				var cklen=$(obj).parent().parent().find('input:checked').length ;
				if(cklen>0){
					$(obj).parent().parent().parent().find('.brand-ok').show();
				}else{
					$(obj).parent().parent().parent().find('.brand-ok').hide();
				}
			 }else{
				if(brand_name!=null){
					$("#brand_name").val(brand_name);
				}
				if(brand_id!=null){
					$("#brand_id").val(brand_id);
				}
				var fromdate = $("#goodsqueryForm").serialize(); 
				location.href = "<%=basePath%>goodslist.action?"+fromdate;
			 }
		}
		//排序
		function sortClick(code,lable,brand_name){
			if(code!=null){
				$("#pg_sortCode").val(code);
			}
			if(lable!=null){
				$("#label").val(lable);
			}
			if(brand_name!=null){
				$("#brand_name").val(brand_name);
			}
			$("#cat_id").val('${goodsQueryForm.cat_id}');
			//$("#goodsqueryForm").submit();
			var fromdate = $("#goodsqueryForm").serialize(); 
			location.href = "<%=basePath%>goodslist.action?"+fromdate;
		}
			
		//删除查询条件	
		 function deleteSearch(index){
		 	$("#seldiv_"+index+" input").val('');
		 	var fromdate = $("#goodsqueryForm").serialize(); 
		 	 location.href="<%=basePath%>goodslist.action?"+fromdate;
		 }
		 
		   //文本框限输入整数            
           $("input[id^='minPrice']").keyup(function () {
               var e = $(this).event || window.event;
               var code = parseInt(e.keyCode);
               if (code >= 96 && code <= 105 || code >= 48 && code <= 57 || code == 8) {
                 
               } else{
               	$("#minPrice").val(0);
               }
           });
			//文本框限输入整数            
           $("input[id^='maxPrice']").keyup(function () {
               var e = $(this).event || window.event;
               var code = parseInt(e.keyCode);
               if (code >= 96 && code <= 105 || code >= 48 && code <= 57 || code == 8) {
                 
               } else{
               	$("#maxPrice").val(199);
               }
           });
           
           //通过价格区间查找商品
           function searchGoodsByPrice(){
	           	var minPrice =$("#minPrice").val();
	           	var maxPrice =$("#maxPrice").val();
           		if(minPrice.length>0&&maxPrice.length>0){
           			location.href="<%=basePath%>goodslist.action?cat_id=${goodsQueryForm.cat_id}&maxPrice="+maxPrice+"&minPrice="+minPrice;
           		}else{
           			alert("请输入查询价格!");
           		}
           }
           
           function findGoodsByPrice(minPrice,maxPrice){
          	 	location.href="<%=basePath%>goodslist.action?cat_id=${goodsQueryForm.cat_id}&maxPrice="+maxPrice+"&minPrice="+minPrice;
           }
           
            // 按商品属性查找商品
            $("input[vType='rdattr_value']").bind("click", function () {
              	  var s=''; 
				  $('input[vType="rdattr_value"]:checked').each(function(){ 
				    s+=$(this).val()+';'; 
				  });
				  alert(s);
            });
		</script>
	</body>
</html>
