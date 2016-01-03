package com.xingfugo.business.outapi.movie.module;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class MoviePicture {
	@XStreamAlias("FilmNo")
	private String filmNo;
	
	@XStreamAlias("FilmName")
	private String filmName;
	
	@XStreamAlias("PictureTitle")
	private String pictureTitle;
	
	@XStreamAlias("PictureUrl")
	private String pictureUrl;

	public String getFilmNo() {
		return filmNo;
	}

	public void setFilmNo(String filmNo) {
		this.filmNo = filmNo;
	}

	public String getFilmName() {
		return filmName;
	}

	public void setFilmName(String filmName) {
		this.filmName = filmName;
	}

	public String getPictureTitle() {
		return pictureTitle;
	}

	public void setPictureTitle(String pictureTitle) {
		this.pictureTitle = pictureTitle;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}
}
