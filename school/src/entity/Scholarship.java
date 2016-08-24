package entity;

public class Scholarship {
	private Integer mark;
	private String name;
	
	public Scholarship() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Scholarship(Integer mark, String name) {
		super();
		this.mark = mark;
		this.name = name;
	}
	public Integer getMark() {
		return mark;
	}
	public void setMark(Integer mark) {
		this.mark = mark;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	

}
