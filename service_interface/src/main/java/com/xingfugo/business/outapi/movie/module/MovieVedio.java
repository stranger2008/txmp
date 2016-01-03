package com.xingfugo.business.outapi.movie.module;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class MovieVedio {
	@XStreamAlias("FilmNo")
	private String filmNo;
	
	@XStreamAlias("FilmName")
	private String filmName;
	
	@XStreamAlias("VideoTitle")
	private String videoTitle;
	
	@XStreamAlias("VideoUrl")
	private String videoUrl;
	
	@XStreamAlias("VideoPicUrl")
	private String videoPicUrl;

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

	public String getVideoTitle() {
		return videoTitle;
	}

	public void setVideoTitle(String videoTitle) {
		this.videoTitle = videoTitle;
	}

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	public String getVideoPicUrl() {
		return videoPicUrl;
	}

	public void setVideoPicUrl(String videoPicUrl) {
		this.videoPicUrl = videoPicUrl;
	}
}
