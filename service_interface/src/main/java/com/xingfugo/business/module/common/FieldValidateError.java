package com.xingfugo.business.module.common;

/**
 * 与字段（参数）对应的错误消息
 */
public class FieldValidateError extends OpError {
	
	public FieldValidateError(String field, String code){
		super(code);
		this.field = field;
	}

	public FieldValidateError(String field, String code, String msg){
		super(code, msg);
		this.field = field;
	}
}
