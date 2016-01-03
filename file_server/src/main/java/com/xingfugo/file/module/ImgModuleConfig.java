package com.xingfugo.file.module;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("module")
public class ImgModuleConfig {
	@XStreamAlias("name")
	@XStreamAsAttribute
	private String name;
	
	@XStreamImplicit
	private List<ImgSpecConfig> specList = null;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ImgSpecConfig> getSpecList() {
		return specList;
	}

	public void setSpecList(List<ImgSpecConfig> specList) {
		this.specList = specList;
	}
	
	
}
