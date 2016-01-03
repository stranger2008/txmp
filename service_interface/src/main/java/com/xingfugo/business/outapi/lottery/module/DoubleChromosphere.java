package com.xingfugo.business.outapi.lottery.module;

/**
 * @author wyl
 *	双色球投注
 */
public class DoubleChromosphere {

	/**
	 * 红球选号方式，多注时必须一致
	 */
	private String redmethod;
	/**
	 * 蓝球选号方式，多注时必须一致
	 */
	private String bluemethod;
	/**
	 * 投注注数
	 */
	private String boards;
	/**
	 * 玩法编号，多注时必须一致
	 */
	private String bettype;
	/**
	 * 红球号码，升序排序
	 */
	private String redballs;
	/**
	 * 蓝球号码，升序排序,多注时蓝球个数必须一致
	 */
	private String blueballs;
	/**
	 * 投注倍数，多注时不用冒号分隔
	 */
	private String betmultir;
	/**
	 * 投注总金额单位：分
	 */
	private Integer totalamount;
	public String getRedmethod() {
		return redmethod;
	}
	public void setRedmethod(String redmethod) {
		this.redmethod = redmethod;
	}
	public String getBluemethod() {
		return bluemethod;
	}
	public void setBluemethod(String bluemethod) {
		this.bluemethod = bluemethod;
	}
	public String getBoards() {
		return boards;
	}
	public void setBoards(String boards) {
		this.boards = boards;
	}
	public String getBettype() {
		return bettype;
	}
	public void setBettype(String bettype) {
		this.bettype = bettype;
	}
	public String getRedballs() {
		return redballs;
	}
	public void setRedballs(String redballs) {
		this.redballs = redballs;
	}
	public String getBlueballs() {
		return blueballs;
	}
	public void setBlueballs(String blueballs) {
		this.blueballs = blueballs;
	}
	public String getBetmultir() {
		return betmultir;
	}
	public void setBetmultir(String betmultir) {
		this.betmultir = betmultir;
	}
	public Integer getTotalamount() {
		return totalamount;
	}
	public void setTotalamount(Integer totalamount) {
		this.totalamount = totalamount;
	}
	
}
