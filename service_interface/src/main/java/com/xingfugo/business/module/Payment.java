package com.xingfugo.business.module;

import java.io.Serializable;

import javax.validation.constraints.DecimalMin;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
/**
 * @function 功能 支付方式实体
 * @author 创建人 陈晓艺
 * @date 创建日期 Thu Sep 11 10:13:43 CST 2014
 */
public class Payment implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String pay_id;
	public String getPay_id() {
		return pay_id;
	}
	public void setPay_id(String pay_id) {
		this.pay_id = pay_id;
	}
	
	@NotBlank
	@Length(max=30)
	private String pay_code;
	public String getPay_code() {
		return pay_code;
	}
	public void setPay_code(String pay_code) {
		this.pay_code = pay_code;
	}
	
	@NotBlank
	@Length(max=30)
	private String pay_name;
	public String getPay_name() {
		return pay_name;
	}
	public void setPay_name(String pay_name) {
		this.pay_name = pay_name;
	}
	@Length(max=1000)
	private String pay_desc;
	public String getPay_desc() {
		return pay_desc;
	}
	public void setPay_desc(String pay_desc) {
		this.pay_desc = pay_desc;
	}
	
	@NotBlank
	private String enabled;
	public String getEnabled() {
		return enabled;
	}
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	@DecimalMin(value="0")
	@Length(max=5)
	private String sort_no;
	public String getSort_no() {
		return sort_no;
	}
	public void setSort_no(String sort_no) {
		this.sort_no = sort_no;
	}
	
	private String client_attr;
	public String getClient_attr() {
		return client_attr;
	}
	public void setClient_attr(String client_attr) {
		this.client_attr = client_attr;
	}
	

}

