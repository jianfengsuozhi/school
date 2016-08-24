package entity;

public class CourseSchedule {
	private String id;
	private String semester;
	private String courseName;
	private String teacherName;
	private String credit;
	private String teamId;
	private String courseId;
	private String teacherId;
	
	public CourseSchedule() {
		super();
	}
	
	public CourseSchedule(String id, String semester, String courseName,
			String teacherName, String credit, String teamId, String courseId,
			String teacherId) {
		super();
		this.id = id;
		this.semester = semester;
		this.courseName = courseName;
		this.teacherName = teacherName;
		this.credit = credit;
		this.teamId = teamId;
		this.courseId = courseId;
		this.teacherId = teacherId;
	}

	public String getTeamId() {
		return teamId;
	}
	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	public String getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSemester() {
		return semester;
	}
	public void setSemester(String semester) {
		this.semester = semester;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public String getCredit() {
		return credit;
	}
	public void setCredit(String credit) {
		this.credit = credit;
	}
	@Override
	public String toString() {
		return "CourseSchedule [id=" + id + ", semester=" + semester
				+ ", courseName=" + courseName + ", teacherName=" + teacherName
				+ ", credit=" + credit + "]";
	}
	
	

}
