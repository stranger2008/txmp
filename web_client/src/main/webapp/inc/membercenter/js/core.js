
var core={

//    使用新地址时弹出表单
    newAddressShow: function(){
        $(this).next().show();
        return false;
    },

//    弹出层
    showLayer: function(options){
//        默认配置
        var defaultOption={
            title: '提示',
            width:  500,
            height: 500,
            content: ''
        };
//        合并参数
        var options= $.extend(defaultOption, options);
        var markHtml='<div id="mark"></div> '
        $('body').append(markHtml);
        var domHeight=$(document).height();
        var winWidth=$(window).width();
        var winHeight=$(window).height();
        $('#mark').css({
            width: '100%',
            height: domHeight,
            background: '#000',
            position: 'absolute',
            left: 0,
            top: 0,
            zIndex: 99999,
            opacity: 0.8
        });
//        弹出层html
        var leftPx=(winWidth-options.width)/2;
        var topPx=(winHeight-options.height)/2;
        var larerHtml='<div id="layerBox" style="width: '+options.width+'px;height: '+options.height+'px;background: #fff;position:absolute;z-index:999999;left: '+leftPx+'px;top: '+topPx+'px">' +
            '<p class="layer-title"><strong>'+options.title+'</strong><a class="close-layer" title="关闭"></a></p>' +
            '<div class="larer-data">'+$('#'+options.content).html()+'</div> '+
            '</div>';
        $('body').append(larerHtml);
        $('.close-layer').bind('click', core.closeLayer);
    },
//    关闭层
    closeLayer: function(){
        $(this).parents('#layerBox').remove();
        $('#mark').remove();
    },
//    弹出层resize
    resizeLayer: function(){
        var layerBox=$('#layerBox');
        var layerLength=layerBox.length;
        if(layerLength>0){
            layerBox.css({
                left: ($(window).width()-layerBox.width())/2,
                top:  ($(window).height()-layerBox.height())/2
            })
        };
    },
//    购物车checkbox
    cartCheckbox: function(){

    },
//    商品详情点击显示大图
    productDetail: function(){
        var imgUrl=$(this).find('img').attr('src');
        $(this).parent().children().removeClass('active');
        $(this).addClass('active');
        $('.goodsdet-img').find('img').attr('src', imgUrl);

    },
//    商品数量点击增加
    productNum:function(){
        var inputObj=$(this).parent().find('input');
        var inputVal=inputObj.val();
        if($(this).text()=='-'){
            if(inputVal==1){
                inputObj.val(1);
            }else{
                inputObj.val(--inputVal);
            };
        }else{
            inputObj.val(++inputVal);
        };
        return false;
    },
//    订单信息提交流程
    orderAddress: function(){
        $(this).parents('.address').removeClass('active').next().addClass('active');
        $('#order-payment input[type="radio"]').bind('click', core.orderPayment);
    },
    orderPayment: function(){
        $(this).parents('.order-payment-type').find('div').removeClass('active');
        $(this).parent().addClass('active');
        $('#order-payment a').bind('click', core.orderPaymentSubmit);
    },
    orderPaymentSubmit: function(){
        var paymentBox=$(this).parents('.order-payment');
        paymentBox.find('p').html(paymentBox.find('input:checked').val()).show();
        paymentBox.find('.order-payment-type').hide();
        paymentBox.removeClass('active').next().addClass('active');
        $('.order-p-y-item a').bind('click', core.orderLnvoice);
    },
    orderLnvoice: function(){
        var _this=$(this);
        var lBox=_this.prev();
        _this.parent().next().show();
        var lName='';
        $('.order-p-t-bill input').click(function(){
            var billBox=$(this).parent().next();
            _this.prev().text();
            switch(parseInt($(this).val())){
                case 1:
                    lName='不开发票';
                    billBox.hide();
                    $(this).parent().next().next().next().hide();
                    billBox.next().hide();
                    break;
                case 2:
                    lName='普通发票';
                    $(this).parent().next().next().next().show();
                    billBox.hide();
                    billBox.next().show();
                    break;
                case 3:
                    $(this).parent().next().next().next().show();
                    lName='增值税发票';
                    billBox.show();
                    billBox.next().show();

                    break;
            };
            lBox.html('&nbsp;&nbsp;&nbsp;'+lName);
        });
        _this.parents('.order-payment-type').find('a').click(function(){
            $(this).parent().next().text(lName).show().prev().hide().parent().removeClass('active');
        })
        return false;
    },
//    使用代金券和使用积分
    useVoucher: function(){
        var thisNextObj=$(this).next();
        if(thisNextObj.is(':visible')){
            thisNextObj.hide();
            $(this).attr('style', '');
        }else{
            $(this).css('background-position','-782px -176px')
            thisNextObj.show();
        }
    }
}

//图片滚动
$.fn.imgRoll=function(options){
    var _this=$(this);
    var goodsPrev=_this.find(options.prevCls);
    var goodsNext=_this.find(options.nextCls);
    var imgBox=_this.find('ul');
    var liLength=imgBox.children().length;
    if(!liLength) return;
    var liWidth=imgBox.children().eq(0)[0].offsetWidth;
    var ulWidth=liWidth*liLength+(liLength*options.margin);
    var scrollCount=liLength-options.num;
    imgBox.css({
        width: ulWidth+'px',
        position: 'relative',
        left: '0'
    });
    var i=0;
    goodsPrev.click(function(){
        if(i==0){
            return;
        };
        --i;
        imgBox.animate(
            {
                left: '-'+(liWidth*i+options.margin*i)+'px'
            },400
        );
        return false;
    });
    goodsNext.click(function(){
        if(scrollCount==i){
            return;
        };
        i=scrollCount==i?scrollCount:++i;
        imgBox.animate(
            {
                left: '-'+(liWidth*i+options.margin*i)+'px'
            },400
        );
        return false;
    });
};

//商品详情切换
$.fn.productTab=function(options){
    $(this).click(function(){
        var parents=$(this).parents('.'+options.parentClass);
        var i=$(this).index();
        parents.children().removeClass('active');
        $('#'+options.showObj).children().hide().eq(i).show();
        parents.find(options.activeObj).eq(i).addClass('active');
    });
}

$(function(){


    $(window).resize(function(){
        core.resizeLayer();
    });
    $(window).scroll(function(){
        core.resizeLayer();
    });


//    订单提交成功后，弹出支付窗口
//    core.showLayer({
//        width: 450,
//        height: 275,
//        title: '支付',
//        content: 'payment'
//    })

//    我的购物车

//    商品详情页面图片滚动



//    进度查询弹出层
    $('.sche-query').click(function(){
        core.showLayer({
            width: 450,
            height: 270,
            title: '请输入您申请入驻时填写的信息',
            content: 'add-busi'
        });
    });


})





















































































































