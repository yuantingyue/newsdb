package cn.neusoft.news.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.neusoft.news.entity.News;
import cn.neusoft.news.entity.NewsDetail;
import cn.neusoft.news.entity.Topic;
import cn.neusoft.news.utils.JDBCUtil;

public class NewsDao {
	private final JDBCUtil jdbcUtil = new JDBCUtil();

	// 查询News的全部数据
	public List<News> findAllNews() {
		String sql = "SELECT * FROM news";
		Connection con = jdbcUtil.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<News> listNews = new ArrayList<News>();
		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				News news = new News();
				news.setNid(rs.getInt("nid"));
				news.setNtid(rs.getInt("ntid"));
				news.setNtitle(rs.getString("ntitle"));
				news.setNauthor(rs.getString("nauthor"));
				news.setNcreatedate(rs.getString("ncreatedate"));
				news.setNpicpath(rs.getString("npicpath"));
				news.setNcontent(rs.getString("ncontent"));
				news.setNmodifydate(rs.getString("nmodifydate"));
				news.setNsummary(rs.getString("nsummary"));
				listNews.add(news);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			jdbcUtil.closeDB(con, stmt, rs);
		}
		return listNews;
	}

	// 分页查询
	public List<News> findAllByPage(int pageNo, int pageSize) {
		String sql = "SELECT * FROM news limit ?,?";
		Connection con = jdbcUtil.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<News> listNews = new ArrayList<News>();
		try {
			stmt = con.prepareStatement(sql);
			// 分页参数
			stmt.setInt(1, (pageNo - 1) * pageSize);
			stmt.setInt(2, pageSize);
			rs = stmt.executeQuery();
			while (rs.next()) {
				News news = new News();
				news.setNid(rs.getInt("nid"));
				news.setNtid(rs.getInt("ntid"));
				news.setNtitle(rs.getString("ntitle"));
				news.setNauthor(rs.getString("nauthor"));
				news.setNcreatedate(rs.getString("ncreatedate"));
				news.setNpicpath(rs.getString("npicpath"));
				news.setNcontent(rs.getString("ncontent"));
				news.setNmodifydate(rs.getString("nmodifydate"));
				news.setNsummary(rs.getString("nsummary"));
				listNews.add(news);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			jdbcUtil.closeDB(con, stmt, rs);
		}
		return listNews;
	}

	// 获取news总条数
	public int findNewsSize() {
		String sql = "SELECT count(*) FROM news";
		Connection con = jdbcUtil.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int n = 0;
		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			rs.next();
			n = rs.getInt(1);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			jdbcUtil.closeDB(con, stmt, rs);
		}
		return n;
	}

	// 分类查询
	public List<News> findClassNews(int ntid, int pageNo, int pageSize) {
		String sql = "select * from news  WHERE ntid=? limit ?,?";
		Connection con = jdbcUtil.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<News> listNews = new ArrayList<News>();
		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, ntid);
			stmt.setInt(2, (pageNo - 1) * pageSize);
			stmt.setInt(3, pageSize);
			rs = stmt.executeQuery();
			while (rs.next()) {
				News news = new News();
				news.setNid(rs.getInt("nid"));
				news.setNtid(rs.getInt("ntid"));
				news.setNtitle(rs.getString("ntitle"));
				news.setNauthor(rs.getString("nauthor"));
				news.setNcreatedate(rs.getString("ncreatedate"));
				news.setNpicpath(rs.getString("npicpath"));
				news.setNcontent(rs.getString("ncontent"));
				news.setNmodifydate(rs.getString("nmodifydate"));
				news.setNsummary(rs.getString("nsummary"));
				listNews.add(news);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			jdbcUtil.closeDB(con, stmt, rs);
		}
		return listNews;
	}

	// 分类后的条数
	// 获取news总条数
	public int findClassNewsSize(int ntid) {
		String sql = "SELECT count(*) FROM news WHERE ntid=?";
		Connection con = jdbcUtil.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int n = 0;
		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, ntid);
			rs = stmt.executeQuery();
			rs.next();
			n = rs.getInt(1);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			jdbcUtil.closeDB(con, stmt, rs);
		}
		return n;
	}

	// 查询文本信息
	public NewsDetail findNewsById(int nid) {
		String sql = "SELECT news.*,topic.tname FROM news,topic WHERE news.ntid=topic.tid and news.nid=?";
		Connection con = jdbcUtil.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		NewsDetail news = null;
		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, nid);
			rs = stmt.executeQuery();
			while (rs.next()) {
				news = new NewsDetail();
				news.setNid(rs.getInt("nid"));
				news.setNtid(rs.getInt("ntid"));
				news.setNtitle(rs.getString("ntitle"));
				news.setNauthor(rs.getString("nauthor"));
				news.setNcreatedate(rs.getString("ncreatedate"));
				news.setNpicpath(rs.getString("npicpath"));
				news.setNcontent(rs.getString("ncontent"));
				news.setNmodifydate(rs.getString("nmodifydate"));
				news.setNsummary(rs.getString("nsummary"));
				news.setTname(rs.getString("tname"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			jdbcUtil.closeDB(con, stmt, rs);
		}
		return news;
	}
	
	//添加新闻到数据库
	public int addNews(News news){
		String sql="insert into news (ntid,ntitle,nauthor,ncreatedate,npicpath,ncontent,nmodifydate,nsummary) values (?,?,?,NOW(),?,?,?,?)";
		Connection con=jdbcUtil.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int n=0;
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, news.getNtid());
			pstmt.setString(2, news.getNtitle());
			pstmt.setString(3, news.getNauthor());
			pstmt.setString(4, news.getNpicpath());
			pstmt.setString(5, news.getNcontent());
			pstmt.setString(6, news.getNmodifydate());
			pstmt.setString(7, news.getNsummary());
			n=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			jdbcUtil.closeDB(con, pstmt, rs);
		}
		return n;
	}
	
	//更新新闻
	public int updateNews(News news){
		String sql="update  news set  ntid=?,ntitle=?,nauthor=?,npicpath=?,ncontent=?,nmodifydate=now(),nsummary=? where nid=?";
		Connection con=jdbcUtil.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int row=0;
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, news.getNtid());
			pstmt.setString(2, news.getNtitle());
			pstmt.setString(3, news.getNauthor());
			pstmt.setString(4, news.getNpicpath());
			pstmt.setString(5, news.getNcontent());
			pstmt.setString(6, news.getNsummary());
			pstmt.setInt(7, news.getNid());
			row=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			jdbcUtil.closeDB(con, pstmt, rs);
		}
		return row;
	}
	
	//delete
	public int deleteNews(int nid){
		String sql="delete from news where nid=?";
		Connection con=jdbcUtil.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int row=0;
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, nid);
			row=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			jdbcUtil.closeDB(con, pstmt, rs);
		}
		return row;
	}
	
	
	
	
}
