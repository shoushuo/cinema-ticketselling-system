package com.class28.group3.ticket;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Serializable;

import com.class28.group3.entity.Movie;
import com.class28.group3.entity.ScheduleItem;
import com.class28.group3.entity.Seat;



public class Ticket implements Serializable {

	/**
	 * 定义计划类属性scheduleItem
	 */
	private ScheduleItem scheduleItem;

	/**
	 * 定义座位属性seat
	 */
	private Seat seat;

	/**
	 * 票价属性price
	 */
	private double price;

	/**
	 * print()方法用于向 MyCinemaProject\ticket 目录下打印电影票
	 * 
	 */
	public void print() {
		try {
			String time = scheduleItem.getTime();
			String ticketName = null;
			ticketName = scheduleItem.getMovie().getMovieName() + " "
					+ seat.getSeatNum() + " " + time.substring(0, 2) + "点"
					+ time.substring(3) + "(普通票).txt";

			File file = new File("ticket/");
			if (!file.exists()) {
				file.mkdirs();
			}

			FileWriter fos = new FileWriter("ticket/" + ticketName);
			BufferedWriter bw = new BufferedWriter(fos);
			bw.write("***********************");
			bw.newLine();
			bw.write("\t\t青鸟影院                         ");
			bw.newLine();
			bw.write("-----------------------\n");
			bw.newLine();
			bw.write("电影名：" + scheduleItem.getMovie().getMovieName());
			bw.newLine();
			bw.write("时间：" + scheduleItem.getTime() );
			bw.newLine();
			bw.write("座位号：" + seat.getSeatNum() );
			bw.newLine();
			bw.write("价格：" + price  );
			bw.newLine();
			bw.write("***********************");
			bw.close();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * compute()方法用于计算票价
	 */
	public void compute() {
		price = scheduleItem.getMovie().getPrice();

	}

	public ScheduleItem getScheduleItem() {
		return scheduleItem;
	}

	public void setScheduleItem(ScheduleItem scheduleItem) {
		this.scheduleItem = scheduleItem;
	}

	public Seat getSeat() {
		return seat;
	}

	public void setSeat(Seat seat) {
		this.seat = seat;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
