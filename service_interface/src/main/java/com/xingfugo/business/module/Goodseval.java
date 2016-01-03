package com.xingfugo.business.module;

import java.io.Serializable;
/**
 * @function 功能 商品评价实体
 * @author 创建人 史倩倩
 * @date 创建日期 Sat Oct 11 11:15:27 CST 2014
 */
public class Goodseval implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String trade_id;
	public String getTrade_id() {
		return trade_id;
	}
	public void setTrade_id(String trade_id) {
		this.trade_id = trade_id;
	}
	
	private String goods_id;
	public String getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(String goods_id) {
		this.goods_id = goods_id;
	}
	
	private String order_id;
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	
	private String buy_cust_id;
	public String getBuy_cust_id() {
		return buy_cust_id;
	}
	public void setBuy_cust_id(String buy_cust_id) {
		this.buy_cust_id = buy_cust_id;
	}
	
	private String sellert_id;
	public String getSellert_id() {
		return sellert_id;
	}
	public void setSellert_id(String sellert_id) {
		this.sellert_id = sellert_id;
	}
	
	private String g_eval;
	public String getG_eval() {
		return g_eval;
	}
	public void setG_eval(String g_eval) {
		this.g_eval = g_eval;
	}
	
	private String goods_score;
	public String getGoods_score() {
		return goods_score;
	}
	public void setGoods_score(String goods_score) {
		this.goods_score = goods_score;
	}
	
	private String g_comment;
	public String getG_comment() {
		return g_comment;
	}
	public void setG_comment(String g_comment) {
		this.g_comment = g_comment;
	}
	
	private String eval_date;
	public String getEval_date() {
		return eval_date;
	}
	public void setEval_date(String eval_date) {
		this.eval_date = eval_date;
	}
	
	private String reply_content;
	public String getReply_content() {
		return reply_content;
	}
	public void setReply_content(String reply_content) {
		this.reply_content = reply_content;
	}
	
	private String reply_date;
	public String getReply_date() {
		return reply_date;
	}
	public void setReply_date(String reply_date) {
		this.reply_date = reply_date;
	}
	
	private String good_num;
	public String getGood_num() {
		return good_num;
	}
	public void setGood_num(String good_num) {
		this.good_num = good_num;
	}
	

}

