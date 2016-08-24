package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.User;

public class UserDAO {
	/**查看用户信息*/
	public List<User> findAll() throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<User> users = new ArrayList<User>();
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement("select * from suser");
			rs = ps.executeQuery();
			while(rs.next()){
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String password = rs.getString("password");
				String email = rs.getString("email");
				User u = new User(id, name, password, email);
				users.add(u);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally{
			DBUtil.closeConnection(con);
		}
		return users;
	}
	
	/**保存增加用户信息(注册时用)*/
	public void save(User u) throws Exception{
		Connection con = null;
		PreparedStatement pst = null;
		try{
			con = DBUtil.getConnection();
			pst = con.prepareStatement
			("insert into suser values(suser_seq.nextval,?,?,?)");
			pst.setString(1,u.getUserName());
			pst.setString(2,u.getUserPwd());
			pst.setString(3,u.getUserEmail());
			pst.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			DBUtil.closeConnection(con);
		}
		
	}
	
	/**按照name查询一个实体信息*/
	public User findByUserName(String name)throws Exception{
		User user = null;
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try{
			conn = DBUtil.getConnection();
			pst = conn.prepareStatement
			 ("select * from suser where name=?");
			pst.setString(1, name);
			rs = pst.executeQuery();
			while(rs.next()){
				user = new User();
				user.setUserId(rs.getInt("id"));
				user.setUserName(rs.getString("name"));
				user.setUserPwd(rs.getString("password"));
				user.setUserEmail(rs.getString("email"));
			}
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			DBUtil.closeConnection(conn);
		}
		return user;
	}
	
}
