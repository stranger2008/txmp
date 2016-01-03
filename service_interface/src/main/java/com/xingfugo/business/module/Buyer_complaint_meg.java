package com.xingfugo.business.module;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
/**
 * @function 功能 会员投诉留言信息实体
 * @author 创建人 陈晓艺
 * @date 创建日期 Tue Oct 14 18:04:48 CST 2014
 */
public class Buyer_complaint_meg implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String meg_id;
	public String getMeg_id() {
		return meg_id;
	}
	public void setMeg_id(String meg_id) {
		this.meg_id = meg_id;
	}
	
	private String com_id;
	public String getCom_id() {
		return com_id;
	}
	public void setCom_id(String com_id) {
		this.com_id = com_id;
	}
	@NotBlank
	@Length(max=500)
	private String content;
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	private String u_type;
	public String getU_type() {
		return u_type;
	}
	public void setU_type(String u_type) {
		this.u_type = u_type;
	}
	
	private Date in_date;
	public Date getIn_date() {
		return in_date;
	}
	public void setIn_date(Date in_date) {
		this.in_date = in_date;
	}
	

}

