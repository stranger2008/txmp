//    我的订单展开下拉
var account={

    orderOpen: function(){
        var obj=$(this).find('div');
        var display=obj.is(':hidden');
        if(display){
            obj.prev().addClass('my-order-open');
            obj.show();
        }else{
            obj.prev().removeClass('my-order-open');
            obj.hide();
        }
    },
//    订单评价
    orderAssess: function(){
        var orderAssess=$('#orderassess');
        var thisOrderAssexxObj=$(this).parents('tr').next();
        if(!thisOrderAssexxObj.is(':hidden')){
            thisOrderAssexxObj.hide();
            return false;
        }
        orderAssess.hide();
        thisOrderAssexxObj.show();
        $('.agreement-btn-y').bind('click', account.orderAssessClose);
        return false;
    },
//    订单评价关闭
    orderAssessClose: function(){
        $(this).parents('#orderassess').hide();
    },
//    评价星级选择
    orderStar: function(){
        $(this).removeClass('serve-l-ash').prevAll().removeClass('serve-l-ash');
        $(this).nextAll().addClass('serve-l-ash');
    }
}


$(function(){

    $('.goods-d-level span').bind('click', account.orderStar);


//    订单展开
    $('.my-order-box').bind('mouseenter mouseleave', account.orderOpen);

//    收藏的商品和店铺切换
    $('.store-tab a').productTab({
        showObj: 'store-show',
        parentClass: 'store-tab',
        activeObj: 'a'
    });

//    猜你喜欢滚动  ps:统一去掉
//    $('#imgRoll1').imgRoll({
//        num: 6,
//        margin: 30,
//        prevCls: '.shop-like-prev',
//        nextCls: '.shop-like-next'
//    });

//    新增收货地址
    $('.add-site a').click(function(){
        core.showLayer({
            width: 760,
            height: 460,
            title: '新增收货地址',
            content: 'add-address'
        });
    });

//    我的订单发表评价
    $('.check-ord').bind('click', account.orderAssess);

})