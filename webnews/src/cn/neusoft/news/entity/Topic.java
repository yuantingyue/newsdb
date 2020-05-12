package cn.neusoft.news.entity;

import java.util.ArrayList;
import java.util.List;

/*
 * 主题 实体类
 * @author
 */

public class Topic {
	private int tid;
	private String tname;

	private List<News> newsList=new ArrayList<News>();

	public Topic(int tid, String tname) {
		this.tid = tid;
		this.tname = tname;
	}

	public Topic() {
		super();
	}

	public List<News> getNewsList() {
		return newsList;
	}

	public void setNewsList(List<News> newsList) {
		this.newsList = newsList;
	}

	public Topic(int tid, String tname, List<News> newsList) {
		super();
		this.tid = tid;
		this.tname = tname;
		this.newsList = newsList;
	}

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

}
