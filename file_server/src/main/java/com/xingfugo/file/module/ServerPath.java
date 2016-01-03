package com.xingfugo.file.module;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("serverPath")
public class ServerPath {
	
	@XStreamAlias("name")
	@XStreamAsAttribute
	private String name ;
	
	@XStreamAlias("value")
	@XStreamAsAttribute
	private String value ;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
}
