package com.class28.group3.cinema;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.class28.group3.entity.ScheduleItem;
import com.class28.group3.entity.Seat;
import com.class28.group3.ticket.Ticket;
import com.class28.group3.util.Schedule;


public class Cinema {
	/**
	 * 放映计划 自定义Schedule类型
	 */
	private Schedule schedule;
	/**
	 * 放映计划的场次 自定义ScheduleItem类型
	 */
	private ScheduleItem scheduleItem;
	/**
	 * 所属座位对象 自定义Seat类型
	 */
	private Seat seat;
	/**
	 * 已售出电影票的集合 泛型集合List<Ticket>类型
	 */
	public List<Ticket> soldTickets = new ArrayList<Ticket>();

	/**
	 * 保存销售情况
	 */
	public void save() {
		ObjectOutputStream oos = null;

		try {
			oos = new ObjectOutputStream(new FileOutputStream(
					"ticket/ticket.bin"));
			oos.writeObject(soldTickets);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				oos.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * 读取销售情况
	 */
	public void load(Ticket ticket) {

		ObjectInputStream ois = null;
		try {
			File f = new File("ticket/");
			if (!f.exists()) {
				f.mkdir();// 创建一个由该File对象表示的目录
				System.out.println("ticket文件夹创建成功！");
				File file = new File("ticket/ticket.bin");
				if (!file.exists()) {
					System.out.println("售票成功！");
					ticket.print();
					/**
					 * 保存第一次售票
					 */
					save();
				}
			} else {

				ois = new ObjectInputStream(new FileInputStream(
						"ticket/ticket.bin"));

				ArrayList<Ticket> sTicket = (ArrayList<Ticket>) ois
						.readObject();
				for (int i = 0; i < sTicket.size(); i++) {
					String name = sTicket.get(i).getScheduleItem().getMovie()
							.getMovieName();// 赋值电影票名称
					String time = sTicket.get(i).getScheduleItem().getTime();// 赋值电影放映时间
					String seatNum = sTicket.get(i).getSeat().getSeatNum();// 赋值电影票座位号

					// 用电影票名称、时间和座位号判断是否和初始化相同
					if ((ticket.getScheduleItem().getMovie().getMovieName())
							.equals(name)
							&& (ticket.getScheduleItem().getTime())
									.equals(time)
							&& (seat.getSeatNum()).equals(seatNum)) {
						System.out.println("此票已售出，请重新购买");
						break;
					} else {
						System.out.println("售票成功！");
						/**
						 * 打印票据到txt文档
						 */
						ticket.print();
						/**
						 * 保存销售成功的电影票
						 */
						save();
						break;
					}

				}
				ois.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
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

}
