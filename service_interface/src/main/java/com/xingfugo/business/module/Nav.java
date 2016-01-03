package com.xingfugo.business.module;

import java.io.Serializable;

import javax.validation.constraints.DecimalMin;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.URL;
/**
 * @function 功能 导航实体
 * @author 创建人 李良林
 * @date 创建日期 Thu Aug 28 13:10:44 CST 2014
 */
public class Nav implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String nav_id;
	
	@NotEmpty
	@Length(max=20)
	private String nav_name;
	public String getNav_name() {
		return nav_name;
	}
	public void setNav_name(String nav_name) {
		this.nav_name = nav_name;
	}
	
	private String isshow;
	public String getIsshow() {
		return isshow;
	}
	public void setIsshow(String isshow) {
		this.isshow = isshow;
	}
	
	@NotEmpty
	@DecimalMin(value="0")
	private String sort_no;
	public String getSort_no() {
		return sort_no;
	}
	public void setSort_no(String sort_no) {
		this.sort_no = sort_no;
	}
	
	private String isopen;
	public String getIsopen() {
		return isopen;
	}
	public void setIsopen(String isopen) {
		this.isopen = isopen;
	}
	
	@NotEmpty
	@Length(max=100)
	private String link_url;
	public String getLink_url() {
		return link_url;
	}
	public void setLink_url(String link_url) {
		this.link_url = link_url;
	}
	
	private String nav_post;
	public String getNav_post() {
		return nav_post;
	}
	public void setNav_post(String nav_post) {
		this.nav_post = nav_post;
	}
	
	private String nav_code;
	public String getNav_code() {
		return nav_code;
	}
	public void setNav_code(String nav_code) {
		this.nav_code = nav_code;
	}
	
	private String plat_type;
	public String getPlat_type() {
		return plat_type;
	}
	public void setPlat_type(String plat_type) {
		this.plat_type = plat_type;
	}
	public String getNav_id() {
		return nav_id;
	}
	public void setNav_id(String nav_id) {
		this.nav_id = nav_id;
	}
	

}

