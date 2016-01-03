package com.xingfugo.business.module;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
/**
 * @function 功能 资金提现实体
 * @author 创建人 陈显革
 * @date 创建日期 Thu Oct 16 16:18:00 CST 2014
 */
public class Fundwithdraw implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private Integer trade_id;
	
	private String order_no;
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	
	private String account_no;
	public String getAccount_no() {
		return account_no;
	}
	public void setAccount_no(String account_no) {
		this.account_no = account_no;
	}
	
	@NotNull
	@Min(0)
    @Digits(integer = 8, fraction = 2)
	private BigDecimal trans_amt;
	
	@NotBlank
	@Length(max=32)
	private String card_no;
	public String getCard_no() {
		return card_no;
	}
	public void setCard_no(String card_no) {
		this.card_no = card_no;
	}
	
	@NotBlank
	@Length(max=100)
	private String usr_name;
	public String getUsr_name() {
		return usr_name;
	}
	public void setUsr_name(String usr_name) {
		this.usr_name = usr_name;
	}
	
	@NotBlank
	@Length(max=50)
	private String open_bank;
	public String getOpen_bank() {
		return open_bank;
	}
	public void setOpen_bank(String open_bank) {
		this.open_bank = open_bank;
	}
	
	@Length(max=80)
	private String sub_bank;
	
	@NotBlank
	@Length(max=20)
	private String prov;
	public String getProv() {
		return prov;
	}
	public void setProv(String prov) {
		this.prov = prov;
	}
	
	@NotBlank
	@Length(max=40)
	private String city;
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	private Integer user_id;
	
	private String user_type;
	public String getUser_type() {
		return user_type;
	}
	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}
	
	private Date operate_date;
	
	private String status;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	private Integer audit_user_id;
	
	private Date finish_date;
	
	private String remark;
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getTrade_id() {
		return trade_id;
	}
	public void setTrade_id(Integer trade_id) {
		this.trade_id = trade_id;
	}
	public BigDecimal getTrans_amt() {
		return trans_amt;
	}
	public void setTrans_amt(BigDecimal trans_amt) {
		this.trans_amt = trans_amt;
	}
	public Date getOperate_date() {
		return operate_date;
	}
	public void setOperate_date(Date operate_date) {
		this.operate_date = operate_date;
	}
	public Date getFinish_date() {
		return finish_date;
	}
	public void setFinish_date(Date finish_date) {
		this.finish_date = finish_date;
	}
	public String getSub_bank() {
		return sub_bank;
	}
	public void setSub_bank(String sub_bank) {
		this.sub_bank = sub_bank;
	}
	
	private BigDecimal fund_use_num;
	public BigDecimal getFund_use_num() {
		return fund_use_num;
	}
	public void setFund_use_num(BigDecimal fund_use_num) {
		this.fund_use_num = fund_use_num;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public Integer getAudit_user_id() {
		return audit_user_id;
	}
	public void setAudit_user_id(Integer audit_user_id) {
		this.audit_user_id = audit_user_id;
	}
	

}

