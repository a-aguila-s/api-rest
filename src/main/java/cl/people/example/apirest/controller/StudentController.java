package cl.people.example.apirest.controller;

import org.springframework.web.bind.annotation.RestController;

import cl.people.example.apirest.entities.Course;
import cl.people.example.apirest.entities.Student;
import cl.people.example.apirest.entities.dto.StudentDto;
import cl.people.example.apirest.service.StudentService;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author Alex Águila
 * date 26-08-2020
 */
@RestController
@Api(value = "Student", description = "Operations over Student objects")
public class StudentController {

    public static final Logger log = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private StudentService studentService;

    //Get List
    @PreAuthorize("isAuthenticated()")
    @ApiOperation(value = "Returns a paginated list of existing courses", response = Student.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 204, message = "Not Content"),
        @ApiResponse(code = 400, message = "Client Errors"),
        @ApiResponse(code = 500, message = "Server Errors")
    })
    @Produces(MediaType.APPLICATION_JSON)
    @GetMapping("/students")
    public Response getCoursesList(@RequestHeader Map<String, String> headers) {
        try {
            List<Student> studentList = studentService.getStudentsList();

            if(studentList.isEmpty()) {
                return Response.status(Response.Status.NOT_FOUND).entity(Course.class).build();    
            }
            return Response.status(Response.Status.OK).entity(studentList).build();
        } catch(Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity(Course.class).build();
        }
    }

    //Course By Id
    @PreAuthorize("isAuthenticated()")
    @ApiOperation(value = "Returns the course identified by the given id", response = Student.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Server Errors")
    })
    @Produces(MediaType.APPLICATION_JSON)
    @GetMapping("/students/{id}")
    public Response getCourseById(@RequestHeader Map<String, String> headers, @PathVariable("id") Long studentId) {
        try {
            Student student = studentService.getStudentById(studentId);

            if(student == null) {
                return Response.status(Response.Status.NOT_FOUND).entity(Student.class).build();
            }
            return Response.status(Response.Status.OK).entity(student).build();
        } catch(Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity(Student.class).build();
        }
    }

    //Create Course
    @PreAuthorize("isAuthenticated()")
    @ApiOperation(value = "Create a course with the data provided in the json body request", response = Student.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 500, message = "Server Errors")
    })
    @Produces(MediaType.APPLICATION_JSON)
    @PostMapping("/students")
    public Response createCourse(@RequestHeader Map<String, String> headers, 
                                                    @RequestBody @Valid StudentDto studentDto) {                                                        
        try {
            if(studentDto.getAge() <= 18) throw new BadRequestException("Age must be upper than 18") ;
            Student newStudent = new Student();
            newStudent.setRut(studentDto.getRut());
            newStudent.setName(studentDto.getName());
            newStudent.setLastName(studentDto.getLastName());
            newStudent.setAge(studentDto.getAge());
            newStudent.setCourse(studentDto.getCourse());
            Student studentCreated = studentService.createStudent(newStudent);

            if(studentCreated == null) {
                return Response.status(Response.Status.BAD_REQUEST).entity(Student.class).build();
            }
            return Response.status(Response.Status.OK).entity(studentCreated).build();
        } catch(Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(Student.class).build();
        }
    }

    //Update Course
    @PreAuthorize("isAuthenticated()")
    @ApiOperation(value = "Update a course with the given id and the body request json", response = Student.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Server Errors")
    })
    @Produces(MediaType.APPLICATION_JSON)
    @PutMapping("/students/{id}")
    public Response updateCourse(@RequestHeader Map<String, String> headers, 
                                                    @PathVariable("id") Long studentId,
                                                    @RequestBody @Valid StudentDto studentDto) {                                                        
        try {
            if(studentDto.getAge() <= 18) throw new BadRequestException("Age must be upper than 18") ;
            Student student = studentService.getStudentById(studentId);
            if (student == null) return Response.status(Response.Status.NOT_FOUND).entity(Student.class).build();
            student.setRut(studentDto.getRut());
            student.setName(studentDto.getName());
            student.setLastName(studentDto.getLastName());
            student.setAge(studentDto.getAge());
            student.setCourse(studentDto.getCourse());
            Student studentUpdated = studentService.updateStudent(student);

            if(studentUpdated == null) {
                return Response.status(Response.Status.BAD_REQUEST).entity(Student.class).build();
            }
            return Response.status(Response.Status.OK).entity(studentUpdated).build();
        } catch(Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(Student.class).build();
        }
    }

    //Delete Course
    @PreAuthorize("isAuthenticated()")
    @ApiOperation(value = "Delete a course with the given id", response = Student.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Server Errors")
    })
    @Produces(MediaType.APPLICATION_JSON)
    @DeleteMapping("/students/{id}")
    public Response deleteCourse(@RequestHeader Map<String, String> headers, 
                                                    @PathVariable("id") Long studentId) {                                                        
        try {
            Student student = studentService.deleteStudent(studentId);
            return Response.status(Response.Status.OK).entity(student).build();
        } catch(Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity(Student.class).build();
        }
    }
    
}