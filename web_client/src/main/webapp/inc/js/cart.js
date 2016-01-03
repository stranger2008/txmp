//天下名品购物车相关

$(function(){

//    使用新地址操作
    $('.newaddr-btn').bind('click', core.newAddressShow);

//    商品详情切换
    $('.goods-d-tab a').productTab({
        showObj: 'goods-d-show',
        parentClass: 'goods-d-tab',
        activeObj: 'a'
    });

//    评价切换
    $('.goods-d-type span').productTab({
        showObj: 'goods-d-detail',
        parentClass: 'goods-d-type',
        activeObj: 'span' 
    });

//    商品数量点击增加
    $('#product-num em').bind('click', core.productNum);
    $('.quantity-form a').bind('click', core.productNum);

//    商品详情点击显示大图
    $('.goods-imgs-list li').bind('click', core.productDetail);

//    订单信息提交流程
    //$('.address-lst input[type="radio"]').bind('click', core.orderAddress);
    $('.a-lan').bind('click', core.orderLnvoice);

//    使用代金券和使用积分
    $('.use-voucher em').bind('click', core.useVoucher);

})





















































































































