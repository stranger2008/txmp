<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<header class="module">
    <section class="shop-top">
            <div class="shop-logo margin-auto">
                <a href="<%=basePath %>shop/${cust_id }.action">
                	<c:if test="${!empty shopconfig.shop_logo}">
                  		<img src="${shopconfig.shop_logo }" onload="DrawImage(this,139,104)"/>
                 	</c:if>
                </a>
            </div>
            <div class="shop-name margin-center">
                <label onclick="window.location.href='<%=basePath %>';" style="cursor:pointer;"></label>
                <span><a href="<%=basePath %>shop/${cust_id }.action">${shopconfig.shop_name }</a></span>
            </div>
    </section>
</header>

<!--搜索开始-->
<div class="module">
    <div class="search2 clearfix">
        <form>
            <div class="box_search2 fl">
                <input type="text" class="sear_t2" value="">
                <button type="submit" class="sear_btn"><img class="full-img" src="<%=basePath %>inc/images/search.png"></button>
            </div>    
            <label class="shop-collect fr" title="关注" onclick="addCollect()"></label>
        </form>
    </div>
</div>

<script type="text/javascript">
function addCollect(){
	$.ajax({
	type: "get",
	url: "<%=basePath %>collectadd.action?info_id=${cust_id}&info_type=1",
		success: function(data, textStatus){
			if(data == "1"){
				alert("收藏成功");
			}else if(data == "2"){
				alert("您已收藏该商家");
			}else if(data == "3"){
				alert("请您先登录，再收藏");
			}
		}
	});
}
</script>