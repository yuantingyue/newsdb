package cn.neusoft.news.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.neusoft.news.entity.Comment;
import cn.neusoft.news.entity.News;
import cn.neusoft.news.utils.JDBCUtil;

public class CommentDao {
	private final JDBCUtil jdbcUtil=new JDBCUtil();
	
	//
	public List<Comment> findCommentByNews(int cnid){
		String sql="select * from comments where cnid=?";
		Connection con=jdbcUtil.getConnection();
		PreparedStatement stmt=null;
		ResultSet rs=null;
		List<Comment> list=new ArrayList<Comment>();
		try {
			stmt=con.prepareStatement(sql);
			stmt.setInt(1, cnid);
			rs=stmt.executeQuery();
			while(rs.next()){
				Comment com=new Comment();
				com.setCid(rs.getInt("cid"));
				com.setCnid(rs.getInt("cnid"));
				com.setCcontent(rs.getString("ccontent"));
				com.setCdate(rs.getString("cdate"));
				com.setCip(rs.getString("cip"));
				com.setCauthor(rs.getString("cauthor"));
				list.add(com);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			jdbcUtil.closeDB(con, stmt, rs);
		}
		return list;
	}
	
	//添加评论
	public int addComment(Comment comment){
		String sql="insert into comments (cnid,ccontent,cdate,cip,cauthor) values (?,?,now(),?,?)";
		Connection con=jdbcUtil.getConnection();
		PreparedStatement stmt=null;
		ResultSet rs=null;
		int n=0;
		try {
			stmt=con.prepareStatement(sql);
			stmt.setInt(1, comment.getCnid());
			stmt.setString(2, comment.getCcontent());
			stmt.setString(3, comment.getCip());
			stmt.setString(4, comment.getCauthor());
			n=stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			jdbcUtil.closeDB(con, stmt, rs);
		}
		return n;
	}
	
	//删除评论
	public int deleteComment(int cid){
		String sql="delete from comments where cid=?";
		Connection con=jdbcUtil.getConnection();
		PreparedStatement stmt=null;
		ResultSet rs=null;
		int n=0;
		try {
			stmt=con.prepareStatement(sql);
			stmt.setInt(1, cid);
			n=stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			jdbcUtil.closeDB(con, stmt, rs);
		}
		return n;
	}
}
