package com.xingfugo.business.module;

import java.io.Serializable;

import javax.validation.constraints.DecimalMin;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
/**
 * @function 功能 商品品牌实体
 * @author 创建人 陈晓艺
 * @date 创建日期 Thu Sep 11 19:32:19 CST 2014
 */
public class Goodsbrand implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String brand_id;
	public String getBrand_id() {
		return brand_id;
	}
	public void setBrand_id(String brand_id) {
		this.brand_id = brand_id;
	}
	@NotBlank
	@Length(max=100)
	private String brand_name;
	public String getBrand_name() {
		return brand_name;
	}
	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}
	@Length(max=100)
	private String brand_site;
	public String getBrand_site() {
		return brand_site;
	}
	public void setBrand_site(String brand_site) {
		this.brand_site = brand_site;
	}
	
	private String logo;
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	@Length(max=1000)
	private String content;
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@NotBlank
	private String goods_attr;
	public String getGoods_attr() {
		return goods_attr;
	}
	public void setGoods_attr(String goods_attr) {
		this.goods_attr = goods_attr;
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
	
	private String info_state;
	public String getInfo_state() {
		return info_state;
	}
	public void setInfo_state(String info_state) {
		this.info_state = info_state;
	}
	
	private String is_recom;
	public String getIs_recom() {
		return is_recom;
	}
	public void setIs_recom(String is_recom) {
		this.is_recom = is_recom;
	}
	

}

