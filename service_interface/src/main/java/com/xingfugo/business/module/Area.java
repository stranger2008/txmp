package com.xingfugo.business.module;

import java.io.Serializable;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
/**
 * @function 功能 地区管理实体
 * @author 创建人 史倩倩
 * @date 创建日期 Tue Sep 02 14:10:46 CST 2014
 */
public class Area implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String area_id;
	
	public String getArea_id() {
		return area_id;
	}
	public void setArea_id(String area_id) {
		this.area_id = area_id;
	}
	
	@NotBlank
	@Length(max=20)
	private String area_name;
	public String getArea_name() {
		return area_name;
	}
	public void setArea_name(String area_name) {
		this.area_name = area_name;
	}
	
	@NotBlank
	@Length(max=50)
	private String en_name;
	public String getEn_name() {
		return en_name;
	}
	public void setEn_name(String en_name) {
		this.en_name = en_name;
	}
	
	@NotBlank
	@Length(max=10)
	private String up_area_id;
	public String getUp_area_id() {
		return up_area_id;
	}
	public void setUp_area_id(String up_area_id) {
		this.up_area_id = up_area_id;
	}
	
	@NotBlank
	@Length(max=1)
	private String area_level;
	public String getArea_level() {
		return area_level;
	}
	public void setArea_level(String area_level) {
		this.area_level = area_level;
	}
	
	@NotBlank
	@Pattern(regexp = "^(0|[1-9][0-9]*)$", message = "请输入0和正整数数字")
	@DecimalMax(value = "2147483647")
	private String sort_no;
	public String getSort_no() {
		return sort_no;
	}
	public void setSort_no(String sort_no) {
		this.sort_no = sort_no;
	}
	
	//@NotEmpty
	@Length(max=1)
	private String is_city;
	public String getIs_city() {
		return is_city;
	}
	public void setIs_city(String is_city) {
		this.is_city = is_city;
	}
	
	//@NotEmpty
	@Length(max=100)
	private String seotitle;
	public String getSeotitle() {
		return seotitle;
	}
	public void setSeotitle(String seotitle) {
		this.seotitle = seotitle;
	}
	
	//@NotEmpty
	@Length(max=100)
	private String seokeyword;
	public String getSeokeyword() {
		return seokeyword;
	}
	public void setSeokeyword(String seokeyword) {
		this.seokeyword = seokeyword;
	}
	
	//@NotEmpty
	@Length(max=200)
	private String seodesc;
	public String getSeodesc() {
		return seodesc;
	}
	public void setSeodesc(String seodesc) {
		this.seodesc = seodesc;
	}
	
	//@NotEmpty
	@Length(max=10)
	private String area_have;
	public String getArea_have() {
		return area_have;
	}
	public void setArea_have(String area_have) {
		this.area_have = area_have;
	}
	
	@Length(max=1)
	private String is_open;
	public String getIs_open() {
		return is_open;
	}
	public void setIs_open(String is_open) {
		this.is_open = is_open;
	}

	private String up_area_name;

	public String getUp_area_name() {
		return up_area_name;
	}
	public void setUp_area_name(String up_area_name) {
		this.up_area_name = up_area_name;
	}
	
}

