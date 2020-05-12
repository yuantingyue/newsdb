package cn.neusoft.news.utils;



public class PageBean {
	private static final int EVERY_PAGE_SIZE=8;
	private int everyPage;			  //每页显示记录数
	private int totalCount;			 //总记录数,在数据库得到
	private int totalPage;			//总页数，算出来
	private int currentPage;	   //当前页，指定
	private int beginIndex;		  //查询起始点
	private int prevPage;		 //上一页
	private int nextPage;	    //下一页
	
	public PageBean(int currentPage) {
		this.currentPage = currentPage;
		this.prevPage=currentPage;
		this.nextPage=currentPage;
	}
	public PageBean(){}					//默认构造函数
	public int getEveryPage() {		//获得每页显示记录数
		this.everyPage=(this.everyPage<EVERY_PAGE_SIZE?EVERY_PAGE_SIZE:this.everyPage);
		return everyPage;
	}
	public void setEveryPage(int everyPage) {//设置每页显示记录数
		this.everyPage = everyPage;
	}
	public int getTotalCount() {//获得总记录数
		return totalCount;
	}
	public void setTotalCount(int totalCount) {//设置总记录数
		this.totalCount = totalCount;
	}
	public int getTotalPage() {//获得总页数
		this.totalPage=(this.totalCount%getEveryPage()==0)?(this.totalCount/getEveryPage()):(this.totalCount/getEveryPage())+1;
		return totalPage;
	}
	public void setTotalPage(int totalPage) {//设置总页数
		this.totalPage = totalPage;
	}
	public int getCurrentPage() {//获得当前页
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {//设置当前页
		this.currentPage = currentPage;
	}
	public int getBeginIndex() {//获得查询起始点
		return beginIndex;
	}
	public void setBeginIndex(int beginIndex) {//设置查询起始点
		this.beginIndex = beginIndex;
	}
	public int getPrevPage() {
		if (currentPage<=1) { // allPage==1时pageNo也等于1
			prevPage = 1;
		}else if (currentPage >= getTotalPage()){
			prevPage--;
		}else {
			prevPage--;
		}
		return prevPage;
	}
	public void setPrevPage(int prevPage) {
		this.prevPage = prevPage;
	}
	public int getNextPage() {
		if (currentPage <=1) { 
			nextPage++;
		}else if (currentPage >= getTotalPage()){
			nextPage=getTotalPage();
		}else {
			nextPage++;
		}
		return nextPage;
	}
	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}
}
