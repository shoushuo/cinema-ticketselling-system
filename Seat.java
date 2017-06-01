package com.class28.group3.entity;

import java.io.Serializable;


@SuppressWarnings("all")

public class Seat implements Serializable{
	
	/**
	 * 座位属性
	 * String类型
	 */
	private String seatNum;

	public String getSeatNum() {
		return seatNum;
	}

	public void setSeatNum(String seatNum) {
		this.seatNum = seatNum;
	}
}
