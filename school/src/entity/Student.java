package entity;
/**
 * 学生信息管理
 * @author Edward
 *
 */
public class Student {
	private String id;
	private String code;
	private String name;
	private String enrollDate;
	private String birthday;
	private String sex;
	private String teamId;
	private String teamName;
	private String phone;
	private String address;
	private String nation;
	
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Student(String id, String code, String name, String enrollDate,
			String birthday, String sex, String teamId, String teamName,
			String phone, String address, String nation) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.enrollDate = enrollDate;
		this.birthday = birthday;
		this.sex = sex;
		this.teamId = teamId;
		this.teamName = teamName;
		this.phone = phone;
		this.address = address;
		this.nation = nation;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getTeamId() {
		return teamId;
	}
	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEnrollDate() {
		return enrollDate;
	}
	public void setEnrollDate(String enrollDate) {
		this.enrollDate = enrollDate;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", code=" + code + ", name=" + name
				+ ", enrollDate=" + enrollDate + ", birthday=" + birthday
				+ ", sex=" + sex + ", teamId=" + teamId + ", teamName="
				+ teamName + "]";
	}
	
}
