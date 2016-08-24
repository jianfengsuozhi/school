package common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dao.DBUtil;

public class Common {
	/**
	 * 获得表中的记录数
	 * @param tableName
	 * @return
	 * @throws Exception
	 */
	public int recordSum(String tableName) throws Exception {
		 Connection conn = null;
		 PreparedStatement ps = null;
		 ResultSet rs = null;
		 try {
			conn = DBUtil.getConnection();
			String sql = "select count(*) recordSum from  "+tableName;
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			rs.next();
			return rs.getInt("recordSum");
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(conn!=null){
				DBUtil.closeConnection(conn);
			}
		}
		return 0;
	}

}
