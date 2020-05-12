package cn.neusoft.news.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.sun.accessibility.internal.resources.accessibility;

import cn.neusoft.news.dao.CommentDao;
import cn.neusoft.news.dao.NewsDao;
import cn.neusoft.news.dao.TopicDao;
import cn.neusoft.news.entity.Comment;
import cn.neusoft.news.entity.News;
import cn.neusoft.news.entity.NewsDetail;
import cn.neusoft.news.entity.Result;
import cn.neusoft.news.entity.Topic;

/**
 * 新闻的servlet Servlet implementation class NewsServlet
 */
public class NewsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int pageSize = 8;
	private static int ntid = -1;

	/**
	 * 
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response) doGet(){} doPost(){doGet();}
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");

		if ("news".equals(action)) {
			printNewsToIndex(request, response);
		} else if ("detail".equals(action)) {
			doNewsDetail(request, response);
		} else if ("doComment".equals(action)) {
			doComment(request, response);
		} else if ("doCommentAJAX".equals(action)) {
			doCommentAJAX(request, response);
		}
	}

	private void doCommentAJAX(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
		if (request.getParameter("ctid")!=null) {
			PrintWriter out= response.getWriter();
			Result result =new Result();
			// 拿到评论数据
			int cnid = Integer.parseInt(request.getParameter("ctid"));
			String cauthor = request.getParameter("cauthor");
			// String cip= request.getParameter("cip");
			String cip = getIp(request);
			String ccontent = request.getParameter("ccontent");
			
			CommentDao commentDao = new CommentDao();
			Comment comment = new Comment(cnid, ccontent, cip, cauthor);
			int n = commentDao.addComment(comment);
			List<Comment> comlist = commentDao.findCommentByNews(cnid);
			result.setDatas(comlist);
			result.setMsg(n+"");
			String json = JSON.toJSONString(result);
			
			out.print(json);
			out.flush();
			out.close();
		}else {
			response.sendRedirect(request.getContextPath()+"/NewsServlet?action=news");
		}
	}

	// 对指定的新闻添加评论
	protected void doComment(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("ctid")!=null) {
			CommentDao commentDao = new CommentDao();
			// 拿到评论数据
			int cnid = Integer.parseInt(request.getParameter("ctid"));
			String cauthor = request.getParameter("cauthor");
			// String cip= request.getParameter("cip");
			String cip = getIp(request);
			String ccontent = request.getParameter("ccontent");
			Comment comment = new Comment(cnid, ccontent, cip, cauthor);
			// 添加数据
			commentDao.addComment(comment);
			// 上下文
			request.getRequestDispatcher("/NewsServlet?action=detail&nid=" + cnid).forward(request, response);
		}else{
			response.sendRedirect(request.getContextPath()+"/NewsServlet?action=news");
		}
		
	}

	// 新闻详情和查看评论
	protected void doNewsDetail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("nid")!=null) {
			int nid = Integer.parseInt(request.getParameter("nid"));
			NewsDao newsDao = new NewsDao();
			CommentDao commentDao = new CommentDao();
			// 查找新闻详情
			NewsDetail newsDetail = newsDao.findNewsById(nid);
			// 查找评论所有信息
			List<Comment> commentsList = commentDao.findCommentByNews(nid);
			// 查找全部news
			List<News> listAllNews = newsDao.findAllNews();
			request.setAttribute("newsDetail", newsDetail);
			request.setAttribute("commentsList", commentsList);
			request.setAttribute("listAllNews", listAllNews);
			request.getRequestDispatcher("/news_read_ajax.jsp").forward(request, response);
		}else {
			response.sendRedirect(request.getContextPath()+"/NewsServlet?action=news");
		}
		
	}

	// 打印news表信息到首页
	protected void printNewsToIndex(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		int sizeNews = -1;
		TopicDao topicDao = new TopicDao();
		NewsDao newsDao = new NewsDao();
		List<News> listNews = null;

		// 查找全部news
		List<News> listAllNews = newsDao.findAllNews();
		// 查询topic表的全部数据
		List<Topic> listTopic = topicDao.findAllTopic();

		// 默认显示第1页（首页）
		int pageNo = 1;
		// 获取页面的当前页
		if (request.getParameter("pageNo") != null) {
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
		}

		// 分类查询news数据
		if (request.getParameter("ntid") != null) {
			ntid = Integer.parseInt(request.getParameter("ntid"));
		}
		// 判断是全部新闻信息分页，还是新闻信息分类后的分页
		String tid = request.getParameter("tid");
		if ("ntid".equals(tid) && ntid != -1) {
			if (pageNo > 0) {
				listNews = newsDao.findClassNews(ntid, pageNo, pageSize);
				sizeNews = newsDao.findClassNewsSize(ntid);
			} else {
				sizeNews = 0;
			}
		} else {
			ntid = -1;
			// 从当前页的首行查询pageSize条数据
			listNews = newsDao.findAllByPage(pageNo, pageSize);
			// 查询表的总条数
			sizeNews = newsDao.findNewsSize();
		}

		// 总页数（尾页）
		int allPages = (sizeNews % pageSize == 0) ? (sizeNews / pageSize) : (sizeNews / pageSize) + 1;

		// 上一页和下一页
		int prev = pageNo;
		int next = pageNo;
		// 判断是否有上下页和首页
		if (allPages <= 0) {// 当文本大小为0时，没有页数: pageNo=0;
			prev = 0;
			next = 0;
			pageNo = 0;
		} else if (allPages == 1) { // allPage==1时pageNo也等于1
			prev = 1;
			next = 1;

		} else if (allPages > 1 && pageNo >= 1 && pageNo < allPages) {
			if (pageNo == 1) {
				prev = 1;
			} else {
				prev--;
			}
			next++;
		} else if (pageNo >= allPages) {
			prev--;
			next = allPages;
		}
		// 上下文
		request.setAttribute("listNews", listNews);
		request.setAttribute("pageNo", pageNo);
		request.setAttribute("allPages", allPages);
		request.setAttribute("prev", prev);
		request.setAttribute("next", next);
		request.setAttribute("listTopic", listTopic);
		request.setAttribute("listAllNews", listAllNews);
		// 转发
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

	// 获取request的ip地址
	public static String getIp(HttpServletRequest request) {
		String remoteAddr = request.getRemoteAddr();
		String forwarded = request.getHeader("X-Forwarded-For");
		String realIp = request.getHeader("X-Real-IP");

		String ip = null;
		if (realIp == null) {
			if (forwarded == null) {
				ip = remoteAddr;
			} else {
				ip = remoteAddr + "/" + forwarded;
			}
		} else {
			if (realIp.equals(forwarded)) {
				ip = realIp;
			} else {
				ip = realIp + "/" + forwarded.replaceAll(", " + realIp, "");
			}
		}
		return ip;
	}
}
