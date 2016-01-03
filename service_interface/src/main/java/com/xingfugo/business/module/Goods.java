/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.model
 * FileName: Goods.java 
 */
package com.xingfugo.business.module;

import java.io.Serializable;
import java.sql.Timestamp;

import org.hibernate.validator.constraints.Length;
/**
 * @function 功能 商品表实体
 * @author 创建人 李良林
 * @date 创建日期 Mon Feb 27 11:28:48 CST 2012
 */
public class Goods implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private Integer goods_id;
	public Integer getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(Integer goods_id) {
		this.goods_id = goods_id;
	}
	
	private String cust_id;
	public String getCust_id() {
		return cust_id;
	}
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	
	private String cat_attr;
	public String getCat_attr() {
		return cat_attr;
	}
	public void setCat_attr(String cat_attr) {
		this.cat_attr = cat_attr;
	}
	
	private String size_attr;
	public String getSize_attr() {
		return size_attr;
	}
	public void setSize_attr(String size_attr) {
		this.size_attr = size_attr;
	}
	@Length(max=100)
	private String goods_name;
	public String getGoods_name() {
		return goods_name;
	}
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	
	private String goods_no;
	public String getGoods_no() {
		return goods_no;
	}
	public void setGoods_no(String goods_no) {
		this.goods_no = goods_no;
	}
	@Length(max=100)
	private String goods_wd;
	public String getGoods_wd() {
		return goods_wd;
	}
	public void setGoods_wd(String goods_wd) {
		this.goods_wd = goods_wd;
	}
	
	private String brand_id;
	public String getBrand_id() {
		return brand_id;
	}
	public void setBrand_id(String brand_id) {
		this.brand_id = brand_id;
	}
	@Length(max=200)
	private String goods_desc;
	public String getGoods_desc() {
		return goods_desc;
	}
	public void setGoods_desc(String goods_desc) {
		this.goods_desc = goods_desc;
	}
	
	private String img_path;

	
	private String goods_video;
	public String getGoods_video() {
		return goods_video;
	}
	public void setGoods_video(String goods_video) {
		this.goods_video = goods_video;
	}
	@Length(max=30000)
	private String goods_detail;
	public String getGoods_detail() {
		return goods_detail;
	}
	public void setGoods_detail(String goods_detail) {
		this.goods_detail = goods_detail;
	}
	
	private String self_cat;
	public String getSelf_cat() {
		return self_cat;
	}
	public void setSelf_cat(String self_cat) {
		this.self_cat = self_cat;
	}
	
	private Timestamp up_date;
	public Timestamp getUp_date() {
		return up_date;
	}
	public void setUp_date(Timestamp up_date) {
		this.up_date = up_date;
	}
	
	private Timestamp down_date;
	public Timestamp getDown_date() {
		return down_date;
	}
	public void setDown_date(Timestamp down_date) {
		this.down_date = down_date;
	}
	
	private String market_price;
	public String getMarket_price() {
		return market_price;
	}
	public void setMarket_price(String market_price) {
		this.market_price = market_price;
	}
	
	private String sale_price;
	public String getSale_price() {
		return sale_price;
	}
	public void setSale_price(String sale_price) {
		this.sale_price = sale_price;
	}
	
	private String cost_price;
	public String getCost_price() {
		return cost_price;
	}
	public void setCost_price(String cost_price) {
		this.cost_price = cost_price;
	}
	
	private String mem_price;
	public String getMem_price() {
		return mem_price;
	}
	public void setMem_price(String mem_price) {
		this.mem_price = mem_price;
	}
	
	private String total_stock;
	public String getTotal_stock() {
		return total_stock;
	}
	public void setTotal_stock(String total_stock) {
		this.total_stock = total_stock;
	}
	
	private Integer now_stock;
	public Integer getNow_stock() {
		return now_stock;
	}
	public void setNow_stock(Integer now_stock) {
		this.now_stock = now_stock;
	}
	
	private String warn_stock;
	public String getWarn_stock() {
		return warn_stock;
	}
	public void setWarn_stock(String warn_stock) {
		this.warn_stock = warn_stock;
	}
	
	private String saled_num;
	public String getSaled_num() {
		return saled_num;
	}
	public void setSaled_num(String saled_num) {
		this.saled_num = saled_num;
	}
	
	private String give_inter;
	public String getGive_inter() {
		return give_inter;
	}
	public void setGive_inter(String give_inter) {
		this.give_inter = give_inter;
	}
	
	private String interbuy;
	public String getInterbuy() {
		return interbuy;
	}
	public void setInterbuy(String interbuy) {
		this.interbuy = interbuy;
	}
	
	private String weight;
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	
	private String unit;
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	private String is_ship;
	public String getIs_ship() {
		return is_ship;
	}
	public void setIs_ship(String is_ship) {
		this.is_ship = is_ship;
	}
	
	private String is_volume;
	public String getIs_volume() {
		return is_volume;
	}
	public void setIs_volume(String is_volume) {
		this.is_volume = is_volume;
	}
	
	private String seo_titel;
	public String getSeo_titel() {
		return seo_titel;
	}
	public void setSeo_titel(String seo_titel) {
		this.seo_titel = seo_titel;
	}
	
	private String seo_keyword;
	public String getSeo_keyword() {
		return seo_keyword;
	}
	public void setSeo_keyword(String seo_keyword) {
		this.seo_keyword = seo_keyword;
	}
	
	private String seo_desc;
	public String getSeo_desc() {
		return seo_desc;
	}
	public void setSeo_desc(String seo_desc) {
		this.seo_desc = seo_desc;
	}
	
	private String relate_goods;
	public String getRelate_goods() {
		return relate_goods;
	}
	public void setRelate_goods(String relate_goods) {
		this.relate_goods = relate_goods;
	}
	
	private String give;
	public String getGive() {
		return give;
	}
	public void setGive(String give) {
		this.give = give;
	}
	
	private String label;
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	@Length(max=300)
	private String busi_remark;
	public String getBusi_remark() {
		return busi_remark;
	}
	public void setBusi_remark(String busi_remark) {
		this.busi_remark = busi_remark;
	}
	
	private String is_del;
	public String getIs_del() {
		return is_del;
	}
	public void setIs_del(String is_del) {
		this.is_del = is_del;
	}
	
	private String info_state;
	public String getInfo_state() {
		return info_state;
	}
	public void setInfo_state(String info_state) {
		this.info_state = info_state;
	}
	
	private Timestamp in_date;

	
	private String user_id;
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	private String no_reason;
	public String getNo_reason() {
		return no_reason;
	}
	public void setNo_reason(String no_reason) {
		this.no_reason = no_reason;
	}
	public String  ship_price;
	public String getShip_price() {
		return ship_price;
	}
	public void setShip_price(String ship_price) {
		this.ship_price = ship_price;
	}
	
	public String volume;
	public String getVolume() {
		return volume;
	}
	public void setVolume(String volume) {
		this.volume = volume;
	}
	
	String infoattr_id;
	public String getInfoattr_id() {
		return infoattr_id;
	}
	public void setInfoattr_id(String infoattr_id) {
		this.infoattr_id = infoattr_id;
	}
	
	String is_virtual;
	public String getIs_virtual() {
		return is_virtual;
	}
	public void setIs_virtual(String is_virtual) {
		this.is_virtual = is_virtual;
	}
	
	public Timestamp getIn_date() {
		return in_date;
	}
	public void setIn_date(Timestamp in_date) {
		this.in_date = in_date;
	}
	public String getImg_path() {
		return img_path;
	}
	public void setImg_path(String img_path) {
		this.img_path = img_path;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Goods){
			Goods goods = (Goods)obj;
			return goods_id.equals(goods.getGoods_id());
		}
		
		return false;
	}
	
	@Override
	public int hashCode() {
		return goods_id.hashCode();
	}
	
	//会员名称(商家名称)--扩展属性
	private String cust_name;
	//分类名称串--扩展属性
	private String cat_names;
	//商品品牌--扩展属性
	private String brand_name;
	public String getCust_name() {
		return cust_name;
	}
	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}
	public String getCat_names() {
		return cat_names;
	}
	public void setCat_names(String cat_names) {
		this.cat_names = cat_names;
	}
	public String getBrand_name() {
		return brand_name;
	}
	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}
	
}

