<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/WEB-INF/view/inc/inc.jsp"%>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link rel="stylesheet" href="<%=basePath%>inc/css/public.css"/>
		<link rel="stylesheet" href="<%=basePath%>inc/css/shop.css"/>
		<script src="<%=basePath%>inc/js/jquery.min.js" type="text/javascript"></script>
		<script type="text/javascript" src="<%=basePath%>inc/js/jquery.cycle.js"></script>
		<script type="text/javascript">
			$(function(){
				$('#banner').cycle({ 
						fx:'scrollLeft',
						pager:'#btn'
				});
			})
		</script>
		<title>店铺首页</title>
	</head>
	<body>
		<!--头部开始-->
		<%@ include file="/WEB-INF/view/inc/top.jsp" %>
		
		<div class="top w1200 clearfix">
			<div class="logo-shop fl">
				<a href="###"><img src="<%=basePath%>inc/images/logo-shop.png"/>
				</a>
				<b><h3>
						${shopconfig.shop_name }
					</h3>
					<a href="#" class="str-btn">品牌直销</a>
				</b>
			</div>
			<div class="search fl">
				<div class="form fl">
					<input type="text" class="sear-txt" value="请输入关键词" />
					<input type="button" class="sear-btn" value="搜全站" />
				</div>
				<div class="fl">
					<input type="button" class="sear-btn2" value="搜全站" />
				</div>
				<div class="clearfix"></div>
				<div class="hotsear">
					<strong>热门：</strong>
					<a href="###">茅台财富酒</a>
					<a href="###">珠江地产</a>
					<a href="###">宝马X5</a>
					<a href="###">恒大矿泉水</a>
					<a href="###">碧欧泉</a>
					<a href="###">江诗丹顿</a>
				</div>
			</div>
			<div class="fr">
				<div class="buycar">
					<b></b>购物车（
					<a href="###">0</a>）件
				</div>
				<div class="safe">
					<div class="safe-cont">
						<b></b>
						<ul>
							<li>
								正品保证
							</li>
							<li class="en">
								Guarantee
							</li>
						</ul>
					</div>
					<div class="safe-cont safe-cont2">
						<b></b>
						<ul>
							<li>
								无理由退换货
							</li>
							<li class="en">
								Without Reason
							</li>
						</ul>
					</div>
				</div>

			</div>
		</div>

		<!--nav开始-->
		<div class="nav">
			<div class="w1200" id="daohang">
				<div class="classify tab fl dropdown">
					<a href="#" class="tablink arwlink">全部商品分类<b></b>
					</a>
					<ul class="subnav">
						<li class="subnav-tit1">
							<b></b><strong>酒</strong>
						</li>
						<li class="subnav-as">
							<a href="#">茅台</a><a href="#">郎酒</a><a href="#">汾酒</a><a href="#">五粮液</a><a href="#">拉菲</a><a href="#">泸州老窖</a><a href="#">牛栏山</a><a href="#">古井贡酒</a>
						</li>
						<li class="subnav-tit2">
							<b></b><strong>茶</strong>
						</li>
						<li class="subnav-as">
							<a href="#">铁观音</a><a href="#">西湖龙井</a><a href="#">大红袍</a><a href="#">荷叶茶</a><a href="#">菊花</a><a href="#">苦荞茶</a><a href="#">碧螺春</a>
						</li>
						<li class="subnav-tit3">
							<b></b><strong>瓷</strong>
						</li>
						<li class="subnav-as">
							<a href="#">卢米</a><a href="#">中国龙瓷</a><a href="#">傲世</a><a href="#">御晟</a><a href="#">爱爵（AIJUE）</a><a href="#">红鑫</a><a href="#">红叶</a>
						</li>
						<li class="subnav-tit4">
							<b></b><strong>珠宝</strong>
						</li>
						<li class="subnav-as">
							<a href="#">周生生</a><a href="#">佐卡伊</a><a href="#">六福珠宝</a><a href="#">潮宏基</a><a href="#">周大福</a><a href="#">七彩云南</a>
						</li>
						<li class="subnav-tit5">
							<b></b><strong>旅游</strong><a href="#" class="subnav-a">热门推荐</a>
						</li>
						<li class="subnav-tit6">
							<b></b><strong>收藏</strong><a href="#" class="subnav-a">精品热搜</a>
						</li>
						<li class="subnav-tit7">
							<b></b><strong>表</strong><a href="#" class="subnav-a">新品精选</a>
						</li>
					</ul>
				</div>
				<ul class="nav-cont fl">
					<li>
						<a href="<%=basePath%>">首页</a>
					</li>
					<li>
						<a href="Index.html">整箱特惠</a><s></s>
					</li>
					<li>
						<a href="Index.html">坛装原酒</a><s></s>
					</li>
					<li>
						<a href="Index.html">特价清仓</a><s></s>
					</li>
				</ul>
			</div>
		</div>
		<!--subbanner开始-->
		<div id="banner" class="subbanner">
			<img src="<%=basePath%>inc/images/subbanner01.png"  width="1920" height="342" />
			<img src="<%=basePath%>inc/images/banner01.png" 	width="1862" height="342"/>
			<img src="<%=basePath%>inc/images/subbanner01.png"  width="1920" height="342"/>
			<img src="<%=basePath%>inc/images/banner01.png" 	width="1862" height="342"/>
		</div>
		<div id="btn" class="btns"></div>
		<!--个人中心-->
		<div class="w1200">
			<div class="shop">
				<h2>
					<span>全部热卖</span><em>Hot</em>
				</h2>
				<div class="shop-pro fr">
					<ul>
						<c:forEach items="${pageResult.list}" var="goods" varStatus="status">
							<c:if test="${status.index<12}">
								<li>
									<span class="img-collect">
										<a href="<%=basePath %>goods/${goods.goods_id}.action">
											<img src="<h:imgSubstr imgpath="${goods.img_path}"/>" />
										</a>
									</span>
									<div class="img-dec">
										<a href="<%=basePath %>goods/${goods.goods_id}.action" class="name" title="${goods.goods_name}">${goods.goods_name}</a>
										<div class="price">
											<strong>¥${goods.sale_price}</strong><em>¥${goods.market_price}</em>
										</div>
										<a href="<%=basePath%>directBuy.action?goods.goods_id=${goods.goods_id}&amount=1" class="buybtn">立即购买></a>
									</div>
								</li>
							</c:if>
						</c:forEach>
					</ul>
				</div>
			</div>
		</div>
		<div class="clearfix"></div>

		<!--分类开始-->
	<c:forEach items="${membercatList}" var="membercat" >
		<div class="w1200">
			<div class="wine">
				<h2>
					<b>${membercat.cat_name}<em></em></b>
					<c:if test="${!empty membercat.shoopGoodsList.list}">
						<a href="#" class="more">查看更多 >></a>
					</c:if>
				</h2>
				<c:if test="${empty membercat.shoopGoodsList.list}">
					<div style="padding-top:20px;">暂无商品</div>
				</c:if>
				<c:if test="${!empty membercat.shoopGoodsList.list}">
				<div class="wine-cont">
					<ul>
						<c:forEach items="${membercat.shoopGoodsList.list}" var="goods">
							<li style="margin-right: 0;">
								<span class="img-collect"><a href="<%=basePath %>goods/${goods.goods_id}.action">
								<img src="<h:imgSubstr imgpath="${goods.img_path}"/>" onload="DrawImage(this,90,90)"/></a>
								</span>
								<div class="img-dec">
									<a href="<%=basePath %>goods/${goods.goods_id}.action" class="name">${goods.goods_name}</a>
									<div class="price">
										<strong>¥${goods.sale_price}</strong><em>¥${goods.market_price}</em>
									</div>
									<a href="<%=basePath%>directBuy.action?goods.goods_id=${goods.goods_id}&amount=1" class="buybtn2">立即购买></a>
								</div>
							</li>
						</c:forEach>
					</ul>
				</div>
			  </c:if>
			</div>
		</div>
	<div class="clearfix"></div>
	</c:forEach>	
		<!--分类结束-->
		
		<div class="clearfix"></div>
		<!--底部-->
		<div class="footer">
			<div class="footernav clearfix">
				<a href="###">关于我们<span>|</span>
				</a>
				<a href="###">联系我们<span>|</span>
				</a>
				<a href="###">人才招聘<span>|</span>
				</a>
				<a href="###">商家入驻<span>|</span>
				</a>
				<a href="###">广告服务<span>|</span>
				</a>
				<a href="###">友情链接</a>
			</div>
			<div class="copyright">
				<p>
					北京市公安局朝阳分局备案编号110105014669
					<span>|</span>京ICP证070359号
					<span>|</span>互联网药品信息服务资格证编号(京)-非经营性-2011-0034
					<span>|</span>新出发京零 字第大120007号
					<br />
					出版物经营许可证编号新出发(京)批字第N-012号
					<span>|</span>互联网出版许可证编号新出网证(京)字150号 网络文化经营许可证京网文[2011]0168-061号
				</p>
				<p>
					Copyright © 2014 www.txmp.com.cn 版权所有
				</p>
			</div>
			<div class="authentication">
				<a href="#"><img src="<%=basePath%>inc/images/cp01.png" width="108" height="40" />
				</a>
				<a href="#"><img src="<%=basePath%>inc/images/cp02.png" width="108" height="40" />
				</a>
				<a href="#"><img src="<%=basePath%>inc/images/cp03.png" width="108" height="40" />
				</a>
				<a href="#"><img src="<%=basePath%>inc/images/cp04.png" width="112" height="40" />
				</a>
			</div>
		</div>
	</body>
</html>

