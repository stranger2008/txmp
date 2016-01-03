package com.xingfugo.business.outapi.movie.module;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class CommingMovie extends MovieInfo {
	//影片图片,规格：314×138 
	@XStreamAlias("HengPic")
	private String hengPic;
	
	//影片图片,规格：720x1280
	@XStreamAlias("GaoqingPic")
	private String gaoqingPic;

	public String getHengPic() {
		return hengPic;
	}

	public void setHengPic(String hengPic) {
		this.hengPic = hengPic;
	}

	public String getGaoqingPic() {
		return gaoqingPic;
	}

	public void setGaoqingPic(String gaoqingPic) {
		this.gaoqingPic = gaoqingPic;
	}

}
