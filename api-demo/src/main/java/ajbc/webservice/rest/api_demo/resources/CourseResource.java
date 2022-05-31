package ajbc.webservice.rest.api_demo.resources;

import java.util.List;

import ajbc.webservice.rest.api_demo.DBservice.CourseDBService;
import ajbc.webservice.rest.api_demo.models.Course;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("courses")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CourseResource {
	CourseDBService courseDb = new CourseDBService();

	@GET
	public List<Course> getAllCourses() {
		return courseDb.getAllCourses();
	}

	@GET
	@Path("/{id}")
	public Course getCourseById(@PathParam("id") Long id) {
		return courseDb.getCourseById(id);
	}

	// add new course
	@POST
	public Course addCourse(Course course) {
		return courseDb.addCourse(course);
	}

	// update an existing course
	@PUT
	@Path("/{id}")
	public Course updateCourse(@PathParam("id") long id, Course course) {
		return courseDb.updateCourse(id, course);
	}
	
	// delete an existing course
	@DELETE
	@Path("/{id}")
	public Course deleteCourse(@PathParam("id") long id) {
		return courseDb.deleteCourse(id);
	}
}
