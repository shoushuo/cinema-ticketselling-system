package com.class28.group3.entity;

import java.io.Serializable;



public class ScheduleItem implements Serializable{
	
	/**
	 * 本场所放映电影属性
	 * 自定义Movie类型
	 */
	private Movie movie;
	
	/**
	 * 放映时间
	 * String类型
	 */
	private String time;

	public ScheduleItem(Movie movie, String time) {
		this.movie = movie;
		this.time = time;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
}
