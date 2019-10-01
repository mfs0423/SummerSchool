package entity;

public class Course {

	private int id;
	private String name;
	private String startTime;
	public Course(int id, String name, String startTime) {
		this.id = id;
		this.name = name;
		this.startTime = startTime;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		name = name;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	@Override
	public String toString() {
		return "Course [id=" + id + ", Name=" + name + ", startTime="
				+ startTime + "]";
	}
	
	
	
}
