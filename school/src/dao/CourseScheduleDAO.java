package dao;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;









import entity.Course;
import entity.CourseInfo;
import entity.CourseSchedule;
import entity.Team;

public class CourseScheduleDAO {
       /**
        * 根据班级号遍历课程进度
     * @throws Exception 
        */
	   public List<CourseSchedule> findCourseScheduleByTeamID(String classId,String teacherId) 
			   throws Exception{
			 Connection conn = null;
			 PreparedStatement ps = null;
			 ResultSet rs = null;
			 try {
				List<CourseSchedule> list = new ArrayList<CourseSchedule>(); 
				conn = DBUtil.getConnection();
				String sql = "select * from course_schedule where teamId=?";
				if(teacherId != null){
					sql += "and teacherId=?";
				}
				ps = conn.prepareStatement(sql);
				ps.setString(1, classId);
				if(teacherId != null){
					ps.setString(2, teacherId);
				}
				rs = ps.executeQuery();
				while(rs.next()){
					CourseSchedule courseSchedule = new CourseSchedule();
					courseSchedule.setId(rs.getString("id"));
					courseSchedule.setCredit(rs.getString("credit"));
					courseSchedule.setSemester(rs.getString("semester"));
				    String courseId =  rs.getString("courseId");
				    courseSchedule.setCourseId(courseId);
				    Course course = new CourseDAO().
				    		             findNameByCourseId(courseId);
				    courseSchedule.setCourseName(course.getName());
				    
				    teacherId = rs.getString("teacherId");
				    courseSchedule.setTeacherId(teacherId);
				    String teacherName = new TeacherDAO().
				    		             findNameByTeacherId(teacherId).getName();
				    courseSchedule.setTeacherName(teacherName);
				    courseSchedule.setTeamId(rs.getString("teamId"));
				    list.add(courseSchedule);
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
	    * 根据课程号遍历课程进度
	    */
	   public List<CourseInfo> findCourseScheduleByCourseId(Course course) 
			   throws Exception{
			 Connection conn = null;
			 PreparedStatement ps = null;
			 ResultSet rs = null;
			 try {
				List<CourseInfo> list = new ArrayList<CourseInfo>(); 
				conn = DBUtil.getConnection();
				String sql = "select * from course_schedule where courseId=?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, course.getId());
				rs = ps.executeQuery();
				while(rs.next()){
					CourseInfo courseInfo = new CourseInfo();
					courseInfo.setCourseName(course.getName());
					
					courseInfo.setCredit(rs.getString("credit"));
					courseInfo.setSemester(rs.getString("semester"));
				    String classId =  rs.getString("teamId");
				    Team team = new TeamDAO().
				    		             findNameByTeamId(classId);
				    courseInfo.setTeamName(team.getName());
				    
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
	  /**
	   * 根据课程进度id删除信息  
	 * @throws Exception 
	   */
	   public void deleteByCourseScheduleId(String id) throws Exception{
			 Connection conn = null;
			 PreparedStatement ps = null;
			 try {
				conn = DBUtil.getConnection();
				String sql = "delete from course_schedule where id=?";
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
	   /**
	    * 根据课程进度增加数据
	 * @throws Exception 
	    */
       public void addSchedule(CourseSchedule schedule) throws Exception{
  		 Connection conn = null;
  		 PreparedStatement ps = null;
  		 try {
  			conn = DBUtil.getConnection();
  			String sql = 
  			   "insert into course_schedule values(course_schedule_seq.nextval,?,?,?,?,?)";
  			ps = conn.prepareStatement(sql);
  			ps.setString(1, schedule.getSemester());
  			ps.setString(2, schedule.getCredit());
  			
  			ps.setString(3, schedule.getTeamId());
  			ps.setString(4, schedule.getCourseId());
            ps.setString(5, schedule.getTeacherId());
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
        * 判断是否存在
        * @param courseId
        * @param teamId
        * @param semester
        * @return
        * @throws Exception
        */
       public Boolean isExits(String courseId,String teamId,String semester) throws Exception{
    	   Connection conn = null;
			 PreparedStatement ps = null;
			 ResultSet rs = null;
			 try {
				List<CourseInfo> list = new ArrayList<CourseInfo>(); 
				conn = DBUtil.getConnection();
				String sql = "select * from course_schedule where courseId=? and teamId=? and semester=?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, courseId);
				ps.setString(2, teamId);
				ps.setString(3, semester);
				rs = ps.executeQuery();
				return rs.next();
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			} finally{
				if(conn!=null){
					DBUtil.closeConnection(conn);
				}
			}	   
       }
       
       
       public Boolean isExits(String semester,String credit,String teamId,String courseId,String teacherId) throws Exception{
    	   Connection conn = null;
    	   PreparedStatement ps = null;
    	   ResultSet rs = null;
    	   try {
    		   conn = DBUtil.getConnection();
    		   String sql = "select * from course_schedule where semester=? and credit=? and teamId=? and courseId=? and teacherId=?";
    		   ps = conn.prepareStatement(sql);
    		   ps.setString(1, semester);
    		   ps.setString(2, credit);
    		   ps.setString(3, teamId);
    		   ps.setString(4, courseId);
    		   ps.setString(5, teacherId);
    		   rs = ps.executeQuery();
    		   return rs.next();
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
