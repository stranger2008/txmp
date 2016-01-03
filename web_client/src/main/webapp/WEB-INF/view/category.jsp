<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
<title>商品分类-<%=web_title %></title>
</head>

<body>
    <!--头部开始-->
    <%@ include file="/WEB-INF/view/inc/top.jsp" %>
    
    <!--搜索开始-->
    <%@ include file="/WEB-INF/view/inc/search.jsp" %>
    
    	<!--当前位置开始-->
    	<div class="module">
            <section>
                <div class="position">
                    <a href="<%=basePath %>">首页</a> > <a href="javascript:void(0);">商品分类</a>
                </div>
            </section>
        </div>
	    
	    <!--内容开始-->
    <div class="module">
        <section>

            <div class="classify mt5">
                <div class="classify_cont">
                    <ul>
                    
                    	<c:forEach items="${catList}" var="cat" varStatus="status">
                        
                        <li>
                            <a href="javascript:void(0)" class="cls-tit" id="toph_${status.index}" onclick="show('${status.index}')">${cat.cat_name}</a>
                            <ul id="panel_${status.index}" style="display:none;">
                            	<li class="cls-ct-li tbl-type">
                            		<c:forEach items="${cat.childs_cat}" var="downcat">
                                    <a href="<%=basePath %>goodslist.action?cat_id=${downcat.cat_id}" class="cls-a tbl-cell">${downcat.cat_name}</a>
                                    </c:forEach>
                                </li>
                            </ul>
                        </li>
                        
						</c:forEach>

                    </ul>
                </div>
            </div>
        </section>
    </div>

	<script>
		//点击显示自己的下级分类，隐藏其他的下级分类
		function show(val){
			var len = ${fn:length(catList)};
			for(var i=0;i<len;i++){
				if(i==val){
					if($("#panel_"+i).css("display") == "block"){
						$("#panel_"+i).hide();
						$("#toph_"+i).attr("class","cls-tit");
					}else{
						$("#panel_"+i).show();
						$("#toph_"+i).attr("class","cls-tit cls-on");
					}
				}else{
					$("#panel_"+i).hide();
					$("#toph_"+i).attr("class","cls-tit");
				}
			}
		}
		
		
		
		//如果li下的a元素不够4的倍数的话，添缺少的数量
		//如果是3添1，6添2
		$(".cls-ct-li").each(function(){
			var liASize = $(this).children().size();
			if(liASize != 0 && liASize%4 != 0){
				var addSize = 4 - (liASize*1)%4;
				for(var i=0;i<addSize;i++){
					$(this).append("<a href=\"javascript:void(0);\" class=\"cls-a tbl-cell\">&nbsp;</a>");
				}
			}
		});
	</script>
	    
        
    <!--底部开始-->
    <%@ include file="/WEB-INF/view/inc/footer.jsp" %>
    

</body>
</html>
