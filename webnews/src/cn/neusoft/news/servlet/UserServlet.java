package cn.neusoft.news.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import cn.neusoft.news.dao.UserDao;
import cn.neusoft.news.entity.User;

/**
 * Servlet implementation class AdminServlet
 */
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		
		if ("doLogin".equals(action)) {
			doLogin(request,response);
		}else if ("doLogout".equals(action)) {
			doLogout(request,response);
		}
	}

	private void doLogout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		response.sendRedirect(request.getContextPath()+"/NewsServlet?action=news");
	}

	private void doLogin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		UserDao userDao = new UserDao();
		
		String username=request.getParameter("uname");
		String password = request.getParameter("upwd");
		
		User userLogin = userDao.login(username, password);
		
		if (userLogin!=null) {
			HttpSession session = request.getSession(); //获取session
			session.setAttribute("loginOk", userLogin);   //存储session
			response.sendRedirect(request.getContextPath()+"/NewsServlet?action=news");
		}else {
			request.setAttribute("loginInfo", "登录失败");
//			response.sendRedirect(request.getContextPath()+"/NewsServlet?action=news");
			request.getRequestDispatcher("/NewsServlet?action=news").forward(request, response);
		}
		
	}

}
