package com.xingfugo.file.module;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamImplicit;

public class ImgAutoConfig {
	@XStreamImplicit
	private List<ImgSpecConfig> sysSpecList = null;
	
	@XStreamImplicit
	private List<ImgModuleConfig> moduleSpecList = null;

	public List<ImgSpecConfig> getSysSpecList() {
		return sysSpecList;
	}

	public void setSysSpecList(List<ImgSpecConfig> sysSpecList) {
		this.sysSpecList = sysSpecList;
	}

	public List<ImgModuleConfig> getModuleSpecList() {
		return moduleSpecList;
	}

	public void setModuleSpecList(List<ImgModuleConfig> moduleSpecList) {
		this.moduleSpecList = moduleSpecList;
	}

}
