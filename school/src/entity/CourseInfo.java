package entity;

public class CourseInfo {
	private String courseName;
	private String teamName;
	private String semester;
	private String credit;
	public CourseInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CourseInfo(String courseName, String teamName, String semester,
			String credit) {
		super();
		this.courseName = courseName;
		this.teamName = teamName;
		this.semester = semester;
		this.credit = credit;
	}
	@Override
	public String toString() {
		return "CourseInfo [courseName=" + courseName + ", teamName=" + teamName
				+ ", semester=" + semester + ", credit=" + credit + "]";
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public String getSemester() {
		return semester;
	}
	public void setSemester(String semester) {
		this.semester = semester;
	}
	public String getCredit() {
		return credit;
	}
	public void setCredit(String credit) {
		this.credit = credit;
	}
	
	

}
