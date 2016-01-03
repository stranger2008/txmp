<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/WEB-INF/view/inc/inc.jsp" %>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link rel="stylesheet" href="<%=basePath %>inc/css/public.css">
		<link rel="stylesheet" href="<%=basePath %>inc/css/index.css">
		<script src="<%=basePath %>inc/js/jquery.min.js" type="text/javascript"></script>
		<script type="text/javascript" src="<%=basePath %>inc/js/jquery.cycle.js"></script>
		<script type="text/javascript">
	        $(function(){
	            $('#banner').cycle({
	                fx:'scrollLeft',
	                pager:'#btn'
	            });
	        })﻿
   		 </script>
		<script type="text/javascript" src="<%=basePath %>inc/js/jquery.jslides1.js"></script>
		<script type="text/javascript" src="<%=basePath %>inc/js/jquery.jslides2.js"></script>
		<script type="text/javascript" src="<%=basePath %>inc/js/jquery.jslides3.js"></script>
		<script type="text/javascript" src="<%=basePath %>inc/js/jquery.jslides4.js"></script>

		<script type="text/javascript" src="<%=basePath %>inc/js/jquery.silver_track.js" charset="utf-8"></script>
		<script type="text/javascript" src="<%=basePath %>inc/js/jquery.silver_track.navigator.js" charset="utf-8"></script>
		<script type="text/javascript" src="<%=basePath %>inc/js/jquery.silver_track.bullet_navigator.js" charset="utf-8"></script>
		<script type="text/javascript" src="<%=basePath %>inc/js/jquery.silver_track.responsive_hub_connector.js" charset="utf-8"></script>
		<script type="text/javascript" src="<%=basePath %>inc/js/script.js" charset="utf-8"></script>
		<script type="text/javascript" src="<%=basePath %>inc/js/example2_and_3.js" charset="utf-8"></script>
		<script type="text/javascript" src="<%=basePath %>inc/js/area_selected.js"></script>
		<title><%=webname %></title>
	</head>
	<body>
		<div class="backpanel">
			<div class="box">
				<a href="<%=basePath %>cartlist.action" class="back-btn1"><b></b><span>购物车</span></a>
			</div>
			<div class="box">
				<a href="###" class="back-btn2"><b></b><span>在线客服</span></a>
			</div>
			<div class="box">
				<a href="###" class="back-btn3"><b></b><span>在线反馈</span></a>
			</div>
			<div class="box">
				<a href="###" class="back-btn4"><b></b><span>回到顶部</span></a>
			</div>
		</div>

		 <%@ include file="/WEB-INF/view/inc/top.jsp" %>
		
		<%@ include file="/WEB-INF/view/inc/search.jsp" %>
		
		<%@ include file="/WEB-INF/view/inc/nav.jsp" %>

		<div id="banner">
			<img src="<%=basePath %>inc/images/banner01.png" width="1862" height="450">
			<img src="<%=basePath %>inc/images/banner01.png" width="1862" height="450">
			<img src="<%=basePath %>inc/images/banner01.png" width="1862" height="450">
			<img src="<%=basePath %>inc/images/banner01.png" width="1862" height="450">
			<img src="<%=basePath %>inc/images/banner01.png" width="1862" height="450">
			<img src="<%=basePath %>inc/images/banner01.png" width="1862" height="450">
		</div>
		<div id="btn"></div>


		<div class="w1200">
			<div class="clearfix">
				<!--图片列表-->
				<div class="listpic fl">
					<div class="track">
						<div class="inner">

							<div class="view-port">
								<div id="example-2" class="slider-container big">

									<div class="item">
										<div class="img-box">
											<span class="img-media"><a href="#"><img
														src="<%=basePath %>inc/images/listpic01.png">
											</a>
											</span>
											<div class="ll">
												<h3>
													天下名酒
												</h3>
												<p>
													男人的情怀
												</p>
												<p class="red">
													经典全球首发
												</p>
												<a href="###">> 更多品牌</a>
											</div>
										</div>
										<div class="img-box">
											<span class="img-media"><a href="#"><img
														src="<%=basePath %>inc/images/listpic02.png">
											</a>
											</span>
											<div class="ll">
												<h3>
													天下名酒
												</h3>
												<p>
													男人的情怀
												</p>
												<p class="red">
													经典全球首发
												</p>
												<a href="###">> 更多品牌</a>
											</div>
										</div>
									</div>

									<div class="item">
										<div class="img-box">
											<span class="img-media"><a href="#"><img
														src="<%=basePath %>inc/images/listpic03.png">
											</a>
											</span>
											<div class="ll">
												<h3>
													天下名酒
												</h3>
												<p>
													男人的情怀
												</p>
												<p class="red">
													经典全球首发
												</p>
												<a href="###">> 更多品牌</a>
											</div>
										</div>
										<div class="img-box">
											<span class="img-media"><a href="#"><img src="<%=basePath %>inc/images/listpic04.png">
											</a>
											</span>
											<div class="ll">
												<h3>
													天下名酒
												</h3>
												<p>
													男人的情怀
												</p>
												<p class="red">
													经典全球首发
												</p>
												<a href="###">> 更多品牌</a>
											</div>
										</div>
									</div>

									<div class="item">
										<div class="img-box">
											<span class="img-media"><a href="#"><img
														src="<%=basePath %>inc/images/listpic05.png">
											</a>
											</span>
											<div class="ll">
												<h3>
													天下名酒
												</h3>
												<p>
													男人的情怀
												</p>
												<p class="red">
													经典全球首发
												</p>
												<a href="###">> 更多品牌</a>
											</div>
										</div>
										<div class="img-box">
											<span class="img-media"><a href="#"><img
														src="<%=basePath %>inc/images/listpic06.png">
											</a>
											</span>
											<div class="ll">
												<h3>
													天下名酒
												</h3>
												<p>
													男人的情怀
												</p>
												<p class="red">
													经典全球首发
												</p>
												<a href="###">> 更多品牌</a>
											</div>
										</div>
									</div>

									<div class="item">
										<div class="img-box">
											<span class="img-media"><a href="#"><img
														src="<%=basePath %>inc/images/listpic01.png">
											</a>
											</span>
											<div class="ll">
												<h3>
													天下名酒
												</h3>
												<p>
													男人的情怀
												</p>
												<p class="red">
													经典全球首发
												</p>
												<a href="###">> 更多品牌</a>
											</div>
										</div>
										<div class="img-box">
											<span class="img-media"><a href="#"><img
														src="<%=basePath %>inc/images/listpic02.png">
											</a>
											</span>
											<div class="ll">
												<h3>
													天下名酒
												</h3>
												<p>
													男人的情怀
												</p>
												<p class="red">
													经典全球首发
												</p>
												<a href="###">> 更多品牌</a>
											</div>
										</div>
									</div>

									<div class="item">
										<div class="img-box">
											<span class="img-media"><a href="#"><img
														src="<%=basePath %>inc/images/listpic03.png">
											</a>
											</span>
											<div class="ll">
												<h3>
													天下名酒
												</h3>
												<p>
													男人的情怀
												</p>
												<p class="red">
													经典全球首发
												</p>
												<a href="###">> 更多品牌</a>
											</div>
										</div>
										<div class="img-box">
											<span class="img-media"><a href="#"><img
														src="<%=basePath %>inc/images/listpic04.png">
											</a>
											</span>
											<div class="ll">
												<h3>
													天下名酒
												</h3>
												<p>
													男人的情怀
												</p>
												<p class="red">
													经典全球首发
												</p>
												<a href="###">> 更多品牌</a>
											</div>
										</div>
									</div>

									<div class="item">
										<div class="img-box">
											<span class="img-media"><a href="#"><img
														src="<%=basePath %>inc/images/listpic05.png">
											</a>
											</span>
											<div class="ll">
												<h3>
													天下名酒
												</h3>
												<p>
													男人的情怀
												</p>
												<p class="red">
													经典全球首发
												</p>
												<a href="###">> 更多品牌</a>
											</div>
										</div>
										<div class="img-box">
											<span class="img-media"><a href="#"><img
														src="<%=basePath %>inc/images/listpic06.png">
											</a>
											</span>
											<div class="ll">
												<h3>
													天下名酒
												</h3>
												<p>
													男人的情怀
												</p>
												<p class="red">
													经典全球首发
												</p>
												<a href="###">> 更多品牌</a>
											</div>
										</div>
									</div>

								</div>
							</div>
							<div class="pagination">
								<a href="###" class="prev disabled"></a>
								<a href="###" class="next disabled"></a>
							</div>

						</div>
					</div>
				</div>

				<!--热销排行榜-->
				<div class="hotlist fr">
					<h2>
						热销排行榜
					</h2>
					<div class="hotpic clearfix">
						<a href="###" class="fl"><img src="<%=basePath %>inc/images/hotpic.png">
						</a>
						<div class="hotpic-c fr">
							<h3>
								<a href="#">女装青年藏蓝色韩范宽松</a>
							</h3>
							<div class="price1">
								¥113.00
							</div>
							<div class="price2">
								¥228.00
							</div>
							<p>
								已售出
								<a href="###">9588</a>笔
							</p>
						</div>
					</div>
					<ul>
						<li>
							<a href="###"><b></b>疯抢中秋月饼低至39</a>
						</li>
						<li>
							<a href="###"><b></b>疯抢中秋月饼低至39</a>
						</li>
						<li>
							<a href="###"><b></b>疯抢中秋月饼低至39</a>
						</li>
						<li>
							<a href="###"><b></b>疯抢中秋月饼低至39</a>
						</li>
						<li>
							<a href="###"><b></b>疯抢中秋月饼低至39</a>
						</li>
						<li>
							<a href="###"><b></b>疯抢中秋月饼低至39</a>
						</li>
					</ul>
				</div>
			</div>

			<!--新品上架-->
			<div class="newpro">
				<h2>
					新品上架
				</h2>
				<div class="clearfix">
					<div class="fl">
						<div class="product-l">
							<img src="<%=basePath %>inc/images/product-l01.png">
							<p class="det01"></p>
                   			 <a href="###">产品名称</a>  
						</div>
						<div class="product-cla">
							<a href="###">进口食品</a>
							<a href="###">地方特产</a>
							<a href="###">休闲食品</a>
							<a href="###">中外名酒</a>
							<a href="###">冲调饮品</a>
							<a href="###">粮油调味</a>
							<a href="###">营养健康</a>
							<a href="###">营养成分</a>
							<a href="###">传统滋补</a>
							<a href="###">生鲜食品</a>
						</div>
					</div>
					<div class="fl">
						<!-- 代码 开始 -->
						<div id="full-screen-slider1"
							style="width: 790px; height: 424px; margin-left: 10px;">
							<ul id="slides1">
								<li
									style="background: url('<%=basePath %>inc/images/newpro01.png') no-repeat center top">
									<a href="###" target="_blank"> </a>
								</li>
								<li
									style="background: url('<%=basePath %>inc/images/newpro02.png') no-repeat center top">
									<a href="###" target="_blank"> </a>
								</li>
								<li
									style="background: url('<%=basePath %>inc/images/newpro03.png') no-repeat center top">
									<a href="###" target="_blank"> </a>
								</li>
								<li
									style="background: url('<%=basePath %>inc/images/newpro04.png') no-repeat center top">
									<a href="###" target="_blank"> </a>
								</li>
							</ul>
						</div>
						<!-- 代码 结束 -->
					</div>
					<div class="product-r fr">
						<a href="###"><img src="<%=basePath %>inc/images/product-r01.png">
						</a>
						<a href="###" class="mt10"><img src="<%=basePath %>inc/images/product-r02.png">
						</a>
					</div>
				</div>
				<div class="propiclist">
					<ul>
						<li>
							<a href="###">
								<div class="pro-d1">
									<img src="<%=basePath %>inc/images/propiclist01.png">
									<div class="pro-p">
										<p>
											<strong>普拉达</strong>
										</p>
										<p>
											手提斜挎包
										</p>
										<p>
											<em>¥1950</em>
										</p>
									</div>
								</div>
								<div class="pro-d2">
									<p>
										<strong>产品名称</strong>
									</p>
									<p>
										齐心复印纸超值满减齐心复印纸超值满减齐心复印纸超值满减
									</p>
									<p>
										<em>满199减40&豪华大礼 </em>
									</p>
								</div> </a>
						</li>
						<li>
							<a href="###">
								<div class="pro-d1">
									<img src="<%=basePath %>inc/images/propiclist02.png">
									<div class="pro-p">
										<p>
											<strong>普拉达</strong>
										</p>
										<p>
											手提斜挎包
										</p>
										<p>
											<em>¥1950</em>
										</p>
									</div>
								</div>
								<div class="pro-d2">
									<p>
										<strong>产品名称</strong>
									</p>
									<p>
										齐心复印纸超值满减齐心复印纸超值满减齐心复印纸超值满减
									</p>
									<p>
										<em>满199减40&豪华大礼 </em>
									</p>
								</div> </a>
						</li>
						<li>
							<a href="###">
								<div class="pro-d1">
									<img src="<%=basePath %>inc/images/propiclist03.png">
									<div class="pro-p">
										<p>
											<strong>普拉达</strong>
										</p>
										<p>
											手提斜挎包
										</p>
										<p>
											<em>¥1950</em>
										</p>
									</div>
								</div>
								<div class="pro-d2">
									<p>
										<strong>产品名称</strong>
									</p>
									<p>
										齐心复印纸超值满减齐心复印纸超值满减齐心复印纸超值满减
									</p>
									<p>
										<em>满199减40&豪华大礼 </em>
									</p>
								</div> </a>
						</li>
						<li>
							<a href="###">
								<div class="pro-d1">
									<img src="<%=basePath %>inc/images/propiclist04.png">
									<div class="pro-p">
										<p>
											<strong>普拉达</strong>
										</p>
										<p>
											手提斜挎包
										</p>
										<p>
											<em>¥1950</em>
										</p>
									</div>
								</div>
								<div class="pro-d2">
									<p>
										<strong>产品名称</strong>
									</p>
									<p>
										齐心复印纸超值满减齐心复印纸超值满减齐心复印纸超值满减
									</p>
									<p>
										<em>满199减40&豪华大礼 </em>
									</p>
								</div> </a>
						</li>
						<li>
							<a href="###">
								<div class="pro-d1">
									<img src="<%=basePath %>inc/images/propiclist05.png">
									<div class="pro-p">
										<p>
											<strong>普拉达</strong>
										</p>
										<p>
											手提斜挎包
										</p>
										<p>
											<em>¥1950</em>
										</p>
									</div>
								</div>
								<div class="pro-d2">
									<p>
										<strong>产品名称</strong>
									</p>
									<p>
										齐心复印纸超值满减齐心复印纸超值满减齐心复印纸超值满减
									</p>
									<p>
										<em>满199减40&豪华大礼 </em>
									</p>
								</div> </a>
						</li>
					</ul>
				</div>
			</div>

			<!--超级划算-->
			<div class="newpro">
				<h2>
					超级划算
				</h2>
				<div class="clearfix">
					<div class="fl">
						<div class="product-l">
							<img src="<%=basePath %>inc/images/product-l02.png">
							<p class="det02"></p>
                   			 <a href="###">产品名称</a> 
						</div>
						<div class="product-cla">
							<a href="###">进口食品</a>
							<a href="###">地方特产</a>
							<a href="###">休闲食品</a>
							<a href="###">中外名酒</a>
							<a href="###">冲调饮品</a>
							<a href="###">粮油调味</a>
							<a href="###">营养健康</a>
							<a href="###">营养成分</a>
							<a href="###">传统滋补</a>
							<a href="###">生鲜食品</a>
						</div>
					</div>
					<div class="fl">
						<!-- 代码 开始 -->
						<div id="full-screen-slider2"
							style="width: 790px; height: 424px; margin-left: 10px;">
							<ul id="slides2">
								<li
									style="background: url('<%=basePath %>inc/images/newpro01.png') no-repeat center top">
									<a href="###" target="_blank"> </a>
								</li>
								<li
									style="background: url('<%=basePath %>inc/images/newpro02.png') no-repeat center top">
									<a href="###" target="_blank"> </a>
								</li>
								<li
									style="background: url('<%=basePath %>inc/images/newpro03.png') no-repeat center top">
									<a href="###" target="_blank"> </a>
								</li>
								<li
									style="background: url('<%=basePath %>inc/images/newpro04.png') no-repeat center top">
									<a href="###" target="_blank"> </a>
								</li>
							</ul>
						</div>
						<!-- 代码 结束 -->
					</div>
					<div class="product-r fr">
						<a href="###"><img src="<%=basePath %>inc/images/product-r03.png">
						</a>
						<a href="###" class="mt10"><img src="<%=basePath %>inc/images/product-r04.png">
						</a>
					</div>
				</div>
				<div class="propiclist">
					<ul>
						<li>
							<a href="###">
								<div class="pro-d1">
									<img src="<%=basePath %>inc/images/propiclist01.png">
									<div class="pro-p">
										<p>
											<strong>普拉达</strong>
										</p>
										<p>
											手提斜挎包
										</p>
										<p>
											<em>¥1950</em>
										</p>
									</div>
								</div>
								<div class="pro-d2">
									<p>
										<strong>产品名称</strong>
									</p>
									<p>
										齐心复印纸超值满减齐心复印纸超值满减齐心复印纸超值满减
									</p>
									<p>
										<em>满199减40&豪华大礼 </em>
									</p>
								</div> </a>
						</li>
						<li>
							<a href="###">
								<div class="pro-d1">
									<img src="<%=basePath %>inc/images/propiclist02.png">
									<div class="pro-p">
										<p>
											<strong>普拉达</strong>
										</p>
										<p>
											手提斜挎包
										</p>
										<p>
											<em>¥1950</em>
										</p>
									</div>
								</div>
								<div class="pro-d2">
									<p>
										<strong>产品名称</strong>
									</p>
									<p>
										齐心复印纸超值满减齐心复印纸超值满减齐心复印纸超值满减
									</p>
									<p>
										<em>满199减40&豪华大礼 </em>
									</p>
								</div> </a>
						</li>
						<li>
							<a href="###">
								<div class="pro-d1">
									<img src="<%=basePath %>inc/images/propiclist03.png">
									<div class="pro-p">
										<p>
											<strong>普拉达</strong>
										</p>
										<p>
											手提斜挎包
										</p>
										<p>
											<em>¥1950</em>
										</p>
									</div>
								</div>
								<div class="pro-d2">
									<p>
										<strong>产品名称</strong>
									</p>
									<p>
										齐心复印纸超值满减齐心复印纸超值满减齐心复印纸超值满减
									</p>
									<p>
										<em>满199减40&豪华大礼 </em>
									</p>
								</div> </a>
						</li>
						<li>
							<a href="###">
								<div class="pro-d1">
									<img src="<%=basePath %>inc/images/propiclist04.png">
									<div class="pro-p">
										<p>
											<strong>普拉达</strong>
										</p>
										<p>
											手提斜挎包
										</p>
										<p>
											<em>¥1950</em>
										</p>
									</div>
								</div>
								<div class="pro-d2">
									<p>
										<strong>产品名称</strong>
									</p>
									<p>
										齐心复印纸超值满减齐心复印纸超值满减齐心复印纸超值满减
									</p>
									<p>
										<em>满199减40&豪华大礼 </em>
									</p>
								</div> </a>
						</li>
						<li>
							<a href="###">
								<div class="pro-d1">
									<img src="<%=basePath %>inc/images/propiclist05.png">
									<div class="pro-p">
										<p>
											<strong>普拉达</strong>
										</p>
										<p>
											手提斜挎包
										</p>
										<p>
											<em>¥1950</em>
										</p>
									</div>
								</div>
								<div class="pro-d2">
									<p>
										<strong>产品名称</strong>
									</p>
									<p>
										齐心复印纸超值满减齐心复印纸超值满减齐心复印纸超值满减
									</p>
									<p>
										<em>满199减40&豪华大礼 </em>
									</p>
								</div> </a>
						</li>
					</ul>
				</div>
			</div>

			<!--持续热卖-->
			<div class="newpro">
				<h2>
					持续热卖
				</h2>
				<div class="clearfix">
					<div class="fl">
						<div class="product-l">
							<img src="<%=basePath %>inc/images/product-l03.png">
						     <p class="det03"></p>
                   			 <a href="###">产品名称</a> 
						</div>
						<div class="product-cla">
							<a href="###">进口食品</a>
							<a href="###">地方特产</a>
							<a href="###">休闲食品</a>
							<a href="###">中外名酒</a>
							<a href="###">冲调饮品</a>
							<a href="###">粮油调味</a>
							<a href="###">营养健康</a>
							<a href="###">营养成分</a>
							<a href="###">传统滋补</a>
							<a href="###">生鲜食品</a>
						</div>
					</div>
					<div class="fl">
						<!-- 代码 开始 -->
						<div id="full-screen-slider3"
							style="width: 790px; height: 424px; margin-left: 10px;">
							<ul id="slides3">
								<li
									style="background: url('<%=basePath %>inc/images/newpro01.png') no-repeat center top">
									<a href="###" target="_blank"> </a>
								</li>
								<li
									style="background: url('<%=basePath %>inc/images/newpro02.png') no-repeat center top">
									<a href="###" target="_blank"> </a>
								</li>
								<li
									style="background: url('<%=basePath %>inc/images/newpro03.png') no-repeat center top">
									<a href="###" target="_blank"> </a>
								</li>
								<li
									style="background: url('<%=basePath %>inc/images/newpro04.png') no-repeat center top">
									<a href="###" target="_blank"> </a>
								</li>
							</ul>
						</div>
						<!-- 代码 结束 -->
					</div>
					<div class="product-r fr">
						<a href="###"><img src="<%=basePath %>inc/images/product-r05.png">
						</a>
						<a href="###" class="mt10"><img src="<%=basePath %>inc/images/product-r06.png">
						</a>
					</div>
				</div>
				<div class="propiclist">
					<ul>
						<li>
							<a href="###">
								<div class="pro-d1">
									<img src="<%=basePath %>inc/images/propiclist01.png">
									<div class="pro-p">
										<p>
											<strong>普拉达</strong>
										</p>
										<p>
											手提斜挎包
										</p>
										<p>
											<em>¥1950</em>
										</p>
									</div>
								</div>
								<div class="pro-d2">
									<p>
										<strong>产品名称</strong>
									</p>
									<p>
										齐心复印纸超值满减齐心复印纸超值满减齐心复印纸超值满减
									</p>
									<p>
										<em>满199减40&豪华大礼 </em>
									</p>
								</div> </a>
						</li>
						<li>
							<a href="###">
								<div class="pro-d1">
									<img src="<%=basePath %>inc/images/propiclist02.png">
									<div class="pro-p">
										<p>
											<strong>普拉达</strong>
										</p>
										<p>
											手提斜挎包
										</p>
										<p>
											<em>¥1950</em>
										</p>
									</div>
								</div>
								<div class="pro-d2">
									<p>
										<strong>产品名称</strong>
									</p>
									<p>
										齐心复印纸超值满减齐心复印纸超值满减齐心复印纸超值满减
									</p>
									<p>
										<em>满199减40&豪华大礼 </em>
									</p>
								</div> </a>
						</li>
						<li>
							<a href="###">
								<div class="pro-d1">
									<img src="<%=basePath %>inc/images/propiclist03.png">
									<div class="pro-p">
										<p>
											<strong>普拉达</strong>
										</p>
										<p>
											手提斜挎包
										</p>
										<p>
											<em>¥1950</em>
										</p>
									</div>
								</div>
								<div class="pro-d2">
									<p>
										<strong>产品名称</strong>
									</p>
									<p>
										齐心复印纸超值满减齐心复印纸超值满减齐心复印纸超值满减
									</p>
									<p>
										<em>满199减40&豪华大礼 </em>
									</p>
								</div> </a>
						</li>
						<li>
							<a href="###">
								<div class="pro-d1">
									<img src="<%=basePath %>inc/images/propiclist04.png">
									<div class="pro-p">
										<p>
											<strong>普拉达</strong>
										</p>
										<p>
											手提斜挎包
										</p>
										<p>
											<em>¥1950</em>
										</p>
									</div>
								</div>
								<div class="pro-d2">
									<p>
										<strong>产品名称</strong>
									</p>
									<p>
										齐心复印纸超值满减齐心复印纸超值满减齐心复印纸超值满减
									</p>
									<p>
										<em>满199减40&豪华大礼 </em>
									</p>
								</div> </a>
						</li>
						<li>
							<a href="###">
								<div class="pro-d1">
									<img src="<%=basePath %>inc/images/propiclist05.png">
									<div class="pro-p">
										<p>
											<strong>普拉达</strong>
										</p>
										<p>
											手提斜挎包
										</p>
										<p>
											<em>¥1950</em>
										</p>
									</div>
								</div>
								<div class="pro-d2">
									<p>
										<strong>产品名称</strong>
									</p>
									<p>
										齐心复印纸超值满减齐心复印纸超值满减齐心复印纸超值满减
									</p>
									<p>
										<em>满199减40&豪华大礼 </em>
									</p>
								</div> </a>
						</li>
					</ul>
				</div>
			</div>

			<!--强力推荐-->
			<div class="newpro">
				<h2>
					强力推荐
				</h2>
				<div class="clearfix">
					<div class="fl">
						<div class="product-l">
							<img src="<%=basePath %>inc/images/product-l02.png">
							<p class="det04"></p>
                    		<a href="###">产品名称</a>  
						</div>
						<div class="product-cla">
							<a href="###">进口食品</a>
							<a href="###">地方特产</a>
							<a href="###">休闲食品</a>
							<a href="###">中外名酒</a>
							<a href="###">冲调饮品</a>
							<a href="###">粮油调味</a>
							<a href="###">营养健康</a>
							<a href="###">营养成分</a>
							<a href="###">传统滋补</a>
							<a href="###">生鲜食品</a>
						</div>
					</div>
					<div class="fl">
						<!-- 代码 开始 -->
						<div id="full-screen-slider4"
							style="width: 790px; height: 424px; margin-left: 10px;">
							<ul id="slides4">
								<li
									style="background: url('<%=basePath %>inc/images/newpro01.png') no-repeat center top">
									<a href="###" target="_blank"> </a>
								</li>
								<li
									style="background: url('<%=basePath %>inc/images/newpro02.png') no-repeat center top">
									<a href="###" target="_blank"> </a>
								</li>
								<li
									style="background: url('<%=basePath %>inc/images/newpro03.png') no-repeat center top">
									<a href="###" target="_blank"> </a>
								</li>
								<li
									style="background: url('<%=basePath %>inc/images/newpro04.png') no-repeat center top">
									<a href="###" target="_blank"> </a>
								</li>
							</ul>
						</div>
						<!-- 代码 结束 -->
					</div>
					<div class="product-r fr">
						<a href="###"><img src="<%=basePath %>inc/images/product-r03.png">
						</a>
						<a href="###" class="mt10"><img src="<%=basePath %>inc/images/product-r04.png">
						</a>
					</div>
				</div>
				<div class="propiclist">
					<ul>
						<li>
							<a href="###">
								<div class="pro-d1">
									<img src="<%=basePath %>inc/images/propiclist01.png">
									<div class="pro-p">
										<p>
											<strong>普拉达</strong>
										</p>
										<p>
											手提斜挎包
										</p>
										<p>
											<em>¥1950</em>
										</p>
									</div>
								</div>
								<div class="pro-d2">
									<p>
										<strong>产品名称</strong>
									</p>
									<p>
										齐心复印纸超值满减齐心复印纸超值满减齐心复印纸超值满减
									</p>
									<p>
										<em>满199减40&豪华大礼 </em>
									</p>
								</div> </a>
						</li>
						<li>
							<a href="###">
								<div class="pro-d1">
									<img src="<%=basePath %>inc/images/propiclist02.png">
									<div class="pro-p">
										<p>
											<strong>普拉达</strong>
										</p>
										<p>
											手提斜挎包
										</p>
										<p>
											<em>¥1950</em>
										</p>
									</div>
								</div>
								<div class="pro-d2">
									<p>
										<strong>产品名称</strong>
									</p>
									<p>
										齐心复印纸超值满减齐心复印纸超值满减齐心复印纸超值满减
									</p>
									<p>
										<em>满199减40&豪华大礼 </em>
									</p>
								</div> </a>
						</li>
						<li>
							<a href="###">
								<div class="pro-d1">
									<img src="<%=basePath %>inc/images/propiclist03.png">
									<div class="pro-p">
										<p>
											<strong>普拉达</strong>
										</p>
										<p>
											手提斜挎包
										</p>
										<p>
											<em>¥1950</em>
										</p>
									</div>
								</div>
								<div class="pro-d2">
									<p>
										<strong>产品名称</strong>
									</p>
									<p>
										齐心复印纸超值满减齐心复印纸超值满减齐心复印纸超值满减
									</p>
									<p>
										<em>满199减40&豪华大礼 </em>
									</p>
								</div> </a>
						</li>
						<li>
							<a href="###">
								<div class="pro-d1">
									<img src="<%=basePath %>inc/images/propiclist04.png">
									<div class="pro-p">
										<p>
											<strong>普拉达</strong>
										</p>
										<p>
											手提斜挎包
										</p>
										<p>
											<em>¥1950</em>
										</p>
									</div>
								</div>
								<div class="pro-d2">
									<p>
										<strong>产品名称</strong>
									</p>
									<p>
										齐心复印纸超值满减齐心复印纸超值满减齐心复印纸超值满减
									</p>
									<p>
										<em>满199减40&豪华大礼 </em>
									</p>
								</div> </a>
						</li>
						<li>
							<a href="###">
								<div class="pro-d1">
									<img src="<%=basePath %>inc/images/propiclist05.png">
									<div class="pro-p">
										<p>
											<strong>普拉达</strong>
										</p>
										<p>
											手提斜挎包
										</p>
										<p>
											<em>¥1950</em>
										</p>
									</div>
								</div>
								<div class="pro-d2">
									<p>
										<strong>产品名称</strong>
									</p>
									<p>
										齐心复印纸超值满减齐心复印纸超值满减齐心复印纸超值满减
									</p>
									<p>
										<em>满199减40&豪华大礼 </em>
									</p>
								</div> </a>
						</li>
					</ul>
				</div>
			</div>

			<div class="advertise">
				<a href="###"><img src="<%=basePath %>inc/images/advertise.png">
				</a>
			</div>

		</div>


		<!--正品保证-->
		 <%@ include file="/WEB-INF/view/inc/footer_help.jsp" %>
		<div class="clearfix"></div>

		<!--底部-->
		 <%@ include file="/WEB-INF/view/inc/footer.jsp" %>
	</body>
</html>
