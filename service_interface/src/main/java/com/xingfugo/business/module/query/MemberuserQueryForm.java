package com.xingfugo.business.module.query;

import java.util.ArrayList;
import java.util.List;

import com.xingfugo.business.module.Userlevelset;
import com.xingfugo.business.module.mybatis.BasePageForm;

/**
 * @function 功能 会员列表表单查询实体
 * @author 创建人 史倩倩
 * @date 创建日期 Fri Sep 05 10:54:27 CST 2014
 */

public class MemberuserQueryForm extends BasePageForm{
	
	private String user_level;
	public String getUser_level() {
		return user_level;
	}
	public void setUser_level(String user_level) {
		this.user_level = user_level;
	}
	
	private String user_name;
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	
	private String state_code;
	private String email;
	private String real_name;
	private String sex;
	private String cellphone;
	private String login_time;
	private String from_time;
	private String to_time;
	public String getFrom_time() {
		return from_time;
	}
	public void setFrom_time(String from_time) {
		this.from_time = from_time;
	}
	public String getTo_time() {
		return to_time;
	}
	public void setTo_time(String to_time) {
		this.to_time = to_time;
	}
	public String getState_code() {
		return state_code;
	}
	public void setState_code(String state_code) {
		this.state_code = state_code;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getReal_name() {
		return real_name;
	}
	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getCellphone() {
		return cellphone;
	}
	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}
	public String getLogin_time() {
		return login_time;
	}
	public void setLogin_time(String login_time) {
		this.login_time = login_time;
	}
	
	public List<Userlevelset> memlist = new ArrayList<Userlevelset>();
	public List<Userlevelset> getMemlist() {
		return memlist;
	}
	public void setMemlist(List<Userlevelset> memlist) {
		this.memlist = memlist;
	}
}

