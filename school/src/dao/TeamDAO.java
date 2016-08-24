package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entity.Course;
import entity.Student;
import entity.Team;


public class TeamDAO {
	/**
	 * 按id的先后顺序取几个班级信息
	 * @throws Exception 
	 */
	public List<Team> perpageClassInfo(int start,int end) throws Exception{
		 List<Team> list = new ArrayList<Team>();
		 Connection conn = null;
		 PreparedStatement ps = null;
		 ResultSet rs = null;
		 try {
			conn = DBUtil.getConnection();
			String sql = "select t.* from(select rownum rn,v.* "+
			             "from(select * from team order by id) v) t " +
					     "where  t.rn between ? and ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, start);
			ps.setInt(2, end);
			rs = ps.executeQuery();
			
			while(rs.next()){
				Team t = new Team();
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
	public int classSum() throws Exception{
		 Connection conn = null;
		 PreparedStatement ps = null;
		 ResultSet rs = null;
		 try {
			conn = DBUtil.getConnection();
			String sql = "select count(*) classSum from team ";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			rs.next();
			return rs.getInt("classSum");
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
	public Team findTeamByClassname(String name) throws Exception{
		 Connection conn = null;
		 PreparedStatement ps = null;
		 ResultSet rs = null;
		 try {
			conn = DBUtil.getConnection();
			String sql = "select * from team where name=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			rs = ps.executeQuery();
			Team team = null;
			if(rs.next()){
				team = new Team(rs.getString("id"),rs.getString("name"));
			}
			
			return team;
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
	public void addTeam(Team team) throws Exception{
		 Connection conn = null;
		 PreparedStatement ps = null;
		 try {
			conn = DBUtil.getConnection();
			String sql = "insert into team values(team_seq.nextval,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, team.getName());
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
    public void deleteTeam(String name) throws Exception{
		 Connection conn = null;
		 PreparedStatement ps = null;
		 try {
			conn = DBUtil.getConnection();
			String sql = "delete from team where name=?";
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
    public void updateTeam(Team team) throws Exception{
		 Connection conn = null;
		 PreparedStatement ps = null;
		 try {
			conn = DBUtil.getConnection();
			String sql = "update team set name=? where id=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, team.getName());
			ps.setString(2, team.getId());
           ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}  finally{
			if(conn!=null){
				DBUtil.closeConnection(conn);
			}
		}	  	    	
    }
    /**
     * 根据班级号查找班级名
     */
	public Team findNameByTeamId(String classId) throws Exception{
		 Connection conn = null;
		 PreparedStatement ps = null;
		 ResultSet rs = null;
		 try {
			conn = DBUtil.getConnection();
			String sql = "select * from team where id=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, classId);
			rs = ps.executeQuery();
			Team team = null;
			if(rs.next()){
				team = new Team(
						rs.getString("id"),
						rs.getString("name")
						
						);
			}
			
			return team;
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
	 * 列举
	 * @return
	 * @throws Exception
	 */
	public List<Team> list() throws Exception{
	   	 Connection conn = null;
		 PreparedStatement ps = null;
		 ResultSet rs = null;
		 try {
			conn = DBUtil.getConnection();
			String sql = "select * from team";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			List<Team> list = new ArrayList<Team>();
			while(rs.next()){
				Team team = new Team();
				team.setId(rs.getString("id"));
				team.setName(rs.getString("name"));
				list.add(team);
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
