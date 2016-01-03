package com.xingfugo.business.outapi.airline.module;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class WSPolicyData {
 	/**
	 * 政策ID
	 */
 	@XStreamAlias("policyId")
	private Integer policyId;
	 /**
	 * 政策返点
	 */
 	@XStreamAlias("commisionPoint")
	private String commisionPoint;
	 /**
	 * 返现
	 */
 	@XStreamAlias("commisionMoney")
	private Double commisionMoney;
	 /**
	 * 是否更换PNR
	 */
 	@XStreamAlias("needSwitchPNR")
	private Integer needSwitchPNR;
	 /**
	 * 政策类型
	 */
 	@XStreamAlias("policyType")
	private String policyType;
	 /**
	 * 政策类型
	 */
 	@XStreamAlias("seatType")
	private Integer seatType;
	 /**
	 * 供应商工作时间
	 */
 	@XStreamAlias("workTime")
	private String workTime;
	 /**
	 * 废票时间
	 */
 	@XStreamAlias("vtWorkTime")
	private String vtWorkTime;
	 /**
	 * 政策归属
	 */
 	@XStreamAlias("policyBelongTo")
	private Integer policyBelongTo;
	 /**
	 * 是否特殊政策
	 */
 	@XStreamAlias("commisionType")
	private Integer commisionType;
	 /**
	 * 政策备注
	 */
 	@XStreamAlias("comment")
	private String comment;
	 /**
	 * 备用参数
	 */
 	@XStreamAlias("param1")
	private String param1;
	 /**
	 * 备用参数
	 */
 	@XStreamAlias("param2")
	private String param2;
	 /**
	 * 备用参数
	 */
 	@XStreamAlias("param3")
	private String param3;
	 /**
	 * 备用参数
	 */
 	@XStreamAlias("param4")
	private String param4;
	public Integer getPolicyId() {
		return policyId;
	}
	public void setPolicyId(Integer policyId) {
		this.policyId = policyId;
	}
	public String getCommisionPoint() {
		return commisionPoint;
	}
	public void setCommisionPoint(String commisionPoint) {
		this.commisionPoint = commisionPoint;
	}
	public Double getCommisionMoney() {
		return commisionMoney;
	}
	public void setCommisionMoney(Double commisionMoney) {
		this.commisionMoney = commisionMoney;
	}
	public Integer getNeedSwitchPNR() {
		return needSwitchPNR;
	}
	public void setNeedSwitchPNR(Integer needSwitchPNR) {
		this.needSwitchPNR = needSwitchPNR;
	}
	public String getPolicyType() {
		return policyType;
	}
	public void setPolicyType(String policyType) {
		this.policyType = policyType;
	}
	public Integer getSeatType() {
		return seatType;
	}
	public void setSeatType(Integer seatType) {
		this.seatType = seatType;
	}
	public String getWorkTime() {
		return workTime;
	}
	public void setWorkTime(String workTime) {
		this.workTime = workTime;
	}
	public String getVtWorkTime() {
		return vtWorkTime;
	}
	public void setVtWorkTime(String vtWorkTime) {
		this.vtWorkTime = vtWorkTime;
	}
	public Integer getPolicyBelongTo() {
		return policyBelongTo;
	}
	public void setPolicyBelongTo(Integer policyBelongTo) {
		this.policyBelongTo = policyBelongTo;
	}
	public Integer getCommisionType() {
		return commisionType;
	}
	public void setCommisionType(Integer commisionType) {
		this.commisionType = commisionType;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getParam1() {
		return param1;
	}
	public void setParam1(String param1) {
		this.param1 = param1;
	}
	public String getParam2() {
		return param2;
	}
	public void setParam2(String param2) {
		this.param2 = param2;
	}
	public String getParam3() {
		return param3;
	}
	public void setParam3(String param3) {
		this.param3 = param3;
	}
	public String getParam4() {
		return param4;
	}
	public void setParam4(String param4) {
		this.param4 = param4;
	}
 

}
