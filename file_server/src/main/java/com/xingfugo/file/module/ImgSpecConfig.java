package com.xingfugo.file.module;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("spec")
public class ImgSpecConfig {
	@XStreamAlias("width")
	@XStreamAsAttribute
	private Integer width = null;
	
	@XStreamAlias("height")
	@XStreamAsAttribute
	private Integer heigth = null;
	
	public Integer getWidth() {
		return width;
	}
	public void setWidth(Integer width) {
		this.width = width;
	}
	public Integer getHeigth() {
		return heigth;
	}
	public void setHeigth(Integer heigth) {
		this.heigth = heigth;
	}
	
}
