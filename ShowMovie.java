package com.class28.group3.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.class28.group3.biz.TicketFactory;
import com.class28.group3.cinema.Cinema;
import com.class28.group3.ticket.FreeTicket;
import com.class28.group3.ticket.StudentTicket;
import com.class28.group3.ticket.Ticket;
import com.class28.group3.util.Schedule;


public class ShowMovie {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);

		Schedule sc = new Schedule();
		sc.loadItems();

		/**
		 * 调用放映列表展示query()
		 */
		query(sc);

		Cinema cinema = new Cinema();
		Seat seat = new Seat();

		/**
		 * 调用座位信息方法的集合
		 */
		List<String> list = seatForm();

		String flag = null;
		do {
			TicketFactory tf = new TicketFactory();
			String Name = inputMovieName(input, sc);// 调用输入电影名称的方法
			String Time = inputMovieTime(input, sc);// 调用输入时间的方法
			Ticket newTicket = ticketType(input, tf);// 调用输入电影票类型的方法
			String seatId = inputSeat(input, list);// 调用输入电影票座位的方法
			seat.setSeatNum(seatId);// 得到座位inputSeat()方法中座位的值并赋值
			cinema.setSeat(seat);// Cinema类的setSeat属性赋值
			newTicket.setSeat(seat);// ticket类的seat属性赋值
			/**
			 * <p>
			 * 遍历输出电影院的电影票、电影票的价格和购票时间
			 * </p>
			 * 参数 scName和scPrice scName是String 类型 scPrice是Double类型
			 */
			for (int j = 0; j < sc.getItems().size(); j++) {
				String scName = sc.getItems().get(j).getMovie().getMovieName();
				Double scPrice = sc.getItems().get(j).getMovie().getPrice();
				/**
				 * 使用if判断条件电影名称是否相同
				 */
				if (Name.equals(scName)) {
					newTicket.setScheduleItem(sc.getItems().get(j));// ticket类的schedule属性赋值
					newTicket.setPrice(scPrice);// ticket类的price 属性赋值
					newTicket.getScheduleItem().setTime(Time);// schduleItem类time赋值
					break;
				}
			}
			/**
			 * 调用计算票价方法
			 */
			newTicket.compute();
			cinema.soldTickets.add(newTicket);// 把销售的票添加到soldTickets集合中
			/**
			 * 读取销售情况(含保存销售成功的电影票方法)
			 */
			cinema.load(newTicket);

			System.out.println("按0重新购买,按其他键退出！");
			flag = input.next();
		} while (flag.equals("0"));
		System.out.println("已安全退出系统！");
	}

	/**
	 * 用来处理座位输入异常的方法
	 * 
	 * @param input
	 * @param list
	 * @return seatId
	 */
	public static String inputSeat(Scanner input, List list) {
		System.out.println("请输入你要选择的座位号：");
		String seatId = input.next();

		// 判断购买的座位是否与返回值seatId相同
		if (!list.contains(seatId)) {
			System.out.println("你输入的座位号不存在，请重新输入！");
			seatId = inputSeat(input, list);//
		}
		return seatId;
	}

	/**
	 * 处理要选择的票的类型的异常
	 * 
	 * @param input
	 * @param tf
	 * @return ticketType
	 */
	public static Ticket ticketType(Scanner input, TicketFactory tf) {
		try {
			System.out.println("请选择你要购票的类型：1.普通票2.学生票3.赠送票");
			int number = input.nextInt();
			Ticket newTicket = tf.ticketTxt(number);// 创建要购票类型的对象
			/**
			 * 根据选票类型不同提示不同操作
			 */
			switch (number) {
			case 1:
				break;
			case 2:
				System.out.println("请输入你需要的折扣（1-9的整数）：");
				StudentTicket st = (StudentTicket) newTicket; // 强制转换为学生票
				int disD = input.nextInt();
				if (disD > 0 && disD < 10) {
					st.setDiscout(disD); // 输入折扣赋值
				} else {
					System.err.println("请输入正确的1-9的整数！");
					ticketType(input, tf);
				}
				break;

			case 3:
				System.out.println("请输入赠票人的名字：");
				FreeTicket ft = (FreeTicket) newTicket;
				ft.setCustomerName(input.next()); // CustomerName赋值
				break;
			default:// 如果输入的数值不是1-3，重新输入
				System.out.println("请输入1-3的整数！");
				newTicket = ticketType(input, tf);

			}
			return newTicket;

		} catch (Exception e) {
			System.out.println("请按照提示输入正确的整数！");
			input.nextLine();// 将每次得到的扫描信息清空
			return ticketType(input, tf);
		}
	}

	/**
	 * 处理输入电影名称异常的方法
	 * 
	 * @param input
	 * @param sc
	 * @return Name
	 */
	public static String inputMovieName(Scanner input, Schedule sc) {
		System.out.println("请选择电影名称：");
		String Name = input.next();

		List<String> list = new ArrayList<String>();// 创建初始化电影名称的集合
		// 遍历初始化电影名称
		for (ScheduleItem item : sc.getItems()) {
			list.add(item.getMovie().getMovieName());// 将每次得到的电影名称添加到集合中
		}
		// 判断选择的电影名称是否与初始化名称相同
		if (!list.contains(Name)) {
			System.out.println("电影不存在，请重新输入！");
			Name = inputMovieName(input, sc);
		}
		return Name;
	}

	/**
	 * 处理输入电影放映时间异常的方法
	 * 
	 * @param input
	 * @param sc
	 * @return time
	 */
	public static String inputMovieTime(Scanner input, Schedule sc) {
		System.out.println("请选择电影时间：");
		String time = input.next();

		List<String> list = new ArrayList<String>();// 创建初始化电影放映时间的集合
		// 遍历初始化电影放映时间
		for (ScheduleItem item : sc.getItems()) {
			list.add(item.getTime());// 将每次得到的电影放映时间添加到集合中
		}
		// 判断选择的电影放映时间是否与初始化电影放映时间相同
		if (!list.contains(time)) {
			System.out.println("时间不存在，请重新输入！");
			time = inputMovieTime(input, sc);
		}
		return time;
	}

	/**
	 * 座位结构图的方法
	 * 
	 * @return list
	 */
	public static List<String> seatForm() {
		System.out.println("----------下面为影院的座位结构图----------");
		System.out.println("\t----------屏幕----------");

		List<String> list = new ArrayList<String>();

		// for循环显示出影院的座位分布情况
		for (int i = 1; i <= 5; i++) {
			for (int j = 1; j <= 7; j++) {
				System.out.print(i + "-" + j + "   ");
				list.add(i + "-" + j);

			}
			System.out.println();
		}
		return list;
	}

	/**
	 * 查询电影名称等信息
	 * 
	 * @param sc
	 */
	public static void query(Schedule sc) {
		int id = 1;
		System.out.println("序号\t" + "电影名称\t" + "英文名称\t" + "导演\t" + "演员\t"
				+ "电影类型\t" + "价格\t" + "时间\t");

		for (ScheduleItem si : sc.getItems()) {

			System.out.println(id + "\t" + si.getMovie().getMovieName() + "\t"
					+ si.getMovie().getPoster() + "\t"
					+ si.getMovie().getDirector() + "\t"
					+ si.getMovie().getActor() + "\t"
					+ si.getMovie().getMovieType() + "\t"
					+ si.getMovie().getPrice() + "\t" + si.getTime());
			id++;

		}
	}

}
