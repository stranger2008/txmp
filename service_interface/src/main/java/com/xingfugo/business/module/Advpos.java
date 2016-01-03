package com.xingfugo.business.module;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Map;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
/**
 * @function 功能 广告管理实体
 * @author 创建人 刘香玲
 * @date 创建日期 Wed Sep 10 16:25:07 CST 2014
 */
public class Advpos implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String pos_id;
	public String getPos_id() {
		return pos_id;
	}
	public void setPos_id(String pos_id) {
		this.pos_id = pos_id;
	}
	/**
	 * 广告位名称
	 */
	@NotBlank
	@Length(max=50)
	private String pos_name;
	public String getPos_name() {
		return pos_name;
	}
	public void setPos_name(String pos_name) {
		this.pos_name = pos_name;
	}
	/**
	 * 所属分类
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
	/**
	 * 所属分类的名称
	 */
	private String cat_attr_names;
	
	/**
	 * 广告位介绍
	 */
	@Length(max=200)
	private String pos_desc;
	public String getPos_desc() {
		return pos_desc;
	}
	public void setPos_desc(String pos_desc) {
		this.pos_desc = pos_desc;
	}
	/**
	 * 广告位类型
	 */
	@NotBlank
	@Length(max=1)
	private String pos_type;
	public String getPos_type() {
		return pos_type;
	}
	public void setPos_type(String pos_type) {
		this.pos_type = pos_type;
	}
	/**
	 * 类型名称
	 */
	private String pos_type_name;
	/**
	 * 宽
	 */
	//@Range(min=0, max=2147483647 ,message = "宽度值应该在0和2147483647之间")
	@Pattern(regexp = "^(0|[1-9][0-9]*)$", message = "请输入0和正整数数字")
	@Max(value = 2147483647, message = "必须小于或等于2147483647")
	private String p_width;
	public String getP_width() {
		return p_width;
	}
	public void setP_width(String p_width) {
		this.p_width = p_width;
	}
	/**
	 * 高
	 */
//	@Range(min=0, max=2147483647 ,message = "高度值应该在0和2147483647之间")
	@Pattern(regexp = "^(0|[1-9][0-9]*)$", message = "请输入0和正整数数字")
	@Max(value = 2147483647, message = "必须小于或等于2147483647")
	private String p_height;
	public String getP_height() {
		return p_height;
	}
	public void setP_height(String p_height) {
		this.p_height = p_height;
	}
	
	/**
	 * 示意图
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
	 * 发布时间
	 */
	private Timestamp in_date;
	public Timestamp getIn_date() {
		return in_date;
	}
	public void setIn_date(Timestamp in_date) {
		this.in_date = in_date;
	}
	public String getCat_attr_names() {
		return cat_attr_names;
	}
	public void setCat_attr_names(String cat_attr_names) {
		this.cat_attr_names = cat_attr_names;
	}
	public String getPos_type_name() {
		return pos_type_name;
	}
	public void setPos_type_name(String pos_type_name) {
		this.pos_type_name = pos_type_name;
	}
	
	
}

