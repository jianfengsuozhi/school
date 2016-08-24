package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entity.Student;
import entity.Team;

public class StudentDAO {
	
   /**
	 * 确定学生的个数
	 * @throws Exception 
	 */
	public int studentSum() throws Exception{
		 Connection conn = null;
		 PreparedStatement ps = null;
		 ResultSet rs = null;
		 try {
			conn = DBUtil.getConnection();
			String sql = "select count(*) studentSum from student ";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			rs.next();
			return rs.getInt("studentSum");
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
	 * 按id的先后顺序取几个学生信息
	 * @throws Exception 
	 */
	public List<Student> perpageStudentInfo(int start,int end) throws Exception{
		
		 List<Student> list = new ArrayList<Student>();
		 Connection conn = null;
		 PreparedStatement ps = null;
		 ResultSet rs = null;
		 try {
			conn = DBUtil.getConnection();
			String sql = "select t.* from(select rownum rn,v.* "+
			             "from(select * from student order by id desc) v) t " +
					     "where  t.rn between ? and ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, start);
			ps.setInt(2, end);
			rs = ps.executeQuery();
			
			while(rs.next()){
				Student t = new Student();
				t.setBirthday(rs.getString("birthday"));
				t.setCode(rs.getString("code"));
				t.setEnrollDate(rs.getString("enrollDate"));
				t.setId(rs.getString("id"));
				t.setName(rs.getString("name"));
				t.setSex(rs.getString("sex"));
				t.setPhone(rs.getString("phone"));
				t.setAddress(rs.getString("address"));
				t.setNation(rs.getString("nation"));
				String teamId = rs.getString("teamId");
				Team team = new TeamDAO().findNameByTeamId(teamId);
				t.setTeamId(teamId);
				t.setTeamName(team.getName());
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
	 * 是否存在
	 * @throws Exception 
	 */
	public Student findStudentByStudentname(String name) throws Exception{
		 
		 Connection conn = null;
		 PreparedStatement ps = null;
		 ResultSet rs = null;
		 try {
			conn = DBUtil.getConnection();
			String sql = "select * from student where name=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			rs = ps.executeQuery();
			Student t = null;
			if(rs.next()){
				t =  new Student();
				t.setBirthday(rs.getString("birthday"));
				t.setCode(rs.getString("code"));
				t.setEnrollDate(rs.getString("enrollDate"));
				t.setId(rs.getString("id"));
				t.setName(rs.getString("name"));
				t.setSex(rs.getString("sex"));
				t.setPhone(rs.getString("phone"));
				t.setAddress(rs.getString("address"));
				t.setNation(rs.getString("nation"));
				String teamId = rs.getString("teamId");
				Team team = new TeamDAO().findNameByTeamId(teamId);
				t.setTeamName(team.getName());
			}
			return t;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			if(conn!=null){
				DBUtil.closeConnection(conn);
			}
		}	
	}
	
	public Boolean isExits(String id,String name) throws Exception{
		 Connection conn = null;
		 PreparedStatement ps = null;
		 ResultSet rs = null;
		 try {
			conn = DBUtil.getConnection();
			String sql = "select * from student where name=? and id!=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, id);
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
	 * 根据id找学生信息
	 * @param id
	 * @return
	 */
	public Student findById(String id){
		 Connection conn = null;
		 PreparedStatement ps = null;
		 ResultSet rs = null;
		 try {
			 conn = DBUtil.getConnection();
			 String sql = "select * from student where id=?";
			 ps = conn.prepareStatement(sql);
			 ps.setString(1, id);
			 rs = ps.executeQuery();
			 Student student = null;
			 if(rs.next()){
				 student = new Student();
				 student.setId(rs.getString("id"));
				 student.setCode(rs.getString("code"));
				 student.setName(rs.getString("name"));
				 student.setEnrollDate(rs.getString("enrollDate"));
				 student.setBirthday(rs.getString("birthday"));
				 student.setSex(rs.getString("sex"));
				 student.setPhone(rs.getString("phone"));
				 student.setAddress(rs.getString("address"));
				 student.setNation(rs.getString("nation"));			 
				 student.setTeamId(rs.getString("teamId"));
			 }
			 return student;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 增加学生信息
	 * @throws Exception 
	 */
	public void addStudent(Student student) throws Exception{
		 Connection conn = null;
		 PreparedStatement ps = null;
		 try {
			conn = DBUtil.getConnection();
			String sql = "insert into student values(student_seq.nextval,?,?,?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, student.getCode());
			ps.setString(2, student.getName());
			ps.setString(3, student.getEnrollDate());
			ps.setString(4, student.getBirthday());
			ps.setString(5, student.getSex());
			ps.setString(6, student.getTeamId());
			ps.setString(7, student.getPhone());
			ps.setString(8, student.getAddress());
			ps.setString(9, student.getNation());
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
	 * 删除学生信息
	 * @throws Exception 
	 */
    public void deleteStudent(String name) throws Exception{
    	
		 Connection conn = null;
		 PreparedStatement ps = null;
		 try {
			conn = DBUtil.getConnection();
			String sql = "delete from student where name=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, name);
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
    
    public List<Student> list() throws Exception{
   	 Connection conn = null;
	 PreparedStatement ps = null;
	 ResultSet rs = null;
	 try {
		conn = DBUtil.getConnection();
		String sql = "select * from student";
		ps = conn.prepareStatement(sql);
		rs = ps.executeQuery();
		List<Student> list = new ArrayList<Student>();
		while(rs.next()){
			Student student = new Student();
			student.setId(rs.getString("id"));
			student.setName(rs.getString("name"));
			list.add(student);
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
    
    public void update(String id,String code,String name,String enrollDate,String birthday,String sex,String teamId,String phone,String address,String nation) throws Exception{
		 Connection conn = null;
 		 PreparedStatement ps = null;
 		 try {
 			conn = DBUtil.getConnection();
 			String sql = 
 			   "update student set code=?,name=?,enrollDate=?,birthday=?,sex=?,teamId=?,phone=?,address=?,nation=? where id=?";
 			ps = conn.prepareStatement(sql);
 			ps.setString(1, code);
 			ps.setString(2, name);
 			ps.setString(3, enrollDate);
 			ps.setString(4, birthday);
 			ps.setString(5, sex);
 			ps.setString(6, teamId);
 			ps.setString(7, phone);
 			ps.setString(8, address);
 			ps.setString(9, nation);
 			ps.setString(10, id);
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
