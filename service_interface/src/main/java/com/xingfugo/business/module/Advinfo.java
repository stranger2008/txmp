package com.xingfugo.business.module;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
/**
 * @function 功能 广告信息管理实体
 * @author 创建人 刘香玲
 * @date 创建日期 Wed Sep 10 16:29:59 CST 2014
 */
public class Advinfo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String adv_id;
	public String getAdv_id() {
		return adv_id;
	}
	public void setAdv_id(String adv_id) {
		this.adv_id = adv_id;
	}
	/**
	 * 广告位标识
	 */
	@NotBlank
	private String pos_id;
	public String getPos_id() {
		return pos_id;
	}
	public void setPos_id(String pos_id) {
		this.pos_id = pos_id;
	}
	/**
	 * 广告名称
	 */
	@NotBlank
	@Length(max=100)
	private String adv_name;
	public String getAdv_name() {
		return adv_name;
	}
	public void setAdv_name(String adv_name) {
		this.adv_name = adv_name;
	}
	/**
	 * 广告介绍
	 */
	@Length(max=200)
	private String adv_desc;
	public String getAdv_desc() {
		return adv_desc;
	}
	public void setAdv_desc(String adv_desc) {
		this.adv_desc = adv_desc;
	}
	/**
	 * 广告代码
	 */
	@Length(max=65535)
	private String adv_code;
	public String getAdv_code() {
		return adv_code;
	}
	public void setAdv_code(String adv_code) {
		this.adv_code = adv_code;
	}
	/**
	 * 图片
	 */
	@Length(max=100)
	private String img_path;
	public String getImg_path() {
		return img_path;
	}
	public void setImg_path(String img_path) {
		this.img_path = img_path;
	}
	/**
	 * flash地址
	 */
	@Length(max=100)
	private String flash_url;
	public String getFlash_url() {
		return flash_url;
	}
	public void setFlash_url(String flash_url) {
		this.flash_url = flash_url;
	}
	/**
	 * 链接地址
	 */
	@Length(max=100)
	private String link_url;
	public String getLink_url() {
		return link_url;
	}
	public void setLink_url(String link_url) {
		this.link_url = link_url;
	}
	/**
	 * 显示标题
	 */
	@Length(max=100)
	private String title;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 显示描述
	 */
	@Length(max=200)
	private String content;
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 开始时间
	 */
	@NotNull
	@DateTimeFormat(pattern="yyyy-MM-dd")  
	private Date start_date;
	public Date getStart_date() {
		return start_date;
	}
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	/**
	 * 结束时间
	 */
	@NotNull
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date end_date;
	public Date getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	/**
	 * 备注
	 */
	@Length(max=200)
	private String remark;
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 发布时间
	 */
	private Timestamp in_date;
	public Timestamp getIn_date() {
		return in_date;
	}
	public void setIn_date(Timestamp in_date) {
		this.in_date = in_date;
	}
	 
}

