<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
  	<title>我的收藏</title> 
    <%@ include file="/WEB-INF/view/inc/inc.jsp" %>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" href="<%=rootPath %>css/public.css" />
    <link rel="stylesheet" href="<%=rootPath %>css/product.css" />
    <link rel="stylesheet" href="<%=rootPath %>css/account.css" />
    <script src="<%=rootPath %>js/jquery.min.js" type="text/javascript"></script>
    <script src="<%=rootPath %>js/core.js"></script>
    <script src="<%=rootPath %>js/account.js"></script>
    <style type="text/css">
    	.selectpsd{ width:100%;font-size:14px; padding:4px 0;margin-top:5px;color:#666; border:1px solid #d7d7d7;}
    	.page {height:15px;padding:5px 0;}
    </style>
    <script type="text/javascript">	
		//全选、反选
		$(function () {
		    $("#SelectAll").click(function () {
		        var s = document.getElementById("SelectAll").checked;
		        if (s == true) {
		            $("input[name='delID']").attr("checked", $(this).attr("checked"));
		        }
		        else {
		            $("input[name='delID']").removeAttr("checked");
		        }
		    });
		})
		//批量删除
		$(function () {
		    var s = '';
		    $("#delBtn").click(function () {
		        $('input[name="delID"]:checked').each(function () {
		            s += $(this).val() + ',';
		        });
		        if (s =='') {
		            alert("请至少选中一件收藏的商品", "提示");
		        }
		        else {
		        	var r = confirm('你确认此操作吗?', '确认框');
		            if(r == true){
		            	location.href='<%=basePath %>user/delete-'+s.substring(0,s.length-1)+'-0.action';
		            }
		        }  
		        s='';          
		    });
		})
		//
	</script>
       

  </head>
  
  <body>
<!--头部开始-->
<%@ include file="/WEB-INF/view/inc/top.jsp" %>
<!--头部下搜索框-->
<%@ include file="/WEB-INF/view/user/inc/top.jsp" %>		 
<!--nav开始-->
<%@ include file="/WEB-INF/view/user/inc/nav.jsp" %>	
<!--个人中心-->
<div class="w1200">
	<div class="usercenter">
    	<div class="position">
        	<a href="#"><strong>我的天下名品</strong></a><span></span><a href="#">账户安全</a>
        </div>
        <!--会员中心左边导航-->
		<%@ include file="/WEB-INF/view/user/inc/left.jsp" %>
         <div class="user-info fr">
        	<!--我收藏的-->
            <div class="store">
                <h1 class="store-title">我的收藏</h1>
                <div class="store-tab">
                    <a href="<%=basePath %>user/collectgoods.action" class="active">我收藏的商品<span></span></a>
                    <a href="<%=basePath %>user/collectshop.action" >我收藏的店铺<span></span></a>
                </div>
                <form action="<%=basePath %>user/collectgoods.action">
                <div class="store-list" id="store-show">
                    <!--收藏的商品-->                                   
                     <c:choose> 
	               		<c:when test="${!empty pageResult.list}">  
                        <table width="100%" border="0" cellspacing="0" cellpadding="0" class="store-action store-action-t">
                            <tr>
                                <td width="50"><input type="checkbox" id="SelectAll" name="SelectAll"/>&nbsp;全选</td>
                                <td><a href="javascript:void(0);" id="delBtn">取消收藏</a></td>
                            </tr>
                        </table>                    
                        <table width="100%" border="0" class="store-item" cellpadding="0" cellspacing="0">
                            <tr class="store-i-head">
                                <td></td>
                                <td align="center">商品</td>
                                <td align="center">天下名品价</td>
                                <td align="center">操作</td>
                            </tr>
                         <c:forEach items="${pageResult.list}" var="collect">
                            <tr>
                                <td width="40" align="center"><input type="checkbox" name="delID" id="delID" value="${collect.coll_id}"/></td>
                                <td width="560">
                                    <dl>
                                        <dt>
                                        	<a href="<%=basePath %>goods/${collect.info_id}.action" target="_blank">
	                            			<c:if test="${!empty collect.img_path}">
			                        			<img src="<h:imgSubstr imgpath="${collect.img_path}"/>" />
		                        			</c:if>
	                            			</a>
	                            		</dt>
                                        <dd>
                                            <a href="<%=basePath %>goods/${collect.info_id}.action" target="_blank">${collect.goods_name}</a>
                                            <p class="store-hot">
                                                <span class="serve-l-red"></span>
                                                <span class="serve-l-red"></span>
                                                <span class="serve-l-red"></span>
                                                <span class="serve-l-red"></span>
                                                <span class="serve-l-red serve-l-ash"></span>
                                            </p>
                                            <em>加关注时间：<fmt:formatDate value="${collect.in_date}" pattern="yyyy-MM-dd HH:mm:ss" /></em>
                                            <c:if test="${collect.isdown==1}">
                                            	<p class="store-hot"><em>商品已下架</em></p>
                                            </c:if>
                                        </dd>
                                    </dl>
                                </td>
                                <td width="100" align="center">
                                    <strong>¥${collect.sale_price}</strong>
                                </td>
                                <td align="center">
                                	<c:if test="${collect.isdown==0}">
                                    	<a href="<%=basePath %>cartadd.action?goods.goods_id=${collect.info_id}&amount=1" class="cart-btn"><span class="store-cart">加入购物车</span></a>
                                    </c:if>
                                    <a href="<%=basePath %>user/collectdel-${collect.coll_id}-${collect.info_type}.action">取消收藏</a>
                                </td>
                            </tr>
                        </c:forEach>
                        </table>
                        
                        <table width="100%" border="0" cellspacing="0" cellpadding="0" class="store-action">
                            <tr>
                                <td width="50%"></td>                              
                                <td align="right">
                                    <div class="account-page">
                                        <div class="page">${pageBar}</div>
                                    </div>
                                </td>
                            </tr>
                        </table>                     
                        </c:when>  
					    <c:otherwise>
					    	<div style="padding-left:15px;">您暂无收藏的商品</div>
					    </c:otherwise>
					 </c:choose>                        
                     <!--猜你喜欢-->
                     <div class="shop-like">
                         <h1>猜你喜欢</h1>
                         <div class="shop-l-c" id="imgRoll1">
                             <!--<a class="shop-like-prev"></a>-->
                             <div class="shop-l-item">
                                 <ul>
                                    <c:forEach items="${guessList}" var="goods">
	                        <li>
	                            <a href="<%=basePath %>goods/${goods.goods_id}.action" class="like-img" target="_blank">
	                            	<c:if test="${!empty goods.img_path}">
		                        		<img src="<h:imgSubstr imgpath="${goods.img_path}"/>" />
	                        		</c:if>
	                            </a><br/>
	                            <a href="<%=basePath %>goods/${goods.goods_id}.action" class="goods-name" target="_blank">
	                            <c:choose> 
                                    <c:when test="${fn:length(goods.goods_name) > 30}">  
							        	${fn:substring(goods.goods_name, 0, 30)}...
								    </c:when>  
								    <c:otherwise>
								    	${goods.goods_name}
								    </c:otherwise>
								    </c:choose>
	                            </a><br/>
	                            <span>¥${goods.sale_price}</span>
	                        </li>
                     			 </c:forEach>
                                 </ul>
                             </div>
                             <!--<a class="shop-like-next"></a>-->
                         </div>
                     </div>                               
                </div>
                </form> 
            </div>
        </div>
	</div>
</div>

<div class="clearfix"></div>
<!--底部-->
<%@ include file="/WEB-INF/view/inc/footer.jsp" %>
  </body>
</html>
