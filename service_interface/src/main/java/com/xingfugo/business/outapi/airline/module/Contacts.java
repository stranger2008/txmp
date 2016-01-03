package com.xingfugo.business.outapi.airline.module;

public class Contacts {
	//联系人标识
	private Integer contact_id;
	//会员标识
	private Integer user_id;
	//订票联系人
	private String linkman;
	//联系电话
	private String linkphone;
	//联系邮箱
	private String linkmail;
	//其他联系方式
	private String otherLinkMethod;
	//默认联系人
	private String defaultlinkman;
	public Integer getContact_id() {
		return contact_id;
	}
	public void setContact_id(Integer contact_id) {
		this.contact_id = contact_id;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public String getLinkman() {
		return linkman;
	}
	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}
	public String getLinkphone() {
		return linkphone;
	}
	public void setLinkphone(String linkphone) {
		this.linkphone = linkphone;
	}
	public String getLinkmail() {
		return linkmail;
	}
	public void setLinkmail(String linkmail) {
		this.linkmail = linkmail;
	}
	public String getOtherLinkMethod() {
		return otherLinkMethod;
	}
	public void setOtherLinkMethod(String otherLinkMethod) {
		this.otherLinkMethod = otherLinkMethod;
	}
	public String getDefaultlinkman() {
		return defaultlinkman;
	}
	public void setDefaultlinkman(String defaultlinkman) {
		this.defaultlinkman = defaultlinkman;
	}
	
	
	
}
