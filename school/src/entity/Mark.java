package entity;

/**
 * 成绩
 * @author Edward
 *
 */
public class Mark {
	private String id;
	private String score;
	private String teamName;
	private String code;
	private String semester;
	private String courseId;
	private String courseName;
	private String studentId;
	private String studentName;
	
	public Mark() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Mark(String id, String score, String teamName, String code,
			String semester, String courseId, String courseName,
			String studentId, String studentName) {
		super();
		this.id = id;
		this.score = score;
		this.teamName = teamName;
		this.code = code;
		this.semester = semester;
		this.courseId = courseId;
		this.courseName = courseName;
		this.studentId = studentId;
		this.studentName = studentName;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getSemester() {
		return semester;
	}
	public void setSemester(String semester) {
		this.semester = semester;
	}
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	@Override
	public String toString() {
		return "Mark [id=" + id + ", score=" + score + ", teamName=" + teamName
				+ ", code=" + code + ", semester=" + semester + ", courseId="
				+ courseId + ", courseName=" + courseName + ", studentId="
				+ studentId + ", studentName=" + studentName + "]";
	}
	
}
