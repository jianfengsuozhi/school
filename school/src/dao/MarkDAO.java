package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entity.Course;
import entity.Mark;
import entity.Student;
import entity.Team;

public class MarkDAO {
	/**
	 * 列举
	 * @param start
	 * @param end
	 * @return
	 * @throws Exception 
	 */
	public List<Mark> list(int start,int end) throws Exception{
		 List<Mark> list = new ArrayList<Mark>();
		 Connection conn = null;
		 PreparedStatement ps = null;
		 ResultSet rs = null;
		 try {
			 	conn = DBUtil.getConnection();
				String sql = "select t.* from(select rownum rn,v.* "+
				             "from(select * from mark order by id desc) v) t " +
						     "where  t.rn between ? and ?";
				ps = conn.prepareStatement(sql);
				ps.setInt(1, start);
				ps.setInt(2, end);
				rs = ps.executeQuery();
				
				while(rs.next()){
					Mark t = new Mark();
					t.setId(rs.getString("id"));
					t.setScore(rs.getString("score"));
					t.setCourseId(rs.getString("courseId"));
					t.setStudentId(rs.getString("studentId"));
					list.add(t);
				}
				return list;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(conn!=null){
				DBUtil.closeConnection(conn);
			}
		}	   
		return null;
	}
	
	
	public List<Mark> listAll() throws Exception{
		 List<Mark> list = new ArrayList<Mark>();
		 Connection conn = null;
		 PreparedStatement ps = null;
		 ResultSet rs = null;
		 try {
			 	conn = DBUtil.getConnection();
				String sql = "select * from mark";
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();
				
				while(rs.next()){
					Mark t = new Mark();
					t.setId(rs.getString("id"));
					t.setScore(rs.getString("score"));
					t.setCourseId(rs.getString("courseId"));
					t.setStudentId(rs.getString("studentId"));
					list.add(t);
				}
				return list;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(conn!=null){
				DBUtil.closeConnection(conn);
			}
		}	   
		return null;
	}
	
	/**
	 * 判断是否存在
	 * @param studentId
	 * @param courseId
	 * @return
	 * @throws Exception
	 */
	public Boolean isExits(String studentId,String courseId) throws Exception{
		 Connection conn = null;
		 PreparedStatement ps = null;
		 ResultSet rs = null;
		 try {
			 	conn = DBUtil.getConnection();
				String sql = "select * from mark where studentId=? and courseId=?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, studentId);
				ps.setString(2, courseId);
				rs = ps.executeQuery();
				return rs.next();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			if(conn!=null){
				DBUtil.closeConnection(conn);
			}
		}	   
	}
	
	/**
	 * 插入
	 * @param score
	 * @param studentId
	 * @param courseId
	 * @throws Exception
	 */
	public void insert(String score,String studentId,String courseId) throws Exception{
		 Connection conn = null;
 		 PreparedStatement ps = null;
 		 try {
 			conn = DBUtil.getConnection();
 			String sql = 
 			   "insert into mark values(mark_seq.nextval,?,?,?)";
 			ps = conn.prepareStatement(sql);
 			ps.setString(1, score);
 			ps.setString(2, studentId);
 			ps.setString(3, courseId);
            ps.executeUpdate();
 		} catch (Exception e) {
 			e.printStackTrace();
 			throw e;
 		}finally{
 			if(conn!=null){
 				DBUtil.closeConnection(conn);
 			}
 		}	
	}
	
	/**
	 * 更新
	 * @param id
	 * @param score
	 * @param studentId
	 * @param courseId
	 * @throws Exception
	 */
	public void update(String id,String score,String studentId,String courseId) throws Exception{
		 Connection conn = null;
 		 PreparedStatement ps = null;
 		 try {
 			conn = DBUtil.getConnection();
 			String sql = 
 			   "update mark set score=?,studentId=?,courseId=? where id=?";
 			ps = conn.prepareStatement(sql);
 			ps.setString(1, score);
 			ps.setString(2, studentId);
 			ps.setString(3, courseId);
 			ps.setString(4, id);
            ps.executeUpdate();
 		} catch (Exception e) {
 			e.printStackTrace();
 			throw e;
 		}finally{
 			if(conn!=null){
 				DBUtil.closeConnection(conn);
 			}
 		}	
	}
	
	public void delete(String id) throws Exception{
		 Connection conn = null;
 		 PreparedStatement ps = null;
 		 try {
 			conn = DBUtil.getConnection();
 			String sql = 
 			   "delete from mark where id=?";
 			ps = conn.prepareStatement(sql);
 			ps.setString(1, id);
            ps.executeUpdate();
 		} catch (Exception e) {
 			e.printStackTrace();
 			throw e;
 		}finally{
 			if(conn!=null){
 				DBUtil.closeConnection(conn);
 			}
 		}	
	}
	
	public List<Mark> findByCourseId(String courseId) throws Exception{
		 Connection conn = null;
		 PreparedStatement ps = null;
		 ResultSet rs = null;
		 try {
			conn = DBUtil.getConnection();
			String sql = "select * from mark where courseId=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, courseId);
			rs = ps.executeQuery();
			List<Mark> list = new ArrayList<>();
			while(rs.next()){
				Mark mark = new Mark();
				mark.setId(rs.getString("id"));
				mark.setScore(rs.getString("score"));
				mark.setCourseId(rs.getString("courseId"));
				mark.setStudentId(rs.getString("studentId"));
				list.add(mark);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			if(conn!=null){
				DBUtil.closeConnection(conn);
			}
		}	
	}
	
	public void update(String id,String score) throws Exception{
		 Connection conn = null;
 		 PreparedStatement ps = null;
 		 try {
 			conn = DBUtil.getConnection();
 			String sql = 
 			   "update mark set score=? where id=?";
 			ps = conn.prepareStatement(sql);
 			ps.setString(1, score);
 			ps.setString(2, id);
            ps.executeUpdate();
 		} catch (Exception e) {
 			e.printStackTrace();
 			throw e;
 		}finally{
 			if(conn!=null){
 				DBUtil.closeConnection(conn);
 			}
 		}	
	}
	
	
	
}
