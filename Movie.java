package com.class28.group3.entity;

import java.io.Serializable;



public class Movie implements Serializable{
	
	/**
	 * 电影名属性
	 * String类型
	 */
	private String movieName;
	
	/**
	 * 电影英文名
	 * String类型
	 */
	private String poster;
	
	/**
	 * 导演名
	 * String类型
	 */
	private String director;
	
	/**
	 * 主演
	 * String 类型
	 */
	private String actor;
	
	/**
	 * 电影类型
	 * 自定义枚举类型
	 */
	private String movieType;
	
	/**
	 * 票价属性
	 * int类型
	 */
	private double price;

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getActor() {
		return actor;
	}

	public void setActor(String actor) {
		this.actor = actor;
	}

	public String getMovieType() {
		return movieType;
	}

	public void setMovieType(String movieType) {
		this.movieType = movieType;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}
