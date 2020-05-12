package cn.neusoft.news.servlet;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class UploadFileServlet
 */
public class UploadFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		
		DiskFileItemFactory dfiFactory= new DiskFileItemFactory();
		ServletFileUpload upload= new ServletFileUpload(dfiFactory);
		//设置文件上传的参数
		upload.setHeaderEncoding("UTF-8");
		//设置文件上传的最大值
		upload.setFileSizeMax(1024*1024*100);
		
		try {
			List list = upload.parseRequest(request);
			Iterator iterator = list.iterator();
			while(iterator.hasNext()){
				FileItem item = (FileItem) iterator.next();
//				C:\\Program Files\\apache-tomcat-8.0.50\\webapps\\webnews\\upload
				//获取upload在tomcat下的绝对路径
				String uploadfilePath=request.getServletContext().getRealPath("upload");
				System.out.println(uploadfilePath);
				request.getSession().setAttribute("uploadFileTest",item.getName() );
				//获取upload的文件目录下的指定文件名的文件
				File targetFile = new File(uploadfilePath,item.getName());
				item.write(targetFile);
				System.out.println("upload OK");
			}
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.sendRedirect("showUnloadFile.jsp");
		
	}

}
