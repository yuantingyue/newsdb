package cn.neusoft.news.entity;

import java.util.List;

public class Result {
	private String msg;
	private List datas;
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public List getDatas() {
		return datas;
	}
	public void setDatas(List datas) {
		this.datas = datas;
	}
	public Result(String msg, List datas) {
		
		this.msg = msg;
		this.datas = datas;
	}
	public Result() {
		
	}
	
	
}
