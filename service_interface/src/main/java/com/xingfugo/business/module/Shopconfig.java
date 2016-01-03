package com.xingfugo.business.module;

import java.io.Serializable;
import java.sql.Timestamp;

import org.hibernate.validator.constraints.NotBlank;
/**
 * @function 功能 商店设置实体
 * @author 创建人 lll
 * @date 创建日期 Tue Feb 28 10:24:54 CST 2012
 */
public class Shopconfig implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private Integer shop_id;
	public Integer getShop_id() {
		return shop_id;
	}
	public void setShop_id(Integer shop_id) {
		this.shop_id = shop_id;
	}
	
	private Integer cust_id;
	public Integer getCust_id() {
		return cust_id;
	}
	public void setCust_id(Integer cust_id) {
		this.cust_id = cust_id;
	}
	
	@NotBlank
	private String shop_name;
	public String getShop_name() {
		return shop_name;
	}
	public void setShop_name(String shop_name) {
		this.shop_name = shop_name;
	}
	
	@NotBlank
	private String shop_logo;
	public String getShop_logo() {
		return shop_logo;
	}
	public void setShop_logo(String shop_logo) {
		this.shop_logo = shop_logo;
	}
	
	private String shop_site;
	public String getShop_site() {
		return shop_site;
	}
	public void setShop_site(String shop_site) {
		this.shop_site = shop_site;
	}
	
	@NotBlank
	private String shop_intro;
	public String getShop_intro() {
		return shop_intro;
	}
	public void setShop_intro(String shop_intro) {
		this.shop_intro = shop_intro;
	}
	
	@NotBlank
	private String busi_range;
	public String getBusi_range() {
		return busi_range;
	}
	public void setBusi_range(String busi_range) {
		this.busi_range = busi_range;
	}
	
	private String busi_mode;
	public String getBusi_mode() {
		return busi_mode;
	}
	public void setBusi_mode(String busi_mode) {
		this.busi_mode = busi_mode;
	}
	
	@NotBlank
	private String contant_man;
	public String getContant_man() {
		return contant_man;
	}
	public void setContant_man(String contant_man) {
		this.contant_man = contant_man;
	}
	
	private String email;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	private String qq;
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	
	private String msn;
	public String getMsn() {
		return msn;
	}
	public void setMsn(String msn) {
		this.msn = msn;
	}
	
	private String alliwang;
	public String getAlliwang() {
		return alliwang;
	}
	public void setAlliwang(String alliwang) {
		this.alliwang = alliwang;
	}
	
	private String mobile;
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	private String phone;
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	private String area_attr;
	public String getArea_attr() {
		return area_attr;
	}
	public void setArea_attr(String area_attr) {
		this.area_attr = area_attr;
	}
	
	private String area_name;
	public String getArea_name() {
		return area_name;
	}
	public void setArea_name(String area_name) {
		this.area_name = area_name;
	}
	
	private String address;
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	private String info_state;
	public String getInfo_state() {
		return info_state;
	}
	public void setInfo_state(String info_state) {
		this.info_state = info_state;
	}
	
	private String is_close;
	
	private String user_id;
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	private Timestamp in_date;
	public Timestamp getIn_date() {
		return in_date;
	}
	public void setIn_date(Timestamp in_date) {
		this.in_date = in_date;
	}
	

	private String banner_image;
	private String adv_image;

	public String getBanner_image() {
		return banner_image;
	}
	public void setBanner_image(String banner_image) {
		this.banner_image = banner_image;
	}
	public String getAdv_image() {
		return adv_image;
	}
	public void setAdv_image(String adv_image) {
		this.adv_image = adv_image;
	}
	
	private Integer goods_count;
	public Integer getGoods_count() {
		return goods_count;
	}
	public void setGoods_count(Integer goods_count) {
		this.goods_count = goods_count;
	}
	public String getIs_close() {
		return is_close;
	}
	public void setIs_close(String is_close) {
		this.is_close = is_close;
	}
	
	private String start_area;
	private String reach_area;
	private String ship_type;
	
	private String user_name;
	private String area_attr_name;
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getArea_attr_name() {
		return area_attr_name;
	}
	public void setArea_attr_name(String area_attr_name) {
		this.area_attr_name = area_attr_name;
	}
	public String getStart_area() {
		return start_area;
	}
	public void setStart_area(String start_area) {
		this.start_area = start_area;
	}
	public String getReach_area() {
		return reach_area;
	}
	public void setReach_area(String reach_area) {
		this.reach_area = reach_area;
	}
	public String getShip_type() {
		return ship_type;
	}
	public void setShip_type(String ship_type) {
		this.ship_type = ship_type;
	}
	
}

