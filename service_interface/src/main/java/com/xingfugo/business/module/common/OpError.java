package com.xingfugo.business.module.common;
/**
 * 服务接口调用错误
 */
public class OpError {
	//错误码
	private String code = null;
	//错误描述
	private String msg = null;
	//字段名称
	protected String field = null;
	
	public OpError(String code, String msg){
		this.code = code;
		this.msg = msg;
	}

	public OpError(String code){
		this.code = code;
	}
	public String getCode() {
		return code;
	}
	public String getMsg() {
		return msg;
	}

	public String getField() {
		return field;
	}
}
