package com.xingfugo.business.outapi.movie.module;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class MovieDetail extends MovieBaseInfo {
	@XStreamAlias("Directors")
	private String directors;
	
	@XStreamAlias("FilmDesc")
	private String filmDesc;
	
	//卖出数量,默认0
	@XStreamAlias("SaleAmount")
	private int saleAmount;
	
	//影片图片,规格：314×138 
	@XStreamAlias("HengPic")
	private String hengPic;
	
	//影片图片,规格：720x1280
	@XStreamAlias("GaoqingPic")
	private String gaoqingPic;

	//影片预告片集合
	@XStreamAlias("FilmVideos")
	private MovieVedios movieVedios;
	
	//影片片花集合
	@XStreamAlias("FilmPictures")
	private MoviePictures moviePictures;

	public int getSaleAmount() {
		return saleAmount;
	}

	public void setSaleAmount(int saleAmount) {
		this.saleAmount = saleAmount;
	}

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

	public MovieVedios getMovieVedios() {
		return movieVedios;
	}

	public void setMovieVedios(MovieVedios movieVedios) {
		this.movieVedios = movieVedios;
	}

	public MoviePictures getMoviePictures() {
		return moviePictures;
	}

	public void setMoviePictures(MoviePictures moviePictures) {
		this.moviePictures = moviePictures;
	}

	public String getDirectors() {
		return directors;
	}

	public void setDirectors(String directors) {
		this.directors = directors;
	}

	public String getFilmDesc() {
		return filmDesc;
	}

	public void setFilmDesc(String filmDesc) {
		this.filmDesc = filmDesc;
	}
}
