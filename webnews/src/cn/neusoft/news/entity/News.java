package cn.neusoft.news.entity;
/*
 * 新闻 实体类
 * @author  
 */

public class News {
	
	private int nid;
	private int ntid;
	private String ntitle;
	private String nauthor;
	private String ncreatedate;
	private String npicpath;
	private String ncontent;
	private String nmodifydate;
	private String nsummary;
	public int getNid() {
		return nid;
	}

	public void setNid(int nid) {
		this.nid = nid;
	}

	public int getNtid() {
		return ntid;
	}

	public void setNtid(int ntid) {
		this.ntid = ntid;
	}

	public String getNtitle() {
		return ntitle;
	}

	public void setNtitle(String ntitle) {
		this.ntitle = ntitle;
	}

	public String getNauthor() {
		return nauthor;
	}

	public void setNauthor(String nauthor) {
		this.nauthor = nauthor;
	}

	public String getNcreatedate() {
		return ncreatedate;
	}

	public void setNcreatedate(String ncreatedate) {
		this.ncreatedate = ncreatedate;
	}

	public String getNpicpath() {
		return npicpath;
	}

	public void setNpicpath(String npicpath) {
		this.npicpath = npicpath;
	}

	public String getNcontent() {
		return ncontent;
	}

	public void setNcontent(String ncontent) {
		this.ncontent = ncontent;
	}

	public String getNmodifydate() {
		return nmodifydate;
	}

	public void setNmodifydate(String nmodifydate) {
		this.nmodifydate = nmodifydate;
	}

	public String getNsummary() {
		return nsummary;
	}

	public void setNsummary(String nsummary) {
		this.nsummary = nsummary;
	}
}
