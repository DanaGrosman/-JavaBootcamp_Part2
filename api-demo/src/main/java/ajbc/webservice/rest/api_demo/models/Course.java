package ajbc.webservice.rest.api_demo.models;

import java.util.ArrayList;
import java.util.List;

public class Course {
	private static long counter = 1000;

	private final Long ID;
	private String name;
	private List<Student> students;

	public Course() {
		this.ID = generateId();
		this.students = new ArrayList<Student>();
	}

	private synchronized long generateId() {
		return counter++;
	}

	public Course(String name) {
		this.ID = generateId();
		this.name = name;
		this.students = new ArrayList<Student>();
	}

	public static long getCounter() {
		return counter;
	}

	public static void setCounter(long counter) {
		Course.counter = counter;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public Long getID() {
		return ID;
	}

	@Override
	public String toString() {
		return "Course [ID=" + ID + ", name=" + name + ", students=" + students + "]";
	}

}
