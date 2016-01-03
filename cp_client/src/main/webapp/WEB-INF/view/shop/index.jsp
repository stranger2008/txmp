<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
<title>店铺首页</title>
</head>

<body>
        
<%@ include file="/WEB-INF/view/shop/top.jsp" %>

<!--所有商品-->
<div class="module">
    <section>
        <div class="all-goods margin-center">
            <h2 class="head"><span class="title">热门商品</span></h2>

            <ul class="all-goods-lst clearfix">
            
            
            	<c:forEach items="${pageResult.list}" var="goods">
                <li>
                    <a href="<%=basePath %>goods/${goods.goods_id}.action" class=" ">
                        <span class="goods-lst-img">
                        	<c:if test="${!empty goods.img_path}">
                        		<img src="<h:imgSubstr imgpath="${goods.img_path}"/>" onload="DrawImage(this,90,90)"/>
                       		</c:if>
                        </span>
                        <div class="img-box">
                            <p>${goods.goods_name}</p>
                            <span class="red-pr">￥${goods.sale_price}</span>
                        </div>
                    </a>
                </li>
                
				</c:forEach>

            </ul>
            <div class="head"><a href="<%=basePath %>shop/list.action?cust_id=${cust_id}&pg.sortCode=11" class="all-more">全部商品</a></div>
        </div>
    </section>
</div>        

<%@ include file="/WEB-INF/view/inc/footer.jsp" %>


</body>
</html>

<script type="text/javascript">
function addCollect(){
	$.ajax({
	type: "get",
	url: "<%=basePath %>collectadd.action?info_id=${cust_id}&info_type=1&rdm="+Math.random(),
		success: function(data, textStatus){
			if(data == "1"){
				alert("收藏成功");
			}else if(data == "2"){
				alert("您已收藏该商家");
			}else if(data == "3"){
				//alert("请您先登录，再收藏");
				if(confirm("请您先登录，再收藏，是否现在登录？")){
					window.location = "<%=basePath %>login.action";
				}
			}
		}
	});
}
</script>

