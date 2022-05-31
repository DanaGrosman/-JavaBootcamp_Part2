package ajbc.webservice.rest.api_demo.DBservice;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ajbc.webservice.rest.api_demo.DB.MyDB;
import ajbc.webservice.rest.api_demo.models.Course;

public class CourseDBService {
	private MyDB db;
	private Map<Long, Course> courses;

	public CourseDBService() {
		db = MyDB.getInstance();
		courses = db.getCourses();
	}

	// returns all the courses from the DB as a list
	public List<Course> getAllCourses() {
		return new ArrayList<Course>(courses.values());
	}

	// get course by id
	public Course getCourseById(Long id) {
		return courses.get(id);
	}

	// add course to DB
	public Course addCourse(Course course) {
		courses.put(course.getID(), course);
		return course;

	}

	// update existing course
	public Course updateCourse(long id, Course courseDetails) {
		if (courses.containsKey(id)) {
			Course currCourse = courses.get(id);
			currCourse.setName(courseDetails.getName());
			currCourse.setStudents(courseDetails.getStudents());
			
			courses.put(id, currCourse);
			return currCourse;
		}
		return null;
	}
	
	// delete a course from map
	public Course deleteCourse(long id) {
		return courses.remove(id);
	}
}
