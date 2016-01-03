package com.xingfugo.business.module;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
/**
 * @function 功能 手机端版本实体
 * @author 创建人 史倩倩
 * @date 创建日期 Fri Sep 12 15:27:55 CST 2014
 */
public class App_android_version implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private Short id;
	public Short getId() {
		return id;
	}
	public void setId(Short id) {
		this.id = id;
	}
	@NotBlank
	@Length(max=100)
	private String pack;
	public String getPack() {
		return pack;
	}
	public void setPack(String pack) {
		this.pack = pack;
	}
	@NotBlank
	@Length(max=20)
	private String ver_code;
	public String getVer_code() {
		return ver_code;
	}
	public void setVer_code(String ver_code) {
		this.ver_code = ver_code;
	}
	@NotBlank
	@Length(max=20)
	private String ver_name;
	public String getVer_name() {
		return ver_name;
	}
	public void setVer_name(String ver_name) {
		this.ver_name = ver_name;
	}
	@NotBlank
	@Length(max=100)
	private String download_url;
	public String getDownload_url() {
		return download_url;
	}
	public void setDownload_url(String download_url) {
		this.download_url = download_url;
	}
	@Length(max=500)
	private String change_log;
	public String getChange_log() {
		return change_log;
	}
	public void setChange_log(String change_log) {
		this.change_log = change_log;
	}
	private String publish_time;
	public String getPublish_time() {
		return publish_time;
	}
	public void setPublish_time(String publish_time) {
		this.publish_time = publish_time;
	}
	

}

