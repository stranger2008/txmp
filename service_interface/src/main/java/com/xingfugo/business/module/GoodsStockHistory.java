package com.xingfugo.business.module;

import java.util.Date;

import javax.validation.constraints.NotNull;

public class GoodsStockHistory {
    private Integer id;

    @NotNull
    private Integer goodsId;

    @NotNull
    private Integer changeAmount;

    //库存变化类型：1，增加库存；0，减少库存
    @NotNull
    private Boolean changeType;

    private Integer beforeChange;

    private Integer afterChange;

    //变化原因：0，订单创建；1：订单取消；2：商品初始化；3：卖家新增；4：其他；
    private Short changeReason;

    private String changeDesc;

    private Date changeTime;

    private String orderNo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getChangeAmount() {
        return changeAmount;
    }

    public void setChangeAmount(Integer changeAmount) {
        this.changeAmount = changeAmount;
    }

    public Boolean getChangeType() {
        return changeType;
    }
    
    public String getChangeTypeDesc() {
    	return changeType ? "增加库存" : "减少库存";
    }

    public void setChangeType(Boolean changeType) {
        this.changeType = changeType;
    }

    public Integer getBeforeChange() {
        return beforeChange;
    }

    public void setBeforeChange(Integer beforeChange) {
        this.beforeChange = beforeChange;
    }

    public Integer getAfterChange() {
        return afterChange;
    }

    public void setAfterChange(Integer afterChange) {
        this.afterChange = afterChange;
    }

    public Short getChangeReason() {
        return changeReason;
    }
    
    //变化原因：0，订单创建；1：订单取消；2：商品初始化；3：卖家操作；4：其他；
    public String getChangeReasonDesc() {
    	switch (changeReason) {
		case 0:
			return "订单创建";
		case 1:
			return "订单取消";
		case 2:
			return "商品初始化";
		case 3:
			return "卖家操作";
		default:
			return "其他";
		}
    }

    public void setChangeReason(Short changeReason) {
        this.changeReason = changeReason;
    }

    public String getChangeDesc() {
        return changeDesc;
    }

    public void setChangeDesc(String changeDesc) {
        this.changeDesc = changeDesc;
    }

    public Date getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(Date changeTime) {
        this.changeTime = changeTime;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
}