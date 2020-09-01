package cl.people.example.apirest.controller;

import org.springframework.web.bind.annotation.RestController;

import cl.people.example.apirest.entities.Course;
import cl.people.example.apirest.entities.dto.CourseDto;
import cl.people.example.apirest.service.CourseService;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

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
 * date 25-08-2020
 */
@RestController
@Api(value = "Course", description = "Operations over Course objects")
public class CourseController {

    public static final Logger log = LoggerFactory.getLogger(CourseController.class);

    @Autowired
    private CourseService courseService;

    //Get List
    @PreAuthorize("isAuthenticated()")
    @ApiOperation(value = "Returns a paginated list of existing courses", response = Course.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 204, message = "Not Content"),
        @ApiResponse(code = 400, message = "Client Errors"),
        @ApiResponse(code = 500, message = "Server Errors")
    })
    @Produces(MediaType.APPLICATION_JSON)
    @GetMapping("/courses")
    public Response getCoursesList(@RequestHeader Map<String, String> headers) {
        try {
            List<Course> course = courseService.getCoursesList();

            if(!course.isEmpty()) {
                return Response.status(Response.Status.NO_CONTENT).entity(Course.class).build();
            }
            return Response.status(Response.Status.OK).entity(course).build();   
        }catch(Exception e) {
            return Response.status(Response.Status.NO_CONTENT).entity(Course.class).build();
        }
    }

    //Course By Id
    @PreAuthorize("isAuthenticated()")
    @ApiOperation(value = "Returns the course identified by the given id", response = Course.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Server Errors")
    })
    @Produces(MediaType.APPLICATION_JSON)
    @GetMapping("/courses/{id}")
    public Response getCourseById(@RequestHeader Map<String, String> headers, @PathVariable("id") Long courseId) {
        try {
            Course course = courseService.getCourseById(courseId);
            if(course == null) {
                return Response.status(Response.Status.NOT_FOUND).entity(Course.class).build();
            }
            return Response.status(Response.Status.OK).entity(course).build();
        } catch(Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity(Course.class).build();
        }
    }

    //Create Course
    @PreAuthorize("isAuthenticated()")
    @ApiOperation(value = "Create a course with the data provided in the json body request", response = Course.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 500, message = "Server Errors")
    })
    @Produces(MediaType.APPLICATION_JSON)
    @PostMapping("/courses")
    public Response createCourse(@RequestHeader Map<String, String> headers, 
                                                    @RequestBody @Valid CourseDto courseDto) {                                                        
        try {
            Course newCourse = new Course();
            newCourse.setName(courseDto.getName());
            newCourse.setCode(courseDto.getCode());
            Course courseCreated = courseService.createCourse(newCourse);

            if(courseCreated == null) {
                return Response.status(Response.Status.BAD_REQUEST).entity(Course.class).build();
            }
            return Response.status(Response.Status.OK).entity(courseCreated).build();
        } catch(Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity(Course.class).build();
        }
    }

    //Update Course
    @PreAuthorize("isAuthenticated()")
    @ApiOperation(value = "Update a course with the given id and the body request json", response = Course.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Server Errors")
    })
    @Produces(MediaType.APPLICATION_JSON)
    @PutMapping("/courses/{id}")
    public Response updateCourse(@RequestHeader Map<String, String> headers, 
                                                    @PathVariable("id") Long courseId,
                                                    @RequestBody @Valid CourseDto courseDto) {                                                        
        try {
            Course course = courseService.getCourseById(courseId);
            if (course == null) return Response.status(Response.Status.NOT_FOUND).entity(Course.class).build();
            course.setName(courseDto.getName());
            course.setCode(courseDto.getCode());
            Course courseUpdated = courseService.updateCourse(course);
            return Response.status(Response.Status.OK).entity(courseUpdated).build();
        } catch(Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(Course.class).build();
        }
    }

    //Delete Course
    @PreAuthorize("isAuthenticated()")
    @ApiOperation(value = "Delete a course with the given id", response = Course.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Server Errors")
    })
    @Produces(MediaType.APPLICATION_JSON)
    @DeleteMapping("/courses/{id}")
    public Response deleteCourse(@RequestHeader Map<String, String> headers, 
                                                    @PathVariable("id") Long courseId) {                                                        
        try {
            Course course = courseService.deleteCourse(courseId);
            return Response.status(Response.Status.OK).entity(course).build();
        } catch(Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity(Course.class).build();
        }
    }
    
}