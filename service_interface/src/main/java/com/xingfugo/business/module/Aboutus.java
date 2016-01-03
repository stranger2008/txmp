package com.xingfugo.business.module;

import java.io.Serializable;
import java.sql.Timestamp;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
/**
 * @function 功能 文章管理实体
 * @author 创建人 刘香玲
 * @date 创建日期 Thu Sep 04 17:05:56 CST 2014
 */
public class Aboutus implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String info_id;
	public String getInfo_id() {
		return info_id;
	}
	public void setInfo_id(String info_id) {
		this.info_id = info_id;
	}
	/**
	 * 标题
	 */
	@NotBlank
	@Length(max=20)
	private String title;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 内容
	 */
	@Length(max=65535)
	private String content;
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 分类
	 */
	@NotBlank
	@Length(max=200)
	private String cat_attr;
	public String getCat_attr() {
		return cat_attr;
	}
	public void setCat_attr(String cat_attr) {
		this.cat_attr = cat_attr;
	}
	
	private Timestamp in_date;
	public Timestamp getIn_date() {
		return in_date;
	}
	public void setIn_date(Timestamp in_date) {
		this.in_date = in_date;
	}

	
	private String cat_attr_names;
	public String getCat_attr_names() {
		return cat_attr_names;
	}
	public void setCat_attr_names(String cat_attr_names) {
		this.cat_attr_names = cat_attr_names;
	}
	
}

