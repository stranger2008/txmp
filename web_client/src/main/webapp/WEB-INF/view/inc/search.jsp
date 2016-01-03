<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<style>
<!--
.keyword {
	color:black;
}
-->
</style>
<div class="top w1200 clearfix">
			<div class="logo fl">
				<a href="<%=basePath %>"><img src="<%=basePath %>inc/images/logo.png">
				</a>
			</div>
			<div class="search fl">
				<div class="form">
					<input type="text" class="sear-txt" value="请输入关键词" id="keywords" />
					<input type="button" class="sear-btn" value="搜索" onclick="searchByKeywords();"/>
				</div>
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
				<a href="<%=basePath %>cartlist.action" >
				<div class="buycar">
					<b></b>购物车（<span id="buy_num"></span>）件
				</div>
				</a>
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
		<script type="text/javascript">
			
			$("#keywords").click(function(){
				var keywords=$("#keywords").val();
					if(keywords='请输入关键词'){
						$("#keywords").val('');
						$("#keywords").addClass("keyword");
					} 
				});
				
			$("#keywords").blur(function(){
				  	var keywords=$("#keywords").val();
				  	if(keywords.length<1){
				  		$("#keywords").val('请输入关键词');
				  		$("#keywords").removeClass("keyword");
				  	}
				});	
			
			function searchByKeywords(){
				var keyword = $("#keywords").val();
				if(keyword=='请输入关键词'){
					return;
				}
				window.location = "<%=basePath %>searchGoods.action?key="+keyword+"&rdm="+Math.random();
				
			};
			
			$(function(){
				var cartsize;
					$.ajax({
						type: 'get',
						url: 'cartlistsize.action',
						cache:false,
						success: function(data){
							$("#buy_num").text(data);
						}
					});
			   
			   var keyword = '${goodsQueryForm.key}'
			   if(keyword){
				$("#keywords").val(keyword);
			   }
			});
	       
   		 </script>
