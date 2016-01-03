package com.xingfugo.business.module;

import java.io.Serializable;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
/**
 * @function 功能 会员等级实体
 * @author 创建人 史倩倩
 * @date 创建日期 Fri Sep 05 15:54:11 CST 2014
 */
public class Userlevelset implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	@Length(max=10)
	private String level_code;
	public String getLevel_code() {
		return level_code;
	}
	public void setLevel_code(String level_code) {
		this.level_code = level_code;
	}
	@NotBlank
	@Length(max=30)
	private String level_name;
	public String getLevel_name() {
		return level_name;
	}
	public void setLevel_name(String level_name) {
		this.level_name = level_name;
	}
	
}

