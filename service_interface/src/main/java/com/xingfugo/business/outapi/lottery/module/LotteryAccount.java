package com.xingfugo.business.outapi.lottery.module;

/**
 * 	彩票购买人信息
 * @author  wyl
 *
 */
public class LotteryAccount {
	
	/**
	 * 购买者标识
	 */
	private Integer account_id;
	/**
	 * 会员标识
	 */
	private Integer user_id;
	/**
	 * 证件类型
	 */
	private Integer certificatetype;
	
	/**
	 *用户姓名 
	 */
	private String fullName;
	/**
	 * 联系电话
	 */
	private String phonenum;
	/**
	 * 证件号码
	 */
	private String certificatenum;
	/**
	 * 投注卡号
	 */
	private String wagercardnum;
	/**
	 * 投注密码
	 */
	private String wagercardpwd;
	public Integer getAccount_id() {
		return account_id;
	}
	public void setAccount_id(Integer account_id) {
		this.account_id = account_id;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	
	public Integer getCertificatetype() {
		return certificatetype;
	}
	public void setCertificatetype(Integer certificatetype) {
		this.certificatetype = certificatetype;
	}
	public String getPhonenum() {
		return phonenum;
	}
	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}
	public String getCertificatenum() {
		return certificatenum;
	}
	public void setCertificatenum(String certificatenum) {
		this.certificatenum = certificatenum;
	}
	public String getWagercardnum() {
		return wagercardnum;
	}
	public void setWagercardnum(String wagercardnum) {
		this.wagercardnum = wagercardnum;
	}
	public String getWagercardpwd() {
		return wagercardpwd;
	}
	public void setWagercardpwd(String wagercardpwd) {
		this.wagercardpwd = wagercardpwd;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

}
