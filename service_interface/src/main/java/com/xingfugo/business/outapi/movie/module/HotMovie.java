 package com.xingfugo.business.outapi.movie.module;

import com.thoughtworks.xstream.annotations.XStreamAlias;
/**
 * 热映影片
 */
public class HotMovie extends MovieInfo {
	//卖出数量,默认0
	@XStreamAlias("SaleAmount")
	private int saleAmount;
	
	//选座票最低价,精确到小数点后2位
	@XStreamAlias("SalePrice")
	private String salePrice;
	
	//通兑票最低价,精确到小数点后2位
	@XStreamAlias("TicketPrice")
	private String ticketPrice;
	
	//影片图片,规格：314×138 
	@XStreamAlias("HengPic")
	private String hengPic;
	
	//影片图片,规格：720x1280
	@XStreamAlias("GaoqingPic")
	private String gaoqingPic;

	public int getSaleAmount() {
		return saleAmount;
	}

	public void setSaleAmount(int saleAmount) {
		this.saleAmount = saleAmount;
	}

	public String getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(String salePrice) {
		this.salePrice = salePrice;
	}

	public String getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(String ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	public String getHengPic() {
		return hengPic;
	}

	public void setHengPic(String hengPic) {
		this.hengPic = hengPic;
	}

	public String getGaoqingPic() {
		return gaoqingPic;
	}

	public void setGaoqingPic(String gaoqingPic) {
		this.gaoqingPic = gaoqingPic;
	}
}
