package com.xingfugo.business.module;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
/**
 * @function 功能 实体分类实体
 * @author 创建人 刘香玲
 * @date 创建日期 Tue Sep 02 14:07:15 CST 2014
 */
public class Category implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 分类标识
	 */
	private String cat_id;
	public String getCat_id() {
		return cat_id;
	}
	public void setCat_id(String cat_id) {
		this.cat_id = cat_id;
	}
	/**
	 * 分类名称
	 */
	@NotBlank
	@Length(max=50)
	private String cat_name;
	public String getCat_name() {
		return cat_name;
	}
	public void setCat_name(String cat_name) {
		this.cat_name = cat_name;
	}
	/**
	 * 英文名
	 */
	@NotBlank
	@Length(max=50)
	private String en_name;
	public String getEn_name() {
		return en_name;
	}
	public void setEn_name(String en_name) {
		this.en_name = en_name;
	}
	/**
	 * 字母索引
	 */
	@NotBlank
	@Length(max=1)
	private String word_index;
	public String getWord_index() {
		return word_index;
	}
	public void setWord_index(String word_index) {
		this.word_index = word_index;
	}
	/**
	 * 上级分类
	 */
	@NotBlank
	@Length(max=10)
	private String up_cat_id;
	public String getUp_cat_id() {
		return up_cat_id;
	}
	public void setUp_cat_id(String up_cat_id) {
		this.up_cat_id = up_cat_id;
	}
	/**
	 * 分类级别
	 */
	@NotBlank
	@Length(max=1)
	private String cat_level;
	public String getCat_level() {
		return cat_level;
	}
	public void setCat_level(String cat_level) {
		this.cat_level = cat_level;
	}
	/**
	 * 所属模块
	 */
	@NotBlank
	@Length(max=10)
	private String module_type;
	public String getModule_type() {
		return module_type;
	}
	public void setModule_type(String module_type) {
		this.module_type = module_type;
	}
	/**
	 * 是否显示
	 */
	@NotBlank
	@Length(max=1)
	private String is_display;
	public String getIs_display() {
		return is_display;
	}
	public void setIs_display(String is_display) {
		this.is_display = is_display;
	}
	/**
	 * 排序
	 */
	@NotBlank
	@Pattern(regexp = "^(0|[1-9][0-9]*)$", message = "请输入0和正整数数字") 
	@Max(value = 2147483647, message = "必须小于或等于2147483647")
	//@Min(value=0)
	private String sort_no;
	public String getSort_no() {
		return sort_no;
	}
	public void setSort_no(String sort_no) {
		this.sort_no = sort_no;
	}
	/**
	 * 是否允许会员发布信息
	 */
	@NotBlank
	@Length(max=1)
	private String member_add;
	public String getMember_add() {
		return member_add;
	}
	public void setMember_add(String member_add) {
		this.member_add = member_add;
	}
	/**
	 * seo标题
	 */
	@Length(max=100)
	private String seotitle;
	public String getSeotitle() {
		return seotitle;
	}
	public void setSeotitle(String seotitle) {
		this.seotitle = seotitle;
	}
	/**
	 * seo关键字
	 */
	@Length(max=100)
	private String seokeyword;
	public String getSeokeyword() {
		return seokeyword;
	}
	public void setSeokeyword(String seokeyword) {
		this.seokeyword = seokeyword;
	}
	/**
	 * seo描述
	 */
	@Length(max=200)
	private String seodesc;
	public String getSeodesc() {
		return seodesc;
	}
	public void setSeodesc(String seodesc) {
		this.seodesc = seodesc;
	}
	/**
	 * 会员类型
	 */
	@Length(max=1)
	private String mem_type;
	public String getMem_type() {
		return mem_type;
	}
	public void setMem_type(String mem_type) {
		this.mem_type = mem_type;
	}
	/**
	 * 分类描述
	 */
	@Length(max=65535)
	private String cat_intr;
	public String getCat_intr() {
		return cat_intr;
	}
	public void setCat_intr(String cat_intr) {
		this.cat_intr = cat_intr;
	}
	/**
	 * 分类简介
	 */
	@Length(max=100)
	private String cat_simple;
	public String getCat_simple() {
		return cat_simple;
	}
	public void setCat_simple(String cat_simple) {
		this.cat_simple = cat_simple;
	}

}

