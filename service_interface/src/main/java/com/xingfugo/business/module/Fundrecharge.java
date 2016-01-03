package com.xingfugo.business.module;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
/**
 * @function 功能 会员资金充值表实体
 * @author 创建人 陈显革
 * @date 创建日期 Wed Oct 08 14:29:02 CST 2014
 */
public class Fundrecharge implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private Integer trade_id;
	public Integer getTrade_id() {
		return trade_id;
	}
	public void setTrade_id(Integer trade_id) {
		this.trade_id = trade_id;
	}
	
	private String order_no;
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	
	@NotNull
	@Min(0)
    @Digits(integer = 8, fraction = 2)
	private BigDecimal fund_num;
	public BigDecimal getFund_num() {
		return fund_num;
	}
	public void setFund_num(BigDecimal fund_num) {
		this.fund_num = fund_num;
	}
	
	@NotBlank
	private String account_no;
	public String getAccount_no() {
		return account_no;
	}
	public void setAccount_no(String account_no) {
		this.account_no = account_no;
	}
	
	private String is_enabled;
	public String getIs_enabled() {
		return is_enabled;
	}
	public void setIs_enabled(String is_enabled) {
		this.is_enabled = is_enabled;
	}
	
	private Date in_date;
	public Date getIn_date() {
		return in_date;
	}
	public void setIn_date(Date in_date) {
		this.in_date = in_date;
	}

}

