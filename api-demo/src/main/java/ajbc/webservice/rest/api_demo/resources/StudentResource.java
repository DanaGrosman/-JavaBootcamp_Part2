package ajbc.webservice.rest.api_demo.resources;

import java.net.URI;
import java.util.List;

import ajbc.webservice.rest.api_demo.DBservice.StudentDBService;
import ajbc.webservice.rest.api_demo.beans.StudentFilterBean;
import ajbc.webservice.rest.api_demo.models.Student;
import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.core.UriInfo;

@Path("students")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StudentResource {
	StudentDBService studentDB = new StudentDBService();

//	@GET
//	public List<Student> getAllStudents() {
//		return studentDB.getAllStudents();
//	}

	@GET
	@Path("/{id}")
	public Student getStudentById(@PathParam("id") Long id) {
		return studentDB.getStudentById(id);
	}

//	@GET
//	public List<Student> getStudentsByAverage(@PathParam("average") double average) {
//		if (average > 0)
//			return studentDB.getStudentsByAverage(average);
//		return studentDB.getAllStudents();
//
//	}

//	@GET
//	public List<Student> getStudentsByAverageRange(@QueryParam("average") double average, @QueryParam("minAverage") double minAverage,
//			@QueryParam("maxAverage") double maxAverage)
//	{
//		if (average > 0)
//			return studentDB.getStudentsByAverage(average);
//		if (minAverage > 0 && maxAverage > 0)
//			return studentDB.getStudentsByAverageRange(minAverage, maxAverage);
//		return studentDB.getAllStudents();
//	}

	@GET
	public List<Student> getStudentsByAverageRange(@BeanParam StudentFilterBean studentFilter) {
		if (studentFilter.getAverage() > 0)
			return studentDB.getStudentsByAverage(studentFilter.getAverage());
		if (studentFilter.getMinAverage() > 0 && studentFilter.getMaxAverage() > 0)
			return studentDB.getStudentsByAverageRange(studentFilter.getMinAverage(), studentFilter.getMaxAverage());
		return studentDB.getAllStudents();
	}

	// add new student
	@POST
	public Response addStudent(Student student, @Context UriInfo uriInfo) {
		Status status;
		URI uri = null;

		Student currStudent = studentDB.addStudent(student);
		if (currStudent == null)
			status = Status.NOT_MODIFIED;
		else {
			status = Status.CREATED;
			uri = uriInfo.getAbsolutePathBuilder().path(new String(currStudent.getID() + "")).build();
		}
		return Response.status(status).entity(currStudent).location(uri).build();
	}

//	@POST
//	public Student addStudent(Student student) {
//		return studentDB.addStudent(student);
//	}

	// update an existing student
	@PUT
	@Path("/{id}")

	public Student updateStudent(@PathParam("id") long id, Student student) {
		return studentDB.updateStudent(id, student);
	}

	// delete an existing student
	@DELETE
	@Path("/{id}")
	public Student deleteStudent(@PathParam("id") long id) {
		return studentDB.deleteStudent(id);
	}

	// link to another resource
	@Path("/{id}/courses")
	public StudentCourseResource getCourseResource() {
		return new StudentCourseResource();
	}
}
