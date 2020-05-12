package cn.neusoft.news.entity;

/*
 * 评论  实体类
 * @author 
 **/
public class Comment {
	
	private int cid;
	private int cnid;
	private String ccontent;
	private String cdate;
	private String cip;
	private String cauthor;
	
	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public int getCnid() {
		return cnid;
	}

	public void setCnid(int cnid) {
		this.cnid = cnid;
	}

	public String getCcontent() {
		return ccontent;
	}

	public void setCcontent(String ccontent) {
		this.ccontent = ccontent;
	}

	public String getCdate() {
		return cdate;
	}

	public void setCdate(String cdate) {
		this.cdate = cdate;
	}

	public String getCip() {
		return cip;
	}

	public void setCip(String cip) {
		this.cip = cip;
	}

	public String getCauthor() {
		return cauthor;
	}

	public void setCauthor(String cauthor) {
		this.cauthor = cauthor;
	}

	public Comment( int cnid, String ccontent, String cip, String cauthor) {
		super();
		this.cnid = cnid;
		this.ccontent = ccontent;
		this.cip = cip;
		this.cauthor = cauthor;
	}

	public Comment() {
		super();
	}
	
	
	
}
