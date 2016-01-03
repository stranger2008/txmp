package com.xingfugo.business.module;

import java.io.Serializable;

public class Advertise implements Serializable{
	
	private static final long serialVersionUID = -5810850698668619892L;
	
	private Integer pos_id = null;
	private String img_path = null;
	private String link_url = null;
	private String adv_desc = null;
	private String title = null;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getPos_id() {
		return pos_id;
	}
	public void setPos_id(Integer pos_id) {
		this.pos_id = pos_id;
	}
	public String getImg_path() {
		return img_path;
	}
	public void setImg_path(String img_path) {
		this.img_path = img_path;
	}
	public String getLink_url() {
		return link_url;
	}
	public void setLink_url(String link_url) {
		this.link_url = link_url;
	}
	public String getAdv_desc() {
		return adv_desc;
	}
	public void setAdv_desc(String adv_desc) {
		this.adv_desc = adv_desc;
	}
	
	
}
