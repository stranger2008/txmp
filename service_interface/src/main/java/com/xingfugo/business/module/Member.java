package com.xingfugo.business.module;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;
/**
 * @function 功能 商家信息实体
 * @author 创建人 陈显革
 * @date 创建日期 Sat Sep 20 14:01:11 CST 2014
 */
public class Member implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String cust_id;
	public String getCust_id() {
		return cust_id;
	}
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	
	@NotBlank
	@Length(max=50)
	private String user_name;
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	
	private String passwd;
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	
	//联系人
	@NotBlank
	@Length(max=50)
	private String contact_name;
	public String getContact_name() {
		return contact_name;
	}
	public void setContact_name(String contact_name) {
		this.contact_name = contact_name;
	}
	
	//联系人电话
	@NotBlank
	@Length(max=50)
	private String contact_phone;
	public String getContact_phone() {
		return contact_phone;
	}
	public void setContact_phone(String contact_phone) {
		this.contact_phone = contact_phone;
	}
	
	//联系邮件
	@NotBlank
	@Length(max=100)
	@Email
	private String contact_email;
	public String getContact_email() {
		return contact_email;
	}
	public void setContact_email(String contact_email) {
		this.contact_email = contact_email;
	}
	
	//商家公司名称
	@NotBlank
	@Length(max=100)
	private String cust_name;
	public String getCust_name() {
		return cust_name;
	}
	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}
	
	//商家公司logo
	@Length(max=100)
	private String logo_img;
	public String getLogo_img() {
		return logo_img;
	}
	public void setLogo_img(String logo_img) {
		this.logo_img = logo_img;
	}
	
	//商家公司地址
	@NotBlank
	@Length(max=100)
	private String area_attr;
	public String getArea_attr() {
		return area_attr;
	}
	public void setArea_attr(String area_attr) {
		this.area_attr = area_attr;
	}
	
	//商家公司详细地址
	@NotBlank
	@Length(max=200)
	private String address;
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	//商家公司电话
	@NotBlank
	@Length(max=50)
	private String phone;
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	//注册资金
	@NotBlank
	@Length(max=20)
	@Range(min=1, max=999999999999999999L)
	private String reg_money;
	public String getReg_money() {
		return reg_money;
	}
	public void setReg_money(String reg_money) {
		this.reg_money = reg_money;
	}
	
	//注册币种
	@NotBlank
	@Length(max=20)
	private String reg_money_type;
	public String getReg_money_type() {
		return reg_money_type;
	}
	public void setReg_money_type(String reg_money_type) {
		this.reg_money_type = reg_money_type;
	}
	
	//营业执照号码
	@NotBlank
	@Length(max=100)
	private String lic_no;
	public String getLic_no() {
		return lic_no;
	}
	public void setLic_no(String lic_no) {
		this.lic_no = lic_no;
	}
	
	//营业执照所在地
	@NotBlank
	@Length(max=200)
	private String lic_address;
	public String getLic_address() {
		return lic_address;
	}
	public void setLic_address(String lic_address) {
		this.lic_address = lic_address;
	}
	
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date lic_start_time;
	
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date lic_end_time;
	
	@NotBlank
	@Length(max=100)
	private String lic_img;
	public String getLic_img() {
		return lic_img;
	}
	public void setLic_img(String lic_img) {
		this.lic_img = lic_img;
	}
	
	@NotBlank
	@Length(max=200)
	private String product;
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	
	@NotBlank
	@Length(max=100)
	private String person_id_img;
	public String getPerson_id_img() {
		return person_id_img;
	}
	public void setPerson_id_img(String person_id_img) {
		this.person_id_img = person_id_img;
	}
	
	@NotBlank
	@Length(max=100)
	private String org_img;
	public String getOrg_img() {
		return org_img;
	}
	public void setOrg_img(String org_img) {
		this.org_img = org_img;
	}
	
	@NotBlank
	@Length(max=100)
	private String tax_img;
	public String getTax_img() {
		return tax_img;
	}
	public void setTax_img(String tax_img) {
		this.tax_img = tax_img;
	}
	
	@NotBlank
	@Length(max=50)
	@Email
	private String email;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@NotBlank
	@Length(max=100)
	private String bank_img;
	public String getBank_img() {
		return bank_img;
	}
	public void setBank_img(String bank_img) {
		this.bank_img = bank_img;
	}
	
	@NotBlank
	@Length(max=100)
	private String bank_name;
	public String getBank_name() {
		return bank_name;
	}
	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}
	
	@NotBlank
	@Length(max=100)
	private String bank_id;
	public String getBank_id() {
		return bank_id;
	}
	public void setBank_id(String bank_id) {
		this.bank_id = bank_id;
	}
	
	private Date apply_time;
	
	private String user_id;
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	private String audit_status;
	public String getAudit_status() {
		return audit_status;
	}
	public void setAudit_status(String audit_status) {
		this.audit_status = audit_status;
	}
	
	private String no_reason;
	public String getNo_reason() {
		return no_reason;
	}
	public void setNo_reason(String no_reason) {
		this.no_reason = no_reason;
	}
	
	private Date audit_time;
	//所属地区名称--扩展属性
	private String area_attr_name;
	//审核人用户名
	private String audit_user_name;
	public String getArea_attr_name() {
		return area_attr_name;
	}
	public void setArea_attr_name(String area_attr_name) {
		this.area_attr_name = area_attr_name;
	}
	public String getAudit_user_name() {
		return audit_user_name;
	}
	public void setAudit_user_name(String audit_user_name) {
		this.audit_user_name = audit_user_name;
	}
	public Date getLic_start_time() {
		return lic_start_time;
	}
	public void setLic_start_time(Date lic_start_time) {
		this.lic_start_time = lic_start_time;
	}
	public Date getLic_end_time() {
		return lic_end_time;
	}
	public void setLic_end_time(Date lic_end_time) {
		this.lic_end_time = lic_end_time;
	}
	public Date getApply_time() {
		return apply_time;
	}
	public void setApply_time(Date apply_time) {
		this.apply_time = apply_time;
	}
	public Date getAudit_time() {
		return audit_time;
	}
	public void setAudit_time(Date audit_time) {
		this.audit_time = audit_time;
	}

}

