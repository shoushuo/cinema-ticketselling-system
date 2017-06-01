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
	 * ��Ʊ������
	 */
	private String customerName;
	
	/**
	 * print()���������� MyCinemaProject\ticket Ŀ¼�´�ӡ��ӰƱ 
	 */
	@Override
	public void print() {
		try {

			String time = super.getScheduleItem().getTime();
			String ticketName = null;
			ticketName = super.getScheduleItem().getMovie().getMovieName()
					+ " " + super.getSeat().getSeatNum() + " "
					+ time.substring(0, 2) + "��" + time.substring(3) + "(��Ʊ).txt";
			
			File file=new File("ticket/");
			if(!file.exists()){
				file.mkdirs();
			}
			
			FileWriter fos = new FileWriter("ticket/" + ticketName);
			BufferedWriter bw = new BufferedWriter(fos);
			bw.write("***********************");
			bw.newLine();
			bw.write("\t����ӰԺ����Ʊ��                ");
			bw.newLine();
			bw.write("-----------------------");
			bw.newLine();
			bw.write("��Ӱ����" + super.getScheduleItem().getMovie().getMovieName());
			bw.newLine();
			bw.write("ʱ�䣺" + super.getScheduleItem().getTime());
			bw.newLine();
			bw.write("��λ�ţ�" + super.getSeat().getSeatNum());
			bw.newLine();
			bw.write("������" + customerName );
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
	 * compute()�������ڼ���Ʊ��
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
