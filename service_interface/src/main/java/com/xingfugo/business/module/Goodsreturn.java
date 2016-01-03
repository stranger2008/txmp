package com.xingfugo.business.module;

 

import java.math.BigDecimal;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;


public class Goodsreturn {
	private String trade_id;
	private String biz_type;
	private String order_id;
	private String goods_id;
	@Length(max=600)
	private String cont_desc;
	@Length(max=600)
	private String img_path;
	@Length(max=300)
	private String no_reason;
	private String info_state;
	private String user_id;
	private String in_date;
	/**
	 * 提交数量
	 */
	private Integer applyNum; 
	/**
	 * 提交凭证， 1 有发票 2 有检测报告
	 */
	@Length(max=3)
	private String applyProof; 
	/**
	 * 商品返回方式，1：上门取件 2：快递至天下名品
	 */
	@Length(max=1)
	private String returnType; 
	/**
	 * 退款金额
	 */
	@Digits(integer=8,fraction=2)
	private BigDecimal refundMoney;
	/**
	 * 退款备注
	 */
	@Length(max=300)
	private String refundRemark;
	@NotNull
	private GoodsReturnAddr goodsReturnAddr;
	
	public String getTrade_id() {
		return trade_id;
	}
	public void setTrade_id(String trade_id) {
		this.trade_id = trade_id;
	}
	public String getBiz_type() {
		return biz_type;
	}
	public void setBiz_type(String biz_type) {
		this.biz_type = biz_type;
	}
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public String getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(String goods_id) {
		this.goods_id = goods_id;
	}
	public String getCont_desc() {
		return cont_desc;
	}
	public void setCont_desc(String cont_desc) {
		this.cont_desc = cont_desc;
	}
	public String getImg_path() {
		return img_path;
	}
	public void setImg_path(String img_path) {
		this.img_path = img_path;
	}
	public String getNo_reason() {
		return no_reason;
	}
	public void setNo_reason(String no_reason) {
		this.no_reason = no_reason;
	}
	public String getInfo_state() {
		return info_state;
	}
	public void setInfo_state(String info_state) {
		this.info_state = info_state;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getIn_date() {
		return in_date;
	}
	public void setIn_date(String in_date) {
		this.in_date = in_date;
	}
	public Integer getApplyNum() {
		return applyNum;
	}
	public void setApplyNum(Integer applyNum) {
		this.applyNum = applyNum;
	}
	public String getApplyProof() {
		return applyProof;
	}
	public void setApplyProof(String applyProof) {
		this.applyProof = applyProof;
	}
	public String getReturnType() {
		return returnType;
	}
	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}
	public BigDecimal getRefundMoney() {
		return refundMoney;
	}
	public void setRefundMoney(BigDecimal refundMoney) {
		this.refundMoney = refundMoney;
	}
	public String getRefundRemark() {
		return refundRemark;
	}
	public void setRefundRemark(String refundRemark) {
		this.refundRemark = refundRemark;
	}
	public GoodsReturnAddr getGoodsReturnAddr() {
		return goodsReturnAddr;
	}
	public void setGoodsReturnAddr(GoodsReturnAddr goodsReturnAddr) {
		this.goodsReturnAddr = goodsReturnAddr;
	}
	
}
