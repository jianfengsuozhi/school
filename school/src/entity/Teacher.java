package entity;
/**
 * 教师实体
 * @author Edward
 *
 */
public class Teacher {
	private String id;
	private String name;
	public Teacher() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Teacher(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	@Override
	public String toString() {
		return "Teacher [id=" + id + ", name=" + name + "]";
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	

}
