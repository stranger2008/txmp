package com.xingfugo.business.outapi.movie.module;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamImplicit;

public class MoviePictures {
	@XStreamImplicit(itemFieldName="item")
	private List<MoviePicture> pictureList;

	public List<MoviePicture> getPictureList() {
		return pictureList;
	}

	public void setPictureList(List<MoviePicture> pictureList) {
		this.pictureList = pictureList;
	}
}
