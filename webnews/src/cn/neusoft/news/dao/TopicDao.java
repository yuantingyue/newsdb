package cn.neusoft.news.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.neusoft.news.entity.News;
import cn.neusoft.news.entity.Topic;
import cn.neusoft.news.utils.JDBCUtil;

public class TopicDao {
	private final JDBCUtil jdbcUtil=new JDBCUtil();
	
	//查询全部数据
	public List<Topic> findAllTopic(){
		String sql="select * from topic";
		Connection con=jdbcUtil.getConnection();
		PreparedStatement stmt=null;
		ResultSet rs=null;
		List<Topic> list=new ArrayList<>();
		
		try {
			stmt=con.prepareStatement(sql);
			rs=stmt.executeQuery();
			while(rs.next()){
				Topic topic=new Topic();
				topic.setTid(rs.getInt("tid"));
				topic.setTname(rs.getString("tname"));
				list.add(topic);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			jdbcUtil.closeDB(con, stmt, rs);
		}
		return list;
	}
	
	//两表查询加分页
	/**
	 * 
	 * @param pageNo  第几行开始
	 * @param pageSize  查询多少行
	 * @return List<Topic> 集合
	 */
	public List<Topic> findClassTopicAndNews(int pageNo,int pageSize){
		String sql="select * from topic left outer join news on topic.tid=news.ntid order by topic.tid limit ?,?";
		Connection con=jdbcUtil.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Topic topic=null;
		List<Topic> topicList=new ArrayList<>();
		int tem=-1;
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, (pageNo-1)*pageSize);
			pstmt.setInt(2, pageSize);
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				//过滤相同的topic  id的对象，一种分类只创建一个topic对象
				if (rs.getInt("tid")!=tem) {
					topic=new Topic();
					topic.setTid(rs.getInt("tid"));
					topic.setTname(rs.getString("tname"));
					topicList.add(topic);
				}
				//封装新闻数据，没有对应值的新闻 不创建空的news
				if (rs.getString("nid")!=null) {
					News news =new News();
					news.setNid(rs.getInt("nid"));
					news.setNtid(rs.getInt("ntid"));
					news.setNtitle(rs.getString("ntitle"));
					news.setNauthor(rs.getString("nauthor"));
					news.setNcreatedate(rs.getString("ncreatedate"));
					news.setNpicpath(rs.getString("npicpath"));
					news.setNcontent(rs.getString("ncontent"));
					news.setNmodifydate(rs.getString("nmodifydate"));
					news.setNsummary(rs.getString("nsummary"));
					topic.getNewsList().add(news);
				}
				tem=rs.getInt("tid"); //更新topic对象的类别id
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			jdbcUtil.closeDB(con, pstmt, rs);
		}
		
		
		return topicList;
	}
	

	public int getTopicCount() {
		String sql = "SELECT count(*) from topic t LEFT OUTER JOIN news n ON t.tid=n.ntid"; 
		Connection con = jdbcUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			count = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.closeDB(con, pstmt, rs);;
		}
		return count;	
	}
		
	public List<Topic> getAllTopics() {
		String sql = "select * from topic";
		List<Topic> list = new ArrayList<Topic>();
		Connection con = jdbcUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Topic topic = new Topic();
				topic.setTid(rs.getInt("tid"));
				topic.setTname(rs.getString("tname"));
				list.add(topic);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.closeDB(con, ps, rs);
		}
		return list;
	}
	
	//查询tid的新闻信息
	public Topic getTopicById(int tid) {
		String sql = "select * from topic LEFT outer JOIN news on topic.tid=news.ntid where topic.tid=?";
		Connection con = jdbcUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Topic topic=null;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, tid);
			rs = ps.executeQuery();
			if (rs.next()) {
				topic = new Topic();
				topic.setTid(rs.getInt("tid"));
				topic.setTname(rs.getString("tname"));
			}
			//封装新闻数据，没有对应值的新闻 不创建空的news
			if (rs.getString("nid")!=null) {
				News news =new News();
				news.setNid(rs.getInt("nid"));
				news.setNtid(rs.getInt("ntid"));
				news.setNtitle(rs.getString("ntitle"));
				news.setNauthor(rs.getString("nauthor"));
				news.setNcreatedate(rs.getString("ncreatedate"));
				news.setNpicpath(rs.getString("npicpath"));
				news.setNcontent(rs.getString("ncontent"));
				news.setNmodifydate(rs.getString("nmodifydate"));
				news.setNsummary(rs.getString("nsummary"));
				topic.getNewsList().add(news);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.closeDB(con, ps, rs);
		}
		return topic;
	}
	
	
	public List<Topic> getTopicsByPager(int pageNo,int pageSize) {
		String sql = "select * from topic limit ?,?";
		List<Topic> list = new ArrayList<Topic>();
		Connection con = jdbcUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, (pageNo-1)*pageSize);
			ps.setInt(2, pageSize);
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Topic topic = new Topic();
				topic.setTid(rs.getInt("tid"));
				topic.setTname(rs.getString("tname"));
				list.add(topic);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.closeDB(con, ps, rs);
		}
		return list;
	}
	
	public int getAllTopicsCount() {
		String sql = "select count(*) from topic";
		
		Connection con = jdbcUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count = 0;
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			rs.next();
			count = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.closeDB(con, ps, rs);
		}
		return count;
	}

	public int addTopic(Topic topic) {
		String sql = "insert into topic(tname) values(?)";
		
		Connection con = jdbcUtil.getConnection();
		PreparedStatement pstmt = null;
		int row = 0;
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, topic.getTname());
			row = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.closeDB(con, pstmt, null);
		}
		return row;
	}
	
	public int deleteTopic(int tid) {
		String sql = "delete from topic where tid=?";
		
		Connection con = jdbcUtil.getConnection();
		PreparedStatement pstmt = null;
		int row = 0;
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, tid);
			
			row = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.closeDB(con, pstmt, null);
		}
		return row;
	}

	public int updateTopic(Topic topic) {
		String sql = "update topic set tname=? where tid=?";
		
		Connection con = jdbcUtil.getConnection();
		PreparedStatement pstmt = null;
		int row = 0;
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, topic.getTname());
			pstmt.setInt(2, topic.getTid());
			
			row = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.closeDB(con, pstmt, null);
		}
		return row;
	}
}

