package com.xingfugo.business.module;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
/**
 * @function 功能 投诉记录信息实体
 * @author 创建人 陈晓艺
 * @date 创建日期 Tue Oct 14 17:58:10 CST 2014
 */
public class Buyer_complaint implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String complaint_id;
	public String getComplaint_id() {
		return complaint_id;
	}
	public void setComplaint_id(String complaint_id) {
		this.complaint_id = complaint_id;
	}
	
	private String order_id;
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	
	private String user_id;
	
	private String cust_id;
	
	@NotBlank
	private String com_type;
	public String getCom_type() {
		return com_type;
	}
	public void setCom_type(String com_type) {
		this.com_type = com_type;
	}
	@NotBlank
	@Length(max=500)
	private String reason;
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	private String status;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	private Date in_date;
	public Date getIn_date() {
		return in_date;
	}
	public void setIn_date(Date in_date) {
		this.in_date = in_date;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getCust_id() {
		return cust_id;
	}
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	
	@Length(max=300)
	private String img_evidence;
	
	@Length(max=500)
	private String remark;
	
	public String getImg_evidence() {
		return img_evidence;
	}
	
	public void setImg_evidence(String img_evidence) {
		this.img_evidence = img_evidence;
	}
	
	public String getRemark() {
		return remark;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	
	
	

}

