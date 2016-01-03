package com.xingfugo.business.module.query;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.xingfugo.business.module.mybatis.BasePageForm;

public class GoodsQueryForm extends BasePageForm{
	
	private Integer cust_id;
	private String cat_id;
	
	private String membercat;
	private String area_id;
	private String key;
	private String is_del;
	
	private String goodsName;
	private Boolean is_onsell;
	private String brand_id;
	private String brand_name;
	
	private String maxPrice;
	private String minPrice;
	
	private String trade_id;
	/**
	 * 商品属性值查询条件列表
	 */
	private List<String> valIdList;
	
	public Boolean getIs_onsell() {
		return is_onsell;
	}

	public void setIs_onsell(Boolean is_onsell) {
		this.is_onsell = is_onsell;
	}
	
	
	//0：热卖 1：精品 2：新品 3： 橱窗推荐  4:热销
	private String label;
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getCat_id() {
		return cat_id;
	}
	public void setCat_id(String cat_id) {
		this.cat_id = cat_id;
	}
	public String getArea_id() {
		return area_id;
	}
	public void setArea_id(String area_id) {
		this.area_id = area_id;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public Integer getCust_id() {
		return cust_id;
	}
	public void setCust_id(Integer cust_id) {
		this.cust_id = cust_id;
	}
	
	//create by 陈显革
	private String goods_name;
	private String goods_no;
	private String cat_attr;
	private String info_state;
	private String info_states;
	
	public String getGoods_name() {
		return goods_name;
	}
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	public String getGoods_no() {
		return goods_no;
	}
	public void setGoods_no(String goods_no) {
		this.goods_no = goods_no;
	}
	public String getCat_attr() {
		return cat_attr;
	}
	public void setCat_attr(String cat_attr) {
		this.cat_attr = cat_attr;
	}
	public String getInfo_state() {
		return info_state;
	}
	public void setInfo_state(String info_state) {
		this.info_state = info_state;
	}
	public String getInfo_states() {
		return info_states;
	}
	public void setInfo_states(String info_states) {
		this.info_states = info_states;
	}
	public String getIs_del() {
		return is_del;
	}
	public void setIs_del(String is_del) {
		this.is_del = is_del;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getBrand_id() {
		return brand_id;
	}

	public void setBrand_id(String brand_id) {
		this.brand_id = brand_id;
	}

	public String getBrand_name() {
		return brand_name;
	}

	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}

	public String getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(String maxPrice) {
		this.maxPrice = maxPrice;
	}

	public String getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(String minPrice) {
		this.minPrice = minPrice;
	}

	public String getTrade_id() {
		return trade_id;
	}

	public void setTrade_id(String trade_id) {
		this.trade_id = trade_id;
	}

	public String getMembercat() {
		return membercat;
	}

	public void setMembercat(String membercat) {
		this.membercat = membercat;
	}
	//商品属性id
	private String attr_id;

	public String getAttr_id() {
		return attr_id;
	}

	public void setAttr_id(String attr_id) {
		this.attr_id = attr_id;
	}

	public List<String> getValIdList() {
		return valIdList;
	}

	public void setValIdList(List<String> valIdList) {
		this.valIdList = valIdList;
	}


}
