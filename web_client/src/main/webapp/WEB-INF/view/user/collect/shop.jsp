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
		            alert("请至少选中一间收藏的店铺", "提示");
		        }
		        else {
		        	var r = confirm('你确认此操作吗?', '确认框');
		            if(r == true){
		            	location.href='<%=basePath %>user/delete-'+s.substring(0,s.length-1)+'-1.action';
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
                    <a href="<%=basePath %>user/collectgoods.action" >我收藏的商品<span></span></a>
                    <a href="<%=basePath %>user/collectshop.action" class="active">我收藏的店铺<span></span></a>
                </div>
                <form action="<%=basePath %>user/collectgoods.action">
                <div class="store-list" id="store-show">
                    <!--收藏的店铺-->
                    <c:choose> 
	               		<c:when test="${!empty pageResult.list}">
                        <table cellspacing="0" cellpadding="0" border="0" width="100%" class="store-action store-action-t">
                            <tr>
                                <td width="50"><input type="checkbox" id="SelectAll" name="SelectAll">&nbsp;全选</td>
                                <td><a href="javascript:void(0);" id="delBtn">取消收藏</a></td>
                            </tr>
                        </table>
                        <c:forEach items="${pageResult.list}" var="collect">
	                        <div class="shop-list">
	                            <div class="shop-logo">
	                                <input type="checkbox" name="delID" id="delID" value="${collect.coll_id}"/>
	                                <img src="${collect.shop_logo}" width="100" height="100"/>
	                                <span>
	                                    ${collect.shop_name}<br/>关注人气：${collect.popular}人
	                                </span>
	                                <a href="<%=basePath %>shop/${collect.info_id}.action" class="shop-login" target="_blank"><em></em>进入店铺</a>
	                                <a href="<%=basePath %>user/collectdel-${collect.coll_id}-${collect.info_type}.action" class="shop-canel"><em></em>取消收藏</a>
	                            </div>
	                            <div class="shop-l-content">
	                            
	                            	<c:forEach items="${collect.goodsList}" var="item">
	                            
		                                <div class="shop-l-c-item">
		                                	<c:if test="${!empty item.img_path}">
				                        		<img src="<h:imgSubstr imgpath="${item.img_path}"/>" onload="DrawImage(this,120,110)"/>
			                        		</c:if>
		                                    <span>
		                                    	<c:choose> 
				                                    <c:when test="${fn:length(item.goods_name) > 17}">  
											        	${fn:substring(item.goods_name, 0, 17)}...
												    </c:when>  
												    <c:otherwise>
												    	${item.goods_name}
												    </c:otherwise>
											    </c:choose> 
		                                    </span>
		                                    <p>
		                                        <strong>¥${item.sale_price}</strong>&nbsp;<em>¥${item.market_price}</em>
		                                    </p>
		                                    <a href="<%=basePath %>goods/${item.goods_id}.action" target="_blank">立即购买</a>
		                                </div>
	                                </c:forEach>
	                            </div>
	                        </div>
						</c:forEach>

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
						    	<div style="padding-left:120px;">您暂无收藏的店铺</div>
						    </c:otherwise>
					 	</c:choose>                                                                
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
					                        		<img src="<h:imgSubstr imgpath="${goods.img_path}"/>" onload="DrawImage(this,90,90)"/>
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
