package com.xingfugo.business.outapi.lottery.module;

import java.util.Date;

/**
 * @author wyl
 *	彩票订单
 */
public class LotteryOrder {
	//订单号
	private String order_no;
	//会员标识
	private Integer cust_id;
	//响应代码
	private String resultcode;
	//彩票序列号
	private String serialnum;
	//期号
	private String period;
	//游戏编号
	private String gameid;
	//内容
	private String merchantserialnum;
	//下单时间
	private Date single_date;
	//截止时间
	private Date end_date;
	//注数
	private String boards;
	//倍数
	private String betmultir;
	//投注金额
	private String totalamount;
	//投注号码
	private String wagerdata;
	//录入时间
	private Date in_date;
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	public Integer getCust_id() {
		return cust_id;
	}
	public void setCust_id(Integer cust_id) {
		this.cust_id = cust_id;
	}
	public String getResultcode() {
		return resultcode;
	}
	public void setResultcode(String resultcode) {
		this.resultcode = resultcode;
	}
	public String getSerialnum() {
		return serialnum;
	}
	public void setSerialnum(String serialnum) {
		this.serialnum = serialnum;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public String getGameid() {
		return gameid;
	}
	public void setGameid(String gameid) {
		this.gameid = gameid;
	}
	public String getMerchantserialnum() {
		return merchantserialnum;
	}
	public void setMerchantserialnum(String merchantserialnum) {
		this.merchantserialnum = merchantserialnum;
	}
	public Date getSingle_date() {
		return single_date;
	}
	public void setSingle_date(Date single_date) {
		this.single_date = single_date;
	}
	public Date getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	public String getBoards() {
		return boards;
	}
	public void setBoards(String boards) {
		this.boards = boards;
	}
	public String getBetmultir() {
		return betmultir;
	}
	public void setBetmultir(String betmultir) {
		this.betmultir = betmultir;
	}
	public String getTotalamount() {
		return totalamount;
	}
	public void setTotalamount(String totalamount) {
		this.totalamount = totalamount;
	}
	public String getWagerdata() {
		return wagerdata;
	}
	public void setWagerdata(String wagerdata) {
		this.wagerdata = wagerdata;
	}
	public Date getIn_date() {
		return in_date;
	}
	public void setIn_date(Date in_date) {
		this.in_date = in_date;
	}
	
	
}
