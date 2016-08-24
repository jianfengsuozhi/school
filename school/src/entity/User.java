package entity;
/**
 * 实体类:对应用户表(user_dt)
 * 
 *
 */
public class User {
	private int userId; 
	private String userName;
	private String userPwd;
	private String userEmail;
	public User(int userId, String userName, String userPwd, String userEmail) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userPwd = userPwd;
		this.userEmail = userEmail;
	}
	public User(){
		
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	@Override
	public String toString() {
		return userId + "," + userName
				+ "," + userPwd + "," + userEmail;
	}
	
}
