<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class="top w1200 clearfix">
    <div class="logo-shop fl">
        <a href="<%=basePath %>"><img src="<%=rootPath %>images/logo-shop.png"></a>
        <b><h3>rio锐澳鸡旗舰店</h3><a href="#" class="str-btn">品牌直销</a></b>
    </div>
    <div class="search fl">
    	<div class="form fl">
		<input type="text" class="sear-txt" value="请输入关键词"/><input type="button" class="sear-btn" value="搜全站" />
        </div>
        <div class="fl"><input type="button" class="sear-btn2" value="搜全站" /></div>
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
    	<a href="<%=basePath %>cartlist.action" >
    	<div class="buycar"><b></b>购物车（<span id="buy_num"></span>）件</div>
    	</a>
        <div class="safe">
            <div class="safe-cont"><b></b><ul><li>正品保证</li><li class="en">Guarantee</li></ul></div>
            <div class="safe-cont safe-cont2"><b></b><ul><li>无理由退换货</li><li class="en">Without Reason</li></ul></div>
        </div>
        
    </div>
</div>
<script type="text/javascript">
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
			   
			    });
	       
   		 </script>