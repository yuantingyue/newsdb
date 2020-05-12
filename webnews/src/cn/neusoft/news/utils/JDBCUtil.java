package cn.neusoft.news.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCUtil {
	private static final String DRIVERNAME = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/newsdb?useUnicode=true&characterEncoding=UTF-8";
	private static final String USER = "root";
	private static final String PASSWORD = "";

	//创建get连接
	public Connection getConnection() {
		Connection con = null;
		try {
			Class.forName(DRIVERNAME);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}

	//关流
	public void closeDB(Connection con,Statement stmt,ResultSet rs){
			try {
				if (rs!=null) {
					rs.close();
				}
				if (stmt!=null) {
					stmt.close();
				}
				
				if (con!=null) {
					con.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
}
