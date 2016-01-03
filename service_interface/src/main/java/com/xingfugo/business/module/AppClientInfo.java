package com.xingfugo.business.module;

import java.util.Date;

import javax.validation.constraints.NotNull;

public class AppClientInfo {
    private Integer id;

    private String userId;

    private Integer appOs;

    private String appOsVersion;

    @NotNull
    private Integer appDeviceType;

    private String appVersion;

    private String mobileModel;

    private String mobileNumber;

    private String screenResolution;

    private String screenDensity;

    private String screenDpi;

    private Date createTime;

    private String deviceId;
    
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public Integer getAppDeviceType() {
		return appDeviceType;
	}

	public void setAppDeviceType(Integer appDeviceType) {
		this.appDeviceType = appDeviceType;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public String getMobileModel() {
		return mobileModel;
	}

	public void setMobileModel(String mobileModel) {
		this.mobileModel = mobileModel;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getScreenResolution() {
		return screenResolution;
	}

	public void setScreenResolution(String screenResolution) {
		this.screenResolution = screenResolution;
	}

	public String getScreenDensity() {
		return screenDensity;
	}

	public void setScreenDensity(String screenDensity) {
		this.screenDensity = screenDensity;
	}

	public String getScreenDpi() {
		return screenDpi;
	}

	public void setScreenDpi(String screenDpi) {
		this.screenDpi = screenDpi;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

}