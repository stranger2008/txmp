package com.xingfugo.business.module;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class IncFeedback {
    private Integer id;

    @NotEmpty
    private String content;

    private String contact;

    private String deviceId;

    @NotNull
    private Integer appOs;

    private String appOsVersion;

    private Date createTime;

    private String appVersion;
    
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public Integer getAppOs() {
		return appOs;
	}

	public void setAppOs(Integer appOs) {
		this.appOs = appOs;
	}

	public String getAppOsVersion() {
		return appOsVersion;
	}

	public void setAppOsVersion(String appOsVersion) {
		this.appOsVersion = appOsVersion;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}
}