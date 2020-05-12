package cn.neusoft.news.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import cn.neusoft.news.entity.User;
import cn.neusoft.news.utils.JDBCUtil;

public class UserDao {
	private final JDBCUtil jdbcUtil = new JDBCUtil();

	/**
	 * username 用户名
	 * password 密码
	 * @return 通过返回user是否为null来判断用户是否存在
	 */
	
	public User login(String username,String password) {
		String sql = "SELECT * from news_users where uname=? and upwd=?";
		Connection con = jdbcUtil.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		User user=null;
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, username);
			stmt.setString(2, password);
			rs = stmt.executeQuery();
			if (rs.next()) {
				user=new User();
				user.setUid(rs.getInt("usid"));
				user.setUname(rs.getString("uname"));
				user.setUpwd(rs.getString("upwd"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			jdbcUtil.closeDB(con, stmt, rs);
		}
		return user;
	}
}
