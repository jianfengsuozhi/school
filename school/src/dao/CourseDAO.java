package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entity.Course;
import entity.Course;
import entity.Teacher;


public class CourseDAO {
	/**
	 * 按id的先后顺序取几个课程信息
	 * @throws Exception 
	 */
	public List<Course> perpageCourseInfo(int start,int end) throws Exception{
		 List<Course> list = new ArrayList<Course>();
		 Connection conn = null;
		 PreparedStatement ps = null;
		 ResultSet rs = null;
		 try {
			conn = DBUtil.getConnection();
			String sql = "select t.* from(select rownum rn,v.* "+
			             "from(select * from course order by id desc) v) t " +
					     "where  t.rn between ? and ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, start);
			ps.setInt(2, end);
			rs = ps.executeQuery();
			
			while(rs.next()){
				Course t = new Course();
				t.setId(rs.getString("id"));
				t.setName(rs.getString("name"));
				
				list.add(t);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally{
			if(conn!=null){
				DBUtil.closeConnection(conn);
			}
		}
	}
	
	/**
	 * 确定班级的个数
	 * @throws Exception 
	 */
	public int courseSum() throws Exception{
		 Connection conn = null;
		 PreparedStatement ps = null;
		 ResultSet rs = null;
		 try {
			conn = DBUtil.getConnection();
			String sql = "select count(*) courseSum from course ";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			rs.next();
			return rs.getInt("courseSum");
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
	 * 根据班级名查询相应信息
	 * @throws Exception 
	 */
	public Course findCourseByCoursename(String name) throws Exception{
		 
		 Connection conn = null;
		 PreparedStatement ps = null;
		 ResultSet rs = null;
		 try {
			conn = DBUtil.getConnection();
			String sql = "select * from course where name=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			rs = ps.executeQuery();
			Course Course = null;
			if(rs.next()){
				Course = new Course(rs.getString("id"),
						            rs.getString("name")
						           );
			}
			
			return Course;
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
	 * 增加班级信息
	 * @throws Exception 
	 */
	public void addCourse(Course course) throws Exception{
		 Connection conn = null;
		 PreparedStatement ps = null;
		 try {
			conn = DBUtil.getConnection();
			String sql = "insert into course values(course_seq.nextval,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, course.getName());
			
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
	 * 删除班级信息
	 * @throws Exception 
	 */
    public void deleteCourse(String name) throws Exception{
		 Connection conn = null;
		 PreparedStatement ps = null;
		 try {
			conn = DBUtil.getConnection();
			String sql = "delete from course where name=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, name);
            ps.executeUpdate();
		} catch (Exception e) {
			
			throw e;
		}finally{
			if(conn!=null){
				DBUtil.closeConnection(conn);
			}
		}	    	
    }
    
    /**
     * 要修改的班级信息
     * @throws Exception 
     */
    public void updateCourse(Course course) throws Exception{
		 Connection conn = null;
		 PreparedStatement ps = null;
		 try {
			conn = DBUtil.getConnection();
			String sql = "update course set name=? where id=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, course.getName());
			ps.setString(2, course.getId());
           ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally{
			if(conn!=null){
				DBUtil.closeConnection(conn);
			}
		}	   	    	
    }
    /**
     * 根据课程号查找课程名
     */
	public Course findNameByCourseId(String courseId) throws Exception{
		 Connection conn = null;
		 PreparedStatement ps = null;
		 ResultSet rs = null;
		 try {
			conn = DBUtil.getConnection();
			String sql = "select * from course where id=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, courseId);
			rs = ps.executeQuery();
			Course course = null;
			if(rs.next()){
				course = new Course(
						rs.getString("id"),
						rs.getString("name")
						
						);
			}
			
			return course;
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
	 * 查询所有课程名称
	 * @throws Exception 
	 */
	public List<String> findCourseNames() throws Exception{
		 Connection conn = null;
		 PreparedStatement ps = null;
		 ResultSet rs = null;
		 try {
			conn = DBUtil.getConnection();
			String sql = "select name from course";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			List<String> list = new ArrayList<String>();
			while(rs.next()){
				list.add(rs.getString("name"));
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
	
	public List<Course> list() throws Exception{
		 Connection conn = null;
		 PreparedStatement ps = null;
		 ResultSet rs = null;
		 try {
			conn = DBUtil.getConnection();
			String sql = "select * from course";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			List<Course> list = new ArrayList<Course>();
			while(rs.next()){
				Course course = new Course();
				course.setId(rs.getString("id"));
				course.setName(rs.getString("name"));
				list.add(course);
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
}
