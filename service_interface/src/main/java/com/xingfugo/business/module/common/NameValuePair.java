package com.xingfugo.business.module.common;

import org.apache.commons.lang.StringUtils;

public class NameValuePair implements Comparable<NameValuePair>{
	//参数名称
	private String name = null;
	//参数值
	private Object value = null;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Object getValue() {
		return value;
	}
	
	public void setValue(Object value) {
		this.value = value;
	}
	
	public int compareTo(NameValuePair nvp) {
		if(nvp == null || StringUtils.isEmpty(nvp.getName())){
			return 1;
		}
		
		if(StringUtils.isEmpty(name)){
			return -1;
		}
		
		return name.compareTo(nvp.getName());
	}
}
