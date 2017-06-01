package com.class28.group3.ticket;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import com.class28.group3.entity.Movie;
import com.class28.group3.entity.ScheduleItem;
import com.class28.group3.entity.Seat;


@SuppressWarnings("all")

public class FreeTicket extends Ticket {
	
	/**
	 * 赠票人属性
	 */
	private String customerName;
	
	/**
	 * print()方法用于向 MyCinemaProject\ticket 目录下打印电影票 
	 */
	@Override
	public void print() {
		try {

			String time = super.getScheduleItem().getTime();
			String ticketName = null;
			ticketName = super.getScheduleItem().getMovie().getMovieName()
					+ " " + super.getSeat().getSeatNum() + " "
					+ time.substring(0, 2) + "点" + time.substring(3) + "(赠票).txt";
			
			File file=new File("ticket/");
			if(!file.exists()){
				file.mkdirs();
			}
			
			FileWriter fos = new FileWriter("ticket/" + ticketName);
			BufferedWriter bw = new BufferedWriter(fos);
			bw.write("***********************");
			bw.newLine();
			bw.write("\t青鸟影院（赠票）                ");
			bw.newLine();
			bw.write("-----------------------");
			bw.newLine();
			bw.write("电影名：" + super.getScheduleItem().getMovie().getMovieName());
			bw.newLine();
			bw.write("时间：" + super.getScheduleItem().getTime());
			bw.newLine();
			bw.write("座位号：" + super.getSeat().getSeatNum());
			bw.newLine();
			bw.write("姓名：" + customerName );
			bw.newLine();
			bw.write("***********************");
			bw.newLine();
			bw.close();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * compute()方法用于计算票价
	 */
	@Override
	public void compute() {
		super.setPrice(0);
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
}

