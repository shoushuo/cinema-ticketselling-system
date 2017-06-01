package com.class28.group3.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.class28.group3.entity.Movie;
import com.class28.group3.entity.ScheduleItem;


@SuppressWarnings("all")
public class Schedule {
	/**
	 * 放映场次属性
	 */
	private List<ScheduleItem> items = new ArrayList<ScheduleItem>();

	public List<ScheduleItem> getItems() {
		return items;
	}

	public void setItems(List<ScheduleItem> items) {
		this.items = items;
	}

	/**
	 * 读取XML文件获取放映集合集合的loadItems()方法
	 */
	public void loadItems() {
		SAXReader xmlReader = new SAXReader();
		try {
			/**
			 * 创建Document对象，并引入XML文件
			 */
			Document doc = xmlReader.read("NewFile.xml");
			Element ele = doc.getRootElement();

			String movieName = null;
			String poster = null;
			String director = null;
			String actor = null;
			String movieType = null;
			String price = null;

			/**
			 * 遍历根元素，得到Movie节点
			 */
			for (Iterator it = ele.elementIterator(); it.hasNext();) {

				Element eleMovie = (Element) it.next();

				// 用Movie节点取Name叶节点的文字
				movieName = eleMovie.elementText("Name");

				// 用Movie节点取Poster叶节点的文字
				poster = eleMovie.elementText("Poster");

				// 用Movie节点取Director叶节点的文字
				director = eleMovie.elementText("Director");

				// 用Movie节点取Actor叶节点的文字
				actor = eleMovie.elementText("Actor");

				// 用Movie节点取Type叶节点的文字
				movieType = eleMovie.elementText("Type");

				// 用Movie节点取Price叶节点的文字
				price = eleMovie.elementText("Price");

				/**
				 * 遍历Movie节点下的节点，如果存在的叶节点有节点，即Schedule节点
				 */
				for (Iterator it1 = eleMovie.elementIterator(); it1.hasNext();) {
					Element eleSchedule = (Element) it1.next();
					/**
					 * 遍历Schedule节点下的节点
					 */
					for (Iterator it2 = eleSchedule.elementIterator(); it2
							.hasNext();) {
						Element eleItem = (Element) it2.next();
						/**
						 * 创建ScheduleItem类的实体对象
						 */
						ScheduleItem item = new ScheduleItem(null, price);
						/**
						 * 创建Movie类的实体对象
						 */
						Movie movie = new Movie();

						// 初始化ScheduleItem类中的time对象值赋为Item节点下的文字
						item.setTime(eleItem.getText());

						// 初始化Movie类的movieName属性
						movie.setMovieName(movieName);

						// 初始化Movie类的poster属性
						movie.setPoster(poster);

						// 初始化Movie类的actor属性
						movie.setActor(actor);

						// 初始化Movie类的director属性
						movie.setDirector(director);

						// 初始化Movie类的movieType属性
						movie.setMovieType(movieType);

						/**
						 * 此处需对String类型的price转换成double类型 初始化Movie类的price属性
						 */

						movie.setPrice(Double.parseDouble(price));

						// 初始化ScheduleItem类中的movie对象值赋为已初始化的movie类
						item.setMovie(movie);
						items.add(item);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
