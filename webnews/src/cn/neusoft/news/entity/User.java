package cn.neusoft.news.entity;
/*
 * 用户 实体类
 * @author
 */
public class User { 
	
	private int uid;
	private String uname;
	private String upwd;

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public void setUpwd(String upwd) {
		this.upwd = upwd;
	}

	public String getUname() {
		return uname;
	}

	public String getUpwd() {
		return upwd;
	}
}
