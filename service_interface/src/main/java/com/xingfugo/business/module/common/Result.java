package com.xingfugo.business.module.common;

import java.io.Serializable;
import java.util.List;

/**
 * 服务接口调用返回对象
 */
public class Result implements Serializable{
	private static final long serialVersionUID = 1428056130636389557L;
	
	private static int CODE_SUCCESS = 1;
	private static int CODE_FAILED = 0;

	//状态码：0：失败；1：成功；
	private int code = -1;
	
	//错误消息列表，仅当状态码为0时存在内容
	private List<? extends OpError> errs = null;
	
	//数据内容，与具体的业务相关
	private Object data = null;

	public static Result getSuccessResult(){
		return new Result(CODE_SUCCESS);
	}
	
	public static Result getFailedResult(){
		return new Result(CODE_FAILED);
	}
	
	private Result(int code){
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}

	public List<? extends OpError> getErrs() {
		return errs;
	}

	public Object getData() {
		return data;
	}

	public void setErrs(List<? extends OpError> errs) {
		this.errs = errs;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
}
