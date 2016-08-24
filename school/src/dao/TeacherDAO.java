package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entity.Course;
import entity.CourseInfo;
import entity.Teacher;
import entity.Teacher;
import entity.Teacher;
import entity.Teacher;
import entity.Team;

public class TeacherDAO {
	/**
	 * 根据teacherId找到教师名
	 * @throws Exception 
	 */
	public Teacher findNameByTeacherId(String teacherId) throws Exception{
		 Connection conn = null;
		 PreparedStatement ps = null;
		 ResultSet rs = null;
		 try {
			conn = DBUtil.getConnection();
			String sql = "select * from teacher where id=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, teacherId);
			rs = ps.executeQuery();
			Teacher teacher = null;
			if(rs.next()){
				teacher = new Teacher(rs.getString("id"),rs.getString("name"));
			}
			
			return teacher;
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
	 * 查询所有教师名称
	 * @throws Exception 
	 */
	public List<Teacher> list() throws Exception{
		 Connection conn = null;
		 PreparedStatement ps = null;
		 ResultSet rs = null;
		 try {
			conn = DBUtil.getConnection();
			String sql = "select * from teacher";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			List<Teacher> list = new ArrayList<Teacher>();
			while(rs.next()){
				Teacher teacher = new Teacher(rs.getString("id"), rs.getString("name"));
				list.add(teacher);
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
	/**
	 * 根据教师号找教师名
	 */
	public String findIdByTeacherName(String teacherName) throws Exception{
		 Connection conn = null;
		 PreparedStatement ps = null;
		 ResultSet rs = null;
		 try {
			conn = DBUtil.getConnection();
			String sql = "select id from teacher where name=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, teacherName);
			rs = ps.executeQuery();
			if(rs.next()){
				return rs.getString("id");
			}
			
			return null;
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
	 * 确定教师的个数
	 * @throws Exception 
	 */
	public int teacherSum() throws Exception{
		 Connection conn = null;
		 PreparedStatement ps = null;
		 ResultSet rs = null;
		 try {
			conn = DBUtil.getConnection();
			String sql = "select count(*) teacherSum from teacher ";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			rs.next();
			return rs.getInt("teacherSum");
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
	 * 按id的先后顺序取几个教师信息
	 * @throws Exception 
	 */
	public List<Teacher> perpageTeacherInfo(int start,int end) throws Exception{
		 List<Teacher> list = new ArrayList<Teacher>();
		 Connection conn = null;
		 PreparedStatement ps = null;
		 ResultSet rs = null;
		 try {
			conn = DBUtil.getConnection();
			String sql = "select t.* from(select rownum rn,v.* "+
			             "from(select * from teacher order by id desc) v) t " +
					     "where  t.rn between ? and ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, start);
			ps.setInt(2, end);
			rs = ps.executeQuery();
			
			while(rs.next()){
				Teacher t = new Teacher();
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
	 * 根据班级名查询相应信息
	 * @throws Exception 
	 */
	public Teacher findTeacherByTeachername(String name) throws Exception{
		 
		 Connection conn = null;
		 PreparedStatement ps = null;
		 ResultSet rs = null;
		 try {
			conn = DBUtil.getConnection();
			String sql = "select * from teacher where name=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			rs = ps.executeQuery();
			Teacher Teacher = null;
			if(rs.next()){
				Teacher = new Teacher(rs.getString("id"),
						            rs.getString("name")
						           );
			}
			
			return Teacher;
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
	public void addTeacher(Teacher teacher) throws Exception{
		 Connection conn = null;
		 PreparedStatement ps = null;
		 try {
			conn = DBUtil.getConnection();
			String sql = "insert into teacher values(teacher_seq.nextval,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, teacher.getName());
		
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
	 * 删除教师信息
	 * @throws Exception 
	 */
    public void deleteTeacher(String name) throws Exception{
		 Connection conn = null;
		 PreparedStatement ps = null;
		 try {
			conn = DBUtil.getConnection();
			String sql = "delete from teacher where name=?";
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
  * 要修改的教师信息
  * @throws Exception 
  */
 public void updateTeacher(Teacher teacher) throws Exception{
		 Connection conn = null;
		 PreparedStatement ps = null;
		 try {
			conn = DBUtil.getConnection();
			String sql = "update teacher set name=? where id=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, teacher.getName());
			
			ps.setString(2, teacher.getId());
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
  * 根据课程号遍历课程进度
  */
 public List<CourseInfo> findCourseScheduleByCourseId(String teacherId) 
		   throws Exception{
		 Connection conn = null;
		 PreparedStatement ps = null;
		 ResultSet rs = null;
		 try {
			List<CourseInfo> list = new ArrayList<CourseInfo>(); 
			conn = DBUtil.getConnection();
			String sql = "select * from course_schedule where teacherId=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, teacherId);
			rs = ps.executeQuery();
			while(rs.next()){
				CourseInfo courseInfo = new CourseInfo();
				
				courseInfo.setCredit(rs.getString("credit"));
				courseInfo.setSemester(rs.getString("semester"));
			    
				String classId =  rs.getString("teamId");
                Team team = new TeamDAO().
			    		             findNameByTeamId(classId);
			    courseInfo.setTeamName(team.getName());
			    
			    String courseId = rs.getString("courseId");
			    Course course = new CourseDAO().findNameByCourseId(courseId);
			    courseInfo.setCourseName(course.getName());
			    list.add(courseInfo);
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

	
}
